package com.seabreeze.life.widget.header;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.seabreeze.life.R;
import com.seabreeze.life.utils.SystemUtils;

public class TextHeader extends LinearLayout {

    private Context mContext;

    public TextHeader(Context context, String title) {
        super(context);
        init(context, title);
    }

    public TextHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, null);
    }

    public TextHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, null);
    }


    private void init(Context context, String title) {
        mContext = context;
        setLayoutParams(new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

//        textView = new TextView(mContext);
//        textView.setHeight(SystemUtils.dp2px(60));
//        textView.setText(title);

        TextView view = (TextView) LayoutInflater.from(mContext).inflate(R.layout.defaul_text, null);
        view.setText(title);

        addView(view);
    }

}
