package com.dicoding.picodiploma.haidentist.ui.home

import android.app.Dialog
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.Lottie
import com.dicoding.picodiploma.haidentist.R
import com.dicoding.picodiploma.haidentist.data.local.UserPreference
import com.dicoding.picodiploma.haidentist.ui.camera.upload.UploadFragment
import com.dicoding.picodiploma.haidentist.ui.disease.DiseaseFragment
import com.dicoding.picodiploma.haidentist.ui.doctor.DoctorActivity
import com.dicoding.picodiploma.haidentist.ui.selfcare.SelfAdapter
import com.dicoding.picodiploma.haidentist.ui.selfcare.SelfcareActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "localdata")
class HomeFragment : Fragment() {


    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<HomeAdapter.ViewHolder>? = null
    private val db = Firebase.firestore
    private lateinit var preference: UserPreference
    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)



    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        preference = UserPreference.getInstance(requireContext().dataStore)
        view?.findViewById<RecyclerView>(R.id.rv_selfcare)?.visibility = View.GONE
        view?.findViewById<Button>(R.id.button_gigi)?.apply {
            this.setOnClickListener {
                val transcation = parentFragmentManager.beginTransaction()
                transcation.replace(R.id.mainContainer,UploadFragment())
                transcation.addToBackStack(null)
                transcation.commit()
            }
        }


        view?.findViewById<View>(R.id.diseasebut)?.setOnClickListener {
            loadFragment(DiseaseFragment())
        }

        val adapter = HomeAdapter{ text ->
            customDialog(text)
        }
        var Hasil : Array<*>
        Hasil = arrayOf("")

        view?.findViewById<RecyclerView>(R.id.rv_selfcare)?.apply {
            this.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL,false)
            LinearLayoutManager.HORIZONTAL
            this.adapter = adapter
        }



        view?.findViewById<View>(R.id.selfcarebut)?.setOnClickListener {
            val intent = Intent(requireContext(),SelfcareActivity::class.java)
            startActivity(intent)
        }

        view?.findViewById<View>(R.id.doctorbutton)?.setOnClickListener {
//            Toast.makeText(requireContext(), "Coming Soon" , Toast.LENGTH_LONG).show()
            val intent = Intent(requireContext(), DoctorActivity::class.java)
            startActivity(intent)
        }

        view?.findViewById<View>(R.id.clinicbutton)?.setOnClickListener {
            Toast.makeText(requireContext(), "Coming Soon" , Toast.LENGTH_SHORT).show()
        }
        viewModel.loaddisease(preference).observe(requireActivity()) {
            Toast.makeText(requireContext(),it.disease, Toast.LENGTH_LONG).show()
           if (it.disease == "") {
               view?.findViewById<View>(R.id.lottie_loading)?.visibility = View.VISIBLE
               view?.findViewById<RecyclerView>(R.id.rv_selfcare)?.visibility = View.GONE
           }else {

               db.collection("perawatan").document(it.disease)
                   .get()
                   .addOnSuccessListener { result ->

                       Hasil = result.data?.values!!.toTypedArray()
                       adapter.submitData(Hasil)
                       view?.findViewById<View>(R.id.lottie_loading)?.visibility = View.GONE
                       view?.findViewById<RecyclerView>(R.id.rv_selfcare)?.visibility = View.VISIBLE
                   }
                   .addOnFailureListener { exception ->
                       Log.w(ContentValues.TAG, "Error getting documents.", exception)
                   }

           }
        }

    }

    private fun loadFragment(fragment: Fragment) {
        val transcation = parentFragmentManager.beginTransaction()
        transcation.replace(R.id.mainContainer,fragment)
        transcation.addToBackStack(null)
        transcation.commit()
    }

    fun customDialog(text : String ) {
        val dialog = Dialog(requireContext())
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