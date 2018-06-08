package com.seabreeze.life.mvp.view;

import com.seabreeze.life.common.base.mvp.BaseView;
import com.seabreeze.life.entity.essay.EssayDetailBean;
import com.seabreeze.life.entity.essay.EssayListBean;

import okhttp3.ResponseBody;

public interface EssayDetailView extends BaseView {

    void onEssayDetailSuccess(EssayDetailBean detailBean);

    void onEssayDetailError(Throwable throwable);
}
