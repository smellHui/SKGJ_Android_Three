package com.tepia.main.view.mainworker.yunwei.startyunwei;


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
import com.tepia.main.model.task.bean.TaskBean;
import com.tepia.main.model.task.bean.TaskItemBean;
import com.tepia.main.model.task.bean.WorkOrderNumBean;
import com.tepia.main.model.user.UserManager;
import com.tepia.main.view.main.work.task.taskdetail.AdapterTaskItemList;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    protected int getLayoutId() {
        return R.layout.fragemnt_start_yunwei;
    }

    @Override
    protected void initData() {

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
            mBinding.loSelectYunweiTypeContainer.setVisibility(View.GONE);
            switch (defaultYunweiType) {
                case "1":
                    mBinding.tvStartYunwei.setText("开始巡检");
                    break;
                case "2":
                    mBinding.tvStartYunwei.setText("开始维护");
                    break;
                case "3":
                    mBinding.tvStartYunwei.setText("开始保洁");
                    break;
                default:
                    mBinding.tvStartYunwei.setText("开始运维");
                    break;
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
                mPresenter.newStartExecute(selectedResrvoir.getReservoirId(), selectedResrvoir.getReservoir(), selectedYunWeiType);
            }
        });
    }

    private void showSelectYunweiType() {

        String[] stringItems = {"巡查", "维护", "保洁"};

        final ActionSheetDialog dialog = new ActionSheetDialog(getBaseActivity(), stringItems, null);
        dialog.title("请运维类型")
                .titleTextSize_SP(14.5f)
                .widthScale(0.8f)
                .show();
        dialog.setOnOpenItemClickL(new OnOpenItemClick() {
            @Override
            public void onOpenItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedYunWeiType = position + 1 + "";
                mBinding.tvYunweiType.setText(stringItems[position]);
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
            mBinding.tvDealedPresent.setText(data.getDoneNum() * 100.0 / data.getTotals() + "%");
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
}
