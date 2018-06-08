package com.seabreeze.life.common.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.seabreeze.life.R;
import com.seabreeze.life.common.api.UrlManager;
import com.seabreeze.life.common.image.ImageLoader;
import com.seabreeze.life.entity.kuula.data.PostsBean;

import java.util.List;

public class KuulaAdapter extends BaseQuickAdapter<PostsBean, BaseViewHolder> {

    public KuulaAdapter(@Nullable List<PostsBean> data) {
        super(R.layout.panorama_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PostsBean item) {
        helper.setText(R.id.title, item.getUser().getName());
        helper.setText(R.id.description, item.getDescription());

        helper.setText(R.id.dialogue_tv, item.getComments() + "");
        if (item.getComments() > 0) {
            helper.setBackgroundRes(R.id.dialogue_img, R.mipmap.dialogue);
        } else {
            helper.setBackgroundRes(R.id.dialogue_img, R.mipmap.dialogue_default);
        }

        helper.setText(R.id.thumbs_tv, item.getLikes() + "");
        if (item.getLikes() > 0) {
            helper.setBackgroundRes(R.id.thumbs_img, R.mipmap.thumbs);
        } else {
            helper.setBackgroundRes(R.id.thumbs_img, R.mipmap.thumbs);
        }

        helper.setText(R.id.see_tv, item.getViews() + "");
        helper.setBackgroundRes(R.id.see_img, R.mipmap.see);

        ImageLoader.getInstance().loadImage(mContext, (ImageView) helper.getView(R.id.img), UrlManager.getKuulaCover(item.getUuid()));
    }
}
