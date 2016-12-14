package br.com.renandias.bookshelf.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.renandias.bookshelf.R;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Activity que gerencia a tela inicial.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    /**
     * Botão que leva usuario para tela de adicionar livros.
     */
    @OnClick(R.id.add_book)
    public void goAddBook() {
        Intent goAddBook = new Intent(MainActivity.this, AddBook.class);
        startActivity(goAddBook);
    }

    /**
     * Botãoq que leva usuario para tela de seus livros.
     */
    @OnClick(R.id.my_books)
    public void goMyBooks() {
        Intent goMyBooks = new Intent(MainActivity.this, MyBooks.class);
        startActivity(goMyBooks);
    }

    /**
     * Botão que leva usuario para tela de seus lembretes.
     */
    @OnClick(R.id.reminders)
    public void goReminders() {
        Intent goReminders = new Intent(MainActivity.this, MyReminders.class);
        startActivity(goReminders);
    }

}
