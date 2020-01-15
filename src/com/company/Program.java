package com.company;

import java.util.Scanner;

public class Program {
    CustomerProgram customerProgram = new CustomerProgram();
    Scanner scanner = new Scanner(System.in);
    BookProgram bookProgram = new BookProgram();

    public Program() {
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
                    System.out.println("HÄR SER DU ALLA BÖCKER SOM FINNS: ");
                    System.out.println("-------------------------------\n ");
                //    bookProgram.showAllBookInformation();

                    break;
                case 2:
                    System.out.println("HÄR SER DU VILKA BÖCKER SOM FINNS TILLGÄNGLIGA: ");
                    System.out.println("---------------------------------------------\n ");
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
                    bookProgram.searchByTitle( "Vilken titel söker du?", "Tyvärr finns inte titeln du sökte, försök igen", "Tyvärr är boken utlånad för tillfället");
                    bookProgram.searchByAuthor("Vilken författare söker du?", "Tyvärr finns inte författaren du sökte, försök igen");
                    break;
                case 5:
                    System.out.println("HÄR KAN DU LÅNA EN BOK:");
                    System.out.println("--------------------\n ");

                    break;
                case 6:
                    System.out.println("HÄR KAN DU SE VILKA BÖCKER DU LÅNAT: ");
                    System.out.println("----------------------------------\n ");
                    customerProgram.showMyBorrowedBooks();
                    break;
                case 7:
                    System.out.println("HÄR KAN DU LÄMNA TILLBAKA EN BOK: ");
                    System.out.println("-------------------------------\n ");
                    break;
                case 8:
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
            System.out.println("[1] SE ALLA UTLÅNADE BÖCKER");
            System.out.println("[2] LÄGGA TILL NYA BÖCKER");
            System.out.println("[3] TA BORT BÖCKER FRÅN LISTAN");
            System.out.println("[4] SE EN LISTA PÅ ALLA ANVÄNDARE");
            System.out.println("[5] SÖKA PÅ SPECIFIK ANVÄNDARE");
            System.out.println("[6] SE EN LISTA PÅ ANVÄNDARNAS LÅNADE BÖCKER");
            System.out.println("[7] LOGGA IN PÅ APPEN");
            System.out.println("[8] AVSLUTA");

            do {
                try {
                    libMenu = Integer.parseInt(scanner.nextLine());
                    if (libMenu < 1 || libMenu > 8) {
                        throw new IndexOutOfBoundsException();
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Välj ett nummer mellan 1-8");
                }
            } while (true);

            switch (libMenu) {
                case 1:
                    System.out.println("HÄR SER DU ALLA UTLÅNADE BÖCKER: ");
                    System.out.println("------------------------------\n ");
                    break;
                case 2:
                    System.out.println("HÄR KAN DU LÄGGA TILL NYA BÖCKER: ");
                    System.out.println("-------------------------------\n ");
                    customerProgram.addBookToList("","","");
                    break;
                case 3:
                    System.out.println("HÄR KAN DU TA BORT BÖCKER: ");
                    System.out.println("------------------------\n ");
                    customerProgram.removeBookFromList();
                    break;
                case 4:
                    System.out.println("HÄR KAN DU SE ALLA ANVÄNDARE SOM FINNS I SYSTEMET: ");
                    System.out.println("------------------------------------------------\n ");
                    break;
                case 5:
                    System.out.println("HÄR KAN DU SÖKA PÅ EN SPECIFIK ANVÄNDARES NAMN:");
                    System.out.println("--------------------------------------------\n ");
                    break;
                case 6:
                    System.out.println("HÄR KAN DU SE VILKA BÖCKER ANVÄNDARNA HAR LÅNAT: ");
                    System.out.println("----------------------------------------------\n ");
                    break;
                case 7:
                    System.out.println("HÄR KAN DU LOGGA IN PÅ APPEN: ");
                    System.out.println("---------------------------\n ");
                    break;
                case 8:
                    return;
                default:
                    break;
            }
        } while (true);
    }
}