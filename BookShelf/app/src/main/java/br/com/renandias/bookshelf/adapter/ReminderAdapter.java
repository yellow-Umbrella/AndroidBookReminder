package br.com.renandias.bookshelf.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.renandias.bookshelf.DataBase.DataBase;
import br.com.renandias.bookshelf.R;
import br.com.renandias.bookshelf.models.Reminder;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Renan on 13/12/2016.
 */

public class ReminderAdapter extends ArrayAdapter<Reminder> {

    public ReminderAdapter(Context context, List<Reminder> reminderList) {
        super(context, R.layout.reminder_adapter, reminderList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null) {
            convertView = View.inflate(getContext(), R.layout.reminder_adapter, null);

            holder = new ViewHolder();

            ButterKnife.bind(holder, convertView);

            convertView.setTag(holder);
        }
        else
            holder = (ViewHolder)convertView.getTag();

        Reminder reminder = getItem(position);
        if(reminder != null) {
            holder.name.setText(reminder.getBookName());
            holder.date.setText(reminder.getDate());
        }

        return convertView;
    }

    class ViewHolder {

        @Bind(R.id.reminders_book_name)
        TextView name;
        @Bind(R.id.date_reminder_text)
        TextView date;

    }

}
