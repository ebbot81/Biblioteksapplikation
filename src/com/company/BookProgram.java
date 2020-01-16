package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class BookProgram {

    ArrayList<Book> books = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public BookProgram() {
        //   showAllBookList();
        addBooks();
    }

    private void addBooks() {
        books.add(new Book("Ondskan", "Jan Guillou", "En bok om ondska", true));
        books.add(new Book("Tomten är far till alla barnen", "Ernst Göransson", "Tomten krånglar till det", true));
        books.add(new Book("Stina Bakar", "Stina Olsson", "Stina älskar att laga lasagne", true));
        books.add(new Book("Dinosauriernas återkomst", "Jens Schneider", "Dinosaurierna kommer tillbaka", true));
        books.add(new Book("Antika trädgårdar", "Örjan Rahmberg", "Väldigt antika trädgårdar visas upp...", true));
        books.add(new Book("Grönsakskungen", "Lennart Svahnsson", "Lennart kan allt om grönsaker", true));
        books.add(new Book("Hattkriget", "Lena Borselius", "Vem kan ta på sig flest hattar på kortast tid?", true));
        books.add(new Book("Något i görningen", "Alex Schulman", "En inflytelserik politiker har något i görningen", true));
        books.add(new Book("Det vita huset", "Gösta Anderhjelm", "Huvudpersonen bygger ett hus på kort tid", true));
        books.add(new Book("Java For Pros", "Hassan A", "Hassan gillar att programmera", true));
    }

    public void showAllBookList() {
        for (Book book : books) {
            System.out.println(showAllBookInformationAndAvailability(book));
        }
    }

    public void showAllBookInformationWithOutAvailability(ArrayList<Book> listOfBooksToShow) {
        for (Book book : listOfBooksToShow) {
            System.out.println(showAllBookInformation(book));
        }
    }


    public String showAllBookInformation(Book book) {
        return String.format("%-20s %-20s %-50s\n ", book.getTitle(), book.getAuthor(), book.getInformation());
    }

    public String showAllBookInformationAndAvailability(Book book) {
        return String.format(" Boktitel: %-20s\n Författare: %-20s\n Info: %-50s %-100b\n ", book.getTitle(), book.getAuthor(), book.getInformation(), book.isAvailability());
    }

    public Book searchByTitleOrAuthorIfTrue(String welcomeMessage, String messageIfFail, String bookNotAvailable) {
        String tempMessage = messageIfFail;
        System.out.println(welcomeMessage);
        do {
            String userInput = scanner.nextLine();
            for (Book book : books) {
                if (book.getTitle().equalsIgnoreCase(userInput) && book.isAvailability() == true ||
                        book.getAuthor().equalsIgnoreCase(userInput) && book.isAvailability() == true ) {
                    book.setAvailability(false);
                    return book;
                } else if (book.getTitle().toLowerCase().equals(userInput) && book.isAvailability() == false ||
                        book.getAuthor().toLowerCase().equals(userInput) && book.isAvailability() == false) {
                    messageIfFail = bookNotAvailable;
                    break;
                }
            }
            System.out.println(messageIfFail);
            messageIfFail = tempMessage;
        } while (true);
    }

    public Book searchByTitleOrAuthorIfFalse(String welcomeMessage, String messageIfFail) {
        String tempMessage = messageIfFail;
        System.out.println(welcomeMessage);
        do {
            String userInput = scanner.nextLine();
            for (Book book : books) {
                if (book.getTitle().equalsIgnoreCase(userInput) && book.isAvailability() == false ||
                        book.getAuthor().equalsIgnoreCase(userInput) && book.isAvailability() == false ) {
                    book.setAvailability(true);
                    return book;
                }
            }
            System.out.println(messageIfFail);
            messageIfFail = tempMessage;
        } while (true);
    }

    public Book searchByTitle(String welcomeMessage, String messageIfFail, String bookNotAvailable) {
        System.out.println(welcomeMessage);
        while (true) {
            String userInput = scanner.nextLine();
            for (Book book : books) {
                 if (book.getTitle().equalsIgnoreCase(userInput) && book.isAvailability() == true) {
                    book.setAvailability(false);
                    return book;
                 } else if (book.getTitle().toLowerCase().equals(userInput) && book.isAvailability() == false) {
                    System.out.println(bookNotAvailable);
                     searchByTitle( welcomeMessage,  messageIfFail,  bookNotAvailable);
                }
            }
                System.out.println(messageIfFail);
        }
    }

    public Book searchByAuthor(String welcomeMessage, String messageIfFail) {
        System.out.println(welcomeMessage);
        while (true) {
            String userInput = scanner.nextLine();
            for (Book book : books) {
                if (book.getAuthor().equalsIgnoreCase(userInput)) {
                    System.out.println("Författaren hittades");
                        return book;
                }
            }
                System.out.println(messageIfFail);
        }
    }
}

