package com.seabreeze.life.common.ui.activity;

import android.annotation.TargetApi;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.view.View;
import android.widget.ImageView;

import com.konifar.fab_transformation.FabTransformation;
import com.robot.seabreeze.log.Log;
import com.seabreeze.life.R;
import com.seabreeze.life.common.base.SimpleActivity;
import com.seabreeze.life.entity.video.DataBean;
import com.seabreeze.life.video.VideoManager;
import com.seabreeze.life.video.VideoPlayer;
import com.seabreeze.life.video.listener.OnTransitionListener;
import com.seabreeze.life.video.model.SwitchVideoModel;
import com.seabreeze.life.video.utils.OrientationUtils;
import com.seabreeze.life.video.video.SampleVideo;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlayActivity extends SimpleActivity {

    public static final String DATA = "video_data";
    public static final String TRANSITION = "video_transition";
    public static final String IMG_TRANSITION = "video_img_transition";

    @BindView(R.id.video_player)
    SampleVideo videoPlayer;


    private boolean isTransition;

    private Transition transition;

    private DataBean dataBean;

    private OrientationUtils orientationUtils;

    private List<SwitchVideoModel> list;

    @Override
    protected int getLayout() {
        return R.layout.activity_play;
    }

    @Override
    protected void initEventAndData() {
        isTransition = getIntent().getBooleanExtra(TRANSITION, false);
        dataBean = getIntent().getExtras().getParcelable(DATA);

        loadData();


        //增加封面
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        videoPlayer.setThumbImageView(imageView);

        //增加title
        videoPlayer.getTitleTextView().setVisibility(View.VISIBLE);

        //设置返回键
        videoPlayer.getBackButton().setVisibility(View.VISIBLE);

        //设置旋转
        orientationUtils = new OrientationUtils(this, videoPlayer);

        //设置全屏按键功能
        videoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orientationUtils.resolveByClick();
            }
        });


        //是否可以滑动调整
        videoPlayer.setIsTouchWiget(true);

        //设置返回按键功能
        videoPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressedSupport();
            }
        });

        //过渡动画
        initTransition();

    }

    @Override
    protected void onResume() {
        super.onResume();
        videoPlayer.onVideoResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        videoPlayer.onVideoPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (orientationUtils != null)
            orientationUtils.releaseListener();
    }

    @Override
    public void onBackPressedSupport() {
        //先返回正常状态
        if (orientationUtils.getScreenType() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            videoPlayer.getFullscreenButton().performClick();
            return;
        }
        //释放所有
        videoPlayer.setVideoAllCallBack(null);
        VideoManager.releaseAllVideos();
        if (isTransition && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            super.onBackPressedSupport();
        } else {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                    overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
                }
            }, 500);

        }
    }

    private void loadData() {
        list = new ArrayList<>();
        list.add(new SwitchVideoModel("普通", dataBean.getPlayUrl()));
        list.add(new SwitchVideoModel("高清", dataBean.getPlayInfo().get(0).getUrl()));
        videoPlayer.setUp(list, true, "");
    }

    private void initTransition() {
        if (isTransition && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            postponeEnterTransition();
            ViewCompat.setTransitionName(videoPlayer, IMG_TRANSITION);
            addTransitionListener();
            startPostponedEnterTransition();
        } else {
            videoPlayer.startPlayLogic();
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private boolean addTransitionListener() {
        transition = getWindow().getSharedElementEnterTransition();
        if (transition != null) {
            transition.addListener(new OnTransitionListener() {
                @Override
                public void onTransitionEnd(Transition transition) {
                    super.onTransitionEnd(transition);
                    videoPlayer.startPlayLogic();
                    transition.removeListener(this);
                }
            });
            return true;
        }
        return false;
    }

}
