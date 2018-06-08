package com.seabreeze.life.mvp.view;

import com.seabreeze.life.common.base.mvp.BaseView;
import com.seabreeze.life.entity.veer.VeerListBean;

import okhttp3.Response;
import okhttp3.ResponseBody;

public interface VeerHomeView extends BaseView {

    void onVeerListBeanSuccess(VeerListBean veerListBean);

    void onVeerListBeanError(Throwable throwable);
}
