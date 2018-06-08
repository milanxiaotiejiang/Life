package com.seabreeze.life.common.ui.fragment.misic;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.seabreeze.life.R;
import com.seabreeze.life.common.adapter.MusicAdapter;
import com.seabreeze.life.common.banner.RecommendController;
import com.seabreeze.life.common.base.BaseFragment;
import com.seabreeze.life.common.event.MessageEvent;
import com.seabreeze.life.common.ui.activity.MainActivity;
import com.seabreeze.life.common.ui.fragment.video.VideoDetailFragment;
import com.seabreeze.life.entity.music.BannerListBean;
import com.seabreeze.life.entity.music.DataBean;
import com.seabreeze.life.entity.music.MusicBean;
import com.seabreeze.life.mvp.persenter.impl.MusicHomePresenterImpl;
import com.seabreeze.life.mvp.view.MusicHomeView;
import com.seabreeze.life.utils.EventUtils;
import com.seabreeze.life.widget.header.DefaultHeader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MusicHomeFragment extends BaseFragment<MusicHomePresenterImpl> implements MusicHomeView {

    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    @BindView(R.id.recycler)
    RecyclerView recycler;

    public static MusicHomeFragment newInstance() {
        Bundle bundle = new Bundle();
        MusicHomeFragment musicHomeFragment = new MusicHomeFragment();
        musicHomeFragment.setArguments(bundle);
        return musicHomeFragment;
    }

    private List<DataBean> mDataBeanList = new ArrayList<>();

    private MusicAdapter musicAdapter;

    private int pager = 1;

    private int totalPage;

    private View bannerView;

    private View defaultView;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void loadData() {
        mPresenter.getMusicData(pager);
        mPresenter.getBannerList();
    }

    @Override
    protected int getSuccessLayoutId() {
        return R.layout.music_home_fragment;
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
        LinearLayoutManager layout = new GridLayoutManager(getActivity(), 3);
        recycler.setLayoutManager(layout);

        defaultView = new DefaultHeader(mContext);
        musicAdapter = new MusicAdapter(mDataBeanList);

        musicAdapter.addHeaderView(defaultView);

        musicAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        recycler.setAdapter(musicAdapter);

        musicAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                pager++;
                if (pager < totalPage) {
                    loadData();
                }
            }
        }, recycler);
        musicAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startChildFragment(mDataBeanList.get(position));
            }
        });
    }

    private void startChildFragment(DataBean dataBean) {
        EventUtils.sendEvent(new MessageEvent(MainActivity.SEARCH_HIDE, ""));

        MusicDetailFragment fragment = MusicDetailFragment.newInstance(dataBean);

        startForResult(fragment, MusicDetailFragment.start_requestCode);
    }

    @Override
    public void onMusicListBeanSuccess(MusicBean musicBean) {
        if (musicBean != null && musicBean.getCode() == 1) {
            if (refresh.isRefreshing()) {
                refresh.setRefreshing(false);
                mDataBeanList.clear();
            }

            totalPage = musicBean.getPage().getTotal_page();

            List<DataBean> musicBeanData = musicBean.getData();
            mDataBeanList.addAll(musicBeanData);
            musicAdapter.loadMoreComplete();
            setState(LoadResult.success);

        } else {
            setState(LoadResult.empty);
        }
    }

    @Override
    public void onMusicListBeanError(Throwable throwable) {
        setState(LoadResult.error);
    }

    @Override
    public void onBannerListSuccess(final BannerListBean bannerListBean) {
        musicAdapter.removeHeaderView(defaultView);
        musicAdapter.addHeaderView(defaultView);
        if (bannerListBean.getCode() == 1 && bannerListBean.getPage().getTotal_count() > 0) {
            musicAdapter.removeHeaderView(bannerView);
            List<BannerListBean.DataBean> data = bannerListBean.getData();

            RecommendController controller = new RecommendController(mContext);
            controller.setData(data);
            bannerView = controller.getContentView();
            musicAdapter.addHeaderView(bannerView, 1);
        }
    }


    @Override
    public void onBannerListError(Throwable throwable) {
        musicAdapter.removeAllHeaderView();
        musicAdapter.addHeaderView(defaultView);
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (requestCode == VideoDetailFragment.start_requestCode) {
            EventUtils.sendEvent(new MessageEvent(MainActivity.SEARCH_SHOW, ""));
        }
    }
}
