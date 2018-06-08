package com.seabreeze.life.video.video.base;

import android.view.Surface;

import com.danikula.videocache.CacheListener;
import com.seabreeze.life.video.listener.MediaPlayerListener;

import java.util.Map;

import tv.danmaku.ijk.media.player.IMediaPlayer;

/**
 * Manager 与 View之间的接口
 * Created by guoshuyu on 2018/1/25.
 */

public interface VideoViewBridge {

    MediaPlayerListener listener();

    MediaPlayerListener lastListener();

    void setListener(MediaPlayerListener listener);

    void setLastListener(MediaPlayerListener lastListener);

    String getPlayTag();

    void setPlayTag(String playTag);

    int getPlayPosition();

    void setPlayPosition(int playPosition);

    void prepare(final String url, float speed, boolean loop, final Map<String, String> mapHeadData);

    IMediaPlayer getMediaPlayer();

    CacheListener getCacheListener();

    void releaseMediaPlayer();

    void setCurrentVideoHeight(int currentVideoHeight);

    void setCurrentVideoWidth(int currentVideoWidth);

    int getCurrentVideoWidth();

    int getCurrentVideoHeight();

    void setDisplay(Surface holder);

    void releaseSurface(Surface surface);

    void setSpeed(float speed, boolean soundTouch);

    int getLastState();

    void setLastState(int lastState);

}
