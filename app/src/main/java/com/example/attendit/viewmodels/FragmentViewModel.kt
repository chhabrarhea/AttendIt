package com.example.attendit.viewmodels

import androidx.databinding.Observable
import androidx.lifecycle.ViewModel
import com.example.attendit.database.Repository

class FragmentViewModel  (private val repository: Repository, private val id:Int): ViewModel() , Observable {
    var students=repository.getStudents(id)
    var events=repository.getAttendances(id)



    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}