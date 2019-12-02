package com.example.cpsc411hw2;

import java.util.ArrayList;

public class Student {
    protected String fName;
    protected String lName;
    protected String lcwid;
    protected ArrayList<CourseEnrollment> cList;

    public Student(String firstName, String lastName,String CWID){
        fName = firstName;
        lName = lastName;
        lcwid = CWID;
    }

    public String getFirstName(){
        return fName;
    }
    public String getLastName(){
        return lName;
    }
    public String getCWID(){
        return lcwid;
    }
    public ArrayList<CourseEnrollment> getcList(){return cList;}

    public void setFirstName(String firstName){
        fName = firstName;
    }
    public void setLastName(String lastName){
        lName = lastName;
    }
    public void setcList(ArrayList<CourseEnrollment> courseList){cList = courseList;}
}
