package com.seabreeze.life.mvp.persenter.impl;

import com.seabreeze.life.common.api.OpApiService;
import com.seabreeze.life.common.base.mvp.BasePresenter;
import com.seabreeze.life.common.base.mvp.BasePresenterImpl;
import com.seabreeze.life.entity.essay.EssayDetailBean;
import com.seabreeze.life.mvp.persenter.EssayDetailPresenter;
import com.seabreeze.life.mvp.view.EssayDetailView;
import com.seabreeze.life.utils.RxSchedulers;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;

public class EssayDetailPresenterImpl extends BasePresenterImpl<EssayDetailView> implements EssayDetailPresenter {

    private OpApiService mRetrofitHelper;

    @Inject
    public EssayDetailPresenterImpl(OpApiService retrofitHelper) {
        this.mRetrofitHelper = retrofitHelper;
    }


    @Override
    public void getEssayDetailData(int id, int sourceId) {
        Disposable subscribe = mRetrofitHelper.getEssayDetail(id, sourceId)
                .compose(RxSchedulers.<EssayDetailBean>rxSchedulerHelper())
                .compose(mView.<EssayDetailBean>bindToLife())
                .subscribe(new Consumer<EssayDetailBean>() {
                    @Override
                    public void accept(EssayDetailBean detailBean) throws Exception {
                        mView.onEssayDetailSuccess(detailBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.onEssayDetailError(throwable);
                    }
                });
        addSubscrebe(subscribe);
    }

}
