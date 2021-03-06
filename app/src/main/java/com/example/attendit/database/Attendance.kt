package com.example.attendit.database

import android.os.Parcelable
import androidx.room.*
import com.example.attendit.util.DateConvertor
import com.example.attendit.util.StudentConvertor
import com.example.attendit.util.Student
import java.util.*

@Entity(
    foreignKeys = arrayOf(
        ForeignKey(
            entity = ClassDetails::class,
            parentColumns = arrayOf("class_id"),
            childColumns = arrayOf("class_id"),
            onDelete = ForeignKey.CASCADE
        )
    )
)
data class Attendance(
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "class_id",index = true)
    var class_id: Long,
    @TypeConverters(StudentConvertor::class)
    @ColumnInfo(name = "present")
    var present: ArrayList<Student>,
    @ColumnInfo(name = "absent")
    @TypeConverters(StudentConvertor::class)
    var absent: ArrayList<Student>,
    @ColumnInfo(name="absoluteDate")
    @TypeConverters(DateConvertor::class)
     var date:Date,
    @ColumnInfo(name = "relativeDate")
     var relativeDate:String
     )