package com.company;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerProgram {

    ArrayList<Customer> customers = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private final int MAX_AMOUNT_OF_DAYS_TO_BORROW_BOOK = 7;

 /*   public CustomerProgram() {
        System.out.println(myBorrowedBooks.size());
        addBookByTitle();
        System.out.println(myBorrowedBooks.size());
        showMyBorrowedBooks();
        addBookByTitle();
        System.out.println(myBorrowedBooks.size());

    }
*/

    public CustomerProgram() {
    }

    private Customer findCustomer(String name) {
        for (Customer customer : customers) {
            if (customer.getName().equals(name)) {
                return customer;
            }
        }
        return null;
    }

    private void logInUser() {
        File file = new File("./");
        String[] files = file.list();
        String userName = "";
        do {
            System.out.println("Vänligen skriv in ditt användarnamn");
            userName = scanner.nextLine();
            for (String str : files) {
                if (str.equalsIgnoreCase(userName + ".ser".toLowerCase())) {
                    Customer currentCustomer = (Customer) SaveAndLoadFile.loadObject(userName + ".ser");
                    System.out.println("Ange ditt lösenord?");
                    String userPass = scanner.nextLine();
                    while (!currentCustomer.getPassword().equals(userPass)) {
                        System.out.println("Lösenordet stämmer inte, vänligen försök igen!");
                        userPass = scanner.nextLine();
                    }
                    System.out.println("Välkommen tillbaka " + currentCustomer.getName());
                    return;
                }
            }
        } while (true);
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

    public void addBookByTitle() {
        // myBorrowedBooks.add(Program.getBookProgram().searchByTitle("Vilken titel söker du?", "Tyvärr finns inte titeln du sökte, försök igen", "Tyvärr är boken utlånad för tillfället"));
    }

    private void addBookByAuthor() {
        // myBorrowedBooks.add(Program.getBookProgram().searchByAuthor("Vilken författare söker du?", "Tyvärr finns inte författaren du sökte, försök igen"));

    }
}