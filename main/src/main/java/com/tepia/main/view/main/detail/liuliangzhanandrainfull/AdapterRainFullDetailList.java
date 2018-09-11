package com.tepia.main.view.main.detail.liuliangzhanandrainfull;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.main.R;
import com.tepia.main.model.detai.RainfullBean;

import java.util.List;

/**
 * 雨量站点详细信息适配器
 *
 * @author ly
 * @date 2018/7/26
 */

public class AdapterRainFullDetailList extends BaseQuickAdapter<RainfullBean.DataBean.StPptnRsBean, BaseViewHolder> {

    public AdapterRainFullDetailList(Context context, int layoutResId, @Nullable List<RainfullBean.DataBean.StPptnRsBean> data) {
        super(layoutResId, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder view, RainfullBean.DataBean.StPptnRsBean item) {

        if(view.getAdapterPosition()%2 == 0){
            view.setBackgroundColor(R.id.lybg,mContext.getResources().getColor(R.color.blue_touch_user));
        }else{
            view.setBackgroundColor(R.id.lybg,mContext.getResources().getColor(R.color.color_c8c8c8));

        }
        view.setText(R.id.titleTv,item.getTm());
        view.setText(R.id.rightTextV, item.getDrp()+" ");

    }
}
