package com.sml.test.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sml.test.MainActivity;
import com.sml.test.R;

/**
 * 保存activity状态
 */
public class SaveStateActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editText;
    private Button buttonSet, buttonRead;
    //定义一个常量
    double pai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_state);
        //将pai保存
        if (savedInstanceState != null)
            pai = savedInstanceState.getDouble("pai");

        //获取控件
        editText = (EditText) findViewById(R.id.edit1);
        buttonSet = (Button) findViewById(R.id.btn_Set);
        buttonRead = (Button) findViewById(R.id.btn_Read);

        //设置监听事件
        buttonSet.setOnClickListener(this);
        buttonRead.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_Set:
                editText.setText("圆周率为：");
                pai = 3.141592654;
                break;
            case R.id.btn_Read:
                String str = editText.getText().toString() + pai;
                Toast.makeText(SaveStateActivity.this, str, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("smllll", "onSaveInstanceState: .....保存状态："+pai);
        //将需要保存的数据放入Bundle中
        outState.putDouble("pai", pai);
    }
}