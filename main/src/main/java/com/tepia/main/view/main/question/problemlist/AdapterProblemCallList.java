package com.tepia.main.view.main.question.problemlist;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.base.utils.ResUtils;
import com.tepia.main.R;
import com.tepia.main.model.dictmap.DictMapEntity;
import com.tepia.main.model.dictmap.DictMapManager;
import com.tepia.main.model.question.AllproblemBean;
import com.tepia.main.model.question.ProblemCallListBean;
import com.tepia.main.model.question.ProblemDetailBean;

import java.util.List;
import java.util.Map;

/**
 * 事件上报列表
 * @author ly
 * @date 2018/7/31
 */

public class AdapterProblemCallList extends BaseQuickAdapter<ProblemDetailBean.DataBean, BaseViewHolder> {
    //0表示待处理 1表示待审核
    private int flag;
    public AdapterProblemCallList(Context context, int layoutResId, @Nullable List<ProblemDetailBean.DataBean> data,int flag) {
        super(layoutResId, data);
        this.mContext = context;
        this.flag = flag;
    }

    @Override
    protected void convert(BaseViewHolder view, ProblemDetailBean.DataBean item) {
        TextView tvTitle = view.getView(R.id.tv_title);
        TextView tvDescribe = view.getView(R.id.tv_describe);
        TextView tvTime = view.getView(R.id.tv_time);
        TextView tvType = view.getView(R.id.tv_type);
        TextView problemsourceTv = view.getView(R.id.problemsourceTv);
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

        DictMapEntity dictMapEntity = DictMapManager.getInstance().getmDictMap();
        Map<String, String> map_status = dictMapEntity.getObject().getProblemStatus();
        view.setText(R.id.problemstatusTv,map_status.get(item.getProblemStatus()));

        //"1": "工单问题","2": "app上报","3": "web上报"
        if(flag == 0) {
            switch (item.getProblemSource()) {
                case "1":
                    problemsourceTv.setText("工单问题");
                    break;
                case "2":
                    problemsourceTv.setText("app上报");
                    break;
                case "3":
                    problemsourceTv.setText("web上报");
                    break;
                default:
            }
        }else if(flag == 1){
            Map<String, String> map_configure = dictMapEntity.getObject().getProblemCofirmType();
            problemsourceTv.setText(map_configure.get(item.getProblemCofirmType()));
            if("2".equals(item.getProblemCofirmType())){
                problemsourceTv.setTextColor(Color.RED);
            }else{
                problemsourceTv.setTextColor(ContextCompat.getColor(mContext,R.color.text_color));

            }
        }

    }


}
