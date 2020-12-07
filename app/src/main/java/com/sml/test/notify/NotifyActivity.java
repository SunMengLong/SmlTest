package com.sml.test.notify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.sml.test.R;
import com.sml.test.popup.PopupWindowActivity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class NotifyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);
    }

    /**
     * 发送普通通知 - 在屏幕上方不弹出
     *
     * @param view
     */
    public void sendNormalNotify(View view) {
        Intent intent = new Intent(this, PopupWindowActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(NotifyActivity.this)
                .setContentTitle("这是测试通知标题")  //设置标题
                .setContentText("这是测试通知内容") //设置内容
                .setWhen(System.currentTimeMillis())  //设置时间
                .setSmallIcon(R.mipmap.ic_launcher)  //设置小图标
                .setContentIntent(pi)
                .setAutoCancel(true) //设置为自动取消
                .setPriority(NotificationCompat.PRIORITY_MAX)
//                .setLargeIcon()   //设置大图标
                .build();
        manager.notify(2, notification);
    }

    /**
     * 发送普通通知 - 在屏幕上方弹出
     *
     * @param view
     */
    public void sendNotifyShowTopDialog(View view) {

        show(getApplication(), PopupWindowActivity.class, 1, "huawei", false, "这是测试通知标题", "这是测试通知内容");
    }

    private void show(Context context, Class intentActivityClass, int notifyId, String channelName, boolean isShowFloatDialog, String notifyTitle, String notifyContent) {
        //通知管理类
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (manager == null) return;
        //Android O(8.0) 版本要求设置渠道
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelName, channelName, isShowFloatDialog ? NotificationManager.IMPORTANCE_HIGH : NotificationManager.IMPORTANCE_LOW);
            manager.createNotificationChannel(channel);
        }
        //点击通知跳转界面
        Intent intent = new Intent(context, intentActivityClass);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelName);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, notifyId, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = builder
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText(notifyContent)
                .setContentTitle(notifyTitle)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setPriority(isShowFloatDialog ? NotificationCompat.PRIORITY_HIGH : NotificationCompat.PRIORITY_MIN) //重要程度
                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .build();
        if (Build.MANUFACTURER.equalsIgnoreCase("Xiaomi")) {
            try {
                Field field = notification.getClass().getDeclaredField("extraNotification");
                Object extraNotification = field.get(notification);
                Method method = extraNotification.getClass().getDeclaredMethod("setMessageCount", int.class);
                method.invoke(extraNotification, 1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        manager.notify(notifyId, notification);
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        sendNotifyShowTopDialog(null);
        finish();
    }
}