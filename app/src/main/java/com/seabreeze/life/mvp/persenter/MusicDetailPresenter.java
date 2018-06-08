package com.seabreeze.life.mvp.persenter;

import com.seabreeze.life.common.base.mvp.BasePresenter;
import com.seabreeze.life.mvp.view.MusicDetailView;
import com.seabreeze.life.mvp.view.VideoDetailView;

public interface MusicDetailPresenter extends BasePresenter<MusicDetailView> {

    void getMusicTrack(int id);
}
