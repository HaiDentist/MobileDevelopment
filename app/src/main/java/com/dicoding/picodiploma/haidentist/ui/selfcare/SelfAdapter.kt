package com.dicoding.picodiploma.haidentist.ui.selfcare

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.haidentist.R
class SelfAdapter: RecyclerView.Adapter<SelfAdapter.ViewHolder>() {


    private val tanggal = arrayOf(
       "11:22",
        "12:22",
        "13:22",
        "14:22",
        "15:22",
        "14:22",
        "15:22",
        )

    private val title = arrayOf(
        "Merayap",
        "Kayang",
        "Terbang",
        "Anjayani",
        "Terbang Tinggi",
        "Seleding",
        "Terbang terus",
    )


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val waktu:TextView = view.findViewById(R.id.waktu)
            val title:TextView = view.findViewById(R.id.title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :SelfAdapter.ViewHolder =  ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_row_care, parent, false)
    )

    override fun onBindViewHolder(holder: SelfAdapter.ViewHolder, position: Int) {
       holder.waktu.text = tanggal[position].toString()
        holder.title.text = title[position].toString()
    }

    override fun getItemCount(): Int = tanggal.size
}
