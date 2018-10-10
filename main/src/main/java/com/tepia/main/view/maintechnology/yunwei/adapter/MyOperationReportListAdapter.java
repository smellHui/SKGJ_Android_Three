package com.tepia.main.view.maintechnology.yunwei.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.base.utils.ResUtils;
import com.tepia.main.R;
import com.tepia.main.model.jishu.yunwei.OperationReportListResponse;
import com.tepia.main.model.jishu.yunwei.WorkOrderListResponse;

import java.util.List;

/**
  * Created by      Android studio
  *
  * @author :wwj (from Center Of Wuhan)
  * Date    :2018/10/9
  * Version :1.0
  * 功能描述 :运维上报列表
 **/

public class MyOperationReportListAdapter extends BaseQuickAdapter<OperationReportListResponse.DataBean.ListBean, BaseViewHolder> {
    public MyOperationReportListAdapter(int layoutResId, @Nullable List<OperationReportListResponse.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OperationReportListResponse.DataBean.ListBean item) {
//        TextView name = helper.getView(R.id.tv_name);
//        TextView date = helper.getView(R.id.tv_date);
//        TextView isExecute = helper.getView(R.id.tv_isExecute);
//        ImageView ivComplete = helper.getView(R.id.iv_complete);
//        name.setText("巡检人:"+item.getExecutorName());
//        date.setText(item.getStartTime());
//        if ("2".equals(item.getExecuteStatus())){
//            //执行中 未完成
//            isExecute.setText("未完成");
//            isExecute.setTextColor(Color.parseColor("#e3654d"));
//            ivComplete.setImageDrawable(ResUtils.getResources().getDrawable(R.drawable.operation_not_complete));
//        }else {
//            isExecute.setText("已完成");
//            isExecute.setTextColor(Color.parseColor("#46c189"));
//            ivComplete.setImageDrawable(ResUtils.getResources().getDrawable(R.drawable.operation_complete));
//        }
        TextView tvProblemtitle = helper.getView(R.id.tv_problemTitle);
        TextView tvName = helper.getView(R.id.tv_name);
        TextView tvIsexecute = helper.getView(R.id.tv_isExecute);
        tvProblemtitle.setText(item.getProblemTitle());
        tvName.setText(item.getReservoirName()+"  "+item.getCreateDate());
        String problemStatus = item.getProblemStatus();
        if ("4".equals(problemStatus)){
            tvIsexecute.setText("未处理");
            tvIsexecute.setTextColor(Color.parseColor("#e3654d"));
        }else {
            tvIsexecute.setText("已处理");
            tvIsexecute.setTextColor(Color.parseColor("#46c189"));
        }

    }
}
