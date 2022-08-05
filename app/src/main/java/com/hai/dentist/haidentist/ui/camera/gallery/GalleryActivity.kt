package com.hai.dentist.haidentist.ui.camera.gallery

import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.Toast
import com.hai.dentist.haidentist.databinding.ActivityGalleryBinding
import com.hai.dentist.haidentist.ui.analisis.AnalisisActivity
import com.hai.dentist.haidentist.utils.Classifier
import com.malkinfo.progressbar.uitel.LoadingDialog

class GalleryActivity : AppCompatActivity() {


    private final var GALLERY_REQ_CODE = 1000
    private lateinit var binding : ActivityGalleryBinding
    private lateinit var image_gallery :ImageView

    private val mInputSize = 224
    private val mModelPath = "model.tflite"
    private val mLabelPath = "labels.txt"
    private lateinit var classifier: Classifier

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        initClassifier()
        image_gallery = binding.gambarUpload
        val button_insert = binding.insert
        gall()
        button_insert.setOnClickListener {
            gall()
        }

        binding.check.setOnClickListener {
            val bitmap = ((binding.gambarUpload).drawable as BitmapDrawable).bitmap
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
    }

    private fun initClassifier() {
        classifier = Classifier(assets, mModelPath, mLabelPath, mInputSize)
    }

    private fun gall() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, GALLERY_REQ_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if (resultCode == RESULT_OK) {

            if (requestCode==GALLERY_REQ_CODE) {
                image_gallery.setImageURI(data?.data)
            }
        }


    }




}