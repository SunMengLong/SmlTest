package com.sml.test.popup;

import android.app.Activity;
import android.app.NotificationManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.sml.test.R;
import com.sml.test.util.ScreenUtils;

/**
 * 自适应弹框-根据传入坐标点（view的右上角坐标点）
 */
public class AdapterDialogXYActivity extends Activity {

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
        popupWindow.showAtLocation(butShowPopupwindow, Gravity.LEFT | Gravity.TOP, windowPos[0], windowPos[1]);
//        popupWindow.showAtLocation(butShowPopupwindow, Gravity.LEFT | Gravity.TOP, 0, 0);
// windowContentViewRoot是根布局View


    }

    /**
     * 计算出来的位置，y方向就在anchorView的上面和下面对齐显示，x方向就是与屏幕右边对齐显示
     * 如果anchorView的位置有变化，就可以适当自己额外加入偏移来修正
     */
    private int[] calculatePopWindowPos(final View slideBarView, final View dialogView) {
        final int showDialogXY[] = new int[2];

        final int anchorLoc[] = new int[2];
        // 获取锚点View在屏幕上的左上角坐标位置
        slideBarView.getLocationOnScreen(anchorLoc);

        dialogView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        final int anchorWidth = dialogView.getMeasuredWidth();
        final int anchorHeight = dialogView.getMeasuredHeight();
        showDialogXY[0] = anchorLoc[0] - anchorWidth;
        showDialogXY[1] = anchorLoc[1] - Math.abs(anchorHeight - anchorLoc[1]) / 2;
        return showDialogXY;
    }
}