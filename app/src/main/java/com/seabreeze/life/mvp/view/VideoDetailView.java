package com.seabreeze.life.mvp.view;

import com.seabreeze.life.common.base.mvp.BaseView;
import com.seabreeze.life.entity.video.VideoListBean;
import com.seabreeze.life.entity.video.VideoListBean2;

public interface VideoDetailView extends BaseView {

    void onVideoDetailSuccess(VideoListBean2 videoListBean);

    void onVideoDetailError(Throwable throwable);
}
