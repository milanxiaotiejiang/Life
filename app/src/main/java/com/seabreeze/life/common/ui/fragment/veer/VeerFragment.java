package com.seabreeze.life.common.ui.fragment.veer;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.seabreeze.life.R;
import com.seabreeze.life.common.base.MainFragment;
import com.seabreeze.life.common.ui.fragment.panorama.PanoramaHomeFragment;

public class VeerFragment extends MainFragment {

    public static VeerFragment newInstance() {
        Bundle bundle = new Bundle();
        VeerFragment veerFragment = new VeerFragment();
        veerFragment.setArguments(bundle);
        return veerFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.veer_fragment;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.fl_first_container, VeerHomeFragment.newInstance());
        }
    }
}
