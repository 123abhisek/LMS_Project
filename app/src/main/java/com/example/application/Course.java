package com.example.application;

public class Course {

    String Course_name ;

    //Empty Constructor
    public Course() {

    }

    //Constructor with parameter
    public Course(String course_name) {
        Course_name = course_name;
    }

    //getter method
    public String getCourse_name() {
        return Course_name;
    }

    //setter method
    public void setCourse_name(String course_name) {
        Course_name = course_name;
    }

}
