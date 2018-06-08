package com.seabreeze.life.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;

import com.seabreeze.life.R;
import com.seabreeze.life.common.ui.activity.PlayActivity;
import com.seabreeze.life.common.ui.activity.PlayMusicActivity;
import com.seabreeze.life.entity.music.TrackBean;
import com.seabreeze.life.entity.video.DataBean;

import java.util.ArrayList;
import java.util.List;

public class JumpUtils {

    /**
     * 跳转到视频播放
     *
     * @param activity
     * @param view
     */
    public static void goToVideoPlayer(Activity activity, View view, DataBean dataBean) {
        Intent intent = new Intent(activity, PlayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(PlayActivity.DATA, dataBean);
        intent.putExtras(bundle);
        intent.putExtra(PlayActivity.TRANSITION, true);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Pair pair = new Pair<>(view, PlayActivity.IMG_TRANSITION);
            ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pair);
            ActivityCompat.startActivity(activity, intent, activityOptions.toBundle());
        } else {
            activity.startActivity(intent);
            activity.overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
        }
    }

    public static void goToMusicPlayer(Activity activity, View view, com.seabreeze.life.entity.music.DataBean dataBean, List<TrackBean> trackBeans) {
        Intent intent = new Intent(activity, PlayMusicActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(PlayMusicActivity.DATA, dataBean);
        bundle.putParcelableArrayList(PlayMusicActivity.TRACK, (ArrayList<? extends Parcelable>) trackBeans);
        intent.putExtras(bundle);
        intent.putExtra(PlayActivity.TRANSITION, true);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Pair pair = new Pair<>(view, PlayActivity.IMG_TRANSITION);
            ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    activity, pair);
            ActivityCompat.startActivity(activity, intent, activityOptions.toBundle());
        } else {
            activity.startActivity(intent);
            activity.overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
        }
    }

}
