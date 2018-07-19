package com.seabreeze.life.common.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.ImageView;

import com.robot.seabreeze.log.Log;
import com.seabreeze.life.R;
import com.seabreeze.life.common.base.SimpleActivity;
import com.seabreeze.life.common.image.ImageLoader;
import com.seabreeze.life.utils.permiss.HiPermission;
import com.seabreeze.life.utils.permiss.PermissionCallback;

import java.lang.ref.WeakReference;

public class SplashActivity extends SimpleActivity {

    private Handler handler = new SplashHandler(SplashActivity.this);

    static final String[] PERMISSIONS = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };

    private static final int REFRESH_COMPLETE = 0X153;

    private static final String PACKAGE_URL_SCHEME = "package:"; // 方案

    @Override
    protected int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initEventAndData() {
        ImageView ivSplash = (ImageView) findViewById(R.id.iv_splash);

//        ImageLoader.loadImage(this, ivSplash, R.mipmap.splash, false, 500);

        HiPermission.create(this)
                .checkMutiPermission(PERMISSIONS, new PermissionCallback() {
                    @Override
                    public void onClose() {
                        showMissingPermissionDialog();
                    }

                    @Override
                    public void onFinish() {
                        initFinish();
                    }

                    @Override
                    public void onDeny(String permission, int position) {
                        Log.e("onDeny");
                    }

                    @Override
                    public void onGuarantee(String permission, int position) {
                        Log.e("onGuarantee");
                    }
                });
    }

    private void initFinish() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 2000);
    }


    // 显示缺失权限提示
    private void showMissingPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SplashActivity.this);
        builder.setTitle(R.string.help);
        builder.setMessage(R.string.string_help_text);

        // 拒绝, 退出应用
        builder.setNegativeButton(R.string.permission_quit, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                handler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 1000);
            }
        });

        builder.setPositiveButton(R.string.settings, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startAppSettings();
            }
        });

        builder.show();
    }

    // 启动应用的设置
    private void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse(PACKAGE_URL_SCHEME + getPackageName()));
        startActivity(intent);
    }


    private void handleMessage(Message msg) {
        switch (msg.what) {
            case REFRESH_COMPLETE:
                finish();
                break;
        }
    }

    private static class SplashHandler extends Handler {

        private WeakReference<SplashActivity> weakReference;

        public SplashHandler(SplashActivity t) {
            weakReference = new WeakReference<>(t);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (weakReference != null) {

                SplashActivity t = weakReference.get();
                if (t != null) {
                    t.handleMessage(msg);
                }
            }
        }
    }

}
