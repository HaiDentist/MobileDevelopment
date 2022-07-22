package com.dicoding.picodiploma.haidentist.ui.analisis

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.haidentist.R
import com.dicoding.picodiploma.haidentist.databinding.ActivityAnalisisBinding
import com.dicoding.picodiploma.haidentist.databinding.ActivityCameraBinding
import com.dicoding.picodiploma.haidentist.ui.home.HomeAdapter
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AnalisisActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnalisisBinding
    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnalisisBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()



        binding.rvPenyakit.apply {
            this.layoutManager = LinearLayoutManager(this@AnalisisActivity, RecyclerView.VERTICAL,false)
            LinearLayoutManager.VERTICAL
            val adapter = AnalisisAdapter()
            this.adapter = adapter

        }

        db.collection("penyakit").document(intent.getStringExtra("penyakit").toString())
            .get()
            .addOnSuccessListener { result ->
                Glide.with(this)
                    .load(result.get("image"))
                    .into(binding.gambarPenyakit)
                binding.deskripsiPenyakit.text = result.get("desc").toString()
                binding.namePenyakit.text = result.get("nama").toString()
                binding.subPenyakit.text = result.get("sub").toString()
                shimmerend()
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }

        binding.buttonSelfcare.setOnClickListener {

        }

//        when(intent.getStringExtra("penyakit").toString()) {
//            "Dental Discoloration" -> {
//                binding.namePenyakit.setText(R.string.koksiodosis)
//                binding.subPenyakit.setText(R.string.sub_koksiodosis)
//                binding.gambarPenyakit.setImageResource(R.drawable.cocci)
//                Glide.with(this).load
//                binding.deskripsiPenyakit.setText(R.string.desc_koksiodosis)
//            }
//            "Healthy" -> {
//                binding.namePenyakit.setText(R.string.healthy)
//                binding.subPenyakit.setText(R.string.subhealthy)
//                binding.gambarPenyakit.setImageResource(R.drawable.feceshealthy)
//                binding.deskripsiPenyakit.setText(R.string.deschealthy)
//            }
//            "Periodontal Disease" -> {
//                binding.namePenyakit.setText(R.string.newcastle)
//                binding.subPenyakit.setText(R.string.subnewcastle)
//                binding.gambarPenyakit.setImageResource(R.drawable.fesesncd)
//                binding.deskripsiPenyakit.setText(R.string.desc_newcastle)
//            }
//        }
    }

    fun shimmerend() {
        binding.shimmering.visibility = View.GONE
        binding.shimmering2.visibility = View.GONE
        binding.about.alpha = 1f
        binding.deskripsiPenyakit.alpha = 1f
        binding.scrollView2.alpha = 1f
        binding.penyakitLain.alpha = 1f
        binding.rvPenyakit.alpha = 1f
    }

}