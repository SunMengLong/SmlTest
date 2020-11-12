package com.sml.test.apng;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.vectordrawable.graphics.drawable.Animatable2Compat;

import com.github.penfeizhou.animation.apng.APNGDrawable;
import com.github.penfeizhou.animation.loader.AssetStreamLoader;

public class LoadApngUtil {

    public static void loadApngUtil(Context context, String imgAssetsName, ImageView showView) {
        AssetStreamLoader assetLoader = new AssetStreamLoader(context, imgAssetsName);
        APNGDrawable apngDrawable = new APNGDrawable(assetLoader);
        showView.setImageDrawable(apngDrawable);
        apngDrawable.setLoopLimit(Integer.MAX_VALUE);
        apngDrawable.registerAnimationCallback(new Animatable2Compat.AnimationCallback() {
            @Override
            public void onAnimationStart(Drawable drawable) {
                super.onAnimationStart(drawable);
            }
        });
    }
}
