package com.tepia.main.view.maincommon.reservoirs.detailadapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.main.R;
import com.tepia.main.model.reserviros.VisitLogBean;
import com.tepia.main.view.maincommon.reservoirs.MyReservoirsItemBean;

import java.util.List;

/**
 * 主页--水库--到访日志适配器
 *
 * @author ly
 * @date 2018/9/25
 */

public class AdapterVisitLog extends BaseQuickAdapter<VisitLogBean.DataBean.ListBean, BaseViewHolder> {

    public AdapterVisitLog(Context context, int layoutResId, @Nullable List<VisitLogBean.DataBean.ListBean> data) {
        super(layoutResId, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder view, VisitLogBean.DataBean.ListBean item) {

        view.setText(R.id.nameOfreservoirTv,item.getReservoir());
        view.setText(R.id.visitTimeTv, item.getVisitTime());
        view.setText(R.id.nameTv, item.getUserName());

    }
}
