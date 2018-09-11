package com.tepia.main.view.main.map.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.tepia.main.R;


public class DescHolder extends RecyclerView.ViewHolder {
    public TextView descView;

    public DescHolder(View itemView) {
        super(itemView);
        initView();
    }

    private void initView() {
        descView = itemView.findViewById(R.id.tv_desc);
    }
}
