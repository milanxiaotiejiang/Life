package com.seabreeze.life.di.component;

import android.app.Activity;

import com.seabreeze.life.common.ui.activity.VrBitmapActivity;
import com.seabreeze.life.di.module.ActivityModule;
import com.seabreeze.life.di.scope.PerActivity;

import dagger.Component;

@PerActivity
@Component(modules = ActivityModule.class, dependencies = AppComponent.class)
public interface ActivityComponent {

    Activity getActivity();

    void inject(VrBitmapActivity vrBitmapActivity);

}
