package com.seabreeze.life.common.ui.activity;

import android.content.res.Configuration;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.asha.vrlib.MD360Director;
import com.asha.vrlib.MD360DirectorFactory;
import com.asha.vrlib.MDVRLibrary;
import com.asha.vrlib.model.BarrelDistortionConfig;
import com.asha.vrlib.model.MDPinchConfig;
import com.robot.seabreeze.log.Log;
import com.seabreeze.life.R;
import com.seabreeze.life.common.base.SimpleActivity;
import com.seabreeze.life.common.vr.CustomProjectionFactory;
import com.seabreeze.life.common.vr.MediaPlayerWrapper;
import com.seabreeze.life.entity.veer.data.UrlsBean;
import com.seabreeze.life.video.utils.CommonUtil;
import com.seabreeze.life.widget.ENDownloadView;
import com.seabreeze.life.widget.ENPlayView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public class VrVideoActivity extends SimpleActivity implements IMediaPlayer.OnPreparedListener,
        IMediaPlayer.OnVideoSizeChangedListener, IMediaPlayer.OnErrorListener, IMediaPlayer.OnCompletionListener {

    public static final String VEER_DATA = "VEER_DATA";

    @BindView(R.id.glSurfaceView)
    RelativeLayout glSurfaceView;
    @BindView(R.id.loading)
    ENDownloadView loading;
    @BindView(R.id.start)
    ENPlayView start;
    @BindView(R.id.back)
    ImageView back;

    private String playUrl;

    private MDVRLibrary mVRLibrary;
    private MediaPlayerWrapper mMediaPlayerWrapper;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_veer_detail;
    }

    @Override
    protected void initEventAndData() {

        UrlsBean urlsBean = getIntent().getParcelableExtra(VEER_DATA);
        if (urlsBean == null) {
            return;
        }
        playUrl = urlsBean.getBest();

        loading.start();
        loading.setVisibility(View.VISIBLE);
        start.setVisibility(View.GONE);

        initVRLibrary();

        initMediaPlayer();
    }

    private void initVRLibrary() {
        GLSurfaceView myGLSurfaceView = new GLSurfaceView(this);

        mVRLibrary = MDVRLibrary.with(this)
                .displayMode(MDVRLibrary.DISPLAY_MODE_NORMAL)
                .interactiveMode(MDVRLibrary.INTERACTIVE_MODE_MOTION)
                .asVideo(new MDVRLibrary.IOnSurfaceReadyCallback() {
                    @Override
                    public void onSurfaceReady(Surface surface) {
                        mMediaPlayerWrapper.setSurface(surface);
                    }
                })
                .pinchConfig(new MDPinchConfig().setMin(1.0f).setMax(8.0f).setDefaultValue(0.1f))
                .pinchEnabled(true)
                .directorFactory(new MD360DirectorFactory() {
                    @Override
                    public MD360Director createDirector(int index) {
                        return MD360Director.builder().setPitch(90).build();
                    }
                })
                .projectionFactory(new CustomProjectionFactory())
                .barrelDistortionConfig(new BarrelDistortionConfig().setDefaultEnabled(false).setScale(0.95f))
                .build(myGLSurfaceView);
        glSurfaceView.addView(myGLSurfaceView);
    }

    private void initMediaPlayer() {
        mMediaPlayerWrapper = new MediaPlayerWrapper();
        mMediaPlayerWrapper.init();

        mMediaPlayerWrapper.setPreparedListener(this);
        mMediaPlayerWrapper.getPlayer().setOnVideoSizeChangedListener(this);
        mMediaPlayerWrapper.getPlayer().setOnErrorListener(this);
        mMediaPlayerWrapper.getPlayer().setOnCompletionListener(this);

        if (playUrl.startsWith("https")) {
            playUrl = playUrl.substring(0, 4) + playUrl.substring(5, playUrl.length());
        }
        Log.e(playUrl);

        mMediaPlayerWrapper.openRemoteFile(playUrl);
        mMediaPlayerWrapper.prepare();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mVRLibrary.onResume(this);
        mMediaPlayerWrapper.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mVRLibrary.onPause(this);
        mMediaPlayerWrapper.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mVRLibrary.onDestroy();
        mMediaPlayerWrapper.destroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mVRLibrary.onOrientationChanged(this);
    }

    @OnClick({R.id.start, R.id.back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start:
                if (start.getCurrentState() == ENPlayView.STATE_PLAY) {
                    mMediaPlayerWrapper.pause();
                    start.pause();
                } else if (start.getCurrentState() == ENPlayView.STATE_PAUSE) {
                    mMediaPlayerWrapper.start();
                    start.play();
                }
                break;
            case R.id.back:
                finish();
                break;
        }
    }

    @Override
    public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int width, int height,
                                   int sar_num, int sar_den) {
        mVRLibrary.onTextureResize(width, height);
    }

    @Override
    public boolean onError(IMediaPlayer iMediaPlayer, int what, int extra) {
        showToast(String.format("Play Error what=%d extra=%d", what, extra));
        finish();
        return true;
    }

    @Override
    public void onPrepared(IMediaPlayer iMediaPlayer) {
        loading.reset();
        loading.setVisibility(View.GONE);

        start.setVisibility(View.VISIBLE);
        start.play();

        if (mVRLibrary != null) {
            mVRLibrary.notifyPlayerChanged();
        }
    }

    @Override
    public void onCompletion(IMediaPlayer iMediaPlayer) {
        start.pause();
    }
}
