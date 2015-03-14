package com.tct.notifierinterface;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ahmed on 3/14/2015.
 */
public class Sender extends AsyncTask<String,Void,String>//   <DoinBAckground,onprogressupdate,postexecute>
{
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    @Override
    protected String doInBackground(String... params) {
        String txt=params[0];
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost("http://opnz.freeiz.com/register.php");
            List<NameValuePair> pairs = new ArrayList<>();
            pairs.add(new BasicNameValuePair("regId", msg));
            pairs.add(new BasicNameValuePair("name", name));
            post.setEntity(new UrlEncodedFormEntity(pairs));
            HttpResponse response = client.execute(post);
            Log.d("OK", response.toString());
        }
        catch(Exception e)
        {
            Log.d("E",e.toString());
        }
    }
}
