package com.dicoding.picodiploma.haidentist.ui.report

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.haidentist.R

class ReportAdapter: RecyclerView.Adapter<ReportAdapter.ViewHolder>() {


    private val tanggal = arrayOf(
        11,
        12,
        13,
        14
    )

    private val judul = arrayOf(
        "Dental Discoloration",
        "Fraktur",
        "Caries",
        "White Spot"
    )

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val tanggal:TextView = view.findViewById(R.id.tanggal_report)
            val judul:TextView = view.findViewById(R.id.title_report)
            val hari:TextView = view.findViewById(R.id.hari_report)
            val time:TextView = view.findViewById(R.id.waktu_report)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :ReportAdapter.ViewHolder =  ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_row_report, parent, false)
    )

    override fun onBindViewHolder(holder: ReportAdapter.ViewHolder, position: Int) {
       holder.tanggal.text = tanggal[position].toString()
        holder.judul.text = judul[position]
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