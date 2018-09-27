package com.tepia.main.view.maincommon.setting.contacts;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.main.databinding.LvContactListItemBinding;
import com.tepia.main.model.user.ContactBean;
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
public class AdapterContactsList extends BaseQuickAdapter<ContactBean, BaseViewHolder> {
    public AdapterContactsList(int layoutResId, @Nullable List<ContactBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ContactBean item) {
        LvContactListItemBinding mBingding = DataBindingUtil.bind(helper.itemView);
        mBingding.tvName.setText(item.getUserName()+"");
        mBingding.tvMobile.setText(item.getMobile() + "");
        mBingding.tvReservoir.setText(item.getReservoir()+"");
        mBingding.tvPositoin.setText(item.getJobName()+"");
    }
}
