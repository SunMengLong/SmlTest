package com.sml.test.a_z;

import android.app.Activity;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sml.test.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AZListActivity extends Activity implements SlideBar.onTouchLetterListener {

    private SlideBar slideBar;
    private RecyclerView rc;

    private LinearLayoutManager manager;

    /**
     * 存储右侧导航列表与名称列表position对应关系
     */
    private Map<Integer, Integer> positionAB = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_z_list);

        rc = findViewById(R.id.recy);
        slideBar = findViewById(R.id.slide_bar);

        initPositionAB();
        setView();
    }

    private void initPositionAB() {
        for (int i = 0; i < 27; i++) {
            positionAB.put(i, -1);
        }
    }

    private void setView() {
        slideBar.setOnTouchLetterListener(this);
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
        data.add(new Content("//////邯郸。"));
        data.add(new Content("，，，，邯郸。"));

        data.addAll(data);
        data.addAll(data);
        data.addAll(data);

        Collections.sort(data);


        for (int i = 0; i < SlideBar.BAR_LETTERS.length; i++) {
            for (int j = 0; j < data.size(); j++) {
                if (SlideBar.BAR_LETTERS[i].equals(data.get(j).getFirstLetter())) {
                    data.get(j).setShowIndex(true);
                    positionAB.put(i, j);
                    break;
                }
            }
        }

        AZListAdapter azListAdapter = new AZListAdapter(data, this);
        rc.setAdapter(azListAdapter);
    }

    @Override
    public void onTouchLetterChange(String letter, boolean touch, int position) {
        if (touch) {
            position = positionAB.get(position);
            if (position == -1) {
                return;
            }
            rc.scrollToPosition(position);
            LinearLayoutManager mLayoutManager =
                    (LinearLayoutManager) rc.getLayoutManager();
            mLayoutManager.scrollToPositionWithOffset(position, 0);
        }
    }
}