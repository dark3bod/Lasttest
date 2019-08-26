package com.sultan.lasttest;

import java.io.Serializable;
import java.util.List;

public class Student implements Serializable {
    public String email,name,lastName,StudentID;
    public List<String> course;
    public void Student(String email,String name,String lastNmae,String StudentID){
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
        this.course= student.course;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<String> getCourse() {
        return course;
    }

    public void setCourse(List<String> course) {
        this.course = course;
    }
}
