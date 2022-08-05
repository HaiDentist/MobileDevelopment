package com.dicoding.picodiploma.haidentist.ui.selfcare

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.haidentist.R

class SelfAdapter (private val onClick : (String) -> Unit ): RecyclerView.Adapter<SelfAdapter.ViewHolder>() {



    private var Hasil : Array<*> = arrayOf("")

    fun submitData(data: Array<*>) {
        Hasil = data
        notifyDataSetChanged()
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val waktu:TextView = view.findViewById(R.id.waktu)
            val title:TextView = view.findViewById(R.id.title)
        val butt:CardView = view.findViewById(R.id.selfcare_view)
        val check:CheckBox = view.findViewById(R.id.check)



        fun bind(text:String) {
            butt.setOnClickListener {
                onClick(text)
            }
            if (check.isChecked == true) {
                title.text = "fuck"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :SelfAdapter.ViewHolder =  ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_row_care, parent, false)
    )

    override fun onBindViewHolder(holder: SelfAdapter.ViewHolder, position: Int) {
        holder.title.text  = Hasil[position].toString().drop(1).dropLast(1)
        holder.bind(Hasil[position].toString().drop(1).dropLast(1))
//        holder.title.text = title[position].toString()
    }

    override fun getItemCount(): Int = Hasil.size - 1







}
