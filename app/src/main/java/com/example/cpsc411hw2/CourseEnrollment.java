package com.example.cpsc411hw2;

public class CourseEnrollment {
    protected String cID;
    protected String g;

    public CourseEnrollment(String courseID, String grade){
        cID = courseID;
        g = grade;
    }

    public String getCourseID(){
        return cID;
    }
    public String getGrade(){
        return g;
    }

    public void setCourseID(String courseID){
        cID = courseID;
    }
    public void setGrade(String grade){
        g = grade;
    }
}
