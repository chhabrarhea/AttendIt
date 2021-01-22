package com.example.attendit.activities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.applandeo.materialcalendarview.EventDay
import com.applandeo.materialcalendarview.listeners.OnDayClickListener
import com.example.attendit.R
import com.example.attendit.database.Database
import com.example.attendit.database.Repository
import com.example.attendit.databinding.FragmentCalendarBinding
import com.example.attendit.util.ClassViewModelFactory
import com.example.attendit.viewmodels.FragmentViewModel
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

private const val ARG_PARAM1 = "class_id"


class CalendarFragment : Fragment(),OnDayClickListener {

    private var classId: Long = -1
    lateinit var events:ArrayList<EventDay>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            classId = it.getLong(ARG_PARAM1)

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCalendarBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        val attendance_dao = Database.getInstance(activity!!.application).ad
        val class_dao = Database.getInstance(activity!!.application).cd
        val repository = Repository(attendance_dao, class_dao)
        val factory = ClassViewModelFactory(repository, classId)
        val viewModel = ViewModelProvider(this, factory).get(FragmentViewModel::class.java)
        events = ArrayList<EventDay>()

        viewModel.events.observe(viewLifecycleOwner, {
            Log.i("calendar", "${it.toString()}")
            for (i in it) {
                val calendar = Calendar.getInstance()
                calendar.time = i
                events.add(EventDay(calendar, R.drawable.ic_baseline_check_24,resources.getColor(R.color.accent)))
            }
            binding.calendar.setEvents(events)
            binding.calendar.setOnDayClickListener(this)
        }
        )



        binding.fab.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, TakeAttendanceActivity::class.java)

            intent.putExtra("class_id", classId)
            Log.i("sd", "$classId")
            startActivity(intent)
        })

        return binding.root
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: Long) =
            CalendarFragment().apply {
                arguments = Bundle().apply {
                    putLong(ARG_PARAM1, param1)

                }
            }
    }

    override fun onDayClick(eventDay: EventDay) {
       if (events.contains(eventDay))
       {
           val df: DateFormat = SimpleDateFormat("MM/dd/yyyy")
           val relativeDate= df.format(eventDay.calendar.time)
           val intent=Intent(activity, AttendanceListActivity::class.java)
           intent.putExtra("class_id", classId)
           intent.putExtra("date", relativeDate)
           startActivity(intent)
       }
    }
}