package com.example.application;

public class Quiz_model {

    String topic_name,description,test_duration,no_of_question,correct_answer,incorrect_answer;

    Quiz_model(){

    }

    public Quiz_model(String topic_name, String description, String test_duration, String no_of_question, String correct_answer, String incorrect_answer) {
        this.topic_name = topic_name;
        this.description = description;
        this.test_duration = test_duration;
        this.no_of_question = no_of_question;
        this.correct_answer = correct_answer;
        this.incorrect_answer = incorrect_answer;
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

    public String getTest_duration() {
        return test_duration;
    }

    public void setTest_duration(String test_duration) {
        this.test_duration = test_duration;
    }

    public String getNo_of_question() {
        return no_of_question;
    }

    public void setNo_of_question(String no_of_question) {
        this.no_of_question = no_of_question;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public String getIncorrect_answer() {
        return incorrect_answer;
    }

    public void setIncorrect_answer(String incorrect_answer) {
        this.incorrect_answer = incorrect_answer;
    }
}
