package com.example.attendit.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.attendit.database.Attendance
import com.example.attendit.util.Student
import java.util.*
import kotlin.collections.ArrayList

@Dao
interface attendance_dao {
    @Insert
    suspend fun addAttendance(attendance: Attendance)
    @Update
    suspend fun updateAttendance(attendance: Attendance)
    @Delete
    suspend fun deleteAttendance(attendance: Attendance)
    @Query("Select date from Attendance where class_id=:id")
    fun getAttendances(id:Int): LiveData<List<Date>>
    @Query("Select * from Attendance where class_id=:id and date=:date")
    suspend fun getAttendance(id:Int,date: Date):List<Attendance>
    @Query("Delete from Attendance where class_id=:id")
    suspend fun deleteAllAttendance(id: Int)


}