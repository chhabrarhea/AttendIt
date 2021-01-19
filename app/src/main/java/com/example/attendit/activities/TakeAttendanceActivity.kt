package com.example.attendit.activities

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.attendit.R
import com.example.attendit.database.Database
import com.example.attendit.database.Repository
import com.example.attendit.databinding.ActivityTakeAttendanceBinding
import com.example.attendit.util.ClassViewModelFactory
import com.example.attendit.util.Student
import com.example.attendit.util.StudentAdapter
import com.example.attendit.viewmodels.TakeAttendanceViewModel
import java.util.*
import kotlin.collections.ArrayList


class TakeAttendanceActivity : AppCompatActivity() {
    lateinit var binding: ActivityTakeAttendanceBinding
    var classId: Long = -1;
    lateinit var date: Date
    lateinit var adapter: StudentAdapter
    lateinit var viewModel: TakeAttendanceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_take_attendance)
        classId = intent.getLongExtra("class_id", -1)
        date = Calendar.getInstance().time
        val attendance_dao = Database.getInstance(this).ad
        val class_dao = Database.getInstance(this).cd
        val repository = Repository(attendance_dao, class_dao)
        val factory = ClassViewModelFactory(repository, classId)
        viewModel = ViewModelProvider(this, factory).get(TakeAttendanceViewModel::class.java)
        adapter = StudentAdapter()
        binding.recycle.itemAnimator = DefaultItemAnimator()
        binding.recycle.adapter = adapter
        binding.recycle.layoutManager = GridLayoutManager(this, 1)
        viewModel.getStudents().invokeOnCompletion {
            adapter.setList(viewModel.students, 0)
            adapter.notifyDataSetChanged()
            takeAttendance()
        }

    }

    private fun takeAttendance() {

        val simpleItemTouchCallback: ItemTouchHelper.SimpleCallback = object :
            ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.DOWN,
                ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
            ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }


            private var background = ColorDrawable(Color.RED)
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val pos = viewHolder.adapterPosition

                if (direction == ItemTouchHelper.RIGHT)
                    viewModel.markPresent(pos)
                else if (direction == ItemTouchHelper.LEFT)
                    viewModel.markAbsent(pos)
                adapter.deleteItem(pos)


            }

            override fun onChildDraw(
                c: Canvas, recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float,
                actionState: Int, isCurrentlyActive: Boolean
            ) {
                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
                val itemView: View = viewHolder.itemView
                val backgroundCornerOffset = 20
                if (dX > 0) { // Swiping to the right
                    background = ColorDrawable(Color.GREEN)
                    background.setBounds(
                        itemView.getLeft(), itemView.getTop(),
                        itemView.getLeft() + dX.toInt() + backgroundCornerOffset,
                        itemView.getBottom()
                    )

                } else if (dX < 0) { // Swiping to the left
                    background = ColorDrawable(Color.RED)
                    background.setBounds(itemView.getRight() + dX.toInt() - backgroundCornerOffset, itemView.getTop(), itemView.getRight(), itemView.getBottom()
                    )

                } else { // view is unSwiped
                    background.setBounds(0, 0, 0, 0)
                }
                background.draw(c)
            }
        }
        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(binding.recycle)

    }

    fun saveAttendance(view: View)
    {
        if(adapter.itemCount!=0)
            Toast.makeText(this,"Mark all students first!",Toast.LENGTH_SHORT).show()
        else
        {
            viewModel.setAttendance().invokeOnCompletion {
                   Toast.makeText(this,"Attendance recorded!",Toast.LENGTH_SHORT).show()
                super.onBackPressed()
            }
        }
    }

}