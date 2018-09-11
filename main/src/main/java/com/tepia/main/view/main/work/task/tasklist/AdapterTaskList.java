package com.tepia.main.view.main.work.task.tasklist;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.base.utils.ResUtils;
import com.tepia.main.R;
import com.tepia.main.model.task.bean.TaskBean;


import java.util.List;

/**
 * Created by Joeshould on 2018/5/23.
 */

public class AdapterTaskList extends BaseQuickAdapter<TaskBean, BaseViewHolder> {

    public AdapterTaskList(Context context, int layoutResId, @Nullable List<TaskBean> data) {
        super(layoutResId, data);
        this.mContext = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void convert(BaseViewHolder view, TaskBean item) {
        TextView tvTitle = view.getView(R.id.tv_title);
        TextView tvDescribe = view.getView(R.id.tv_describe);
        TextView tvTime = view.getView(R.id.tv_time);
        TextView tvType = view.getView(R.id.tv_type);
        TextView serialTv = view.getView(R.id.serialTv);
        serialTv.setText(view.getAdapterPosition() + 1 + "");
        serialTv.setVisibility(View.GONE);
        tvTitle.setText(item.getWorkOrderName());
        tvDescribe.setText(item.getRemarks());
        tvTime.setText(item.getPlanStartTime());
        if (item.getBizPlanInfo() == null) {
            return;
        }
        if (item.getBizPlanInfo() != null && item.getBizPlanInfo().getOperationType() != null) {
            switch (item.getBizPlanInfo().getOperationType()) {
                case "1":
                    tvType.setText(ResUtils.getString(R.string.xunjianstr));
                    tvType.setBackground(ResUtils.getResources().getDrawable(R.drawable.bg_lv_item_bt_xj));
                    break;
                case "2":
                    tvType.setText(ResUtils.getString(R.string.weihustr));
                    tvType.setBackground(ResUtils.getResources().getDrawable(R.drawable.bg_lv_item_bt_wxyh));
                    break;
                case "3":
                    tvType.setText(ResUtils.getString(R.string.baojiestr));
                    tvType.setBackground(ResUtils.getResources().getDrawable(R.drawable.bg_lv_item_bt_bj));
                    break;
                default:
                    break;
            }
        }
    }


    public int getItemPositoin(String workOrderId) {
        for (int i = 0; i < getData().size(); i++) {
            if (getData().get(i).getWorkOrderId().equals(workOrderId)){
                return i;
            }
        }
        return Integer.MAX_VALUE;
    }
}
