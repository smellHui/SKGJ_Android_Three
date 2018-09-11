package com.tepia.main.view.main.map.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tepia.main.R;



/**
 * @author 44822
 */
public class DHeaderHolder extends RecyclerView.ViewHolder {
    public TextView titleView;
    public ImageView iv_more;
    public DHeaderHolder(View itemView) {
        super(itemView);
        initView();
    }

    private void initView() {
        titleView = itemView.findViewById(R.id.tv_title);
        iv_more = itemView.findViewById(R.id.iv_more);
    }
}
