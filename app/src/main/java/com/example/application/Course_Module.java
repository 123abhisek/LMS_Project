package com.example.application;

public class Course_Module {

    String module_Name;

    //Empty Constructor
    public Course_Module() {
    }

    //Constructor with parameter
    public Course_Module(String module_Name) {
        this.module_Name = module_Name;
    }

    //getter method
    public String getModule_Name() {
        return module_Name;
    }

    //setter method
    public void setModule_Name(String module_Name) {
        this.module_Name = module_Name;
    }
}
