package br.com.renandias.bookshelf.activities;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import br.com.renandias.bookshelf.utility.AlertReceiver;
import br.com.renandias.bookshelf.DataBase.DataBase;
import br.com.renandias.bookshelf.R;
import br.com.renandias.bookshelf.models.Reminder;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Classe que gerencia a tela de adicionar um reminder.
 */
public class AddReminder extends AppCompatActivity {

    //Binding dos views na tela.
    @Bind(R.id.book_name)
    TextView bookNameText;
    @Bind(R.id.reminder_date)
    TextView reminderDate;
    @Bind(R.id.reminder_time)
    TextView reminderTime;

    private Calendar cal;
    private Long bookId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);
        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        String bookName = extras.getString("bookName");
        bookNameText.setText(bookName);
        bookId = extras.getLong("bookId");

        //Date picker
        /*final Calendar*/ cal = Calendar.getInstance();
        year_x = cal.get(cal.YEAR);
        month_x = cal.get(cal.MONTH);
        day_x = cal.get(cal.DAY_OF_MONTH);

        //Time picker
        hour_x = cal.get(cal.HOUR_OF_DAY);
        minute_x = cal.get(cal.MINUTE);

        reminderDate.setText(day_x + "/" + (month_x+1) + "/" + year_x);
    }

    //Set Reminder
    private boolean datePicked = false;
    private boolean timePicked = false;

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

            if(minute_x >= 10)
                reminderTime.setText(hour_x + ":" + minute_x);
            else
                reminderTime.setText(hour_x + ":0" + minute_x);

            //Toast.makeText(AddReminder.this, "" + hour_x + " " + minute_x, Toast.LENGTH_SHORT).show();
            timePicked = true;
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
            month_x = monthOfYear;
            day_x = dayOfMonth;

            reminderDate.setText(day_x + "/" + (month_x+1) + "/" + year_x);

            //Toast.makeText(AddReminder.this, "" + year_x + " " + month_x + " " + day_x, Toast.LENGTH_SHORT).show();
            datePicked = true;
        }
    };
    //

    //Kinda Works
    /**
     *Seta o reminder na hora e data selecionada.
     */
    @OnClick(R.id.save_reminder)
    public void saveReminder5() {
        long timeMill = 0;

        if(datePicked == true && timePicked == true) {

            timeMill = dato2Mill(year_x, month_x, day_x, hour_x, minute_x);
            String date;
            if(minute_x > 10)
                date = day_x + "/" + month_x + "/" + year_x + " " + hour_x + ":" + minute_x;
            else
                date = day_x + "/" + month_x + "/" + year_x + " " + hour_x + ":"  + "0" + minute_x;


            Intent alertIntent = new Intent(this, AlertReceiver.class);
            alertIntent.putExtra("bookName", bookNameText.getText().toString());
            alertIntent.putExtra("notif_id", date.hashCode());    //notification id

            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

            alarmManager.set(AlarmManager.RTC_WAKEUP, timeMill,
                    PendingIntent.getBroadcast(this, date.hashCode(), alertIntent,
                            PendingIntent.FLAG_UPDATE_CURRENT));


            Reminder rem = new Reminder(date.hashCode(), bookId, bookNameText.getText().toString(), date);
            DataBase db = new DataBase(this);
            db.saveReminder(rem);

            Toast.makeText(this, "New Reminder", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, MyReminders.class);
            startActivity(intent);

        }
        else if(datePicked == false)
            Toast.makeText(this, "Select a date", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Select a Time", Toast.LENGTH_SHORT).show();

//        long x = Calendar.getInstance().getTimeInMillis();
//        Log.i("Time Set", timeMill + "");
//        Log.i("Time Now", x + "");
//        Log.i("TimeSet - TimeNow", (timeMill - x) + "");
    }

    /**
     * Método que retorna ano, mes, dia hora e minuto inserido em milisegundo.
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @return
     */
    private long dato2Mill(int year, int month, int day, int hour, int minute) {
        //Calendar c = Calendar.getInstance();
        cal.set(year, month, day, hour, minute, 0);
        return cal.getTimeInMillis();
    }

}
