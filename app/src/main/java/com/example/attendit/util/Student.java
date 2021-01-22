package com.example.attendit.util;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.Objects;

public class Student implements Parcelable {
    private int sno;
    private String name;
    private int attended;

    public Student(String n, int s) {
        this.name = n;
        this.sno = (s);
        this.attended=0;
    }
    public Student(String name,int sno,int attended)
    {
        this.name=name;
        this.sno=sno;
        this.attended=attended;
    }

    protected Student(Parcel in) {
        sno = in.readInt();
        name = in.readString();
        attended = in.readInt();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    public void incrementAttended(){
        this.attended+=1;
    }



    public void setAttended(int attended) {
        this.attended = attended;
    }

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAttended() {
        return attended;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(sno);
        parcel.writeString(name);
        parcel.writeInt(attended);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {
            return false;}
        Student student = (Student) o;
        if (sno == student.sno && attended == student.attended && name.equals(student.name)){
            return true;
        }


        if (name.equals(student.name) && attended!=student.attended){Log.i("equals",student.attended+" attended "+attended+" "+name);}

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sno, name, attended);
    }
}
