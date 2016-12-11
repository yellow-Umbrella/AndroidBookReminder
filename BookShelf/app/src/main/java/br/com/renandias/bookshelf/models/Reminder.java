package br.com.renandias.bookshelf.models;

/**
 * Created by Renan on 11/12/2016.
 */

public class Reminder {

    private Long id;
    private Long notifID;
    private Long bookId;
    private String date;

    public Reminder() {}

    public Reminder(Long notifId, Long bookId, String date) {
        this.notifID = notifId;
        this.bookId = bookId;
        this.date = date;
    }

    public Reminder(Long id, Long notifID, Long bookId, String date) {
        this.id = id;
        this.notifID = notifID;
        this.bookId = bookId;
        this.date = date;
    }

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

}
