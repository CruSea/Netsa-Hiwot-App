package com.netsahiwot.netsa_hiwot;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Sammie on 19/08/2015.
 */

public class NotifyService extends Service {

    public class ServiceBinder extends Binder {
        NotifyService getService() {
            return NotifyService.this;
        }
    }

    private static final int NOTIFICATION = 1243;
    private TemptedDatabaseHelper tdh;
    private Random rand;
    private static ArrayList<ArrayList<String>> current;
    SharedPreferences preferences;
    public static final String INTENT_NOTIFY = "com.netsahiwot.netsa_hiwot.INTENT_TEMPTED";

    @Override
    public void onCreate() {
        Log.i("NotifyService", "onCreate()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);

        if (intent.getBooleanExtra(INTENT_NOTIFY, false))
            showNotification();

        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private final IBinder mBinder = new ServiceBinder();

    @SuppressWarnings("deprecation")
    private void showNotification() {

        tdh = new TemptedDatabaseHelper(this);
        rand = new Random();
        // Generates the random number between 0 and total value of the elements
        int r = (int) (rand.nextFloat() * tdh.numberOfRows());
        preferences = PreferenceManager
                .getDefaultSharedPreferences(this);

        /*SharedPreferences.Editor editor = preferences.edit();
                editor.commit();*/

        Boolean prefNotif = preferences.getBoolean("TemptedNotif", false);

        if (prefNotif.equals(true)) {
            CharSequence title = getResources().getString(
                    R.string.app_name);
            current = tdh.getAllVerses();
            CharSequence text = current.get(r).get(0);
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.freedom)
                    .setContentTitle(title)
                    .setContentText(text)
                    .setAutoCancel(true);
            Intent intent1 = new Intent(this.getApplicationContext(),
                    ActivityTempted.class);

            intent1.putExtra("id", r);
            intent1.putExtra("mode", "NotifTempted");
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
            stackBuilder.addParentStack(ActivityTempted.class);
            stackBuilder.addNextIntent(intent1);
            PendingIntent pendingIntent = stackBuilder.getPendingIntent(0,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            mBuilder.setContentIntent(pendingIntent);
            NotificationManager mNotificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(NOTIFICATION, mBuilder.build());

        }

        stopSelf();
    }
}