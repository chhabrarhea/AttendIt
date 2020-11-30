package com.example.attendit.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.attendit.util.DateConvertor
import com.example.attendit.util.StudentConvertor

@Database(entities = [Attendance::class, ClassDetails::class],version=1)
@TypeConverters(StudentConvertor::class,DateConvertor::class)
abstract  class Database :RoomDatabase() {
    abstract val ad: attendance_dao
    abstract val cd: classes_dao

    companion object{
        @Volatile
        private var INSTANCE:com.example.attendit.database.Database?=null
        fun getInstance(c: Context):com.example.attendit.database.Database
        {
            synchronized(this)
            {
                var i= INSTANCE
                if(i==null)
                {
                    i= Room.databaseBuilder(c.applicationContext,com.example.attendit.database.Database::class.java,"subscriber_db")
                        .build()
                }
                return i
            }
}}}