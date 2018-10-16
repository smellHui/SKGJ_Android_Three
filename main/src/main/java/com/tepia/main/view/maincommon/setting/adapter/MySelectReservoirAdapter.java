package com.tepia.main.view.maincommon.setting.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.main.R;
import com.tepia.main.model.detai.ReservoirBean;

import java.util.List;

/**
  * Created by      Android studio
  *
  * @author :wwj (from Center Of Wuhan)
  * Date    :2018/10/16
  * Version :1.0
  * 功能描述 :选择水库适配器
 **/

public class MySelectReservoirAdapter extends BaseQuickAdapter<ReservoirBean,BaseViewHolder> {
    public MySelectReservoirAdapter(int layoutResId, @Nullable List<ReservoirBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ReservoirBean item) {
        helper.setText(R.id.tv_reservoir_name,item.getReservoir());
    }
}
