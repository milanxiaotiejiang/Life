package com.seabreeze.life.common.ui.activity;

import android.content.pm.ActivityInfo;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.seabreeze.life.R;
import com.seabreeze.life.common.base.ActivityCollector;

/**
 * Created by android on 2018/1/31.
 */

public class CrashActivity extends AppCompatActivity {

    private ViewDataBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        ActivityCollector.addActivity(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crash);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_crash);
    }

    public void crashClick(View v) {
        ActivityCollector.finishAll();
    }

}
