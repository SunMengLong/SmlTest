package com.sml.test.popup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.sml.test.MainActivity;
import com.sml.test.R;
import com.sml.test.app.MyApplication;
import com.sml.test.notify.NotifyActivity;

public class AlertWindowActivity extends AppCompatActivity {

    private WindowManager wm;
    private WindowManager.LayoutParams wmParams;
    private CustomeMovebutton CustomeMovebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_window);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SYSTEM_ALERT_WINDOW}, 1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (CustomeMovebutton != null) {
            wm.removeView(CustomeMovebutton);
        }
    }

    public void alert_window_btn(View view) {
        showDialog();
    }

    public void showDialog() {
        if (Build.VERSION.SDK_INT >= 23 && !Settings.canDrawOverlays(AlertWindowActivity.this)) {
            Toast.makeText(AlertWindowActivity.this, "请打开投资界允许权限开关", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            return;
        }

        wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int widthPixels = dm.widthPixels;
        int heightPixels = dm.heightPixels;
        wmParams = ((MyApplication) getApplication()).getMywmParams();
        wmParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            wmParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        wmParams.format = PixelFormat.RGBA_8888;//设置背景图片
        wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;//
        wmParams.gravity = Gravity.LEFT | Gravity.TOP;//
        wmParams.x = widthPixels - 150;   //设置位置像素
        wmParams.y = heightPixels - 110;
        wmParams.width = 200; //设置图片大小
        wmParams.height = 200;
        CustomeMovebutton = new CustomeMovebutton(getApplicationContext());
        CustomeMovebutton.setImageResource(R.drawable.btn_endcall);
        wm.addView(CustomeMovebutton, wmParams);
        CustomeMovebutton.setOnSpeakListener(new CustomeMovebutton.OnSpeakListener() {
            @Override
            public void onSpeakListener() {
                Toast.makeText(AlertWindowActivity.this, "点击事件", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), NotifyActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
            }
        });
    }
}