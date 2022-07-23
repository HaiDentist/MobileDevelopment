package com.dicoding.picodiploma.haidentist.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.haidentist.R
import com.dicoding.picodiploma.haidentist.ui.camera.upload.UploadFragment
import com.dicoding.picodiploma.haidentist.ui.disease.DiseaseFragment
import com.dicoding.picodiploma.haidentist.ui.selfcare.SelfcareActivity


class HomeFragment : Fragment() {


    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<HomeAdapter.ViewHolder>? = null

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

        view?.findViewById<RecyclerView>(R.id.rv_selfcare)?.apply {
            this.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL,false)
            LinearLayoutManager.HORIZONTAL
            adapter = HomeAdapter()

//            layoutManager = LinearLayoutManager(
//                this@OfflineConsultationActivity,
//                RecyclerView.HORIZONTAL,
//                false
//            )
        }

        view?.findViewById<View>(R.id.selfcarebut)?.setOnClickListener {
            val intent = Intent(requireContext(),SelfcareActivity::class.java)
            startActivity(intent)
        }
        // TODO: Use the ViewModel
    }

    private fun loadFragment(fragment: Fragment) {
        val transcation = parentFragmentManager.beginTransaction()
        transcation.replace(R.id.mainContainer,fragment)
        transcation.addToBackStack(null)
        transcation.commit()
    }

}