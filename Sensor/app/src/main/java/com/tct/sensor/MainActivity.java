package com.tct.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.PowerManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;


public class MainActivity extends ActionBarActivity implements SensorEventListener {

        private SensorManager sensorman;
        Sensor sensorPx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorman=(SensorManager)getSystemService(SENSOR_SERVICE);
        sensorPx=sensorman.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        float d=sensorPx.getMaximumRange();
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "My Tag");
        wl.acquire();
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.flags |= WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON;
        params.screenBrightness = -1;
        getWindow().setAttributes(params);
    }
    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
     //  Log.d("acc",accuracy+"");
    }
    @Override
    public final void onSensorChanged(SensorEvent event) {
        float distance = event.values[0];
        Log.d("val1",distance+"");
        if(distance<5.0)
        {
            WindowManager.LayoutParams params = getWindow().getAttributes();
            params.flags |= WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON;
            params.screenBrightness = -1;
            getWindow().setAttributes(params);
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
            Log.d("::","ON");
            //params.flags |= LayoutParams.FLAG_KEEP_SCREEN_ON;
            //params.screenBrightness = 0;
            //getWindow().setAttributes(params);

        }
       // Log.d("val2",event.accuracy+"");
        // Do something with this sensor data.
    }

    @Override
    protected void onResume() {
        // Register a listener for the sensor.
        super.onResume();
        sensorman.registerListener(this, sensorPx, SensorManager.SENSOR_DELAY_NORMAL);
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
