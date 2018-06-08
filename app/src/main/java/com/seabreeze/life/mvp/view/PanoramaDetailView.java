package com.seabreeze.life.mvp.view;

import com.seabreeze.life.common.base.mvp.BaseView;
import com.seabreeze.life.entity.kuula.KuulaBean;

import okhttp3.ResponseBody;

public interface PanoramaDetailView extends BaseView {

    void onPanoramaDetailSuccess(KuulaBean.PayloadBean payload);

    void onPanoramaDetailError(Throwable throwable);
}
