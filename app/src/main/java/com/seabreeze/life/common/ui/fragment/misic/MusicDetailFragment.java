package com.seabreeze.life.common.ui.fragment.misic;


import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.konifar.fab_transformation.FabTransformation;
import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.robot.seabreeze.log.Log;
import com.seabreeze.life.R;
import com.seabreeze.life.common.adapter.MusicTrackAdapter;
import com.seabreeze.life.common.base.BaseDetailFragment;
import com.seabreeze.life.common.base.mvp.BasePresenterImpl;
import com.seabreeze.life.common.image.ImageLoader;
import com.seabreeze.life.entity.music.DataBean;
import com.seabreeze.life.entity.music.TrackBean;
import com.seabreeze.life.mvp.persenter.impl.MusicDetailPresenterImpl;
import com.seabreeze.life.mvp.view.MusicDetailView;
import com.seabreeze.life.utils.JumpUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.ResponseBody;

public class MusicDetailFragment extends BaseDetailFragment<DataBean, MusicDetailPresenterImpl> implements MusicDetailView {

    @BindView(R.id.img_detail)
    ImageView imgDetail;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.recycler)
    RecyclerView recycler;

    public static MusicDetailFragment newInstance(DataBean dataBean) {
        Bundle args = new Bundle();
        args.putParcelable(ARG_ITEM, dataBean);
        MusicDetailFragment fragment = new MusicDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private List<TrackBean> mTrackBeanList;

    private int color = 0xffffcc00;

    @Override
    protected int getLayoutId() {
        return R.layout.music_home_detail_fragment;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {

        showPager();

        initColor();

        fab.setEnabled(false);
        mPresenter.getMusicTrack(mDataBean.getId());
    }

    private void showPager() {
        toolbar.setTitle("");
        initToolbarNav(toolbar);
        ImageLoader.getInstance().loadImage(mContext, imgDetail, mDataBean.getHeadimg());

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fab.getVisibility() == View.VISIBLE) {
                    FabTransformation.with(fab).setListener(new FabTransformation.OnTransformListener() {
                        @Override
                        public void onStartTransform() {
                        }

                        @Override
                        public void onEndTransform() {
                            JumpUtils.goToMusicPlayer(getActivity(), imgDetail, mDataBean, mTrackBeanList);
                        }
                    }).transformTo(imgDetail);
                }
            }
        });
    }

    private void initColor() {
        Glide.with(mContext)
                .asBitmap()
                .load(mDataBean.getHeadimg())
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                        Palette.from(resource).generate(new Palette.PaletteAsyncListener() {
                            @Override
                            public void onGenerated(@NonNull Palette palette) {
                                if (palette != null) {
                                    Palette.Swatch darkMutedSwatch = palette.getDarkMutedSwatch();
                                    if (darkMutedSwatch != null) {
                                        color = darkMutedSwatch.getRgb();
                                    }
                                }
                                if (toolbarLayout != null) {
                                    toolbarLayout.setContentScrimColor(color);
                                    int[][] states = new int[][]{new int[0]};
                                    int[] colors = new int[]{color};
                                    fab.setBackgroundTintList(new ColorStateList(states, colors));
                                }
                            }
                        });
                    }
                });

    }

    @Override
    public void onResume() {
        super.onResume();
        FabTransformation.with(fab).setListener(new FabTransformation.OnTransformListener() {
            @Override
            public void onStartTransform() {

            }

            @Override
            public void onEndTransform() {
                if (imgDetail.getVisibility() == View.INVISIBLE) {
                    imgDetail.setVisibility(View.VISIBLE);
                }
            }
        }).transformFrom(imgDetail);
    }


    @Override
    public void onMusicTrackSuccess(List<TrackBean> trackBeanList) {
        recycler.setVisibility(View.VISIBLE);
        LinearLayoutManager layout = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(layout);
        MusicTrackAdapter musicTrackAdapter = new MusicTrackAdapter(trackBeanList);
        musicTrackAdapter.addHeaderView(initHeadView());
        musicTrackAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        recycler.setAdapter(musicTrackAdapter);

        fab.setEnabled(true);
        mTrackBeanList = trackBeanList;
    }

    private View initHeadView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.default_expandable_view, null);
        TextView title = view.findViewById(R.id.title);
        ExpandableTextView expandableTextView = view.findViewById(R.id.expand_text_view);
        expandableTextView.setText(mDataBean.getDescription());
        title.setText(mDataBean.getNickname());
        return view;
    }

    @Override
    public void onMusicTrackError(Throwable throwable) {
        recycler.setVisibility(View.GONE);
    }
}
