package br.com.renandias.bookshelf.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.renandias.bookshelf.models.Book;

/**
 * Created by Renan on 10/12/2016.
 */

public class BookDataBase extends SQLiteOpenHelper {

    //Database
    private static final String DATABASE_NAME = "bookdb";
    private static final int DATABASE_VERSION = 1;

    //Book model
    private static final int ID = 0;
    private static final int NAME = 1;
    private static final int PAGES = 2;

    public BookDataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table book (id integer primary key, name text, pages integer)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table if exist book";
        db.execSQL(sql);

        onCreate(db);
    }

    public void save(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", book.getName());
        values.put("pages", book.getPages());

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

                list.add(new Book(cursor.getLong(ID), cursor.getString(NAME), cursor.getInt(PAGES)));

            } while(cursor.moveToNext());

        }

        return list;
    }

}
