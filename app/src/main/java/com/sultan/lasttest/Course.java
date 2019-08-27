package com.sultan.lasttest;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public class Course implements Serializable  {
    public String courseCode, courseName, teacherUID,courseID;
    public List<String> studentUID;
    public Course(){
        courseName = "00";
        courseCode = "00";
        teacherUID = "00";
        studentUID = null;
        courseID = "00";
    }

    public Course(String courseCode, String courseName, String teacherUID, List<String> studentUID, String courseID){
        this.courseCode=courseCode;
        this.courseName=courseName;
        this.teacherUID=teacherUID;
        this.studentUID = studentUID;
        this.courseID = courseID;
    }

    public Course(Course course){
        this.courseCode = course.courseCode;
        this.courseName = course.courseName;
        this.teacherUID = course.teacherUID;
        this.studentUID = course.studentUID;
        this.courseID = course.courseID;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setTeacherUID(String teacherUID) {
        this.teacherUID = teacherUID;
    }

    public void setStudentUID(List<String> studentUID) {
        this.studentUID = studentUID;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getTeacherUID() {
        return teacherUID;
    }

    public List<String> getStudentUID() {
        return studentUID;
    }
}
