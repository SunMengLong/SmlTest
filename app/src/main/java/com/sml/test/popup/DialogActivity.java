package com.sml.test.popup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sml.test.R;

public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
    }

    /**
     * 全局弹框
     *
     * @param view
     */
    public void alert_btn(View view) {
        Intent intent = new Intent(DialogActivity.this, AlertWindowActivity.class);
        startActivity(intent);
    }

    /**
     * 自适应弹框
     *
     * @param view
     */
    public void adapter_btn(View view) {
        Intent intent = new Intent(DialogActivity.this, PopupWindowActivity.class);
        startActivity(intent);
    }

    /**
     * 自适应弹框 - 根据传入的view坐标点
     *
     * @param view
     */
    public void adapter_xy_btn(View view) {
        Intent intent = new Intent(DialogActivity.this, AdapterDialogXYActivity.class);
        startActivity(intent);
    }
}