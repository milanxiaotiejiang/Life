package com.seabreeze.life.common.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.robot.seabreeze.log.Log;
import com.seabreeze.life.R;
import com.seabreeze.life.common.image.ImageLoader;
import com.seabreeze.life.entity.video.DataBean;
import com.seabreeze.life.entity.video.ItemListBean;
import com.seabreeze.life.entity.video.data.CoverBean;
import com.seabreeze.life.utils.DateUtils;

import java.util.List;

public class VideoAdapter extends BaseQuickAdapter<ItemListBean, BaseViewHolder> {
    public VideoAdapter(@Nullable List<ItemListBean> data) {
        super(R.layout.video_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ItemListBean item) {
        DataBean itemData = item.getData();
        helper.setText(R.id.title, itemData.getTitle());
        helper.setText(R.id.description, itemData.getDescription());

        String category = itemData.getCategory();
        String time = DateUtils.formatTime2((long) itemData.getDuration());
        helper.setText(R.id.time, "#" + category + " / " + time);

        CoverBean coverBean = itemData.getCover();
        if (coverBean != null) {
            ImageLoader.getInstance().loadImage(mContext, (ImageView) helper.getView(R.id.img), coverBean.getDetail());
        }
    }
}
