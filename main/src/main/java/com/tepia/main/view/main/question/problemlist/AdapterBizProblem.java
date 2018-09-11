package com.tepia.main.view.main.question.problemlist;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.base.utils.ResUtils;
import com.tepia.main.R;
import com.tepia.main.model.question.AllproblemBean;
import com.tepia.main.model.question.ProblemDetailBean;

import java.util.List;

/**
 * 事件上报审核状态
 *
 * @author ly
 * @date 2018/7/31
 */

public class AdapterBizProblem extends BaseQuickAdapter<ProblemDetailBean.DataBean.BizProblemFlowsBean, BaseViewHolder> {

    public AdapterBizProblem(Context context, int layoutResId, @Nullable List<ProblemDetailBean.DataBean.BizProblemFlowsBean> data) {
        super(layoutResId, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder view, ProblemDetailBean.DataBean.BizProblemFlowsBean item) {
       /* if (item != null) {
            if (TextUtils.isEmpty(item.getExcuteEndTime()) && TextUtils.isEmpty(item.getExcuteUserNm())) {
                return;
            }
        }*/
        if (item != null) {
            TextView excuteendtimeTv = view.getView(R.id.excuteendtimeTv);
            TextView proconfigTv = view.getView(R.id.proconfigTv);
            TextView excutenameTv = view.getView(R.id.excutenameTv);
            TextView excutestatusTv = view.getView(R.id.excutestatusTv);
            TextView excutesdesTv = view.getView(R.id.excutesdesTv);

            if (TextUtils.isEmpty(item.getExcuteEndTime())) {
                excuteendtimeTv.setText("--");
            } else {
                excuteendtimeTv.setText(item.getExcuteEndTime());

            }
            if (TextUtils.isEmpty(item.getConfigOrderNm())) {
                proconfigTv.setText("--");

            } else {
                proconfigTv.setText(item.getConfigOrderNm());

            }

            if (TextUtils.isEmpty(item.getExcuteUserNm())) {
                excutenameTv.setText("--");

            } else {
                excutenameTv.setText(item.getExcuteUserNm());

            }

            if(item.getExcuteStatus() != null) {
                if (item.getExcuteStatus() == 0) {
                    excutestatusTv.setText("拒绝");
                } else if (item.getExcuteStatus() == 1) {
                    excutestatusTv.setText("同意");

                }
            }

            if (TextUtils.isEmpty(item.getExcuteDes())) {
                excutesdesTv.setText("--");

            } else {
                excutesdesTv.setText(item.getExcuteDes());

            }

        }


    }


}
