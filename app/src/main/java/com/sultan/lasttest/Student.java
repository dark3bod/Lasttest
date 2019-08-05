package com.sultan.lasttest;

import java.io.Serializable;

public class Student implements Serializable {
    public String email,name,lastName,StudentID;
    public void Student(String email,String name,String lastNmae,String StudentID,String UID){
        this.email=email;
        this.name=name;
        this.lastName=lastNmae;
        this.StudentID=StudentID;
    }
    public void Student(Student student){
        this.email=student.email;
        this.name=student.name;
        this.lastName=student.lastName;
        this.StudentID=student.StudentID;
    }
}
