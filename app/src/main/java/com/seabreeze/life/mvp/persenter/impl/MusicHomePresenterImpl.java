package com.seabreeze.life.mvp.persenter.impl;

import com.robot.seabreeze.log.Log;
import com.seabreeze.life.common.api.KyApiService;
import com.seabreeze.life.common.api.WyApiService;
import com.seabreeze.life.common.base.mvp.BasePresenter;
import com.seabreeze.life.common.base.mvp.BasePresenterImpl;
import com.seabreeze.life.entity.music.BannerListBean;
import com.seabreeze.life.entity.music.MusicBean;
import com.seabreeze.life.entity.music.MusicListBean;
import com.seabreeze.life.mvp.persenter.MusicHomePresenter;
import com.seabreeze.life.mvp.view.MusicHomeView;
import com.seabreeze.life.utils.RxSchedulers;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;

public class MusicHomePresenterImpl extends BasePresenterImpl<MusicHomeView> implements MusicHomePresenter {

    private WyApiService mRetrofitHelper;

    @Inject
    public MusicHomePresenterImpl(WyApiService retrofitHelper) {
        this.mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void getMusicData(int id) {

        Disposable disposable = mRetrofitHelper.getMusicList(id, 20)
                .compose(RxSchedulers.<MusicBean>rxSchedulerHelper())
                .compose(mView.<MusicBean>bindToLife())
                .subscribe(new Consumer<MusicBean>() {
                    @Override
                    public void accept(MusicBean musicBean) throws Exception {
                        mView.onMusicListBeanSuccess(musicBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.onMusicListBeanError(throwable);
                    }
                });
        addSubscrebe(disposable);
    }

    @Override
    public void getBannerList() {
        Disposable disposable = mRetrofitHelper.getBannerList()
                .compose(RxSchedulers.<BannerListBean>rxSchedulerHelper())
                .compose(mView.<BannerListBean>bindToLife())
                .subscribe(new Consumer<BannerListBean>() {
                    @Override
                    public void accept(BannerListBean listBean) throws Exception {
                        mView.onBannerListSuccess(listBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.onBannerListError(throwable);
                    }
                });
        addSubscrebe(disposable);
    }

}
