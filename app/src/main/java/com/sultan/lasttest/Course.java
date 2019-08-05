package com.sultan.lasttest;

import java.io.Serializable;
import java.util.List;

public class Course implements Serializable {
    public String courseCode, courseName, teacherUID;

    public Course(String courseCode,String courseName, String teacherUID){
        this.courseCode=courseCode;
        this.courseName=courseName;
        this.teacherUID=teacherUID;
    }

    public Course(Course course){
        this.courseCode = course.courseCode;
        this.courseName = course.courseName;
        this.teacherUID = course.teacherUID;
    }

}
