package com.tepia.main.view.main.question;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.main.R;
import com.tepia.main.model.question.ReservoirDateBean;

import java.util.List;

/**
 * 问题展示类
 * Created by ly on 2018/5/25.
 */
public class AdapterQuestion extends BaseQuickAdapter<ReservoirDateBean, BaseViewHolder> {
    private TextView titleTv;

    public AdapterQuestion(int layoutResId, List<ReservoirDateBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ReservoirDateBean typeResponse) {

        titleTv = helper.getView(R.id.flowlayout_tv);
        titleTv.setText(typeResponse.getReservoir());

    }

}


