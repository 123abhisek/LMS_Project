package com.example.application;

public class Assignment_Model {
    String topic_name;
    String description;
    String marks;

    Assignment_Model(){

    }

    public Assignment_Model(String topic_name,String description , String marks ) {
       this.topic_name = topic_name;
        this.description = description;
        this.marks = marks;
    }

    public String getTopic_name() {
        return topic_name;
    }

    public void setTopic_name(String topic_name) {
        this.topic_name = topic_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }
}
