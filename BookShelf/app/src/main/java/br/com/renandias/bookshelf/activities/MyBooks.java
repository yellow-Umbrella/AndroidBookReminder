package br.com.renandias.bookshelf.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import br.com.renandias.bookshelf.DataBase.DataBase;
import br.com.renandias.bookshelf.R;
import br.com.renandias.bookshelf.adapter.BookAdapter;
import br.com.renandias.bookshelf.models.Book;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

/**
 * Activity que gerencia a tela que mostra os livros cadastrados do usuario.
 */
public class MyBooks extends AppCompatActivity {

    //Binding da ListView
    @Bind(R.id.my_books)
    ListView viewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_books);
        ButterKnife.bind(this);

        DataBase db = new DataBase(this);

        List<Book> list = db.getAllBooks();

        BookAdapter adapter = new BookAdapter(this, list);

        viewList.setAdapter(adapter);
    }

    /**
     * Método que leva usuario para criar um lembrete do livro selecionado.
     * @param parent
     * @param position
     */
    @OnItemClick(R.id.my_books)
    public void onTtemClick(AdapterView<?> parent, int position) {
        Intent goAddReminder = new Intent(this, AddReminder.class);

        Book book = (Book) parent.getAdapter().getItem(position);
        String name = book.getName();
        Long bookId = book.getId();
        Integer pages = book.getPages();
        goAddReminder.putExtra("bookName", name);
        goAddReminder.putExtra("bookId", bookId);

        startActivity(goAddReminder);
    }


}
