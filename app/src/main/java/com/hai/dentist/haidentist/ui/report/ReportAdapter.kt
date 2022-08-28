package com.hai.dentist.haidentist.ui.report

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.hai.dentist.haidentist.R
import java.util.*

class ReportAdapter (val context: Context): RecyclerView.Adapter<ReportAdapter.ViewHolder>() {


    val c = Calendar.getInstance()

    val year = c.get(Calendar.YEAR)
    val month = c.get(Calendar.MONTH)
    val day = c.get(Calendar.DAY_OF_MONTH)
    val day2 = c.get(Calendar.DAY_OF_YEAR)

    val hour = c.get(Calendar.HOUR_OF_DAY)
    val minute = c.get(Calendar.MINUTE)

    private val tanggal = arrayOf(
        day,
    )

    private val judul = arrayOf(
        "Hasil Analisis Gigi",
    )

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val tanggal:TextView = view.findViewById(R.id.tanggal_report)
            val judul:TextView = view.findViewById(R.id.title_report)
            val hari:TextView = view.findViewById(R.id.hari_report)
            val time:TextView = view.findViewById(R.id.waktu_report)
            val butt : View = view.findViewById(R.id.card_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :ReportAdapter.ViewHolder =  ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_row_report, parent, false)
    )

    override fun onBindViewHolder(holder: ReportAdapter.ViewHolder, position: Int) {
       holder.tanggal.text = tanggal[position].toString()
        holder.judul.text = judul[position]
        bind(context)
    }

    fun bind(context: Context) {
        Toast.makeText(context, "Coming soon",
            Toast.LENGTH_SHORT).show()
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