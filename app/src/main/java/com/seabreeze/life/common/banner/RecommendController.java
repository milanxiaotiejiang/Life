package com.seabreeze.life.common.banner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.DynamicPagerAdapter;
import com.robot.seabreeze.log.Log;
import com.seabreeze.life.R;
import com.seabreeze.life.common.image.ImageLoader;
import com.seabreeze.life.entity.music.BannerListBean;

import java.util.List;

public class RecommendController {

    private Context mContext;
    private LayoutInflater inflater;
    private View contentView;
    private RollPagerView rollPagerView;

    public RecommendController(Context context) {
        this.mContext = context;
        init();
    }

    private void init() {
        inflater = LayoutInflater.from(mContext);
        contentView = inflater.inflate(R.layout.banner, null);
        rollPagerView = contentView.findViewById(R.id.roll_pager_view);
    }

    public View getContentView() {
        return contentView;
    }

    public void setData(List<BannerListBean.DataBean> data) {
        rollPagerView.setAdapter(new BannerAdapter(data));
    }

    class BannerAdapter extends DynamicPagerAdapter {

        private List<BannerListBean.DataBean> data;

        public BannerAdapter(List<BannerListBean.DataBean> data) {
            this.data = data;
        }

        @Override
        public View getView(ViewGroup container, int position) {
            View view = inflater.inflate(R.layout.item_gallery_layout, null);
            ImageView imageView = view.findViewById(R.id.item_head_gallery_img);

            ImageLoader.getInstance().loadBanner(mContext, imageView, data.get(position).getRes_cover());
            return view;
        }

        @Override
        public int getCount() {
            return data.size();
        }

    }
}
