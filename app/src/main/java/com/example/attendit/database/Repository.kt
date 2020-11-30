package com.example.attendit.database

class Repository(private val attendanceDao: attendance_dao,private val classesDao: classes_dao) {

    val classes=classesDao.getAll()

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
    suspend fun incrementClasses(id:Int)
    {
        classesDao.incrementClassesTaken(id)
    }

}