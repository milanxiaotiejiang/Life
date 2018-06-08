package com.seabreeze.life.mvp.persenter.impl;

import com.seabreeze.life.common.api.OpApiService;
import com.seabreeze.life.common.base.mvp.BasePresenterImpl;
import com.seabreeze.life.entity.essay.EssayListBean;
import com.seabreeze.life.mvp.persenter.EssayHomePresenter;
import com.seabreeze.life.mvp.view.EssayHomeView;
import com.seabreeze.life.utils.RxSchedulers;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;

public class EssayHomePresenterImpl extends BasePresenterImpl<EssayHomeView> implements EssayHomePresenter {

    private OpApiService mRetrofitHelper;

    @Inject
    public EssayHomePresenterImpl(OpApiService retrofitHelper) {
        this.mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void getEssayData(int id) {
        Disposable disposable = mRetrofitHelper.getEssayList(id)
                .compose(RxSchedulers.<EssayListBean>rxSchedulerHelper())
                .compose(mView.<EssayListBean>bindToLife())
                .subscribe(new Consumer<EssayListBean>() {
                    @Override
                    public void accept(EssayListBean essayListBean) throws Exception {
                        mView.onEssayListBeanSuccess(essayListBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.onEssayListBeanError(throwable);
                    }
                });
        addSubscrebe(disposable);
    }
}
