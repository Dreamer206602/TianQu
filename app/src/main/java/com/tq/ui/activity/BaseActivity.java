package com.tq.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tq.R;

/**
 * 所有Activity的基类
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }
}
