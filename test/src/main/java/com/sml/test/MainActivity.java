package com.sml.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ml.apng.SmlTest;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SmlTest.logi();
    }
}
