package com.tepia.main.view.mainworker.report.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.main.R;
import com.tepia.main.model.dictmap.DictMapEntity;
import com.tepia.main.model.dictmap.DictMapManager;
import com.tepia.main.model.report.EmergenceListBean;
import com.tepia.main.view.maincommon.reservoirs.MyReservoirsItemBean;

import java.util.List;
import java.util.Map;

/**
 * 主页--上报--应急适配器
 *
 * @author ly
 * @date 2018/9/17
 */

public class AdapterEmergency extends BaseQuickAdapter<EmergenceListBean.DataBean.ListBean, BaseViewHolder> {

    public AdapterEmergency(Context context, int layoutResId, @Nullable List<EmergenceListBean.DataBean.ListBean> data) {
        super(layoutResId, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder view, EmergenceListBean.DataBean.ListBean item) {

        view.setText(R.id.yearTv,item.getCreateDate());
        view.setText(R.id.titleTv, item.getProblemTitle());

        DictMapEntity dictMapEntity = DictMapManager.getInstance().getmDictMap();
        Map<String, String> mapProblemStatus = dictMapEntity.getObject().getProblemStatus();
        view.setText(R.id.stateTv, mapProblemStatus.get(item.getProblemStatus()));
        if("4".equals(item.getProblemStatus())){
            //待反馈
            view.setTextColor(R.id.stateTv,Color.parseColor("#e3654d"));
        }else if("5".equals(item.getProblemStatus())){
            //待反馈
            view.setTextColor(R.id.stateTv, Color.parseColor("#46c189"));
        }
        int position = view.getAdapterPosition();
        if( position%2 == 0 ) {
            view.setBackgroundColor(R.id.rootLy, ContextCompat.getColor(mContext, R.color.white));
        }else {
            view.setBackgroundColor(R.id.rootLy, ContextCompat.getColor(mContext, R.color.reportitem));
        }

    }
}
