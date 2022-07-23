package com.example.application;

public class Notice_Model {

    String Notice,Topic_name;

    Notice_Model(){

    }

    public Notice_Model(String notice,String topic_name) {
        this.Notice = notice;
        this.Topic_name = topic_name;
    }

    public String getTopic_name() {
        return Topic_name;
    }

    public void setTopic_name(String topic_name) {
        this.Topic_name = topic_name;
    }

    public String getNotice() {
        return Notice;
    }

    public void setNotice(String notice) {
        this.Notice = notice;
    }
}
