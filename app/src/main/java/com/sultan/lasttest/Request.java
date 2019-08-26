package com.sultan.lasttest;

import java.io.Serializable;

public class Request implements Serializable {

    public String StudentID ,TeacherID ,Time ,status;

    public Request(String studentID, String teacherID, String time, String status) {
        StudentID = studentID;
        TeacherID = teacherID;
        Time = time;
        this.status = status;
    }
    public Request(Request r){
        StudentID = r.StudentID;
        TeacherID = r.TeacherID;
        Time = r.Time;
        status = r.status;
    }

    public String getStudentID() {
        return StudentID;
    }

    public void setStudentID(String studentID) {
        StudentID = studentID;
    }

    public String getTeacherID() {
        return TeacherID;
    }

    public void setTeacherID(String teacherID) {
        TeacherID = teacherID;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
