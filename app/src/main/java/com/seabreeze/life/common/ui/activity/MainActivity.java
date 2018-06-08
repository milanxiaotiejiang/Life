package com.seabreeze.life.common.ui.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.jaeger.library.StatusBarUtil;
import com.robot.seabreeze.log.Log;
import com.seabreeze.life.R;
import com.seabreeze.life.common.base.MainFragment;
import com.seabreeze.life.common.base.SimpleActivity;
import com.seabreeze.life.common.base.rx.RxSupportFragment;
import com.seabreeze.life.common.event.MessageEvent;
import com.seabreeze.life.common.ui.fragment.misic.MusicDetailFragment;
import com.seabreeze.life.common.ui.fragment.misic.MusicHomeFragment;
import com.seabreeze.life.common.ui.fragment.video.VideoDetailFragment;
import com.seabreeze.life.common.ui.fragment.video.VideoHomeFragment;
import com.seabreeze.life.utils.EventUtils;
import com.seabreeze.life.common.ui.fragment.essay.EssayFragment;
import com.seabreeze.life.common.ui.fragment.misic.MusicFragment;
import com.seabreeze.life.common.ui.fragment.panorama.PanoramaFragment;
import com.seabreeze.life.common.ui.fragment.veer.VeerFragment;
import com.seabreeze.life.common.ui.fragment.video.VideoFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.yokeyword.fragmentation.ISupportFragment;

public class MainActivity extends SimpleActivity implements MainFragment.OnBackToFirstListener,
        NavigationView.OnNavigationItemSelectedListener, FloatingSearchView.OnMenuItemClickListener,
        View.OnClickListener {

    @BindView(R.id.floating_search_view)
    FloatingSearchView floatingSearchView;
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;
    @BindView(R.id.activity_main)
    CoordinatorLayout activityMain;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private ImageView headerImage;
    private TextView headerName;
    private TextView headerTagline;

    private List<RxSupportFragment> mFragments = new ArrayList<>();

    private String[] titles = {"movie", "music", "essay", "veer", "panorama"};

    public static final int SEARCH_HIDE = 0;
    public static final int SEARCH_SHOW = 1;
    public static final int SHOW = 2;
    public static final int HIDE = 3;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventAndData() {

        initSearchView();

        initMenu();

        initFragment();

        initBottomNavigationItem();

    }

    private void initSearchView() {
        floatingSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, String newQuery) {
                Log.e("oldQuery : " + oldQuery + " , newQuery : " + newQuery);
            }
        });

        floatingSearchView.setOnMenuItemClickListener(this);

        floatingSearchView.attachNavigationDrawerToMenuButton(drawerLayout);
    }

    private void initMenu() {
        navView.setNavigationItemSelectedListener(this);

        View headerView = navView.getHeaderView(0);
        headerImage = headerView.findViewById(R.id.header_image);
        headerName = headerView.findViewById(R.id.header_name);
        headerTagline = headerView.findViewById(R.id.header_tagline);
        headerImage.setOnClickListener(this);
        headerName.setOnClickListener(this);
    }

    private void initFragment() {
        mFragments = new ArrayList<>();
        mFragments.add(VideoFragment.newInstance());
        mFragments.add(MusicFragment.newInstance());
        mFragments.add(EssayFragment.newInstance());
        mFragments.add(VeerFragment.newInstance());
        mFragments.add(PanoramaFragment.newInstance());

        loadMultipleRootFragment(R.id.act_container, 0, mFragments.get(0), mFragments.get(1), mFragments.get(2), mFragments.get(3), mFragments.get(4));
        StatusBarUtil.setColor(mContext, getResources().getColor(R.color.color_movie));
    }

    private void initBottomNavigationItem() {
        BottomNavigationItem movieItem = new BottomNavigationItem(R.drawable.movie_icon, titles[0])
                .setActiveColorResource(R.color.color_movie)
                .setInActiveColor(R.color.inactive);

        BottomNavigationItem musicItem = new BottomNavigationItem(R.drawable.music_icon, titles[1])
                .setActiveColorResource(R.color.color_music);

        BottomNavigationItem essayItem = new BottomNavigationItem(R.drawable.essay_icon, titles[2])
                .setActiveColorResource(R.color.color_essay);

        BottomNavigationItem veerItem = new BottomNavigationItem(R.drawable.veer_icon, titles[3])
                .setActiveColorResource(R.color.color_veer);

        BottomNavigationItem panoramaItem = new BottomNavigationItem(R.drawable.panorama_icon, titles[4])
                .setActiveColorResource(R.color.color_panorama);

        bottomNavigationBar.addItem(movieItem)
                .addItem(musicItem)
                .addItem(essayItem)
                .addItem(veerItem)
                .addItem(panoramaItem)
                .initialise();

        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_SHIFTING);
        bottomNavigationBar.setBarBackgroundColor(R.color.transparent);
        bottomNavigationBar.setAutoHideEnabled(true);

        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                showHideFragment(mFragments.get(position));
                floatingSearchView.setVisibility(View.VISIBLE);
                switch (position) {
                    case 0:
                        StatusBarUtil.setColor(mContext, getResources().getColor(R.color.color_movie));
                        break;
                    case 1:
                        StatusBarUtil.setColor(mContext, getResources().getColor(R.color.color_music));
                        break;
                    case 2:
                        StatusBarUtil.setColor(mContext, getResources().getColor(R.color.color_essay));
                        break;
                    case 3:
                        StatusBarUtil.setColor(mContext, getResources().getColor(R.color.color_veer));
                        break;
                    case 4:
                        StatusBarUtil.setColor(mContext, getResources().getColor(R.color.color_panorama));
                        break;
                }
                if (position == 0) {
                    VideoFragment rxSupportFragment = (VideoFragment) mFragments.get(position);
                    ISupportFragment topChildFragment = rxSupportFragment.getTopChildFragment();
                    if (topChildFragment instanceof VideoHomeFragment) {
                        floatingSearchView.setVisibility(View.VISIBLE);
                    } else if (topChildFragment instanceof VideoDetailFragment) {
                        floatingSearchView.setVisibility(View.GONE);
                    }
                } else if (position == 1) {
                    MusicFragment rxSupportFragment = (MusicFragment) mFragments.get(position);
                    ISupportFragment topChildFragment = rxSupportFragment.getTopChildFragment();
                    if (topChildFragment instanceof MusicHomeFragment) {
                        floatingSearchView.setVisibility(View.VISIBLE);
                    } else if (topChildFragment instanceof MusicDetailFragment) {
                        floatingSearchView.setVisibility(View.GONE);
                    }
                } else if (position == 2) {
                }
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.header_image:
                showToast("header_image");
                break;
            case R.id.header_name:
                showToast("header_name");
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventUtils.register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventUtils.unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onResultEvent(MessageEvent event) {

        switch (event.getCode()) {
            case SEARCH_HIDE:
                floatingSearchView.setVisibility(View.GONE);
                break;
            case SEARCH_SHOW:
                floatingSearchView.setVisibility(View.VISIBLE);
                break;
            case SHOW:
                floatingSearchView.setVisibility(View.VISIBLE);
                bottomNavigationBar.setVisibility(View.VISIBLE);
                break;
            case HIDE:
                floatingSearchView.setVisibility(View.GONE);
                bottomNavigationBar.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onBackToFirstFragment() {
        bottomNavigationBar.selectTab(0);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_post:
                showToast("click nav_post");
                break;
            case R.id.nav_collect:
                showToast("click nav_collect");
                break;
            case R.id.nav_about:
                showToast("click nav_about");
                break;
            case R.id.nav_setting:
                showToast("click nav_setting");
                break;
        }
        return false;
    }

    /**
     * searchview右侧menu
     *
     * @param item
     */
    @Override
    public void onActionMenuItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.me:
                showToast("click me");
                break;
            case R.id.voice:
                showToast("click setting");
                break;
            case R.id.share:
                showToast("click share");
                break;
        }
    }
}
