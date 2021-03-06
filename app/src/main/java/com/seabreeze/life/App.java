package com.seabreeze.life;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;

import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.github.moduth.blockcanary.BlockCanary;
import com.github.moduth.blockcanary.BlockCanaryContext;
import com.robot.seabreeze.log.Log;
import com.robot.seabreeze.log.inner.ConsoleTree;
import com.robot.seabreeze.log.inner.FileTree;
import com.robot.seabreeze.log.inner.LogcatTree;
import com.seabreeze.life.di.component.AppComponent;
import com.seabreeze.life.di.component.DaggerAppComponent;
import com.seabreeze.life.di.module.AppModule;
import com.seabreeze.life.di.module.RetrofitModule;
import com.seabreeze.life.utils.FileUtils;
import com.seabreeze.life.utils.crash.RCrashHandler;
import com.squareup.leakcanary.LeakCanary;
import com.zhy.autolayout.config.AutoLayoutConifg;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class App extends Application {

    private AppComponent appComponent;
    private static App app;

    public static int SCREEN_WIDTH = -1;
    public static int SCREEN_HEIGHT = -1;
    public static float DIMEN_RATE = -1.0F;
    public static int DIMEN_DPI = -1;

    public static App getInstance() {
        return app;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        app = this;

        initAppComponent();

        initLogger(this);

        getScreenSize();

        AutoLayoutConifg.getInstance().useDeviceSize();

        //内存泄漏检测
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);

        BlockCanary.install(this, new BlockCanaryContext()).start();

        String crashPath = FileUtils.getCacheDir(this) + File.separator + "crash";
        RCrashHandler.getInstance(crashPath).init(this, null);
    }

    private void initAppComponent() {
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this))
                .retrofitModule(new RetrofitModule(this))
                .build();
    }

    private void initLogger(@NonNull Context context) {
        if (BuildConfig.DEBUG) {
            Log.getLogConfig().configAllowLog(true).configShowBorders(false);
            String printLogPath = FileUtils.getCacheDir(this) + File.separator + "log";
            Log.plant(new FileTree(context, printLogPath));
            Log.plant(new ConsoleTree());
            Log.plant(new LogcatTree());
        }
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }


    public void getScreenSize() {
        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        Display display = windowManager.getDefaultDisplay();
        display.getMetrics(dm);
        DIMEN_RATE = dm.density / 1.0F;
        DIMEN_DPI = dm.densityDpi;
        SCREEN_WIDTH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;
        if (SCREEN_WIDTH > SCREEN_HEIGHT) {
            int t = SCREEN_HEIGHT;
            SCREEN_HEIGHT = SCREEN_WIDTH;
            SCREEN_WIDTH = t;
        }
        Log.d("DIMEN_RATE:" + DIMEN_RATE);
        Log.d("DIMEN_DPI:" + DIMEN_DPI);
        Log.d("SCREEN_WIDTH:" + SCREEN_WIDTH);
        Log.d("SCREEN_HEIGHT:" + SCREEN_HEIGHT);
    }


}

