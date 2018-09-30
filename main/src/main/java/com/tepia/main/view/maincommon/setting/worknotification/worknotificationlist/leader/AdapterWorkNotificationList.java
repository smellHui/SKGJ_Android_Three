package com.tepia.main.view.maincommon.setting.worknotification.worknotificationlist.leader;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.main.databinding.LvWorkNotificationItemBinding;
import com.tepia.main.model.worknotification.WorkNoticeBean;

import java.util.List;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2018-09-29
 * Time            :       17:01
 * Version         :       1.0
 * 功能描述        :
 **/
public class AdapterWorkNotificationList extends BaseQuickAdapter<WorkNoticeBean, BaseViewHolder> {
    public AdapterWorkNotificationList(int layoutResId, @Nullable List<WorkNoticeBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WorkNoticeBean item) {
        LvWorkNotificationItemBinding mBinding = DataBindingUtil.bind(helper.itemView);
        mBinding.tvTitle.setText(item.getNoticeTitle() + "");
        mBinding.tvPeople.setText("发布人：" + item.getUserName());
        mBinding.tvTime.setText(item.getCreateDate());
        mBinding.tvDesc.setText(item.getNoticeContent());
    }
}
