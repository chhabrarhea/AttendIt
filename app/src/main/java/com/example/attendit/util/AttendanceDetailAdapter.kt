package com.example.attendit.util

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.Log

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.attendit.R
import com.example.attendit.databinding.AttendanceDetailCardBinding

class AttendanceDetailAdapter(val present:ArrayList<Student>,val context:Context):RecyclerView.Adapter<AttendanceDetailAdapter.Viewholder>() {

    val students=ArrayList<Student>()

    fun setList(s:List<Student>){
        this.students.clear()
        this.students.addAll(s)
        for(i in present)
            Log.i("sd","${i.name}")
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AttendanceDetailCardBinding.inflate(inflater)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        if (present.contains(students.get(position))) {
            holder.bind(students.get(position), true, context)

        }

        else{
            holder.bind(students.get(position),false,context)
            Log.i("fs","${students.get(position).name}")
        }


    }


    override fun getItemCount(): Int {
        return students.size
    }

    class Viewholder(val binding:AttendanceDetailCardBinding):RecyclerView.ViewHolder(binding.root){


        fun bind(student: Student, present:Boolean, context: Context){
            binding.name.text="${student.sno}. ${student.name}"
            if(present){
                binding.card.setCardBackgroundColor(Color.parseColor("#006064"))
                binding.icon.setImageDrawable(context.getDrawable(R.drawable.ic_baseline_check_24))
            }
            else{
                binding.card.setCardBackgroundColor(Color.parseColor("#7f0000"))
                binding.icon.setImageDrawable(context.getDrawable(R.drawable.ic_baseline_close_24))
            }
        }
    }
}