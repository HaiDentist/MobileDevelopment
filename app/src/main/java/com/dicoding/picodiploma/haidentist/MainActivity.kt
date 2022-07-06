package com.dicoding.picodiploma.haidentist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.dicoding.picodiploma.haidentist.databinding.ActivityMainBinding
import com.dicoding.picodiploma.haidentist.ui.home.HomeFragment
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


        bottomNav = binding.bottomNavigationView as BottomNavigationView
        bottomNav.setOnItemReselectedListener {
                when(it.itemId) {
                    R.id.homeFragment -> {
                        loadFragment(HomeFragment())
                        return@setOnItemReselectedListener
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