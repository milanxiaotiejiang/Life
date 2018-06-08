package com.seabreeze.life.common.base.mvp;

public interface BasePresenter<T extends BaseView> {

    void attachView(T view);

    void detachView();
}
