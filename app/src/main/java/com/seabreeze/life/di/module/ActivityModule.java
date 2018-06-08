package com.seabreeze.life.di.module;

import android.app.Activity;

import com.seabreeze.life.di.scope.PerActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @PerActivity
    public Activity provodeActivity() {
        return mActivity;
    }
}
