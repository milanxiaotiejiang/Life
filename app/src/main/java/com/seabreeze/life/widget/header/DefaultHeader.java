package com.seabreeze.life.widget.header;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.seabreeze.life.R;
import com.seabreeze.life.utils.SystemUtils;

public class DefaultHeader extends LinearLayout {

    private Context mContext;

    public DefaultHeader(Context context) {
        super(context);
        init(context);
    }

    public DefaultHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DefaultHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        mContext = context;
        setLayoutParams(new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        TextView textView = new TextView(mContext);
        textView.setHeight(SystemUtils.dp2px(60));

        addView(textView);
    }

}
