package com.tepia.main.view.mainworker.yunwei.yunweilist;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.base.AppRoutePath;
import com.tepia.base.utils.ToastUtils;
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

        if (item.getExecuteStatus() != null) {
            switch (item.getExecuteStatus()) {
                case "1":
                    mBinding.tvStatus.setText("待执行");
                    mBinding.tvQusetion.setText("--");
                    mBinding.tvQusetion.setTextColor(Color.parseColor("#46c189"));
                    mBinding.ivStatus.setImageResource(R.drawable.operation_not_complete);
                    break;
                case "2":
                    mBinding.tvStatus.setText("执行中");
                    mBinding.tvQusetion.setText("--");
                    mBinding.tvQusetion.setTextColor(Color.parseColor("#46c189"));
                    mBinding.ivStatus.setImageResource(R.drawable.operation_not_complete);
                    break;
                case "3":

                case "4":

                case "5":
                    if (item.getProblemNum().equals("0") || item.getProblemNum().equals(item.getProblemDoneNum())) {
                        mBinding.tvQusetion.setText("正常");
                        mBinding.tvQusetion.setTextColor(Color.parseColor("#46c189"));
                    } else {
                        if (!TextUtils.isEmpty(item.getProblemNonNum())) {
                            mBinding.tvQusetion.setText("" + item.getProblemNonNum() + "/" + item.getProblemNum());
                            mBinding.tvQusetion.setTextColor(Color.parseColor("#e3654d"));
                        } else {
                            mBinding.tvQusetion.setText("" + item.getProblemNum() + "/" + item.getProblemNum());
                            mBinding.tvQusetion.setTextColor(Color.parseColor("#46c189"));
                        }

                    }
                    mBinding.ivStatus.setImageResource(R.drawable.operation_complete);
                    break;
                default:
                    mBinding.tvStatus.setText("已审核");
                    mBinding.tvQusetion.setText(item.getProblemNum() + "");
                    mBinding.ivStatus.setImageResource(R.drawable.operation_complete);
                    break;
            }
        }


        mBinding.tvQusetion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (item.getProblemNum().equals("0")) {
                    ToastUtils.shortToast("该工单暂时没有问题");
                } else {
                    ARouter.getInstance().build(AppRoutePath.app_work_order_question_list)
                            .withString("workOrderId",item.getWorkOrderId())
                            .navigation();
                }
            }
        });


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
