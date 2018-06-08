package com.seabreeze.life.common.ui.fragment.essay;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.seabreeze.life.R;
import com.seabreeze.life.common.base.MainFragment;

public class EssayFragment extends MainFragment {

    public static EssayFragment newInstance() {
        Bundle bundle = new Bundle();
        EssayFragment essayFragment = new EssayFragment();
        essayFragment.setArguments(bundle);
        return essayFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.essay_fragment;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.fl_first_container, EssayHomeFragment.newInstance());
        }
    }
}
