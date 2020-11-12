package com.sml.test.popup;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.sml.test.R;
import com.sml.test.util.ScreenUtils;

public class PopupWindowActivity extends Activity {

    private ImageView butShowPopupwindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window);
        butShowPopupwindow = findViewById(R.id.but_show_popupwindow);
        butShowPopupwindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow();
            }
        });
    }

    private void showPopupWindow() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.popupwindow_shop, null);
        final PopupWindow popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
// 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
// 这里单独写一篇文章来分析
        popupWindow.setBackgroundDrawable(new ColorDrawable());
// 设置好参数之后再show
//        popupWindow.showAsDropDown(contentView);

        int windowPos[] = calculatePopWindowPos(butShowPopupwindow, contentView);
        int xOff = 0;// 可以自己调整偏移
        windowPos[0] -= xOff;
        popupWindow.showAtLocation(butShowPopupwindow, Gravity.TOP | Gravity.START, windowPos[0], windowPos[1]);
// windowContentViewRoot是根布局View
    }

    /**
     * 计算出来的位置，y方向就在anchorView的上面和下面对齐显示，x方向就是与屏幕右边对齐显示
     * 如果anchorView的位置有变化，就可以适当自己额外加入偏移来修正
     *
     * @param anchorView  呼出window的view
     * @param contentView window的内容布局
     * @return window显示的左上角的xOff, yOff坐标
     */
    private int[] calculatePopWindowPos(final View anchorView, final View contentView) {
        final int windowPos[] = new int[2];
        final int anchorLoc[] = new int[2];
        // 获取锚点View在屏幕上的左上角坐标位置
        anchorView.getLocationOnScreen(anchorLoc);
        final int anchorWidth = anchorView.getWidth();
        final int anchorHeight = anchorView.getHeight();
        // 获取屏幕的高宽
        final int screenHeight = ScreenUtils.getScreenHeight(anchorView.getContext());
        final int screenWidth = ScreenUtils.getScreenWidth(anchorView.getContext());
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        // 计算contentView的高宽
        final int windowHeight = contentView.getMeasuredHeight();
        final int windowWidth = contentView.getMeasuredWidth();
        // 判断需要向上弹出还是向下弹出显示
//        final boolean isNeedShowUp = (screenHeight - anchorLoc[1] - anchorHeight < windowHeight);
        final boolean isNeedShowUp = (anchorLoc[1] - ScreenUtils.getStatusBarHeight(this) > windowHeight);

        // 计算横向偏移量

        if (isNeedShowUp) {
            windowPos[0] = anchorLoc[0] - Math.abs(anchorWidth - windowWidth) / 2;
            windowPos[1] = anchorLoc[1] - windowHeight;
        } else {
            windowPos[0] = anchorLoc[0] - Math.abs(anchorWidth - windowWidth) / 2;
            windowPos[1] = anchorLoc[1] + anchorHeight;
        }
        return windowPos;
    }
}