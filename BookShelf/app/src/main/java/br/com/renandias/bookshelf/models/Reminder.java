package br.com.renandias.bookshelf.models;

/**
 * Created by Renan on 11/12/2016.
 */

public class Reminder {

    private Long id;
    private Book book;
    private String date;

    public Reminder() {}

    public Reminder(Book book, String date) {
        this.book = book;
        this.date = date;
    }

    public Reminder(Long id, Book book, String date) {
        this.id = id;
        this.book = book;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
