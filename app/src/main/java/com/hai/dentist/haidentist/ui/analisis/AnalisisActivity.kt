package com.hai.dentist.haidentist.ui.analisis

import android.app.Dialog
import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hai.dentist.haidentist.MainActivity
import com.hai.dentist.haidentist.R
import com.hai.dentist.haidentist.databinding.ActivityAnalisisBinding
import com.hai.dentist.haidentist.ui.doctor.DoctorActivity
import com.hai.dentist.haidentist.ui.selfcare.SelfcareActivity

class AnalisisActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnalisisBinding
    private val db = Firebase.firestore
    lateinit var Hasil : Array<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnalisisBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()


        Hasil = arrayOf("1","2")
        val adapter = AnalisisAdapter{ text ->
            customDialog(text)
        }

        val penyakit = intent.getStringExtra("penyakit")



//        db.collection("penyakit").document(intent.getStringExtra("penyakit").toString())
        // default db collection diatas
        db.collection("penyakit").document(intent.getStringExtra("penyakit").toString())
            .get()
            .addOnSuccessListener { result ->
                Glide.with(this)
                    .load(result.get("image"))
                    .into(binding.gambarPenyakit)
                binding.deskripsiPenyakit.text = result.get("desc").toString()
                binding.namePenyakit.text = result.get("nama").toString()
                binding.subPenyakit.text = result.get("sub").toString()

                    db.collection("potensi").document(intent.getStringExtra("penyakit").toString())
                        .get()
                        .addOnSuccessListener { hasi ->
                            Hasil = hasi.data?.values!!.toTypedArray()
//                            Hasil = arrayOf(
//                                "1",
//                                "2",
//                                "3"
//                            )
//                            Toast.makeText(this, hasi.data!!.values.toString(),Toast.LENGTH_LONG).show()
//                    Log.d(TAG,result.data?.values.toString())
//                    println(result.data?.values.toString())
//                    Toast.makeText(this, result.data?.values.toString(), Toast.LENGTH_LONG).show()
                            adapter.submitData(Hasil)
                            shimmerend()
                        }
                        .addOnFailureListener {
                            Log.d(TAG, "onCreate: ")
                        }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }

//        db.collection("potensi").document(intent.getStringExtra("penyakit").toString())
//            .get()
//            .addOnSuccessListener { result ->
////                Hasil = result.data?.values!!.toTypedArray()
//                Hasil = arrayOf(
//                    "1",
//                    "2",
//                    "3"
//                )
//                adapter.submitData(Hasil)
//            }
//            .addOnFailureListener { exception ->
//                Log.w(TAG, "Error getting documents.", exception)
//            }


        binding.btnSelfcare.setOnClickListener {
            val intent = Intent(this, SelfcareActivity::class.java)
                .putExtra("perawatan", penyakit)
            startActivity(intent)
            Toast.makeText(this, "SelfCare" , Toast.LENGTH_LONG).show()
        }
        binding.buttonBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.buttonRumahSakit.setOnClickListener {
            startActivity(Intent(this, DoctorActivity::class.java))
            finish()
        }

        binding.rvPenyakit.apply {
            this.layoutManager = LinearLayoutManager(this@AnalisisActivity, RecyclerView.VERTICAL,false)
            LinearLayoutManager.VERTICAL
//            val adapter = SelfAdapter{ text, click ->
//                customDialog(text)
//            }
            this.adapter = adapter

        }

    }

    fun shimmerend() {
        binding.shimmering.visibility = View.GONE
        binding.shimmering2.visibility = View.GONE
        binding.about.alpha = 1f
        binding.deskripsiPenyakit.alpha = 1f
        binding.scrollView2.alpha = 1f
        binding.penyakitLain.alpha = 1f
        binding.rvPenyakit.alpha = 1f
    }


    fun response(text: String) : String {
        var hasil : Any? = ""


        return hasil.toString()
    }

    fun customDialog(text : String) {

        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialog.setContentView(R.layout.custom_dialog_2)
        db.collection("penjelasan").document(text)
            .get()
            .addOnSuccessListener { result ->
                dialog.findViewById<ProgressBar>(R.id.customprog).visibility = View.GONE
                dialog.findViewById<TextView>(R.id.dialog_text).text = result.get("desc").toString()
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }

        val btntutup = dialog.findViewById<Button>(R.id.btn_dialog)
        btntutup.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

//    fun customDialog(text : String ) {
//        val dialog = Dialog(this)
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        dialog.setContentView(R.layout.custom_dialog)
//        dialog.findViewById<TextView>(R.id.dialog_text).text = text

}