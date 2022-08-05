package com.hai.dentist.haidentist.ui.analisis

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hai.dentist.haidentist.R

class AnalisisAdapter:
    RecyclerView.Adapter<AnalisisAdapter.ViewHolder>() {
//    PagingDataAdapter<ListStoryItem, PagingAdapter.MyViewHolder>(DIFF_CALLBACK) {
//private val habitMap = LinkedHashMap<PageType, Habit>()
//
//
//    fun submitData(habit: Care) {
//        habitMap[key] = habit
//        notifyDataSetChanged()
//    }

    private val names = arrayOf(
        "Fraktur",
        "Plak",
        "Karies",
        "periodentitis",
        "penyakit hati"
    )

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val nama:TextView = view.findViewById(R.id.name_penyakit)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :AnalisisAdapter.ViewHolder =  ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_row_disease, parent, false)
    )

    override fun onBindViewHolder(holder: AnalisisAdapter.ViewHolder, position: Int) {
        holder.nama.text = names[position]
    }

    override fun getItemCount(): Int = names.size
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