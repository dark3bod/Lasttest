package com.sultan.lasttest.database;

import java.io.Serializable;
import java.util.List;

public class section implements Serializable {


    public List<String>studentUID;
    public String CourseID;

    public  String teacherUID;
    public section(){}



    public section(String CourseID, List<String> studentUID, String teacherUID) {
        this.CourseID = CourseID;
        this.studentUID = studentUID;
        this.teacherUID = teacherUID;
    }

;
    public void section(section section){
        this.CourseID = section.CourseID;
        this.teacherUID = section.teacherUID;
    }

}
