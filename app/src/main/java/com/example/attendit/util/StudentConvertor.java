package com.example.attendit.util;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.example.attendit.util.Student;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.Collections;
import java.util.List;

public class StudentConvertor {
    private static Gson gson = new Gson();


    @TypeConverter
    public static ArrayList<Student> stringToObjectList(String data) {
        if (data == null) {
            return  new ArrayList<>();
        }

        Type listType = new TypeToken<ArrayList<Student>>() {
        }.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String ObjectListToString(List<Student> someObjects) {
        return gson.toJson(someObjects);


    }


}
