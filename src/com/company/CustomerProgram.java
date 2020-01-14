package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class CustomerProgram {

    private ArrayList<Book> myBorrowedBooks = new ArrayList<>();
    ArrayList<String> books = new ArrayList<String>();
    private Scanner scanner = new Scanner(System.in);
    private BookProgram bookProgram = new BookProgram();

 /*   public CustomerProgram() {
        System.out.println(myBorrowedBooks.size());
        addBookByTitle();
        System.out.println(myBorrowedBooks.size());
        showMyBorrowedBooks();
        addBookByTitle();
        System.out.println(myBorrowedBooks.size());

    }
*/

 public void addBooks(String book) {
     books.add(book);
 }

 public void removeBooks(String book) {
     books.remove(0);
 }

 public void borrowBook(String borrow) {
    books.add(borrow);
 }

 public void showMyBorrowedBooks() {
        for (Book book : myBorrowedBooks) {
            System.out.println(bookProgram.showAllBookInformationAndAvailability(book));
        }
    }

    public void addBookByTitle() {
        myBorrowedBooks.add(bookProgram.searchByTitle("Vilken titel söker du?", "Tyvärr finns inte titeln du sökte, försök igen", "Tyvärr är boken utlånad för tillfället"));
    }

    private void addBookByAuthor() {
        myBorrowedBooks.add(bookProgram.searchByAuthor("Vilken författare söker du?", "Tyvärr finns inte författaren du sökte, försök igen"));

    }
}