package com.seabreeze.life.mvp.view;

import com.seabreeze.life.common.base.mvp.BaseView;
import com.seabreeze.life.entity.video.VideoListBean;

public interface VideoHomeView extends BaseView {

    void onVideoListBeanSuccess(VideoListBean videoListBean);

    void onVideoListBeanError(Throwable throwable);
}
