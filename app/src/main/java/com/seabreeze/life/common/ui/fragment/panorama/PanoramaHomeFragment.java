package com.seabreeze.life.common.ui.fragment.panorama;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.seabreeze.life.R;
import com.seabreeze.life.common.adapter.KuulaAdapter;
import com.seabreeze.life.common.base.BaseFragment;
import com.seabreeze.life.common.event.MessageEvent;
import com.seabreeze.life.common.ui.activity.MainActivity;
import com.seabreeze.life.common.ui.activity.VrBitmapActivity;
import com.seabreeze.life.common.ui.fragment.video.VideoDetailFragment;
import com.seabreeze.life.entity.kuula.KuulaListBean;
import com.seabreeze.life.entity.kuula.data.PageBean;
import com.seabreeze.life.entity.kuula.data.PostsBean;
import com.seabreeze.life.mvp.persenter.impl.PanoramaHomePresenterImpl;
import com.seabreeze.life.mvp.view.PanoramaHomeView;
import com.seabreeze.life.utils.EventUtils;
import com.seabreeze.life.utils.JumpUtils;
import com.seabreeze.life.widget.header.DefaultHeader;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class PanoramaHomeFragment extends BaseFragment<PanoramaHomePresenterImpl> implements PanoramaHomeView {

    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    @BindView(R.id.recycler)
    RecyclerView recycler;

    public static PanoramaHomeFragment newInstance() {
        Bundle bundle = new Bundle();
        PanoramaHomeFragment panoramaHomeFragment = new PanoramaHomeFragment();
        panoramaHomeFragment.setArguments(bundle);
        return panoramaHomeFragment;
    }

    private List<PostsBean> mPostBeanList = new ArrayList<>();

    private KuulaAdapter kuulaAdapter;

    private int pager = 0;

    private int totalPages;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void loadData() {
        mPresenter.getPanoramaData(pager);
    }

    @Override
    protected int getSuccessLayoutId() {
        return R.layout.panorama_home_fragment;
    }

    @Override
    protected void initEventAndData() {
        refresh.setProgressViewOffset(false, 100, 200);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pager = 1;
                loadData();
            }
        });
        LinearLayoutManager layout = new GridLayoutManager(getActivity(), 2);
        recycler.setLayoutManager(layout);

        kuulaAdapter = new KuulaAdapter(mPostBeanList);

        kuulaAdapter.addHeaderView(new DefaultHeader(mContext));

        kuulaAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        recycler.setAdapter(kuulaAdapter);

        kuulaAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                pager++;
                if (pager < totalPages) {
                    loadData();
                }
            }
        }, recycler);

        kuulaAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startChildFragment(mPostBeanList.get(position));
            }
        });
    }

    private void startChildFragment(PostsBean dataBean) {
//        EventUtils.sendEvent(new MessageEvent(MainActivity.HIDE, ""));

        Intent intent = new Intent(mActivity, VrBitmapActivity.class);
        intent.putExtra(VrBitmapActivity.POSTS_ID, dataBean.getId());
        startActivity(intent);
    }

    @Override
    public void onPanoramaListBeanSuccess(KuulaListBean kuulaListBean) {
        if (kuulaListBean != null && kuulaListBean.getStatus() == 0) {

            if (refresh.isRefreshing()) {
                refresh.setRefreshing(false);
                mPostBeanList.clear();
            }

            List<PostsBean> posts = kuulaListBean.getPayload().getPosts();
            PageBean page = kuulaListBean.getPayload().getPage();

            totalPages = page.getTotal();

            mPostBeanList.addAll(posts);
            kuulaAdapter.loadMoreComplete();
            setState(LoadResult.success);
        } else {
            setState(LoadResult.empty);
        }
    }

    @Override
    public void onPanoramaListBeanError(Throwable throwable) {
        setState(LoadResult.error);
    }

}
