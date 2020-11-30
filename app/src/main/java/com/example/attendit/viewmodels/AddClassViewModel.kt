package com.example.attendit.viewmodels

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.attendit.database.ClassDetails
import com.example.attendit.database.Repository
import com.example.attendit.util.Event
import com.example.attendit.util.Student
import kotlinx.coroutines.launch
import kotlin.collections.ArrayList

class AddClassViewModel(private val repository: Repository):ViewModel(),Observable {
    private val statusMessage = MutableLiveData<Event<String>>()
    val message: LiveData<Event<String>>
        get() = statusMessage

    fun insertClass(n:String,s:String,student: ArrayList<Student>)
    {
        insert(ClassDetails(0,n,s,student,0))
    }
    fun insert(c: ClassDetails) = viewModelScope.launch {
        val newRowId = repository.addClass(c)
        if (newRowId > -1) {
            statusMessage.value = Event("Subscriber Inserted Successfully $newRowId")
        } else {
            statusMessage.value = Event("Error Occurred")
        }

    }
    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}