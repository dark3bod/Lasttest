package com.sultan.lasttest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Teacher implements Serializable {
    public String email,name,lastName;
    public List<String> course;
    public void Teacher(String email,String name,String lastNmae){
        this.email=email;
        this.name=name;
        this.lastName=lastNmae;

    }
    public void Teacher(Teacher teacher){
        this.email=teacher.email;
        this.name=teacher.name;
        this.lastName=teacher.lastName;
        this.course= teacher.course;

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
