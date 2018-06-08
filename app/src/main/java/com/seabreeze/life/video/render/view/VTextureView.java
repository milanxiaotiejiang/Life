package com.seabreeze.life.video.render.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;

import com.seabreeze.life.utils.FileUtils;
import com.seabreeze.life.video.listener.VideoShotListener;
import com.seabreeze.life.video.listener.VideoShotSaveListener;
import com.seabreeze.life.video.render.RenderView;
import com.seabreeze.life.video.render.glrender.VideoGLViewBaseRender;
import com.seabreeze.life.video.render.view.listener.ISurfaceListener;
import com.seabreeze.life.video.utils.MeasureHelper;

import java.io.File;

/**
 * 用于显示video的，做了横屏与竖屏的匹配，还有需要rotation需求的
 * Created by shuyu on 2016/11/11.
 */

public class VTextureView extends TextureView implements TextureView.SurfaceTextureListener, IRenderView, MeasureHelper.MeasureFormVideoParamsListener {

    private ISurfaceListener mISurfaceListener;

    private MeasureHelper.MeasureFormVideoParamsListener mVideoParamsListener;

    private MeasureHelper measureHelper;

    private Surface mSurface;

    public VTextureView(Context context) {
        super(context);
        init();
    }

    public VTextureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        measureHelper = new MeasureHelper(this, this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureHelper.prepareMeasure(widthMeasureSpec, heightMeasureSpec, (int) getRotation());
        setMeasuredDimension(measureHelper.getMeasuredWidth(), measureHelper.getMeasuredHeight());
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        mSurface = new Surface(surface);
        if (mISurfaceListener != null) {
            mISurfaceListener.onSurfaceAvailable(mSurface);
        }
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
        if (mISurfaceListener != null) {
            mISurfaceListener.onSurfaceSizeChanged(mSurface, width, height);
        }
    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        //清空释放
        if (mISurfaceListener != null) {
            mISurfaceListener.onSurfaceDestroyed(mSurface);
        }
        return true;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {
        //如果播放的是暂停全屏了
        if (mISurfaceListener != null) {
            mISurfaceListener.onSurfaceUpdated(mSurface);
        }
    }

    @Override
    public ISurfaceListener getISurfaceListener() {
        return mISurfaceListener;
    }

    @Override
    public void setISurfaceListener(ISurfaceListener surfaceListener) {
        setSurfaceTextureListener(this);
        mISurfaceListener = surfaceListener;
    }

    @Override
    public int getSizeH() {
        return getHeight();
    }

    @Override
    public int getSizeW() {
        return getWidth();
    }

    /**
     * 暂停时初始化位图
     */
    @Override
    public Bitmap initCover() {
        Bitmap bitmap = Bitmap.createBitmap(getSizeW(), getSizeH(), Bitmap.Config.RGB_565);
        return getBitmap(bitmap);

    }

    /**
     * 暂停时初始化位图
     */
    @Override
    public Bitmap initCoverHigh() {
        Bitmap bitmap = Bitmap.createBitmap(getSizeW(), getSizeH(), Bitmap.Config.ARGB_8888);
        return getBitmap(bitmap);

    }


    /**
     * 获取截图
     *
     * @param shotHigh 是否需要高清的
     */
    @Override
    public void taskShotPic(VideoShotListener videoShotListener, boolean shotHigh) {
        if (shotHigh) {
            videoShotListener.getBitmap(initCoverHigh());
        } else {
            videoShotListener.getBitmap(initCover());
        }
    }

    /**
     * 保存截图
     *
     * @param high 是否需要高清的
     */
    @Override
    public void saveFrame(final File file, final boolean high, final VideoShotSaveListener videoShotSaveListener) {
        VideoShotListener shotSaveListener = new VideoShotListener() {
            @Override
            public void getBitmap(Bitmap bitmap) {
                if (bitmap == null) {
                    videoShotSaveListener.result(false, file);
                } else {
                    FileUtils.saveBitmap(bitmap, file);
                    videoShotSaveListener.result(true, file);
                }
            }
        };
        if (high) {
            shotSaveListener.getBitmap(initCoverHigh());
        } else {
            shotSaveListener.getBitmap(initCover());
        }
    }


    @Override
    public View getRenderView() {
        return this;
    }

    @Override
    public void onRenderResume() {
    }

    @Override
    public void onRenderPause() {
    }

    @Override
    public void releaseRenderAll() {
    }

    @Override
    public void setRenderMode(int mode) {
    }

    @Override
    public void setRenderTransform(Matrix transform) {
        setTransform(transform);
    }

    @Override
    public void setGLRenderer(VideoGLViewBaseRender renderer) {
    }

    @Override
    public void setGLMVPMatrix(float[] MVPMatrix) {
    }

    /**
     * 设置滤镜效果
     */
    @Override
    public void setGLEffectFilter(VideoGLView.ShaderInterface effectFilter) {
    }


    @Override
    public void setVideoParamsListener(MeasureHelper.MeasureFormVideoParamsListener listener) {
        mVideoParamsListener = listener;
    }

    @Override
    public int getCurrentVideoWidth() {
        if (mVideoParamsListener != null) {
            return mVideoParamsListener.getCurrentVideoWidth();
        }
        return 0;
    }

    @Override
    public int getCurrentVideoHeight() {
        if (mVideoParamsListener != null) {
            return mVideoParamsListener.getCurrentVideoHeight();
        }
        return 0;
    }

    @Override
    public int getVideoSarNum() {
        if (mVideoParamsListener != null) {
            return mVideoParamsListener.getVideoSarNum();
        }
        return 0;
    }

    @Override
    public int getVideoSarDen() {
        if (mVideoParamsListener != null) {
            return mVideoParamsListener.getVideoSarDen();
        }
        return 0;
    }


    /**
     * 添加播放的view
     */
    public static VTextureView addTextureView(Context context, ViewGroup textureViewContainer, int rotate,
                                              final ISurfaceListener SurfaceListener,
                                              final MeasureHelper.MeasureFormVideoParamsListener videoParamsListener) {
        if (textureViewContainer.getChildCount() > 0) {
            textureViewContainer.removeAllViews();
        }
        VTextureView textureView = new VTextureView(context);
        textureView.setISurfaceListener(SurfaceListener);
        textureView.setVideoParamsListener(videoParamsListener);
        textureView.setRotation(rotate);
        RenderView.addToParent(textureViewContainer, textureView);
        return textureView;
    }
}