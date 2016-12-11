package br.com.renandias.bookshelf;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import br.com.renandias.bookshelf.DataBase.DataBase;
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

        // Date picker
        final Calendar cal = Calendar.getInstance();
        year_x = cal.get(cal.YEAR);
        month_x = cal.get(cal.MONTH);
        day_x = cal.get(cal.DAY_OF_MONTH);
    }

    //Time Picker

    private final int DIALOG_ID_TIME = 0;
    private int hour_x, minute_x;

    @OnClick(R.id.show_time)
    public void showTimePicker() {
        showDialog(DIALOG_ID_TIME);
    }

    protected TimePickerDialog.OnTimeSetListener kTimePickerListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            hour_x = hourOfDay;
            minute_x = minute;
            Toast.makeText(AddReminder.this, "" + hour_x + " " + minute_x, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public Dialog onCreateDialog(int id) {
        if(id == DIALOG_ID_TIME)
            return new TimePickerDialog(AddReminder.this, kTimePickerListener, hour_x, minute_x, false);
        else if(id == DIALOG_ID_DATE)
            return new DatePickerDialog(AddReminder.this, kDatePickerListenetr, year_x, month_x, day_x);
        return null;
    }
    //

    //DatePicker
    private final int DIALOG_ID_DATE = 1;
    private int year_x, month_x, day_x;

    @OnClick(R.id.show_date)
    public void showDatePicker() {
        showDialog(DIALOG_ID_DATE);
    }

    private DatePickerDialog.OnDateSetListener kDatePickerListenetr = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            year_x = year;
            month_x = monthOfYear + 1;
            day_x = dayOfMonth;
            Toast.makeText(AddReminder.this, "" + year_x + " " + month_x + " " + day_x, Toast.LENGTH_SHORT).show();
        }
    };
    //

    @OnClick(R.id.save_reminder)
    public void saveReminder5() {

        int hour;
        int minute;

        Long alertTime = new Date().getTime()+30*1000;

        Intent alertIntent = new Intent(this, AlertReceiver.class);
        alertIntent.putExtra("bookName", bookNameText.getText().toString());

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        alarmManager.set(AlarmManager.RTC_WAKEUP, alertTime,
                PendingIntent.getBroadcast(this, 1, alertIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT));

        Reminder rem = new Reminder(new Long(33), new Long(1), "quando eu quiser");
        DataBase db = new DataBase(this);
        db.saveReminder(rem);

        Toast.makeText(this, "New Reminder", Toast.LENGTH_SHORT).show();

        //add reminder to the data base!!

    }
}
