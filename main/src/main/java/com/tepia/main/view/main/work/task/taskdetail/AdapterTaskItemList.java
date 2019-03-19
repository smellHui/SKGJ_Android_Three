package com.tepia.main.view.main.work.task.taskdetail;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.base.view.floatview.CollectionsUtil;
import com.tepia.main.R;
import com.tepia.main.model.task.bean.PositionNamesBean;
import com.tepia.main.model.task.bean.TaskBean;
import com.tepia.main.model.task.bean.TaskItemBean;


import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import static com.tepia.base.utils.Utils.getContext;

/**
 * Created by Joeshould on 2018/5/23.
 *
 * @author zxh
 */

public class AdapterTaskItemList extends BaseQuickAdapter<TaskItemBean, BaseViewHolder> {
    private TextView tvItemCount;
    private RecyclerView rvPositionTitle;
    private TextView tvDescribe;
    private TextView tvTaskPeople;
    private TextView tvTaskTime;
    private ImageView ivIsOver;
    private TextView tvItemExp;

    public AdapterTaskItemList(Context context, int layoutResId, @Nullable List<TaskItemBean> data) {
        super(layoutResId, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder view, TaskItemBean item) {


        tvItemCount = view.getView(R.id.tv_item_count);
        rvPositionTitle = view.getView(R.id.rv_position_title);
        tvDescribe = view.getView(R.id.tv_describe);
        tvTaskPeople = view.getView(R.id.tv_task_people);
        tvTaskTime = view.getView(R.id.tv_task_time);
        ivIsOver = view.getView(R.id.iv_is_over);
        tvItemExp = view.getView(R.id.tv_item_exp);

        tvDescribe.setText(item.getSuperviseItemName());
        tvTaskTime.setText(item.getExcuteDate());

        tvItemCount.setText(item.getReservoirSuperviseSequence() + "");
        tvItemCount.setText(view.getLayoutPosition() + 1 + "");
        if(item.isCommitLocal()){
            tvTaskPeople.setVisibility(View.VISIBLE);
        }else {
            tvTaskPeople.setVisibility(View.GONE);
        }
        if (item.getPositionTreeNames() != null) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            rvPositionTitle.setLayoutManager(layoutManager);
            List<PositionNamesBean> positionNames = new ArrayList<>();
            if (item.getPositionTreeNames().contains("/")) {
                String[] temps = TextUtils.split(item.getPositionTreeNames(), "/");
                for (int i = 0; i < temps.length; i++) {
                    positionNames.add(new PositionNamesBean(temps[i]));
                }
            }
            AdapterPositionTitle adapterPositionTitle = new AdapterPositionTitle(R.layout.lv_item_position_title, positionNames);
            rvPositionTitle.setAdapter(adapterPositionTitle);
        }
        if (!TextUtils.isEmpty(item.getCompleteStatus()) && "0".equals(item.getCompleteStatus())) {
            ivIsOver.setVisibility(View.VISIBLE);
            tvTaskTime.setVisibility(View.VISIBLE);
            if (item.getExecuteResultType() != null && item.getExecuteResultType().equals("1")) {
                ivIsOver.setImageResource(R.drawable.icon_status_finish_with_exp);
                tvItemExp.setVisibility(View.VISIBLE);
            } else {
                ivIsOver.setImageResource(R.drawable.icon_status_finish);
                tvItemExp.setVisibility(View.GONE);
            }
            if (item.isCommitLocal() && item.getExecuteDate().compareTo(item.getExcuteDate()) > 0) {
                ivIsOver.setVisibility(View.VISIBLE);
                tvTaskTime.setVisibility(View.VISIBLE);
                if (item.getExResult() != null && item.getExResult().equals("1")) {
                    ivIsOver.setImageResource(R.drawable.icon_status_finish_with_exp);
                    tvItemExp.setVisibility(View.VISIBLE);
                } else {
                    ivIsOver.setImageResource(R.drawable.icon_status_finish);
                    tvItemExp.setVisibility(View.GONE);
                }
                tvTaskTime.setText(item.getExecuteDate());
            }
        } else {
            if (item.isCommitLocal()) {
                ivIsOver.setVisibility(View.VISIBLE);
                tvTaskTime.setVisibility(View.VISIBLE);
                if (item.getExResult() != null && item.getExResult().equals("1")) {
                    ivIsOver.setImageResource(R.drawable.icon_status_finish_with_exp);
                    tvItemExp.setVisibility(View.VISIBLE);
                } else {
                    ivIsOver.setImageResource(R.drawable.icon_status_finish);
                    tvItemExp.setVisibility(View.GONE);
                }
                tvTaskTime.setText(item.getExecuteDate());
            } else {
                ivIsOver.setVisibility(View.GONE);
                tvItemExp.setVisibility(View.GONE);
                tvTaskTime.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 是否所有 任务项 都完成
     *
     * @return
     */
    public boolean isDealFinish() {
        for (TaskItemBean bean : getData()) {
            if (bean.getCompleteStatus() == null || !"0".equals(bean.getCompleteStatus())) {
                if (!bean.isCommitLocal()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 有多少离线数据没有提交
     *
     * @return
     */
    public List<TaskItemBean> getLocalData() {
        TaskBean taskbean = null;
        if (!CollectionsUtil.isEmpty(getData())){
            List<TaskBean> templist = DataSupport.where("workOrderId=?", getData().get(0).getWorkOrderId()).find(TaskBean.class);
            if (!CollectionsUtil.isEmpty(templist)) {
               taskbean = templist.get(0);
            }
        }
        List<TaskItemBean> localData = new ArrayList<>();
        if (taskbean == null || CollectionsUtil.isEmpty(taskbean.getBizReservoirWorkOrderItems())){
            for (TaskItemBean bean : getData()) {
                if (bean.isCommitLocal()) {
                    localData.add(bean);
                }

            }
        }else {
            for (TaskItemBean bean : taskbean.getBizReservoirWorkOrderItems()) {
                if (bean.isCommitLocal()) {
                    localData.add(bean);
                }

            }
        }

        return localData;
    }

    public int getFirstExePositoin() {
        if (getData() == null) {
            return 0;
        }
        for (int i = 0; i < getData().size(); i++) {
            TaskItemBean bean = getData().get(i);
            if (TextUtils.isEmpty(bean.getCompleteStatus())) {
                return i;
            }
            if (!"0".equals(bean.getCompleteStatus())) {
                return i;
            }
        }
        return getData().size();
    }

    /**
     * 有异常的项
     *
     * @return
     */
    public List<TaskItemBean> getAbnormalityList() {
        List<TaskItemBean> abnormality = new ArrayList<>();
        for (int i = 0; i < getData().size(); i++) {
            TaskItemBean bean = getData().get(i);
            if (bean.getExecuteResultType() != null && bean.getExecuteResultType().equals("1")) {
                abnormality.add(bean);
            } else if (bean.isCommitLocal() && bean.getExResult().equals("1")) {
                abnormality.add(bean);
            }
        }
        return abnormality;
    }
}
