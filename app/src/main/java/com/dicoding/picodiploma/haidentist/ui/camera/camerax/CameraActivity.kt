package com.dicoding.picodiploma.haidentist.ui.camera.camerax

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.util.Rational
import android.view.Surface
import android.view.View
import android.widget.Toast
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.haidentist.R
import com.dicoding.picodiploma.haidentist.databinding.ActivityCameraBinding
import com.dicoding.picodiploma.haidentist.databinding.LayoutCameraBinding
import com.dicoding.picodiploma.haidentist.ui.analisis.AnalisisActivity
import com.dicoding.picodiploma.haidentist.utils.Classifier
import com.malkinfo.progressbar.uitel.LoadingDialog
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService

class CameraActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCameraBinding
    private var imageFile: File? = null
    private lateinit var outputDirectory: File
    private lateinit var cameraExecutor: ExecutorService
    private var imageCapture: ImageCapture? = null
    private lateinit var cameraBinding: LayoutCameraBinding


    private val mInputSize = 224
    private val mModelPath = "model.tflite"
    private val mLabelPath = "labels.txt"
    private lateinit var classifier: Classifier

    private var cameraSelector: CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        initClassifier()
        //Request Camera Permissions

        if (allPermissionGranted()) {
            startCamera()
        } else {
            ActivityCompat.requestPermissions(
                this,
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }
        cameraBinding = binding.cameraView
        cameraBinding.cameraCaptureButton.setOnClickListener {
            takePhoto()
            binding.check.visibility = View.VISIBLE
            binding.retake.visibility = View.VISIBLE
            binding.layout.visibility = View.VISIBLE
        }

        cameraBinding.switchCamera.setOnClickListener {
            cameraSelector =
                if (cameraSelector.equals(CameraSelector.DEFAULT_BACK_CAMERA)) CameraSelector.DEFAULT_FRONT_CAMERA
                else CameraSelector.DEFAULT_BACK_CAMERA

            startCamera()
        }

        cameraBinding.closeCamera.setOnClickListener { finish() }

        outputDirectory = getOutputDirectory()

        binding.check.setOnClickListener {
            val bitmap = ((binding.image).drawable as BitmapDrawable).bitmap
            val result = classifier.recognizeImage(bitmap)
            val penyakit = result.get(0).title


            runOnUiThread { Toast.makeText(this, result.get(0).title + " " + result.get(0).confidence , Toast.LENGTH_SHORT).show() }

            val loading = LoadingDialog(this)
            loading.startLoading()
            val handler = Handler()
            val intent = Intent(this, AnalisisActivity::class.java)
            intent.putExtra("penyakit",penyakit)
            handler.postDelayed(object :Runnable{
                override fun run() {
                    loading.isDismiss()
                    startActivity(intent)
                }
            },3000)
        }

        binding.retake.setOnClickListener {
            binding.cameraView.root.visibility = View.VISIBLE
            binding.image.visibility = View.GONE
            binding.borderView.visibility = View.VISIBLE
            binding.check.visibility = View.GONE
            binding.retake.visibility = View.GONE
            binding.layout.visibility = View.GONE
        }


    }

    private fun initClassifier() {
        classifier = Classifier(assets, mModelPath, mLabelPath, mInputSize)
    }

    private fun allPermissionGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            this, it
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun getOutputDirectory(): File {
        val mediaDir = externalMediaDirs.firstOrNull().let {
            File(
                it, resources.getString(R.string.app_name)
            ).apply { mkdirs() }
        }
        return if (mediaDir != null && mediaDir.exists())
            mediaDir else filesDir
    }

    private fun takePhoto() {
        //Get a stable reference of the modifiable image capture use case
        val imageCapture = imageCapture ?: return

        //Create time stamped output file to hold the image
        val photoFile = File(
            outputDirectory,
            SimpleDateFormat(
                FILENAME_FORMAT, Locale.US
            ).format(System.currentTimeMillis()) + ".jpg"
        )

        //Create ouput options object which contains file + metaData
        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

        //Set up image capture listener, which is triggered after photo has been taken
        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(this),
            object : ImageCapture.OnImageSavedCallback {
                override fun onError(exc: ImageCaptureException) {
                    Log.e(TAG, "Photo capture failed: ${exc.message}", exc)
                }

                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                    val savedUri = Uri.fromFile(photoFile)
                    onImageCaptured(savedUri)
                    val msg = "Photo capture succeeded: $savedUri"
                    //Toast.makeText(this@CameraActivity, msg, Toast.LENGTH_SHORT).show()
                    Log.d(TAG, msg)
                }
            })
    }

    @SuppressLint("UnsafeOptInUsageError")
    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener(Runnable {
            //Used to bind lifecycle of camera to the lifecycle owner
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            //Preview
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(cameraBinding.viewFinder.createSurfaceProvider())
                }

            imageCapture = ImageCapture.Builder()
                .build()

            //Select back camera as a default
            //val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            val viewPort = ViewPort.Builder(Rational(350, 170), Surface.ROTATION_0).build()
            val useCaseGroup = UseCaseGroup.Builder()
                .addUseCase(preview)
                //.addUseCase(imageAnalyzer)
                .addUseCase(imageCapture!!)
                .setViewPort(viewPort)
                .build()

            try {
                //Unbind use cases before rebinding
                cameraProvider.unbindAll()

                //Bind use case to camera
                cameraProvider.bindToLifecycle(
                    this, cameraSelector, useCaseGroup
                )
                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, imageCapture
                )
            } catch (exc: Exception) {
                Log.e(TAG, "Use case binding failed", exc)
            }
        }, ContextCompat.getMainExecutor(this))
    }

    private fun onImageCaptured(uri: Uri) {
        val file = File(uri.path!!)
        imageFile = file

        Glide.with(binding.image).load(file).into(binding.image)
        showImage()
    }

    private fun showImage() {
        binding.borderView.visibility = View.GONE
        binding.cameraView.root.visibility = View.GONE
        binding.image.visibility = View.VISIBLE
    }


    companion object {
        private const val TAG = "AddTaskDialog"
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    }


}