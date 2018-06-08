package com.seabreeze.life.video.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.seabreeze.life.R;
import com.seabreeze.life.video.model.SwitchVideoModel;

import java.util.List;

public class SwitchVideoTypeDialog extends Dialog {

    private Context mContext;

    private ListView mListView;

    private ArrayAdapter<SwitchVideoModel> adapter = null;

    private List<SwitchVideoModel> data;

    private OnListItemClickListener listener;

    public interface OnListItemClickListener {
        void onItemClick(int position);
    }


    public SwitchVideoTypeDialog(@NonNull Context context) {
        super(context, R.style.dialog_style);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void initList(List<SwitchVideoModel> data, OnListItemClickListener listener) {
        this.listener = listener;
        this.data = data;

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.switch_video_dialog, null);
        mListView = view.findViewById(R.id.switch_dialog_list);
        setContentView(view);
        adapter = new ArrayAdapter<>(mContext, R.layout.switch_video_dialog_item, data);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new OnItemClickListener());

        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
        params.width = (int) (metrics.widthPixels * 0.8);
        window.setAttributes(params);
    }

    private class OnItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            dismiss();
            listener.onItemClick(position);
        }
    }
}
