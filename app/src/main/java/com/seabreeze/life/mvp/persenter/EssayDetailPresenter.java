package com.seabreeze.life.mvp.persenter;

import com.seabreeze.life.common.base.mvp.BasePresenter;
import com.seabreeze.life.mvp.view.EssayDetailView;

public interface EssayDetailPresenter extends BasePresenter<EssayDetailView> {

    void getEssayDetailData(int id, int sourceId);

}
