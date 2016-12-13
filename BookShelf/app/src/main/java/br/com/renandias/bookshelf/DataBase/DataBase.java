package br.com.renandias.bookshelf.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.renandias.bookshelf.models.Book;
import br.com.renandias.bookshelf.models.Reminder;

/**
 * Created by Renan on 11/12/2016.
 */

public class DataBase extends SQLiteOpenHelper {

    //Database
    private static final String DATABASE_NAME = "bookshelf";
    private static final int DATABASE_VERSION = 1;

    //Book model
    private static final int BOOK_ID = 0;
    private static final int BOOK_NAME = 1;
    private static final int BOOK_PAGES = 2;
    private static final int BOOK_IMAGE_BITMAP = 3;

    //Reminder Model
    private static final int REMINDER_ID = 0;
    private static final int REMINDER_NOTIF = 1;
    private static final int REMINDER_BOOK_ID = 2;
    private static final int REMINDER_DATE = 3;

    public DataBase(Context context) { super(context, DATABASE_NAME, null, DATABASE_VERSION); }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table book (id integer primary key, name text, pages integer, image_byte blob)";
        db.execSQL(sql);
        sql = "create table reminder (id integer primary key, notif_id integer, book_id integer, reminder_date text)";
        db.execSQL(sql);
    }

//    @Override
//    public void onOpen(SQLiteDatabase db) {
//        super.onOpen(db);
//        if(!db.isReadOnly()) {
//            db.execSQL("PRAGMA foreign_key=ON;");
//        }
//    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table if exist book";
        db.execSQL(sql);
        sql = "drop table if exist reminder";
        db.execSQL(sql);

        onCreate(db);
    }

    public void saveBook(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", book.getName());
        values.put("pages", book.getPages());
        values.put("image_byte", DbBitmapUtility.getBytes(book.getBitmapImage()));

        db.insert("book", null, values);

        db.close();
    }

    public List<Book> getAllBooks() {

        List<Book> list = new ArrayList<>();

        String sql = "select * from book";
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()) {

            do {
                list.add(new Book(cursor.getLong(BOOK_ID), cursor.getString(BOOK_NAME), cursor.getInt(BOOK_PAGES), DbBitmapUtility.getImage(cursor.getBlob(BOOK_IMAGE_BITMAP))));
            } while(cursor.moveToNext());

        }

        return list;
    }

    public void saveReminder(Reminder reminder) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id", reminder.getId());
        values.put("notif_id", reminder.getNotifID());
        values.put("book_id", reminder.getBookId());
        values.put("reminder_date", reminder.getDate());

        db.insert("reminder", null, values);

        db.close();
    }

    public List<Reminder> getAllReminders() {

        List<Reminder> list = new ArrayList<>();

        String sql = "select * from reminder";
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()) {

            do {

                sql = "select * from book where id=" + cursor.getInt(REMINDER_BOOK_ID);
                Cursor bookCursor = db.rawQuery(sql, null);

                Book book;

                if(bookCursor.moveToFirst()) {                                                                                      //fix it
                    book = new Book(cursor.getLong(BOOK_ID), cursor.getString(BOOK_NAME), cursor.getInt(BOOK_PAGES));
                } else {
                    book = new Book();
                }

                list.add(new Reminder(cursor.getLong(REMINDER_ID), cursor.getLong(REMINDER_NOTIF), cursor.getLong(REMINDER_BOOK_ID), cursor.getString(REMINDER_DATE)));
            }while(cursor.moveToNext());

        }

        return list;
    }

}
