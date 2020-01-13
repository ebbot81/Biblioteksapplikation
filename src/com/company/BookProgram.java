package com.company;

import java.util.ArrayList;

public class BookProgram {

    ArrayList<Book> books = new ArrayList<>();

    public BookProgram() {
        addBooks();
        showAllBookList();
    }

    private void addBooks() {
        books.add(new Book("Ondskan", "Jan Guillou", "Pojken som blev misshandlad av både sin pappa och skolkamraterna, innan han slutligen fick sin hämnd", true));
        books.add(new Book("Tomten är far till alla barnen", "Ernst Göransson", "Tomten krånglar till det för sig själv och alla andra", true));
        books.add(new Book("Stina Bakar", "Stina Olsson", "Stina älskar att laga lasagne", true));
        books.add(new Book("Dinosauriernas återkomst", "Jens Schneider", "En verklighetsbaserad historia om när Dinosaurierna återvände till civilisationen", true));
        books.add(new Book("Antika trädgårdar", "Örjan Rahmberg", "Väldigt antika trädgårdar visas upp...", true));
        books.add(new Book("Grönsakskungen", "Lennart Svahnsson", "Lennart kan allt om grönsaker", true));
        books.add(new Book("Hattkriget", "Lena Borselius", "Vem kan ta på sig flest hattar på kortast tid", true));
        books.add(new Book("Något i görningen", "Alex Schulman", "En inflytelserik politiker har något i görningen", true));
        books.add(new Book("Det vita huset", "Gösta Anderhjelm", "Huvudpersonen bygger ett vitt hus på två månader", true));
        books.add(new Book("Java For Pros", "Hassan A", "Hassan jobbar som bagare, men drömmer om att bli programmerare", true));
    }

    private void showAllBookList() {
        for (Book book : books) {
            System.out.println(showAllBookInformationAndAvailability(book));
            ;
        }
    }

    private String showAllBookInformation(Book book) {
        return String.format("%-20s %-20s %-50s\n ", book.getTitle(), book.getAuthor(), book.getInformation());
    }

    private String showAllBookInformationAndAvailability(Book book) {
        return String.format("%-20s %-20s %-50s %b\n ", book.getTitle(), book.getAuthor(), book.getInformation(), book.isAvailability());


    }
}
