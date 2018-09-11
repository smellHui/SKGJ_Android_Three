package com.tepia.main.view.main.work.task.taskdetail;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.main.R;
import com.tepia.main.model.task.bean.PositionNamesBean;

import java.util.List;

/**
 * Created by Joeshould on 2018/5/30.
 */

public class AdapterPositionTitle extends BaseQuickAdapter<PositionNamesBean,BaseViewHolder>{
    private TextView tvTitle;
    private ImageView ivNext;
    public AdapterPositionTitle(int layoutResId, @Nullable List<PositionNamesBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder view, PositionNamesBean item) {
        tvTitle = view.getView(R.id.tv_title);
        ivNext = view.getView(R.id.iv_next);
        tvTitle.setText(item.getTitle());
        if (view.getAdapterPosition() == getItemCount()-1){
            ivNext.setVisibility(View.GONE);
        }
    }
}
