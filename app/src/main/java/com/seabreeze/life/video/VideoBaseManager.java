package com.seabreeze.life.video;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.Surface;

import com.danikula.videocache.CacheListener;
import com.danikula.videocache.HttpProxyCacheServer;
import com.danikula.videocache.file.Md5FileNameGenerator;
import com.seabreeze.life.utils.FileUtils;
import com.seabreeze.life.video.listener.MediaPlayerListener;
import com.seabreeze.life.video.model.Model;
import com.seabreeze.life.video.model.VideoOptionModel;
import com.seabreeze.life.video.player.IJKPlayerManager;
import com.seabreeze.life.video.player.IPlayerManager;
import com.seabreeze.life.video.player.SystemPlayerManager;
import com.seabreeze.life.video.utils.CommonUtil;
import com.seabreeze.life.video.utils.StorageUtils;
import com.seabreeze.life.video.utils.VideoType;
import com.seabreeze.life.video.video.base.VideoViewBridge;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;

import tv.danmaku.ijk.media.exo.IjkExoMediaPlayer;
import tv.danmaku.ijk.media.player.AbstractMediaPlayer;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkLibLoader;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public abstract class VideoBaseManager implements IMediaPlayer.OnPreparedListener, IMediaPlayer.OnCompletionListener,
        IMediaPlayer.OnBufferingUpdateListener, IMediaPlayer.OnSeekCompleteListener, IMediaPlayer.OnErrorListener,
        IMediaPlayer.OnVideoSizeChangedListener, IMediaPlayer.OnInfoListener, CacheListener, VideoViewBridge {

    public static String TAG = "VideoBaseManager";

    private static final int HANDLER_PREPARE = 0;

    private static final int HANDLER_SETDISPLAY = 1;

    private static final int HANDLER_RELEASE = 2;

    private static final int HANDLER_RELEASE_SURFACE = 3;

    private static final int BUFFER_TIME_OUT_ERROR = -192;//外部超时错误码

    //单例模式实在不好给instance()加参数，还是直接设为静态变量吧
    //自定义so包加载类
    protected static IjkLibLoader ijkLibLoader;

    protected MediaHandler mMediaHandler;

    protected Handler mainThreadHandler;

    protected WeakReference<MediaPlayerListener> listener;

    protected WeakReference<MediaPlayerListener> lastListener;

    //配置ijk option
    protected List<VideoOptionModel> optionModelList;

    //视频代理
    protected HttpProxyCacheServer proxy;

    //是否需要的自定义缓冲路径
    protected File cacheFile;

    //播放的tag，防止错位置，因为普通的url也可能重复
    protected String playTag = "";

    //header for cache
    protected Map<String, String> mMapHeadData;

    protected Context context;

    protected IPlayerManager playerManager;

    //当前播放的视频宽的高
    protected int currentVideoWidth = 0;

    //当前播放的视屏的高
    protected int currentVideoHeight = 0;

    //当前视频的最后状态
    protected int lastState;

    //播放的tag，防止错位置，因为普通的url也可能重复
    protected int playPosition = -22;

    //缓冲比例
    protected int buffterPoint;

    //播放超时
    protected int timeOut = 8 * 1000;

    //播放类型，默认IJK
    protected int videoType = VideoType.IJKPLAYER;

    //是否需要静音
    protected boolean needMute = false;

    //是否需要外部超时判断
    protected boolean needTimeOutOther;

    /**
     * 设置自定义so包加载类
     * 需要在instance之前设置
     */
    public static void setIjkLibLoader(IjkLibLoader libLoader) {
        IJKPlayerManager.setIjkLibLoader(libLoader);
        ijkLibLoader = libLoader;
    }

    public static IjkLibLoader getIjkLibLoader() {
        return ijkLibLoader;
    }

    /**
     * 删除默认所有缓存文件
     */
    public static void clearAllDefaultCache(Context context) {
        String path = StorageUtils.getIndividualCacheDirectory(context.getApplicationContext()).getAbsolutePath();
        FileUtils.deleteFiles(new File(path));
    }

    /**
     * 删除url对应默认缓存文件
     */
    public static void clearDefaultCache(Context context, String url) {
        Md5FileNameGenerator md5FileNameGenerator = new Md5FileNameGenerator();
        String name = md5FileNameGenerator.generate(url);
        String pathTmp = StorageUtils.getIndividualCacheDirectory(context.getApplicationContext()).getAbsolutePath() + File.separator + name + ".download";
        String path = StorageUtils.getIndividualCacheDirectory(context.getApplicationContext()).getAbsolutePath() + File.separator + name;
        CommonUtil.deleteFile(pathTmp);
        CommonUtil.deleteFile(path);
    }

    /***
     * @param libLoader 是否使用外部动态加载so
     * */
    protected void init(IjkLibLoader libLoader) {
        playerManager = getPlayManager(VideoType.IJKPLAYER);
        IJKPlayerManager.setIjkLibLoader(libLoader);
        HandlerThread mediaHandlerThread = new HandlerThread(TAG);
        mediaHandlerThread.start();
        mMediaHandler = new MediaHandler((mediaHandlerThread.getLooper()));
        mainThreadHandler = new Handler();
    }

    protected IPlayerManager getPlayManager(int videoType) {
        switch (videoType) {
            // TODO: 2018/5/11/011
//            case VideoType.IJKEXOPLAYER2:
//                return new EXO2PlayerManager();
            case VideoType.SYSTEMPLAYER:
                return new SystemPlayerManager();
            case VideoType.IJKPLAYER:
            default:
                return new IJKPlayerManager();
        }
    }

    /**
     * 创建缓存代理服务,带文件目录的.
     */
    public HttpProxyCacheServer newProxy(Context context, File file) {
        if (!file.exists()) {
            file.mkdirs();
        }
        HttpProxyCacheServer.Builder builder = new HttpProxyCacheServer.Builder(context);
        builder.cacheDirectory(file);
        cacheFile = file;
        return builder.build();
    }

    public void setProxy(HttpProxyCacheServer proxy) {
        this.proxy = proxy;
    }

    /**
     * 创建缓存代理服务
     */
    public HttpProxyCacheServer newProxy(Context context) {
        return new HttpProxyCacheServer.Builder(context.getApplicationContext()).build();
    }

    @Override
    public MediaPlayerListener listener() {
        if (listener == null)
            return null;
        return listener.get();
    }

    @Override
    public MediaPlayerListener lastListener() {
        if (lastListener == null)
            return null;
        return lastListener.get();
    }

    @Override
    public void setListener(MediaPlayerListener listener) {
        if (listener == null)
            this.listener = null;
        else
            this.listener = new WeakReference<>(listener);
    }

    @Override
    public void setLastListener(MediaPlayerListener lastListener) {
        if (lastListener == null)
            this.lastListener = null;
        else
            this.lastListener = new WeakReference<>(lastListener);
    }

    @Override
    public void setSpeed(float speed, boolean soundTouch) {
        if (playerManager != null) {
            playerManager.setSpeed(speed, soundTouch);
        }
    }

    @Override
    public void prepare(final String url, float speed, boolean loop, final Map<String, String> mapHeadData) {
        if (TextUtils.isEmpty(url))
            return;

        Message msg = new Message();
        msg.what = HANDLER_PREPARE;
        Model model = new Model(url, speed, loop, mapHeadData);
        msg.obj = model;
        mMediaHandler.sendMessage(msg);
        if (needTimeOutOther) {
            startTimeOutBuffer();
        }
    }

    @Override
    public void releaseMediaPlayer() {
        Message msg = new Message();
        msg.what = HANDLER_RELEASE;
        mMediaHandler.sendMessage(msg);
        playTag = "";
        playPosition = -22;
    }

    @Override
    public void setDisplay(Surface surface) {
        Message msg = new Message();
        msg.what = HANDLER_SETDISPLAY;
        msg.obj = surface;
        mMediaHandler.sendMessage(msg);
    }

    @Override
    public void releaseSurface(Surface holder) {
        Message msg = new Message();
        msg.what = HANDLER_RELEASE_SURFACE;
        msg.obj = holder;
        mMediaHandler.sendMessage(msg);
    }

    @Override
    public void onPrepared(IMediaPlayer iMediaPlayer) {
        mainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                cancelTimeOutBuffer();
                if (listener != null) {
                    listener().onPrepared();
                }
            }
        });
    }

    @Override
    public void onCompletion(IMediaPlayer iMediaPlayer) {
        mainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                cancelTimeOutBuffer();
                if (listener != null) {
                    listener().onAutoCompletion();
                }
            }
        });
    }

    @Override
    public void onBufferingUpdate(IMediaPlayer iMediaPlayer, final int percent) {
        mainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                if (listener != null) {
                    if (percent > buffterPoint) {
                        listener().onBufferingUpdate(percent);
                    } else {
                        listener().onBufferingUpdate(buffterPoint);
                    }
                }
            }
        });
    }

    @Override
    public void onSeekComplete(IMediaPlayer iMediaPlayer) {
        mainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                cancelTimeOutBuffer();
                if (listener != null) {
                    listener().onSeekComplete();
                }
            }
        });
    }

    @Override
    public boolean onError(IMediaPlayer iMediaPlayer, final int what, final int extra) {
        mainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                cancelTimeOutBuffer();
                if (listener != null) {
                    listener().onError(what, extra);
                }
            }
        });
        return true;
    }

    @Override
    public boolean onInfo(IMediaPlayer iMediaPlayer, final int what, final int extra) {
        mainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                if (needTimeOutOther) {
                    if (what == MediaPlayer.MEDIA_INFO_BUFFERING_START) {
                        startTimeOutBuffer();
                    } else if (what == MediaPlayer.MEDIA_INFO_BUFFERING_END) {
                        cancelTimeOutBuffer();
                    }
                }
                if (listener != null) {
                    listener().onInfo(what, extra);
                }
            }
        });
        return false;
    }


    @Override
    public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i1, int i2, int i3) {
        currentVideoWidth = iMediaPlayer.getVideoWidth();
        currentVideoHeight = iMediaPlayer.getVideoHeight();
        mainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                if (listener != null) {
                    listener().onVideoSizeChanged();
                }
            }
        });
    }

    @Override
    public void onCacheAvailable(File cacheFile, String url, int percentsAvailable) {
        buffterPoint = percentsAvailable;
    }

    @Override
    public IMediaPlayer getMediaPlayer() {
        if (playerManager != null) {
            return playerManager.getMediaPlayer();
        }
        return null;
    }

    @Override
    public CacheListener getCacheListener() {
        return this;
    }

    @Override
    public int getLastState() {
        return lastState;
    }

    @Override
    public void setLastState(int lastState) {
        this.lastState = lastState;
    }

    @Override
    public int getCurrentVideoWidth() {
        return currentVideoWidth;
    }

    @Override
    public int getCurrentVideoHeight() {
        return currentVideoHeight;
    }

    @Override
    public void setCurrentVideoHeight(int currentVideoHeight) {
        this.currentVideoHeight = currentVideoHeight;
    }

    @Override
    public void setCurrentVideoWidth(int currentVideoWidth) {
        this.currentVideoWidth = currentVideoWidth;
    }

    @Override
    public String getPlayTag() {
        return playTag;
    }

    @Override
    public void setPlayTag(String playTag) {
        this.playTag = playTag;
    }

    @Override
    public int getPlayPosition() {
        return playPosition;
    }

    @Override
    public void setPlayPosition(int playPosition) {
        this.playPosition = playPosition;
    }

    private class MediaHandler extends Handler {

        MediaHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case HANDLER_PREPARE:
                    initVideo(msg);
                    break;
                case HANDLER_SETDISPLAY:
                    showDisplay(msg);
                    break;
                case HANDLER_RELEASE:
                    if (playerManager != null) {
                        playerManager.release();
                    }
                    setNeedMute(false);
                    if (proxy != null) {
                        proxy.unregisterCacheListener(VideoBaseManager.this);
                    }
                    buffterPoint = 0;
                    cancelTimeOutBuffer();
                    break;
                case HANDLER_RELEASE_SURFACE:
                    releaseSurface(msg);
                    break;
            }
        }
    }

    private void initVideo(Message msg) {
        try {
            currentVideoWidth = 0;
            currentVideoHeight = 0;

            if (playerManager != null) {
                playerManager.release();
            }

            playerManager = getPlayManager(videoType);

            playerManager.initVideoPlayer(context, msg, optionModelList);
            setNeedMute(needMute);
            IMediaPlayer mediaPlayer = playerManager.getMediaPlayer();
            mediaPlayer.setOnCompletionListener(this);
            mediaPlayer.setOnBufferingUpdateListener(this);
            mediaPlayer.setScreenOnWhilePlaying(true);
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.setOnSeekCompleteListener(this);
            mediaPlayer.setOnErrorListener(this);
            mediaPlayer.setOnInfoListener(this);
            mediaPlayer.setOnVideoSizeChangedListener(this);
            mediaPlayer.prepareAsync();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 启动十秒的定时器进行 缓存操作
     */
    private void startTimeOutBuffer() {
        // 启动定时
        mainThreadHandler.postDelayed(mTimeOutRunnable, timeOut);

    }

    /**
     * 取消 十秒的定时器进行 缓存操作
     */
    private void cancelTimeOutBuffer() {
        // 取消定时
        if (needTimeOutOther)
            mainThreadHandler.removeCallbacks(mTimeOutRunnable);
    }


    private Runnable mTimeOutRunnable = new Runnable() {
        @Override
        public void run() {
            if (listener != null) {
                listener().onError(BUFFER_TIME_OUT_ERROR, BUFFER_TIME_OUT_ERROR);
            }
        }
    };

    private void releaseSurface(Message msg) {
        if (msg.obj != null) {
            if (playerManager != null) {
                playerManager.releaseSurface();
            }
        }
    }

    /**
     * 后面再修改设计模式吧，现在先用着
     */
    private void showDisplay(Message msg) {
        if (playerManager != null) {
            playerManager.showDisplay(msg);
        }
    }

    public int getVideoType() {
        return videoType;
    }

    /**
     * 设置了视频的播放类型
     * IJKPLAYER = 0; 默认IJK
     * IJKEXOPLAYER2 = 2;EXOPlayer2 (最好配合GSYVideoType.SUFRACE)
     * SYSTEMPLAYER = 4;系统播放器 (最好配合GSYVideoType.SUFRACE)
     */
    public void setVideoType(Context context, int videoType) {
        this.context = context.getApplicationContext();
        this.videoType = videoType;
    }

    /**
     * 打开raw播放支持
     *
     * @param context
     */
    public void enableRawPlay(Context context) {
        this.context = context.getApplicationContext();
    }

    public List<VideoOptionModel> getOptionModelList() {
        return optionModelList;
    }

    /**
     * 设置IJK视频的option
     */
    public void setOptionModelList(List<VideoOptionModel> optionModelList) {
        this.optionModelList = optionModelList;
    }

    public boolean isNeedMute() {
        return needMute;
    }

    /**
     * 是否需要静音
     */
    public void setNeedMute(boolean needMute) {
        this.needMute = needMute;
        if (playerManager != null) {
            playerManager.setNeedMute(needMute);
        }
    }

    public int getTimeOut() {
        return timeOut;
    }

    public boolean isNeedTimeOutOther() {
        return needTimeOutOther;
    }

    /**
     * 是否需要在buffer缓冲时，增加外部超时判断
     * <p>
     * 超时后会走onError接口，播放器通过onPlayError回调出
     * <p>
     * 错误码为 ： BUFFER_TIME_OUT_ERROR = -192
     * <p>
     * 由于onError之后执行GSYVideoPlayer的OnError，如果不想触发错误，
     * 可以重载onError，在super之前拦截处理。
     * <p>
     * public void onError(int what, int extra){
     * do you want before super and return;
     * super.onError(what, extra)
     * }
     *
     * @param timeOut          超时时间，毫秒 默认8000
     * @param needTimeOutOther 是否需要延时设置，默认关闭
     */
    public void setTimeOut(int timeOut, boolean needTimeOutOther) {
        this.timeOut = timeOut;
        this.needTimeOutOther = needTimeOutOther;
    }

    /**
     * 设置log输入等级
     */
    public void setLogLevel(int logLevel) {
        IJKPlayerManager.setLogLevel(logLevel);
    }
}
