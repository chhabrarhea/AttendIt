package com.example.attendit.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.attendit.R
import com.example.attendit.database.Database
import com.example.attendit.database.Repository
import com.example.attendit.databinding.ActivityAttendanceDetailBinding
import com.example.attendit.util.AttendanceDetailAdapter
import com.example.attendit.util.ClassViewModelFactory
import com.example.attendit.util.Student
import com.example.attendit.viewmodels.AttendanceListViewModel

class AttendanceDetailActivity : AppCompatActivity() {
    var class_id:Long=-1;
    var id:Int=-1
    var date=""
    lateinit var present:ArrayList<Student>
    lateinit var viewModel:AttendanceListViewModel
    lateinit var adapter: AttendanceDetailAdapter
    lateinit var binding:ActivityAttendanceDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_attendance_detail)

        class_id=intent.getLongExtra("class_id",-1)
        id=intent.getIntExtra("id",-1)
        date= intent.getStringExtra("date").toString()
        present= intent.getParcelableArrayListExtra<Student>("present")!!

        adapter= AttendanceDetailAdapter(present,this)
        binding.recycle.adapter=adapter
        binding.recycle.layoutManager=GridLayoutManager(this,1)


        val attendance_dao= Database.getInstance(application).ad
        val class_dao= Database.getInstance(application).cd
        val repository= Repository(attendance_dao, class_dao)
        val factory = ClassViewModelFactory(repository,class_id)
        viewModel = ViewModelProvider(this, factory).get(AttendanceListViewModel::class.java)

        viewModel.getStudents().invokeOnCompletion {
            adapter.setList(viewModel.student)
            adapter.notifyDataSetChanged()
        }

    }
}