package com.seabreeze.life.video.render.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;

import com.seabreeze.life.utils.FileUtils;
import com.seabreeze.life.video.listener.VideoShotListener;
import com.seabreeze.life.video.listener.VideoShotSaveListener;
import com.seabreeze.life.video.render.RenderView;
import com.seabreeze.life.video.render.effect.NoEffect;
import com.seabreeze.life.video.render.glrender.VideoGLViewBaseRender;
import com.seabreeze.life.video.render.glrender.VideoGLViewSimpleRender;
import com.seabreeze.life.video.render.view.listener.GLSurfaceListener;
import com.seabreeze.life.video.render.view.listener.ISurfaceListener;
import com.seabreeze.life.video.render.view.listener.VideoGLRenderErrorListener;
import com.seabreeze.life.video.utils.MeasureHelper;

import java.io.File;


/**
 * 在videffects的基础上调整的
 * <p>
 * 原 @author sheraz.khilji
 */
@SuppressLint("ViewConstructor")
public class VideoGLView extends GLSurfaceView implements GLSurfaceListener, IRenderView, MeasureHelper.MeasureFormVideoParamsListener {

    /**
     * 利用布局计算大小
     */
    public static final int MODE_LAYOUT_SIZE = 0;
    /**
     * 利用Render计算大小
     */
    public static final int MODE_RENDER_SIZE = 1;

    private VideoGLViewBaseRender mRenderer;

    private Context mContext;

    private ShaderInterface mEffect = new NoEffect();

    private MeasureHelper.MeasureFormVideoParamsListener mVideoParamsListener;

    private MeasureHelper measureHelper;

    private GLSurfaceListener mOnSurfaceListener;

    private ISurfaceListener mISurfaceListener;

    private float[] mMVPMatrix;

    private int mMode = MODE_LAYOUT_SIZE;

    public interface ShaderInterface {
        String getShader(GLSurfaceView mGlSurfaceView);
    }

    public VideoGLView(Context context) {
        super(context);
        init(context);
    }

    public VideoGLView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        setEGLContextClientVersion(2);
        mRenderer = new VideoGLViewSimpleRender();
        measureHelper = new MeasureHelper(this, this);
        mRenderer.setSurfaceView(VideoGLView.this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mRenderer != null) {
            mRenderer.initRenderSize();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mMode == MODE_RENDER_SIZE) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            measureHelper.prepareMeasure(widthMeasureSpec, heightMeasureSpec, (int) getRotation());
            initRenderMeasure();
        } else {
            measureHelper.prepareMeasure(widthMeasureSpec, heightMeasureSpec, (int) getRotation());
            setMeasuredDimension(measureHelper.getMeasuredWidth(), measureHelper.getMeasuredHeight());
        }
    }

    @Override
    public ISurfaceListener getISurfaceListener() {
        return mISurfaceListener;
    }

    @Override
    public void setISurfaceListener(ISurfaceListener surfaceListener) {
        setOnSurfaceListener(this);
        mISurfaceListener = surfaceListener;
    }

    @Override
    public void onSurfaceAvailable(Surface surface) {
        if (mISurfaceListener != null) {
            mISurfaceListener.onSurfaceAvailable(surface);
        }
    }

    @Override
    public int getSizeH() {
        return getHeight();
    }

    @Override
    public int getSizeW() {
        return getWidth();
    }

    @Override
    public Bitmap initCover() {
        return null;
    }

    @Override
    public Bitmap initCoverHigh() {
        return null;
    }

    /**
     * 获取截图
     *
     * @param shotHigh 是否需要高清的
     */
    @Override
    public void taskShotPic(VideoShotListener VideoShotListener, boolean shotHigh) {
        if (VideoShotListener != null) {
            setVideoShotListener(VideoShotListener, shotHigh);
            takeShotPic();

        }
    }

    /**
     * 保存截图
     *
     * @param high 是否需要高清的
     */
    @Override
    public void saveFrame(final File file, final boolean high, final VideoShotSaveListener VideoShotSaveListener) {
        VideoShotListener VideoShotListener = new VideoShotListener() {
            @Override
            public void getBitmap(Bitmap bitmap) {
                if (bitmap == null) {
                    VideoShotSaveListener.result(false, file);
                } else {
                    FileUtils.saveBitmap(bitmap, file);
                    VideoShotSaveListener.result(true, file);
                }
            }
        };
        setVideoShotListener(VideoShotListener, high);
        takeShotPic();
    }

    @Override
    public View getRenderView() {
        return this;
    }


    @Override
    public void onRenderResume() {
        requestLayout();
        onResume();
    }

    @Override
    public void onRenderPause() {
        requestLayout();
        onPause();

    }

    @Override
    public void releaseRenderAll() {
        requestLayout();
        releaseAll();

    }

    @Override
    public void setRenderMode(int mode) {
        setMode(mode);
    }


    @Override
    public void setRenderTransform(Matrix transform) {

    }

    @Override
    public void setGLRenderer(VideoGLViewBaseRender renderer) {
        setCustomRenderer(renderer);
    }

    @Override
    public void setGLMVPMatrix(float[] MVPMatrix) {
        setMVPMatrix(MVPMatrix);
    }

    /**
     * 设置滤镜效果
     */
    @Override
    public void setGLEffectFilter(VideoGLView.ShaderInterface effectFilter) {
        setEffect(effectFilter);
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

    protected void initRenderMeasure() {
        if (mVideoParamsListener != null && mMode == MODE_RENDER_SIZE) {
            try {
                int videoWidth = mVideoParamsListener.getCurrentVideoWidth();
                int videoHeight = mVideoParamsListener.getCurrentVideoHeight();
                if (this.mRenderer != null) {
                    this.mRenderer.setCurrentViewWidth(measureHelper.getMeasuredWidth());
                    this.mRenderer.setCurrentViewHeight(measureHelper.getMeasuredHeight());
                    this.mRenderer.setCurrentVideoWidth(videoWidth);
                    this.mRenderer.setCurrentVideoHeight(videoHeight);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void initRender() {
        setRenderer(mRenderer);
    }


    public void setVideoGLRenderErrorListener(VideoGLRenderErrorListener videoGLRenderErrorListener) {
        this.mRenderer.setVideoGLRenderErrorListener(videoGLRenderErrorListener);
    }

    /**
     * 设置自定义的render，其他自定义设置会被取消，需要重新设置
     * 在initRender() 前设置才会生效
     *
     * @param CustomRender
     */
    public void setCustomRenderer(VideoGLViewBaseRender CustomRender) {
        this.mRenderer = CustomRender;
        mRenderer.setSurfaceView(VideoGLView.this);
        initRenderMeasure();
    }

    public void setOnSurfaceListener(GLSurfaceListener mSurfaceListener) {
        this.mOnSurfaceListener = mSurfaceListener;
        mRenderer.setSurfaceListener(this.mOnSurfaceListener);
    }

    public void setEffect(ShaderInterface shaderEffect) {
        if (shaderEffect != null) {
            mEffect = shaderEffect;
            mRenderer.setEffect(mEffect);
        }
    }

    public void setMVPMatrix(float[] MVPMatrix) {
        if (MVPMatrix != null) {
            mMVPMatrix = MVPMatrix;
            mRenderer.setMVPMatrix(MVPMatrix);
        }
    }

    public void takeShotPic() {
        mRenderer.takeShotPic();
    }


    public void setVideoShotListener(VideoShotListener listener, boolean high) {
        this.mRenderer.setVideoShotListener(listener, high);
    }

    public int getMode() {
        return mMode;
    }

    /**
     * @param mode MODE_LAYOUT_SIZE = 0,  MODE_RENDER_SIZE = 1
     */
    public void setMode(int mode) {
        this.mMode = mode;
    }

    public void releaseAll() {
        if (mRenderer != null) {
            mRenderer.releaseAll();
        }
    }

    public VideoGLViewBaseRender getRenderer() {
        return mRenderer;
    }

    public ShaderInterface getEffect() {
        return mEffect;
    }

    public float[] getMVPMatrix() {
        return mMVPMatrix;
    }

    /**
     * 添加播放的view
     */
    public static VideoGLView addGLView(final Context context, final ViewGroup textureViewContainer, final int rotate,
                                        final ISurfaceListener SurfaceListener,
                                        final MeasureHelper.MeasureFormVideoParamsListener videoParamsListener,
                                        final VideoGLView.ShaderInterface effect, final float[] transform,
                                        final VideoGLViewBaseRender customRender, final int renderMode) {
        if (textureViewContainer.getChildCount() > 0) {
            textureViewContainer.removeAllViews();
        }
        final VideoGLView VideoGLView = new VideoGLView(context);
        if (customRender != null) {
            VideoGLView.setCustomRenderer(customRender);
        }
        VideoGLView.setEffect(effect);
        VideoGLView.setVideoParamsListener(videoParamsListener);
        VideoGLView.setRenderMode(renderMode);
        VideoGLView.setISurfaceListener(SurfaceListener);
        VideoGLView.setRotation(rotate);
        VideoGLView.initRender();
        VideoGLView.setVideoGLRenderErrorListener(new VideoGLRenderErrorListener() {
            @Override
            public void onError(VideoGLViewBaseRender render, String Error, int code, boolean byChangedRenderError) {
                if (byChangedRenderError)
                    addGLView(context,
                            textureViewContainer,
                            rotate,
                            SurfaceListener,
                            videoParamsListener,
                            render.getEffect(),
                            render.getMVPMatrix(),
                            render, renderMode);

            }
        });
        if (transform != null && transform.length == 16) {
            VideoGLView.setMVPMatrix(transform);
        }
        RenderView.addToParent(textureViewContainer, VideoGLView);
        return VideoGLView;
    }


}
