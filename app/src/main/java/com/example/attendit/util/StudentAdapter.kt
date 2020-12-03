package com.example.attendit.util



import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.attendit.R
import com.example.attendit.database.ClassDetails
import com.example.attendit.databinding.ClassItemBinding
import com.example.attendit.databinding.StudentItemBinding

class StudentAdapter  ( public var total:Int) :
    RecyclerView.Adapter<StudentAdapter.VH>() {

    public var students= ArrayList<Student>()
    class VH(val binding: StudentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(s: Student,total: Int) {
            binding.text.setText("${s.sno}. ${s.name}")
            binding.count.setText("${s.attended/total}")


        }
    }

    fun setList(s: List<Student>) { students.clear()
        students.addAll(s)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)
        val binding: StudentItemBinding =
            DataBindingUtil.inflate(inflater, R.layout.student_item, null, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(students[position],total )
    }

    override fun getItemCount(): Int {
        return students.size
    }
}