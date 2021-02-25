package com.sml.test.util;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sml.test.R;

public class UtilActivity extends Activity {

    private Button getScreenButton;

    private TextView aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_util);
        getScreenButton = findViewById(R.id.util_get_screen_but);
        getScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getScreenButton.setText("屏幕宽度：" + ScreenUtils.getScreenWidth(UtilActivity.this) + " 屏幕高度：" + ScreenUtils.getScreenHeight(UtilActivity.this) + " 状态栏高度：" + ScreenUtils.getStatusBarHeight(UtilActivity.this));
            }
        });

        aa.setText("111");
    }
}