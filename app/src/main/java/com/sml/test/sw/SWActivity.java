package com.sml.test.sw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.sml.test.R;

import io.agora.rtc.Constants;
import io.agora.rtc.IRtcEngineEventHandler;
import io.agora.rtc.RtcEngine;

/**
 * 声网语音通话
 */
public class SWActivity extends AppCompatActivity {

    private static final int PERMISSION_REQ_ID_RECORD_AUDIO = 22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_w);
        // 获取权限后，初始化 RtcEngine，并加入频道。
        if (checkSelfPermission(Manifest.permission.RECORD_AUDIO, PERMISSION_REQ_ID_RECORD_AUDIO)) {
            initAgoraEngineAndJoinChannel();
        }
    }

    public boolean checkSelfPermission(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(this,
                permission)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{permission},
                    requestCode);
            return false;
        }
        return true;
    }

    private void initAgoraEngineAndJoinChannel() {
        initializeAgoraEngine();
        joinChannel();
    }

    // Java
    private void joinChannel() {
        String accessToken = getString(R.string.agora_access_token);
        if (TextUtils.equals(accessToken, "") || TextUtils.equals(accessToken, "#YOUR ACCESS TOKEN#")) {
            accessToken = null;
        }

        Log.i("smlllll", "joinChannel: 。。。加入频道");
        // 调用 Agora SDK 的 joinChannel 方法加入频道。未指定 uid，SDK 会自动分配一个。
        mRtcEngine.joinChannel(accessToken, "Channel3", "Extra Optional Data", 0);
    }

    private RtcEngine mRtcEngine;
    private final IRtcEngineEventHandler mRtcEventHandler = new IRtcEngineEventHandler() {

        // 注册 onUserOffline 回调。远端用户离开频道后，会触发该回调。
        @Override
        public void onUserOffline(final int uid, final int reason) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mRtcEngine.leaveChannel();
                    finish();
                    Log.i("smlllll", "run: ....对方离开频道。。。。");
//                    onRemoteUserLeft(uid, reason);
                }
            });
        }

        // 注册 onUserMuteAudio 回调。远端用户静音后，会触发该回调。
        @Override
        public void onUserMuteAudio(final int uid, final boolean muted) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.i("smlllll", "run: ....对方静音。。。。");
//                    onRemoteUserVoiceMuted(uid, muted);
                }
            });
        }

        @Override
        public void onUserJoined(int i, int i1) {
            super.onUserJoined(i, i1);
            Log.i("smlllll", "run: ....对方加入频道。。。。");
        }

        @Override
        public void onLeaveChannel(RtcStats rtcStats) {
            super.onLeaveChannel(rtcStats);
            Log.i("smlllll", "onLeaveChannel: 离开频道。。。。。。");
        }

        @Override
        public void onJoinChannelSuccess(String s, int i, int i1) {
            super.onJoinChannelSuccess(s, i, i1);
            Log.i("smlllll", "joinChannel: 。。。加入频道   成功....");
        }

        @Override
        public void onError(int i) {
            super.onError(i);
            Log.i("smllllllll", "onError: ...."+i);
        }
    };

    // 调用 Agora SDK 的方法初始化 RtcEngine。
    private void initializeAgoraEngine() {
        try {
            mRtcEngine = RtcEngine.create(getBaseContext(), getString(R.string.agora_app_id), mRtcEventHandler);
            mRtcEngine.setChannelProfile(Constants.CHANNEL_PROFILE_COMMUNICATION);
        } catch (Exception e) {
            throw new RuntimeException("NEED TO check rtc sdk init fatal error\n" + Log.getStackTraceString(e));
        }
    }

    /**
     * 静音
     *
     * @param view
     */
    public void onLocalAudioMuteClicked(View view) {
        mRtcEngine.muteLocalAudioStream(false);
    }

    /**
     * 免提
     *
     * @param view
     */
    public void onSwitchSpeakerphoneClicked(View view) {
        mRtcEngine.setEnableSpeakerphone(true);
    }

    /**
     * 结束语聊
     *
     * @param view
     */
    public void onEncCallClicked(View view) {
        mRtcEngine.leaveChannel();
        finish();
    }
}