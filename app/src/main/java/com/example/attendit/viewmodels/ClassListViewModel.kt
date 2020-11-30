package com.example.attendit.viewmodels

import androidx.databinding.Observable
import androidx.lifecycle.ViewModel
import com.example.attendit.database.Repository
import android.content.Intent
import com.example.attendit.database.ClassDetails

class ClassListViewModel (private val repository: Repository):ViewModel() ,Observable{
    val classes=repository.classes


    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}