package com.sml.test.edit;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.sml.test.R;

public class EditTextActivity extends Activity {

    private MsgEditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        editText = findViewById(R.id.edit);
    }

    public void sendNormalNotify(View view) {
        editText.addAtSpan("@", "1111", 1);
    }
}
