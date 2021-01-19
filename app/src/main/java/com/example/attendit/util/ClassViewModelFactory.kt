package com.example.attendit.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.attendit.database.Repository
import com.example.attendit.viewmodels.AddClassViewModel
import com.example.attendit.viewmodels.ClassListViewModel
import com.example.attendit.viewmodels.FragmentViewModel
import com.example.attendit.viewmodels.TakeAttendanceViewModel

class ClassViewModelFactory(private val repository: Repository,private val id:Long): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AddClassViewModel::class.java)){
            return AddClassViewModel(repository) as T

        }
        else if(modelClass.isAssignableFrom(ClassListViewModel::class.java))
            return ClassListViewModel(repository) as T


        else if(modelClass.isAssignableFrom(FragmentViewModel::class.java))
            return FragmentViewModel(repository,id) as T
        else if(modelClass.isAssignableFrom(TakeAttendanceViewModel::class.java))
            return TakeAttendanceViewModel(repository,id) as T

        throw IllegalArgumentException("Unknown View Model class")
    }


}