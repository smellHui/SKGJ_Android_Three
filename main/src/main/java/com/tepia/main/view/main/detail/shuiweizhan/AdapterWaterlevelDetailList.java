package com.tepia.main.view.main.detail.shuiweizhan;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.main.R;
import com.tepia.main.model.detai.RainfullBean;
import com.tepia.main.model.detai.WaterlevelBean;

import java.util.List;

/**
 * 水位站点详细信息适配器
 *
 * @author ly
 * @date 2018/7/27
 */

public class AdapterWaterlevelDetailList extends BaseQuickAdapter<WaterlevelBean.DataBean.StRsvrRSBean, BaseViewHolder> {

    public AdapterWaterlevelDetailList(Context context, int layoutResId, @Nullable List<WaterlevelBean.DataBean.StRsvrRSBean> data) {
        super(layoutResId, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder view, WaterlevelBean.DataBean.StRsvrRSBean item) {

        if(view.getAdapterPosition()%2 == 0){
            view.setBackgroundColor(R.id.lybg,mContext.getResources().getColor(R.color.blue_touch_user));
        }else{
            view.setBackgroundColor(R.id.lybg,mContext.getResources().getColor(R.color.color_c8c8c8));

        }
        view.setText(R.id.titleTv,item.getTm());
        view.setText(R.id.rightTextV, item.getW()+" ");
        view.getView(R.id.middleTv).setVisibility(View.VISIBLE);
        view.setText(R.id.middleTv, item.getRz()+" ");

    }
}
