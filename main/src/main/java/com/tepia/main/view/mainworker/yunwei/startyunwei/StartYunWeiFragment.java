package com.tepia.main.view.mainworker.yunwei.startyunwei;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.Gson;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.MVPBaseFragment;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.view.dialog.basedailog.ActionSheetDialog;
import com.tepia.base.view.dialog.basedailog.OnOpenItemClick;
import com.tepia.main.R;
import com.tepia.main.databinding.FragemntStartYunweiBinding;
import com.tepia.main.model.detai.ReservoirBean;
import com.tepia.main.model.task.UnfinishedNumResponse;
import com.tepia.main.model.task.bean.TaskBean;
import com.tepia.main.model.task.bean.TaskItemBean;
import com.tepia.main.model.task.bean.WorkOrderNumBean;
import com.tepia.main.model.user.UserManager;
import com.tepia.main.view.main.work.task.taskdetail.AdapterTaskItemList;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author :      zhang xinhua
 * Version         :       1.0
 * 功能描述        :
 **/

public class StartYunWeiFragment extends MVPBaseFragment<StartYunWeiContract.View, StartYunWeiPresenter> implements StartYunWeiContract.View {

    private FragemntStartYunweiBinding mBinding;
    /**
     * 被选择的水库
     */
    private ReservoirBean selectedResrvoir;
    /**
     * 被选择的运维类型
     */
    private String selectedYunWeiType;
    private AdapterTaskItemList adapterTaskItemList;
    public String defaultYunweiType;
    private ArrayList<String> yunweiTypeStrs;

    @Override
    protected int getLayoutId() {
        return R.layout.fragemnt_start_yunwei;
    }

    @Override
    protected void initData() {
        Map<String, String> map = UserManager.getInstance().getYunWeiTypeList();
        if (map != null) {
            yunweiTypeStrs = new ArrayList<>();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                yunweiTypeStrs.add(entry.getKey() + "");
            }
        }
    }

    @Override
    protected void initView(View view) {
        mBinding = DataBindingUtil.bind(view);
        initListener();
        mBinding.rvTaskItemList.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterTaskItemList = new AdapterTaskItemList(getContext(), R.layout.lv_item_task_item_list2, null);
        mBinding.rvTaskItemList.setAdapter(adapterTaskItemList);
        mBinding.tvStartYunwei.setText("开始运维");
        mBinding.loTaskNumPresent.setVisibility(View.GONE);
        if (!TextUtils.isEmpty(defaultYunweiType)) {
            selectedYunWeiType = defaultYunweiType;
            mBinding.loSelectYunweiType.setVisibility(View.GONE);
            switch (defaultYunweiType) {
                case "1":
                    mBinding.tvStartYunwei.setText("开始巡检");
                    mBinding.tvOperationTaskNumTip.setText("巡检任务");
                    mBinding.tvDealedOperationTaskNumTip.setText("完成巡检");
                    break;
                case "2":
                    mBinding.tvStartYunwei.setText("开始维护");
                    mBinding.tvOperationTaskNumTip.setText("维护任务");
                    mBinding.tvDealedOperationTaskNumTip.setText("完成维护");
                    break;
                case "3":
                    mBinding.tvStartYunwei.setText("开始保洁");
                    mBinding.tvOperationTaskNumTip.setText("保洁任务");
                    mBinding.tvDealedOperationTaskNumTip.setText("完成保洁");
                    break;
                default:
                    mBinding.tvStartYunwei.setText("开始运维");
                    mBinding.tvOperationTaskNumTip.setText("运维任务");
                    mBinding.tvDealedOperationTaskNumTip.setText("完成运维");
                    break;
            }
        } else {
            mBinding.loSelectYunweiType.setVisibility(View.VISIBLE);
            if (yunweiTypeStrs != null && yunweiTypeStrs.size() != 0) {
                selectedYunWeiType = UserManager.getInstance().getYunWeiTypeList().get(yunweiTypeStrs.get(0));
                mBinding.tvYunweiType.setText(yunweiTypeStrs.get(0));
            } else {
                selectedYunWeiType = "1";
                mBinding.tvYunweiType.setText("巡检");
                mBinding.tvOperationTaskNumTip.setText("巡检任务");
                mBinding.tvDealedOperationTaskNumTip.setText("完成巡检");
            }
        }

    }

    private void initListener() {
        mBinding.loSelectReservoir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSelectReservoir();
            }
        });
        mBinding.loSelectYunweiType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showSelectYunweiType();
            }
        });
        mBinding.tvStartYunwei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(selectedYunWeiType)) {
                    ToastUtils.shortToast("请选择运维类型");
                    return;
                }
                if (selectedResrvoir == null) {
                    ToastUtils.shortToast("请选择水库");
                    return;
                }
                mPresenter.getUnfinishedNum(selectedResrvoir.getReservoirId(), selectedYunWeiType);
//                mPresenter.newStartExecute(selectedResrvoir.getReservoirId(), selectedResrvoir.getReservoir(), selectedYunWeiType);
            }
        });
    }

    private void showSelectYunweiType() {
        String[] stringItems = {"巡检", "维护", "保洁"};
        if (yunweiTypeStrs != null && yunweiTypeStrs.size() != 0) {
            stringItems = yunweiTypeStrs.toArray(new String[yunweiTypeStrs.size()]);
        }

        final ActionSheetDialog dialog = new ActionSheetDialog(getBaseActivity(), stringItems, null);
        dialog.title("请运维类型")
                .titleTextSize_SP(14.5f)
                .widthScale(0.8f)
                .show();
        String[] finalStringItems = stringItems;
        dialog.setOnOpenItemClickL(new OnOpenItemClick() {
            @Override
            public void onOpenItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (yunweiTypeStrs != null) {
                    selectedYunWeiType = UserManager.getInstance().getYunWeiTypeList().get(yunweiTypeStrs.get(position)) + "";
                } else {
                    selectedYunWeiType = position + 1 + "";
                }
                mBinding.tvYunweiType.setText(finalStringItems[position]);
                selectFinish(selectedYunWeiType, selectedResrvoir);
                dialog.dismiss();
            }
        });
    }


    private void showSelectReservoir() {
        ArrayList<ReservoirBean> reservoirBeans = UserManager.getInstance().getLocalReservoirList();
        if (reservoirBeans != null) {
            String[] stringItems = new String[reservoirBeans.size()];
            for (int i = 0; i < reservoirBeans.size(); i++) {
                stringItems[i] = reservoirBeans.get(i).getReservoir();
            }
            final ActionSheetDialog dialog = new ActionSheetDialog(getBaseActivity(), stringItems, null);
            dialog.title("请选择水库")
                    .titleTextSize_SP(14.5f)
                    .widthScale(0.8f)
                    .show();
            dialog.setOnOpenItemClickL(new OnOpenItemClick() {
                @Override
                public void onOpenItemClick(AdapterView<?> parent, View view, int position, long id) {
                    selectedResrvoir = reservoirBeans.get(position);
                    mBinding.tvReservoir.setText(selectedResrvoir.getReservoir());
                    selectFinish(selectedYunWeiType, selectedResrvoir);
                    dialog.dismiss();
                }
            });
        }
    }

    private void selectFinish(String selectedYunWeiType, ReservoirBean selectedResrvoir) {
        if (TextUtils.isEmpty(selectedYunWeiType)) {
            return;
        }
        if (selectedResrvoir == null) {
            return;
        }
        switch (selectedYunWeiType) {
            case "1":
                mBinding.tvStartYunwei.setText("开始巡检");
                mBinding.tvOperationTaskNumTip.setText("巡检任务");
                mBinding.tvDealedOperationTaskNumTip.setText("完成巡检");
                break;
            case "2":
                mBinding.tvStartYunwei.setText("开始维护");
                mBinding.tvOperationTaskNumTip.setText("维护任务");
                mBinding.tvDealedOperationTaskNumTip.setText("完成维护");
                break;
            case "3":
                mBinding.tvStartYunwei.setText("开始保洁");
                mBinding.tvOperationTaskNumTip.setText("保洁任务");
                mBinding.tvDealedOperationTaskNumTip.setText("完成保洁");
                break;
            default:
                mBinding.tvStartYunwei.setText("开始运维");
                mBinding.tvOperationTaskNumTip.setText("运维任务");
                mBinding.tvDealedOperationTaskNumTip.setText("完成运维");
                break;
        }
        mPresenter.getItemListByReservoirId(selectedResrvoir.getReservoirId(), selectedYunWeiType);
        mPresenter.getWorkOrderNumByReservoirId(selectedResrvoir.getReservoirId(), selectedYunWeiType);
    }

    @Override
    protected void initRequestData() {
        if (TextUtils.isEmpty(selectedYunWeiType)) {
            return;
        }
        if (UserManager.getInstance().getDefaultReservoir() != null) {
            selectedResrvoir = UserManager.getInstance().getDefaultReservoir();
            mBinding.tvReservoir.setText(selectedResrvoir.getReservoir());
            mPresenter.getItemListByReservoirId(selectedResrvoir.getReservoirId(), selectedYunWeiType);
            mPresenter.getWorkOrderNumByReservoirId(selectedResrvoir.getReservoirId(), selectedYunWeiType);
        }

    }

    @Override
    public void getWorkOrderNumByReservoirIdSuccess(WorkOrderNumBean data) {
        mBinding.loTaskNumPresent.setVisibility(View.VISIBLE);
        mBinding.tvOperationTaskNum.setText(data.getTotals() + "次");
        mBinding.tvDealedOperationTaskNum.setText(data.getDoneNum() + "次");
        if (data.getTotals() == 0) {
            mBinding.tvDealedPresent.setText("--");
        } else {
            DecimalFormat df = new DecimalFormat("#.0");
            mBinding.tvDealedPresent.setText(df.format(data.getDoneNum() * 100.0 / data.getTotals()) + "%");
        }
    }

    @Override
    public void getItemListByReservoirIdSuccess(List<TaskItemBean> data) {
        adapterTaskItemList.setNewData(data);
    }

    @Override
    public void newStartExecuteSuccess(TaskBean data) {
        if (data != null) {
            ARouter.getInstance().build(AppRoutePath.app_task_detail)
                    .withString("workOrderId", data.getWorkOrderId())
                    .navigation();
        }
    }

    @Override
    public void getUnfinishedNumSuccess(UnfinishedNumResponse.DataBean data) {
        showNewWorkOrder(data);
    }

    private void showNewWorkOrder(UnfinishedNumResponse.DataBean data) {
        if (data == null || data.getTotals() == 0) {
            new AlertDialog.Builder(getContext()).setTitle("新建任务").setMessage("是否确定新建任务")
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            mPresenter.newStartExecute(selectedResrvoir.getReservoirId(), selectedResrvoir.getReservoir(), selectedYunWeiType);
                        }
                    })
                    .show();
        } else {
            new AlertDialog.Builder(getContext()).setTitle("新建任务")
                    .setMessage("存在当日未完成的任务")
                    .setNegativeButton("继续执行", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            ARouter.getInstance().build(AppRoutePath.app_task_detail)
                                    .withString("workOrderId", data.getWork_order_id())
                                    .navigation();
                        }
                    })
                    .setPositiveButton("新建工单", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            mPresenter.newStartExecute(selectedResrvoir.getReservoirId(), selectedResrvoir.getReservoir(), selectedYunWeiType);
                        }
                    })
                    .show();
        }
    }
}
