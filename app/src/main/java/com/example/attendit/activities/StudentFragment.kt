package com.example.attendit.activities

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.attendit.R
import com.example.attendit.database.Database
import com.example.attendit.database.Repository
import com.example.attendit.databinding.FragmentStudentBinding

import com.example.attendit.util.*
import com.example.attendit.viewmodels.ClassListViewModel
import com.example.attendit.viewmodels.FragmentViewModel
import java.util.*
import kotlin.collections.ArrayList


private const val ARG_PARAM1 = "class_id"



class StudentFragment : Fragment() {

    private var classId: Long = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
           classId= it.getLong(ARG_PARAM1)

        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding= FragmentStudentBinding.inflate(inflater, container, false)
            Log.i("fragment","$classId")
        binding.lifecycleOwner = this
        val attendance_dao= Database.getInstance(activity!!.application).ad
        val class_dao= Database.getInstance(activity!!.application).cd
        val repository= Repository(attendance_dao, class_dao)
        val factory = ClassViewModelFactory(repository,classId)
        val viewModel = ViewModelProvider(this, factory).get(FragmentViewModel::class.java)
        val adapter=StudentAdapter()
        binding.recycle.adapter=adapter

//        adapter.setList(Collections.singletonList(Student("Rhea",9)),viewModel.total)
        adapter.notifyDataSetChanged()


        viewModel.getTotal().invokeOnCompletion {
                adapter.setList(viewModel.student,viewModel.total)
                adapter.notifyDataSetChanged()

            }
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: Long) =
            StudentFragment().apply {
                arguments = Bundle().apply {
                      putLong(ARG_PARAM1, param1)

                }
            }
    }
}




