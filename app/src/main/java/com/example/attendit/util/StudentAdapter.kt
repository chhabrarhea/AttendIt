package com.example.attendit.util



import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.attendit.R
import com.example.attendit.database.ClassDetails
import com.example.attendit.databinding.ClassItemBinding
import com.example.attendit.databinding.StudentItemBinding

class StudentAdapter()  : RecyclerView.Adapter<StudentAdapter.VH>() {

    var students= ArrayList<Student>()
    var total=0

    fun setList(s: List<Student>,t:Int) {
        this.students.clear()
        this.students.addAll(s)
        this.total=t}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = StudentItemBinding.inflate(inflater)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {

        holder.bind(students[position],this.total )
    }

    override fun getItemCount(): Int {

        return this.students.size
    }
    inner class VH(val binding: StudentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(s: Student,total: Int) {

            binding.text.text=("${s.sno}. ${s.name}")
            if (total > 0) {
                binding.count.text=("${(s.attended *100/ total).toFloat()}%")
            } else
                binding.count.visibility= View.GONE
            binding.executePendingBindings()
        }

    }

    fun deleteItem(position: Int){
        students.removeAt(position)
        notifyDataSetChanged();
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, students.size);
    }

}