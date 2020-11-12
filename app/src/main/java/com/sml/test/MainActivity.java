package com.sml.test;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sml.test.animator.AlphaBreathActivity;
import com.sml.test.apng.APngActivity;
import com.sml.test.popup.PopupWindowActivity;
import com.sml.test.refrush.ListRefrushActivity;
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
    }


}