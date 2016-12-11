package br.com.renandias.bookshelf;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import br.com.renandias.bookshelf.models.Book;
import br.com.renandias.bookshelf.models.Reminder;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddReminder extends AppCompatActivity {

    @Bind(R.id.book_name)
    TextView bookNameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);
        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        String bookName = extras.getString("bookName");

        bookNameText.setText(bookName);
    }

    @OnClick(R.id.save_reminder)
    public void saveReminder5() {

        Long alertTime = new Date().getTime()+5*1000;

        Intent alertIntent = new Intent(this, AlertReceiver.class);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        alarmManager.set(AlarmManager.RTC_WAKEUP, alertTime,
                PendingIntent.getBroadcast(this, 1, alertIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT));

        Toast.makeText(this, "New Reminder", Toast.LENGTH_SHORT).show();

        //add reminder to the data base!!

    }
}
