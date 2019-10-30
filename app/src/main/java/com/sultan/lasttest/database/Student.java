package com.sultan.lasttest.database;

import java.io.Serializable;
import java.util.List;

public class Student implements Serializable {
    public String email,name,lastName,StudentID , deptID;

    public List<String> course;

    public Student(String email, String name, String lastName, String studentID, List<String> course, List<String> section ,String deptID) {
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        StudentID = studentID;
        this.course = course;
        this.section = section;
        this.deptID=deptID;
    }

    public List<String> section;





    public void Student(Student student){
        this.email=student.email;
        this.name=student.name;
        this.lastName=student.lastName;
        this.StudentID=student.StudentID;
        this.course= student.course;
        this.section = student.section;
        this.deptID = student.deptID;


    }

    public Student(){}


}
