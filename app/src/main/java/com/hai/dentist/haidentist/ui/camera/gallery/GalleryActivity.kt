package com.hai.dentist.haidentist.ui.camera.gallery

import android.content.Context
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.hai.dentist.haidentist.data.local.UserPreference
import com.hai.dentist.haidentist.data.model.Disease
import com.hai.dentist.haidentist.databinding.ActivityGalleryBinding
import com.hai.dentist.haidentist.ui.analisis.AnalisisActivity
import com.hai.dentist.haidentist.utils.Classifier
import com.malkinfo.progressbar.uitel.LoadingDialog
import com.malkinfo.progressbar.uitel.LoadingDialogSpin

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "localdata")
class GalleryActivity : AppCompatActivity() {


    private final var GALLERY_REQ_CODE = 1000
    private lateinit var binding : ActivityGalleryBinding
    private lateinit var image_gallery :ImageView
    private lateinit var preference: UserPreference
    private val mInputSize = 224
    private val mModelPath = "model.tflite"
    private val mLabelPath = "labels.txt"
    private lateinit var classifier: Classifier
    private val cameraViewModel: GalleryViewModel by viewModels()
    var anu : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        gall()
        super.onCreate(savedInstanceState)
        binding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        preference = UserPreference.getInstance(dataStore)

        initClassifier()
        image_gallery = binding.gambarUpload
        val button_insert = binding.insert
        button_insert.setOnClickListener {
            gall()

        }

        binding.check.setOnClickListener {
            val loading = LoadingDialog(this)
            loading.startLoading()
            val handler = Handler()
            val intent = Intent(this, AnalisisActivity::class.java)
            handler.postDelayed(object :Runnable{
                override fun run() {
                    loading.isDismiss()

                    val bitmap = ((binding.gambarUpload).drawable as BitmapDrawable).bitmap
                    val result = classifier.recognizeImage(bitmap)
                    val penyakit = result[0].title

//            runOnUiThread { Toast.makeText(this, result.get(0).title + " " + result.get(0).confidence , Toast.LENGTH_SHORT).show() }
                    val disease = Disease(
                        disease = result[0].title
                    )
                    cameraViewModel.savedisease(disease,preference)
                    intent.putExtra("penyakit",penyakit)
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
        anu = "terisi"

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