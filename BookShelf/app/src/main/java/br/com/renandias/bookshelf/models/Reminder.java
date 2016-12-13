package br.com.renandias.bookshelf.models;

/**
 * Created by Renan on 11/12/2016.
 */

/**
 * Classe model de um Lembrete(Reminder) com id, id da notificação, id do livro, nome do livro e
 * data da notificação.
 */
public class Reminder {

    //Atributos
    private Long id;
    private Long notifID;
    private Long bookId;
    private String bookName;
    private String date;

    //Constructors
    public Reminder() {}

    public Reminder(Long notifId, Long bookId, String bookName, String date) {
        this.notifID = notifId;
        this.bookId = bookId;
        this.bookName = bookName;
        this.date = date;
    }

    public Reminder(Long id, Long notifID, Long bookId, String bookName, String date) {
        this.id = id;
        this.notifID = notifID;
        this.bookId = bookId;
        this.bookName = bookName;
        this.date = date;
    }

    //Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNotifID() {
        return notifID;
    }

    public void setNotifID(Long notifID) {
        this.notifID = notifID;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}
