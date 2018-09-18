package com.tepia.main.view.maincommon.reservoirs.detailadapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.main.R;
import com.tepia.main.view.maincommon.reservoirs.MyReservoirsItemBean;

import java.util.List;

/**
 * 主页--水库--防汛物资适配器
 *
 * @author ly
 * @date 2018/9/18
 */

public class AdapterFloodReservoirs extends BaseQuickAdapter<MyReservoirsItemBean, BaseViewHolder> {

    public AdapterFloodReservoirs(Context context, int layoutResId, @Nullable List<MyReservoirsItemBean> data) {
        super(layoutResId, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder view, MyReservoirsItemBean item) {

        view.setText(R.id.titleResnameTv,item.getTitle());
        view.setText(R.id.middletitleResnameTv, item.getMiddle_title());
        view.setText(R.id.numTv, "200吨");
        int postition = view.getAdapterPosition();
        if (postition < 10) {
            view.setText(R.id.serrialTv, "0"+postition);

        }else{
            view.setText(R.id.serrialTv, ""+postition);

        }

    }
}
