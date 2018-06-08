package com.seabreeze.life.mvp.persenter;

import com.seabreeze.life.common.base.mvp.BasePresenter;
import com.seabreeze.life.mvp.view.VideoDetailView;

public interface VideoDetailPresenter extends BasePresenter<VideoDetailView> {

    void getVideoRelated(int id );
}
