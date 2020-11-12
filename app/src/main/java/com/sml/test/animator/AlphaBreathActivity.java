package com.sml.test.animator;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.widget.ImageView;

import com.sml.test.R;

/**
 * 呼吸动画
 */
public class AlphaBreathActivity extends AppCompatActivity {

    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alpha_breath);

        img = findViewById(R.id.img);

        startAlphaBreathAnimation();
    }

    /**
     * 开启透明度渐变呼吸动画
     */
    private void startAlphaBreathAnimation() {
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(img, "alpha", 0f, 1f);
        alphaAnimator.setDuration(4000);
        alphaAnimator.setInterpolator(new BraetheInterpolator());//使用自定义的插值器
        alphaAnimator.setRepeatCount(ValueAnimator.INFINITE);
        alphaAnimator.start();
    }
}