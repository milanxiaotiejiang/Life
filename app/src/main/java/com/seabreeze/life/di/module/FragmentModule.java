package com.seabreeze.life.di.module;

import android.app.Activity;
import android.support.v4.app.Fragment;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {

    private Fragment mFragment;

    public FragmentModule(Fragment fragment) {
        this.mFragment = fragment;
    }

    @Provides
    public Activity provideActivity(){
        return mFragment.getActivity();
    }
}
