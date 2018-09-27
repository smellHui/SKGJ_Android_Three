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
        if (item.getStartTime() != null && item.getStartTime().length() >= 10) {
            mBinding.tvDate.setText(item.getStartTime().substring(0, 10));
        }
        mBinding.tvReservoir.setText(item.getReservoirName());
        mBinding.tvExecWorker.setText(item.getExecutorName());
        mBinding.tvQusetion.setText(item.getProblemNum() + "");
        if (item.getExecuteStatus() != null) {
            switch (item.getExecuteStatus()) {
                case "1":
                    mBinding.tvStatus.setText("待执行");
                    mBinding.ivStatus.setImageResource(R.drawable.operation_not_complete);
                    break;
                case "2":
                    mBinding.tvStatus.setText("执行中");
                    mBinding.ivStatus.setImageResource(R.drawable.operation_not_complete);
                    break;
                case "3":
                    mBinding.tvStatus.setText("已完成");
                    mBinding.ivStatus.setImageResource(R.drawable.operation_complete);
                    break;
                case "4":
                    mBinding.tvStatus.setText("已审核");
                    mBinding.ivStatus.setImageResource(R.drawable.operation_complete);
                    break;
                default:
                    mBinding.tvStatus.setText("已审核");
                    mBinding.ivStatus.setImageResource(R.drawable.operation_complete);
                    break;
            }
        }
        if (helper.getLayoutPosition() % 2 == 0) {
            helper.setBackgroundColor(R.id.rootLy, ContextCompat.getColor(mContext, R.color.white));
        } else {
            helper.setBackgroundColor(R.id.rootLy, ContextCompat.getColor(mContext, R.color.reportitem));
        }
        if (item.getOperationType() != null) {
            switch (item.getOperationType()) {
                case "1":
                    mBinding.tvYunweiType.setText("巡检");
                    break;
                case "2":
                    mBinding.tvYunweiType.setText("维护");
                    break;
                case "3":
                    mBinding.tvYunweiType.setText("保洁");
                    break;
                default:
                    mBinding.tvYunweiType.setText("巡检");
                    break;
            }
        }
    }
}
