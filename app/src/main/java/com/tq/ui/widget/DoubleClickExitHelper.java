package com.tq.ui.widget;

/**
 * Created by boobooMX on 2016/3/22 0022.16.21
 * Created 邮箱 ：boobooMX@163.com
 */

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.widget.Toast;

import com.tq.R;
import com.tq.app.AppManager;

/**
 * 双击退出
 */
public class DoubleClickExitHelper {
    private final Activity mActivity;
    private boolean isOnKeyBacking;
    private Handler mHandler;
    private Toast mBackToast;

    public DoubleClickExitHelper(Activity mActivity) {
        this.mActivity = mActivity;
        mHandler=new Handler(Looper.myLooper());

    }
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode!=KeyEvent.KEYCODE_BACK){
            return false;
        }
        if(isOnKeyBacking){
            mHandler.removeCallbacks(onBackTimeRunnable);
            if(mBackToast!=null){
                mBackToast.cancel();
            }
            //退出
            AppManager.getAppManager().AppExit(mActivity);
            return true;
        }else{
            isOnKeyBacking=true;
            if(mBackToast==null){
                mBackToast=Toast.makeText(mActivity, R.string.tip_double_click_exit,Toast.LENGTH_LONG);
            }
            mBackToast.show();
            mHandler.postDelayed(onBackTimeRunnable,2000);
            return true;
        }
    }

    private Runnable onBackTimeRunnable=new Runnable() {
        @Override
        public void run() {
            isOnKeyBacking=false;
            if(mBackToast!=null){
                mBackToast.cancel();
            }
        }
    };


}
