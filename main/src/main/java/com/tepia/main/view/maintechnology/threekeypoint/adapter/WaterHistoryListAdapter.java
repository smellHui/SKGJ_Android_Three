package com.tepia.main.view.maintechnology.threekeypoint.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.main.R;
import com.tepia.main.model.jishu.threepoint.WaterHistoryResponse;
import com.tepia.main.model.jishu.threepoint.WaterLevelResponse;

import java.util.List;

/**
 * Created by      Intellij IDEA
 *
 * @author :       wwj
 * Date    :       2018-09-26
 * Time    :       15:57
 * Version :       1.0
 * Company :       北京太比雅科技(武汉研发中心)
 **/
public class WaterHistoryListAdapter extends BaseQuickAdapter<WaterHistoryResponse.DataBean, BaseViewHolder> {
    public WaterHistoryListAdapter(int layoutResId, @Nullable List<WaterHistoryResponse.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WaterHistoryResponse.DataBean item) {
//        TextView tvReservoirName = helper.getView(R.id.tv_reservoir_name);
        TextView tvDate = helper.getView(R.id.tv_date);
        TextView tvWaterLevel = helper.getView(R.id.tv_water_level);
        TextView tvW = helper.getView(R.id.tv_w);
//        tvReservoirName.setText(item.getStnm());
        tvDate.setText(item.getTm());
        tvWaterLevel.setText(item.getRz()+"");
        if (TextUtils.isEmpty(item.getW()+"")||item.getW()==0) {
            tvW.setText(mContext.getString(R.string.setting_t_null));
        }else{
            tvW.setText(item.getW()+"");
        }
        if (helper.getLayoutPosition() % 2 == 0) {
            helper.setBackgroundColor(R.id.ll_root, ContextCompat.getColor(mContext, R.color.white));
        } else {
            helper.setBackgroundColor(R.id.ll_root, Color.parseColor("#F7F9FC"));
        }
    }
}
