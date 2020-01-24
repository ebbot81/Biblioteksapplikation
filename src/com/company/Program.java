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
    private static ArrayList<User> users = new ArrayList<>();
    private static Integer currentUserIndex = 0;
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
        /*System.out.println(users.size());
        System.out.println(users.size());
        for( User user : users){
            System.out.println(user.getName());
        }*/
        //fileSaveFiles();
        //fileLoadFiles();
        start();
    }

/*
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

*/
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
                users.add( customer );
                currentUserIndex = returnUserIndex(customer);
                SaveAndLoadFile.saveObject(fileExt + userName + ".ser", checkUserByName(users, userName));
                //fileLoadSavedObject(fileExt + userName + ".ser");
            } else {
                Librarian librarian = new Librarian(userName, userPassword);
                users.add( librarian );
                currentUserIndex = returnUserIndex(librarian);
                SaveAndLoadFile.saveObject(fileExt + userName + ".ser", checkUserByName(users, userName));
                //fileLoadSavedObject(fileExt + userName + ".ser");
            }
            fileSaveFiles();
            //fileLoadFiles();
            return;
        }
    }

    private void logIn(boolean customerIfTrueLibrarianIfFalse) {
        System.out.println();
        System.out.println("Vänligen fyll i ditt användarnamn ");
        System.out.println("(Återgå till startmenyn genom att när som helst trycka [9])");
        String userName = scanner.nextLine();
        if (userName.equals("9")) {
            return;
        }

        if (customerIfTrueLibrarianIfFalse) {
            while (searchForCustomerByName(users, userName) == null) {
                System.out.println("Användarnamnet finns ej registrerat. Vänligen försök igen.");
                System.out.println("(Återgå till startmenyn genom att när som helst trycka [9])");
                userName = scanner.nextLine();
                if (userName.equals("9")) {
                    return;
                }
            }

            System.out.println("Ange ditt lösenord?");
            String userPass = scanner.nextLine();
            if (userPass.equals("9")) {
                return;
            }

            while (!searchForCustomerByName(users, userName).getPassword().equals(userPass)) {
                System.out.println("Lösenordet stämmer inte. Vänligen försök igen.");
                System.out.println("(Återgå till startmenyn genom att när som helst trycka [9])");
                userPass = scanner.nextLine();
                if (userPass.equals("9")) {
                    return;
                }
            }

            currentUserIndex = returnUserIndex(searchForCustomerOrLibrarianByName(users, userName));
            customerMenu();

        } else {

            while (searchForLibrarianByName(users, userName) == null) {
                System.out.println("Användarnamnet finns ej registrerat. Vänligen försök igen.");
                System.out.println("(Återgå till startmenyn genom att när som helst trycka [9])");
                userName = scanner.nextLine();
                if (userName.equals("9")) {
                    return;
                }
            }

            System.out.println("Ange ditt lösenord?");
            String userPass = scanner.nextLine();
            if (userPass.equals("9")) {
                return;
            }

            while (!searchForLibrarianByName(users, userName).getPassword().equals(userPass)) {
                System.out.println("Lösenordet stämmer inte. Vänligen försök igen.");
                System.out.println("(Återgå till startmenyn genom att när som helst trycka [9])");
                userPass = scanner.nextLine();
                if (userPass.equals("9")) {
                    return;
                }
            }

            librarianMenu();
        }
    }

    private User searchForCustomerOrLibrarianByName(ArrayList<User> userList, String CustomerOrLibrarianName) {
        for (User user : userList) {
            if (user.getName().equalsIgnoreCase(CustomerOrLibrarianName) && user instanceof Customer) {
                return user;
            } else if (user.getName().equalsIgnoreCase(CustomerOrLibrarianName) && user instanceof Librarian) {
                return user;
            }
        }
        return null;
    }

    private User searchForCustomerByName(ArrayList<User> userList, String customerName) {
        for (User user : userList) {
            if (user.getName().equalsIgnoreCase(customerName) && user instanceof Customer) {
                return user;
            }
        }
        return null;
    }

    private User searchForLibrarianByName(ArrayList<User> userList, String librarianName) {
        for (User user : userList) {
            if (user.getName().equalsIgnoreCase(librarianName) && user instanceof Librarian) {
                return user;
            }
        }
        return null;
    }

    private int returnUserIndex(User user) {
        return users.indexOf(user);
    }
    /*private void logIn(boolean trueForCustomerFalseForLibrarian) {
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
                    User user = (User) SaveAndLoadFile.loadObject(fileExt + userName + ".ser");
                    currentUserIndex = returnUserIndex(user) + 1;
                    System.out.println("Ange ditt lösenord?");
                    String userPass = scanner.nextLine();
                    if( userPass.equals("9")){start();}

                    while (!user.getPassword().equals(userPass)) {
                        System.out.println("Lösenordet stämmer inte. Vänligen försök igen eller tryck [9] för att återvända till startmenyn.");
                        userPass = scanner.nextLine();
                        if( userPass.equals("9")){start();}
                    }
                    System.out.println(users.size());
                    fileSaveFiles();
                    return;
                }
            }
            System.out.println("Användarnamnet finns ej registrerat. Försök igen eller tryck [9] för att återvända till startmenyn.");
            userName = scanner.nextLine();
            if( userName.equals("9")){start();}
            fileSaveFiles();
            System.out.println(users.size());
        } while (true);
    }
    private int returnUserIndex(User user) {
        return users.indexOf(user);
    }*/
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
        users = (ArrayList<User>) SaveAndLoadFile.loadObject("users.ser");
        books = (ArrayList<Book>) SaveAndLoadFile.loadObject("books.ser");
    }
    private void fileSaveFiles() {
        SaveAndLoadFile.saveObject("users.ser", users);
        SaveAndLoadFile.saveObject("books.ser", books);
     /*   if (currentUser instanceof Customer) {
            SaveAndLoadFile.saveObject("_customer_" + currentUser.getName() + ".ser", currentUser);
            SaveAndLoadFile.saveObject("books.ser", books);
            SaveAndLoadFile.saveObject("customers.ser", customers);
            SaveAndLoadFile.saveObject("librarians.ser", librarians);
            return;
        }
        SaveAndLoadFile.saveObject("_librarian_" + currentUser.getName() + ".ser", currentUser);
        SaveAndLoadFile.saveObject("books.ser", books);
        SaveAndLoadFile.saveObject("customers.ser", customers);
        SaveAndLoadFile.saveObject("librarians.ser", librarians);*/
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////D

    public void start() {
        fileLoadFiles();
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
                    start();
                    break;
                case 2:
                    logIn(false);
                    start();
                    break;
                case 3:
                    createAccount(true);
                    start();
                    break;
                case 4:
                    createAccount(false);
                    start();
                    break;
                case 5:
                    exitProgram();
                    break;
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
            System.out.println("[6] VISA LISTAN SORTERAD EFTER TITEL");
            System.out.println("[7] VISA LISTAN SORTERAD EFTER FÖRFATTARE");
            System.out.println("[8] SE HUR LÅNG TID DET ÄR KVAR PÅ DINA LÅNADE BÖCKER");
            System.out.println("[9] GÅ TILL STARTMENYN");

            do {
                try {
                    custMenu = Integer.parseInt(scanner.nextLine());
                    if (custMenu < 1 || custMenu > 9) {
                        throw new IndexOutOfBoundsException();
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Välj ett nummer mellan 1-9");
                }
            } while (true);

            switch (custMenu) {
                case 1:
                    bookProgram.notificationMsgForBorrowDays(users.get(currentUserIndex).getBooks());
                    bookProgram.headLines();
                    bookProgram.showAllBookInformationWithOutAvailability(books);
                    break;
                case 2:
                    bookProgram.headLinesAndStatus();
                    bookProgram.showAllBookList();
               //     bookProgram.showAllBookInformationWithOutAvailability(bookProgram.books);
                    break;
                case 3:
                    bookProgram.headLinesAndStatus();
                    bookProgram.showAllBookList();
                    customerProgram.borrowBook();
                    break;
                case 4:
                    bookProgram.headLinesAndStatus();
                    customerProgram.showMyBorrowedBooks(users.get(currentUserIndex).getBooks());
                    break;
                case 5:
                    bookProgram.headLinesAndStatus();
                    customerProgram.showMyBorrowedBooks(users.get(currentUserIndex).getBooks());
                    customerProgram.returnBook();
                    break;
                case 6:
                    bookProgram.headLines();
                    bookProgram.sortByTitle(books);
                    bookProgram.showAllBookInformationWithOutAvailability(books);
                    customerMenu();
                    break;
                case 7:
                    bookProgram.headLines();
                    bookProgram.sortByAuthor(books);
                    bookProgram.showAllBookInformationWithOutAvailability(books);
                    customerMenu();
                    break;
                case 8:
                    bookProgram.notificationMsgForBorrowDays(users.get(currentUserIndex).getBooks());
                //    customerMenu();
                    break;
                case 9:
                    fileSaveFiles();
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
                    bookProgram.showAllBookInformationWithOutAvailability(books);
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
                    librarianProgram.addBookToList();
                    break;
                case 5:
                    bookProgram.showAllBookList();
                    librarianProgram.removeBookFromList();
                    break;
                case 6:
                    System.out.println("HÄR KAN DU SE ALLA ANVÄNDARE SOM FINNS I SYSTEMET: ");
                    System.out.println("------------------------------------------------\n ");
                    librarianProgram.showUserNameAndOrBooks(users, false);
                    break;
                case 7:
                    System.out.println("DESSA ANVÄNDARE FINNS I SYSTEMET");
                    librarianProgram.showUserNameAndOrBooks(users, false);
                    librarianProgram.showUserByName(users, "Välkommen, skriv in namnet på användaren du söker", "Systemet hittade flera users på din sökning, vänligen specificera", "Din sökning hittades inte", "Listan är tom");
                    break;
                case 8:
                    System.out.println("HÄR KAN DU SE VILKA BÖCKER ANVÄNDARNA HAR LÅNAT: ");
                    System.out.println("----------------------------------------------\n ");
                    librarianProgram.showUserNameAndOrBooks(users, true);
                    break;
                case 9:
                    fileSaveFiles();
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
                User user  = (Customer) SaveAndLoadFile.loadObject(userName + ".ser");
            } catch (Exception e) {
                System.out.println("Inkorrekt användarnamn");
            }
            String userPassword = "";
            do{
                System.out.println("Skriv in lösenordet");
                userPassword = scanner.nextLine();
            }while (!users.get(currentUserIndex).getPassword().equals(userPassword));
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

    public static ArrayList<Book> getBooks() {
        return books;
    }

    public static ArrayList<User> getCustomers() {
        return users;
    }


    public static User getCurrentUser() {
        return users.get(currentUserIndex);
    }

    private void exitProgram() {
        System.exit(0);
    }
}