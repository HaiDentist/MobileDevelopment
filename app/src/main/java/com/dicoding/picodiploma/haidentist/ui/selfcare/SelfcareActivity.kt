package com.dicoding.picodiploma.haidentist.ui.selfcare

import android.app.Dialog
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.haidentist.MainActivity
import com.dicoding.picodiploma.haidentist.R
import com.dicoding.picodiploma.haidentist.data.local.UserPreference
import com.dicoding.picodiploma.haidentist.databinding.ActivitySelfcareBinding
import com.dicoding.picodiploma.haidentist.ui.home.HomeViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.getField
import com.google.firebase.ktx.Firebase

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "localdata")
class SelfcareActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySelfcareBinding
    private val db = Firebase.firestore
    private lateinit var viewModel: HomeViewModel
    private lateinit var preference: UserPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelfcareBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        preference = UserPreference.getInstance(dataStore)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding.rvCare.visibility = View.GONE
        val adapter = SelfAdapter{ text ->
            customDialog(text)
        }

//        val adapter = RandomHabitAdapter { habit ->
//            val intent = Intent(this, CountDownActivity::class.java)
//            intent.putExtra(HABIT, habit)
//            startActivity(intent)
//        }
        var Hasil : Array<*>
        Hasil = arrayOf("")
        viewModel.loaddisease(preference).observe(this) {
            if (it.disease != "") {
                binding.periksadahulu.visibility = View.GONE
                binding.rvCare.visibility = View.VISIBLE

                db.collection("perawatan").document(it.disease)
                    .get()
                    .addOnSuccessListener { result ->
                        Hasil = result.data?.values!!.toTypedArray()
//                    Log.d(TAG,result.data?.values.toString())
//                    println(result.data?.values.toString())
//                    Toast.makeText(this, result.data?.values.toString(), Toast.LENGTH_LONG).show()
                        adapter.submitData(Hasil)
                    }
                    .addOnFailureListener { exception ->
                        Log.w(ContentValues.TAG, "Error getting documents.", exception)
                    }

            }
        }

                binding.rvTime.apply {
            this.layoutManager = LinearLayoutManager(this@SelfcareActivity, RecyclerView.HORIZONTAL,false)
            LinearLayoutManager.HORIZONTAL
            this.adapter = WaktuAdapter() // Terbalik (Selfadapter dan TimeAdapter)
        }

        binding.buttonBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

//        val adapter = RandomHabitAdapter { habit ->
//            val intent = Intent(this, CountDownActivity::class.java)
//            intent.putExtra(HABIT, habit)
//            startActivity(intent)
//        }
//        viewPager.adapter = adapter

        binding.rvCare.apply {
            this.layoutManager = LinearLayoutManager(this@SelfcareActivity, RecyclerView.VERTICAL, false)
            LinearLayoutManager.VERTICAL
            this.adapter = adapter

        }


    }

    fun showalertDialog (message: String) {

        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Title")
            .setMessage(message)
            .setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which -> dialog.dismiss()  } )
    }

    private fun check() {

    }

    fun customDialog(text : String ) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.custom_dialog)
        dialog.findViewById<TextView>(R.id.dialog_text).text = text
        val btntutup = dialog.findViewById<Button>(R.id.btn_dialog)
        btntutup.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }



}