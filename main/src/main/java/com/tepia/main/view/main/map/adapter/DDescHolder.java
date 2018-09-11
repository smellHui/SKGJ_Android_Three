package com.tepia.main.view.main.map.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.tepia.main.R;


/**
 * @author 44822
 */
public class DDescHolder extends RecyclerView.ViewHolder {
    public TextView descView;
    public CheckBox checkBox;


    public DDescHolder(View itemView) {
        super(itemView);
        initView();
    }

    private void initView() {
        descView = itemView.findViewById(R.id.tv_desc);
        checkBox = itemView.findViewById(R.id.checkBox);
    }
}
