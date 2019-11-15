package com.sultan.lasttest.database;

import java.io.Serializable;

public class request implements Serializable {



    public String CourseID;
    public String reqID;
    public String StudentID;
    public String TeacherID;
    public String Time;
    public String status;
    public String Date;
    public String problem;
    public String reason;


    public request(String CourseID, String reqID, String StudentID, String TeacherID, String Time, String status, String Date, String problem, String reason) {
        this.CourseID = CourseID;
        this.reqID = reqID;
        this.StudentID = StudentID;
        this.TeacherID = TeacherID;
        this.Time = Time;
        this.status = status;
        this.Date = Date;
        this.problem = problem;
        this.reason = reason;
    }

    public String ID;



    public request(){}
    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }


    public String getCourseID() {
        return CourseID;
    }

    public void setCourseID(String CourseID) {
        this.CourseID = CourseID;
    }

    public String getReqID() {
        return reqID;
    }

    public void setReqID(String reqID) {
        this.reqID = reqID;
    }

    public request(request r){
        StudentID = r.StudentID;
        TeacherID = r.TeacherID;
        Time = r.Time;
        status = r.status;
        reqID=r.reqID;
        CourseID =r.CourseID;
        Date = r.Date;
        problem=r.problem;
    }

    public String getProblem() {
        return problem;
    }

    public String getReason() {
        return reason;
    }

    public String getID() {
        return ID;
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
