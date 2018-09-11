package com.tepia.main.view.main.work.plan.planlist;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.base.utils.TimeFormatUtils;
import com.tepia.main.databinding.ItemPlanListViewBinding;
import com.tepia.main.model.plan.PlanBean;

import java.util.List;

public class AdapterPlanList  extends BaseQuickAdapter<PlanBean, BaseViewHolder> {
    public AdapterPlanList(int layoutResId, @Nullable List<PlanBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PlanBean item) {
        ItemPlanListViewBinding mBinding = DataBindingUtil.bind(helper.itemView);
        mBinding.tvTime.setText(TimeFormatUtils.getStringDate());
    }
}
