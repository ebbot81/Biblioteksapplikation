package com.company;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerProgram {

    private final int MAX_AMOUNT_OF_DAYS_TO_BORROW_BOOK = 7;

    public CustomerProgram() {
    }

    public void borrowBook() {
        Book book = Program.getBookProgram().returnBooksFromLibrary(Program.getBooks(),
                "Vänligen fyll i vilken bok eller författare du vill låna",
                "Din sökning gav flera resultat",
                "Din sökning gav inget resultat",
                "listan är tom");

        if( book != null ) {
            book.setReturnDate(Program.getBookProgram().setDateToBorrowBook(MAX_AMOUNT_OF_DAYS_TO_BORROW_BOOK));
            Program.getCurrentUser().addBook(returnBookIfAvailable(book, "Tyvärr är boken redan utlånad"));
            return;
        }
    }

    public void returnBook() {
        Book book = Program.getBookProgram().returnBooksFromLibrary(Program.getCurrentUser().getBooks(),
                "Vänligen fyll i vilken bok eller författare du vill låna",
                "Din sökning gav flera resultat",
                "Din sökning gav inget resultat",
                "listan är tom");
        makeAvailable(Program.getBooks(), book);
        Program.getCurrentUser().removeBook(book);
    }

    private void makeAvailable(ArrayList<Book> bookList, Book bookToMakeAvailable) {
        if (bookToMakeAvailable != null) {
            if (bookList.contains(bookToMakeAvailable)) {
                bookToMakeAvailable.setAvailability(true);
                return;
            }
        }
    }

    private Book returnBookIfAvailable(Book book, String msgIfFail) {
        if (book.isAvailability() == true) {
            book.setAvailability(false);
            return book;
        }
        System.out.println(msgIfFail);
        return null;
    }

    public void showMyBorrowedBooks(ArrayList<Book> listOfBooksToPrint) {
        for (Book book : listOfBooksToPrint) {
            System.out.println(Program.getBookProgram().showAllBookInformationAndAvailability(book));
        }
    }
}