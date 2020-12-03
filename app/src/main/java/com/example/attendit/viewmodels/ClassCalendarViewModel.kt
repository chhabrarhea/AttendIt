package com.example.attendit.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.attendit.database.Repository
import kotlinx.coroutines.launch

class ClassCalendarViewModel(private val repo:Repository,private val id:Int):ViewModel() {

    fun getAttendanceList(id:Int)=viewModelScope.launch {
        repo.getAttendances(id)
    }
}