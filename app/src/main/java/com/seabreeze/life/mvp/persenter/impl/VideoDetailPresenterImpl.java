package com.seabreeze.life.mvp.persenter.impl;

import com.robot.seabreeze.log.Log;
import com.seabreeze.life.common.api.KyApiService;
import com.seabreeze.life.common.base.mvp.BasePresenterImpl;
import com.seabreeze.life.entity.video.ItemListBean;
import com.seabreeze.life.entity.video.VideoListBean;
import com.seabreeze.life.entity.video.VideoListBean2;
import com.seabreeze.life.mvp.persenter.VideoDetailPresenter;
import com.seabreeze.life.mvp.view.VideoDetailView;
import com.seabreeze.life.utils.RxSchedulers;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class VideoDetailPresenterImpl extends BasePresenterImpl<VideoDetailView> implements VideoDetailPresenter {


    private KyApiService mRetrofitHelper;

    @Inject
    public VideoDetailPresenterImpl(KyApiService retrofitHelper) {
        this.mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void getVideoRelated(int id) {
        Disposable disposable = mRetrofitHelper.getVideoRelated(id, 10)
                .compose(RxSchedulers.<VideoListBean2>rxSchedulerHelper())
                .compose(mView.<VideoListBean2>bindToLife())
                .subscribe(new Consumer<VideoListBean2>() {
                    @Override
                    public void accept(VideoListBean2 videoListBean) throws Exception {

                        mView.onVideoDetailSuccess(videoListBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.onVideoDetailError(throwable);
                    }
                });
        addSubscrebe(disposable);
    }
}