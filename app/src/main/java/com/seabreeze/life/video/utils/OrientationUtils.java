package com.seabreeze.life.video.utils;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.provider.Settings;
import android.view.OrientationEventListener;

import com.seabreeze.life.video.video.BaseVideoPlayer;

public class OrientationUtils {

    private Activity activity;
    private BaseVideoPlayer baseVideoPlayer;

    private int screenType = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
    private OrientationEventListener orientationEventListener;
    private boolean mClick, mClickLand, mClickPort;
    private int mIsLand;
    private boolean mEnable = true;

    //是否跟随系统
    private boolean mRotateWithSystem = true;

    public OrientationUtils(Activity activity, BaseVideoPlayer baseVideoPlayer) {
        this.activity = activity;
        this.baseVideoPlayer = baseVideoPlayer;
        init();
    }

    private void init() {
        orientationEventListener = new OrientationEventListener(activity) {
            @Override
            public void onOrientationChanged(int orientation) {
                boolean autoRotateOn = (Settings.System.getInt(activity.getContentResolver(),
                        Settings.System.ACCELEROMETER_ROTATION, 0) == 1);
                if (!autoRotateOn && mRotateWithSystem) {
                    return;
                }
                if (baseVideoPlayer != null && baseVideoPlayer.isVerticalFullByVideoSize()) {
                    return;
                }
                // 设置竖屏
                if (((orientation >= 0) && (orientation <= 30)) || (orientation >= 330)) {
                    if (mClick) {
                        if (mIsLand > 0 && !mClickLand) {
                            return;
                        } else {
                            mClickPort = true;
                            mClick = false;
                            mIsLand = 0;
                        }
                    } else {
                        if (mIsLand > 0) {
                            screenType = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
                            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                            if (baseVideoPlayer.getFullscreenButton() != null) {
                                if (baseVideoPlayer.isIfCurrentIsFullscreen()) {
                                    baseVideoPlayer.getFullscreenButton().setImageResource(baseVideoPlayer.getShrinkImageRes());
                                } else {
                                    baseVideoPlayer.getFullscreenButton().setImageResource(baseVideoPlayer.getEnlargeImageRes());
                                }
                            }
                            mIsLand = 0;
                            mClick = false;
                        }
                    }
                }
                // 设置横屏
                else if (((orientation >= 230) && (orientation <= 310))) {
                    if (mClick) {
                        if (!(mIsLand == 1) && !mClickPort) {
                            return;
                        } else {
                            mClickLand = true;
                            mClick = false;
                            mIsLand = 1;
                        }
                    } else {
                        if (!(mIsLand == 1)) {
                            screenType = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
                            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                            if (baseVideoPlayer.getFullscreenButton() != null) {
                                baseVideoPlayer.getFullscreenButton().setImageResource(baseVideoPlayer.getShrinkImageRes());
                            }
                            mIsLand = 1;
                            mClick = false;
                        }
                    }
                }
                // 设置反向横屏
                else if (orientation > 30 && orientation < 95) {
                    if (mClick) {
                        if (!(mIsLand == 2) && !mClickPort) {
                            return;
                        } else {
                            mClickLand = true;
                            mClick = false;
                            mIsLand = 2;
                        }
                    } else if (!(mIsLand == 2)) {
                        screenType = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
                        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
                        if (baseVideoPlayer.getFullscreenButton() != null) {
                            baseVideoPlayer.getFullscreenButton().setImageResource(baseVideoPlayer.getShrinkImageRes());
                        }
                        mIsLand = 2;
                        mClick = false;
                    }
                }
            }
        };
        orientationEventListener.enable();
    }

    /**
     * 点击切换的逻辑，比如竖屏的时候点击了就是切换到横屏不回受屏幕的影响
     */
    public void resolveByClick() {
        if (mIsLand == 0 && baseVideoPlayer != null && baseVideoPlayer.isVerticalFullByVideoSize()) {
            return;
        }
        mClick = true;
        if (mIsLand == 0) {
            screenType = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            if (baseVideoPlayer.getFullscreenButton() != null) {
                baseVideoPlayer.getFullscreenButton().setImageResource(baseVideoPlayer.getShrinkImageRes());
            }
            mIsLand = 1;
            mClickLand = false;
        } else {
            screenType = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            if (baseVideoPlayer.getFullscreenButton() != null) {
                if (baseVideoPlayer.isIfCurrentIsFullscreen()) {
                    baseVideoPlayer.getFullscreenButton().setImageResource(baseVideoPlayer.getShrinkImageRes());
                } else {
                    baseVideoPlayer.getFullscreenButton().setImageResource(baseVideoPlayer.getEnlargeImageRes());
                }
            }
            mIsLand = 0;
            mClickPort = false;
        }
    }

    /**
     * 列表返回的样式判断，因为立即旋转会导致界面跳动的问题
     *
     * @return
     */
    public int backToProtVideo() {
        if (mIsLand > 0) {
            mClick = true;
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            if (baseVideoPlayer != null && baseVideoPlayer.getFullscreenButton() != null)
                baseVideoPlayer.getFullscreenButton().setImageResource(baseVideoPlayer.getEnlargeImageRes());
            mIsLand = 0;
            mClickPort = false;
            return 500;
        }
        return 0;
    }

    public boolean isEnable() {
        return mEnable;
    }

    public void setEnable(boolean enable) {
        this.mEnable = enable;
        if (mEnable) {
            orientationEventListener.enable();
        } else {
            orientationEventListener.disable();
        }
    }

    public void releaseListener() {
        if (orientationEventListener != null) {
            orientationEventListener.disable();
        }
    }

    public boolean isClick() {
        return mClick;
    }

    public void setClick(boolean Click) {
        this.mClick = mClick;
    }

    public boolean isClickLand() {
        return mClickLand;
    }

    public void setClickLand(boolean ClickLand) {
        this.mClickLand = ClickLand;
    }

    public int getIsLand() {
        return mIsLand;
    }

    public void setIsLand(int IsLand) {
        this.mIsLand = IsLand;
    }


    public boolean isClickPort() {
        return mClickPort;
    }

    public void setClickPort(boolean ClickPort) {
        this.mClickPort = ClickPort;
    }

    public int getScreenType() {
        return screenType;
    }

    public void setScreenType(int screenType) {
        this.screenType = screenType;
    }

    public boolean isRotateWithSystem() {
        return mRotateWithSystem;
    }

    /**
     * 是否更新系统旋转，false的话，系统禁止旋转也会跟着旋转
     *
     * @param rotateWithSystem 默认true
     */
    public void setRotateWithSystem(boolean rotateWithSystem) {
        this.mRotateWithSystem = rotateWithSystem;
    }
}
