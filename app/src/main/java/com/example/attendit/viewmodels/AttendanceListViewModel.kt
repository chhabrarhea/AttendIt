package com.example.attendit.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.attendit.database.Attendance
import com.example.attendit.database.Repository
import com.example.attendit.util.Student
import com.example.attendit.util.StudentConvertor
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class AttendanceListViewModel(private val repository: Repository, val class_id:Long):ViewModel() {


    lateinit var attendance: List<Attendance>
    lateinit var student:ArrayList<Student>

    fun getAttendances(date: String)=viewModelScope.launch {
       attendance=repository.getAttendancesOfDay(class_id,date) }

    fun getStudents()=viewModelScope.launch {
       student=StudentConvertor.stringToObjectList( repository.getStudents(class_id))

    }

}