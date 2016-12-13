package br.com.renandias.bookshelf;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import br.com.renandias.bookshelf.DataBase.DataBase;
import br.com.renandias.bookshelf.adapter.ReminderAdapter;
import br.com.renandias.bookshelf.models.Reminder;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Activity que gerencia a tela que mostra os lembretes dos usuarios.
 */
public class MyReminders extends AppCompatActivity {

    //Binding da ListView
    @Bind(R.id.all_reminders_listView)
    ListView viewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reminders);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        DataBase db = new DataBase(this);

        List<Reminder> list = db.getAllReminders();

        ReminderAdapter adapter = new ReminderAdapter(this, list);
        viewList.setAdapter(adapter);
    }

    /**
     * Botão que leva usuário a sua lista de livros para criar um lembrete.
     */
    @OnClick(R.id.fab)
    public void goMyBooks() {
        Intent intent = new Intent(this, MyBooks.class);
        startActivity(intent);
    }

}
