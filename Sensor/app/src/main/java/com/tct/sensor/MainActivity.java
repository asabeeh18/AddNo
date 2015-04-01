package com.tct.sensor;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.PowerManager;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;


public class MainActivity extends ActionBarActivity implements SensorEventListener {

    private SensorManager sensorman;
    Sensor sensorPx;
    KeyguardManager.KeyguardLock lock;
    PowerManager.WakeLock wakeLock;
    Switch senseSwitch;
    boolean sensing=true;
    PowerManager pm;
    BroadcastReceiver mReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       /* sensorman=(SensorManager)getSystemService(SENSOR_SERVICE);
        sensorPx=sensorman.getDefaultSensor(Sensor.TYPE_PROXIMITY);
*/
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_USER_PRESENT);
        mReceiver = new BroadReceiver();
        registerReceiver(mReceiver, filter);

        Log.d("life","On Create");
        pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "Wait for Intent");

         wakeLock.acquire();

        //  senseSwitch = (Switch) findViewById(R.id.senseSwitch);

        //set the switch to ON
        //senseSwitch.setChecked(false);
        //attach a listener to check for changes in state
        /*senseSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if (isChecked) {
                    greed();
                } else {
                    wakeLock.release();
                }

            }
        });
        */
        /*PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "My Tag");
        wl.acquire();
        KeyguardManager manager = (KeyguardManager) this.getSystemService(Context.KEYGUARD_SERVICE);
        lock = manager.newKeyguardLock("abc");

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.flags |= WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON;
        params.screenBrightness = -1;
        getWindow().setAttributes(params);
        */
    }

    

    //activate
    private void greed()
    {
        sensorman.registerListener(this, sensorPx, SensorManager.SENSOR_DELAY_NORMAL);
        // wakeLock.acquire();
        //   sensing=true;
    }
    //disable
    private void generous()
    {
        sensorman.unregisterListener(this);
        wakeLock.release();
        sensing=false;
    }
    //Turns the screen ON
    private void giveMeBack()
    {
        wakeLock.release();
        wakeLock=null;
        wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK
                | PowerManager.ACQUIRE_CAUSES_WAKEUP
                | PowerManager.ON_AFTER_RELEASE, "MyWakeLock");
        wakeLock.acquire();
        wakeLock.release();
        wakeLock=null;
        wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "My Tag");
        wakeLock.acquire();
    }
    /* private void act()
     {
         if(sensing)
         {
             if (!wakeLock.isHeld())
                 wakeLock.acquire();
         }
         else
         {
             if (wakeLock.isHeld())
             {
                 sensorman.unregisterListener(this);
                 wakeLock.release();
             }
         }
     }
 */
    @Override
    public final void onSensorChanged(SensorEvent event) {
        float distance = event.values[0];
        Log.d("val1",distance+"");
        if(distance<5.0)
        {
            // giveMeBack();
           /* lock.disableKeyguard();
            WindowManager.LayoutParams params = getWindow().getAttributes();
            params.flags |= WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON;
            params.screenBrightness = 1;
            getWindow().setAttributes(params);
            */
            Log.d("::","ON");
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Log.d("life","INRESUME");
    }
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Log.d("life","INDESTROY");
        // act();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        Log.d("life","INSTOP");
    }

    @Override
    protected void onPause() {
        super.onPause();
        // unregisterReceiver(mReceiver);
        Log.d("life","INPAUSE");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
