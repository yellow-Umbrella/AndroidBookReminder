package br.com.renandias.bookshelf;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import br.com.renandias.bookshelf.DataBase.BookDataBase;
import br.com.renandias.bookshelf.models.Book;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MyBooks extends AppCompatActivity {

    @Bind(R.id.my_books)
    ListView viewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_books);
        ButterKnife.bind(this);

        BookDataBase bdb = new BookDataBase(this);

        List<Book> list = bdb.getAllBooks();
        String names[] = new String[list.size()];

        int i = 0;
        for(Book x: list)
            names[i++] = x.getName();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        viewList.setAdapter(adapter);
    }
}
