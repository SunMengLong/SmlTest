package com.sml.test.apng;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.sml.test.R;

public class APngActivity extends Activity {

    private ImageView apngImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_png);

        apngImg = findViewById(R.id.apng_img);

        LoadApngUtil.loadApngUtil(this, "select_animation.png", apngImg);
    }
}