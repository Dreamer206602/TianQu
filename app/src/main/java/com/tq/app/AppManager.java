package com.tq.app;

/**
 * Created by boobooMX on 2016/3/22 0022.16.36
 * Created 邮箱 ：boobooMX@163.com
 */

import android.app.Activity;
import android.content.Context;

import java.util.Calendar;
import java.util.Stack;

/**
 * activity堆栈式的管理
 *
 */
public class AppManager {
    private static Stack<Activity> activityStack;
    private static AppManager instance;

    /**
     * 单一的实例
     * @return
     */
    public static AppManager getAppManager(){
        if(instance==null){
            synchronized (AppManager.class){
                if(instance==null){
                    instance=new AppManager();
                }
            }
        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     * @param activity
     */
    public void addActivity(Activity activity){
        if(activityStack==null){
            activityStack=new Stack<>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前的activity（堆栈中最后一个压入的）
     * @return
     */
    public Activity currentActivity(){
        Activity activity=activityStack.lastElement();
        return activity;
    }

    /**
     * 结束指定的activity
     * @param activity
     */
    public void finishActivity(Activity activity){
        if(activity!=null&& !activity.isFinishing()){
            activityStack.remove(activity);
            activity.finish();
            activity=null;
        }
    }

    /**
     * 结束指定类名的Activity
     * @param cls
     */
    public void finishActivity(Class<?>cls){
        for(Activity activity:activityStack){
            if(activity.getClass().equals(cls)){
                finishActivity(activity);
                break;
            }
        }
    }

    /**
     * 结束所有的Activity
     */
    public void finishAllActivity(){
        for (int i = 0,size=activityStack.size(); i < size; i++) {
            if(activityStack.get(i)!=null){
                //finishActivity方法中的activity.isFinishing()方法会导致某些activity无法销毁
                //貌似跳转的时候最后一个activity是finishing状态，所以没有
                //执行，
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 获取指定的Activity
     * @param cls
     * @return
     */
    public static Activity getActivity(Class<?>cls){
        if(activityStack!=null)
            for (Activity activity:activityStack){
                if(activity.getClass().equals(cls)){
                    return activity;
                }
            }
            return null;
    }

    /**
     * 退出应用的程序
     * @param context
     */
    public void AppExit(Context context){
        try {
            finishAllActivity();
            //杀死应用的进程
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        }catch (Exception e){
            e.printStackTrace();
        }
    }






}

