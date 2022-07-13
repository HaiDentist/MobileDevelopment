package com.dicoding.picodiploma.haidentist.ui.analisis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.haidentist.R
import com.dicoding.picodiploma.haidentist.databinding.ActivityAnalisisBinding
import com.dicoding.picodiploma.haidentist.databinding.ActivityCameraBinding
import com.dicoding.picodiploma.haidentist.ui.home.HomeAdapter

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
    }

//    view?.findViewById<RecyclerView>(R.id.rv_selfcare)?.apply {
//        this.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL,false)
//        LinearLayoutManager.HORIZONTAL
//        adapter = HomeAdapter()
//
////            layoutManager = LinearLayoutManager(
////                this@OfflineConsultationActivity,
////                RecyclerView.HORIZONTAL,
////                false
////            )
//    }


}