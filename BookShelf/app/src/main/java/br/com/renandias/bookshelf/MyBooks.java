package br.com.renandias.bookshelf;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import br.com.renandias.bookshelf.DataBase.BookDataBase;
import br.com.renandias.bookshelf.DataBase.DataBase;
import br.com.renandias.bookshelf.models.Book;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class MyBooks extends AppCompatActivity {

    @Bind(R.id.my_books)
    ListView viewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_books);
        ButterKnife.bind(this);

        DataBase db = new DataBase(this);

        List<Book> list = db.getAllBooks();
        String names[] = new String[list.size()];

        int i = 0;
        for(Book x: list)
            names[i++] = x.getName();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names);
        viewList.setAdapter(adapter);
    }

    @OnItemClick(R.id.my_books)
    public void onTtemClick(AdapterView<?> parent, int position) {

        Intent goAddReminder = new Intent(this, AddReminder.class);

        String name = (String) parent.getAdapter().getItem(position); // don't know if it works  //It does!!
        goAddReminder.putExtra("bookName", name);
        startActivity(goAddReminder);

    }


}
