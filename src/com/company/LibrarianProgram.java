package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class LibrarianProgram {

    Scanner scanner = new Scanner(System.in);
    private ArrayList<String> books = new ArrayList<>();

    public void showUsers(ArrayList<User> userList) {
        if (userList.size() > 0) {
            for (User user : userList) {
                System.out.println(user.getName());
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
        Program.getBookProgram().books.add(new Book(newBook, newAuthor, newInfo, true));
    }

    public void removeBookFromList() {
        Program.getBookProgram().books.remove( Program.getBookProgram().removeBooksFromLibrary(Program.getCurrentUser().getBooks(), "Vilken bok vill du ta bort?", "Din sökning gav flera resultat", "Din sökning gav inget resultat", "listan är tom"));
    }

    public void showUserByName(ArrayList<User> bookListToReturnFrom, String msgWelcome, String msgRefineSearch, String msgIfFail, String msgIfEmptyList) {
        String tempMsgRefineSearch = msgRefineSearch;
        String tempMsgIfFail = msgIfFail;
        ArrayList<User> sameSearchUsers = new ArrayList<>();
        String userInput = "";
        if (bookListToReturnFrom.size() > 0) {
            System.out.println();
            System.out.println(msgWelcome);
            do {
                do {
                    msgIfFail = tempMsgIfFail;
                    eraseUserList(sameSearchUsers);
                    userInput = scanner.nextLine();
                    for (int i = 0; i < bookListToReturnFrom.size(); i++) {
                        if (bookListToReturnFrom.get(i).getName().toLowerCase().contains(userInput.toLowerCase())) {
                            sameSearchUsers.add(bookListToReturnFrom.get(i));
                        }
                    }
                    if(sameSearchUsers.size()>0){
                        msgIfFail = tempMsgRefineSearch;
                        showUsers(sameSearchUsers);

                    }
                    if (sameSearchUsers.size() == 1) {
                        System.out.println(sameSearchUsers.get(0));
                        //return sameSearchUsers.get(0);
                    }
                    System.out.println( msgIfFail);
                } while (true);
            } while (true);
        }
        System.out.println(msgIfEmptyList);
        //return null;
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
