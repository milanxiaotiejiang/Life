package com.seabreeze.life.common.base.mvp;

import org.reactivestreams.Subscription;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BasePresenterImpl<T extends BaseView> implements BasePresenter<T> {

    protected T mView;

    protected CompositeDisposable mDisposable;

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
    }

    protected void clearSubscribe() {
        if (mDisposable != null) {
            mDisposable.clear();
        }
    }

    protected void addSubscrebe(Disposable disposable) {
        if (mDisposable == null) {
            mDisposable = new CompositeDisposable();
        }
        mDisposable.add(disposable);
    }

}
