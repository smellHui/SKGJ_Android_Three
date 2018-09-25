package com.tepia.main.view.maincommon.reservoirs.detailadapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.main.R;
import com.tepia.main.model.reserviros.SupportingBean;
import com.tepia.main.view.maincommon.reservoirs.MyReservoirsItemBean;

import java.util.List;

/**
 * 主页--水库--设施适配器
 *
 * @author ly
 * @date 2018/9/18
 */

public class AdapterSupportingReservoirs extends BaseQuickAdapter<SupportingBean.DataBean, BaseViewHolder> {

    public AdapterSupportingReservoirs(Context context, int layoutResId, @Nullable List<SupportingBean.DataBean> data) {
        super(layoutResId, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder view, SupportingBean.DataBean item) {

        view.setText(R.id.titleResnameTv,item.getDeName());
        view.setText(R.id.middletitleResnameTv, item.getDeFunction());

    }
}
