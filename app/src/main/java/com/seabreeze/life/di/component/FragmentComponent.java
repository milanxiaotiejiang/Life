package com.seabreeze.life.di.component;

import android.app.Activity;

import com.seabreeze.life.common.ui.fragment.essay.EssayDetailFragment;
import com.seabreeze.life.common.ui.fragment.essay.EssayHomeFragment;
import com.seabreeze.life.common.ui.fragment.misic.MusicDetailFragment;
import com.seabreeze.life.common.ui.fragment.misic.MusicHomeFragment;
import com.seabreeze.life.common.ui.fragment.panorama.PanoramaHomeFragment;
import com.seabreeze.life.common.ui.fragment.veer.VeerHomeFragment;
import com.seabreeze.life.common.ui.fragment.video.VideoDetailFragment;
import com.seabreeze.life.common.ui.fragment.video.VideoHomeFragment;
import com.seabreeze.life.di.module.FragmentModule;
import com.seabreeze.life.di.scope.PerFragment;

import dagger.Component;

@PerFragment
@Component(modules = FragmentModule.class, dependencies = AppComponent.class)
public interface FragmentComponent {

    Activity getActivity();

    void inject(VideoHomeFragment videoHomeFragment);

    void inject(MusicHomeFragment musicHomeFragment);

    void inject(EssayHomeFragment essayHomeFragment);

    void inject(VeerHomeFragment veerHomeFragment);

    void inject(PanoramaHomeFragment panoramaHomeFragment);

    void inject(VideoDetailFragment videoDetailFragment);

    void inject(MusicDetailFragment musicDetailFragment);

    void inject(EssayDetailFragment essayHomeDetailFragment);

}
