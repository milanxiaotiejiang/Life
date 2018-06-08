package com.seabreeze.life.common.ui.fragment.misic;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.seabreeze.life.R;
import com.seabreeze.life.common.base.MainFragment;

public class MusicFragment extends MainFragment {

    public static MusicFragment newInstance() {
        Bundle bundle = new Bundle();
        MusicFragment musicFragment = new MusicFragment();
        musicFragment.setArguments(bundle);
        return musicFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.music_fragment;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.fl_first_container, MusicHomeFragment.newInstance());
        }
    }
}
