package com.tct.scrolllist;

import android.content.Context;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Created by Ahmed on 2/17/2015.
 */
public class EndlessScrollListener implements AbsListView.OnScrollListener {

    Context act;
    ListView mainList;
    private int visibleThreshold = 5;
    private int currentPage = 0;
    private int previousTotal = 0;
    private boolean loading = true;
    String[] aa=new String[]{"http://opnz.freeiz.com/","http://www.less-real.com/imagevault/uploaded/quotefaces/Saito-18056.jpg","http://opnz.freeiz.com/un.php"};

    public EndlessScrollListener(ListView mainList,Context act) {
        this.act=act;
        this.mainList=mainList;
    }
    public EndlessScrollListener(int visibleThreshold) {
        this.visibleThreshold = visibleThreshold;
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {
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
            new Connect(mainList,act).execute(aa);
            loading = true;
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }
}