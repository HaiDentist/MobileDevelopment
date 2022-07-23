package com.dicoding.picodiploma.haidentist.ui.selfcare

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.haidentist.databinding.ActivitySelfcareBinding

class SelfcareActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySelfcareBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelfcareBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.rvTime.apply {
            this.layoutManager = LinearLayoutManager(this@SelfcareActivity, RecyclerView.HORIZONTAL,false)
            LinearLayoutManager.HORIZONTAL
            adapter = WaktuAdapter() // Terbalik (Selfadapter dan TimeAdapter)
        }

        binding.rvCare.apply {
            this.layoutManager = LinearLayoutManager(this@SelfcareActivity, RecyclerView.VERTICAL, false)
            LinearLayoutManager.VERTICAL
            adapter = SelfAdapter()
        }


    }



}