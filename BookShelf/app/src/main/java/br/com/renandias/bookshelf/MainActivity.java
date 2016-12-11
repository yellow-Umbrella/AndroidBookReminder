package br.com.renandias.bookshelf;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.add_book)
    public void goAddBook() {
        Intent goAddBook = new Intent(MainActivity.this, AddBook.class);
        startActivity(goAddBook);
    }

    @OnClick(R.id.my_books)
    public void goMyBooks() {
        Intent goMyBooks = new Intent(MainActivity.this, MyBooks.class);
        startActivity(goMyBooks);
    }

    @OnClick(R.id.reminders)
    public void goReminders() {
        Intent goReminders = new Intent(MainActivity.this, MyReminders.class);
        startActivity(goReminders);
    }

}
