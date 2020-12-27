package com.sml.test.a_z;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.sml.test.R;

public class IndexDialog {

    private static volatile IndexDialog instance = null;
    private PopupWindow popupWindow = null;

    public static IndexDialog getInstance() {
        if (instance == null) {
            synchronized (IndexDialog.class) {
                if (instance == null) {
                    instance = new IndexDialog();
                }
            }
        }
        return instance;
    }

    public void showPopupWindow(Context context, View slideView, int position, String indexStr) {
        dismissDialog();
        View contentView = LayoutInflater.from(context).inflate(R.layout.popupwindow_shop, null);
        TextView indexStrTv = contentView.findViewById(R.id.index_str_tv);
        indexStrTv.setText(indexStr);
        popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        int windowPos[] = calculatePopWindowPos(slideView, contentView);
        windowPos[1] = position;
        popupWindow.showAtLocation(slideView, Gravity.LEFT | Gravity.TOP, windowPos[0], windowPos[1]);
    }

    public void dismissDialog(){
        if (popupWindow != null) {
            popupWindow.dismiss();
            popupWindow = null;
        }
    }

    private int[] calculatePopWindowPos(final View slideBarView, final View dialogView) {
        final int showDialogXY[] = new int[2];

        final int anchorLoc[] = new int[2];
        // 获取锚点View在屏幕上的左上角坐标位置
        slideBarView.getLocationOnScreen(anchorLoc);

        dialogView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        final int anchorWidth = dialogView.getMeasuredWidth();

        showDialogXY[0] = anchorLoc[0] - anchorWidth;
        showDialogXY[1] = anchorLoc[1];
        return showDialogXY;
    }
}
