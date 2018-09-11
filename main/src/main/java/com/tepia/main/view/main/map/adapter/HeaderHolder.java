package com.tepia.main.view.main.map.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.tepia.main.R;


/**
 *
 */

public class HeaderHolder extends RecyclerView.ViewHolder {
    public TextView titleView;
    public TextView openView;
    public HeaderHolder(View itemView) {
        super(itemView);
        initView();
    }

    private void initView() {
        titleView = itemView.findViewById(R.id.tv_title);
        openView = itemView.findViewById(R.id.tv_open);
    }
}
