package com.example.attendit.util;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.example.attendit.util.Student;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.Collections;
import java.util.List;

public class StudentConvertor implements Serializable {
    private static Gson gson = new Gson();


    @TypeConverter
    public static ArrayList<Student> stringToObjectList(String data) {
        if (data == null) {
            return null;
        }

        Type listType = new TypeToken<List<Student>>() {
        }.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String ObjectListToString(List<Student> someObjects) {
        return gson.toJson(someObjects);


    }


}
