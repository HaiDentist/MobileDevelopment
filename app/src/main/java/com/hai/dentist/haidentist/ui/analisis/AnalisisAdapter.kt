package com.hai.dentist.haidentist.ui.analisis

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.hai.dentist.haidentist.R

class AnalisisAdapter(private val onClick : (String) -> Unit ):
    RecyclerView.Adapter<AnalisisAdapter.ViewHolder>() {

    private var Hasil : Array<*> = arrayOf("")

    fun submitData(data: Array<*>) {
        Hasil = data
        notifyDataSetChanged()
    }

    private val names = arrayOf(
        "Fraktur",
        "Plak",
        "Karies",
        "periodentitis",
        "penyakit hati"
    )

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val nama:TextView = view.findViewById(R.id.name_penyakit)
            val butt:View = view.findViewById(R.id.butt)

            fun bind(text:String) {
            butt.setOnClickListener {
                onClick(text)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :AnalisisAdapter.ViewHolder =  ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_row_disease, parent, false)
    )

    override fun onBindViewHolder(holder: AnalisisAdapter.ViewHolder, position: Int) {
        holder.nama.text = Hasil[position].toString().drop(1).dropLast(1)
        holder.bind(Hasil[position].toString().drop(1).dropLast(1))
    }


    override fun getItemCount(): Int = Hasil.size
}
