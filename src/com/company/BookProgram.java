package com.company;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BookProgram {

    ArrayList<Book> books = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private LocalDate localDate = LocalDate.now();

    public BookProgram() {
        //   showAllBookList();
        //addBooksFromFile(books,"books.txt");
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void notificationMsgForBorrowDays(ArrayList<Book> bookList) {
        if (bookList.size() > 0) {
            for (Book book : bookList) {
                System.out.println();
                if (amountOfBorrowDaysCounter(book) >= 7) {
                    System.out.println("Du har " + amountOfBorrowDaysCounter(book) + " dagar kvar innan du måste lämna tillbaka ");
                    System.out.println("\"" + book.getTitle() + "\"");
                    System.out.println();
                } else if (amountOfBorrowDaysCounter(book) <= 6 && amountOfBorrowDaysCounter(book) >= 1) {
                    System.out.println("Notera att du har mindre än en vecka (" + amountOfBorrowDaysCounter(book) + " dagar) kvar");
                    System.out.println("tills du måste lämna tillbaka \"" + book.getTitle() + "\"");
                    System.out.println();
                } else if (amountOfBorrowDaysCounter(book) == 0) {
                    System.out.println("Du har inte lämnat in \"" + book.getTitle() + "\" i tid!");
                    System.out.println(" Det är bäst att du flyr landet för maffian är efter dig!");
                    System.out.println();
                }
            }
        }
    }

    public long amountOfBorrowDaysCounter(Book book) {
        long differenceBetweenTwoDates = ChronoUnit.DAYS.between(localDate, book.getReturnDate());
        return differenceBetweenTwoDates;
    }

    public LocalDate setDateToBorrowBook(int amountOfDaysToBorrowBook) {
        LocalDate returnDate = localDate.plusDays(amountOfDaysToBorrowBook);
        return returnDate;
    }

//////////////////////////////////////////////////////////////////////////////////////////////////

    public void headLines() {
        System.out.print(String.format("\n%37s", "TITEL"));
        System.out.print(String.format("%37s", "FÖRFATTARE"));
        System.out.print(String.format("%33s", "INFORMATION\n"));
        System.out.println("                                **************************************************************************\n");
    }

    public void headLinesAndStatus() {
        System.out.print(String.format("%36s", "STATUS"));
        System.out.print(String.format("%36s", "TITEL"));
        System.out.print(String.format("%37s", "FÖRFATTARE"));
        System.out.print(String.format("%33s", "INFORMATION\n"));
        System.out.print("                              ***************************************************************************************************************\n");
    }

    public void showAllBookList() {
        for (Book book : Program.getBooks()) {
            System.out.print(showAllBookInformationAndAvailability(book));
        }
    }

    public void showBookListIfAvailable() {
        for (Book book : Program.getBooks()) {
            if (book.isAvailability() == true) {
                System.out.println(showAllBookInformationAndAvailability(book));
            }
        }
    }

    public void showBookListIfNotAvailable() {
        for (Book book : Program.getBooks()) {
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

    public Book returnBooksFromLibrary(ArrayList<Book> bookListToReturnFrom, String msgWelcome, String msgRefineSearch, String msgIfFail, String msgIfEmptyList) {
        String tempMsgRefineSearch = msgRefineSearch;
        String tempMsgIfFail = msgIfFail;
        ArrayList<Book> sameSearchBooks = new ArrayList<>();
        String userInput = "";
        if (bookListToReturnFrom.size() > 0) {
            System.out.println();
            System.out.println(msgWelcome);

            do {
                msgIfFail = tempMsgIfFail;
                eraseBookList(sameSearchBooks);
                System.out.println("Tryck [9] när som helst för att återvända till menyn");
                userInput = scanner.nextLine();
                if (userInput.equals("9")) {
                    return null;
                }
                for (int i = 0; i < bookListToReturnFrom.size(); i++) {
                    if (bookListToReturnFrom.get(i).getTitle().toLowerCase().contains(userInput.toLowerCase()) ||
                            bookListToReturnFrom.get(i).getAuthor().toLowerCase().contains(userInput.toLowerCase())) {
                        sameSearchBooks.add(bookListToReturnFrom.get(i));
                    }
                }
                headLines();
                if (sameSearchBooks.size() > 0) {
                    msgIfFail = tempMsgRefineSearch;
                    showAllBookInformationWithOutAvailability(sameSearchBooks);
                }
                if (sameSearchBooks.size() == 1) {
                    return sameSearchBooks.get(0);
                }
                System.out.println(msgIfFail);
            } while (true);
        }
        System.out.println(msgIfEmptyList);
        return null;
    }

    public void sortByTitle(ArrayList<Book> bookListToSort) {
        Collections.sort(bookListToSort, new Sort.sortByTitle());
    }

    public void sortByAuthor(ArrayList<Book> bookListToSort) {
        Collections.sort(bookListToSort, new Sort.sortByAuthor());
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