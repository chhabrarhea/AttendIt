package com.example.attendit.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_SET_USER_VISIBLE_HINT
import com.example.attendit.R
import com.example.attendit.databinding.ActivityClassDetailBinding
import com.example.attendit.util.SectionPagerAdapter
import com.google.android.material.appbar.AppBarLayout

class ClassDetailActivity : AppCompatActivity() {
    lateinit var binding:ActivityClassDetailBinding
    lateinit var pagerAdapter: SectionPagerAdapter
    lateinit var className: String
    lateinit var classSubject: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_class_detail)
        className= intent.getStringExtra("class_name").toString()
        classSubject= intent.getStringExtra("class_subject").toString()
        val classId= intent.getLongExtra("class_id",-1)
        binding.toolbar.setTitle("$className - $classSubject")
        binding.toolbar.titleMarginTop=110
        binding.toolbar.setTitleTextAppearance(this,R.style.textAppearance)
         pagerAdapter = SectionPagerAdapter(supportFragmentManager,classId)
        binding.pager.setAdapter(pagerAdapter)
        binding.slidingTabs.setupWithViewPager(binding.pager)
    }
}