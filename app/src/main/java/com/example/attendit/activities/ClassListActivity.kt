package com.example.attendit.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.attendit.R
import com.example.attendit.database.ClassDetails
import com.example.attendit.database.Database
import com.example.attendit.database.Repository
import com.example.attendit.databinding.ActivityClassListBinding
import com.example.attendit.util.ClassAdapter
import com.example.attendit.util.ClassViewModelFactory
import com.example.attendit.util.MarginItemDecorator
import com.example.attendit.viewmodels.ClassListViewModel


class ClassListActivity : AppCompatActivity() {
    lateinit var binding: ActivityClassListBinding
    lateinit var viewModel: ClassListViewModel
    lateinit var adapter: ClassAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this, R.layout.activity_class_list)
        val attendance_dao= Database.getInstance(application).ad
        val class_dao= Database.getInstance(application).cd
        val repository= Repository(attendance_dao, class_dao)
        val factory = ClassViewModelFactory(repository,-1)
        viewModel = ViewModelProvider(this, factory).get(ClassListViewModel::class.java)
        binding.lifecycleOwner = this

        binding.addClass.setOnClickListener{
            val intent=Intent(this, AddClassActivity::class.java)
            startActivity(intent)
        }
        binding.recycle.layoutManager = LinearLayoutManager(this)

        binding.recycle.addItemDecoration(
            MarginItemDecorator(
                resources.getDimension(R.dimen.default_padding)
                    .toInt()
            )
        )
        adapter= ClassAdapter{ item: ClassDetails->itemClicked(item) }
        binding.recycle.adapter=adapter
        viewModel.classes.observe(this, Observer {
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })


    }
    private fun itemClicked(sub: ClassDetails)
    {
       val intent=Intent(this, ClassDetailActivity::class.java)
        intent.putExtra("class_id", sub.class_id)
        intent.putExtra("class_name", sub.className)
        intent.putExtra("class_subject", sub.subject)
        startActivity(intent)
    }

}