package com.seabreeze.life.common.base;

import android.content.Context;

import com.seabreeze.life.common.ui.fragment.video.VideoFragment;


public abstract class MainFragment extends SimpleFragment {

    protected OnBackToFirstListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnBackToFirstListener) {
            mListener = (OnBackToFirstListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnBackToFirstListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public boolean onBackPressedSupport() {
        if (getChildFragmentManager().getBackStackEntryCount() > 1) {
            popChild();
        } else {
            if (this instanceof VideoFragment) {   // 如果是 第一个Fragment 则退出app
                _mActivity.finish();
            } else {                                    // 如果不是,则回到第一个Fragment
                mListener.onBackToFirstFragment();
            }
        }
        return true;
    }

    public interface OnBackToFirstListener {
        void onBackToFirstFragment();
    }
}
