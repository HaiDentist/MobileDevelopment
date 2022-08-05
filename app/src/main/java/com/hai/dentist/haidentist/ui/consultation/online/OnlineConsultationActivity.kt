package com.hai.dentist.haidentist.ui.consultation.online

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.hai.dentist.haidentist.databinding.ActivityOnlineConsultationBinding

class OnlineConsultationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnlineConsultationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnlineConsultationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.buttonBack.setOnClickListener {
            super.onBackPressed()
        }

        binding.messageRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@OnlineConsultationActivity)
            adapter = MessageAdapter("Doctor Name")
        }
    }
}