package com.seabreeze.life.common.ui.activity;

import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.asha.vrlib.MDDirectorCamUpdate;
import com.asha.vrlib.MDVRLibrary;
import com.asha.vrlib.texture.MD360BitmapTexture;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.robot.seabreeze.log.Log;
import com.seabreeze.life.R;
import com.seabreeze.life.common.base.BaseActivity;
import com.seabreeze.life.common.vr.CustomProjectionFactory;
import com.seabreeze.life.entity.kuula.KuulaBean;
import com.seabreeze.life.mvp.persenter.impl.PanoramaDetailPresenterImpl;
import com.seabreeze.life.mvp.view.PanoramaDetailView;
import com.seabreeze.life.widget.SmileyLoadingView;
import com.seabreeze.life.widget.like.LikeButtonView;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class VrBitmapActivity extends BaseActivity<PanoramaDetailPresenterImpl> implements PanoramaDetailView {

    public static final String POSTS_ID = "posts_id";

    @BindView(R.id.glSurfaceView)
    RelativeLayout glSurfaceView;
    @BindView(R.id.sensor)
    ImageView sensor;
    @BindView(R.id.mode)
    TextView mode;
    @BindView(R.id.glass)
    TextView glass;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.description)
    TextView description;
    @BindView(R.id.loading)
    SmileyLoadingView loading;
    @BindView(R.id.like_icon)
    LikeButtonView likeIcon;
    @BindView(R.id.like_number)
    TextView likeNumber;
    @BindView(R.id.collect)
    ImageView collect;
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.comment)
    ImageView comment;
    @BindView(R.id.comment_number)
    TextView commentNumber;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.reset_scene)
    ImageView reset;

    private String mUrl;

    private MDVRLibrary mVRLibrary;
    private MD360BitmapTexture.Callback mCallback;

    private boolean isNormal;
    private boolean sensorOpen;
    private boolean isGlass;

    private ValueAnimator animator;

    private String id;
    private KuulaBean.PayloadBean mPayload;
    private int count = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        noTitle();
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_panorama_detail;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {
        id = getIntent().getStringExtra(POSTS_ID);
        if (id == null) {
            return;
        }

        loading.setVisibility(View.VISIBLE);
        loading.start();
        mPresenter.getVrCategoryData(id);

        likeIcon.setOnLikeClickListener(new LikeButtonView.OnLikeClickListener() {
            @Override
            public void onLikeClickListener(boolean isChecked) {
                int like = Integer.valueOf((String) likeNumber.getText());
                if (isChecked) {
                    likeNumber.setText(String.format("%d", like + 1));
                } else {
                    likeNumber.setText(String.format("%d", like - 1));
                }
            }
        });

        GLSurfaceView myGLSurfaceView = new GLSurfaceView(this);
        mVRLibrary = MDVRLibrary.with(this)
                .displayMode(MDVRLibrary.DISPLAY_MODE_NORMAL)
                .interactiveMode(MDVRLibrary.INTERACTIVE_MODE_TOUCH)
                .asBitmap(new MDVRLibrary.IBitmapProvider() {
                    @Override
                    public void onProvideBitmap(MD360BitmapTexture.Callback callback) {
                        mCallback = callback;
                    }
                })
                .pinchEnabled(true)
                .projectionFactory(new CustomProjectionFactory())
                .build(myGLSurfaceView);
        glSurfaceView.addView(myGLSurfaceView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mVRLibrary.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mVRLibrary.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mVRLibrary.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mVRLibrary.onOrientationChanged(this);
    }

    @Override
    public void onPanoramaDetailSuccess(KuulaBean.PayloadBean payload) {

        mPayload = payload;

        KuulaBean.PayloadBean.UserBean user = payload.getUser();
        if (user != null) {
            userName.setText(user.getName());
        }
        description.setText(payload.getDescription());

        likeNumber.setText(String.format("%d", payload.getWholiked().size()));
        commentNumber.setText(String.format("%d", payload.getComments()));


        count = 0;
        loadPhoto();
    }

    private void loadPhoto() {
        if (mPayload == null) {
            return;
        }
        List<KuulaBean.PayloadBean.PhotosBean> photos = mPayload.getPhotos();
        if (photos == null || photos.size() == 0) {
            return;
        }

        KuulaBean.PayloadBean.PhotosBean photosBean = photos.get(0);
        List<String> urls = photosBean.getUrls();

        if (urls == null || urls.size() == 0) {
            List<String> sizes = photosBean.getSizes();
            if (sizes == null || sizes.size() == 0) {
                return;
            }
            if (count > sizes.size() - 1) {
                count = 0;
            }
            String size = sizes.get(count);
            this.mUrl = "https://files.kuula.io/" + mPayload.getUuid() + File.separator + mPayload.getCover() + "-" + size + ".jpg";

            loadImage(mUrl, mCallback);
            return;
        }

        if (count > urls.size() - 1) {
            count = 0;
        }

        mUrl = urls.get(count);

        loadImage(mUrl, mCallback);

    }

    private void loadImage(String photos, final MD360BitmapTexture.Callback callback) {
        if (photos == null) {
            showMsg("图片url不存在");
            return;
        }
        RequestOptions options = new RequestOptions()
                .override(2048, 1096);
        Glide.with(mContext)
                .asBitmap()
                .load(photos)
                .apply(options)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                        mVRLibrary.onTextureResize(resource.getWidth(), resource.getHeight());
                        callback.texture(resource);
                        loading.stop();
                        loading.setVisibility(View.GONE);
                    }

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        super.onLoadFailed(errorDrawable);
                        Log.e(errorDrawable);
                        loading.stop();
                        loading.setVisibility(View.GONE);
                        reset.setVisibility(View.VISIBLE);
                    }
                });
    }

    @Override
    public void onPanoramaDetailError(Throwable throwable) {
        Log.e(throwable);
        loading.stop();
        showMsg(throwable.getMessage());
    }

    @OnClick({R.id.mode, R.id.sensor, R.id.glass, R.id.back, R.id.reset_scene})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mode:
                if (isNormal) {
                    normalMode();
                    mode.setText("鱼眼");
                } else {
                    fishEyesMode();
                    mode.setText("普通");
                }
                break;
            case R.id.sensor:
                if (!sensorOpen) {
                    sensorOpen = true;
                    mVRLibrary.switchInteractiveMode(this, MDVRLibrary.INTERACTIVE_MODE_MOTION);
                    sensor.setImageResource(R.drawable.sensor_icon);
                } else {
                    sensorOpen = false;
                    mVRLibrary.switchInteractiveMode(this, MDVRLibrary.INTERACTIVE_MODE_TOUCH);
                    sensor.setImageResource(R.drawable.unsensor_icon);
                }
                break;
            case R.id.glass:
                if (isGlass) {
                    normalGlass();
                    glass.setText("vr模式");
                } else {
                    enableGlass();
                    glass.setText("普通模式");
                }
                break;
            case R.id.back:
                if (isGlass) {
                    normalGlass();
                    glass.setText("vr模式");
                    return;
                }
                finish();
                break;
            case R.id.reset_scene:
                reset.setVisibility(View.GONE);
                loading.setVisibility(View.VISIBLE);
                loading.start();
                count++;
                loadPhoto();
                break;
        }
    }

    private void enableGlass() {
        isGlass = !isGlass;
        changeScreenOrientation(isGlass);
        mVRLibrary.switchDisplayMode(this, MDVRLibrary.DISPLAY_MODE_GLASS);
        mVRLibrary.setAntiDistortionEnabled(true);
    }

    private void normalGlass() {
        isGlass = !isGlass;
        changeScreenOrientation(isGlass);
        mVRLibrary.switchDisplayMode(this, MDVRLibrary.DISPLAY_MODE_NORMAL);
        mVRLibrary.setAntiDistortionEnabled(false);
    }

    public void changeScreenOrientation(boolean isGlass) {
        if (isGlass) {
            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    private void fishEyesMode() {
        isNormal = !isNormal;
        MDDirectorCamUpdate camera = mVRLibrary.updateCamera();
        PropertyValuesHolder near = PropertyValuesHolder.ofFloat("near", camera.getNearScale(), -0.6f);
        PropertyValuesHolder eyeZ = PropertyValuesHolder.ofFloat("eyeZ", camera.getEyeZ(), 18f);
        PropertyValuesHolder pitch = PropertyValuesHolder.ofFloat("pitch", camera.getPitch(), 45f);
        PropertyValuesHolder yaw = PropertyValuesHolder.ofFloat("yaw", camera.getYaw(), 45f);
        PropertyValuesHolder roll = PropertyValuesHolder.ofFloat("roll", camera.getRoll(), 0f);
        startCameraAnimation(camera, near, eyeZ, pitch, yaw, roll);
    }

    private void normalMode() {
        isNormal = !isNormal;
        MDDirectorCamUpdate camera = mVRLibrary.updateCamera();
        PropertyValuesHolder near = PropertyValuesHolder.ofFloat("near", camera.getNearScale(), 0f);
        PropertyValuesHolder eyeZ = PropertyValuesHolder.ofFloat("eyeZ", camera.getEyeZ(), 0f);
        PropertyValuesHolder pitch = PropertyValuesHolder.ofFloat("pitch", camera.getPitch(), 0f);
        PropertyValuesHolder yaw = PropertyValuesHolder.ofFloat("yaw", camera.getYaw(), 0f);
        PropertyValuesHolder roll = PropertyValuesHolder.ofFloat("roll", camera.getRoll(), 0f);
        startCameraAnimation(camera, near, eyeZ, pitch, yaw, roll);
    }

    private void startCameraAnimation(final MDDirectorCamUpdate camera, PropertyValuesHolder... values) {
        if (animator != null)
            animator.cancel();

        animator = ValueAnimator.ofPropertyValuesHolder(values).setDuration(2000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float near = (float) animation.getAnimatedValue("near");
                float eyeZ = (float) animation.getAnimatedValue("eyeZ");
                float pitch = (float) animation.getAnimatedValue("pitch");
                float yaw = (float) animation.getAnimatedValue("yaw");
                float roll = (float) animation.getAnimatedValue("roll");
                camera.setEyeZ(eyeZ).setNearScale(near).setPitch(pitch).setYaw(yaw).setRoll(roll);
            }
        });
        animator.start();
    }

    @Override
    public void onBackPressedSupport() {
        if (isGlass) {
            normalGlass();
            glass.setText("vr模式");
            return;
        }
        super.onBackPressedSupport();
    }


}
