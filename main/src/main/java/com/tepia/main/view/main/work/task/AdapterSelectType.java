package com.tepia.main.view.main.work.task;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.main.R;

import java.util.List;

/**
 * Created by Joeshould on 2018/6/25.
 */

public class AdapterSelectType  extends BaseQuickAdapter<String, BaseViewHolder> {
    public AdapterSelectType(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder view, String item) {
        TextView tvTitle = view.getView(R.id.tv_title);
        tvTitle.setText(item);
    }
}
