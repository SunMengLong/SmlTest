package com.sml.test;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.sml.test.animator.AlphaBreathActivity;
import com.sml.test.apng.APngActivity;
import com.sml.test.notify.NotifyActivity;
import com.sml.test.popup.AlertWindowActivity;
import com.sml.test.popup.PopupWindowActivity;
import com.sml.test.refrush.ListRefrushActivity;
import com.sml.test.sw.SWActivity;
import com.sml.test.util.UtilActivity;

public class MainActivity extends Activity {

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {

        findViewById(R.id.util_but).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent view = new Intent(MainActivity.this, UtilActivity.class);
                startActivity(view);
            }
        });

        findViewById(R.id.popubwindow_but).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent view = new Intent(MainActivity.this, PopupWindowActivity.class);
                startActivity(view);
            }
        });

        findViewById(R.id.alpha_breath_but).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent view = new Intent(MainActivity.this, AlphaBreathActivity.class);
                startActivity(view);
            }
        });

        findViewById(R.id.refrush_but).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent view = new Intent(MainActivity.this, ListRefrushActivity.class);
                startActivity(view);
            }
        });

        findViewById(R.id.apng_but).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent view = new Intent(MainActivity.this, APngActivity.class);
                startActivity(view);
            }
        });

        findViewById(R.id.sw_but).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent view = new Intent(MainActivity.this, SWActivity.class);
                startActivity(view);
            }
        });

        findViewById(R.id.alert_window_but).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent view = new Intent(MainActivity.this, AlertWindowActivity.class);
                startActivity(view);
            }
        });

        findViewById(R.id.notify_but).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent view = new Intent(MainActivity.this, NotifyActivity.class);
                startActivity(view);
            }
        });
    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        findViewById(R.id.notify_but).postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Log.i("smllll", "run: 通知");
//                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//                Notification notification = new NotificationCompat.Builder(MainActivity.this)
//                        .setContentTitle("这是测试通知标题")  //设置标题
//                        .setContentText("这是测试通知内容") //设置内容
//                        .setWhen(System.currentTimeMillis())  //设置时间
//                        .setSmallIcon(R.mipmap.ic_launcher)  //设置小图标
//                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))   //设置大图标
//                        .setDefaults(Notification.DEFAULT_ALL)
//                        .setPriority(NotificationCompat.PRIORITY_MAX)
//                        .build();
//
//                manager.notify(1, notification);
//            }
//        }, 2000);
//    }
}