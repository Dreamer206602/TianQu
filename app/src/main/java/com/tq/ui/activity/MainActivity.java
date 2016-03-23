package com.tq.ui.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.tq.R;
import com.tq.app.AppConfig;
import com.tq.app.AppContext;
import com.tq.app.AppManager;
import com.tq.interf.OnTabReselectListener;
import com.tq.ui.MainTab;
import com.tq.ui.widget.DoubleClickExitHelper;
import com.tq.ui.widget.MyFragmentTabHost;
import com.tq.ui.widget.dialog.QuickOptionDialog;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements View.OnClickListener, TabHost.OnTabChangeListener, View.OnTouchListener {


    @Bind(R.id.quick_option_iv)
    ImageView mAddBt;
    @Bind(R.id.tabhost)
    MyFragmentTabHost mTabHost;

    private CharSequence mTitle;

    private DoubleClickExitHelper mDoubleClickExit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        AppManager.getAppManager().addActivity(this);
    }

    private void initView() {
        mTitle=getTitle();
        mDoubleClickExit=new DoubleClickExitHelper(this);
        mTabHost.setup(this,getSupportFragmentManager(),R.id.realtabcontent);
        if(Build.VERSION.SDK_INT>10){
            mTabHost.getTabWidget().setShowDividers(0);
        }
        //初始化下面的四个tabs
        initTabs();
        //中间按键图片的触发
        mAddBt.setOnClickListener(this);
        mTabHost.setCurrentTab(0);//默认显示第一个tab的界面
        mTabHost.setOnTabChangedListener(this);
    }

    /**
     * 初始化四个Tabs
     */
    private void initTabs() {
        MainTab[] tabs=MainTab.values();
        final int size=tabs.length;
        for (int i = 0; i <size ; i++) {
            MainTab mainTab=tabs[i];
            TabHost.TabSpec tab= mTabHost.newTabSpec(getString(mainTab.getResName()));
            View indicator=LayoutInflater.from(getApplicationContext()).inflate(R.layout.tab_indicator,null);
            TextView title= (TextView) indicator.findViewById(R.id.tab_title);
            Drawable drawable=this.getResources().getDrawable(mainTab.getResIcon());
            title.setCompoundDrawablesWithIntrinsicBounds(null,drawable,null,null);
            if(i==2){
                indicator.setVisibility(View.INVISIBLE);
                mTabHost.setNoTabChangedTag(getString(mainTab.getResName()));
            }
            title.setText(getString(mainTab.getResName()));
            tab.setIndicator(indicator);
            tab.setContent(new TabHost.TabContentFactory() {
                @Override
                public View createTabContent(String tag) {
                    return new View(MainActivity.this);
                }
            });
            mTabHost.addTab(tab,mainTab.getClz(),null);
//            if(mainTab.equals(MainTab.ME)){
//
//            }
            mTabHost.getTabWidget().getChildAt(i).setOnTouchListener(this);


        }

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //点击了快速操作的按钮
            case R.id.quick_option_iv:
                showQuickOption();
                break;

        }
    }

    //显示快速操作的按钮
    private void showQuickOption() {
        QuickOptionDialog dialog=new QuickOptionDialog(this);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();


    }

    @Override
    public void onTabChanged(String tabId) {
        final int size=mTabHost.getTabWidget().getTabCount();
        for (int i = 0; i < size; i++) {
            View v=mTabHost.getTabWidget().getChildAt(i);
            if(i==mTabHost.getCurrentTab()){
                v.setSelected(true);
            }else{
                v.setSelected(false);
            }
        }

        //对我这部分内容的处理
//        if(tabId.equals(getString(MainTab.ME.getResName()))){
//
//        }
        supportInvalidateOptionsMenu();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        super.onTouchEvent(event);
        boolean consumed=false;
        if(event.getAction()==MotionEvent.ACTION_DOWN&&v.equals(mTabHost.getCurrentTabView())){
            Fragment currentFragment=getCurrentFragment();
            if(currentFragment!=null&&currentFragment instanceof OnTabReselectListener)
            {
                OnTabReselectListener listener=(OnTabReselectListener)currentFragment;
                listener.onTabReselect();
                consumed=true;
            }
        }
        return consumed;
    }

    private Fragment getCurrentFragment() {

        return getSupportFragmentManager().findFragmentByTag(mTabHost.getCurrentTabTag());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            //是否退出应用
            if(AppContext.get(AppConfig.KEY_DOUBLE_CLICK_EXIT,true)){
                return mDoubleClickExit.onKeyDown(keyCode,event);
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
