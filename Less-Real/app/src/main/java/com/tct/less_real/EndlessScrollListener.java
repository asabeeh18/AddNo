package com.tct.less_real;

import android.app.Activity;
import android.app.Notification;
import android.content.Context;
import android.app.ActionBar;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Created by Ahmed on 2/18/2015.
 */
public class EndlessScrollListener implements AbsListView.OnScrollListener {
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    Context act;
    ListView mainList;
    private int visibleThreshold = 5;
    private int currentPage = 0;
    private int previousTotal = 0;
    private boolean loading = true;
  static int mLastFirstVisibleItem = 0;
    String[] aa=new String[]{"http://opnz.freeiz.com/","http://www.less-real.com/imagevault/uploaded/quotefaces/Saito-18056.jpg","http://opnz.freeiz.com/un.php"};
    ActionBar bar;
    public EndlessScrollListener(ListView mainList,Context act,ActionBar bar) {
        this.bar=bar;
        this.act=act;
        this.mainList=mainList;
    }
    public EndlessScrollListener(int visibleThreshold) {
        this.visibleThreshold = visibleThreshold;
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {

        Log.d("scroll", "scrollin");
        if (view.getId() == mainList.getId()) {

        }
            final int currentFirstVisibleItem = mainList.getFirstVisiblePosition();

            if (currentFirstVisibleItem > mLastFirstVisibleItem) {
                Log.d("scroll","-------HIDDEN");
                // getSherlockActivity().getSupportActionBar().hide();
                if(bar!=null)
                {
                    bar.hide();
                    Log.d("scroll","-------HIDDEN-");
                }
            } else if (currentFirstVisibleItem < mLastFirstVisibleItem) {
                Log.d("scroll", "SHOWN----------");
                if(bar!=null)
                {
                    // getSherlockActivity().getSupportActionBar().show();
                    bar.show();
                    Log.d("scroll", "-SHOWN----------");
                }
            }

            mLastFirstVisibleItem = currentFirstVisibleItem;
        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
                currentPage++;
                Log.d("State", "Still with OLDy");
            }
        }
        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
            // I load the next page of gigs using a background task,
            // but you can call any function here.
            Log.d("State", "<<MORE DATA>>");
            new Connect(mainList,act,bar).execute(aa);
            loading = true;
        }
    }
}