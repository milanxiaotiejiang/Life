package com.seabreeze.life.common.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.graphics.Palette;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.seabreeze.life.R;
import com.seabreeze.life.common.adapter.CoverFlowAdapter;
import com.seabreeze.life.common.base.SimpleActivity;
import com.seabreeze.life.common.image.ScalePageTransformer;
import com.seabreeze.life.entity.music.DataBean;
import com.seabreeze.life.entity.music.MusicListBean;
import com.seabreeze.life.entity.music.TrackBean;
import com.seabreeze.life.entity.music.TracksBean;
import com.seabreeze.life.widget.LrcView;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class PlayMusicActivity extends SimpleActivity implements IMediaPlayer.OnPreparedListener, IMediaPlayer.OnErrorListener,
        IMediaPlayer.OnCompletionListener, IMediaPlayer.OnSeekCompleteListener {

    public static final String DATA = "DATA";
    public static final String TRACK = "TRACK";

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.share)
    ImageView share;
    @BindView(R.id.headline)
    TextView title;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.model)
    ImageView model;
    @BindView(R.id.play_pause)
    ImageView mPlayPause;
    @BindView(R.id.progressBar1)
    ProgressBar mLoading;
    @BindView(R.id.next)
    ImageView next;
    @BindView(R.id.controller)
    RelativeLayout controller;
    @BindView(R.id.current_time)
    TextView mStart;
    @BindView(R.id.progress)
    SeekBar mSeekbar;
    @BindView(R.id.total_time)
    TextView mEnd;
    @BindView(R.id.progress_controller)
    LinearLayout progressController;
    @BindView(R.id.viewpager)
    ViewPager mVpcontent;
    @BindView(R.id.rl_content)
    LinearLayout rlContent;
    @BindView(R.id.other_controller)
    RelativeLayout otherController;
    @BindView(R.id.play_music)
    RelativeLayout playMusic;
    @BindView(R.id.lrc_view)
    LrcView lrcView;

    private DataBean dataBean;
    private List<TrackBean> tracksBeans;

    private CoverFlowAdapter adapter;
    private IjkMediaPlayer player;

    private int playerIndex = 0;
    private boolean isContainue = true;

    private int mode = 0;

    private Runnable mRunnable;

    int color = 0xffffcc00;

    @Override
    protected int getLayout() {
        return R.layout.activity_play_music;
    }

    @Override
    protected void initEventAndData() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        dataBean = extras.getParcelable(DATA);
        tracksBeans = extras.getParcelableArrayList(TRACK);
        initGallery();
        initListener();
        initMeida();
    }

    private void initGallery() {

        if (tracksBeans == null || tracksBeans.size() == 0) {
            return;
        }

        adapter = new CoverFlowAdapter(this, tracksBeans);
        mVpcontent.setAdapter(adapter);
        mVpcontent.setOffscreenPageLimit(3);
        mVpcontent.setPageTransformer(true, new ScalePageTransformer());

        title.setText(tracksBeans.get(0).getSongname());
        name.setText(tracksBeans.get(0).getSinger());
        lrcView.loadLrc(tracksBeans.get(0).getLyrics());

        mVpcontent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                playerIndex = position;
                title.setText(tracksBeans.get(position).getSongname());
                name.setText(tracksBeans.get(position).getSinger());
                lrcView.loadLrc(tracksBeans.get(position).getLyrics());
                playMusic();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        initColor();
    }

    private void initColor() {
        Glide.with(this)
                .asBitmap()
                .load(dataBean.getHeadimg())
                .apply(new RequestOptions().override(300, 300))
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                        Palette.from(resource).generate(new Palette.PaletteAsyncListener() {
                            @Override
                            public void onGenerated(@NonNull Palette palette) {
                                try {
                                    color = palette.getLightMutedSwatch().getRgb();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                playMusic.setBackgroundColor(color);
                            }
                        });
                    }
                });
    }

    private void initListener() {
        mSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                player.pause();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                double time = player.getDuration() * (seekBar.getProgress() * 0.01);
                player.seekTo((long) time);
            }
        });
        updateProgress();
    }

    private void updateProgress() {
        mRunnable = new Runnable() {
            @Override
            public void run() {
                if (isContainue) {
                    mHandler.postDelayed(this, 1000);
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (player != null) {
                            int progress = (int) ((player.getCurrentPosition() * 1f / player.getDuration() * 1f) * 100);
                            mSeekbar.setProgress(progress);
                            mStart.setText(DateUtils.formatElapsedTime(player.getCurrentPosition() / 1000));
                            lrcView.updateTime(player.getCurrentPosition());
                        }
                    }
                });
            }
        };
        if (isContainue) {
            mHandler.postDelayed(mRunnable, 1000);
        }
    }


    private void initMeida() {
        player = new IjkMediaPlayer();
        player.reset();
        player.setOnPreparedListener(this);
        player.setOnErrorListener(this);
        player.setOnCompletionListener(this);
        player.setOnSeekCompleteListener(this);
        playMusic();
    }

    private void playMusic() {
        try {
            player.reset();
            player.setDataSource(tracksBeans.get(playerIndex).getFile128());
            player.prepareAsync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isContainue = false;
        if (player.isPlaying()) {
            player.pause();
            player.stop();
            player.release();
            player = null;
        }
    }

    @OnClick({R.id.back, R.id.share, R.id.play_pause, R.id.model, R.id.next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.play_pause:
                if (player.isPlaying()) {
                    mPlayPause.setImageResource(R.drawable.play_msc_icon);
                    player.pause();
                } else {
                    mPlayPause.setImageResource(R.drawable.pause_msc_icon);
                    player.start();
                }
                break;
            case R.id.model:
                switch (mode) {
                    case 0:
                        mode = 1;
                        model.setImageResource(R.drawable.circle_icon);
                        break;
                    case 1:
                        mode = 2;
                        model.setImageResource(R.drawable.single_play_icon);
                        break;
                    case 2:
                        mode = 0;
                        model.setImageResource(R.drawable.random_icon);
                        break;
                }
                break;
            case R.id.next:
                changeMusic();
                break;
        }
    }

    private void changeMusic() {
        int index = playerIndex;
        switch (mode) {
            case 1:
                index += 1;
                break;
            case 2:
                playMusic();
                break;
            case 0:
                Random random = new Random();
                int anInt = random.nextInt(10);
                if (anInt == index) {
                    anInt = random.nextInt(10);
                }
                index = anInt;
                break;
        }
        mVpcontent.setCurrentItem(index, true);
    }

    @Override
    public void onCompletion(IMediaPlayer iMediaPlayer) {
        changeMusic();
    }

    @Override
    public boolean onError(IMediaPlayer iMediaPlayer, int i, int i1) {
        iMediaPlayer.pause();
        return false;
    }

    @Override
    public void onPrepared(IMediaPlayer iMediaPlayer) {
        iMediaPlayer.start();
        mStart.setText("00:00");
        mEnd.setText(DateUtils.formatElapsedTime(iMediaPlayer.getDuration() / 1000));
    }

    @Override
    public void onSeekComplete(IMediaPlayer iMediaPlayer) {
        iMediaPlayer.start();
    }
}
