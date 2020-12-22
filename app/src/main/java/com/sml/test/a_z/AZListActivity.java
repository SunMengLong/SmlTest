package com.sml.test.a_z;

import android.app.Activity;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sml.test.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AZListActivity extends Activity {

    private SlideBar slideBar;
    private RecyclerView rc;

    private LinearLayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_z_list);

        rc = findViewById(R.id.recy);
        slideBar = findViewById(R.id.slide_bar);

        setView();
    }

    private void setView() {
        manager = new LinearLayoutManager(this);
        rc.setLayoutManager(manager);

        List<Content> data = new ArrayList<>();
        data.add(new Content("上海"));
        data.add(new Content("天津"));
        data.add(new Content("北京"));
        data.add(new Content("深圳"));
        data.add(new Content("广州"));
        data.add(new Content("aaaa"));
        data.add(new Content("成都"));
        data.add(new Content("西安"));
        data.add(new Content("武汉"));
        data.add(new Content("郑州"));
        data.add(new Content("邯郸"));
        data.add(new Content("，，，，邯郸。"));
        data.add(new Content("上海"));
        data.add(new Content("天津"));
        data.add(new Content("北京"));
        data.add(new Content("深圳"));
        data.add(new Content("广州"));
        data.add(new Content("aaaa"));
        data.add(new Content("成都"));
        data.add(new Content("西安"));
        data.add(new Content("武汉"));
        data.add(new Content("郑州"));
        data.add(new Content("邯郸"));
        data.add(new Content("，，，，邯郸。"));
        data.add(new Content("上海"));
        data.add(new Content("天津"));
        data.add(new Content("北京"));
        data.add(new Content("深圳"));
        data.add(new Content("广州"));
        data.add(new Content("aaaa"));
        data.add(new Content("成都"));
        data.add(new Content("西安"));
        data.add(new Content("武汉"));
        data.add(new Content("郑州"));
        data.add(new Content("邯郸"));
        data.add(new Content("，，，，邯郸。"));

        slideBar.bindViewAndData(rc, data);

        AZListAdapter azListAdapter = new AZListAdapter(data, this);
        rc.setAdapter(azListAdapter);
    }
}