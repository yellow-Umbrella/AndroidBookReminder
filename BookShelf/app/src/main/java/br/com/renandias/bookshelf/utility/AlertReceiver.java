package br.com.renandias.bookshelf.utility;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import br.com.renandias.bookshelf.R;
import br.com.renandias.bookshelf.activities.MainActivity;

/**
 * Created by Renan on 11/12/2016.
 */

/**
 * Classe que recebe os alarmes seta os mesmos.
 */
public class AlertReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        String bookName = bundle.getString("bookName");
        Integer id = bundle.getInt("notif_id");
        createNotification(context, "BookShelf Alarm", "Read " + bookName, "BookShelf Alarm", id);
    }

    /**
     * Cria a notificação e seta no sistema.
     * @param context
     * @param msg
     * @param msgText
     * @param msgAlert
     */
    public void createNotification(Context context, String msg, String msgText, String msgAlert, Integer id) {
        PendingIntent notificIntent = PendingIntent.getActivities(context, 0,
                new Intent[]{new Intent(context, MainActivity.class)}, 0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle(msg)
                .setTicker(msgAlert)
                .setContentText(msgText);

        mBuilder.setContentIntent(notificIntent);
        mBuilder.setDefaults(NotificationCompat.DEFAULT_SOUND);
        mBuilder.setAutoCancel(true);

        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(id, mBuilder.build());
    }

}
