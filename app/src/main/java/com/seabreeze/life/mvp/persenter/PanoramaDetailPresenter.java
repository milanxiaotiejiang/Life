package com.seabreeze.life.mvp.persenter;

import com.seabreeze.life.common.base.mvp.BasePresenter;
import com.seabreeze.life.mvp.view.PanoramaDetailView;

public interface PanoramaDetailPresenter extends BasePresenter<PanoramaDetailView> {

    void getVrCategoryData(String id);

    void getKuulaComments(String id);
}
