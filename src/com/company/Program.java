package com.company;

import java.util.Scanner;

public class Program {
    CustomerProgram customerProgram = new CustomerProgram();
    Scanner scanner = new Scanner(System.in);
  //  BookProgram bookProgram = new BookProgram();

    public Program() {
    }

    public void start() {
        int menu;
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
            System.out.println("[8] GÅ TILL BIBLIOTEKARIENS MENY");
            System.out.println("[9] AVSLUTA");

            do {
                try {
                    menu = Integer.parseInt(scanner.nextLine());
                    if (menu < 1 || menu > 9) {
                        throw new IndexOutOfBoundsException();
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Välj ett nummer mellan 1-9");
                }
            } while (true);

            switch (menu) {
                case 1:
                    System.out.println("HÄR SER DU ALLA BÖCKER SOM FINNS: ");
                    System.out.println("-------------------------------\n ");
          //          bookProgram.showAllBookList();
                    break;
                case 2:
                    System.out.println("HÄR SER DU VILKA BÖCKER SOM FINNS TILLGÄNGLIGA: ");
                    System.out.println("---------------------------------------------\n ");

                    break;
                case 3:
                    System.out.println("HÄR KAN DU SÖKA PÅ EN SPECIFIK BOKS INFO : ");
                    System.out.println("----------------------------------------\n ");
                //    bookProgram.showAllBookInformation(Book book);
                    break;
                case 4:
                    System.out.println("HÄR KAN DU SÖKA PÅ BOKTITEL ELLER FÖRFATTARE: ");
                    System.out.println("-------------------------------------------\n ");

                    break;
                case 5:
                    System.out.println("HÄR KAN DU LÅNA EN BOK:");
                    System.out.println("--------------------\n ");

                    break;
                case 6:
                    System.out.println("HÄR KAN DU SE VILKA BÖCKER DU LÅNAT: ");
                    System.out.println("----------------------------------\n ");

                    break;
                case 7:
                    System.out.println("HÄR KAN DU LÄMNA TILLBAKA EN BOK: ");
                    System.out.println("-------------------------------\n ");

                    break;
                case 8:
                    System.out.println("HÄR ÄR BIBLIOTEKARIENS MENY: ");
                    System.out.println("--------------------------\n ");
                    librarianMenu();
                case 9:
                    return;

                default:
                    break;
            }
        } while (true);
    }

    private void librarianMenu() {
        int menu;
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
            System.out.println("[8] GÅ TILL KUNDENS MENY");
            System.out.println("[9] AVSLUTA");

            do {
                try {
                    menu = Integer.parseInt(scanner.nextLine());
                    if (menu < 1 || menu > 9) {
                        throw new IndexOutOfBoundsException();
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Välj ett nummer mellan 1-9");
                }
            } while (true);

            switch (menu) {
                case 1:
                    System.out.println("HÄR SER DU ALLA UTLÅNADE BÖCKER: ");
                    System.out.println("------------------------------\n ");
                    break;
                case 2:
                    System.out.println("HÄR KAN DU LÄGGA TILL NYA BÖCKER: ");
                    System.out.println("-------------------------------\n ");
                    break;
                case 3:
                    System.out.println("HÄR KAN DU TA BORT BÖCKER: ");
                    System.out.println("------------------------\n ");
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
                    System.out.println("HÄR ÄR KUNDENS MENY: ");
                    System.out.println("------------------\n ");
                    start();
                case 9:
                    return;

                default:
                    break;
            }
        } while (true);
    }
}


