package com.company;

import java.util.ArrayList;

public class Customer extends User {

    private boolean available = true;
    private ArrayList<Book> books = new ArrayList<>();


    public Customer(String name, String password) {
        super(name, password);
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public ArrayList<Book>getBooks() {
        return books;
    }

}
