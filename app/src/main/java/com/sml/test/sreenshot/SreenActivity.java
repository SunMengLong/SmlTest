package com.sml.test.sreenshot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import com.sml.test.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SreenActivity extends AppCompatActivity {

    private ScreenShotListenManager screenShotListenManager;//截屏监听相关
    private boolean isScreenShotListen;//是否监听截屏

    private static final Uri QUERY_URI = MediaStore.Files.getContentUri("external");
    private static final String ORDER_BY = MediaStore.Files.FileColumns._ID + " DESC limit %d";
    private static final String DURATION = "duration";
    private int limit = Integer.MAX_VALUE;
    //筛除gif
    private static final String NOT_GIF = "!='image/gif'";
    private static final String SELECTION_NOT_GIF = MediaStore.Files.FileColumns.MEDIA_TYPE + "=?"
            + " AND " + MediaStore.MediaColumns.SIZE + ">0"
            + " AND " + MediaStore.MediaColumns.MIME_TYPE + NOT_GIF;
    private final int RELOAD_MEDIA_WHAT = 0;//重新加载
    private final int RELOAD_MEDIA_DELAYTIME = 2000;//重新加载间隔时间

    private Handler reLoadMediahHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            loadAllMedia();
        }
    };
    // 媒体文件数据库字段
    private static final String[] PROJECTION = {
            MediaStore.Files.FileColumns._ID,
            MediaStore.MediaColumns.DATA,
            MediaStore.MediaColumns.MIME_TYPE,
            MediaStore.MediaColumns.WIDTH,
            MediaStore.MediaColumns.HEIGHT,
            DURATION,
            // 图片的旋转方向，部分手机拍摄的照片会默认带个旋转方向。如果是视频，这个值为0
            MediaStore.Images.ImageColumns.ORIENTATION};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sreen);
        registerScreenShotListener();

//        LoaderManager.getInstance(this).restartLoader(1, null,
//                new LoaderCallbacks());
    }

    /**
     * 注册截屏监听
     */
    private void registerScreenShotListener() {
        screenShotListenManager = ScreenShotListenManager.newInstance(this);
        screenShotListenManager.setListener(
                new ScreenShotListenManager.OnScreenShotListener() {
                    public void onShot(String imagePath) {
                        if (isScreenShotListen) {
                            // do something
                            Log.i("smllll", "onShot: 截屏");
                        }
                    }
                }
        );

        if (Build.VERSION.SDK_INT > 22) {
            List<String> permissionList = new ArrayList<>();
            // 检查权限
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            } else {
                // 开始截图监听
                screenShotListenManager.startListen();
            }
            if (permissionList != null && (permissionList.size() != 0)) {
                ActivityCompat.requestPermissions(this, permissionList.toArray(new String[permissionList.size()]), 0);
            }
        } else {
            // 开始截图监听
            screenShotListenManager.startListen();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        isScreenShotListen = true;
    }

    @Override
    public void onStop() {
        super.onStop();
        isScreenShotListen = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 注销监听
        screenShotListenManager.stopListen();
        isScreenShotListen = false;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Toast.makeText(this, "屏幕获取焦点：" + hasFocus, Toast.LENGTH_SHORT).show();
    }

    class LoaderCallbacks implements LoaderManager.LoaderCallbacks<Cursor> {

        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            reLoadMediahHandler.sendEmptyMessageDelayed(RELOAD_MEDIA_WHAT, RELOAD_MEDIA_DELAYTIME);
            CursorLoader cursorLoader = null;
            String sortOrder = String.format(Locale.getDefault(), ORDER_BY, limit);
            // 只获取图片
            String[] MEDIA_TYPE_IMAGE = getSelectionArgsForSingleMediaType(MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE);
            cursorLoader = new CursorLoader(
                    SreenActivity.this, QUERY_URI,
                    PROJECTION, SELECTION_NOT_GIF, MEDIA_TYPE_IMAGE
                    , sortOrder);
            return cursorLoader;
        }

        @Override
        public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
            Log.i("smllll", "onLoadFinished: 图片个数：" + data.getCount());
//            reLoadMediahHandler.removeMessages(RELOAD_MEDIA_WHAT);
        }

        @Override
        public void onLoaderReset(@NonNull Loader<Cursor> loader) {
            reLoadMediahHandler.sendEmptyMessageDelayed(RELOAD_MEDIA_WHAT, RELOAD_MEDIA_DELAYTIME);
        }
    }

    /**
     * 获取指定类型的文件
     *
     * @param mediaType
     * @return
     */
    private static String[] getSelectionArgsForSingleMediaType(int mediaType) {
        return new String[]{String.valueOf(mediaType)};
    }

    public void loadAllMedia() {
        LoaderManager.getInstance(SreenActivity.this).restartLoader(1, null,
                new LoaderCallbacks());
    }
}