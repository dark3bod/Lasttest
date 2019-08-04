package com.sultan.lasttest;

public class Teacher {
    public String email,name,lastName;

    public void Teacher(String email,String name,String lastNmae){
        this.email=email;
        this.name=name;
        this.lastName=lastNmae;

    }
    public void Teacher(Teacher teacher){
        this.email=teacher.email;
        this.name=teacher.name;
        this.lastName=teacher.lastName;

    }
}
