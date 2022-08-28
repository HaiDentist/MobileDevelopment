package com.hai.dentist.haidentist.ui.onboarding

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.hai.dentist.haidentist.MainActivity
import com.hai.dentist.haidentist.data.local.UserPreference
import com.hai.dentist.haidentist.databinding.ActivityOnBoardingBinding
import com.hai.dentist.haidentist.ui.auth.login.LoginActivity


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "localdata")
class OnBoarding : AppCompatActivity() {

    private lateinit var binding: ActivityOnBoardingBinding
    private lateinit var viewModel: OnBoardingViewModel
    private lateinit var preference: UserPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        preference = UserPreference.getInstance(dataStore)

        preference.getUser().asLiveData().observe(this) {
            if (it.isLogin) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
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