package com.seabreeze.life.common.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.seabreeze.life.R;
import com.seabreeze.life.common.image.GlideRoundTransform;
import com.seabreeze.life.common.image.ImageLoader;
import com.seabreeze.life.entity.video.data.TagsBean;

import java.util.List;

public class VideoTagsAdapter extends BaseQuickAdapter<TagsBean, BaseViewHolder> {

    public VideoTagsAdapter(@Nullable List<TagsBean> data) {
        super(R.layout.item_video_tags, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TagsBean item) {
        helper.setText(R.id.title, "#" + item.getName() + "#");
        ImageLoader.getInstance().loadImage(mContext, (ImageView) helper.getView(R.id.img), item.getBgPicture(),
                new GlideRoundTransform(12));
    }
}
