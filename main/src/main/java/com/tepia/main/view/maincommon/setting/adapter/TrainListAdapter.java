package com.tepia.main.view.maincommon.setting.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.main.model.train.TrainListResponse;

import java.util.List;

/**
 * Created by      Intellij IDEA
 *
 * @author :       wwj
 * Date    :       2018-09-27
 * Time    :       16:23
 * Version :       1.0
 * Company :       北京太比雅科技(武汉研发中心)
 **/
public class TrainListAdapter extends BaseQuickAdapter<TrainListResponse.DataBean.ListBean,BaseViewHolder> {
    public TrainListAdapter(int layoutResId, @Nullable List<TrainListResponse.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TrainListResponse.DataBean.ListBean item) {

    }
}
