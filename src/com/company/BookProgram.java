package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookProgram  {

    ArrayList<Book> books = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public BookProgram() {
        //   showAllBookList();
        addBooksFromFile(books,"books.txt");
    }

    private void addBooksFromFile(ArrayList<Book> listOfBooksToAddBooksTo, String fileName) {
        List<String> lines = SaveAndLoadFile.readAllLines(fileName);
        for (String str : lines) {
            String[] parts = str.split(",");
            for (int i = 0; i < str.length(); i++) {
                listOfBooksToAddBooksTo.add(new Book(parts[0], parts[1], parts[2], true));
                break;
            }
        }
    }

    private void addBooks() {
        books.add(new Book("Ondskan", "Jan Guillou", "En bok om ondska", true));
        books.add(new Book("Tomten strular", "Ernst Göransson", "Tomten krånglar till det", true));
        books.add(new Book("Stina Bakar", "Stina Olsson", "Stina älskar att laga lasagne", true));
        books.add(new Book("Dinosaurierna", "Jens Schneider", "Dinosaurierna kommer tillbaka", true));
        books.add(new Book("Antika trädgårdar", "Örjan Rahmberg", "Väldigt antika trädgårdar visas upp...", true));
        books.add(new Book("Grönsakskungen", "Lennart Svahnsson", "Lennart kan allt om grönsaker", true));
        books.add(new Book("Hattkriget", "Lena Borselius", "Vem kan ta på sig flest hattar på kortast tid?", true));
        books.add(new Book("Något i görningen", "Alex Schulman", "En inflytelserik politiker har något i görningen", true));
        books.add(new Book("Det vita huset", "Gösta Anderhjelm", "Huvudpersonen bygger ett hus på kort tid", true));
        books.add(new Book("Java For Pros", "Hassan A", "Hassan gillar att programmera", true));
    }


    public void headLines() {
        System.out.print(String.format("\n%36s", "TITEL"));
        System.out.print(String.format("%36s", "FÖRFATTARE"));
        System.out.print(String.format("%33s", "INFORMATION\n"));
        System.out.println("                               *************************************************************************\n");
    }

    public void headLinesAndStatus() {
        System.out.print(String.format("%36s", "STATUS"));
        System.out.print(String.format("%36s", "TITEL"));
        System.out.print(String.format("%36s", "FÖRFATTARE"));
        System.out.print(String.format("%33s", "INFORMATION\n"));
        System.out.print("                              **************************************************************************************************************\n");
    }

    public void showAllBookList() {
        for (Book book : books)
        {
            System.out.print(showAllBookInformationAndAvailability(book));
        }
    }

    public void showBookListIfAvailable() {
        for (Book book : books) {
            if (book.isAvailability() == true) {
                System.out.println(showAllBookInformationAndAvailability(book));
            }
        }
    }

    public void showBookListIfNotAvailable() {
        for (Book book : books) {
            if (book.isAvailability() == false) {
                System.out.print(showAllBookInformationAndAvailability(book));
            }
        }
    }

    public void showAllBookInformationWithOutAvailability(ArrayList<Book> listOfBooksToShow) {
        for (Book book : listOfBooksToShow) {
            System.out.println(showAllBookInformation(book));
        }
    }


    public String showAllBookInformation(Book book) {
        return String.format("                                %-30s %-30s %-30s\n ", book.getTitle(), book.getAuthor(), book.getInformation());
    }

    public String showAllBookInformationAndAvailability(Book book) {
        if (book.isAvailability() == true) {
            System.out.print("                              Tillgänglig");
        } else System.out.print("                              Utlånad    ");
        return String.format("                          %-30s %-30s %-40s\n\n", book.getTitle(), book.getAuthor(), book.getInformation());
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
                 if (book.getTitle().contains(userInput) && book.isAvailability() == true) {
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

    public Book removeBooksFromLibrary(ArrayList<Book> bookListToReturnFrom, String msgWelcome, String msgRefineSearch, String msgIfFail, String msgIfEmptyList) {
        String tempMsgRefineSearch = msgRefineSearch;
        String tempMsgIfFail = msgIfFail;
        ArrayList<Book> sameSearchBooks = new ArrayList<>();
        String userInput = "";
        if (bookListToReturnFrom.size() > 0) {
            System.out.println();
            System.out.println(msgWelcome);
            do {
                do {
                    msgIfFail = tempMsgIfFail;
                    eraseBookList(sameSearchBooks);
                    userInput = scanner.nextLine();
                    for (int i = 0; i < bookListToReturnFrom.size(); i++) {
                        if (bookListToReturnFrom.get(i).getTitle().toLowerCase().contains(userInput.toLowerCase()) ||
                                bookListToReturnFrom.get(i).getAuthor().toLowerCase().contains(userInput.toLowerCase())) {
                            sameSearchBooks.add(bookListToReturnFrom.get(i));
                        }
                    }
                    if(sameSearchBooks.size()>0){
                        msgIfFail = tempMsgRefineSearch;
                        showAllBookInformationWithOutAvailability(sameSearchBooks);
                    }
                    if (sameSearchBooks.size() == 1) {
                        //bookListToRemoveFrom.remove(sameSearchBooks.get(0));
                        if (sameSearchBooks.get(0).isAvailability() == true) {
                        sameSearchBooks.get(0).setAvailability(false);
                        } else {
                            sameSearchBooks.get(0).setAvailability(true);
                        }
                        return sameSearchBooks.get(0);
                    }
                    System.out.println( msgIfFail);
                } while (true);
            } while (true);
        }
        System.out.println(msgIfEmptyList);
        return null;
    }

    public void eraseBookList(ArrayList<Book> bookListToEmpty) {
        if (bookListToEmpty.size() > 0) {
            do {
                bookListToEmpty.remove(bookListToEmpty.get(0));
            } while (bookListToEmpty.size() > 0);
            return;
        }
    }


}

