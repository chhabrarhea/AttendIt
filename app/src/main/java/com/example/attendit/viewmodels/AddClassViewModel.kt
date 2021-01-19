package com.example.attendit.viewmodels

import android.content.Context
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.attendit.activities.AddClassActivity
import com.example.attendit.activities.ClassListActivity
import com.example.attendit.database.ClassDetails
import com.example.attendit.database.Repository
import com.example.attendit.util.Event
import com.example.attendit.util.Student
import kotlinx.coroutines.launch
import kotlin.collections.ArrayList
import android.content.Intent
class AddClassViewModel(private val repository: Repository):ViewModel(),Observable {
    private val statusMessage = MutableLiveData<Event<Long>>()
    val message: LiveData<Event<Long>>
        get() = statusMessage


    fun insertClass(n:String,s:String,student: ArrayList<Student>)
    {
        insert(ClassDetails(0,n,s,student,0))
    }
    fun insert(c: ClassDetails) = viewModelScope.launch {
        val newRowId = repository.addClass(c)
        if (newRowId > -1) {
            statusMessage.value = Event(newRowId)
        } else {
            statusMessage.value = Event(-1)
        }

    }
    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}