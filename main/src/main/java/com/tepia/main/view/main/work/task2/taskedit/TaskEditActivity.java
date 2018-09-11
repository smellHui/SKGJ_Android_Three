package com.tepia.main.view.main.work.task2.taskedit;


import android.databinding.DataBindingUtil;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;
import com.google.gson.Gson;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.base.utils.DoubleClickUtil;
import com.tepia.base.utils.ResUtils;
import com.tepia.base.utils.TimeFormatUtils;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.utils.Utils;
import com.tepia.main.R;
import com.tepia.main.databinding.ActivityTaskEditBinding;
import com.tepia.main.model.task.bean.TaskBean;
import com.tepia.main.model.task.bean.TaskItemBean;

import java.util.List;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 * 工单 编辑页
 * @author Joeshould
 */
@Route(path = AppRoutePath.app_task_edit)
public class TaskEditActivity extends MVPBaseActivity<TaskEditContract.View, TaskEditPresenter> implements TaskEditContract.View {
    @Autowired(name = "workOrderId")
    String workOrderId;
    @Autowired(name = "taskBean")
    String temp;
    private ActivityTaskEditBinding mBinding;
    private TaskBean taskBean;

    private TextView tvRightText;
    private AdapterTaskItemConfig adapterTaskItemConfig;

    @Override
    public int getLayoutId() {
        return R.layout.activity_task_edit;
    }

    @Override
    public void initView() {
        mBinding = DataBindingUtil.bind(mRootView);
        tvRightText = findViewById(R.id.tv_right_text);
        setCenterTitle("工单编辑");
        tvRightText.setVisibility(View.VISIBLE);
        tvRightText.setText("确定");
        showBack();
        if (taskBean != null) {
            mBinding.tvPlanStartTime.setText(taskBean.getPlanStartTime());
            mBinding.tvPlanEndTime.setText(taskBean.getPlanEndTime());
            mBinding.etEventDes.setText(taskBean.getRemarks());
            mBinding.etTaskName.setText(taskBean.getWorkOrderName());

        }
        initListView();
    }

    private void initListView() {
        mBinding.rvTaskItemConfigList.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.rvTaskItemConfigList.setNestedScrollingEnabled(false);

        adapterTaskItemConfig = new AdapterTaskItemConfig(R.layout.lv_item_task_item_config, null);
        mBinding.rvTaskItemConfigList.setAdapter(adapterTaskItemConfig);
        ItemDragAndSwipeCallback itemDragAndSwipeCallback
                = new ItemDragAndSwipeCallback(adapterTaskItemConfig);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragAndSwipeCallback);
        itemTouchHelper.attachToRecyclerView( mBinding.rvTaskItemConfigList);
        // 开启拖拽
        adapterTaskItemConfig.enableDragItem(itemTouchHelper,R.id.lo_task_list_item,true);
        adapterTaskItemConfig.setOnItemDragListener(new OnItemDragListener() {
            @Override
            public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos) {
                ToastUtils.shortToast("你在拖拽第" + (pos + 1) + "个位置的item哦！");
            }

            @Override
            public void onItemDragMoving(RecyclerView.ViewHolder source, int from,
                                         RecyclerView.ViewHolder target, int to) {

            }

            @Override
            public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos) {
                ToastUtils.shortToast("拖拽到了第" + (pos + 1) + "个位置哦！");
            }
        });
        adapterTaskItemConfig.enableSwipeItem();
        adapterTaskItemConfig.setOnItemSwipeListener(new OnItemSwipeListener() {
            @Override
            public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int pos) {
                ToastUtils.shortToast("继续向左滑动即可删除第" + (pos + 1) + "个位置的item");
            }

            @Override
            public void clearView(RecyclerView.ViewHolder viewHolder, int pos) {

            }

            @Override
            public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int pos) {
                ToastUtils.shortToast("删除了第" + (pos + 1) + "个位置的item哦");
            }

            @Override
            public void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder,
                                          float dX, float dY, boolean isCurrentlyActive) {

            }
        } );
    }

    @Override
    public void initData() {
        temp = getIntent().getStringExtra("taskBean");
        taskBean = new Gson().fromJson(temp, TaskBean.class);

    }

    @Override
    protected void initListener() {
        mBinding.loPlanStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                showTimeSelect1();
            }
        });
        mBinding.loPlanEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                showTimeSelect2();
            }
        });
        tvRightText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                String workOrderName = mBinding.etTaskName.getText().toString().trim();
                String planStartTime = mBinding.tvPlanStartTime.getText().toString().trim();
                String planEndTime = mBinding.tvPlanEndTime.getText().toString().trim();
                String remarks = mBinding.etEventDes.getText().toString().trim();
                String workOrderItemIds = adapterTaskItemConfig.getItemConfigString();
                if (TextUtils.isEmpty(workOrderName)){
                    ToastUtils.shortToast("请输入工单名称");
                    return;
                }
                if (TextUtils.isEmpty(remarks)){
                    ToastUtils.shortToast("请输入工单描述");
                    return;
                }
                if (TextUtils.isEmpty(workOrderItemIds)){
                    ToastUtils.shortToast("请选择工单项");
                    return;
                }
                mPresenter.updateWork(taskBean.getWorkOrderId(), workOrderName,
                        planStartTime, planEndTime, remarks, workOrderItemIds);
            }
        });
    }

    /**
     * 显示结束时间 选择器
     */
    private void showTimeSelect2() {
        OnDateSetListener onDateSetListener = new OnDateSetListener() {
            @Override
            public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                mBinding.tvPlanEndTime.setText(TimeFormatUtils.getStringDate(millseconds));
            }
        };
        new TimePickerDialog.Builder()
                .setCallBack(onDateSetListener)
                .setCancelStringId(ResUtils.getString(R.string.cancel))
                .setSureStringId(ResUtils.getString(R.string.sure))
                .setTitleStringId(ResUtils.getString(R.string.starttime))
                .setYearText(ResUtils.getString(R.string.year))
                .setMonthText(ResUtils.getString(R.string.month))
                .setDayText(ResUtils.getString(R.string.day))
                .setHourText(ResUtils.getString(R.string.hour))
                .setMinuteText(ResUtils.getString(R.string.minute))
                .setCyclic(false)
                .setMinMillseconds(System.currentTimeMillis() - 90 * 1000 * 60 * 60 * 24L) // 最小时间
                .setMaxMillseconds(System.currentTimeMillis() + 90 * 1000 * 60 * 60 * 24L) // 最大时间
                .setCurrentMillseconds(System.currentTimeMillis()) // 当前时间
                .setThemeColor(ResUtils.getResources().getColor(R.color.colorPrimary))
                .setType(Type.ALL)
                .setWheelItemTextNormalColor(Utils.getContext().getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(ResUtils.getResources().getColor(R.color.black))
                .setWheelItemTextSize(12)
                .build()
                .show(getSupportFragmentManager(), "all");
    }

    /**
     * 显示开始时间 选择器
     */
    private void showTimeSelect1() {
        OnDateSetListener onDateSetListener = new OnDateSetListener() {
            @Override
            public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                mBinding.tvPlanStartTime.setText(TimeFormatUtils.getStringDate(millseconds));
            }
        };
        new TimePickerDialog.Builder()
                .setCallBack(onDateSetListener)
                .setCancelStringId(ResUtils.getString(R.string.cancel))
                .setSureStringId(ResUtils.getString(R.string.sure))
                .setTitleStringId(ResUtils.getString(R.string.starttime))
                .setYearText(ResUtils.getString(R.string.year))
                .setMonthText(ResUtils.getString(R.string.month))
                .setDayText(ResUtils.getString(R.string.day))
                .setHourText(ResUtils.getString(R.string.hour))
                .setMinuteText(ResUtils.getString(R.string.minute))
                .setCyclic(false)
                .setMinMillseconds(System.currentTimeMillis() - 90 * 1000 * 60 * 60 * 24L) // 最小时间
                .setMaxMillseconds(System.currentTimeMillis() + 90 * 1000 * 60 * 60 * 24L) // 最大时间
                .setCurrentMillseconds(System.currentTimeMillis()) // 当前时间
                .setThemeColor(ResUtils.getResources().getColor(R.color.colorPrimary))
                .setType(Type.ALL)
                .setWheelItemTextNormalColor(Utils.getContext().getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(ResUtils.getResources().getColor(R.color.black))
                .setWheelItemTextSize(12)
                .build()
                .show(getSupportFragmentManager(), "all");

    }

    @Override
    protected void initRequestData() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.getAllItemList(taskBean.getWorkOrderId());
    }

    @Override
    public void updateWorkSuccess() {
        finish();
    }

    @Override
    public void getAllItemListSuccess(List<TaskItemBean> data) {
        if (taskBean != null && taskBean.getBizReservoirWorkOrderItems() != null){
            for (TaskItemBean bean :taskBean.getBizReservoirWorkOrderItems()) {
                for (int i = 0; i < data.size(); i++) {
                    if (bean.getItemId().equals(data.get(i).getItemId())){
                        data.get(i).setSelected(true);
                        continue;
                    }
                }
            }
        }
        adapterTaskItemConfig.setNewData(data);
    }
}
