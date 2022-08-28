package com.hai.dentist.haidentist.ui.disease.detail

import android.content.ContentValues
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hai.dentist.haidentist.R
import com.hai.dentist.haidentist.databinding.ActivityAnalisisBinding
import com.hai.dentist.haidentist.databinding.ActivityDiseaseDetailBinding

class DiseaseDetailActivity : AppCompatActivity() {

    private val db = Firebase.firestore
    private lateinit var binding: ActivityDiseaseDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDiseaseDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.buttonBack.setOnClickListener {
            finish()
        }

        val penyakit = intent.extras!!.getString("detail")
        binding.namePenyakit.text = penyakit
        db.collection("disease").document(penyakit!!)
            .get()
            .addOnSuccessListener { result ->
                Glide.with(this)
                    .load(result.get("image"))
                    .into(binding.gambarPenyakit)
                binding.deskripsiPenyakit.text = result.get("penjelasan").toString()
                binding.deskripsiPenyebab.text = result.get("penyebab").toString()
                binding.deskripsiResiko.text = result.get("resiko").toString()
                binding.progressing.visibility = View.GONE
                binding.about.visibility = View.VISIBLE
                binding.deskripsiResiko.visibility = View.VISIBLE
                binding.deskripsiPenyebab.visibility = View.VISIBLE
                binding.deskripsiPenyakit.visibility = View.VISIBLE
                binding.deskripsiResiko.visibility = View.VISIBLE
                binding.penyebab.visibility = View.VISIBLE
                binding.resiko.visibility = View.VISIBLE

            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }
    }
}