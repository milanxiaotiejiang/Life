package com.seabreeze.life.common.ui.fragment.panorama;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.seabreeze.life.R;
import com.seabreeze.life.common.base.MainFragment;

public class PanoramaFragment extends MainFragment {

    public static PanoramaFragment newInstance() {
        Bundle bundle = new Bundle();
        PanoramaFragment panoramaFragment = new PanoramaFragment();
        panoramaFragment.setArguments(bundle);
        return panoramaFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.panorama_fragment;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.fl_first_container, PanoramaHomeFragment.newInstance());
        }
    }
}
