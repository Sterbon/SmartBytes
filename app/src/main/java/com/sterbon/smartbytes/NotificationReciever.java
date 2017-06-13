package com.sterbon.smartbytes;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

/**
 * Created by Utsav on 6/7/2017.
 */

class Notification extends BroadcastReceiver {
    MainActivity mainActivity;
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onReceive(Context context, Intent intent) {
        Calendar time=Calendar.getInstance();
        int now=time.get(Calendar.DATE);

        NotificationCompat.Builder mbuilder = new NotificationCompat.Builder(context)
                                                    .setSmallIcon(R.drawable.logo1)
                                                    .setContentTitle("Todays Allowed Consumption")
                                                    .setContentText(String.valueOf(mainActivity.musage));
        Intent resultIntent = new Intent(context, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        mbuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, mbuilder.build());



        }
    }

