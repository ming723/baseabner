package com.abner.ming.base.gesturelock;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.abner.ming.base.R;


public class GestureMainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnSetLock;
    private Button mBtnVerifyLock;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_main);
        setUpView();
        setUpListener();
    }
    private void setUpView() {
        mBtnSetLock = (Button) findViewById(R.id.btn_set_lockpattern);
        mBtnVerifyLock = (Button) findViewById(R.id.btn_verify_lockpattern);
    }

    private void setUpListener() {
        mBtnSetLock.setOnClickListener(this);
        mBtnVerifyLock.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_set_lockpattern) {
            startSetLockPattern();

        } else if (i == R.id.btn_verify_lockpattern) {
            startVerifyLockPattern();

        } else {
        }
    }

    private void startSetLockPattern() {
        Intent intent = new Intent(GestureMainActivity.this, GestureEditActivity.class);
        startActivity(intent);
    }

    private void startVerifyLockPattern() {
        Intent intent = new Intent(GestureMainActivity.this, GestureVerifyActivity.class);
        startActivity(intent);
    }
}
