package com.dicoding.picodiploma.haidentist.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.haidentist.R
import com.dicoding.picodiploma.haidentist.ui.selfcare.SelfAdapter
import java.util.*

class HomeAdapter (private val onClick :(String) -> Unit ): RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    val c = Calendar.getInstance()
    val day = c.get(Calendar.DAY_OF_MONTH)

    private var Hasil : Array<*> = arrayOf("")
    fun submitData(data: Array<*>) {
        Hasil = data
        notifyDataSetChanged()
    }
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val tanggal:TextView = view.findViewById(R.id.tanggal)
            val title : TextView = view.findViewById(R.id.title_home)
            val butt : CardView = view.findViewById(R.id.card_view)

        fun bind(text:String) {
            butt.setOnClickListener {
                onClick(text)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :HomeAdapter.ViewHolder =  ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_row_selfcare, parent, false)
    )

    override fun onBindViewHolder(holder: HomeAdapter.ViewHolder, position: Int) {
        holder.tanggal.text = day.toString()
//        holder.tanggal.text = tanggal[position].toString()
        holder.title.text = Hasil[position].toString().drop(1).dropLast(1)
        holder.bind(Hasil[position].toString().drop(1).dropLast(1))
    }

    override fun getItemCount(): Int = Hasil.size - 1


}



//class SelfAdapter (private val onClick : (String) -> Unit ): RecyclerView.Adapter<SelfAdapter.ViewHolder>() {
//
//    private var Hasil : Array<*> = arrayOf("")
//
//    fun submitData(data: Array<*>) {
//        Hasil = data
//        notifyDataSetChanged()
//    }
//
//
//    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val waktu:TextView = view.findViewById(R.id.waktu)
//        val title:TextView = view.findViewById(R.id.title)
//        val butt: CardView = view.findViewById(R.id.selfcare_view)
//
//
//
//        fun bind(text:String) {
//            butt.setOnClickListener {
//                onClick(text)
//            }
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : SelfAdapter.ViewHolder =  ViewHolder(
//        LayoutInflater.from(parent.context).inflate(R.layout.item_row_care, parent, false)
//    )
//
//    override fun onBindViewHolder(holder: SelfAdapter.ViewHolder, position: Int) {
//        holder.title.text  = Hasil[position].toString().drop(1).dropLast(1)
//        holder.bind(Hasil[position].toString().drop(1).dropLast(1))
////        holder.title.text = title[position].toString()
//    }
//
//    override fun getItemCount(): Int = Hasil.size - 1