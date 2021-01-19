package com.example.attendit.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.attendit.util.StudentConvertor
import com.example.attendit.util.Student

@Entity(tableName = "list_of_classes")
data class ClassDetails (
    @ColumnInfo(name = "class_id")
    @PrimaryKey(autoGenerate = true)
    var class_id: Long,
    @ColumnInfo(name = "class_name")
    var className: String,
    @ColumnInfo(name = "class_subject")
    var subject:String,

    @TypeConverters(StudentConvertor::class)
    @ColumnInfo(name="student_list")
    var student_list:ArrayList<Student>,
    @ColumnInfo(name = "classes_taken")
    var classesTaken: Int
)