package br.com.renandias.bookshelf;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import br.com.renandias.bookshelf.DataBase.DataBase;
import br.com.renandias.bookshelf.models.Reminder;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyReminders extends AppCompatActivity {

    @Bind(R.id.all_reminders_listView)
    ListView viewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reminders);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DataBase db = new DataBase(this);

        List<Reminder> list = db.getAllReminders();
        String bookReminderName[] = new String[list.size()];

        int i = 0;
        for(Reminder x: list)
            bookReminderName[i++] = String.valueOf(x.getBookId()); //null pointer exception

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bookReminderName);
        viewList.setAdapter(adapter);
    }

    @OnClick(R.id.fab)
    public void goMyBooks() {
        Intent intent = new Intent(this, MyBooks.class);
        startActivity(intent);
    }

}
