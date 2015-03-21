package com.tct.textflow;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import uk.co.deanwild.flowtextview.FlowTextView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FlowTextView tv = (FlowTextView) findViewById(R.id.ftv);
        tv.setText("I needed to have a text wrap around images and other views in an Android App but I was surprised and frustrated to discover that there was no way to do this natively.  The general consensus was that the only practical way to achieve this was to use WebViews.  Using webviews is a bit bloaty and overkill to achieve this. We lose the direct control and performance of using native widgets.  So I developed a native widget which extends RelativeLayout (I figured this was more versatile than LinearLayout) which is capable of painting text around its child views.  The code is still a little rough and I suspect it could be vastly improved in many areas but it seems to work quite well.  TODO . I needed to have a text wrap around images and other views in an Android App but I was surprised and frustrated to discover that there was no way to do this natively.  The general consensus was that the only practical way to achieve this was to use WebViews.  Using webviews is a bit bloaty and overkill to achieve this. We lose the direct control and performance of using native widgets.  So I developed a native widget which extends RelativeLayout (I figured this was more versatile than LinearLayout) which is capable of painting text around its child views.  The code is still a little rough and I suspect it could be vastly improved in many areas but it seems to work quite well.  TODO"); // using plain text
        tv.invalidate(); // call this to render the text
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
