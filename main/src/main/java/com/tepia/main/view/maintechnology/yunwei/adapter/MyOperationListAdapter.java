package com.tepia.main.view.maintechnology.yunwei.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by      Intellij IDEA
 * 运维列表
 *
 * @author :       wwj
 * Date    :       2018-09-18
 * Time    :       20:19
 * Version :       1.0
 * Company :       北京太比雅科技(武汉研发中心)
 **/
public class MyOperationListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public MyOperationListAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
