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

 public void addBookToList(String newBook, String newAuthor, String newInfo ) {
     String newBookTitle = newBook + newAuthor + newInfo;
     System.out.println("Vad heter boken du vill lägga till i listan? ");
     newBook = scanner.nextLine();
     System.out.println("Vem har skrivit boken? ");
     newAuthor = scanner.nextLine();
     System.out.println("Skriv in lite information om boken. ");
     newInfo = scanner.nextLine();
     System.out.println("Du lade till:\nBok: " + newBook + "\nFörfattare: " + newAuthor + "\nInformation om boken: " + newInfo );
     books.add(newBookTitle);
 }

 public void removeBookFromList() {
     String removeBook;
     System.out.println("Vilken bok vill du ta bort? \n");
     bookProgram.showAllBookList();
     removeBook = scanner.nextLine();
     System.out.println("Du tog bort: " + removeBook + " från listan");
     if (removeBook.equals("")) {
         books.remove(removeBook);
     }
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