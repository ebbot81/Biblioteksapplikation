package com.company;

import java.util.Comparator;

public class Sort {

    public static class sortByTitle implements Comparator<Book> {
        public int compare(Book a, Book b) {
            return a.getTitle().compareTo(b.getTitle());
        }
    }

    public static class sortByAuthor implements Comparator<Book> {
        public int compare(Book a, Book b) {
            return a.getAuthor().compareTo(b.getAuthor());
        }
    }
}
