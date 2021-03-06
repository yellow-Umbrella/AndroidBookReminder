package br.com.renandias.bookshelf.activities;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import br.com.renandias.bookshelf.utility.AlertReceiver;
import br.com.renandias.bookshelf.DataBase.DataBase;
import br.com.renandias.bookshelf.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CheckReminder extends AppCompatActivity {

    @Bind(R.id.check_rem_book_name)
    TextView remBookName;
    @Bind(R.id.check_rem_date)
    TextView remDate;

    private Long reminderId;
    private Integer notifId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_reminder);
        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();

        remBookName.setText(extras.getString("bookName"));
        remDate.setText(extras.getString("date"));
        reminderId = extras.getLong("id");
        notifId = extras.getInt("notif_id");

    }

    @OnClick(R.id.delete_reminder)
    public void deleteReminder() {
        Intent goBackMyReminders = new Intent(this, MyReminders.class);
        goBackMyReminders.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        DataBase db = new DataBase(this);
        db.deleteReminder(reminderId);

        Context context = getApplicationContext();

        NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        manager.cancel(notifId);

        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, notifId, intent, 0);
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);


        startActivity(goBackMyReminders);
    }


}
