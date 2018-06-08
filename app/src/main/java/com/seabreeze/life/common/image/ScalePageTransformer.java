package com.seabreeze.life.common.image;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;

public class ScalePageTransformer implements ViewPager.PageTransformer {

    public static final float MAX_SCALE = 1.0f;
    public static final float MIN_SCALE = 0.8f;

    @Override
    public void transformPage(@NonNull View page, float position) {
        if (position < -1) {
            position = -1;
        } else if (position > 1) {
            position = 1;
        }

        float tempScale = position < 0 ? 1 + position : 1 - position;

        float slope = (MAX_SCALE - MIN_SCALE) / 1;

        float scaleValue = MIN_SCALE + tempScale * slope;

        //设置缩放比例
        page.setScaleX(scaleValue);
        page.setScaleY(scaleValue);
        //设置透明度
        page.setAlpha(scaleValue);
    }
}
