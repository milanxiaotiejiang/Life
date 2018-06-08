package com.seabreeze.life.common.ui.fragment.essay;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.seabreeze.life.R;
import com.seabreeze.life.common.adapter.EssayAdapter;
import com.seabreeze.life.common.base.BaseFragment;
import com.seabreeze.life.common.event.MessageEvent;
import com.seabreeze.life.common.ui.activity.MainActivity;
import com.seabreeze.life.common.ui.fragment.video.VideoDetailFragment;
import com.seabreeze.life.entity.essay.DataBean;
import com.seabreeze.life.entity.essay.EssayListBean;
import com.seabreeze.life.mvp.persenter.impl.EssayHomePresenterImpl;
import com.seabreeze.life.mvp.view.EssayHomeView;
import com.seabreeze.life.utils.EventUtils;
import com.seabreeze.life.widget.header.DefaultHeader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class EssayHomeFragment extends BaseFragment<EssayHomePresenterImpl> implements EssayHomeView {

    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    @BindView(R.id.recycler)
    RecyclerView recycler;

    public static EssayHomeFragment newInstance() {
        Bundle bundle = new Bundle();
        EssayHomeFragment essayHomeFragment = new EssayHomeFragment();
        essayHomeFragment.setArguments(bundle);
        return essayHomeFragment;
    }

    private List<DataBean> mDataBean = new ArrayList<>();

    private EssayAdapter essayAdapter;

    private int pager = 0;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void loadData() {
        mPresenter.getEssayData(pager);
    }

    @Override
    protected int getSuccessLayoutId() {
        return R.layout.essay_home_fragment;
    }

    @Override
    protected void initEventAndData() {
        refresh.setProgressViewOffset(false, 100, 200);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pager = 0;
                loadData();
            }
        });
        LinearLayoutManager layout = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(layout);

        essayAdapter = new EssayAdapter(mDataBean);

        essayAdapter.addHeaderView(new DefaultHeader(mContext));
        essayAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        recycler.setAdapter(essayAdapter);

        essayAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {

                loadData();
            }
        }, recycler);

        essayAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startChildFragment(mDataBean.get(position));
            }
        });
    }

    private void startChildFragment(DataBean dataBean) {
        EventUtils.sendEvent(new MessageEvent(MainActivity.SEARCH_HIDE, ""));

        EssayDetailFragment fragment = EssayDetailFragment.newInstance(dataBean);

        startForResult(fragment, EssayDetailFragment.start_requestCode);
    }

    @Override
    public void onEssayListBeanSuccess(EssayListBean essayListBean) {
        if (essayListBean != null && essayListBean.getRes() == 0) {
            if (refresh.isRefreshing()) {
                refresh.setRefreshing(false);
                mDataBean.clear();
            }

            List<DataBean> essayListBeanData = essayListBean.getData();
            DataBean lastDataBean = essayListBeanData.get(essayListBeanData.size() - 1);
            pager = lastDataBean.getId();

            mDataBean.addAll(essayListBeanData);
            essayAdapter.loadMoreComplete();
            setState(LoadResult.success);
        } else {
            setState(LoadResult.empty);
        }
    }

    @Override
    public void onEssayListBeanError(Throwable throwable) {
        setState(LoadResult.error);
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (requestCode == VideoDetailFragment.start_requestCode) {
            EventUtils.sendEvent(new MessageEvent(MainActivity.SEARCH_SHOW, ""));
        }
    }

}
