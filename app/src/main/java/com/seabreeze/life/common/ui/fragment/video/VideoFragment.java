package com.seabreeze.life.common.ui.fragment.video;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.seabreeze.life.R;
import com.seabreeze.life.common.base.MainFragment;

public class VideoFragment extends MainFragment {

    public static VideoFragment newInstance() {
        Bundle bundle = new Bundle();
        VideoFragment videoFragment = new VideoFragment();
        videoFragment.setArguments(bundle);
        return videoFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.video_fragment;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.fl_first_container, VideoHomeFragment.newInstance());
        }
    }
}
