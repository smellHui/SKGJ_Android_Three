package com.tepia.main.view.maincommon.setting.worknotification.worknotificationlist.worker;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.main.databinding.LvWorkNotificationItemBinding;
import com.tepia.main.databinding.LvWorkNotificationItemWorkerBinding;
import com.tepia.main.model.worknotification.FeedBackWorkNoticeBean;
import com.tepia.main.model.worknotification.WorkNoticeBean;

import java.util.List;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2018-09-30
 * Time            :       10:54
 * Version         :       1.0
 * 功能描述        :
 **/
public class AdapterWorkNotificationWorkerList extends BaseQuickAdapter<FeedBackWorkNoticeBean, BaseViewHolder> {

    public AdapterWorkNotificationWorkerList(int layoutResId, @Nullable List<FeedBackWorkNoticeBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FeedBackWorkNoticeBean item) {
        LvWorkNotificationItemWorkerBinding mBinding = DataBindingUtil.bind(helper.itemView);
        mBinding.tvTitle.setText(item.getBizWorkNotice().getNoticeTitle() + "");
        mBinding.tvPeople.setText("发布人：" + item.getBizWorkNotice().getUserName());
        mBinding.tvTime.setText(item.getBizWorkNotice().getCreateDate());
//        mBinding.tvDesc.setText(item.getBizWorkNotice().getNoticeContent());
        mBinding.tvReservoir.setText(item.getReservoirName());
        if (item.getStatus().equals("0")) {
            mBinding.tvStatus.setText("未反馈");
            mBinding.tvStatus.setTextColor(Color.parseColor("#e3654d"));
        } else {
            mBinding.tvStatus.setText("已反馈");
            mBinding.tvStatus.setTextColor(Color.parseColor("#46c189"));
        }
    }
}
