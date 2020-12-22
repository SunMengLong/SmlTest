package com.sml.test.a_z;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SlideBar extends View {

    public static final String[] BAR_LETTERS = {"A", "B", "C", "D", "E", "F", "G",
            "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z", "#"};
    private Paint paint = new Paint();
    private int selectIndex = -1; //点击的下标
    private boolean isShowBkg = false; //按下时是否改变背景色
    private RecyclerView recyclerView; //与之绑定的RecyclerView
    List<Content> data; //内容集合
    private Map<Integer, Integer> positionAB = new HashMap<>(); //存储右侧导航列表与名称列表position对应关系

    public void bindViewAndData(RecyclerView recyclerView, List<Content> data) {
        this.recyclerView = recyclerView;
        this.data = data;
        Collections.sort(data);
        initPositionAB();
        for (int i = 0; i < SlideBar.BAR_LETTERS.length; i++) {
            for (int j = 0; j < data.size(); j++) {
                if (SlideBar.BAR_LETTERS[i].equals(data.get(j).getFirstLetter())) {
                    positionAB.put(i, j);
                    data.get(j).setShowIndex(true);
                    break;
                }
            }
        }
    }

    private void initPositionAB() {
        for (int i = 0; i < BAR_LETTERS.length; i++) {
            positionAB.put(i, -1);
        }
    }

    public SlideBar(Context context) {
        super(context);
    }

    public SlideBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SlideBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isShowBkg == true) {
//            canvas.drawColor(Color.BLUE);//显示背景
        }
        int height = getHeight();
        int width = getWidth();
        int singleHeight = (height - 20) / BAR_LETTERS.length;//每个字母占得高度
        for (int i = 0; i < BAR_LETTERS.length; i++) {//遍历字母
            paint.setColor(Color.GRAY);
            paint.setTextSize(30);
            paint.setAntiAlias(true);//设置抗锯齿样式
            if (i == selectIndex) {//如果被选中
//                paint.setColor(Color.WHITE);
                paint.setTextSize(35);
                paint.setFakeBoldText(true);//加粗
            }
            //从左下角开始绘制
            float xpos = (width - paint.measureText(BAR_LETTERS[i])) / 2;//x轴
            float ypos = singleHeight * i + singleHeight;//y轴 （i从0开始算）
            canvas.drawText(BAR_LETTERS[i], xpos, ypos, paint);//开始绘制
            paint.reset();//重置画笔
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float ypos = event.getY();
        int oldSelectIndex = selectIndex;//上一次触发的坐标
        int newSelectIndex = (int) (ypos / getHeight() * BAR_LETTERS.length);//根据触摸的位置确定点的哪个字母
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                isShowBkg = true;
                if (oldSelectIndex != newSelectIndex
                        && newSelectIndex >= 0 && newSelectIndex <= BAR_LETTERS.length) {
                    onTouchLetterChange(BAR_LETTERS[newSelectIndex], true, newSelectIndex);//注册点击事件并传入字母
                    oldSelectIndex = newSelectIndex;
                    selectIndex = newSelectIndex;
                    invalidate();//重新绘制
                }
                break;
            case MotionEvent.ACTION_UP:
                isShowBkg = false;
                selectIndex = -1;
                invalidate();
                onTouchLetterChange(BAR_LETTERS[newSelectIndex], false, newSelectIndex);//注册点击事件并传入字母
                break;
            case MotionEvent.ACTION_MOVE:
                if (oldSelectIndex != newSelectIndex
                        && newSelectIndex >= 0 && newSelectIndex <= BAR_LETTERS.length) {
                    onTouchLetterChange(BAR_LETTERS[newSelectIndex], true, newSelectIndex);//注册点击事件并传入字母
                    oldSelectIndex = newSelectIndex;
                    selectIndex = newSelectIndex;
                    invalidate();//重新绘制
                }
                break;
        }
        return true;
    }

    public void onTouchLetterChange(String letter, boolean touch, int position) {
        if (touch) {
            position = positionAB.get(position);
            if (position == -1) {
                return;
            }
            recyclerView.scrollToPosition(position);
            LinearLayoutManager mLayoutManager =
                    (LinearLayoutManager) recyclerView.getLayoutManager();
            mLayoutManager.scrollToPositionWithOffset(position, 0);
        }
    }
}
