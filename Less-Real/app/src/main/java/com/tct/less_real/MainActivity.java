package com.tct.less_real;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {
    SharedPreferences pref;
    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pref = getSharedPreferences("cookie", Context.MODE_PRIVATE);
        if(pref==null)
            Log.d("State","NULLA"+pref.toString());
        if (pref.contains("user"))
        {
            user=pref.getString("user","");
            Log.d("State", "Found: " + user);
        }
        else
            Log.d("State","USer NOTFOUND: "+user);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        if(user==null)
        {
            MenuItem mn= menu.findItem(R.id.login);
                    mn.setTitle("Login");
        }
        else
        {
            MenuItem mn= menu.findItem(R.id.login);
            mn.setTitle(user);
        }
        Log.d("State","changed:");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.login) {
            if(user==null)
                loginSeq();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void loginSeq()
    {
        int code=2;
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        if(code==1)
            findViewById(R.id.login).setVisibility(View.GONE);
    }
}
