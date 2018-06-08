package com.seabreeze.life.common.image;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.TransitionOptions;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.vr.sdk.widgets.pano.VrPanoramaView;
import com.robot.seabreeze.log.Log;
import com.seabreeze.life.R;

public class ImageLoader {

    public static final String ANDROID_RESOURCE = "android.resource://";
    public static final String FOREWARD_SLASH = "/";

    private VrPanoramaView.Options options;

    private static class ImageLoaderHolder {
        private static final ImageLoader INSTANCE = new ImageLoader();
    }

    private ImageLoader() {
        options = new VrPanoramaView.Options();
        //设置图片类型为单通道图片
        options.inputType = VrPanoramaView.Options.TYPE_MONO;
    }

    public static ImageLoader getInstance() {
        return ImageLoaderHolder.INSTANCE;
    }

    public void loadVrImage(Context context, final VrPanoramaView vrView, String url) {
        Glide.with(context)
                .asBitmap()
                .load(url)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                        if (resource != null) {
                            vrView.loadImageFromBitmap(resource, options);
                        }
                    }
                });
    }

    public void loadImage(Context context, ImageView imageView, String url, @NonNull Transformation<Bitmap> transformation) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .transform(transformation);

        Glide.with(context)
                .load(url)
                .apply(options)
                .transition(new DrawableTransitionOptions().crossFade())
                .into(imageView);
    }

    /**
     * 加载优先级最高，跳过内存，不使用磁盘缓存，圆角变换，默认加载动画
     */
    public void loadImage(Context context, ImageView imageView, String url) {
        loadImage(context, imageView, url, R.drawable.mask, R.drawable.mask, Priority.HIGH, true,
                DiskCacheStrategy.NONE, new GlideRoundTransform(), new DrawableTransitionOptions().crossFade());
    }

    public void loadBanner(Context context, ImageView imageView, String url) {
        loadImage(context, imageView, url, R.drawable.mask, R.drawable.mask, Priority.HIGH, true,
                DiskCacheStrategy.RESOURCE, new GlideRoundTransform(), new DrawableTransitionOptions().crossFade());
    }


    /**
     * @param context        上下文
     * @param imageView      图片展示控件
     * @param model          加载资源
     * @param placeholder    请求图片加载中
     * @param error          请求图片加载错误
     * @param priority       优先级，设置图片加载的顺序
     * @param skip           是否跳过内存，true跳过
     * @param strategy       磁盘缓存策略
     * @param transformation 图片变换
     *                       <p>
     *                       DiskCacheStrategy.ALL           使用DATA和RESOURCE缓存远程数据，仅使用RESOURCE来缓存本地数据。
     *                       DiskCacheStrategy.NONE          不使用磁盘缓存
     *                       DiskCacheStrategy.DATA          在资源解码前就将原始数据写入磁盘缓存
     *                       DiskCacheStrategy.RESOURCE      在资源解码后将数据写入磁盘缓存，即经过缩放等转换后的图片资源。
     *                       DiskCacheStrategy.AUTOMATIC     根据原始图片数据和资源编码策略来自动选择磁盘缓存策略。
     *                       <p>
     *                       Priority.LOW
     *                       Priority.NORMAL
     *                       Priority.HIGH
     *                       Priority.IMMEDIATE
     */
    public void loadImage(Context context, ImageView imageView, @Nullable Object model,
                          int placeholder, int error, @NonNull Priority priority, boolean skip,
                          @NonNull DiskCacheStrategy strategy,
                          @NonNull Transformation<Bitmap> transformation,
                          @NonNull TransitionOptions transitionOptions) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(placeholder)
                .error(error)
                .priority(priority)
                .skipMemoryCache(skip)
                .diskCacheStrategy(strategy)
                .transform(transformation);

        Glide.with(context)
                .load(model)
                .apply(options)
                .transition(transitionOptions)
                .into(imageView);

    }

    /**
     * 清理内存中的缓存。
     *
     * @param context
     */
    public void clearMemory(Context context) {
        Glide.get(context).clearMemory();
    }

    /**
     * 清理硬盘中的缓存
     *
     * @param context
     */
    public void clearDiskCache(Context context) {
        Glide.get(context).clearDiskCache();
    }

//    Glide.with(context).resumeRequests()和 Glide.with(context).pauseRequests()

    public void resumeRequests(Context context) {
        Glide.with(context).resumeRequests();
    }

    public void pauseRequests(Context context) {
        Glide.with(context).pauseRequests();
    }

}