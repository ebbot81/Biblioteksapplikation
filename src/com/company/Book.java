package com.company;

public class Book {
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
}
