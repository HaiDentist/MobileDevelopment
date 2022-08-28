package com.hai.dentist.haidentist.ui.disease

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.hai.dentist.haidentist.R

class DiseaseAdapter(private val onClick: (String) -> Unit):
    RecyclerView.Adapter<DiseaseAdapter.ViewHolder>() {
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
        "White Spot"
    )

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val nama:TextView = view.findViewById(R.id.name_penyakit)
            val butt:CardView = view.findViewById(R.id.butt)

        fun bind(text:String) {
            butt.setOnClickListener {
                onClick(text)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :DiseaseAdapter.ViewHolder =  ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_row_disease, parent, false)
    )

    override fun onBindViewHolder(holder: DiseaseAdapter.ViewHolder, position: Int) {
        holder.nama.text = names[position]
        holder.bind(names[position])
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