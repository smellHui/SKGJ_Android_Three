package com.tepia.main.view.maincommon.reservoirs;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.main.R;
import com.tepia.main.model.detai.WaterlevelBean;

import java.util.List;

/**
 * 适配器
 *
 * @author ly
 * @date 2018/7/27
 */

public class AdapterMainReservoirs extends BaseQuickAdapter<MyReservoirsItemBean, BaseViewHolder> {

    public AdapterMainReservoirs(Context context, int layoutResId, @Nullable List<MyReservoirsItemBean> data) {
        super(layoutResId, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder view, MyReservoirsItemBean item) {

        view.setText(R.id.titleResnameTv,item.getTitle());
        view.setText(R.id.middletitleResnameTv, item.getMiddle_title());
        view.setBackgroundRes(R.id.imageViewLeft,item.getResourceImg());

    }
}
