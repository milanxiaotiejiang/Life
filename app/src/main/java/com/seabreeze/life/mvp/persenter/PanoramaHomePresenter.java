package com.seabreeze.life.mvp.persenter;

import com.seabreeze.life.common.base.mvp.BasePresenter;
import com.seabreeze.life.mvp.view.PanoramaHomeView;
import com.seabreeze.life.mvp.view.VeerHomeView;

public interface PanoramaHomePresenter extends BasePresenter<PanoramaHomeView> {

    void getPanoramaData(int offset);
}
