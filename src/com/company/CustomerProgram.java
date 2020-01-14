package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class CustomerProgram {

    private ArrayList<Book> myBorrowedBooks = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private BookProgram bookProgram = new BookProgram();

    public CustomerProgram() {
        System.out.println(myBorrowedBooks.size());
        addBookByTitle();
        System.out.println(myBorrowedBooks.size());
        showMyBorrowedBooks();
        addBookByTitle();
        System.out.println(myBorrowedBooks.size());

    }

    private void showMyBorrowedBooks() {
        for (Book book : myBorrowedBooks) {
            System.out.println(bookProgram.showAllBookInformationAndAvailability(book));
        }
    }

    private void addBookByTitle() {
        myBorrowedBooks.add(bookProgram.searchByTitle("Vilken titel söker du?", "Tyvärr finns inte titeln du sökte, försök igen", "Tyvärr är boken utlånad för tillfället"));
    }

    private void addBookByAuthor() {
        myBorrowedBooks.add(bookProgram.searchByAuthor("Vilken författare söker du?", "Tyvärr finns inte författaren du sökte, försök igen"));

    }
}