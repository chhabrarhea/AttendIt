package com.example.attendit.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.content.Intent
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.attendit.database.Attendance
import com.example.attendit.databinding.AttendanceItemBinding
import com.example.attendit.databinding.StudentItemBinding
import java.text.DateFormat
import java.text.SimpleDateFormat

class AttendanceAdapter(private val clickListener: (Attendance) -> Unit) : RecyclerView.Adapter<AttendanceAdapter.Viewholder>() {

    val attendances=ArrayList<Attendance>()

    fun setList(a:List<Attendance>){
        this.attendances.clear()
        this.attendances.addAll(a)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AttendanceItemBinding.inflate(inflater)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
       holder.bind(attendances.get(position),clickListener)
    }

    override fun getItemCount(): Int {
        return attendances.size
    }
    class Viewholder(val binding:AttendanceItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind (attendance:Attendance, clickListener: (Attendance) -> Unit) {
            val df=SimpleDateFormat("dd / MM / yyyy, HH:mm")
            binding.name.text = df.format(attendance.date)
            binding.root.setOnClickListener{
                clickListener(attendance)
            }
        }

    }
}