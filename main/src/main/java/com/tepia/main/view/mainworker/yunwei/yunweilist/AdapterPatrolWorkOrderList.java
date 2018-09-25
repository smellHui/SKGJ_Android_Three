package com.tepia.main.view.mainworker.yunwei.yunweilist;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.main.R;
import com.tepia.main.databinding.LvPatrolWorkorderListBinding;
import com.tepia.main.model.task.bean.TaskBean;

import java.util.List;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2018-09-25
 * Time            :       10:16
 * Version         :       1.0
 * 功能描述        :
 **/
public class AdapterPatrolWorkOrderList extends BaseQuickAdapter<TaskBean, BaseViewHolder> {
    public AdapterPatrolWorkOrderList(int layoutResId, @Nullable List<TaskBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TaskBean item) {
        LvPatrolWorkorderListBinding mBinding = DataBindingUtil.bind(helper.itemView);
        mBinding.tvItemCount.setText(helper.getLayoutPosition() + 1 + "");
        mBinding.tvDate.setText(item.getStartTime());
        mBinding.tvExecWorker.setText(item.getExecutorName());
        if (helper.getLayoutPosition() % 2 == 0) {
            helper.setBackgroundColor(R.id.rootLy, ContextCompat.getColor(mContext, R.color.white));
        } else {
            helper.setBackgroundColor(R.id.rootLy, ContextCompat.getColor(mContext, R.color.reportitem));
        }
    }
}
