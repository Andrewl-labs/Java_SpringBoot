package org.example.domain;

import java.time.LocalDate;

public class Loan {
    private int id;
    private Person member;
    private Book book;
    private LocalDate date;
    private LoanStatus status;

    public Loan() {}

    public Loan(int id, Person member, Book book, LocalDate date, LoanStatus status) {
        this.id = id;
        this.member = member;
        this.book = book;
        this.date = date;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public LocalDate getDate() {
        return date;
    }

    public Book getBook() {
        return book;
    }

    public Person getMember() {
        return member;
    }
}
