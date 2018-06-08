package com.seabreeze.life.common.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.seabreeze.life.R;
import com.seabreeze.life.common.image.ImageLoader;
import com.seabreeze.life.entity.essay.DataBean;
import com.seabreeze.life.utils.DateUtils;

import java.util.List;

public class EssayAdapter extends BaseQuickAdapter<DataBean, BaseViewHolder> {

    public EssayAdapter(@Nullable List<DataBean> data) {
        super(R.layout.essay_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DataBean item) {
        if (item.getTag_list() != null) {
            helper.setText(R.id.type, item.getTag_list().size() == 0 ? "- 阅读 -" : "- " + item.getTag_list().get(0).getTitle() + " -");
        }
        helper.setText(R.id.title, item.getTitle());
        helper.setText(R.id.author, "文 / " + item.getAuthor().getUser_name());
        helper.setText(R.id.subhead, item.getForward());
        helper.setText(R.id.post_time, DateUtils.formatDate2String(item.getPost_date()));


        ImageLoader.getInstance().loadImage(mContext, (ImageView) helper.getView(R.id.image), item.getImg_url());
    }
}
