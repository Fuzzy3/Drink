package com.oestjacobsen.android.drink;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;


public class DrinkService extends IntentService {
    private static final String TAG = "DrinkService";
    private static final int DRINKINTERVAL = 60 * 1000; //60 seconds

    public DrinkService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

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
