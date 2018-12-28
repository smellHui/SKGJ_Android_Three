package com.tepia.main.view.mainadminister.yunwei.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.main.R;
import com.tepia.main.model.jishu.admin.AdminWorkOrderResponse;
import com.tepia.main.model.jishu.admin.ProblemListByAddvcdResponse;

import java.util.List;

/**
 * Created by      Android studio
 *
 * @author :wwj (from Center Of Wuhan)
 * Date    :2018/12/28
 * Version :1.0
 * 功能描述 :  根据行政编码统计水库问题总数和处理完成数
 **/

public class TownOperationReportAdapter extends BaseQuickAdapter<ProblemListByAddvcdResponse.DataBean, BaseViewHolder> {
    public TownOperationReportAdapter(int layoutResId, @Nullable List<ProblemListByAddvcdResponse.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProblemListByAddvcdResponse.DataBean item) {
        TextView tvDate = helper.getView(R.id.tv_date);
        TextView tvReservoirName = helper.getView(R.id.tv_reservoir_name);
        TextView tvComplete = helper.getView(R.id.tv_complete);
        TextView tvCount = helper.getView(R.id.tv_count);
        TextView tvStatus = helper.getView(R.id.tv_status);
        tvDate.setText(item.getDate());
        int doneNum = item.getDone_num();
        int totals = item.getTotals();
        int non_num = item.getNon_num();
//        LogUtil.i("doneNum:"+doneNum+"totals:"+totals+"non_num:"+non_num);
        tvReservoirName.setText(item.getReservoirName());
        int percent = 0;
        if (doneNum != 0 && totals != 0) {
            percent = doneNum * 100 / totals;
        }
        tvStatus.setText(percent + "%");
        tvComplete.setText(item.getDone_num() + "");

        tvCount.setText(item.getTotals() + "");

        if (helper.getLayoutPosition() % 2 == 0) {
            helper.setBackgroundColor(R.id.rootLy, ContextCompat.getColor(mContext, R.color.white));
        } else {
            helper.setBackgroundColor(R.id.rootLy, Color.parseColor("#F7F9FC"));
        }
    }
}
