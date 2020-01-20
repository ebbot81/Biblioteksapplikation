package com.company;

import java.io.Serializable;

public class Book implements Serializable {
    private String title;
    private String author;
    private String information;
    private boolean availability;

    public Book(String title, String author, String information, boolean availability) {
        this.title = title;
        this.author = author;
        this.information = information;
        this.availability = availability;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getInformation() {
        return information;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
