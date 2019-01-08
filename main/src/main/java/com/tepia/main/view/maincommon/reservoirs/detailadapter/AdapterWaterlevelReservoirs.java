
package com.tepia.main.view.maincommon.reservoirs.detailadapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.main.R;
import com.tepia.main.model.reserviros.FloodBean;
import com.tepia.main.model.reserviros.FloodSeasonBean;
import com.tepia.main.view.maincommon.reservoirs.detail.WaterLevelActivity;

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
public class AdapterWaterlevelReservoirs extends BaseQuickAdapter<FloodSeasonBean.DataBean.ListBean, BaseViewHolder> {

    private WaterLevelActivity waterLevelActivity;
    public AdapterWaterlevelReservoirs(Context context, WaterLevelActivity waterLevelActivity,int layoutResId, @Nullable List<FloodSeasonBean.DataBean.ListBean> data) {
        super(layoutResId, data);
        this.mContext = context;
        this.waterLevelActivity = waterLevelActivity;
    }



    @Override
    protected void convert(BaseViewHolder view, FloodSeasonBean.DataBean.ListBean item) {

        view.setText(R.id.yearTv,item.getFloodYearMonth() + "");
        view.setText(R.id.waterLevelTv,item.getFloodLevel() + "");
        view.getView(R.id.editTv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 waterLevelActivity.initDialog(false,item);
            }
        });

        int position = view.getAdapterPosition();
        if( position%2 == 0 ) {
            view.setBackgroundColor(R.id.rootLy, ContextCompat.getColor(mContext, R.color.white));
        }else {
            view.setBackgroundColor(R.id.rootLy, ContextCompat.getColor(mContext, R.color.reportitem));
        }



    }
}
