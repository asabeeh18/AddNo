package com.tct.notifierinterface;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Ahmed on 3/14/2015.
 */
public class Sender extends AsyncTask<String,Void,String>//   <DoinBAckground,onprogressupdate,postexecute>
{
    Context con;
    public Sender(Context con)
    {
        this.con=con;
    }

    @Override
    protected void onPostExecute(String s)
    {
        Log.d("OK",s);
        Toast.makeText(con,"Buzzed::"+s, Toast.LENGTH_LONG).show();
    }

    @Override
    protected String doInBackground(String... params)
    {
        String txt=params[0];
        try
        {
            HttpClient client = new DefaultHttpClient();
            HttpGet get= new HttpGet("http://opnz.freeiz.com/send_message.php?message="+txt);
            HttpResponse response = client.execute(get);
            Log.d("OK", response.toString());
            HttpEntity entity =response.getEntity();
            InputStream ins=entity.getContent();
            return convertStreamToString(ins);
        }
        catch(Exception e)
        {
            Log.d("E",e.toString());
        }
        return "";
    }

    public static String convertStreamToString(InputStream is)
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try
        {
            while ((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                is.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
