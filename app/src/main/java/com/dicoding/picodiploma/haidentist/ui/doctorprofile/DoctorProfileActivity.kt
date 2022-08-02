package com.dicoding.picodiploma.haidentist.ui.doctorprofile

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.haidentist.data.model.DoctorModel
import com.dicoding.picodiploma.haidentist.databinding.ActivityDoctorProfileBinding
import com.dicoding.picodiploma.haidentist.ui.consultation.offline.OfflineConsultationActivity
import com.dicoding.picodiploma.haidentist.ui.consultation.online.OnlineConsultationActivity
import com.dicoding.picodiploma.haidentist.ui.doctor.DoctorActivity


class DoctorProfileActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USER = "extra_user"
    }

    private lateinit var nameDoctor: String
    private lateinit var doctorRating: String

    private lateinit var binding: ActivityDoctorProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoctorProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()


        val dataAvatar: ImageView = binding.doctorProfile
        val dataName: TextView = binding.doctorName
        val dataRating: TextView = binding.rating

        val doctor = intent.getParcelableExtra(EXTRA_USER) as DoctorModel?
        val image = doctor?.avatar
        nameDoctor = doctor?.name.toString()
        doctorRating = doctor?.rating.toString()
        Glide.with(this)
            .load(image)
            .circleCrop()
            .into(dataAvatar)
        dataName.text = nameDoctor
        dataRating.text = doctorRating

        binding.buttonBack.setOnClickListener {
            val intent = Intent(this, DoctorActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        binding.onlineConsultation.setOnClickListener {
            val intent = Intent(this, OnlineConsultationActivity::class.java)
            startActivity(intent)
        }

        binding.offlineConsultation.setOnClickListener {
            val intent = Intent(this, OfflineConsultationActivity::class.java)
            startActivity(intent)
        }
    }
}