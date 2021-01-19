package com.example.attendit.util;

public class Student {
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
}
