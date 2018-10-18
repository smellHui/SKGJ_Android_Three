package com.tepia.main.view.mainworker.yunwei.yunweilist;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.loadmore.SimpleLoadMoreView;
import com.jzxiang.pickerview.data.Type;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.MVPBaseFragment;
import com.tepia.base.utils.DoubleClickUtil;
import com.tepia.base.utils.Utils;
import com.tepia.base.view.dialog.basedailog.ActionSheetDialog;
import com.tepia.base.view.dialog.basedailog.OnOpenItemClick;
import com.tepia.main.R;
import com.tepia.main.databinding.FragemntYunweiListBinding;
import com.tepia.main.model.detai.ReservoirBean;
import com.tepia.main.model.task.bean.TaskBean;
import com.tepia.main.model.task.response.TaskListResponse;
import com.tepia.main.model.user.UserManager;
import com.tepia.main.utils.TimePickerDialogUtil;
import com.tepia.main.view.maincommon.setting.ChoiceReservoirActivity;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author :      zhang xinhua
 * Version         :       1.0
 * 功能描述        :        运维记录
 **/

public class YunWeiListFragment extends MVPBaseFragment<YunWeiListContract.View, YunWeiListPresenter> implements YunWeiListContract.View {
    private FragemntYunweiListBinding mBinding;
    private AdapterPatrolWorkOrderList adapterPatrolWorkOrderList;
    private ReservoirBean selectedResrvoir;
    private String selectedYunWeiType;
    public String defaultYunweiType;
    private ArrayList<String> yunweiTypeStrs;
    private TimePickerDialogUtil timePickerDialogUtil;
    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM", Locale.getDefault());


    @Override
    protected int getLayoutId() {
        return R.layout.fragemnt_yunwei_list;
    }

    @Override
    protected void initData() {
        Map<String, String> map = UserManager.getInstance().getYunWeiTypeList();
        if (map != null) {
            yunweiTypeStrs = new ArrayList<>();
            yunweiTypeStrs.add("全部");
            for (Map.Entry<String, String> entry : map.entrySet()) {
                yunweiTypeStrs.add(entry.getKey() + "");
            }
        }
    }

    @Override
    protected void initView(View view) {
        mBinding = DataBindingUtil.bind(view);
        mBinding.rvWorkOrderList.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterPatrolWorkOrderList = new AdapterPatrolWorkOrderList(R.layout.lv_patrol_workorder_list, null);
        mBinding.rvWorkOrderList.setAdapter(adapterPatrolWorkOrderList);
        if (!TextUtils.isEmpty(defaultYunweiType)) {
            selectedYunWeiType = defaultYunweiType;
            mBinding.loSelectYunweiType.setVisibility(View.GONE);
        }
        initMonthDate();
        initListener();

    }

    private void initMonthDate() {
        long fiveYears = 5L * 365 * 1000 * 60 * 60 * 24L;
        timePickerDialogUtil = new TimePickerDialogUtil(getActivity(), sf);
        //得到一个Calendar的实例
        Calendar ca = Calendar.getInstance();
        //设置时间为当前时间
        ca.setTime(new Date());
        Date lastDate = ca.getTime();
        mBinding.tvSelectMonth.setText(sf.format(lastDate));
        mBinding.loSelectMonth.setOnClickListener(v -> {
            String current = (String) mBinding.tvSelectMonth.getText();
            long currentLong = 0;
            if (current != null && current.length() > 0) {
                currentLong = strToLong(current);
            }
            timePickerDialogUtil.initTimePickerSetStartAndEnd((timePickerView, millseconds) -> {
                String text = timePickerDialogUtil.getDateToString(millseconds);
                mBinding.tvSelectMonth.setText(text);
            }, Type.YEAR_MONTH, System.currentTimeMillis() - fiveYears, System.currentTimeMillis(), R.color.color_load_blue);
            if (timePickerDialogUtil.startDialog != null) {
                timePickerDialogUtil.startDialog = null;
            }
            if (currentLong != 0) {
                timePickerDialogUtil.builder.setCurrentMillseconds(currentLong);
            }
            timePickerDialogUtil.builder.setTitleStringId("请选择查询月份");
            timePickerDialogUtil.startDialog = timePickerDialogUtil.builder.build();
            timePickerDialogUtil.startDialog.show(getFragmentManager(), "all");
        });
    }

    public static long strToLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        //继续转换得到秒数的long型
        long lTime = strtodate.getTime();
        return lTime;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ChoiceReservoirActivity.resultCode:
                if (null != data) {
                    boolean isSelectAll = data.getBooleanExtra(ChoiceReservoirActivity.isAllReservoir, false);
                    if (isSelectAll) {
                        mBinding.tvReservoir.setText("全部");
                        selectedResrvoir = null;
                        return;
                    }
                }
                ReservoirBean defaultReservoir = UserManager.getInstance().getDefaultReservoir();
                mBinding.tvReservoir.setText(defaultReservoir.getReservoir());
                break;
            default:
                break;
        }
    }


    private void initListener() {
        mBinding.srflContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (selectedResrvoir == null) {
                    mPresenter.getPatrolWorkOrderList("", selectedYunWeiType, mBinding.tvSelectMonth.getText().toString());
                } else {
                    mPresenter.getPatrolWorkOrderList(selectedResrvoir.getReservoirId(), selectedYunWeiType, mBinding.tvSelectMonth.getText().toString());
                }
                mBinding.srflContainer.setRefreshing(false);
            }
        });

        adapterPatrolWorkOrderList.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });

        mBinding.loSelectYunweiType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSelectYunweiType();
            }
        });


        mBinding.tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (selectedResrvoir == null) {
                    mPresenter.getPatrolWorkOrderList("", selectedYunWeiType, mBinding.tvSelectMonth.getText().toString());
                } else {
                    mPresenter.getPatrolWorkOrderList(selectedResrvoir.getReservoirId(), selectedYunWeiType, mBinding.tvSelectMonth.getText().toString());
                }

            }
        });
        mBinding.loSelectReservoir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                showSelectReservoir();
                Intent intent = new Intent(getActivity(), ChoiceReservoirActivity.class);
                ChoiceReservoirActivity.setIntent(intent, true);
                startActivityForResult(intent, ChoiceReservoirActivity.resultCode);
            }
        });
        adapterPatrolWorkOrderList.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                ARouter.getInstance().build(AppRoutePath.app_task_detail)
                        .withString("workOrderId", adapterPatrolWorkOrderList.getData().get(position).getWorkOrderId())
                        .navigation();
            }
        });


        adapterPatrolWorkOrderList.openLoadAnimation();
        adapterPatrolWorkOrderList.setEnableLoadMore(true);
        adapterPatrolWorkOrderList.setLoadMoreView(new SimpleLoadMoreView());
        adapterPatrolWorkOrderList.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mBinding.rvWorkOrderList.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mPresenter.isCanLoadMore) {
                            if (selectedResrvoir == null) {
                                mPresenter.getPatrolWorkOrderListMore("", selectedYunWeiType, mBinding.tvSelectMonth.getText().toString());
                            } else {
                                mPresenter.getPatrolWorkOrderListMore(selectedResrvoir.getReservoirId(), selectedYunWeiType, mBinding.tvSelectMonth.getText().toString());
                            }
                        } else {
                            adapterPatrolWorkOrderList.loadMoreEnd();
                        }
                    }
                }, 1000);

            }
        }, mBinding.rvWorkOrderList);
    }

    @Override
    protected void initRequestData() {
        mPresenter.getPatrolWorkOrderList("", selectedYunWeiType, mBinding.tvSelectMonth.getText().toString());
    }

    private void showSelectYunweiType() {

        String[] stringItems = {"全部", "巡查", "维护", "保洁"};
        if (yunweiTypeStrs != null && yunweiTypeStrs.size() != 0) {
            stringItems = yunweiTypeStrs.toArray(new String[yunweiTypeStrs.size()]);
        }
        final ActionSheetDialog dialog = new ActionSheetDialog(getBaseActivity(), stringItems, null);
        dialog.title("请选择运维类型")
                .titleTextSize_SP(14.5f)
                .widthScale(0.8f)
                .show();
        String[] finalStringItems = stringItems;
        dialog.setOnOpenItemClickL(new OnOpenItemClick() {
            @Override
            public void onOpenItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    if (yunweiTypeStrs != null) {
                        selectedYunWeiType = UserManager.getInstance().getYunWeiTypeList().get(yunweiTypeStrs.get(position)) + "";
                    } else {
                        selectedYunWeiType = position + "";
                    }
                } else {
                    selectedYunWeiType = "";
                }
                mBinding.tvYunweiType.setText(finalStringItems[position]);
                dialog.dismiss();
            }
        });
    }

    private void showSelectReservoir() {
        ArrayList<ReservoirBean> reservoirBeans = UserManager.getInstance().getLocalReservoirList();
        if (reservoirBeans != null) {
            String[] stringItems = new String[reservoirBeans.size() + 1];
            stringItems[0] = "全部";
            for (int i = 0; i < reservoirBeans.size(); i++) {
                stringItems[i + 1] = reservoirBeans.get(i).getReservoir();
            }
            final ActionSheetDialog dialog = new ActionSheetDialog(getBaseActivity(), stringItems, null);
            dialog.title("请选择水库")
                    .titleTextSize_SP(14.5f)
                    .widthScale(0.8f)
                    .show();
            dialog.setOnOpenItemClickL(new OnOpenItemClick() {
                @Override
                public void onOpenItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (position != 0) {
                        selectedResrvoir = reservoirBeans.get(position - 1);
                    } else {
                        selectedResrvoir = null;
                    }
                    mBinding.tvReservoir.setText(stringItems[position]);
                    dialog.dismiss();
                }
            });
        }
    }

    @Override
    public void getPatrolWorkOrderListSuccess(List<TaskBean> list) {

        adapterPatrolWorkOrderList.setNewData(list);
        if (list == null || list.size() == 0) {
            adapterPatrolWorkOrderList.getData().clear();
            adapterPatrolWorkOrderList.notifyDataSetChanged();
            View view = LayoutInflater.from(Utils.getContext()).inflate(R.layout.view_empty_list_view, null);
            adapterPatrolWorkOrderList.setEmptyView(view);
        }
        adapterPatrolWorkOrderList.loadMoreComplete();
    }

    @Override
    public void getPatrolWorkOrderListMoreSuccess(TaskListResponse.DataBean dataBean) {

        int i = dataBean.getStartRow() - 1;
        for (int j = 0; j < dataBean.getList().size(); j++) {
            if (!adapterPatrolWorkOrderList.getData().contains(dataBean.getList().get(j))) {
                adapterPatrolWorkOrderList.addData(i + j, dataBean.getList().get(j));
            } else {
                adapterPatrolWorkOrderList.setData(i + j, dataBean.getList().get(j));
            }
        }
        adapterPatrolWorkOrderList.loadMoreComplete();

    }
}
