package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class LibrarianProgram {

    Scanner scanner = new Scanner(System.in);

    public void showUserNameAndOrBooks(ArrayList<User> userList, boolean showUserBooksOrNot) {
        if (userList.size() > 0) {
            for (User user : userList) {
                if (user instanceof Customer) {
                    if (showUserBooksOrNot) {
                        if (user.getBooks().size() > 0) {
                            System.out.println(user.getName());
                            Program.getBookProgram().showAllBookInformationWithOutAvailability(user.getBooks());
                        }
                    } else {
                        System.out.println(user.getName());
                    }
                }
            }
        }
    }

    public void addBookToList() {
        String newBook;
        String newAuthor;
        String newInfo;
        do {
            System.out.println("Vad heter boken du vill lägga till i listan? ");
            newBook = scanner.nextLine();
        } while (newBook.isBlank());
        do {

            System.out.println("Vem har skrivit boken? ");
            newAuthor = scanner.nextLine();
        } while (newAuthor.isBlank());
        do {
            System.out.println("Skriv in lite information om boken. ");
            newInfo = scanner.nextLine();
        } while (newInfo.isBlank());
        System.out.println("Du lade till:\nBok: " + newBook + "\nFörfattare: " + newAuthor + "\nInformation om boken: " + newInfo);
        Program.getBooks().add(new Book(newBook, newAuthor, newInfo, true));
    }

    public void removeBookFromList() {
        Program.getBooks().remove(Program.getBookProgram().returnBooksFromLibrary(Program.getBooks(), "Vilken bok vill du ta bort?", "Din sökning gav flera resultat", "Din sökning gav inget resultat", "listan är tom"));
    }

    public void showUserByName(ArrayList<User> userListToReturnFrom, String msgWelcome, String msgRefineSearch, String msgIfFail, String msgIfEmptyList) {
        String tempMsgRefineSearch = msgRefineSearch;
        String tempMsgIfFail = msgIfFail;
        ArrayList<User> sameSearchUsers = new ArrayList<>();
        String userInput = "";
        if (userListToReturnFrom.size() > 0) {
            System.out.println();
            System.out.println(msgWelcome);
            do {
                msgIfFail = tempMsgIfFail;
                eraseUserList(sameSearchUsers);
                userInput = scanner.nextLine();
                for (int i = 0; i < userListToReturnFrom.size(); i++) {
                    if (userListToReturnFrom.get(i).getName().toLowerCase().contains(userInput.toLowerCase()) &&
                            userListToReturnFrom.get(i) instanceof Customer) {
                        sameSearchUsers.add(userListToReturnFrom.get(i));
                    }
                }
                if (sameSearchUsers.size() > 1) {
                    msgIfFail = tempMsgRefineSearch;
                    showUserNameAndOrBooks(sameSearchUsers, false);

                }
                if (sameSearchUsers.size() == 1) {
                    System.out.println(sameSearchUsers.get(0).getName());
                    return;
                }
                System.out.println(msgIfFail);
            } while (true);
        }
        System.out.println(msgIfEmptyList);
    }

    public void eraseUserList(ArrayList<User> userListToEmpty) {
        if (userListToEmpty.size() > 0) {
            do {
                userListToEmpty.remove(userListToEmpty.get(0));
            } while (userListToEmpty.size() > 0);
            return;
        }
    }
}
