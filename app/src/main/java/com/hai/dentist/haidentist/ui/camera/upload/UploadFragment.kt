package com.hai.dentist.haidentist.ui.camera.upload

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.hai.dentist.haidentist.databinding.FragmentUploadBinding
import com.hai.dentist.haidentist.ui.camera.camerax.CameraActivity
import com.hai.dentist.haidentist.ui.camera.gallery.GalleryActivity
import com.hai.dentist.haidentist.utils.rotateBitmap
import java.io.File


class UploadFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentUploadBinding? = null
    val binding get() = _binding!!
    private var getFile: File? = null

    //User give permission
    private fun allPermissionGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(requireContext(), it) == PackageManager.PERMISSION_GRANTED
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUploadBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.cameraView.setOnClickListener {
            val intent = Intent(requireContext(), CameraActivity::class.java)
            startActivity(intent)
        }

        binding.iconCamera.setOnClickListener {
            startCameraX()
        }

        binding.iconGallery.setOnClickListener {
            val intent =Intent(requireContext() ,GalleryActivity::class.java )
            startActivity(intent)
        }

        binding.continueCamera.setOnClickListener{
            val intent = Intent(requireContext(), CameraActivity::class.java)

            if (!allPermissionGranted()) {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    REQUIRED_PERMISSIONS,
                    REQUEST_CODE_PERMISSIONS
                )
                return@setOnClickListener
            }
//            startActivity(intent)
            startCameraX()
        }

        return view

    }

    //Start CameraX
    private fun startCameraX() {
        val intent = Intent(requireContext(), CameraActivity::class.java)
        launcherIntentCameraX.launch(intent)
    }

    private val launcherIntentCameraX =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == CAMERA_X_RESULT) {
                val myFile: File = it.data?.getSerializableExtra(EXTRA_PHOTO) as File
                val backCamera = it.data?.getBooleanExtra(BACK_CAMERA, true) as Boolean
                getFile = myFile

                val result = if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) {
                    BitmapFactory.decodeFile(myFile.path)
                } else {
                    rotateBitmap(BitmapFactory.decodeFile(myFile.path), backCamera)
                }

                binding.gambarGigis.setImageBitmap(result)
            }
        }

    companion object {
        const val CAMERA_X_RESULT = 200
        const val EXTRA_PHOTO = "extra_photo"
        const val BACK_CAMERA = "extra_BackCamera"

        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
    }


}