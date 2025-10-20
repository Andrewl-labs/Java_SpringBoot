package org.example.domain;

public class Book {
    private int id;
    private String title;
    private String author;
    private int available;
    private int borrowed;

    // Full Constructor
    public Book(int id, String title, String author, int available, int borrowed) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.available = available;
        this.borrowed = borrowed;
    }
    // Void constructor
    public Book() {}
    // New book constructor without id and borrowed
    public Book(String title, String author, int available) {
        this.title = title;
        this.author = author;
        this.available = available;
    }
    // New book constructor without id, borrowed and available
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public int getBorrowed() {
        return borrowed;
    }

    public int getAvailable() {
        return available;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }
}