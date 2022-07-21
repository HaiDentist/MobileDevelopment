package com.dicoding.picodiploma.haidentist.ui.analisis

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.haidentist.R
import com.dicoding.picodiploma.haidentist.databinding.ActivityAnalisisBinding
import com.dicoding.picodiploma.haidentist.databinding.ActivityCameraBinding
import com.dicoding.picodiploma.haidentist.ui.home.HomeAdapter
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AnalisisActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnalisisBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnalisisBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()



        binding.rvPenyakit.apply {
            this.layoutManager = LinearLayoutManager(this@AnalisisActivity, RecyclerView.VERTICAL,false)
            LinearLayoutManager.VERTICAL
            adapter = AnalisisAdapter()
        }

        binding.buttonSelfcare.setOnClickListener {

        }

//        when(intent.getStringExtra("penyakit").toString()) {
//            "Dental Discoloration" -> {
//                binding.namePenyakit.setText(R.string.koksiodosis)
//                binding.subPenyakit.setText(R.string.sub_koksiodosis)
//                binding.gambarPenyakit.setImageURI()
//                binding.deskripsiPenyakit.setText(R.string.desc_koksiodosis)
//            }
//
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
        binding.shimmer.visibility = View.GONE
    }



}