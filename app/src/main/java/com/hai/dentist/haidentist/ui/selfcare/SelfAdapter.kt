package com.hai.dentist.haidentist.ui.selfcare

import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.hai.dentist.haidentist.R

class SelfAdapter (private val onClick : (String,Boolean) -> Unit ): RecyclerView.Adapter<SelfAdapter.ViewHolder>() {

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
        val back:ConstraintLayout = view.findViewById(R.id.selfcare_background)



        fun bind(text:String) {
            butt.setOnClickListener {
                onClick(text,false)
            }

            check.setOnCheckedChangeListener { buttonView, isChecked ->
                when(isChecked) {
                    true -> {
                        back.setBackgroundColor(Color.parseColor("#5FD85F"))
                        title.setPaintFlags(title.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)
                    }
                    false -> {
                        back.setBackgroundColor(Color.TRANSPARENT)
                        title.setPaintFlags(title.getPaintFlags() and Paint.STRIKE_THRU_TEXT_FLAG.inv())
                    }
                }
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
