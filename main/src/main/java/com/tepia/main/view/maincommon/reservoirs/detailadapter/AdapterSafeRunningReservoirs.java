package com.tepia.main.view.maincommon.reservoirs.detailadapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.main.R;
import com.tepia.main.view.maincommon.reservoirs.MyReservoirsItemBean;

import java.util.List;

/**
 * 主页--水库--安全鉴定记录适配器
 *
 * @author ly
 * @date 2018/9/18
 */

public class AdapterSafeRunningReservoirs extends BaseQuickAdapter<MyReservoirsItemBean, BaseViewHolder> {

    public AdapterSafeRunningReservoirs(Context context, int layoutResId, @Nullable List<MyReservoirsItemBean> data) {
        super(layoutResId, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder view, MyReservoirsItemBean item) {

        view.setText(R.id.titleResnameTv,item.getTitle());
        view.setText(R.id.middletitleResnameTv, item.getMiddle_title());

    }
}
