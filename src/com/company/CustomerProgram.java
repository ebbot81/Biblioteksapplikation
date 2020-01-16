package com.company;

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


 public void borrowBook(Customer customerListToAddBookTo) {
     customerListToAddBookTo.addBook( Program.getBookProgram().searchByTitleOrAuthorIfTrue("Vilken bok vill du låna?", "Boken du sökte finns inte, försök igen med exakt boktitel eller författarnamn", "Tyvärr är boken du sökte utlånad för tillfället"));
    }

    public void returnBook(Customer customerListToAddBookTo) {
        customerListToAddBookTo.removeBook( Program.getBookProgram().searchByTitleOrAuthorIfFalse("Vilken bok vill du lämna tillbaka?", "Du kan inte lämna tillbaka en bok du inte har lånat"));
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