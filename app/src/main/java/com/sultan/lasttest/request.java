package com.sultan.lasttest;

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

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String ID;


    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }


    public request(){}
    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public request(String studentID, String teacherID, String time, String status, String CourseID, String reqID, String Date, String problem ) {
        StudentID = studentID;
        this.CourseID=CourseID;
        this.reqID=reqID;
        TeacherID = teacherID;
        Time = time;
        this.status = status;
        this.Date= Date;
        this.problem=problem;
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
