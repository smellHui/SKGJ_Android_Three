package com.tepia.main.view.maincommon.setting.worknotification.worknotificationdetail.leader;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.main.R;
import com.tepia.main.databinding.LvFeedBackItemBinding;
import com.tepia.main.model.worknotification.FeedBackWorkNoticeBean;
import com.tepia.main.model.worknotification.WorkNoticeFeedbackBean;

import java.util.List;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2018-09-30
 * Time            :       14:02
 * Version         :       1.0
 * 功能描述        :
 **/
public class AdapterFeedBackList extends BaseQuickAdapter<WorkNoticeFeedbackBean, BaseViewHolder> {
    public AdapterFeedBackList(int layoutResId, @Nullable List<WorkNoticeFeedbackBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WorkNoticeFeedbackBean item) {
        LvFeedBackItemBinding mBinding = DataBindingUtil.bind(helper.itemView);
        if (item.getFeedBackUserName() != null) {
            mBinding.tvPeople.setText(item.getFeedBackUserName());
        }
        if (item.getReservoirName() != null) {
            mBinding.tvReservoir.setText(item.getReservoirName());
        }
        if (item.getFeedbackDate() != null) {
            mBinding.tvTime.setText(item.getFeedbackDate());
        }
        if (item.getFeedbackContent() != null) {
            mBinding.tvDesc.setText(item.getFeedbackContent());
        }
        if (item.getStatus().equals("0")) {
            mBinding.tvStatus.setText("未反馈");
        } else {
            mBinding.tvStatus.setText("已反馈");
        }

        if (helper.getLayoutPosition() % 2 == 0) {
            helper.setBackgroundColor(R.id.lo_container, ContextCompat.getColor(mContext, R.color.white));
        } else {
            helper.setBackgroundColor(R.id.lo_container, ContextCompat.getColor(mContext, R.color.reportitem));
        }
    }
}
