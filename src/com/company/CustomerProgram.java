package com.company;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerProgram {

    ArrayList<Customer> customers = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

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
                if (str.equalsIgnoreCase(userName + ".ser".toLowerCase())){
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

 public void borrowBook(Customer customerListToAddBookTo) {
     customerListToAddBookTo.addBook( Program.getBookProgram().searchByTitleOrAuthorIfTrue("Vilken bok vill du låna?", "Boken du sökte finns inte, försök igen med exakt boktitel eller författarnamn", "Tyvärr är boken du sökte utlånad för tillfället"));
//     System.out.println("Vad kul! Du lånade");
    }

    public void returnBook(Customer customerListToRemoveBookFrom) {
        customerListToRemoveBookFrom.removeBook( Program.getBookProgram().searchByTitleOrAuthorIfFalse("Vilken bok vill du lämna tillbaka?", "Du kan inte lämna tillbaka en bok du inte har lånat"));
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