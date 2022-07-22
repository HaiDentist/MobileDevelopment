package com.dicoding.picodiploma.haidentist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.dicoding.picodiploma.haidentist.databinding.ActivityMainBinding
import com.dicoding.picodiploma.haidentist.ui.camera.upload.UploadFragment
import com.dicoding.picodiploma.haidentist.ui.home.HomeFragment
import com.dicoding.picodiploma.haidentist.ui.profile.ProfileFragment
import com.dicoding.picodiploma.haidentist.ui.report.ReportFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {

    lateinit var bottomNav : BottomNavigationView
    lateinit var nav :NavigationBarView

    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadFragment(HomeFragment())
        supportActionBar?.hide()

        bottomNav = binding.bottomNavigationView

        bottomNav.setOnItemReselectedListener {
                when(it.itemId) {
                    R.id.homeFragment -> {
                        loadFragment(HomeFragment())
                    }
                    R.id.cameraResultActivity -> {
                        loadFragment(UploadFragment())

                    }
                    R.id.messageFragment -> {
                        loadFragment(ReportFragment())
                    }
                }
        }

        bottomNav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.homeFragment -> {

                    loadFragment(HomeFragment())

                    return@setOnItemSelectedListener true
                }
                R.id.cameraResultActivity -> {
                    loadFragment(UploadFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.profileFragment -> {
                    loadFragment(ProfileFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.messageFragment -> {
                    loadFragment(ReportFragment())
                    return@setOnItemSelectedListener true
                }
                else -> {
                    false
                }
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transcation = supportFragmentManager.beginTransaction()
        transcation.replace(R.id.mainContainer,fragment)
        transcation.addToBackStack(null)
        transcation.commit()
    }

}