package com.seabreeze.life.mvp.persenter;

import com.seabreeze.life.common.base.mvp.BasePresenter;
import com.seabreeze.life.mvp.view.VideoHomeView;

public interface VideoHomePresenter extends BasePresenter<VideoHomeView> {

    void getVideoData(String date);
}
