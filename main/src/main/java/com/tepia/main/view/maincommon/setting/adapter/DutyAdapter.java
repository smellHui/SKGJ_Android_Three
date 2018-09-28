package com.tepia.main.view.maincommon.setting.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.main.ConfigConsts;
import com.tepia.main.R;
import com.tepia.main.model.detai.ReservoirBean;
import com.tepia.main.model.setting.DutyOfWorkBean;
import com.tepia.main.model.train.TrainListResponse;

import java.util.List;

/**
  * Created by      Android studio
  *
  * @author :ly (from Center Of Wuhan)
  * Date    :2018-9-28
  * Version :1.0
  * 功能描述 :工作职责页面适配器
 **/
public class DutyAdapter extends BaseQuickAdapter<ReservoirBean,BaseViewHolder> {
    public DutyAdapter(int layoutResId, @Nullable List<ReservoirBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder view, ReservoirBean item) {
        view.setText(R.id.reserviorNameTv,item.getReservoir());
       
    }
}
