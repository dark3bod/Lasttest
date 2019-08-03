package com.sultan.lasttest;

public class Student {
    public String email,name,lastName,StudentID,UID;
    public void Student(String email,String name,String lastNmae,String StudentID,String UID){
        this.email=email;
        this.name=name;
        this.lastName=lastNmae;
        this.StudentID=StudentID;
        this.UID=UID;
    }
    public void Student(Student student){
        this.email=student.email;
        this.name=student.name;
        this.lastName=student.lastName;
        this.StudentID=student.StudentID;
    }
}
