package com.seabreeze.life.common.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.vr.sdk.widgets.pano.VrPanoramaView;
import com.robot.seabreeze.log.Log;
import com.seabreeze.life.App;
import com.seabreeze.life.R;
import com.seabreeze.life.common.image.GlideCircleTransform;
import com.seabreeze.life.common.image.ImageLoader;
import com.seabreeze.life.entity.veer.DataBean;
import com.seabreeze.life.entity.veer.data.ProfileBean;
import com.seabreeze.life.entity.veer.data.TagsBean;
import com.seabreeze.life.entity.veer.data.UserBean;
import com.seabreeze.life.utils.DateUtils;
import com.seabreeze.life.utils.RxSchedulers;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class VeerAdapter extends BaseQuickAdapter<DataBean, BaseViewHolder> {

    public VeerAdapter(@Nullable List<DataBean> data) {
        super(R.layout.veer_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DataBean item) {
        helper.setText(R.id.title, item.getTitle());
        helper.setText(R.id.description, item.getInfo());
        helper.setText(R.id.post_time, DateUtils.formatDate2String(item.getCreated_at()));
        helper.setText(R.id.tv_view_count, String.valueOf(item.getView_count()));

        UserBean userBean = item.getUser();
        if (userBean != null) {
            ProfileBean profile = userBean.getProfile();
            if (profile != null) {
                ImageLoader.getInstance().loadImage(mContext, (ImageView) helper.getView(R.id.head_img),
                        profile.getAvatar_url(), new GlideCircleTransform());
            }
            helper.setText(R.id.name, userBean.getUsername());
        }

        helper.addOnClickListener(R.id.ic_play);

        final VrPanoramaView vrView = helper.getView(R.id.vr_view);

        vrView.setInfoButtonEnabled(false);//信息按钮禁掉
        vrView.setStereoModeButtonEnabled(false);//眼镜模式按钮禁掉
        vrView.setFullscreenButtonEnabled(false);//全屏模式按钮禁掉
        vrView.setTouchTrackingEnabled(true); //开启手触模式

        TagFlowLayout tagFlowLayout = helper.getView(R.id.tag_flow_layout);

        ImageLoader.getInstance().loadVrImage(mContext, vrView, item.getThumbnail().getUrl_vr());

        List<TagsBean> tagsBeans = item.getTags();
        if (tagsBeans != null) {
            List<TagsBean> beans = new ArrayList<>();

            for (int i = 0; i < tagsBeans.size(); i++) {
                if (i == 3) {
                    break;
                }
                beans.add(tagsBeans.get(i));
            }

            HotAdapter hotAdapter = new HotAdapter(beans);
            tagFlowLayout.setAdapter(hotAdapter);
        }
    }

    class HotAdapter extends TagAdapter<TagsBean> {
        private LayoutInflater mInflater;

        public HotAdapter(List<TagsBean> datas) {
            super(datas);
            this.mInflater = LayoutInflater.from(mContext);
        }

        @Override
        public View getView(FlowLayout parent, int position, TagsBean tagsBean) {
            View view = mInflater.inflate(R.layout.item_hot, parent, false);
            TextView tvTitle = view.findViewById(R.id.tvTitle);
            tvTitle.setText(" # " + tagsBean.getName());
            return view;
        }
    }
}
