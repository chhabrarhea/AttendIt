package com.example.attendit.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.attendit.R
import com.example.attendit.database.ClassDetails
import com.example.attendit.databinding.ClassItemBinding

class ClassAdapter  (private val clickListener: (ClassDetails) -> Unit) :
    RecyclerView.Adapter<ClassAdapter.VH>() {
    private val list = ArrayList<ClassDetails>()

    class VH(val binding: com.example.attendit.databinding.ClassItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(s: ClassDetails, clickListener: (ClassDetails) -> Unit) {
            binding.text.setText(s.className+"\n"+s.subject)
            binding.count.setText("${s.student_list.size}")
            
            binding.root.setOnClickListener {
                clickListener(s)
            }
        }
    }

    fun setList(ClassDetailss: List<ClassDetails>) {
        list.clear()
        list.addAll(ClassDetailss)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ClassItemBinding =
            DataBindingUtil.inflate(inflater, R.layout.class_item, null, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(list[position], clickListener)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}