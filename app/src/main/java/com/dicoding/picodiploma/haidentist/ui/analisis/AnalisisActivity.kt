package com.dicoding.picodiploma.haidentist.ui.analisis

import android.app.Dialog
import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.Toast
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.haidentist.R
import com.dicoding.picodiploma.haidentist.databinding.ActivityAnalisisBinding
import com.dicoding.picodiploma.haidentist.databinding.ActivityCameraBinding
import com.dicoding.picodiploma.haidentist.ui.home.HomeAdapter
import com.dicoding.picodiploma.haidentist.ui.selfcare.SelfcareActivity
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


        val penyakit = intent.getStringExtra("penyakit")
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


        binding.btnSelfcare.setOnClickListener {
            val intent = Intent(this, SelfcareActivity::class.java)
                .putExtra("perawatan", penyakit)
            startActivity(intent)
            Toast.makeText(this, "SelfCare" , Toast.LENGTH_LONG).show()
        }
        binding.buttonRumahSakit.setOnClickListener {
            customDialog()
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

    fun customDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.custom_dialog)

        val btntutup = dialog.findViewById<Button>(R.id.btn_dialog)
        btntutup.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

}