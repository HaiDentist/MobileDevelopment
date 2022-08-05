package com.hai.dentist.haidentist.ui.selfcare

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hai.dentist.haidentist.R
import java.util.*

class WaktuAdapter: RecyclerView.Adapter<WaktuAdapter.ViewHolder>() {

    val c = Calendar.getInstance()

    val year = c.get(Calendar.YEAR)
    val month = c.get(Calendar.MONTH)
    val day = c.get(Calendar.DAY_OF_MONTH)
    val day2 = c.get(Calendar.DAY_OF_YEAR)

    val hour = c.get(Calendar.HOUR_OF_DAY)
    val minute = c.get(Calendar.MINUTE)

    val nama = c.get(Calendar.DAY_OF_WEEK)

    private val tanggal = arrayOf(
        day,
        day.plus(1),
        day.plus(2),
        day.plus(3),
        day.plus(4),
        day.plus(5),
        day.plus(6),
    )

    private val warna = arrayOf(
        R.color.blue_primary,
        R.color.gray,
        R.color.gray,
        R.color.gray,
        R.color.gray,
        R.color.gray,
        R.color.gray,
    )


    private val hari = arrayOf(
        getNameOfDay(year, day2),
        getNameOfDay(year, day2.plus(1)),
        getNameOfDay(year, day2.plus(2)),
        getNameOfDay(year, day2.plus(3)),
        getNameOfDay(year, day2.plus(4)),
        getNameOfDay(year, day2.plus(5)),
        getNameOfDay(year, day2.plus(6)),
        )

    fun getNameOfDay(year: Int, dayOfYear: Int): String? {
        val calendar = Calendar.getInstance()
        calendar[Calendar.YEAR] = year
        calendar[Calendar.DAY_OF_YEAR] = dayOfYear
        val days =
            arrayOf("Sun","Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
        val dayIndex = calendar[Calendar.DAY_OF_WEEK]
        return days[dayIndex - 1]
    }

//    var inFormat: SimpleDateFormat = SimpleDateFormat("dd-MM-yyyy")
//    var date: Date = inFormat.parse(day)
//    var outFormat: SimpleDateFormat = SimpleDateFormat("EEEE")
//    var goal: String = outFormat.format(date)


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tanggal:TextView = view.findViewById(R.id.tanggal)
        val hari:TextView = view.findViewById(R.id.hari)
        val back:View = view.findViewById(R.id.backback)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :WaktuAdapter.ViewHolder =  ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_row_time, parent, false)
    )

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: WaktuAdapter.ViewHolder, position: Int) {
       holder.tanggal.text = tanggal[position].toString()
        holder.back.setBackgroundResource(warna[position])
        holder.hari.text = hari[position].toString()
    }

    override fun getItemCount(): Int = tanggal.size
}
