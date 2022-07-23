package com.example.application;

public class uploadPDF {

    public String url;
    public String name;


    public uploadPDF() {
    }

    public uploadPDF(String url , String name) {
        this.url = url;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
