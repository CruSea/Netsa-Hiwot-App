package com.netsahiwot.netsa_hiwot;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by Sammie on 19/08/2015.
 */
public class  AlarmTask implements Runnable{

    private final Calendar date;
    private final AlarmManager am;
    private final Context context;

    public AlarmTask(Context context, Calendar date) {
        Log.d("Hi sammie!!!", "AlarmTask object has been created...");
        this.context = context;
        this.am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        this.date = date;
    }

    @Override
    public void run() {

        Log.d("Hi sammie!!!", "AlarmTask is running.....");
        Intent intent = new Intent(context, NotifyService.class);
        intent.putExtra(NotifyService.INTENT_NOTIFY, true);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, 0);

        am.setRepeating(AlarmManager.RTC_WAKEUP, date.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        Log.d("Hi sammie!!!", "AlarmTask...end of the run method");

    }
}
