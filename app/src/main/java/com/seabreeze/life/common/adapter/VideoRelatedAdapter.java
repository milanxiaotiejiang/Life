package com.seabreeze.life.common.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.seabreeze.life.R;
import com.seabreeze.life.common.image.ImageLoader;
import com.seabreeze.life.entity.video.DataBean;
import com.seabreeze.life.entity.video.data.CoverBean;
import com.seabreeze.life.utils.DateUtils;

import java.util.List;

public class VideoRelatedAdapter extends BaseQuickAdapter<DataBean, BaseViewHolder> {

    public VideoRelatedAdapter(@Nullable List<DataBean> data) {
        super(R.layout.item_video_related, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DataBean item) {
        helper.setText(R.id.title, item.getTitle());
        helper.setText(R.id.tip, "#" + item.getCategory());

        String time = DateUtils.formatTime2((long) item.getDuration());
        helper.setText(R.id.time, time);

        ImageLoader.getInstance().loadImage(mContext, (ImageView) helper.getView(R.id.img), item.getCoverForFeed());
    }
}
