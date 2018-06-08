package com.seabreeze.life.mvp.view;

import com.seabreeze.life.common.base.mvp.BaseView;
import com.seabreeze.life.entity.music.TrackBean;
import com.seabreeze.life.entity.video.VideoListBean2;

import java.util.List;

import okhttp3.ResponseBody;

public interface MusicDetailView extends BaseView {

    void onMusicTrackSuccess(List<TrackBean> trackBeans);

    void onMusicTrackError(Throwable throwable);
}
