package com.tepia.main.view.maintechnology.threekeypoint.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.main.model.jishu.threepoint.WaterLevelResponse;
import com.tepia.main.model.jishu.yunwei.WorkOrderListResponse;

import java.util.List;

/**
 * Created by      Intellij IDEA
 *
 * @author :       wwj
 * Date    :       2018-09-26
 * Time    :       15:57
 * Version :       1.0
 * Company :       北京太比雅科技(武汉研发中心)
 **/
public class MyWaterListAdapter extends BaseQuickAdapter<WaterLevelResponse.DataBean.ListBean, BaseViewHolder> {
    public MyWaterListAdapter(int layoutResId, @Nullable List<WaterLevelResponse.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WaterLevelResponse.DataBean.ListBean item) {

    }
}
