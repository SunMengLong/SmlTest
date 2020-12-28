package com.sml.greendao.app;

import android.app.Application;

import com.sml.greendao.manager.DaoManager;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initGreenDao();
    }

    private void initGreenDao() {
        DaoManager mManager = DaoManager.getInstance();
        mManager.init(this);
    }
}
