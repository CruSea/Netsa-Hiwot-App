package com.netsahiwot.netsa_hiwot;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * Created by Amanue l on 25/8/2016.
 */
public class CustomNotification extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {



        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent repeting_intent = new Intent(context, ActivityTempted.class);
        repeting_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context ,100,repeting_intent,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
             .setContentIntent(pendingIntent)
             .setSmallIcon(android.R.drawable.arrow_up_float)
             .setContentTitle("Notification Title")
                .setContentText("It is Netsahiwot App")
                .setAutoCancel(true);
        notificationManager.notify(100,builder.build());
    }

}
