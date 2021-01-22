package com.example.attendit.database

import androidx.lifecycle.LiveData
import com.example.attendit.util.Student
import com.example.attendit.util.StudentConvertor
import java.util.*
import kotlin.collections.ArrayList

class Repository(private val attendanceDao: attendance_dao,private val classesDao: classes_dao) {

    val classes=classesDao.getAll()

     fun getAttendances(id:Long): LiveData<List<Date>>
    {
        return attendanceDao.getAttendances(id)
    }
    suspend fun addClass(classDetails: ClassDetails):Long
    {
       return classesDao.addClass(classDetails)
    }
    suspend fun addAttendance(attendance: Attendance)
    {
        attendanceDao.addAttendance(attendance)
    }
    suspend fun updateClass(classDetails: ClassDetails)
    {
        classesDao.updateClass(classDetails)
    }
    suspend fun updateAttendance(attendance: Attendance)
    {
        attendanceDao.updateAttendance(attendance)
    }
    suspend fun deleteClass(classDetails: ClassDetails)
    {
        classesDao.deleteClass(classDetails)
    }
    suspend fun deleteAttendance(attendance: Attendance)
    {
        attendanceDao.deleteAttendance(attendance)
    }
    suspend fun deleteAllClasses()
    {
        classesDao.deleteAllClasses()
    }
    suspend fun deleteAllAttendance(id:Int)
    {
        attendanceDao.deleteAllAttendance(id)
    }
    suspend fun incrementClasses(id:Long)
    {
        classesDao.incrementClassesTaken(id)
    }
    suspend fun getTotal(id:Long):Int
    {
        return classesDao.getTotal(id)
    }
     suspend fun getStudents(id: Long):String
      {
          return classesDao.getStudents(id)
      }
    suspend fun setStudents(students: List<Student>,id: Long){
        classesDao.setStudents(StudentConvertor.ObjectListToString(students),id)
    }

    suspend fun getAttendance(id:Long,date: Date):Attendance{
        return attendanceDao.getAttendance(id,date).get(0)
    }

    suspend fun getAttendancesOfDay(id: Long,date:String):List<Attendance>{
        return attendanceDao.getAttendancesOfDay(id,date)
    }



}