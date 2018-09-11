package com.tepia.main.view.main.work.task2.taskedit;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.main.R;
import com.tepia.main.databinding.LvItemTaskItemConfigBinding;
import com.tepia.main.model.task.bean.PositionNamesBean;
import com.tepia.main.model.task.bean.TaskItemBean;
import com.tepia.main.view.main.work.task.taskdetail.AdapterPositionTitle;

import java.util.ArrayList;
import java.util.List;

import static com.tepia.base.utils.Utils.getContext;

public class AdapterTaskItemConfig extends BaseItemDraggableAdapter<TaskItemBean, BaseViewHolder> {
    private LvItemTaskItemConfigBinding mBinding;

    public AdapterTaskItemConfig(int layoutResId, @Nullable List<TaskItemBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TaskItemBean item) {
        mBinding = DataBindingUtil.bind(helper.itemView);
        if (item.getPositionTreeNames() != null) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            mBinding.rvPositionTitle.setLayoutManager(layoutManager);
            List<PositionNamesBean> positionNames = new ArrayList<>();
            if (item.getPositionTreeNames().contains("/")) {
                String[] temps = TextUtils.split(item.getPositionTreeNames(), "/");
                for (int i = 0; i < temps.length; i++) {
                    positionNames.add(new PositionNamesBean(temps[i]));
                }
            }
            AdapterPositionTitle adapterPositionTitle = new AdapterPositionTitle(R.layout.lv_item_position_title, positionNames);
            mBinding.rvPositionTitle.setAdapter(adapterPositionTitle);
        }
        mBinding.tvDescribe.setText(item.getSuperviseItemName());
        mBinding.tvTaskTime.setVisibility(View.GONE);
        mBinding.tvItemCount.setText(helper.getLayoutPosition() + 1 + "");
        if (item.isSelected()) {
            mBinding.cbSelected.setChecked(true);
        } else {
            mBinding.cbSelected.setChecked(false);
        }
        mBinding.cbSelected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                getData().get(helper.getAdapterPosition()).setSelected(b);
            }
        });
    }

    public String getItemConfigString() {
        String workOrderItemIds = "";
        for (TaskItemBean bean : getData()) {
            if (bean.isSelected()) {
                workOrderItemIds += bean.getItemId() + ",";
            }
        }
        if (workOrderItemIds.endsWith(",")) {
            workOrderItemIds = workOrderItemIds.substring(0, workOrderItemIds.lastIndexOf(','));
        }
        return workOrderItemIds;
    }
}
