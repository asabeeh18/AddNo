package com.tct.message;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Ahmed on 2/8/2015.
 */
public class SendActivity extends AsyncTask<String,Void,Bitmap> //Args of form ...Inbackgroud,publishprogress,PostExec
{
    private TextView tv;
    private ImageView bmImage;
    public SendActivity(TextView tv,ImageView bmImage)
    {
        this.tv=tv;
        this.bmImage=bmImage;
    }
    protected void onPreExecute(){

        this.tv.setText("**Initiated**");
    }
    protected Bitmap doInBackground(String... arg0)
    {
        String urldisplay = arg0[0];
        Bitmap mIcon11 = null;
        try {
            URL newurl = new URL(urldisplay);
            mIcon11 = BitmapFactory.decodeStream(newurl.openConnection() .getInputStream());
            Log.d("done","Did");
            //InputStream in = new java.net.URL(urldisplay).openStream();
            //mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.d("done","Diqqd");
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        Log.d("done","Dizzd");
        return mIcon11;
        /*
        String ip = (String) arg0[0];
        ip = "http://" + ip;    //http necessary
        URL url = new URL(ip);
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        wr.write(ip);
        wr.flush(); //flush post requests
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line = null;
        // Read Server Response
        while ((line = reader.readLine()) != null) {
            sb.append(line);
            break;
        }

        return sb.toString();   //return echoed back string
        */
    }
    protected void onPostExecute(Bitmap res)
    {
        bmImage.setImageBitmap(res);
        this.tv.setText("DONE!");
    }
}
