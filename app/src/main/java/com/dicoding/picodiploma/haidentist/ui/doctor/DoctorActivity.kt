package com.dicoding.picodiploma.haidentist.ui.doctor

import android.content.Intent
import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.haidentist.MainActivity
import com.dicoding.picodiploma.haidentist.R
import com.dicoding.picodiploma.haidentist.data.model.DoctorModel
import com.dicoding.picodiploma.haidentist.databinding.ActivityDoctorBinding
import com.dicoding.picodiploma.haidentist.ui.doctorprofile.DoctorProfileActivity

class DoctorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDoctorBinding
    private lateinit var rvDoctors: RecyclerView

    //Inisialisasi Variable
    private lateinit var dataName: Array<String>
    private lateinit var dataCategory: Array<String>
    private lateinit var dataRating: Array<String>
    private lateinit var dataSchedule: Array<String>
    private lateinit var dataAvatar: TypedArray
    private var list: ArrayList<DoctorModel> = arrayListOf()
    private var listDoctorAdapter = ListDoctorAdapter(list)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoctorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        rvDoctors = binding.rvDoctors
        rvDoctors.setHasFixedSize(true)


        binding.buttonBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
        addItem()
        showRecyclerList()
    }
    private fun showSelectedDoctor(data: DoctorModel) {
        val moveObjectWithIntent = Intent(this@DoctorActivity, DoctorProfileActivity::class.java)
        moveObjectWithIntent.putExtra(DoctorProfileActivity.EXTRA_USER, data)
        startActivity(moveObjectWithIntent)
    }

    private fun prepare() {
        dataName = resources.getStringArray(R.array.name)
        dataCategory = resources.getStringArray(R.array.category)
        dataRating = resources.getStringArray(R.array.rating)
        dataSchedule = resources.getStringArray(R.array.schedule)
        dataAvatar = resources.obtainTypedArray(R.array.avatar)
    }

    private fun addItem(): ArrayList<DoctorModel> {
        prepare()
        for (i in dataName.indices) {
            val doctor = DoctorModel(
                dataName[i],
                dataCategory[i],
                dataRating[i],
                dataSchedule[i],
                dataAvatar.getResourceId(i, -1)
            )
            list.add(doctor)
        }
        dataAvatar.recycle()
        return list
    }

    private fun showRecyclerList() {
        rvDoctors.layoutManager = LinearLayoutManager(this)
        rvDoctors.adapter = listDoctorAdapter
        listDoctorAdapter.setOnItemClickCallback(object : ListDoctorAdapter.OnItemClickCallBack {
            override fun onItemClicked(data: DoctorModel) = showSelectedDoctor(data)
        })
    }

}