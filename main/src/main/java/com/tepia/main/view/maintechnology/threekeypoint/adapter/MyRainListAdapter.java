package com.tepia.main.view.maintechnology.threekeypoint.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.main.R;
import com.tepia.main.model.jishu.threepoint.RainConditionResponse;

import java.util.List;

/**
 * Created by      Intellij IDEA
 *
 * @author :       wwj
 * Date    :       2018-09-26
 * Time    :       15:58
 * Version :       1.0
 * Company :       北京太比雅科技(武汉研发中心)
 **/
public class MyRainListAdapter extends BaseQuickAdapter<RainConditionResponse.DataBean.ListBean, BaseViewHolder> {
    public MyRainListAdapter(int layoutResId, @Nullable List<RainConditionResponse.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RainConditionResponse.DataBean.ListBean item) {
        TextView tvReservoirName = helper.getView(R.id.tv_reservoir_name);
        TextView tvDate = helper.getView(R.id.tv_date);
        TextView tvDrp = helper.getView(R.id.tv_drp);
        tvReservoirName.setText(item.getStnm());
        tvDate.setText(item.getTm());
        tvDrp.setText(item.getDrp()+"");
        if (helper.getLayoutPosition() % 2 == 0) {
            helper.setBackgroundColor(R.id.ll_root, ContextCompat.getColor(mContext, R.color.white));
        } else {
            helper.setBackgroundColor(R.id.ll_root, Color.parseColor("#F7F9FC"));
        }
    }
}
