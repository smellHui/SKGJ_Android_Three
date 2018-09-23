package com.tepia.main.view.mainworker.report.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.main.R;
import com.tepia.main.view.maincommon.reservoirs.MyReservoirsItemBean;

import java.util.List;

/**
 * 主页--上报--应急适配器
 *
 * @author ly
 * @date 2018/9/17
 */

public class AdapterEmergency extends BaseQuickAdapter<MyReservoirsItemBean, BaseViewHolder> {

    public AdapterEmergency(Context context, int layoutResId, @Nullable List<MyReservoirsItemBean> data) {
        super(layoutResId, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder view, MyReservoirsItemBean item) {

        view.setText(R.id.yearTv,"2018-08-09");
        view.setText(R.id.titleTv, "坝体左侧十米处有白蚁坝体左侧十米处有白蚁");
        view.setText(R.id.stateTv, "已处理");
        int position = view.getAdapterPosition();
        if( position%2 == 0 ) {
            view.setBackgroundColor(R.id.rootLy, ContextCompat.getColor(mContext, R.color.white));
        }else {
            view.setBackgroundColor(R.id.rootLy, ContextCompat.getColor(mContext, R.color.reportitem));
        }

    }
}
