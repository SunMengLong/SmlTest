package com.novice.guide;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.app.hubert.guide.NewbieGuide;
import com.app.hubert.guide.model.GuidePage;
import com.app.hubert.guide.model.HighLight;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        NewbieGuide.with(this)
                .setLabel("guide1")
                .alwaysShow(true)
                .anchor(getWindow().getDecorView())
                .addGuidePage(GuidePage.newInstance()
//                        .addHighLight(findViewById(R.id.btn), HighLight.Shape.ROUND_RECTANGLE,50,5,null)
                        .setLayoutRes(R.layout.assets_type_item))
                .show();
    }
}