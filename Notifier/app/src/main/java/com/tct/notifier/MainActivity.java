package com.tct.notifier;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private GCMClientManager pushClientManager;
    String PROJECT_NUMBER = "151235883577";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pushClientManager = new GCMClientManager(this, PROJECT_NUMBER);
        pushClientManager.registerIfNeeded(new GCMClientManager.RegistrationCompletedHandler() {
            @Override
            public void onSuccess(String registrationId, boolean isNewRegistration) {
                Toast.makeText(MainActivity.this, registrationId,
                        Toast.LENGTH_SHORT).show();
                // SEND async device registration to your back-end server
                // linking user with device registration id
                // POST https://my-back-end.com/devices/register?user_id=123&device_id=abc
            }

            @Override
            public void onFailure(String ex) {
                super.onFailure(ex);
                // If there is an error registering, don't just keep trying to register.
                // Require the user to click a button again, or perform
                // exponential back-off when retrying.
            }
        });
    }
}
