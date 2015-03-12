package com.tct.less_real;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ahmed on 2/18/2015.
 */
public class Connect extends AsyncTask<String,Void,Quote>
{
    static List<Quote> objList=new ArrayList<>();
    static ListView mainList;
    static LIster customAdapter;
    static boolean set=false;
    Context act;
    ActionBar bar;
    public Connect(ListView mainList,Context act,ActionBar bar)
    {
        this.act=act;
        this.bar=bar;
        //this.txt=txt;
        //this.img=img;
        //this.says=says;
        this.mainList=mainList;
    }
    @Override
    protected Quote doInBackground(String... arg) {
        String txLink = arg[0];
        String imgLInk = arg[1];
        String says = arg[2];
        Quote send = new Quote();
        Log.d("State", "Calling Http0");
        send.text = getText(txLink);
        Log.d("State", "Calling Http1");
        send.says = getText(says);
        Log.d("State", "Calling Http2");
        try {
            URL newurl = new URL(imgLInk);
            send.img = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
            return send;
        } catch (Exception e) {
            Log.d("State", "Error!!!!..: "+e.toString());
            return send;
        }
    }

    protected String getText(String link) {
        try {
            URL url = new URL(link);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter
                    (conn.getOutputStream());
            wr.write(" ");
            wr.flush();
            BufferedReader reader = new BufferedReader
                    (new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line=null;
            // Read Server Response
            while((line = reader.readLine()) != null) {
                sb.append(line);
                break;
            }
            return sb.toString();
        } catch (Exception e) {
            return e.toString();
        }
    }
    protected void onPostExecute(Quote res) {
        Log.d("State", "Downloaded");
        for(int i=0;i<9;i++)
            objList.add(res);
        if(customAdapter==null) {

            Log.d("State", "Added in List");
            customAdapter = new LIster(objList, act);
            Log.d("State", "Constructor Lister");
            mainList.setAdapter(customAdapter);
            Log.d("State", "SET listner");
            mainList.setOnScrollListener(new EndlessScrollListener(mainList,act,bar));
        }
        else
        {

            Log.d("State", "Shiiiiiiit--------------");
            customAdapter.notifyDataSetChanged();

            int firstVisibleItem = mainList.getFirstVisiblePosition();
            int oldCount = customAdapter.getCount();
            View view = mainList.getChildAt(0);
            int pos = (mainList == null ? 0 :  mainList.getTop());
            customAdapter.notifyDataSetChanged();
            mainList.setSelectionFromTop(firstVisibleItem, 0);

        }
        //  img.setImageBitmap(res.img);
        Log.d("State", "ALL Done !! STOp");
    }
}