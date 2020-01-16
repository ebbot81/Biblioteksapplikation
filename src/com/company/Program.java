package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {
    public transient Scanner scanner = new Scanner(System.in);
    private static BookProgram bookProgram = new BookProgram();
    private static CustomerProgram customerProgram = new CustomerProgram();
    private static LibrarianProgram librarianProgram = new LibrarianProgram();
    private static ArrayList<Customer> customers = new ArrayList<>();
    private static ArrayList<Librarian> librarians = new ArrayList<>();
    private Customer currentCustomer = new Customer("hassan", "1123");

    public Program() {
   /*     System.out.println(customers.size());
        System.out.println();
        addCustomer();
        System.out.println();
        System.out.println(customers.size());
        SaveAndLoadFile.saveObject(customers.get(0).getName() + ".ser", customers.get(0));
        Customer hassan = (Customer) SaveAndLoadFile.loadObject(customers.get(0).getName() + ".ser");
        System.out.println(hassan.getName()+ hassan.getPassword());
        login();*/
    }

    private void login() {
        System.out.println("Hej och välkommen, vänligen fyll i dina uppgifter och tryck sedan på ENTER");
        do {
            System.out.println("Skriv in ditt användarnamn: ");
            try {
                String userName = scanner.nextLine();
                currentCustomer = (Customer) SaveAndLoadFile.loadObject(userName + ".ser");
            } catch (Exception e) {
                System.out.println("Inkorrekt användarnamn");
            }
            String userPassword = "";
             do{
                System.out.println("Skriv in lösenordet");
                userPassword = scanner.nextLine();
            }while (!currentCustomer.getPassword().equals(userPassword));
            System.out.println("Korrekt inloggning");
            return;
        } while (true);
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
            System.out.println("\nVILKEN MENY VILL DU BESÖKA?");
            System.out.println("-----------------------------");
            System.out.println("[1] KUNDENS MENY");
            System.out.println("[2] BIBLIOTEKARIENS MENY (INLOGGNING KRÄVS)");
            System.out.println("[3] AVSLUTA");

            do {
                try {
                    chooseMenu = Integer.parseInt(scanner.nextLine());
                    if (chooseMenu < 1 || chooseMenu > 3) {
                        throw new IndexOutOfBoundsException();
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Välj ett nummer mellan 1-3");
                }
            } while (true);

            switch (chooseMenu) {
                case 1:
                    customerMenu();
                    break;
                case 2:
                    librarianMenu();
                    break;
                case 3:
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
            System.out.println("[2] SE ALLA TILLGÄNGLIGA BÖCKER SOM FINNS I APPEN");
            System.out.println("[3] SÖKER DU NÅGON SPECIFIK BOKS INFO?, TRYCK HÄR");
            System.out.println("[4] SÖKA PÅ BOKTITEL ELLER FÖRFATTARE");
            System.out.println("[5] LÅNA EN BOK");
            System.out.println("[6] SE VILKA BÖCKER DU LÅNAT");
            System.out.println("[7] LÄMNA TILLBAKA EN BOK");
            System.out.println("[8] AVSLUTA");

            do {
                try {
                    custMenu = Integer.parseInt(scanner.nextLine());
                    if (custMenu < 1 || custMenu > 8) {
                        throw new IndexOutOfBoundsException();
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Välj ett nummer mellan 1-8");
                }
            } while (true);

            switch (custMenu) {
                case 1:
                    bookProgram.headLines();
                    bookProgram.showAllBookInformationWithOutAvailability(bookProgram.books);
                    break;
                case 2:
                    System.out.println("\nHÄR SER DU VILKA BÖCKER SOM FINNS TILLGÄNGLIGA OCH VILKA SOM ÄR UTLÅNADE: ");
                    System.out.println("------------------------------------------------------------------------\n ");
                    bookProgram.showAllBookList();

                    break;
                case 3:
                    System.out.println("HÄR KAN DU SÖKA PÅ EN SPECIFIK BOKS INFO : ");
                    System.out.println("----------------------------------------\n ");
                    //      System.out.println(bookProgram.showAllBookInformation());
                    break;
                case 4:
                    System.out.println("HÄR KAN DU SÖKA PÅ BOKTITEL ELLER FÖRFATTARE: ");
                    System.out.println("-------------------------------------------\n ");
                    bookProgram.searchByTitle("Vilken titel söker du?", "Tyvärr finns inte titeln du sökte, försök igen", "Tyvärr är boken utlånad för tillfället");
                    bookProgram.searchByAuthor("Vilken författare söker du?", "Tyvärr finns inte författaren du sökte, försök igen");
                    break;
                case 5:
                    System.out.println("HÄR KAN DU LÅNA EN BOK:");
                    System.out.println("--------------------\n ");
                    bookProgram.showAllBookList();
                    customerProgram.borrowBook(currentCustomer);
                    break;
                case 6:
                    System.out.println("HÄR KAN DU SE VILKA BÖCKER DU LÅNAT: ");
                    System.out.println("----------------------------------\n ");
                    customerProgram.showMyBorrowedBooks(currentCustomer.getBooks());
                    break;
                case 7:
                    System.out.println("HÄR KAN DU LÄMNA TILLBAKA EN BOK: ");
                    System.out.println("-------------------------------\n ");
                    customerProgram.showMyBorrowedBooks(currentCustomer.getBooks());
                    customerProgram.returnBook(currentCustomer);
                    break;
                case 8:
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
            System.out.println("[2] SE ALLA UTLÅNADE BÖCKER");
            System.out.println("[3] LÄGGA TILL NYA BÖCKER");
            System.out.println("[4] TA BORT BÖCKER FRÅN LISTAN");
            System.out.println("[5] SE EN LISTA PÅ ALLA ANVÄNDARE");
            System.out.println("[6] SÖKA PÅ SPECIFIK ANVÄNDARE");
            System.out.println("[7] SE EN LISTA PÅ ANVÄNDARNAS LÅNADE BÖCKER");
            System.out.println("[8] LOGGA IN PÅ APPEN");
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
                    System.out.println("HÄR SER DU ALLA BÖCKER SOM FINNS: ");
                    System.out.println("-------------------------------\n ");
                    bookProgram.showAllBookInformationWithOutAvailability(bookProgram.books);
                    break;
                case 2:
                    System.out.println("HÄR SER DU ALLA UTLÅNADE BÖCKER: ");
                    System.out.println("------------------------------\n ");
                    break;
                case 3:
                    System.out.println("HÄR KAN DU LÄGGA TILL NYA BÖCKER: ");
                    System.out.println("-------------------------------\n ");
                    librarianProgram.addBookToList();
                    break;
                case 4:
                    System.out.println("HÄR KAN DU TA BORT BÖCKER: ");
                    System.out.println("------------------------\n ");
                    librarianProgram.removeBookFromList();
                    break;
                case 5:
                    System.out.println("HÄR KAN DU SE ALLA ANVÄNDARE SOM FINNS I SYSTEMET: ");
                    System.out.println("------------------------------------------------\n ");
                    break;
                case 6:
                    System.out.println("HÄR KAN DU SÖKA PÅ EN SPECIFIK ANVÄNDARES NAMN:");
                    System.out.println("--------------------------------------------\n ");
                    break;
                case 7:
                    System.out.println("HÄR KAN DU SE VILKA BÖCKER ANVÄNDARNA HAR LÅNAT: ");
                    System.out.println("----------------------------------------------\n ");
                    break;
                case 8:
                    System.out.println("HÄR KAN DU LOGGA IN PÅ APPEN: ");
                    System.out.println("---------------------------\n ");
                    break;
                case 9:
                    start();
                    return;
                default:
                    break;
            }
        } while (true);
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

    public static CustomerProgram getCustomerProgram() {
        return customerProgram;
    }

    public static BookProgram getBookProgram() {
        return bookProgram;
    }

    public static ArrayList<Customer> getCustomers() {
        return customers;
    }

    public static ArrayList<Librarian> getLibrarians() {
        return librarians;
    }
}