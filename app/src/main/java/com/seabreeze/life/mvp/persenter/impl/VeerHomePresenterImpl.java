package com.seabreeze.life.mvp.persenter.impl;

import com.robot.seabreeze.log.Log;
import com.seabreeze.life.common.api.OpApiService;
import com.seabreeze.life.common.api.VeerApiService;
import com.seabreeze.life.common.base.mvp.BasePresenterImpl;
import com.seabreeze.life.entity.essay.EssayListBean;
import com.seabreeze.life.entity.veer.VeerListBean;
import com.seabreeze.life.mvp.persenter.EssayHomePresenter;
import com.seabreeze.life.mvp.persenter.VeerHomePresenter;
import com.seabreeze.life.mvp.view.EssayHomeView;
import com.seabreeze.life.mvp.view.VeerHomeView;
import com.seabreeze.life.utils.RxSchedulers;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;

public class VeerHomePresenterImpl extends BasePresenterImpl<VeerHomeView> implements VeerHomePresenter {

    private VeerApiService mRetrofitHelper;

    @Inject
    public VeerHomePresenterImpl(VeerApiService retrofitHelper) {
        this.mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void getVeerData(int page) {
        Disposable disposable = mRetrofitHelper.getVeer(page)
                .compose(RxSchedulers.<VeerListBean>rxSchedulerHelper())
                .compose(mView.<VeerListBean>bindToLife())
                .subscribe(new Consumer<VeerListBean>() {
                    @Override
                    public void accept(VeerListBean body) throws Exception {
                        mView.onVeerListBeanSuccess(body);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.onVeerListBeanError(throwable);
                    }
                });
        addSubscrebe(disposable);

    }

}
