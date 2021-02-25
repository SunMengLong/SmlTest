package com.sml.test;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.sml.test.a_z.AZListActivity;
import com.sml.test.animator.AlphaBreathActivity;
import com.sml.test.apng.APngActivity;
import com.sml.test.bottomsheetbehavior.BottomSheetBehaviorActivity;
import com.sml.test.edit.EditTextActivity;
import com.sml.test.expandable.ExpandableActivity;
import com.sml.test.notify.NotifyActivity;
import com.sml.test.popup.AlertWindowActivity;
import com.sml.test.popup.DialogActivity;
import com.sml.test.popup.PopupWindowActivity;
import com.sml.test.refrush.ListRefrushActivity;
import com.sml.test.sw.SWActivity;
import com.sml.test.util.UtilActivity;

import java.util.IdentityHashMap;

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

        findViewById(R.id.dialog_but).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent view = new Intent(MainActivity.this, DialogActivity.class);
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

        findViewById(R.id.notify_but).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent view = new Intent(MainActivity.this, NotifyActivity.class);
                startActivity(view);
            }
        });

        findViewById(R.id.bottom_sheet_behavior_state_but).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent view = new Intent(MainActivity.this, BottomSheetBehaviorActivity.class);
                startActivity(view);
            }
        });

        findViewById(R.id.at_friend_but).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent view = new Intent(MainActivity.this, EditTextActivity.class);
                startActivity(view);
            }
        });

        findViewById(R.id.a_z_list_but).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent view = new Intent(MainActivity.this, AZListActivity.class);
                startActivity(view);
            }
        });

        findViewById(R.id.expanable_but).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent view = new Intent(MainActivity.this, ExpandableActivity.class);
                startActivity(view);
            }
        });
    }
}