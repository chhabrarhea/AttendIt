package com.example.attendit.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.attendit.R
import com.example.attendit.database.ClassDetails
import com.example.attendit.database.Database
import com.example.attendit.database.Repository
import com.example.attendit.databinding.ActivityAddClassBinding
import com.example.attendit.viewmodels.AddClassViewModel
import com.example.attendit.util.ClassViewModelFactory
import com.example.attendit.util.Student

class AddClassActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddClassBinding
    lateinit var student: Student
    lateinit var students:ArrayList<Student>
    lateinit var viewModel: AddClassViewModel
    var i=1;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this, R.layout.activity_add_class)
        students= ArrayList()
        val attendance_dao=Database.getInstance(application).ad
        val class_dao=Database.getInstance(application).cd
        val repository=Repository(attendance_dao,class_dao)
        val factory = ClassViewModelFactory(repository)
        viewModel = ViewModelProvider(this,factory).get(AddClassViewModel::class.java)

        binding.lifecycleOwner = this

        viewModel.message.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })
        binding.next.setOnClickListener {
            val name=binding.studentName.text.toString()
            if (name.equals(""))
                binding.studentName.setError("Cannot be empty")
            else
            {

                student=Student(name,i)
                students.add(student)
                binding.studentName.setText("")
                binding.sno.setText("${++i}. ")
            }

        }

        binding.submit.setOnClickListener {
            if(binding.name.text==null || binding.name.text.toString().equals("")) {
                binding.nameLayout.isErrorEnabled = true
                binding.name.setError("Cannot be empty")
                return@setOnClickListener
            }
            else
                binding.nameLayout.isErrorEnabled = false

            if (binding.subject.text==null || binding.subject.text.toString().equals(""))
            {
                binding.subjectLayout.isErrorEnabled = true
                binding.subject.setError("Cannot be empty")
                return@setOnClickListener
            }
            else
                binding.subjectLayout.isErrorEnabled = true
            viewModel.insertClass(binding.name.text.toString(),binding.subject.text.toString(),students)


        }


    }

    }
