package com.seabreeze.life.common.vr;

import android.view.Surface;

import java.io.IOException;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class MediaPlayerWrapper implements IMediaPlayer.OnPreparedListener {

    protected IMediaPlayer mPlayer;


    private static final int STATUS_IDLE = 0;
    private static final int STATUS_PREPARING = 1;
    private static final int STATUS_PREPARED = 2;
    private static final int STATUS_STARTED = 3;
    private static final int STATUS_PAUSED = 4;
    private static final int STATUS_STOPPED = 5;
    private int mStatus = STATUS_IDLE;

    private IjkMediaPlayer.OnPreparedListener mPreparedListener;

    public void init() {
        mStatus = STATUS_IDLE;
        mPlayer = new IjkMediaPlayer();
        mPlayer.setOnPreparedListener(this);
        mPlayer.setOnInfoListener(new IMediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(IMediaPlayer mp, int what, int extra) {
                return false;
            }
        });

        enableHardwareDecoding();
    }

    private void enableHardwareDecoding() {
        if (mPlayer instanceof IjkMediaPlayer) {
            IjkMediaPlayer player = (IjkMediaPlayer) mPlayer;
            player.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec", 1);
            player.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec-auto-rotate", 1);
            player.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "overlay-format", IjkMediaPlayer.SDL_FCC_RV32);
            player.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "framedrop", 60);
            player.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "max-fps", 0);
            player.setOption(IjkMediaPlayer.OPT_CATEGORY_CODEC, "skip_loop_filter", 48);
        }
    }

    public void openRemoteFile(String url) {
        try {
            mPlayer.setDataSource(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void prepare() {
        if (mPlayer == null)
            return;
        if (mStatus == STATUS_IDLE || mStatus == STATUS_STOPPED) {
            mPlayer.prepareAsync();
            mStatus = STATUS_PREPARING;
        }
    }

    public void start() {
        if (mPlayer == null)
            return;
        if (mStatus == STATUS_PREPARED || mStatus == STATUS_PAUSED) {
            mPlayer.start();
            mStatus = STATUS_STARTED;
        }
    }

    public void pause() {
        if (mPlayer == null)
            return;
        if (mPlayer.isPlaying() && mStatus == STATUS_STARTED) {
            mPlayer.pause();
            mStatus = STATUS_PAUSED;
        }
    }

    public void stop() {
        if (mPlayer == null)
            return;
        if (mStatus == STATUS_STARTED || mStatus == STATUS_PAUSED) {
            mPlayer.stop();
            mStatus = STATUS_STOPPED;
        }
    }


    public void resume() {
        start();
    }

    public void destroy() {
        stop();
        if (mPlayer != null) {
            mPlayer.setSurface(null);
            mPlayer.release();
        }
        mPlayer = null;
    }

    @Override
    public void onPrepared(IMediaPlayer iMediaPlayer) {

        mStatus = STATUS_PREPARED;
        start();
        if (mPreparedListener != null)
            mPreparedListener.onPrepared(iMediaPlayer);
    }

    public void setPreparedListener(IMediaPlayer.OnPreparedListener mPreparedListener) {
        this.mPreparedListener = mPreparedListener;
    }


    public IMediaPlayer getPlayer() {
        return mPlayer;
    }

    public void setSurface(Surface surface) {
        if (getPlayer() != null) {
            getPlayer().setSurface(surface);
        }
    }
}
