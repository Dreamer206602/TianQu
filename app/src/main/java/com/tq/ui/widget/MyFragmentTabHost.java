package com.tq.ui.widget;

import android.content.Context;
import android.support.v4.app.FragmentTabHost;
import android.util.AttributeSet;
import android.widget.TabHost;

/**
 * Created by Administrator on 2016/3/22 0022.
 */
public class MyFragmentTabHost extends FragmentTabHost{

    private String mCurrentTag;
    private String mNoTabChangedTag;

    public MyFragmentTabHost(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onTabChanged(String tabId) {
        if(tabId.equals(mNoTabChangedTag)){
            setCurrentTabByTag(mCurrentTag);
        }else{
            super.onTabChanged(tabId);
            mCurrentTag=tabId;
        }
    }

    public void setNoTabChangedTag(String tag) {
        this.mNoTabChangedTag = tag;
    }
}
