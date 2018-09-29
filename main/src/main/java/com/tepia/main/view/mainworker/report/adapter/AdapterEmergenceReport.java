package com.tepia.main.view.mainworker.report.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.main.R;
import com.tepia.main.model.detai.ReservoirBean;
import com.tepia.main.model.question.ReservoirDateBean;

import java.util.List;

/**
 * 问题展示类
 * Created by ly on 2018/5/25.
 */
public class AdapterEmergenceReport extends BaseQuickAdapter<ReservoirBean, BaseViewHolder> {
    private TextView titleTv;

    public AdapterEmergenceReport(int layoutResId, List<ReservoirBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ReservoirBean typeResponse) {

        titleTv = helper.getView(R.id.flowlayout_tv);
        titleTv.setText(typeResponse.getReservoir());

    }

}


