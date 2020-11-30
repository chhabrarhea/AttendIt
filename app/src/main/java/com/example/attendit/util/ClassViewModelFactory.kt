package com.example.attendit.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.attendit.database.Repository
import com.example.attendit.viewmodels.AddClassViewModel
import com.example.attendit.viewmodels.ClassListViewModel

class ClassViewModelFactory(private val repository: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AddClassViewModel::class.java)){
            return AddClassViewModel(repository) as T

        }
        else if(modelClass.isAssignableFrom(ClassListViewModel::class.java))
            return ClassListViewModel(repository) as T
        throw IllegalArgumentException("Unknown View Model class")
    }


}