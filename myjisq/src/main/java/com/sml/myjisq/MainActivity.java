package com.sml.myjisq;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private float touruzonge = 100;
    private float zongshouyi = 100;
    private float shouyilv;
    private String huibaolv;

    private TextView touruzongeTv;
    private TextView zongshouyiTv;
    private TextView shouyilvTv;
    private TextView huibaolvTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.edit);
        touruzongeTv = findViewById(R.id.touru_tv);
        zongshouyiTv = findViewById(R.id.zongshouyi_tv);
        shouyilvTv = findViewById(R.id.shouyilv_tv);
        huibaolvTv = findViewById(R.id.huibaolv_tv);

        findViewById(R.id.but).setOnClickListener(v -> {
            String aaa = editText.getText().toString();
            boolean isss = false;
            if (aaa.contains(" ")) {
                isss = true;
                aaa.replace(" ", "");
            }
            float dqshouyilv = Float.parseFloat(aaa);
            if (isss) {
                dqshouyilv = dqshouyilv * -1;
            }
            Log.i("smlllll", "onCreate: ..." + dqshouyilv);

            if (dqshouyilv < 0) {
                touruzonge = touruzonge + (zongshouyi * dqshouyilv * -1 * 100);
            }

            zongshouyi = zongshouyi + (zongshouyi * dqshouyilv);

            shouyilv = shouyilv + dqshouyilv;

            huibaolv = touruzonge + "/" + zongshouyi;

            touruzongeTv.setText("投入总额：" + touruzonge);
            zongshouyiTv.setText("总收益：" + zongshouyi);
            shouyilvTv.setText("收益率：" + shouyilv);
            huibaolvTv.setText("回报率：" + huibaolv);

            editText.setText("");
        });
    }
}