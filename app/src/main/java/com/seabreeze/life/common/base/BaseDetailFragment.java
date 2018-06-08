package com.seabreeze.life.common.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.seabreeze.life.App;
import com.seabreeze.life.R;
import com.seabreeze.life.common.base.mvp.BasePresenter;
import com.seabreeze.life.common.base.mvp.BaseView;
import com.seabreeze.life.common.base.rx.RxSupportFragment;
import com.seabreeze.life.common.event.MessageEvent;
import com.seabreeze.life.common.ui.activity.MainActivity;
import com.seabreeze.life.di.component.DaggerFragmentComponent;
import com.seabreeze.life.di.component.FragmentComponent;
import com.seabreeze.life.di.module.FragmentModule;
import com.seabreeze.life.utils.EventUtils;
import com.trello.rxlifecycle2.LifecycleTransformer;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseDetailFragment<D, T extends BasePresenter> extends RxSupportFragment implements BaseView {

    protected static final String ARG_ITEM = "arg_item";

    public static final int start_requestCode = 0x122;

    protected View mView;
    protected Activity mActivity;
    protected Context mContext;
    private Unbinder mUnBinder;

    protected Handler mHandler = new Handler();

    protected D mDataBean;

    @Inject
    protected T mPresenter;

    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        mContext = context;
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataBean = getArguments().getParcelable(ARG_ITEM);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), null);
        mUnBinder = ButterKnife.bind(this, mView);
        initInject();
        if (mPresenter != null)
            mPresenter.attachView(this);
        initEventAndData();
        return mView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnBinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.detachView();
    }

    @Override
    public void showMsg(String msg) {
showToast(msg);
    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.bindToLifecycle();
    }

    protected void initToolbarNav(Toolbar toolbar) {
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });
        toolbar.inflateMenu(R.menu.back_menu);
    }

    protected FragmentComponent getFragmentComponent() {

        return DaggerFragmentComponent.builder()
                .appComponent(App.getInstance().getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    protected FragmentModule getFragmentModule() {
        return new FragmentModule(this);
    }

    /**
     * 显示toast
     *
     * @param resId
     */
    public void showToast(final int resId) {
        showToast(getString(resId));
    }

    /**
     * 显示toast
     *
     * @param resStr
     * @return Toast对象，便于控制toast的显示与关闭
     */
    public void showToast(final String resStr) {

        if (TextUtils.isEmpty(resStr)) {
            return;
        }
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast toast = Toast.makeText(getActivity(), resStr, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    protected abstract int getLayoutId();

    protected abstract void initInject();

    protected abstract void initEventAndData();
}
