package com.dicoding.picodiploma.haidentist.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.haidentist.R

class HomeAdapter: RecyclerView.Adapter<HomeAdapter.ViewHolder>() {


    private val tanggal = arrayOf(
        11,
        12,
        13,
        14
    )

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val tanggal:TextView = view.findViewById(R.id.tanggal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :HomeAdapter.ViewHolder =  ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_row_selfcare, parent, false)
    )

    override fun onBindViewHolder(holder: HomeAdapter.ViewHolder, position: Int) {
       holder.tanggal.text = tanggal[position].toString()
    }

    override fun getItemCount(): Int = tanggal.size
}
//
//inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//    val hourItem: TextView = view.findViewById(R.id.hour)
//}
//
//override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourAdapter.ViewHolder =
//    ViewHolder(
//        LayoutInflater.from(parent.context).inflate(R.layout.item_row_hour, parent, false)
//    )
//
//override fun onBindViewHolder(holder: HourAdapter.ViewHolder, position: Int) {
//    holder.hourItem.text = jam[position]
//}
//
//override fun getItemCount(): Int = jam.size