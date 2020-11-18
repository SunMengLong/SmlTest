package com.sml.test.app;

import android.app.Application;
import android.view.WindowManager;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        this.registerActivityLifecycleCallbacks(new ActivityManager());
    }

    private WindowManager.LayoutParams wmParams=new WindowManager.LayoutParams();
    public WindowManager.LayoutParams getMywmParams(){
        return wmParams;
    }
}
