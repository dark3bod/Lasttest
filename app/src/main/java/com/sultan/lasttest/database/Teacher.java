package com.sultan.lasttest.database;

import java.io.Serializable;
import java.util.List;

public class Teacher implements Serializable {
    public String email;
    public String name;
    public String lastName;
    public String  floor;
    public String building;
    public String officeNO;
    public String password , deptID;
    public List<String> course;
    public List<Integer> timeAvailable;

   // public List<String> section;

    public Teacher(String email, String name, String lastName, String floor, String building, String officeNO, String password, List<String> course, List<Integer> timeAvailable , String deptID) {
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.floor = floor;
        this.building = building;
        this.officeNO = officeNO;
        this.password = password;
        this.course = course;
        this.timeAvailable = timeAvailable;
        this.deptID = deptID;

    }

    public Teacher() {
    }


    public List<Integer> getTimeAvailable() {
        return timeAvailable;
    }

    public void setTimeAvailable(List<Integer> timeAvailable) {
        this.timeAvailable = timeAvailable;
    }


    public void Teacher(Teacher teacher){
        this.email=teacher.email;
        this.name=teacher.name;
        this.lastName=teacher.lastName;
        this.course= teacher.course;
        this.timeAvailable = teacher.timeAvailable;

    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCourse(List<String> course) {
        this.course = course;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public List<String> getCourse() {
        return course;
    }
}
