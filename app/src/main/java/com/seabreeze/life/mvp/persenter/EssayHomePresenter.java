package com.seabreeze.life.mvp.persenter;

import com.seabreeze.life.common.base.mvp.BasePresenter;
import com.seabreeze.life.mvp.view.EssayHomeView;

public interface EssayHomePresenter extends BasePresenter<EssayHomeView> {

    void getEssayData(int id);
}
