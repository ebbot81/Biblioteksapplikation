package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class LibrarianProgram {

    Scanner scanner = new Scanner(System.in);
    private ArrayList<String> books = new ArrayList<>();

    public void addBookToList( ) {
        String newBook = "";
        String newAuthor = "";
        String newInfo = "";
        do {
        System.out.println("Vad heter boken du vill lägga till i listan? ");
        newBook = scanner.nextLine();
        } while (newBook.isBlank());
        do {
        System.out.println("Vem har skrivit boken? ");
        newAuthor = scanner.nextLine();
        } while (newAuthor.isBlank());
        do {
        System.out.println("Skriv in lite information om boken. ");
        newInfo = scanner.nextLine();
        }while (newInfo.isBlank());
        System.out.println("Du lade till:\nBok: " + newBook + "\nFörfattare: " + newAuthor + "\nInformation om boken: " + newInfo );
        Program.getBookProgram().books.add(new Book(newBook, newAuthor, newInfo, true));
    }

    public void removeBookFromList() {
        String removeBook;
        System.out.println("Vilken bok vill du ta bort? \n");
        Program.getBookProgram().showAllBookList();
        removeBook = scanner.nextLine();
        System.out.println("Du tog bort: " + removeBook + " från listan");
        if (removeBook.equals("")) {
            Program.getBookProgram().books.remove(removeBook);
        }
    }
}
