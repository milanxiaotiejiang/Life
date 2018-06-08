package com.seabreeze.life.video;

import android.app.Activity;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import com.danikula.videocache.HttpProxyCacheServer;
import com.danikula.videocache.file.Md5FileNameGenerator;
import com.seabreeze.life.R;
import com.seabreeze.life.video.utils.CommonUtil;
import com.seabreeze.life.video.utils.StorageUtils;
import com.seabreeze.life.video.video.BaseVideoPlayer;
import com.seabreeze.life.video.video.base.VideoViewBridge;

import java.io.File;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import tv.danmaku.ijk.media.exo.IjkExoMediaPlayer;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkLibLoader;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * 兼容的空View，目前用于 VideoManager的设置
 */
public abstract class VideoPlayer extends BaseVideoPlayer {

    public VideoPlayer(Context context, Boolean fullFlag) {
        super(context, fullFlag);
    }

    public VideoPlayer(Context context) {
        super(context);
    }

    public VideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VideoPlayer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 设置自定义so包加载类，必须在setUp之前调用
     * 不然setUp时会第一次实例化VideoManager
     */
    public void setIjkLibLoader(IjkLibLoader libLoader) {
        VideoManager.setIjkLibLoader(libLoader);
    }

    @Override
    public VideoViewBridge getVideoManager() {
        return VideoManager.instance();
    }

    @Override
    protected boolean backFromFull(Context context) {
        return VideoManager.backFromWindowFull(context);
    }

    @Override
    protected void releaseVideos() {
        VideoManager.releaseAllVideos();
    }

    @Override
    protected HttpProxyCacheServer getProxy(Context context, File file) {
        return VideoManager.getProxy(context, file);
    }

    @Override
    protected int getFullId() {
        return VideoManager.FULLSCREEN_ID;
    }

    @Override
    protected int getSmallId() {
        return VideoManager.SMALL_ID;
    }

}
