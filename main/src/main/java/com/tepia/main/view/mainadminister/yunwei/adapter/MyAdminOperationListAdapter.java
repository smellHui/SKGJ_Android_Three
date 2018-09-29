package com.tepia.main.view.mainadminister.yunwei.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.main.R;
import com.tepia.main.model.jishu.admin.AdminWorkOrderResponse;

import java.util.List;

/**
 * Created by      Intellij IDEA
 * 运维列表
 *
 * @author :       wwj
 * Date    :       2018-09-18
 * Time    :       20:19
 * Version :       1.0
 * Company :       北京太比雅科技(武汉研发中心)
 **/
public class MyAdminOperationListAdapter extends BaseQuickAdapter<AdminWorkOrderResponse.DataBean.ListBean, BaseViewHolder> {

    public MyAdminOperationListAdapter(int layoutResId, @Nullable List<AdminWorkOrderResponse.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AdminWorkOrderResponse.DataBean.ListBean item) {
        TextView tvDate = helper.getView(R.id.tv_date);
        TextView tvReservoirName = helper.getView(R.id.tv_reservoir_name);
        TextView tvComplete = helper.getView(R.id.tv_complete);
        TextView tvCount = helper.getView(R.id.tv_count);
        TextView tvStatus = helper.getView(R.id.tv_status);
        tvDate.setText(item.getDate());
        tvReservoirName.setText(item.getReservoirName());
        tvComplete.setText(item.getDone_num()+"");
        tvCount.setText(item.getTotals()+"");
        int percent=0;
        int doneNum = item.getDone_num();
        int totals = item.getTotals();
        if (doneNum!=0&&totals!=0){
            percent = doneNum*100/totals;
        }
        tvStatus.setText(percent+"%");
        if (helper.getLayoutPosition() % 2 == 0) {
            helper.setBackgroundColor(R.id.rootLy, ContextCompat.getColor(mContext, R.color.white));
        } else {
            helper.setBackgroundColor(R.id.rootLy, Color.parseColor("#F7F9FC"));
        }
    }
}
