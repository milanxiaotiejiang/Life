package com.seabreeze.life.common.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.seabreeze.life.R;
import com.seabreeze.life.common.image.ImageLoader;
import com.seabreeze.life.entity.music.TrackBean;
import com.seabreeze.life.utils.DateUtils;

import java.util.List;

public class MusicTrackAdapter extends BaseQuickAdapter<TrackBean, BaseViewHolder> {

    public MusicTrackAdapter(@Nullable List<TrackBean> data) {
        super(R.layout.item_music_track, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TrackBean item) {
        helper.setText(R.id.num, String.valueOf(helper.getLayoutPosition() - getHeaderLayoutCount() + 1));
        helper.setText(R.id.songname, item.getSongname());
        helper.setText(R.id.singer, item.getSinger());
        helper.setText(R.id.time, DateUtils.formatTime2(item.getTime()));

        ImageLoader.getInstance().loadImage(mContext, (ImageView) helper.getView(R.id.cover), item.getRes_cover());

    }
}
