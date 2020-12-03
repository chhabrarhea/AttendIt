package com.example.attendit.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.attendit.util.Student

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
    suspend fun incrementClassesTaken(id:Int)
    @Query("Select listOfStudents from list_of_classes where class_id=:id")
    fun getStudents(id: Int):LiveData<ArrayList<Student>>
}