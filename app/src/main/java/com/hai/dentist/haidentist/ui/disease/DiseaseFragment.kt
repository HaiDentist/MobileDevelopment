package com.hai.dentist.haidentist.ui.disease

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hai.dentist.haidentist.R
import com.hai.dentist.haidentist.ui.disease.detail.DiseaseDetailActivity
import com.hai.dentist.haidentist.ui.disease.detail.DiseaseDetailFragment
import com.hai.dentist.haidentist.ui.home.HomeFragment


class DiseaseFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_disease, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        view?.findViewById<RecyclerView>(R.id.rv_penyakit_all)?.apply {
            this.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL,false)
            LinearLayoutManager.VERTICAL
            this.adapter = DiseaseAdapter{ disease ->
                val intent = Intent(requireContext(), DiseaseDetailActivity::class.java )
                intent.putExtra("detail",disease)
                startActivity(intent)
            }
//            layoutManager = LinearLayoutManager(
//                this@OfflineConsultationActivity,
//                RecyclerView.HORIZONTAL,
//                false
//            )
//            val adapter = RandomHabitAdapter { habit ->
//                val intent = Intent(this, CountDownActivity::class.java)
//                intent.putExtra(HABIT, habit)
//                startActivity(intent)
//            }
        }
        view?.findViewById<ImageView>(R.id.button_back)?.setOnClickListener {
            loadFragment(HomeFragment())
        }

    }

    private fun loadFragment(fragment: Fragment) {
        val transcation = parentFragmentManager.beginTransaction()
        transcation.replace(R.id.mainContainer,fragment)
        transcation.addToBackStack(null)
        transcation.commit()
    }

}