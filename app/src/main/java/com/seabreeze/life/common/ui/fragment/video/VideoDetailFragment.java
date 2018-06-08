package com.seabreeze.life.common.ui.fragment.video;

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
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.konifar.fab_transformation.FabTransformation;
import com.seabreeze.life.R;
import com.seabreeze.life.common.adapter.VideoRelatedAdapter;
import com.seabreeze.life.common.adapter.VideoTagsAdapter;
import com.seabreeze.life.common.base.BaseDetailFragment;
import com.seabreeze.life.common.event.MessageEvent;
import com.seabreeze.life.common.image.ImageLoader;
import com.seabreeze.life.common.ui.activity.MainActivity;
import com.seabreeze.life.entity.video.DataBean;
import com.seabreeze.life.entity.video.VideoListBean2;
import com.seabreeze.life.entity.video.data.CoverBean;
import com.seabreeze.life.entity.video.data.TagsBean;
import com.seabreeze.life.mvp.persenter.impl.VideoDetailPresenterImpl;
import com.seabreeze.life.mvp.view.VideoDetailView;
import com.seabreeze.life.utils.DateUtils;
import com.seabreeze.life.utils.EventUtils;
import com.seabreeze.life.utils.JumpUtils;
import com.seabreeze.life.widget.header.TextHeader;

import java.util.List;

import butterknife.BindView;

public class VideoDetailFragment extends BaseDetailFragment<DataBean, VideoDetailPresenterImpl> implements VideoDetailView {

    @BindView(R.id.bg_image)
    ImageView bgImage;
    @BindView(R.id.img_detail)
    ImageView imgDetail;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.type)
    TextView type;
    @BindView(R.id.description)
    TextView description;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.recycler_tag)
    RecyclerView recyclerTag;
    @BindView(R.id.recycler_related)
    RecyclerView recyclerRelated;

    public static VideoDetailFragment newInstance(DataBean dataBean) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_ITEM, dataBean);
        VideoDetailFragment videoDetailFragment = new VideoDetailFragment();
        videoDetailFragment.setArguments(bundle);
        return videoDetailFragment;
    }

    private int color = 0xffffcc00;

    @Override
    protected int getLayoutId() {
        return R.layout.video_home_detail_fragment;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {

        showPager();

        initColor();

        initTags();

        initRelated();
    }

    private void showPager() {
        fab.setBackgroundTintList(new ColorStateList(new int[][]{new int[0]}, new int[]{0xffffcc00}));
        toolbar.setTitle("");
        initToolbarNav(toolbar);

        CoverBean coverBean = mDataBean.getCover();
        if (coverBean != null) {
            ImageLoader.getInstance().loadImage(mContext, imgDetail, coverBean.getDetail());
            ImageLoader.getInstance().loadImage(mContext, bgImage, coverBean.getBlurred());
        } else {
            ImageLoader.getInstance().loadImage(mContext, imgDetail, mDataBean.getCoverForFeed());
        }

        title.setText(mDataBean.getTitle());


        String stringBuilder = "#" + mDataBean.getCategory() +
                " " +
                " / " +
                " " +
                DateUtils.formatTime2(mDataBean.getDuration());
        type.setText(stringBuilder);
        description.setText(mDataBean.getDescription());

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
                            JumpUtils.goToVideoPlayer(getActivity(), imgDetail, mDataBean);
                        }
                    }).transformTo(imgDetail);
                }
            }
        });
    }

    private void initColor() {
        CoverBean coverBean = mDataBean.getCover();
        String blurred;
        if (coverBean != null) {
            blurred = coverBean.getBlurred();
        } else {
            blurred = mDataBean.getCoverForFeed();
        }
        Glide.with(mContext)
                .asBitmap()
                .load(blurred)
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
                                toolbarLayout.setContentScrimColor(color);
                                int[][] states = new int[][]{new int[0]};
                                int[] colors = new int[]{color};
                                fab.setBackgroundTintList(new ColorStateList(states, colors));
                            }
                        });
                    }
                });
    }

    private void initTags() {
        List<TagsBean> tags = mDataBean.getTags();
        if (tags != null && tags.size() > 0) {
            recyclerTag.setVisibility(View.VISIBLE);
            VideoTagsAdapter tagsAdapter = new VideoTagsAdapter(tags);
            tagsAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
            LinearLayoutManager llm = new LinearLayoutManager(mContext);
            llm.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerTag.setLayoutManager(llm);
            recyclerTag.setAdapter(tagsAdapter);
            recyclerTag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    JumpUtils.goToVideoPlayer(getActivity(), imgDetail, mDataBean);
                }
            });
        } else {
            recyclerTag.setVisibility(View.GONE);
        }
    }

    private void initRelated() {
        mPresenter.getVideoRelated(mDataBean.getId());
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
    public void onVideoDetailSuccess(final VideoListBean2 videoListBean) {
        final List<DataBean> videoList = videoListBean.getVideoList();
        if (videoList != null) {
            recyclerRelated.setVisibility(View.VISIBLE);
            LinearLayoutManager layout = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            recyclerRelated.setLayoutManager(layout);
            VideoRelatedAdapter relatedAdapter = new VideoRelatedAdapter(videoListBean.getVideoList());
            relatedAdapter.addHeaderView(new TextHeader(mContext, "相关推荐"));
            relatedAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
            recyclerRelated.setAdapter(relatedAdapter);
            relatedAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    VideoDetailFragment fragment = VideoDetailFragment.newInstance(videoList.get(position));

                    start(fragment);
                }
            });
        } else {
            recyclerRelated.setVisibility(View.GONE);
        }
    }

    @Override
    public void onVideoDetailError(Throwable throwable) {
        recyclerRelated.setVisibility(View.GONE);
    }

}
