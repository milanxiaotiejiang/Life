package com.seabreeze.life.common.base;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.seabreeze.life.App;
import com.seabreeze.life.common.base.rx.RxSupportActivity;
import com.seabreeze.life.common.base.rx.RxSupportFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;

public abstract class SimpleActivity extends RxSupportActivity {

    protected Activity mContext;

    private Unbinder mUnBinder;

    protected Handler mHandler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mUnBinder = ButterKnife.bind(this);
        mContext = this;
        initEventAndData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
    }

    protected void setToolBar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressedSupport();
            }
        });
    }


    protected abstract int getLayout();

    protected abstract void initEventAndData();

    /**
     * 显示toast
     *
     * @param resId
     */
    public void showToast(final int resId) {
        showToast(getString(resId));
    }

    /**
     * 显示toast
     *
     * @param resStr
     * @return Toast对象，便于控制toast的显示与关闭
     */
    public void showToast(final String resStr) {

        if (TextUtils.isEmpty(resStr)) {
            return;
        }
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast toast = Toast.makeText(SimpleActivity.this, resStr, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}
