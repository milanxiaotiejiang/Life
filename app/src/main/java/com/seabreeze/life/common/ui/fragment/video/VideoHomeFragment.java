package com.seabreeze.life.common.ui.fragment.video;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.seabreeze.life.R;
import com.seabreeze.life.common.adapter.VideoAdapter;
import com.seabreeze.life.common.base.BaseFragment;
import com.seabreeze.life.common.event.MessageEvent;
import com.seabreeze.life.common.ui.activity.MainActivity;
import com.seabreeze.life.entity.video.ItemListBean;
import com.seabreeze.life.entity.video.VideoListBean;
import com.seabreeze.life.mvp.persenter.impl.VideoHomePresenterImpl;
import com.seabreeze.life.mvp.view.VideoHomeView;
import com.seabreeze.life.utils.EventUtils;
import com.seabreeze.life.widget.header.DefaultHeader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class VideoHomeFragment extends BaseFragment<VideoHomePresenterImpl> implements VideoHomeView {

    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    @BindView(R.id.recycler)
    RecyclerView recycler;

    public static VideoHomeFragment newInstance() {
        Bundle bundle = new Bundle();
        VideoHomeFragment videoHomeFragment = new VideoHomeFragment();
        videoHomeFragment.setArguments(bundle);
        return videoHomeFragment;
    }

    private List<ItemListBean> mItemListBeans = new ArrayList<>();

    private String date = "";

    private VideoAdapter videoAdapter;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void loadData() {
        mPresenter.getVideoData(date);
    }

    @Override
    protected int getSuccessLayoutId() {
        return R.layout.video_home_fragment;
    }

    @Override
    protected void initEventAndData() {
        refresh.setProgressViewOffset(false, 100, 200);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                date = "";
                loadData();
            }
        });
        LinearLayoutManager layout = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(layout);

        videoAdapter = new VideoAdapter(mItemListBeans);

        videoAdapter.addHeaderView(new DefaultHeader(mContext));
        videoAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        recycler.setAdapter(videoAdapter);

        videoAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {

                loadData();
            }
        }, recycler);

        videoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startChildFragment(mItemListBeans.get(position));
            }
        });
    }


    private void startChildFragment(ItemListBean itemListBean) {
        EventUtils.sendEvent(new MessageEvent(MainActivity.SEARCH_HIDE, ""));

        VideoDetailFragment fragment = VideoDetailFragment.newInstance(itemListBean.getData());

        startForResult(fragment, VideoDetailFragment.start_requestCode);
    }

    @Override
    public void onVideoListBeanSuccess(VideoListBean videoListBean) {
        if (videoListBean != null) {
            if (refresh.isRefreshing()) {
                refresh.setRefreshing(false);
                mItemListBeans.clear();
            }
            int end = videoListBean.getNextPageUrl().lastIndexOf("&num");
            int start = videoListBean.getNextPageUrl().lastIndexOf("date=");
            date = videoListBean.getNextPageUrl().substring(start + 5, end);

            mItemListBeans.addAll(videoListBean.getItemList());
            videoAdapter.loadMoreComplete();
            setState(LoadResult.success);
        } else {
            setState(LoadResult.empty);
        }
    }

    @Override
    public void onVideoListBeanError(Throwable throwable) {
        if (mItemListBeans != null && mItemListBeans.size() > 0) {
            videoAdapter.loadMoreFail();
        } else {
            setState(LoadResult.error);
        }
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (requestCode == VideoDetailFragment.start_requestCode) {
            EventUtils.sendEvent(new MessageEvent(MainActivity.SEARCH_SHOW, ""));
        }
    }
}
