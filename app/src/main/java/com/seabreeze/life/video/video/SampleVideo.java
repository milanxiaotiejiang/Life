package com.seabreeze.life.video.video;

import android.content.Context;
import android.graphics.Matrix;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.seabreeze.life.R;
import com.seabreeze.life.video.VideoManager;
import com.seabreeze.life.video.VideoPlayer;
import com.seabreeze.life.video.model.SwitchVideoModel;
import com.seabreeze.life.video.utils.SwitchVideoTypeDialog;
import com.seabreeze.life.video.utils.VideoType;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SampleVideo extends StandardVideoPlayer {

    private TextView mMoreScale;

    private TextView mSwitchSize;

    private TextView mChangeRotate;

    private List<SwitchVideoModel> mUrlList = new ArrayList<>();

    //记住切换数据源类型
    private int mType = 0;

    private int mTransformSize = 0;

    //数据源
    private int mSourcePosition = 0;

    private String mTypeText = "标准";

    public SampleVideo(Context context, Boolean fullFlag) {
        super(context, fullFlag);
    }

    public SampleVideo(@NonNull Context context) {
        super(context);
    }

    public SampleVideo(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void init(Context context) {
        super.init(context);
        initView();
    }

    private void initView() {
        mMoreScale = findViewById(R.id.moreScale);
        mSwitchSize = findViewById(R.id.switchSize);
        mChangeRotate =   findViewById(R.id.change_rotate);

        mMoreScale.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mHadPlay) {
                    return;
                }
                if (mType == 0) {
                    mType = 1;
                } else if (mType == 1) {
                    mType = 2;
                } else if (mType == 2) {
                    mType = 3;
                } else if (mType == 3) {
                    mType = 4;
                } else if (mType == 4) {
                    mType = 0;
                }
                resolveTypeUI();
            }
        });

        //切换视频清晰度
        mSwitchSize.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showSwitvhDialog();
            }
        });

        //旋转播放角度
        mChangeRotate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mHadPlay) {
                    return;
                }
                if ((mTextureView.getRotation() - mRotate) == 270) {
                    mTextureView.setRotation(mRotate);
                    mTextureView.requestLayout();
                } else {
                    mTextureView.setRotation(mTextureView.getRotation() + 90);
                    mTextureView.requestLayout();
                }
            }
        });
    }

    public boolean setUp(List<SwitchVideoModel> urls, boolean cacheWithPlay, String title) {
        mUrlList = urls;
        return setUp(urls.get(mSourcePosition).getUrl(), cacheWithPlay, title);
    }

    public boolean setUp(List<SwitchVideoModel> urls, boolean cacheWithPlay, File cachePath, String title) {
        mUrlList = urls;
        return setUp(urls.get(mSourcePosition).getUrl(), cacheWithPlay, cachePath, title);
    }

    @Override
    public int getLayoutId() {
        return R.layout.sample_video;
    }


    /**
     * 显示比例
     * 注意，VideoType.setShowType是全局静态生效，除非重启APP。
     */
    private void resolveTypeUI() {
        if (!mHadPlay) {
            return;
        }
        if (mType == 1) {
            mMoreScale.setText("16:9");
            VideoType.setShowType(VideoType.SCREEN_TYPE_16_9);
        } else if (mType == 2) {
            mMoreScale.setText("4:3");
            VideoType.setShowType(VideoType.SCREEN_TYPE_4_3);
        } else if (mType == 3) {
            mMoreScale.setText("全屏");
            VideoType.setShowType(VideoType.SCREEN_TYPE_FULL);
        } else if (mType == 4) {
            mMoreScale.setText("拉伸全屏");
            VideoType.setShowType(VideoType.SCREEN_MATCH_FULL);
        } else if (mType == 0) {
            mMoreScale.setText("默认比例");
            VideoType.setShowType(VideoType.SCREEN_TYPE_DEFAULT);
        }
        changeTextureViewShowType();
        if (mTextureView != null)
            mTextureView.requestLayout();
        mSwitchSize.setText(mTypeText);
    }


    private void showSwitvhDialog() {
        SwitchVideoTypeDialog typeDialog = new SwitchVideoTypeDialog(getContext());
        typeDialog.initList(mUrlList, new SwitchVideoTypeDialog.OnListItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String name = mUrlList.get(position).getName();
                if (mSourcePosition != position) {
                    if (mCurrentState == VideoPlayer.CURRENT_STATE_PLAYING ||
                            mCurrentState == VideoPlayer.CURRENT_STATE_PAUSE &&
                                    getVideoManager().getMediaPlayer() != null) {
                        final String url = mUrlList.get(position).getUrl();
                        onVideoPause();
                        final long currentPosition = mCurrentPosition;
                        getVideoManager().releaseMediaPlayer();
                        cancelProgressTimer();
                        hideAllWidget();
                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                setUp(url, mCache, mCachePath, mTitle);
                                setSeekOnStart(currentPosition);
                                startPlayLogic();
                                cancelProgressTimer();
                                hideAllWidget();
                            }
                        }, 500);
                        mTypeText = name;
                        mSwitchSize.setText(name);
                        mSourcePosition = position;
                    }
                } else {
                    Toast.makeText(getContext(), "已经是 " + name, Toast.LENGTH_LONG).show();
                }
            }
        });
        typeDialog.show();
    }
}
