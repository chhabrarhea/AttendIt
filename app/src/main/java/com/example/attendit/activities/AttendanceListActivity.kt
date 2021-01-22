package com.example.attendit.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.attendit.R
import com.example.attendit.database.Attendance
import com.example.attendit.database.Database
import com.example.attendit.database.Repository
import com.example.attendit.databinding.ActivityAttendanceListBinding
import com.example.attendit.util.AttendanceAdapter
import com.example.attendit.util.ClassViewModelFactory
import com.example.attendit.viewmodels.AttendanceListViewModel
import android.content.Intent
import java.text.SimpleDateFormat

class AttendanceListActivity : AppCompatActivity() {
   var date:String=""
    var class_id:Long=-1
    lateinit var viewModel:AttendanceListViewModel
    lateinit var adapter: AttendanceAdapter
    lateinit var binding:ActivityAttendanceListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_attendance_list)
        date= intent.getStringExtra("date").toString()
        Log.i("calendar hh",date)
        class_id=intent.getLongExtra("class_id",0)

        adapter=AttendanceAdapter { item:Attendance->itemClicked(item)  }
        binding.recycle.adapter=adapter
        binding.recycle.layoutManager=GridLayoutManager(this,1)

        binding.toolbar.setTitle(date)

        val attendance_dao= Database.getInstance(application).ad
        val class_dao= Database.getInstance(application).cd
        val repository= Repository(attendance_dao, class_dao)
        val factory = ClassViewModelFactory(repository,class_id)
        viewModel = ViewModelProvider(this, factory).get(AttendanceListViewModel::class.java)
        viewModel.getAttendances(date).invokeOnCompletion {
           adapter.setList(viewModel.attendance)
            Log.i("calendar","${viewModel.attendance.size}")
            adapter.notifyDataSetChanged()
        }

    }

    fun itemClicked(attendance: Attendance){
        val intent=Intent(this@AttendanceListActivity,AttendanceDetailActivity::class.java)
        intent.putExtra("class_id",class_id)
        intent.putParcelableArrayListExtra("present",attendance.present)
        intent.putParcelableArrayListExtra("absent",attendance.absent)
        intent.putExtra("id",attendance.id)
        val df= SimpleDateFormat("dd / MM / yyyy, HH:mm")
        intent.putExtra("date",df.format(attendance.date))
        startActivity(intent)

    }
}