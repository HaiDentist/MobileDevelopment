package com.hai.dentist.haidentist.ui.consultation.offline

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hai.dentist.haidentist.databinding.ActivityOfflineConsultationBinding
import com.fimo.aidentist.ui.adapter.DateAdapter
import com.fimo.aidentist.ui.adapter.HourAdapter

class OfflineConsultationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOfflineConsultationBinding
    private lateinit var adapter: DateAdapter
    private lateinit var adapter2: HourAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOfflineConsultationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.buttonBack.setOnClickListener {
            super.onBackPressed()
        }

        binding.rvDate.apply {
            layoutManager = LinearLayoutManager(
                this@OfflineConsultationActivity,
                RecyclerView.HORIZONTAL, false
            )
            adapter = DateAdapter()
        }

        binding.rvTime.apply {
            layoutManager = LinearLayoutManager(
                this@OfflineConsultationActivity,
                RecyclerView.HORIZONTAL,
                false
            )
            adapter = HourAdapter()
        }
    }
}