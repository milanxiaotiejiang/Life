package com.seabreeze.life.common.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.seabreeze.life.App;
import com.seabreeze.life.R;
import com.seabreeze.life.common.base.mvp.BasePresenter;
import com.seabreeze.life.common.base.mvp.BaseView;
import com.seabreeze.life.common.base.rx.RxSupportFragment;
import com.seabreeze.life.di.component.DaggerFragmentComponent;
import com.seabreeze.life.di.component.FragmentComponent;
import com.seabreeze.life.di.module.FragmentModule;
import com.trello.rxlifecycle2.LifecycleTransformer;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<T extends BasePresenter> extends RxSupportFragment implements BaseView {

    @Inject
    protected T mPresenter;

    protected FrameLayout mView;

    protected Activity mActivity;

    protected Context mContext;

    private Unbinder mUnBinder;

    protected boolean isInited = false;

    protected Handler mHandler = new Handler();

    private int state = STATE_EMPTY;

    //五种状态
    public final static int STATE_SUCCESS = 0;
    public final static int STATE_LOADING = 1;
    public final static int STATE_EMPTY = 2;
    public final static int STATE_ERROR = 3;

    //四种界面
    private View loadingView;
    private View errorView;
    private View emptyView;
    private View successView;


    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        mContext = context;
        super.onAttach(context);
    }

    /**
     * 将四种状态添加到帧布局中，根据状态显示不同的界面
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = new FrameLayout(mContext);
            if (errorView == null) {
                errorView = View.inflate(mContext, R.layout.loading_error_page, null);
                mView.addView(errorView, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
                errorView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showStateView();
                    }
                });
            }
            if (emptyView == null) {
                emptyView = View.inflate(mContext, R.layout.empty_view, null);
                mView.addView(emptyView, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
                emptyView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showStateView();
                    }
                });
            }
            if (loadingView == null) {
                loadingView = View.inflate(mContext, R.layout.loading_page, null);
                mView.addView(loadingView, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
            }
            initInject();
        }
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mPresenter != null)
            mPresenter.attachView(this);

        if (successView == null) {
            successView = View.inflate(mContext, getSuccessLayoutId(), null);
        }
        mUnBinder = ButterKnife.bind(this, successView);

        if (!isHidden()) {
            isInited = true;

            initEventAndData();
            showStateView();
        }
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

    public void showStateView() {
        if (state == STATE_ERROR || state == STATE_EMPTY) {
            state = STATE_LOADING;
        }
        if (state == STATE_LOADING) {
            loadData();
        }
        showPager();
    }

    /**
     * 根据不同的状态显示不同的布局
     */
    private void showPager() {
        if (loadingView != null) {
            loadingView.setVisibility(state == STATE_LOADING ? View.VISIBLE : View.GONE);
        }
        if (errorView != null) {
            errorView.setVisibility(state == STATE_ERROR ? View.VISIBLE : View.GONE);
        }
        if (emptyView != null) {
            emptyView.setVisibility(state == STATE_EMPTY ? View.VISIBLE : View.GONE);
        }
        if (state == STATE_SUCCESS && successView != null) {
            ViewParent parent = successView.getParent();
            if (parent != null && parent instanceof ViewGroup) {
                ViewGroup group = (ViewGroup) parent;
                group.removeView(successView);
            }
            mView.addView(successView, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        }

        if (successView != null) {
            successView.setVisibility(state == STATE_SUCCESS ? View.VISIBLE : View.GONE);
        }
    }


    /**
     * 设置状态
     *
     * @param result
     */
    protected void setState(LoadResult result) {
        state = result.getValue();
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showPager();
            }
        });

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

    @Override
    public void showMsg(String msg) {
        showToast(msg);
    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.<T>bindToLifecycle();
    }

    protected abstract void initInject();

    protected abstract void loadData();

    protected abstract int getSuccessLayoutId();

    protected abstract void initEventAndData();

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


    /**
     * 请求结果的枚举
     */
    public enum LoadResult {
        error(STATE_ERROR), success(STATE_SUCCESS), empty(STATE_EMPTY);

        int value;

        LoadResult(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
