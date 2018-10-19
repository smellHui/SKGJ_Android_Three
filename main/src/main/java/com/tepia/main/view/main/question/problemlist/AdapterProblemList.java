package com.tepia.main.view.main.question.problemlist;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.base.utils.ResUtils;
import com.tepia.main.R;
import com.tepia.main.model.dictmap.DictMapEntity;
import com.tepia.main.model.dictmap.DictMapManager;
import com.tepia.main.model.question.AllproblemBean;
import com.tepia.main.model.task.bean.TaskBean;

import java.util.List;
import java.util.Map;

/**
 * 事件上报列表
 * @author ly
 * @date 2018/7/31
 */

public class AdapterProblemList extends BaseQuickAdapter<AllproblemBean.DataBean.ListBean, BaseViewHolder> {

    public AdapterProblemList(Context context, int layoutResId, @Nullable List<AllproblemBean.DataBean.ListBean> data) {
        super(layoutResId, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder view, AllproblemBean.DataBean.ListBean item) {
        TextView tvTitle = view.getView(R.id.tv_title);
        TextView tvDescribe = view.getView(R.id.tv_describe);
        TextView tvTime = view.getView(R.id.tv_time);
        TextView tvType = view.getView(R.id.tv_type);
        TextView serialTv = view.getView(R.id.serialTv);
        serialTv.setText(view.getAdapterPosition() + 1 + "");
        tvTitle.setText(item.getProblemTitle());
        tvDescribe.setText(item.getReservoirName());
        tvTime.setText(item.getCreateDate());
        switch (item.getProblemType()){
            case "1":
                tvType.setText(mContext.getString(R.string.question_xunjian));
                tvType.setBackground(ResUtils.getResources().getDrawable(R.drawable.bg_lv_item_bt_xj));

                break;
            case "2":
                tvType.setText(mContext.getString(R.string.question_weixiu));
                tvType.setBackground(ResUtils.getResources().getDrawable(R.drawable.bg_lv_item_bt_wxyh));

                break;
            case "3":
                tvType.setText(mContext.getString(R.string.question_baojie));
                tvType.setBackground(ResUtils.getResources().getDrawable(R.drawable.bg_lv_item_bt_bj));

                break;
            case "4":
                tvType.setText(mContext.getString(R.string.question_qita));
                tvType.setBackground(ResUtils.getResources().getDrawable(R.drawable.bg_lv_item_bt_qita));

                break;
            default:
        }
//        view.setText(R.id.problemstatusTv,item.getProblemStatusName());
        DictMapEntity dictMapEntity = DictMapManager.getInstance().getmDictMap();
        Map<String, String> map_status = dictMapEntity.getObject().getProblemStatus();
        view.setText(R.id.problemstatusTv,map_status.get(item.getProblemStatus()));


    }


}
