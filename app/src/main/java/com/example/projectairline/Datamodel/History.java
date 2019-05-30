package com.example.projectairline.Datamodel;

public class History {

    private String month;
    private String year;


    public History(String month, String year) {
        this.month = month;
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }
}
