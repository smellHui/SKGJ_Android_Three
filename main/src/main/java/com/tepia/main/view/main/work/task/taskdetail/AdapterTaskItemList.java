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
import com.tepia.main.R;
import com.tepia.main.model.task.bean.PositionNamesBean;
import com.tepia.main.model.task.bean.TaskItemBean;


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

        tvDescribe.setText(item.getSuperviseItemName());
        tvTaskTime.setText(item.getExcuteDate());
        tvItemCount.setText(item.getReservoirSuperviseSequence() + "");
        tvItemCount.setText(view.getLayoutPosition() + 1 + "");
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
        } else {
            ivIsOver.setVisibility(View.GONE);
            tvTaskTime.setVisibility(View.GONE);
        }
    }

    public boolean isDealFinish() {
        for (TaskItemBean bean : getData()) {
            if (bean.getCompleteStatus() == null) {
                return false;
            }
            if (!"0".equals(bean.getCompleteStatus())) {
                return false;
            }
        }
        return true;
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
}
