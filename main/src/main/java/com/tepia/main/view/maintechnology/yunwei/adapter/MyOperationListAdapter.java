package com.tepia.main.view.maintechnology.yunwei.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.base.utils.ResUtils;
import com.tepia.main.R;
import com.tepia.main.model.jishu.yunwei.WorkOrderListResponse;

import java.util.List;
import java.util.Objects;

/**
  * Created by      Android studio
  *
  * @author :wwj (from Center Of Wuhan)
  * Date    :2018/10/9
  * Version :1.0
  * 功能描述 :  运维列表
 **/

public class MyOperationListAdapter extends BaseQuickAdapter<WorkOrderListResponse.DataBean.ListBean, BaseViewHolder> {
    public MyOperationListAdapter(int layoutResId, @Nullable List<WorkOrderListResponse.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WorkOrderListResponse.DataBean.ListBean item) {
        TextView name = helper.getView(R.id.tv_name);
        TextView date = helper.getView(R.id.tv_date);
        TextView isExecute = helper.getView(R.id.tv_isExecute);
        ImageView ivComplete = helper.getView(R.id.iv_complete);
        String operationType = item.getOperationType();
        String typeName = "执行";
        if ("1".equals(operationType)) {
            typeName = "巡检";
        } else if ("2".equals(operationType)) {
            typeName = "维护";
        } else if ("3".equals(operationType)) {
            typeName = "保洁";
        }
        name.setText(typeName + "人:" + item.getExecutorName());
        date.setText(item.getStartTime());
        if ("2".equals(item.getExecuteStatus())) {
            //执行中 未完成
            isExecute.setText("未完成");
            isExecute.setTextColor(Color.parseColor("#e3654d"));
            ivComplete.setImageDrawable(ResUtils.getResources().getDrawable(R.drawable.operation_not_complete));
        } else {
            isExecute.setText("已完成");
            isExecute.setTextColor(Color.parseColor("#46c189"));
            ivComplete.setImageDrawable(ResUtils.getResources().getDrawable(R.drawable.operation_complete));

        }
    }
}
