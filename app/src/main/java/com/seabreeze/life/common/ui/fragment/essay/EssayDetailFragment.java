package com.seabreeze.life.common.ui.fragment.essay;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.seabreeze.life.R;
import com.seabreeze.life.common.base.BaseDetailFragment;
import com.seabreeze.life.common.base.BaseFragment;
import com.seabreeze.life.entity.essay.DataBean;
import com.seabreeze.life.entity.essay.EssayDetailBean;
import com.seabreeze.life.entity.essay.EssayListBean;
import com.seabreeze.life.mvp.persenter.impl.EssayDetailPresenterImpl;
import com.seabreeze.life.mvp.view.EssayDetailView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.ResponseBody;

public class EssayDetailFragment extends BaseDetailFragment<DataBean, EssayDetailPresenterImpl> implements EssayDetailView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.author)
    TextView author;
    @BindView(R.id.web_view)
    WebView webView;

    public static EssayDetailFragment newInstance(DataBean dataBean) {
        Bundle args = new Bundle();
        args.putParcelable(ARG_ITEM, dataBean);
        EssayDetailFragment detailFragment = new EssayDetailFragment();
        detailFragment.setArguments(args);
        return detailFragment;
    }

    private int id;
    private int sourceId;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {
        toolbar.setTitle("一个阅读");
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        initToolbarNav(toolbar);

        sourceId = mDataBean.getItem_id();
        id = mDataBean.getId();
        mPresenter.getEssayDetailData(sourceId, id);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.essay_home_detail_fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onEssayDetailSuccess(EssayDetailBean detailBean) {
        if (detailBean.getRes() == 0) {
            EssayDetailBean.DataBean dataBean = detailBean.getData();

            title.setText(dataBean.getHp_title());
            author.setText("文 / " + dataBean.getHp_author());
            String html = dataBean.getHp_content();

            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setBlockNetworkImage(false);

            webView.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);
        }
    }

    @Override
    public void onEssayDetailError(Throwable throwable) {

    }
}
