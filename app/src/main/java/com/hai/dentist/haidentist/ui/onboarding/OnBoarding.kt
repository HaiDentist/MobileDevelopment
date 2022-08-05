package com.hai.dentist.haidentist.ui.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.hai.dentist.haidentist.databinding.ActivityOnBoardingBinding
import com.hai.dentist.haidentist.ui.auth.login.LoginActivity

class OnBoarding : AppCompatActivity() {

    private lateinit var binding: ActivityOnBoardingBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        setupAction()
    }

    private fun setupAction() {
        binding.buttonGetStarted.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }
    }
}