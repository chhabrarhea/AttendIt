package com.example.attendit.viewmodels

import android.util.Log
import androidx.databinding.Observable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.attendit.database.Attendance
import com.example.attendit.database.Repository
import com.example.attendit.util.Student
import com.example.attendit.util.StudentConvertor
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class TakeAttendanceViewModel(private val repository: Repository, private val id:Long): ViewModel() ,
    Observable  {
    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        TODO("Not yet implemented")
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        TODO("Not yet implemented")
    }

    var events=repository.getAttendances(id)
    val  present=ArrayList<Student>()
    val  absent=ArrayList<Student>()
    lateinit var students:ArrayList<Student>
    lateinit var students1:ArrayList<Student>
    fun getStudents()=viewModelScope.launch{
        students=StudentConvertor.stringToObjectList(repository.getStudents(id))
        students1= ArrayList()
        students1.addAll(students)
    }
     fun setAttendance()=viewModelScope.launch {
         val attendance=Attendance(0,id,present,absent,Calendar.getInstance().time)
         present.forEach {
            val pos= students.indexOf(it)
             Log.i("Students","foreach ${it.name} $pos ")
             it.incrementAttended()
             students.removeAt(pos)
             students.add(pos,it) }
         repository.setStudents(students,id)
         repository.addAttendance(attendance)
         repository.incrementClasses(id)
     }
    fun markPresent (pos:Int){
        present.add(students1.get(pos))
        students1.removeAt(pos)
        Log.i("Students","mark present ${students.get(pos).name}")
    }
    fun markAbsent (pos:Int){
        absent.add(students1.get(pos))
        students1.removeAt(pos)

    }

}