package com.tepia.main.view.maincommon.reservoirs.detailadapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.main.R;
import com.tepia.main.model.reserviros.FloodBean;
import com.tepia.main.view.maincommon.reservoirs.MyReservoirsItemBean;

import java.util.List;

/**
 * 主页--水库--防汛物资适配器
 *
 * @author ly
 * @date 2018/9/18
 */

public class AdapterFloodReservoirs extends BaseQuickAdapter<FloodBean.DataBean, BaseViewHolder> {

    public AdapterFloodReservoirs(Context context, int layoutResId, @Nullable List<FloodBean.DataBean> data) {
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
