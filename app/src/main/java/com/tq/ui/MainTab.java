package com.tq.ui;

import com.tq.R;
import com.tq.ui.fragment.ExploreFragment;
import com.tq.ui.fragment.MyInformationFragment;
import com.tq.ui.fragment.NewsViewPagerFragment;
import com.tq.ui.fragment.TweetsViewPagerFragment;

/**
 * Created by boobooMX on 2016/3/23 0023.09.17
 * Created 邮箱 ：boobooMX@163.com
 */
public enum MainTab {
    NEWS(0, R.string.main_tab_name_news,R.drawable.tab_icon_new, NewsViewPagerFragment.class),
    TWEET(1,R.string.main_tab_name_tweet, R.drawable.tab_icon_tweet,TweetsViewPagerFragment.class),
    QUICK(2,R.string.main_tab_name_quick,R.drawable.tab_icon_new,null),
    EXPLORE(3,R.string.main_tab_name_explore,R.drawable.tab_icon_explore, ExploreFragment.class),
    ME(4,R.string.main_tab_name_my,R.drawable.tab_icon_me, MyInformationFragment.class);

    private int idx;
    private int resName;
    private int resIcon;
    private Class<?>clz;

    MainTab(int idx, int resName, int resIcon, Class<?> clz) {
        this.idx = idx;
        this.resName = resName;
        this.resIcon = resIcon;
        this.clz = clz;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public int getResName() {
        return resName;
    }

    public void setResName(int resName) {
        this.resName = resName;
    }

    public int getResIcon() {
        return resIcon;
    }

    public void setResIcon(int resIcon) {
        this.resIcon = resIcon;
    }

    public Class<?> getClz() {
        return clz;
    }

    public void setClz(Class<?> clz) {
        this.clz = clz;
    }
}
