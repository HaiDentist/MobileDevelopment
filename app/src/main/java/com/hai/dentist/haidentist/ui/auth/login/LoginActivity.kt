package com.hai.dentist.haidentist.ui.auth.login

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.hai.dentist.haidentist.MainActivity
import com.hai.dentist.haidentist.data.local.UserPreference
import com.hai.dentist.haidentist.data.model.UserDataModel
import com.hai.dentist.haidentist.databinding.ActivityLoginBinding
import com.hai.dentist.haidentist.ui.auth.sign.SignActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "localdata")
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var preference: UserPreference
    private lateinit var viewModel: LoginViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        preference = UserPreference.getInstance(dataStore)
        auth = Firebase.auth
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        playAnimation()
        setupAction()
    }

    private fun setupAction() {
        binding.tvSignup.setOnClickListener {
            startActivity(Intent(this, SignActivity::class.java))
            finish()
        }
        binding.buttonLogin.setOnClickListener {
            val email = binding.emailEditText.text.toString().trim()
            val password = binding.passwordEditText.text.toString().trim()
            binding.emailEditTextLayout.error = null
            binding.passwordEditTextLayout.error = null
            when {
                email.isEmpty() -> {
                    binding.emailEditTextLayout.error = "Masukkan Email"
                }
                password.isEmpty() ->{
                    binding.passwordEditTextLayout.error = "Masukkan Password"
                }

                else ->{
                    login(email, password)
                }
            }
        }
        binding.tvGuest.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun login(email:String, password:String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    val mode =  UserDataModel(
                        token = user.toString(),
                        isLogin = true
                    )
                    viewModel.saveusers(preference, mode )

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()

                }
            }
    }



    private fun go() {

    }


    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.imageViewLogin, View.TRANSLATION_X, -30F, 30F).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
            startDelay = 500
        }.start()

        val title = ObjectAnimator.ofFloat(binding.titleTextView, View.ALPHA, 1F).setDuration(500)
        val emailInput =
            ObjectAnimator.ofFloat(binding.emailEditTextLayout, View.ALPHA, 1F).setDuration(500)
        val passwordInput =
            ObjectAnimator.ofFloat(binding.passwordEditTextLayout, View.ALPHA, 1F).setDuration(500)
        val button = ObjectAnimator.ofFloat(binding.buttonLogin, View.ALPHA, 1F).setDuration(500)
        val signupTitle =
            ObjectAnimator.ofFloat(binding.tvTittleSignup, View.ALPHA, 15F).setDuration(500)
        val signUpButton =
            ObjectAnimator.ofFloat(binding.tvSignup, View.ALPHA, 15F).setDuration(500)
        val or =
            ObjectAnimator.ofFloat(binding.or, View.ALPHA,15F).setDuration(500)
        val guest = ObjectAnimator.ofFloat(binding.tvGuest, View.ALPHA,15F).setDuration(500)

        //Show animation alternate
        AnimatorSet().apply {
            playSequentially(title, emailInput, passwordInput, button, signupTitle, signUpButton,or,guest)
            start()
        }
    }
}