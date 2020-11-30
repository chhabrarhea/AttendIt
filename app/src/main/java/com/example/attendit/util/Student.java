package com.example.attendit.util;

public class Student {
    private int sno;
    private String name;
    private int attended;

    public Student(String name, int sno) {
        this.name = name;
        this.sno = sno;
        this.attended=0;
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
