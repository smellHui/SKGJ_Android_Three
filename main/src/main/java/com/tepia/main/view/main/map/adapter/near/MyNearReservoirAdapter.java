package com.tepia.main.view.main.map.adapter.near;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.main.R;
import com.tepia.main.view.main.map.adapter.search.SearchModel;

import java.util.List;

/**
 * Created by      Intellij IDEA
 *
 * @author :       wwj
 * Date    :       2018-11-22
 * Time    :       15:33
 * Version :       1.0
 * Company :       北京太比雅科技(武汉研发中心)
 **/
public class MyNearReservoirAdapter extends BaseQuickAdapter<NearReservoirResponse.DataBean, BaseViewHolder> {
    public MyNearReservoirAdapter(int layoutResId, @Nullable List<NearReservoirResponse.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NearReservoirResponse.DataBean item) {
        helper.setText(R.id.tv_title, item.getReservoir());
        double nearby = item.getNearby();
        String distance = "";
        if (nearby>=1000){
            distance = nearby/1000+"km";
        }else {
            distance = nearby+"m";
        }
        helper.setText(R.id.tv_type,"距离:"+distance);
    }
}
