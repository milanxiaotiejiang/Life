package com.seabreeze.life.common.base.mvp;

import com.trello.rxlifecycle2.LifecycleTransformer;

public interface BaseView {

    void showMsg(String msg);

    //绑定生命周期
    <T> LifecycleTransformer<T> bindToLife();
}
