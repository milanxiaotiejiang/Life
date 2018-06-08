package com.seabreeze.life.common.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.seabreeze.life.R;
import com.seabreeze.life.common.image.ImageLoader;
import com.seabreeze.life.entity.music.DataBean;
import com.seabreeze.life.entity.music.MusicListBean;

import java.util.List;

public class MusicAdapter extends BaseQuickAdapter<DataBean, BaseViewHolder> {

    public MusicAdapter(@Nullable List<DataBean> data) {
        super(R.layout.music_item, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, DataBean item) {
        helper.setText(R.id.title, item.getNickname());
        helper.setText(R.id.description, item.getDescription());

        ImageLoader.getInstance().loadImage(mContext, (ImageView) helper.getView(R.id.img), item.getHeadimg());
    }
}
