package com.seabreeze.life.mvp.persenter.impl;

import com.seabreeze.life.common.api.KuulaApiService;
import com.seabreeze.life.common.api.KyApiService;
import com.seabreeze.life.common.base.mvp.BasePresenterImpl;
import com.seabreeze.life.entity.kuula.KuulaListBean;
import com.seabreeze.life.mvp.persenter.PanoramaHomePresenter;
import com.seabreeze.life.mvp.view.PanoramaHomeView;
import com.seabreeze.life.utils.RxSchedulers;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;

public class PanoramaHomePresenterImpl extends BasePresenterImpl<PanoramaHomeView> implements PanoramaHomePresenter {

    private KuulaApiService mRetrofitHelper;

    @Inject
    public PanoramaHomePresenterImpl(KuulaApiService retrofitHelper) {
        this.mRetrofitHelper = retrofitHelper;
    }


    @Override
    public void getPanoramaData(int offset) {
        Disposable disposable = mRetrofitHelper.getKuulaList(offset)
                .compose(RxSchedulers.<KuulaListBean>rxSchedulerHelper())
                .compose(mView.<KuulaListBean>bindToLife())
                .subscribe(new Consumer<KuulaListBean>() {
                    @Override
                    public void accept(KuulaListBean kuulaListBean) throws Exception {
                        mView.onPanoramaListBeanSuccess(kuulaListBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.onPanoramaListBeanError(throwable);
                    }
                });
        addSubscrebe(disposable);
    }
}
