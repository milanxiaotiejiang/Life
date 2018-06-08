package com.seabreeze.life.di.module;

import android.content.Context;

import com.seabreeze.life.App;
import com.seabreeze.life.common.api.KuulaApiService;
import com.seabreeze.life.common.api.KyApiService;
import com.seabreeze.life.common.api.OpApiService;
import com.seabreeze.life.common.api.VeerApiService;
import com.seabreeze.life.common.api.WyApiService;
import com.seabreeze.life.di.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module
public class AppModule {

    public final App mApp;

    public AppModule(App app) {
        this.mApp = app;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return mApp;
    }

    @Singleton
    OkHttpClient provideOKHttpClient(RetrofitHelper helper) {
        return helper.getClient();
    }

    @Provides
    KyApiService provideKyApiService(RetrofitHelper helper) {
        return helper.getKyApiService();
    }

    @Provides
    WyApiService provideWyApiService(RetrofitHelper helper) {
        return helper.getWyApiService();
    }

    @Provides
    OpApiService provideOpApiService(RetrofitHelper helper) {
        return helper.getOpApiService();
    }

    @Provides
    VeerApiService provideVeerApiService(RetrofitHelper helper) {
        return helper.getVeerApiService();
    }

    @Provides
    KuulaApiService provideKuulaApiService(RetrofitHelper helper) {
        return helper.getKuulaApiService();
    }

}
