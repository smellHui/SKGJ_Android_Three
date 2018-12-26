package com.tepia.main.view.maincommon.reservoirs.detailadapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.main.R;
import com.tepia.main.model.reserviros.FloodBean;

import java.util.List;

/**
  * Created by      Android studio
  *
  * @author :ly (from Center Of Wuhan)
  * 创建时间 :2018-12-26
  * 更新时间 :
  * Version :1.3.0
  * 功能描述 :主页--水库--讯限水位适配器
 **/
public class AdapterWaterlevelReservoirs extends BaseQuickAdapter<FloodBean.DataBean, BaseViewHolder> {

    public AdapterWaterlevelReservoirs(Context context, int layoutResId, @Nullable List<FloodBean.DataBean> data) {
        super(layoutResId, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder view, FloodBean.DataBean item) {

        view.setText(R.id.titleResnameTv,item.getMeName());
        view.setText(R.id.middletitleResnameTv, item.getPosition());
        view.setText(R.id.numTv, item.getMeTotals());
        int postition = view.getAdapterPosition();
        ++postition;
        if (postition < 10) {

            view.setText(R.id.serrialTv, "0"+postition);

        }else{
            view.setText(R.id.serrialTv, ""+postition);

        }

    }
}
