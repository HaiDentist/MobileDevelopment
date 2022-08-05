package com.hai.dentist.haidentist.ui.auth.sign

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hai.dentist.haidentist.databinding.ActivitySignBinding
import com.hai.dentist.haidentist.ui.auth.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.malkinfo.progressbar.uitel.LoadingDialogSuccess

class SignActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignBinding

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        auth = Firebase.auth
        setupAction()
        playAnimation()
        settingup()

    }


    private fun setupAction() {
        binding.tvLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

    }

    private fun settingup() {
            binding.buttonSignUp.setOnClickListener {
                val name = binding.nameEditText.text.toString().trim()
                val email = binding.emailEditText.text.toString().trim()
                val password = binding.passwordEditText.text.toString().trim()
                binding.nameEditTextLayout.error = null
                binding.emailEditTextLayout.error = null
                when {
                    name.isEmpty() -> {
                        binding.nameEditTextLayout.error = "Masukkan Nama"
                    }
                    email.isEmpty() ->{
                        binding.emailEditTextLayout.error = "Masukkan Email"
                    }

                    else ->{
                        create(email, password)
                    }
                }

            }
        }


    fun create(email: String, password:String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    customDialog()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun customDialog() {
        val loading = LoadingDialogSuccess(this)
        loading.startLoading()
        val handler = Handler()
        handler.postDelayed(object :Runnable{
            override fun run() {
                loading.isDismiss()
                val intent = Intent(this@SignActivity, LoginActivity::class.java)
                startActivity(intent)
            }
        },1000)
    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.imageViewSignup, View.TRANSLATION_X, -30F, 30F).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
            startDelay = 500
        }.start()

        val title = ObjectAnimator.ofFloat(binding.titleTextView, View.ALPHA, 1F).setDuration(500)
        val nameInput =
            ObjectAnimator.ofFloat(binding.nameEditTextLayout, View.ALPHA, 1F).setDuration(500)
        val emailInput =
            ObjectAnimator.ofFloat(binding.emailEditTextLayout, View.ALPHA, 1F).setDuration(500)
        val passwordInput =
            ObjectAnimator.ofFloat(binding.passwordEditTextLayout, View.ALPHA, 1F).setDuration(500)
        val button = ObjectAnimator.ofFloat(binding.buttonSignUp, View.ALPHA, 1F).setDuration(500)
        val signupTitle =
            ObjectAnimator.ofFloat(binding.tvTittleLogin, View.ALPHA, 15F).setDuration(500)
        val signUpButton = ObjectAnimator.ofFloat(binding.tvLogin, View.ALPHA, 15F).setDuration(500)

        //Show animate alternate
        AnimatorSet().apply {
            playSequentially(
                title,
                nameInput,
                emailInput,
                passwordInput,
                button,
                signupTitle,
                signUpButton
            )
            start()
        }
    }

}