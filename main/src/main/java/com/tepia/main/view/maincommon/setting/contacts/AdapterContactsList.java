package com.tepia.main.view.maincommon.setting.contacts;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.voice.xunfei.DemoBean;

import java.util.List;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2018-09-19
 * Time            :       17:07
 * Version         :       1.0
 * 功能描述        :        通信录列表适配器
 **/
public  class AdapterContactsList extends BaseQuickAdapter<DemoBean,BaseViewHolder>{
    public AdapterContactsList(int layoutResId, @Nullable List<DemoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DemoBean item) {

    }
}
