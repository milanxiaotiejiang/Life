package com.seabreeze.life.di;

import com.seabreeze.life.common.api.KuulaApiService;
import com.seabreeze.life.common.api.KyApiService;
import com.seabreeze.life.common.api.OpApiService;
import com.seabreeze.life.common.api.UrlManager;
import com.seabreeze.life.common.api.VeerApiService;
import com.seabreeze.life.common.api.WyApiService;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {

    OkHttpClient client;

    private KyApiService kyApiService;
    private WyApiService wyApiService;
    private OpApiService opApiService;
    private VeerApiService veerApiService;
    private KuulaApiService kuulaApiService;

    @Inject
    RetrofitHelper(OkHttpClient client) {
        this.client = client;
        init();
    }

    private void init() {
        kyApiService = getApiService(UrlManager.KAIYAN_HOST, KyApiService.class);
        wyApiService = getApiService(UrlManager.WANGYI_HOST, WyApiService.class);
        opApiService = getApiService(UrlManager.YIREN_HOST, OpApiService.class);
        veerApiService = getApiService(UrlManager.VEER_HOST, VeerApiService.class);
        kuulaApiService = getApiService(UrlManager.KUULA_HOST, KuulaApiService.class);
    }

    private <T> T getApiService(String baseUrl, Class<T> clz) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(clz);
    }

    public OkHttpClient getClient() {
        return client;
    }

    public KyApiService getKyApiService() {
        return kyApiService;
    }

    public WyApiService getWyApiService() {
        return wyApiService;
    }

    public OpApiService getOpApiService() {
        return opApiService;
    }

    public VeerApiService getVeerApiService() {
        return veerApiService;
    }

    public KuulaApiService getKuulaApiService() {
        return kuulaApiService;
    }
}
