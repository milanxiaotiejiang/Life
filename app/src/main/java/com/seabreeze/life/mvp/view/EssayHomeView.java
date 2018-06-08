package com.seabreeze.life.mvp.view;

import com.seabreeze.life.common.base.mvp.BaseView;
import com.seabreeze.life.entity.essay.EssayListBean;

import java.util.List;

public interface EssayHomeView extends BaseView {

    void onEssayListBeanSuccess(EssayListBean essayListBean);

    void onEssayListBeanError(Throwable throwable);

}
