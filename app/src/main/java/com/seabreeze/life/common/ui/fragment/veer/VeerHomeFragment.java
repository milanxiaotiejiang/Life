package com.seabreeze.life.common.ui.fragment.veer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.seabreeze.life.R;
import com.seabreeze.life.common.adapter.VeerAdapter;
import com.seabreeze.life.common.base.BaseFragment;
import com.seabreeze.life.common.image.ImageLoader;
import com.seabreeze.life.common.ui.activity.VrVideoActivity;
import com.seabreeze.life.entity.veer.DataBean;
import com.seabreeze.life.entity.veer.PaginationBean;
import com.seabreeze.life.entity.veer.VeerListBean;
import com.seabreeze.life.mvp.persenter.impl.VeerHomePresenterImpl;
import com.seabreeze.life.mvp.view.VeerHomeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class VeerHomeFragment extends BaseFragment<VeerHomePresenterImpl> implements VeerHomeView {

    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    @BindView(R.id.recycler)
    RecyclerView recycler;

    public static VeerHomeFragment newInstance() {
        Bundle bundle = new Bundle();
        VeerHomeFragment veerHomeFragment = new VeerHomeFragment();
        veerHomeFragment.setArguments(bundle);
        return veerHomeFragment;
    }

    private List<DataBean> mDataBeanList = new ArrayList<>();

    private VeerAdapter veerAdapter;

    private int page = 1;
    private int totalPages;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void loadData() {
        mPresenter.getVeerData(page);
    }

    @Override
    protected int getSuccessLayoutId() {
        return R.layout.veer_home_fragment;
    }

    @Override
    protected void initEventAndData() {
        refresh.setProgressViewOffset(false, 100, 200);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                loadData();
            }
        });
        LinearLayoutManager layout = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(layout);

        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(recycler);

        recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) { // 滚动静止时才加载图片资源，极大提升流畅度
                    ImageLoader.getInstance().resumeRequests(mContext);
                } else {
                    ImageLoader.getInstance().pauseRequests(mContext);
                }
                super.onScrollStateChanged(recyclerView, newState);
            }
        });


        veerAdapter = new VeerAdapter(mDataBeanList);

        veerAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        recycler.setAdapter(veerAdapter);

        veerAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                if (page < totalPages) {
                    loadData();
                }
            }
        }, recycler);
        veerAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startChildFragment(mDataBeanList.get(position));
                showMsg(position + "'");
            }
        });
        veerAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                startChildFragment(mDataBeanList.get(position));

            }
        });
    }

    private void startChildFragment(DataBean dataBean) {
        Intent intent = new Intent(mActivity, VrVideoActivity.class);
        intent.putExtra(VrVideoActivity.VEER_DATA, dataBean.getUrls());
        startActivity(intent);
    }

    @Override
    public void onVeerListBeanSuccess(VeerListBean veerListBean) {
        if (veerListBean != null && veerListBean.getCode() == 200) {
            if (refresh.isRefreshing()) {
                refresh.setRefreshing(false);
                mDataBeanList.clear();
            }

            PaginationBean pagination = veerListBean.getPagination();
            totalPages = pagination.getTotal_pages();

            mDataBeanList.addAll(veerListBean.getData());
            veerAdapter.loadMoreComplete();
            setState(LoadResult.success);
        } else {
            setState(LoadResult.empty);
        }
    }

    @Override
    public void onVeerListBeanError(Throwable throwable) {
        setState(LoadResult.error);
    }
}
