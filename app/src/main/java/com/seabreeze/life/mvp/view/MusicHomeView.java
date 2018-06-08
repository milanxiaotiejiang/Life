package com.seabreeze.life.mvp.view;

import com.seabreeze.life.common.base.mvp.BaseView;
import com.seabreeze.life.entity.music.BannerListBean;
import com.seabreeze.life.entity.music.MusicBean;
import com.seabreeze.life.entity.music.MusicListBean;
import com.seabreeze.life.entity.video.VideoListBean;

import java.util.List;

import okhttp3.ResponseBody;

public interface MusicHomeView extends BaseView {

    void onMusicListBeanSuccess(MusicBean musicBean);

    void onMusicListBeanError(Throwable throwable);

    void onBannerListSuccess(BannerListBean bannerListBean);

    void onBannerListError(Throwable throwable);
}
