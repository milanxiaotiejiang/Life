package com.seabreeze.life.mvp.persenter.impl;

import com.robot.seabreeze.log.Log;
import com.seabreeze.life.common.api.KyApiService;
import com.seabreeze.life.common.base.mvp.BasePresenterImpl;
import com.seabreeze.life.entity.video.ItemListBean;
import com.seabreeze.life.utils.RxSchedulers;
import com.seabreeze.life.entity.video.VideoListBean;
import com.seabreeze.life.mvp.persenter.VideoHomePresenter;
import com.seabreeze.life.mvp.view.VideoHomeView;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class VideoHomePresenterImpl extends BasePresenterImpl<VideoHomeView> implements VideoHomePresenter {


    private KyApiService mRetrofitHelper;

    @Inject
    public VideoHomePresenterImpl(KyApiService retrofitHelper) {
        this.mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void getVideoData(String date) {
        Disposable disposable = mRetrofitHelper.getVideoList(date)
                .compose(RxSchedulers.<VideoListBean>rxSchedulerHelper())
                .compose(mView.<VideoListBean>bindToLife())
                .map(new Function<VideoListBean, VideoListBean>() {
                    @Override
                    public VideoListBean apply(VideoListBean videoListBean) throws Exception {

                        List<ItemListBean> itemListBeans = videoListBean.getItemList();
                        Iterator<ItemListBean> iterator = itemListBeans.iterator();
                        while (iterator.hasNext()) {
                            ItemListBean itemListBean = iterator.next();
                            if (itemListBean.getType().equals(ItemListBean.TYPE_VIDEO)) {
                                Log.e(itemListBean.getData().getTitle());
                            } else {
                                iterator.remove();
                            }
                        }
                        videoListBean.setCount(itemListBeans.size());
                        return videoListBean;
                    }
                })
                .subscribe(new Consumer<VideoListBean>() {
                    @Override
                    public void accept(VideoListBean videoListBean) throws Exception {

                        mView.onVideoListBeanSuccess(videoListBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.onVideoListBeanError(throwable);
                    }
                });
        addSubscrebe(disposable);
    }
}
