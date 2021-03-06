package com.seabreeze.life.mvp.persenter;

import com.seabreeze.life.common.base.mvp.BasePresenter;
import com.seabreeze.life.mvp.view.EssayHomeView;
import com.seabreeze.life.mvp.view.VeerHomeView;

public interface VeerHomePresenter extends BasePresenter<VeerHomeView> {

    void getVeerData(int page);
}
