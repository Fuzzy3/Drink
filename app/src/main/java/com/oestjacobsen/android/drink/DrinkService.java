package com.oestjacobsen.android.drink;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.widget.Toast;


public class DrinkService extends IntentService {
    private static final String TAG = "DrinkService";
    private static final int DRINKINTERVAL = 60 * 60 * 1000; //1 hour

    public DrinkService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG, "Received Intent");

        Resources resources = getResources();
        Notification notification = new NotificationCompat.Builder(this)
                .setTicker("DRINK MORE")
                .setSmallIcon(android.R.drawable.ic_media_play)
                .setContentTitle("DRINK!")
                .setContentText(resources.getString(R.string.notification))
                .setAutoCancel(true)
                .setVibrate(new long[] {1000})
                .setAutoCancel(true)
                .build();



        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);
        notificationManager.notify(0, notification);
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, DrinkService.class);
    }

    public static void setServiceAlarm(Context context, boolean isOn) {
        Intent i = DrinkService.newIntent(context);
        PendingIntent pi = PendingIntent.getService(context, 0,i,0);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);

        if(isOn) {
            alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime(), DRINKINTERVAL, pi);
        } else {
            alarmManager.cancel(pi);
            pi.cancel();
        }
    }
}
