package com.seabreeze.life.mvp.persenter.impl;

import com.seabreeze.life.common.api.KyApiService;
import com.seabreeze.life.common.api.WyApiService;
import com.seabreeze.life.common.base.mvp.BasePresenterImpl;
import com.seabreeze.life.entity.music.TrackBean;
import com.seabreeze.life.entity.video.VideoListBean2;
import com.seabreeze.life.mvp.persenter.MusicDetailPresenter;
import com.seabreeze.life.mvp.persenter.VideoDetailPresenter;
import com.seabreeze.life.mvp.view.MusicDetailView;
import com.seabreeze.life.mvp.view.VideoDetailView;
import com.seabreeze.life.utils.RxSchedulers;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;

public class MusicDetailPresenterImpl extends BasePresenterImpl<MusicDetailView> implements MusicDetailPresenter {


    private WyApiService mRetrofitHelper;

    @Inject
    public MusicDetailPresenterImpl(WyApiService retrofitHelper) {
        this.mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void getMusicTrack(int id) {
        Disposable disposable = mRetrofitHelper.getTrack(1, id)
                .compose(RxSchedulers.<List<TrackBean>>rxSchedulerHelper())
                .compose(mView.<List<TrackBean>>bindToLife())
                .subscribe(new Consumer<List<TrackBean>>() {
                    @Override
                    public void accept(List<TrackBean> trackBeans) throws Exception {
                        mView.onMusicTrackSuccess(trackBeans);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.onMusicTrackError(throwable);
                    }
                });
        addSubscrebe(disposable);
    }
}