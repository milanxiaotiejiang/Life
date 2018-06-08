package com.seabreeze.life.common.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.graphics.Palette;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.seabreeze.life.R;
import com.seabreeze.life.common.image.GlideCircleTransform;
import com.seabreeze.life.common.image.ImageLoader;
import com.seabreeze.life.entity.music.TrackBean;
import com.seabreeze.life.widget.LrcView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class CoverFlowAdapter extends PagerAdapter {

    private List<TrackBean> trackBeans;
    private LayoutInflater inflater;
    private Context context;
    private List<WeakReference<View>> references;

    public CoverFlowAdapter(Context context, List<TrackBean> trackBeans) {
        this.context = context;
        this.trackBeans = trackBeans;
        this.inflater = LayoutInflater.from(context);
        this.references = new ArrayList<>();
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = null;
        if (references.size() > 0) {
            if (references.get(0) != null) {
                view = initView(references.get(0).get(), position);
                references.remove(0);
            }
        }
        view = initView(null, position);
        container.addView(view);
        return view;
    }

    private View initView(View view, int position) {
        MyHolder holder = null;
        if (view == null) {
            view = inflater.inflate(R.layout.play_music_item, null);
            holder = new MyHolder(view);
            view.setTag(holder);
        } else {
            holder = (MyHolder) view.getTag();
        }
        if (trackBeans != null && position < trackBeans.size()) {
            TrackBean trackBean = trackBeans.get(position);
            ImageLoader.getInstance().loadImage(context, holder.bgImage, trackBean.getRes_cover(), new GlideCircleTransform());
        }
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        if (object instanceof View) {
            View view = (View) object;
            container.removeView(view);
            references.add(new WeakReference<View>(view));
        }
    }

    @Override
    public int getCount() {
        return trackBeans.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    public static final class MyHolder {
        public final ImageView bgImage;
        public final ImageView collection;
        public final ImageView download;
        public final ImageView share;

        public View view;

        public MyHolder(View itemView) {
            view = itemView;
            bgImage = view.findViewById(R.id.cover_img);
            collection = view.findViewById(R.id.collect_icon);
            download = view.findViewById(R.id.download_icon);
            share = view.findViewById(R.id.share_icon);
        }
    }


}
