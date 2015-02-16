package com.tct.scrolllist;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("State","Init");
        //LIster chapterListAdapter = new LIster(this);
        String[] aa=new String[]{"http://opnz.freeiz.com/","http://icons.iconarchive.com/icons/graphicloads/100-flat-2/48/bank-icon.png","http://opnz.freeiz.com/un.php"};
        ListView mainList = (ListView)findViewById(R.id.listView1);
       // ImageView img=(ImageView)findViewById(R.id.img);
        new Connect(mainList,this).execute(aa);
        Log.d("State","EXIT");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }



}