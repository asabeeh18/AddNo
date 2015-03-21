package com.tct.sensor;

import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

/**
 * Created by Ahmed on 3/15/2015.
 */
public class BroadReceiver extends WakefulBroadcastReceiver {

    PowerManager.WakeLock wakeLock;
    PowerManager pm;
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("recv",intent.toString());
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF))
        {
            Log.d("recv","OFFFFF");
            wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK
                    | PowerManager.ACQUIRE_CAUSES_WAKEUP
                    | PowerManager.ON_AFTER_RELEASE, "MyWakeLock");
            wakeLock.acquire();
            wakeLock.release();
        }

    }
}