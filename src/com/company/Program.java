package com.company;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Scanner;

public class Program implements Serializable {
    public static Program program;
    public transient Scanner scanner = new Scanner(System.in);
    private static BookProgram bookProgram = new BookProgram();
    private static ArrayList<Book> books = bookProgram.books;
    private static CustomerProgram customerProgram = new CustomerProgram();
    private static LibrarianProgram librarianProgram = new LibrarianProgram();
 //   private static ArrayList<Customer> customers = new ArrayList<>();
 //   private static ArrayList<Librarian> librarians = new ArrayList<>();
    private static User currentUser = null;
    private static ArrayList<User> customers = new ArrayList<>();
    private static ArrayList<User> librarians = new ArrayList<>();
    private Integer[] monthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 31, 31};
    private LocalDate localDate = LocalDate.now();
    private final int MAX_ALLOWED_BORROW_DAY = 8;
    private int thisDay = localDate.getDayOfMonth();
    private int thisMonth = localDate.getMonthValue();
    private int thisYear = localDate.getYear();
    private int returnDay = localDate.getDayOfMonth();
    private int returnMonth = localDate.getMonthValue();
    private int returnYear = localDate.getYear();

    public Program() {

        int restDay = 0;
        int remainDay = 0;
        int totalDay = thisDay + MAX_ALLOWED_BORROW_DAY;
        int rounds = 0;
        int currentDaysOfMonth = monthDays[thisMonth - 1];

        int time = thisDay + 14;
        int next = 0;

        while (time > 0) {
            int i = 0;
            next++;
            time -= 1;
            if (next >= monthDays[thisMonth - i]) {
                returnDay = time;
                i++;
                next = 0;
                returnMonth++;
                if (returnMonth > monthDays.length) {
                    returnYear++;
                }
            }
        }
            System.out.println(returnDay);
            System.out.println(returnMonth);
            System.out.println(returnYear);
        }

/*
        String lastDayToReturnBook;  // Start lastDayToReturnBook
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 14);  // number of days to add
        lastDayToReturnBook = sdf.format(calendar.getTime());  // dt is now the new lastDayToReturnBook
        System.out.println(lastDayToReturnBook);

        customers.add(new Customer("Hassan", "1111"));
        customers.add(new Customer("Tobbe", "1111"));
        customers.add(new Customer("Viktor", "1111"));
        customers.add(new Customer("Johan", "1111"));

        System.out.println(customers.get(0).getBorrowDay());

        System.out.println(localDate);
        if (localDate.getDayOfMonth() == Integer.parseInt(lastDayToReturnBook)) {
            System.out.println("samma dag");

        }else if (localDate.getDayOfMonth() <= Integer.parseInt(lastDayToReturnBook)) {
            System.out.println("Du har: " + lastDayToReturnBook + "dagar kvar");
        }else {
            System.out.println("Du är sen med återlämningen av boken" + lastDayToReturnBook);
        }*/
        /*}
        Calendar c = Calendar.getInstance();
        c.add( Calendar.DATE, 370);  // number of days to add
        System.out.println(returnDay);
        System.out.println(returnMonth);
        System.out.println(c.getTime());
   */

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////D
    private void createAccount(boolean trueForCustomerFalseForLibrarian) {
        String fileExt = "";
        if (trueForCustomerFalseForLibrarian == true) {
            fileExt = "_customer_";
        } else {
            fileExt = "_librarian_";
        }
        System.out.println();
        System.out.println("Hej och välkommen!");
        String userName = "";
        String userPassword = "";
        while(!userName.equals("1") || userPassword.equals("1")) {
            do {
                System.out.println("Vänligen ange ett användarnamn. Använd minst 3 tecken.");
                userName = scanner.nextLine();
                if( userName.equals("9")){start();}
                while (checkIfUserAlreadyExists(fileExt + userName) == true) {
                    System.out.println("Användarnamnet finns redan!");
                    userName = scanner.nextLine();
                    if( userName.equals("9")){start();}
                }
            } while (userName.isBlank() || userName.length() < 3);
            System.out.println("Välkommen " + userName + "! Ange ett lösenord");
            userPassword = scanner.nextLine();
            if( userPassword.equals("9")){start();}
            while (userPassword.isBlank() || userPassword.length() < 3) {
                System.out.println("Försök igen och använd minst 3 tecken.");
                userPassword = scanner.nextLine();
                if( userPassword.equals("9")){start();}
            }
            if (trueForCustomerFalseForLibrarian == true) {
                Customer customer = new Customer(userName, userPassword);
                customers.add( customer );
                currentUser = customer;
                SaveAndLoadFile.saveObject(fileExt + userName + ".ser", checkUserByName(customers, userName));
                //fileLoadSavedObject(fileExt + userName + ".ser");
            } else {
                Librarian librarian = new Librarian(userName, userPassword);
                librarians.add( librarian );
                currentUser = librarian;
                SaveAndLoadFile.saveObject(fileExt + userName + ".ser", checkUserByName(librarians, userName));
                //fileLoadSavedObject(fileExt + userName + ".ser");
            }
            fileLoadFiles();
            return;
        }
    }
    private void logIn(boolean trueForCustomerFalseForLibrarian) {
        boolean createAccountForCustomer = trueForCustomerFalseForLibrarian;
        String fileExt = "";
        if (createAccountForCustomer == true) {
            fileExt = "_customer_";
        } else {
            fileExt = "_librarian_";
        }
        File file = new File("./");
        String[] files = file.list();
        System.out.println("Vänligen fyll i ditt användarnamn eller tryck [9] för att återvända till startmenyn.");
        String userName = scanner.nextLine();
        if( userName.equals("9")){start();}
        do {
            for (String str : files) {
                if (str.equalsIgnoreCase(fileExt + userName + ".ser")) {
                    currentUser = (User) SaveAndLoadFile.loadObject(fileExt + userName + ".ser");
                    fileLoadFiles();
                    System.out.println("Ange ditt lösenord?");
                    String userPass = scanner.nextLine();
                    if( userPass.equals("9")){start();}
                    while (!currentUser.getPassword().equals(userPass)) {
                        System.out.println("Lösenordet stämmer inte. Vänligen försök igen eller tryck [9] för att återvända till startmenyn.");
                        userPass = scanner.nextLine();
                        if( userPass.equals("9")){start();}
                    }
                    return;
                }
            }
            System.out.println("Användarnamnet finns ej registrerat. Försök igen eller tryck [9] för att återvända till startmenyn.");
            userName = scanner.nextLine();
            if( userName.equals("9")){start();}
        } while (true);
    }
    private boolean checkIfUserAlreadyExists(String userName) {
        File file = new File("./");
        String[] files = file.list();
        for (String str : files) {
            if (str.equalsIgnoreCase(userName + ".ser")) {
                return true;
            }
        }
        return false;
    }
    private User checkUserByName(ArrayList<User> userList, String nameOfUser) {
        for (User user : userList) {
            if (user.getName().equalsIgnoreCase(nameOfUser)) {
                return user;
            }
        }
        return null;
    }
    private void fileLoadFiles() {
        customers = (ArrayList<User>) SaveAndLoadFile.loadObject("customers.ser");
        librarians = (ArrayList<User>) SaveAndLoadFile.loadObject("librarians.ser");
        books = (ArrayList<Book>) SaveAndLoadFile.loadObject("books.ser");
    }
    private void fileSaveFiles() {
        if (currentUser instanceof Customer) {
            SaveAndLoadFile.saveObject("_customer_" + currentUser.getName() + ".ser", currentUser);
            SaveAndLoadFile.saveObject("books.ser", books);
            SaveAndLoadFile.saveObject("customers.ser", customers);
            SaveAndLoadFile.saveObject("librarians.ser", librarians);
            return;
        }
        SaveAndLoadFile.saveObject("_librarian_" + currentUser.getName() + ".ser", currentUser);
        SaveAndLoadFile.saveObject("books.ser", books);
        SaveAndLoadFile.saveObject("customers.ser", customers);
        SaveAndLoadFile.saveObject("librarians.ser", librarians);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////D

    private void sortByTitle() {
        Collections.sort(books, new Sort.sortByTitle());
    }

    private void sortByAuthor() {
        Collections.sort(books, new Sort.sortByAuthor());
    }

    private void addCustomer() {
        System.out.println("Hej och välkommen!");
        do {
            System.out.println("Skriv in användarnamn: ");
            String userName = scanner.nextLine();
            System.out.println("Skriv in ditt lösenord: ");
            String userPassword = scanner.nextLine();
            System.out.println("Skriv in lösenordet igen: ");
            String userPasswordAgain = scanner.nextLine();

            while (!userPassword.equals(userPasswordAgain)) {
                System.out.println("Lösenordet var inte korrekt, försök igen");
                System.out.println("Skriv in ditt lösenord: ");
                userPassword = scanner.nextLine();
                System.out.println("Skriv in lösenordet igen: ");
                userPasswordAgain = scanner.nextLine();
            }
            customers.add(new Customer(userName, userPassword));
            //    SaveAndLoadFile.saveObject(userName +".ser", customers.add(new Customer(userName, userPassword)));
            return;
        } while (true);
    }

    public void start() {
        int chooseMenu;
        do {
            System.out.println("\nVÄLKOMMEN TILL BIBLIOTEKSAPPEN");
            System.out.println("-----------------------------");
            System.out.println("[1] LOGGA IN SOM KUND");
            System.out.println("[2] LOGGA IN SOM BIBLIOTEKARIE");
            System.out.println("[3] REGISTRERA DIG SOM KUND");
            System.out.println("[4] REGISTRERA DIG SOM BIBLIOTEKARIE");
            System.out.println("[5] AVSLUTA");

            do {
                try {
                    chooseMenu = Integer.parseInt(scanner.nextLine());
                    if (chooseMenu < 1 || chooseMenu > 5) {
                        throw new IndexOutOfBoundsException();
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Välj ett nummer mellan 1-5");
                }
            } while (true);

            switch (chooseMenu) {
                case 1:
                    logIn(true);
                    customerMenu();
                    break;
                case 2:
                    logIn(false);
                    librarianMenu();
                    break;
                case 3:
                    createAccount(true);
                    break;
                case 4:
                    createAccount(false);
                    return;
                case 5:
                    start();
                    return;
                default:
                    break;
            }
        } while (true);
    }

    private void customerMenu() {
        int custMenu;
        do {
            System.out.println("\nBIBLIOTEKSAPPLIKATIONEN - KUNDENS MENY");
            System.out.println("--------------------------------------");
            System.out.println("Vad vill du göra?\n");
            System.out.println("[1] SE ALLA BÖCKER SOM FINNS I APPEN");
            System.out.println("[2] SE STATUSEN PÅ BÖCKERNA SOM FINNS I APPEN");
            System.out.println("[3] LÅNA EN BOK");
            System.out.println("[4] SE VILKA BÖCKER DU LÅNAT");
            System.out.println("[5] LÄMNA TILLBAKA EN BOK");
            System.out.println("[6] AVSLUTA");

            do {
                try {
                    custMenu = Integer.parseInt(scanner.nextLine());
                    if (custMenu < 1 || custMenu > 6) {
                        throw new IndexOutOfBoundsException();
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Välj ett nummer mellan 1-6");
                }
            } while (true);

            switch (custMenu) {
                case 1:
                    bookProgram.headLines();
                    bookProgram.showAllBookInformationWithOutAvailability(bookProgram.books);
                    break;
                case 2:
                    bookProgram.headLinesAndStatus();
                    bookProgram.showAllBookList();
               //     bookProgram.showAllBookInformationWithOutAvailability(bookProgram.books);
                    break;
                case 3:
                    bookProgram.headLinesAndStatus();
                    bookProgram.showAllBookList();
                    customerProgram.borrowBook(currentUser);
                    break;
                case 4:
                    bookProgram.headLinesAndStatus();
                    customerProgram.showMyBorrowedBooks(currentUser.getBooks());
                    break;
                case 5:
                    bookProgram.headLinesAndStatus();
                    customerProgram.showMyBorrowedBooks(currentUser.getBooks());
                    customerProgram.returnBook(currentUser);
                    break;
                case 6:
                    start();
                    return;
                default:
                    break;
            }
        } while (true);
    }

    private void librarianMenu() {
        int libMenu;
        do {
            System.out.println("\nBIBLIOTEKSAPPLIKATIONEN - BIBLIOTEKARIENS MENY");
            System.out.println("------------------------------------------------");
            System.out.println("Vad vill du göra?\n");
            System.out.println("[1] SE ALLA BÖCKER SOM FINNS I APPEN");
            System.out.println("[2] SE ALLA TILLGÄNGLIGA BÖCKER");
            System.out.println("[3] SE ALLA UTLÅNADE BÖCKER");
            System.out.println("[4] LÄGGA TILL NYA BÖCKER");
            System.out.println("[5] TA BORT BÖCKER FRÅN LISTAN");
            System.out.println("[6] SE EN LISTA PÅ ALLA ANVÄNDARE");
            System.out.println("[7] SÖKA PÅ SPECIFIK ANVÄNDARE");
            System.out.println("[8] SE EN LISTA PÅ ANVÄNDARNAS LÅNADE BÖCKER");
            System.out.println("[9] AVSLUTA");

            do {
                try {
                    libMenu = Integer.parseInt(scanner.nextLine());
                    if (libMenu < 1 || libMenu > 9) {
                        throw new IndexOutOfBoundsException();
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Välj ett nummer mellan 1-9");
                }
            } while (true);

            switch (libMenu) {
                case 1:
                    bookProgram.headLines();
                    bookProgram.showAllBookInformationWithOutAvailability(bookProgram.books);
                    break;
                case 2:
                    bookProgram.headLinesAndStatus();
                    bookProgram.showBookListIfAvailable();
                    break;
                case 3:
                    bookProgram.headLinesAndStatus();
                    bookProgram.showBookListIfNotAvailable();
                    break;
                case 4:
                    System.out.println("HÄR KAN DU LÄGGA TILL NYA BÖCKER: ");
                    System.out.println("-------------------------------\n ");
                    librarianProgram.addBookToList();
                    break;
                case 5:
                    System.out.println("HÄR KAN DU TA BORT BÖCKER: ");
                    System.out.println("------------------------\n ");
                    bookProgram.showAllBookList();
                    librarianProgram.removeBookFromList();
                    break;
                case 6:
                    System.out.println("HÄR KAN DU SE ALLA ANVÄNDARE SOM FINNS I SYSTEMET: ");
                    System.out.println("------------------------------------------------\n ");
                    librarianProgram.showUserNameAndOrBooks(customers, false);
                    break;
                case 7:
                    System.out.println("HÄR KAN DU SÖKA PÅ EN SPECIFIK ANVÄNDARES NAMN:");
                    System.out.println("--------------------------------------------\n ");
                    librarianProgram.showUserByName(customers, "Välkommen, skriv in namnet på användaren du söker", "Systemet hittade flera users på din sökning, vänligen specificera", "Din sökning hittades inte", "Listan är tom");
                    break;
                case 8:
                    System.out.println("HÄR KAN DU SE VILKA BÖCKER ANVÄNDARNA HAR LÅNAT: ");
                    System.out.println("----------------------------------------------\n ");
                    librarianProgram.showUserNameAndOrBooks(customers, true);
                    break;
                case 9:
                    start();
                    return;
                default:
                    break;
            }
        } while (true);
    }

    private void login() {
        System.out.println("Hej och välkommen, vänligen fyll i dina uppgifter och tryck sedan på ENTER");
        do {
            System.out.println("Skriv in ditt användarnamn: ");
            try {
                String userName = scanner.nextLine();
                currentUser = (Customer) SaveAndLoadFile.loadObject(userName + ".ser");
            } catch (Exception e) {
                System.out.println("Inkorrekt användarnamn");
            }
            String userPassword = "";
            do{
                System.out.println("Skriv in lösenordet");
                userPassword = scanner.nextLine();
            }while (!currentUser.getPassword().equals(userPassword));
            System.out.println("Korrekt inloggning");
            return;
        } while (true);
    }

    public static CustomerProgram getCustomerProgram() {
        return customerProgram;
    }

    public static BookProgram getBookProgram() {
        return bookProgram;
    }

    public static ArrayList<User> getCustomers() {
        return customers;
    }

    public static ArrayList<User> getLibrarians() {
        return librarians;
    }

    public static User getCurrentUser() {
        return currentUser;
    }
}