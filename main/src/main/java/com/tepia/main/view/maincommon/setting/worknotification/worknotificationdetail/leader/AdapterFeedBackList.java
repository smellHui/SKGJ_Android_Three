package com.tepia.main.view.maincommon.setting.worknotification.worknotificationdetail.leader;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.base.utils.ResUtils;
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
            mBinding.tvStatus.setTextColor(Color.parseColor("#e3654d"));
            mBinding.ivComplete.setImageDrawable(ResUtils.getResources().getDrawable(R.drawable.operation_not_complete));
            mBinding.tvDesc.setVisibility(View.GONE);
            mBinding.tvPeople.setVisibility(View.GONE);
            mBinding.tvTime.setVisibility(View.GONE);
        } else {
            mBinding.tvStatus.setText("已反馈");
            mBinding.tvStatus.setTextColor(Color.parseColor("#46c189"));
            mBinding.ivComplete.setImageDrawable(ResUtils.getResources().getDrawable(R.drawable.operation_complete));
            mBinding.tvDesc.setVisibility(View.VISIBLE);
            mBinding.tvPeople.setVisibility(View.VISIBLE);
            mBinding.tvTime.setVisibility(View.VISIBLE);
        }

//        isExecute.setTextColor(Color.parseColor("#e3654d"));
//        ivComplete.setImageDrawable(ResUtils.getResources().getDrawable(R.drawable.operation_not_complete));
//    } else {
//        isExecute.setText("已完成");
//        isExecute.setTextColor(Color.parseColor("#46c189"));
//        ivComplete.setImageDrawable(ResUtils.getResources().getDrawable(R.drawable.operation_complete));
//        if (helper.getLayoutPosition() % 2 == 0) {
//            helper.setBackgroundColor(R.id.lo_container, ContextCompat.getColor(mContext, R.color.white));
//        } else {
//            helper.setBackgroundColor(R.id.lo_container, ContextCompat.getColor(mContext, R.color.reportitem));
//        }
    }
}
