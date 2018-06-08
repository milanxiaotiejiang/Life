package com.seabreeze.life.mvp.persenter.impl;

import com.robot.seabreeze.log.Log;
import com.seabreeze.life.common.api.KuulaApiService;
import com.seabreeze.life.common.base.mvp.BasePresenterImpl;
import com.seabreeze.life.entity.kuula.KuulaBean;
import com.seabreeze.life.mvp.persenter.PanoramaDetailPresenter;
import com.seabreeze.life.mvp.view.PanoramaDetailView;
import com.seabreeze.life.utils.RxSchedulers;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;

public class PanoramaDetailPresenterImpl extends BasePresenterImpl<PanoramaDetailView> implements PanoramaDetailPresenter {

    private KuulaApiService mRetrofitHelper;

    @Inject
    public PanoramaDetailPresenterImpl(KuulaApiService retrofitHelper) {
        this.mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void getVrCategoryData(String id) {
        Disposable disposable = mRetrofitHelper.getKuulaImage(id)
                .compose(RxSchedulers.<KuulaBean>rxSchedulerHelper())
                .compose(mView.<KuulaBean>bindToLife())
                .subscribe(new Consumer<KuulaBean>() {
                    @Override
                    public void accept(KuulaBean kuulaBean) throws Exception {
                        if (kuulaBean.getStatus() == 0) {
                            KuulaBean.PayloadBean payload = kuulaBean.getPayload();
                            if (payload != null) {
                                mView.onPanoramaDetailSuccess(payload);
                            } else {
                                mView.onPanoramaDetailError(new Throwable("payload is null"));
                            }
                        } else {
                            mView.onPanoramaDetailError(new Throwable("kuulaBean status " + kuulaBean.getStatus()));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.onPanoramaDetailError(throwable);
                    }
                });
        addSubscrebe(disposable);
    }

    @Override
    public void getKuulaComments(String id) {
        Disposable disposable = mRetrofitHelper.getKuulaComments(id)
                .compose(RxSchedulers.<ResponseBody>rxSchedulerHelper())
                .compose(mView.<ResponseBody>bindToLife())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody body) throws Exception {
                        String string = body.string();
                        Log.e(string);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.onPanoramaDetailError(throwable);
                    }
                });
        addSubscrebe(disposable);
    }
}
