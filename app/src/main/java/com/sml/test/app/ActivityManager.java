package com.sml.test.app;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * activity管理类
 * 可以获取到栈顶activity
 */
public class ActivityManager extends IActivityManager implements Application.ActivityLifecycleCallbacks {

    private static volatile IActivityManager instance = null;

    private static Activity stackTopActivity = null;

    public static IActivityManager getInstance() {
        if (instance == null) {
            synchronized (ActivityManager.class) {
                if (instance == null) {
                    instance = new ActivityManager();
                }
            }
        }
        return instance;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {
        stackTopActivity = activity;
    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }

    @Override
    public Activity getStackTopActivity() {
        return stackTopActivity;
    }
}
