package com.example.attendit.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.attendit.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "class_id"



class StudentFragment : Fragment() {

    private var classId: Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            classId = it.getInt(ARG_PARAM1)

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        return inflater.inflate(R.layout.fragment_student, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: Int) =
            StudentFragment().apply {
                arguments = Bundle().apply {
                    classId?.let { putInt(ARG_PARAM1, it) }

                }
            }
    }
}