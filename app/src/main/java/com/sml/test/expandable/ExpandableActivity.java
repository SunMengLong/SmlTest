package com.sml.test.expandable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.sml.test.R;

public class ExpandableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable);

        ExpandableTextView tvContent = findViewById(R.id.tv_content);
        tvContent.setNeedSelf(true);
        tvContent.setLinkClickListener(new ExpandableTextView.OnLinkClickListener() {
            @Override
            public void onLinkClickListener(LinkType type, String content, String selfContent) {
                Toast.makeText(ExpandableActivity.this, "标题为：" + content + ", 内容为：" + selfContent, Toast.LENGTH_SHORT).show();
            }
        });
        tvContent.setContent("[hello](word)今天晴转多云");
    }
}