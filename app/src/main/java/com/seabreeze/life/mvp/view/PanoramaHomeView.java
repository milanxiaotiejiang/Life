package com.seabreeze.life.mvp.view;

import com.seabreeze.life.common.base.mvp.BaseView;
import com.seabreeze.life.entity.kuula.KuulaListBean;

import okhttp3.ResponseBody;

public interface PanoramaHomeView extends BaseView {

    void onPanoramaListBeanSuccess(KuulaListBean kuulaListBean);

    void onPanoramaListBeanError(Throwable throwable);
}
