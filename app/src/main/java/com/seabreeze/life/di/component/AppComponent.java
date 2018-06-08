package com.seabreeze.life.di.component;

import android.content.Context;

import com.seabreeze.life.common.api.KuulaApiService;
import com.seabreeze.life.common.api.KyApiService;
import com.seabreeze.life.common.api.OpApiService;
import com.seabreeze.life.common.api.VeerApiService;
import com.seabreeze.life.common.api.WyApiService;
import com.seabreeze.life.di.module.AppModule;
import com.seabreeze.life.di.module.RetrofitModule;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;

@Singleton
@Component(modules = {AppModule.class, RetrofitModule.class})
public interface AppComponent {

    Context getContext();

    KyApiService getKyApiService();

    WyApiService getWyApiService();

    OpApiService getOpApiService();

    VeerApiService getVeerApiService();

    KuulaApiService getKuulaApiService();

    OkHttpClient getOKHttpClient();
}
