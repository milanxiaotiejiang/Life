package com.seabreeze.life.mvp.persenter;

import com.seabreeze.life.common.base.mvp.BasePresenter;
import com.seabreeze.life.mvp.view.MusicHomeView;

public interface MusicHomePresenter extends BasePresenter<MusicHomeView> {

    void getMusicData(int id);

    void getBannerList();
}
