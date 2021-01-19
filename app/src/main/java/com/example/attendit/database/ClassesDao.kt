package com.example.attendit.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.attendit.util.Student
import com.example.attendit.util.StudentConvertor

@Dao
interface classes_dao {
    @Insert
    suspend fun addClass(classDetails: ClassDetails):Long
    @Delete
    suspend fun deleteClass(classDetails: ClassDetails)
    @Update
    suspend fun updateClass(classDetails: ClassDetails)
    @Query("Select * from list_of_classes")
    fun getAll(): LiveData<List<ClassDetails>>
    @Query("Delete from list_of_classes")
    suspend fun deleteAllClasses()
    @Query("UPDATE list_of_classes SET classes_taken = classes_taken + 1 WHERE class_id = :id")
    suspend fun incrementClassesTaken(id:Long)
    @Query("Select student_list from list_of_classes where class_id=:id")
    suspend fun getStudents(id: Long):String
    @Query("Select classes_taken from list_of_classes where class_id=:id")
    suspend fun getTotal(id:Long):Int
    @TypeConverter()
    @Query("UPDATE list_of_classes SET student_list=:list WHERE class_id=:id")
    suspend fun setStudents(list:String,id: Long)
}