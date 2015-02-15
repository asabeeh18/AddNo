package com.tct.scrolllist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ahmed on 2/15/2015.
 */
public class LIster extends BaseAdapter {
    Context context;
    public LIster(Context context)
    {
        this.context=context;
    }
    List<codeLearnChapter> codeLearnChapterList = getDataForListView();
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return codeLearnChapterList.size();
    }

    @Override
    public codeLearnChapter getItem(int arg0) {
        // TODO Auto-generated method stub
        return codeLearnChapterList.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }

    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2) {

        if(arg1==null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            arg1 = inflater.inflate(R.layout.listitem, arg2,false);
        }

        TextView chapterName = (TextView)arg1.findViewById(R.id.textView1);
        TextView chapterDesc = (TextView)arg1.findViewById(R.id.textView2);

        codeLearnChapter chapter = codeLearnChapterList.get(arg0);

        chapterName.setText(chapter.chapterName);
        chapterDesc.setText(chapter.chapterDescription);

        return arg1;
    }
    public List<codeLearnChapter> getDataForListView()
    {
        List<codeLearnChapter> codeLearnChaptersList = new ArrayList<codeLearnChapter>();

        for(int i=0;i<10;i++)
        {

            codeLearnChapter chapter = new codeLearnChapter();
            chapter.chapterName = "Chapter "+i;
            chapter.chapterDescription = "This is description for chapter "+i;
            codeLearnChaptersList.add(chapter);
        }

        return codeLearnChaptersList;

    }
}
