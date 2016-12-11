package br.com.renandias.bookshelf;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import br.com.renandias.bookshelf.DataBase.BookDataBase;
import br.com.renandias.bookshelf.DataBase.DataBase;
import br.com.renandias.bookshelf.models.Book;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddBook extends AppCompatActivity {

    @Bind(R.id.book_name)
    EditText bookName;
    @Bind(R.id.pages)
    EditText bookPages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.save_button)
    public void saveBook() {

        String name = bookName.getText().toString();
        Integer pages;
        try {
            pages = Integer.valueOf(bookPages.getText().toString());
        } catch(NumberFormatException exception) {
            pages = null;
        }

        if(!name.isEmpty() && pages != null) {

            DataBase db = new DataBase(this);
            db.saveBook(new Book(name, pages));

            Toast.makeText(this, "Book Saved", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Missing data", Toast.LENGTH_SHORT).show();
        }

    }
}
