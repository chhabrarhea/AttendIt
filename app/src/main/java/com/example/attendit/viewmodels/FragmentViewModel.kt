package com.example.attendit.viewmodels

import android.util.Log
import androidx.databinding.Observable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.attendit.database.Repository
import com.example.attendit.util.Student
import com.example.attendit.util.StudentConvertor
import kotlinx.coroutines.launch

class FragmentViewModel  (private val repository: Repository, private val id:Long): ViewModel() , Observable {

    var events=repository.getAttendances(id)
    lateinit var student:ArrayList<Student>
     var total:Int=0

    fun getTotal() = viewModelScope.launch {
         total=repository.getTotal(id)
        Log.i("Students","$total")
        student=StudentConvertor.stringToObjectList(repository.getStudents(id))
        student.forEach {
            Log.i("Students ${it.sno}. ${it.name}","${it.attended}")
        }
    }




    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}