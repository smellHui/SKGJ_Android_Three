package com.tepia.main.view.maintechnology.yunwei;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jzxiang.pickerview.data.Type;
import com.tepia.base.mvp.MVPBaseFragment;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.NetUtil;
import com.tepia.base.utils.TimeFormatUtils;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.utils.Utils;
import com.tepia.base.view.dialog.basedailog.ActionSheetDialog;
import com.tepia.base.view.dialog.basedailog.OnOpenItemClick;
import com.tepia.main.ConfigConsts;
import com.tepia.main.R;
import com.tepia.main.model.detai.ReservoirBean;
import com.tepia.main.model.jishu.yunwei.OperationReportListResponse;
import com.tepia.main.model.user.UserManager;
import com.tepia.main.utils.EmptyLayoutUtil;
import com.tepia.main.utils.TimePickerDialogUtil;
import com.tepia.main.view.maincommon.setting.ChoiceReservoirActivity;
import com.tepia.main.view.maintechnology.yunwei.adapter.MyOperationReportListAdapter;
import com.tepia.main.view.maintechnology.yunwei.presenter.YunWeiJiShuContract;
import com.tepia.main.view.maintechnology.yunwei.presenter.YunWeiJiShuPresenter;
import com.tepia.main.view.mainworker.report.EmergenceShowDetailActivity;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by      Android studio
 *
 * @author :wwj (from Center Of Wuhan)
 * Date    :2018/10/9
 * Version :1.0
 * 功能描述 :技术责任人 运维上报
 **/

public class OperationReportFragment extends MVPBaseFragment<YunWeiJiShuContract.View, YunWeiJiShuPresenter> {
    private Spinner spinner;
    private RecyclerView rv;
    private TextView tvStartDate;
    private ArrayList<ReservoirBean> localReservoirList;
    private String[] reservoirs;
    private ArrayAdapter spinnerAdapter;
    private int spinnerPosition;
    private List<OperationReportListResponse.DataBean.ListBean> dataList = new ArrayList<>();
    private MyOperationReportListAdapter rvAdapter;
    private int pageSize = 10;
    private int currentPage = 1;
    private boolean isloadmore;
    private boolean first;
    private int mCurrentCounter = 0;
    private String reservoirId;
    private String startDate;
    private TextView tvReservoir;
    private TextView tvEndDate;
    private String endDate;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_operation_list;
    }

    @Override
    protected void initData() {
        localReservoirList = UserManager.getInstance().getLocalReservoirList();
    }

    @Override
    protected void initView(View view) {
        LinearLayout llListTitle = findView(R.id.ll_list_title);
        llListTitle.setVisibility(View.GONE);
//        spinner = findView(R.id.operation_spinner);
        rv = findView(R.id.rv_operation_list);
        tvStartDate = findView(R.id.tv_start_date);
//        tvEndDate = findView(R.id.tv_end_date);
//        initStartDate();
//        initEndDate();
        initMonthDate();
//        initSpinner();
        tvReservoir = findView(R.id.tv_reservoir);
        tvReservoir.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ChoiceReservoirActivity.class);
            intent.putExtra(ChoiceReservoirActivity.isFromYunWei,true);
            startActivityForResult(intent, ChoiceReservoirActivity.resultCode);
        });
        initRecyclerView();
        initSearch();
    }

    private void initMonthDate() {
        long fiveYears = 5L * 365 * 1000 * 60 * 60 * 24L;
        timePickerDialogUtil = new TimePickerDialogUtil(getActivity(), sf);
        //得到一个Calendar的实例
        Calendar ca = Calendar.getInstance();
        //设置时间为当前时间
        ca.setTime(new Date());
        Date lastDate = ca.getTime();
        tvStartDate.setText(sf.format(lastDate));
        tvStartDate.setOnClickListener(v -> {
            String current = (String) tvStartDate.getText();
            long currentLong = 0;
            if (current != null && current.length() > 0) {
                currentLong = strToLong(current);
            }
            timePickerDialogUtil.initTimePickerSetStartAndEnd((timePickerView, millseconds) -> {
                String text = timePickerDialogUtil.getDateToString(millseconds);
                tvStartDate.setText(text);
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


    private void initSearch() {
        Button btConfirm = findView(R.id.bt_confirm);
        btConfirm.setOnClickListener(v -> {
            if (!NetUtil.isNetworkConnected(Utils.getContext())) {
                ToastUtils.shortToast(R.string.no_network);
            } else {
                search();
            }
        });
    }

    private void search() {
        dataList.clear();
        //转换开始日期和结束日期
        getStartAndEndOfMonth();
        rvAdapter.setEnableLoadMore(false);
        currentPage = 1;
        isloadmore = false;
        first = true;
        if (mPresenter != null) {
            if (mPresenter != null) {
                mPresenter.getProblemList(reservoirId, "", startDate, endDate, String.valueOf(currentPage), String.valueOf(pageSize), "", true);
            }
        }
    }

    private void initRecyclerView() {
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvAdapter = new MyOperationReportListAdapter(R.layout.operation_list_report_item, dataList);
        rvAdapter.openLoadAnimation();
        rv.setAdapter(rvAdapter);
        rvAdapter.setOnLoadMoreListener(() -> {
            rv.postDelayed(() -> {
                currentPage++;
                first = false;
                isloadmore = true;
                //加载更多数据
                loadDataOrMore(false);
            }, 1000);
        }, rv);
        rvAdapter.setOnItemClickListener((adapter, view, position) -> {
//            Intent bundle = new Intent(getActivity(),JiShuReportDetailActivity.class);
//            bundle.putExtra("item",dataList.get(position));
//            startActivity(bundle);
            Intent intent = new Intent();
            intent.setClass(getBaseActivity(), EmergenceShowDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString(ConfigConsts.emergence, dataList.get(position).getProblemId());
            bundle.putString("problemStatus", dataList.get(position).getProblemStatus());
            intent.putExtras(bundle);
            startActivityForResult(intent, 1);
        });
    }

    private void loadDataOrMore(boolean isShowLoading) {
        if (mPresenter != null) {
            mPresenter.getProblemList(reservoirId, "", startDate, endDate, String.valueOf(currentPage), String.valueOf(pageSize), "", isShowLoading);
        }
    }

    private TimePickerDialogUtil timePickerDialogUtil;
    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM", Locale.getDefault());

    private void initStartDate() {
        long fiveYears = 5L * 365 * 1000 * 60 * 60 * 24L;
        timePickerDialogUtil = new TimePickerDialogUtil(getActivity(), sf);
        //得到一个Calendar的实例
        Calendar ca = Calendar.getInstance();
        //设置时间为当前时间
        ca.setTime(new Date());
        //昨天减1
        ca.add(Calendar.DATE, -1);
        Date lastDate = ca.getTime();
//        tvStartDate.setText(TimeFormatUtils.dateToStrLong(lastDate));
        tvStartDate.setOnClickListener(v -> {
            String current = (String) tvStartDate.getText();
            long currentLong = 0;
            if (current != null && current.length() > 0) {
                currentLong = strToLong(current);
            }
            timePickerDialogUtil.initTimePickerSetStartAndEnd((timePickerView, millseconds) -> {
                String text = timePickerDialogUtil.getDateToString(millseconds);
                tvStartDate.setText(text);
            }, Type.YEAR_MONTH_DAY, System.currentTimeMillis() - fiveYears, System.currentTimeMillis() + fiveYears, R.color.color_load_blue);
            if (timePickerDialogUtil.startDialog != null) {
                timePickerDialogUtil.startDialog = null;
            }
            if (currentLong != 0) {
                timePickerDialogUtil.builder.setCurrentMillseconds(currentLong);
            }
            timePickerDialogUtil.builder.setTitleStringId("请选择开始日期");
            timePickerDialogUtil.startDialog = timePickerDialogUtil.builder.build();
            timePickerDialogUtil.startDialog.show(getFragmentManager(), "all");
        });
    }

    private void initEndDate() {
        long fiveYears = 5L * 365 * 1000 * 60 * 60 * 24L;
        timePickerDialogUtil = new TimePickerDialogUtil(getActivity(), sf);
        //得到一个Calendar的实例
        Calendar ca = Calendar.getInstance();
        //设置时间为当前时间
        ca.setTime(new Date());
        //昨天减1
        ca.add(Calendar.DATE, -1);
        Date lastDate = ca.getTime();
//        tvEndDate.setText(TimeFormatUtils.dateToStrLong(lastDate));
        tvEndDate.setOnClickListener(v -> {
            String current = (String) tvEndDate.getText();
            long currentLong = 0;
            if (current != null && current.length() > 0) {
                currentLong = strToLong(current);
            }
            timePickerDialogUtil.initTimePickerSetStartAndEnd((timePickerView, millseconds) -> {
                String text = timePickerDialogUtil.getDateToString(millseconds);
                tvEndDate.setText(text);
            }, Type.YEAR_MONTH_DAY, System.currentTimeMillis() - fiveYears, System.currentTimeMillis() + fiveYears, R.color.color_load_blue);
            if (timePickerDialogUtil.startDialog != null) {
                timePickerDialogUtil.startDialog = null;
            }
            if (currentLong != 0) {
                timePickerDialogUtil.builder.setCurrentMillseconds(currentLong);
            }
            timePickerDialogUtil.builder.setTitleStringId("请选择结束日期");
            timePickerDialogUtil.startDialog = timePickerDialogUtil.builder.build();
            timePickerDialogUtil.startDialog.show(getFragmentManager(), "all");
        });
    }

    private void initSpinner() {
        if (localReservoirList != null && localReservoirList.size() > 0) {
            reservoirs = new String[localReservoirList.size() + 1];
            for (int i = 0; i < localReservoirList.size(); i++) {
                reservoirs[i + 1] = localReservoirList.get(i).getReservoir();
            }
            reservoirs[0] = "全部";
        }
//        localReservoirList.forEach(name -> LogUtil.i(name.getReservoir()));
//        LogUtil.i(Arrays.toString(reservoirs));
        //将可选内容与ArrayAdapter连接起来
        spinnerAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_operation_reservoir, reservoirs);
        //设置下拉列表的风格
        spinnerAdapter.setDropDownViewResource(R.layout.dropdown_stytle);
        //将adapter2 添加到spinner中
        spinner.setAdapter(spinnerAdapter);
        //添加事件Spinner事件监听
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                spinnerPosition = pos;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
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

    boolean isFirstLoad;

    @Override
    protected void initRequestData() {
        mPresenter.attachView(new YunWeiJiShuContract.View<OperationReportListResponse>() {
            @Override
            public void success(OperationReportListResponse operationReportListResponse) {
                OperationReportListResponse.DataBean dataBean = operationReportListResponse.getData();
                List<OperationReportListResponse.DataBean.ListBean> data = dataBean.getList();
                int totalSize = dataBean.getTotal();
                if (first) {
                    if (data == null || data.size() == 0) {
                        dataList.clear();
                        rvAdapter.setEmptyView(EmptyLayoutUtil.show("暂无数据"));
                        rvAdapter.notifyDataSetChanged();
                    } else {
                        dataList.clear();
                        dataList.addAll(data);
                        rvAdapter.notifyDataSetChanged();
                        first = false;
                    }
                    rvAdapter.setEnableLoadMore(true);
                } else if (isloadmore) {
                    dataList.addAll(data);
                    rvAdapter.notifyDataSetChanged();
                    mCurrentCounter = rvAdapter.getData().size();
                    if (mCurrentCounter >= totalSize) {
                        //数据全部加载完毕
                        rvAdapter.loadMoreEnd();
                        return;
                    }
                    rvAdapter.loadMoreComplete();
                }
                isFirstLoad = true;
            }

            @Override
            public void failure(String msg) {
                ToastUtils.shortToast(msg);
                isFirstLoad = true;
                if (isloadmore) {
                    if (currentPage > 1) {
                        currentPage--;
                        rvAdapter.loadMoreFail();
                    }
                } else {
                    dataList.clear();
                    rvAdapter.setEmptyView(EmptyLayoutUtil.show(msg));
                }
            }

            @Override
            public Context getContext() {
                return null;
            }
        });
        if (!isFirstLoad) {
            ReservoirBean defaultReservoir = UserManager.getInstance().getDefaultReservoir();
            String defaultReservoirId = defaultReservoir.getReservoirId();
            if (defaultReservoirId != reservoirId) {
                reservoirId = defaultReservoir.getReservoirId();
                tvReservoir.setText(defaultReservoir.getReservoir());
                rvAdapter.setEnableLoadMore(false);
                currentPage = 1;
                isloadmore = false;
                first = true;
                //转换开始日期和结束日期
                getStartAndEndOfMonth();
                if (mPresenter != null) {
                    mPresenter.getProblemList(reservoirId, "", startDate, endDate, String.valueOf(currentPage), String.valueOf(pageSize), "", false);
                }
            }
        }
    }

    public void initRequestResponse() {
        ReservoirBean defaultReservoir = UserManager.getInstance().getDefaultReservoir();
        String defaultReservoirId = defaultReservoir.getReservoirId();
        if (defaultReservoirId != reservoirId) {
            reservoirId = defaultReservoir.getReservoirId();
            tvReservoir.setText(defaultReservoir.getReservoir());
            rvAdapter.setEnableLoadMore(false);
            currentPage = 1;
            isloadmore = false;
            first = true;
            tvStartDate.setText("");
            if (mPresenter != null) {
                mPresenter.getProblemList(reservoirId, "", startDate, endDate, String.valueOf(currentPage), String.valueOf(pageSize), "", false);
            }
        }
    }

    private void getStartAndEndOfMonth() {
        String date = tvStartDate.getText().toString();
        String[] split = date.split("-");
        if (split.length == 2) {
            int dayOfMonth = getDayOfMonth(Integer.valueOf(split[0]), Integer.valueOf(split[1]));
            startDate = date + "-01 00:00:00";
            endDate = date + "-" + dayOfMonth + " 23:59:59";
        }
    }

    private int getDayOfMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.set(year, month, 0); //输入类型为int类型
        int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
//        LogUtil.i("一个月的天数:"+dayOfMonth);
        return dayOfMonth;
    }

    private void showSelectReservoir() {
        if (localReservoirList != null) {
            String[] stringItems = new String[localReservoirList.size()];
            for (int i = 0; i < localReservoirList.size(); i++) {
                stringItems[i] = localReservoirList.get(i).getReservoir();
            }
            final ActionSheetDialog dialog = new ActionSheetDialog(getBaseActivity(), stringItems, null);
            dialog.title("请选择水库")
                    .titleTextSize_SP(14.5f)
                    .widthScale(0.8f)
                    .show();
            dialog.setOnOpenItemClickL(new OnOpenItemClick() {
                @Override
                public void onOpenItemClick(AdapterView<?> parent, View view, int position, long id) {
                    spinnerPosition = position;
//                    mBinding.tvReservoir.setText(selectedResrvoir.getReservoir());
//                    selectFinish(selectedYunWeiType, selectedResrvoir);
//                    mBinding.loHeader.tvReservoirName.setText(selectedResrvoir.getReservoir());
                    ReservoirBean selectedResrvoir = localReservoirList.get(position);
                    reservoirId = localReservoirList.get(position).getReservoirId();
                    com.tepia.main.model.user.UserManager.getInstance().saveDefaultReservoir(selectedResrvoir);
                    tvReservoir.setText(stringItems[position]);
                    dialog.dismiss();
                }
            });
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                //添加计划返回
                if (data != null) {
                    Bundle extras = data.getExtras();
                    if (extras != null) {
                        boolean isSubmit = extras.getBoolean("isSubmit");
//                    LogUtil.i("isAddPlan:"+isAddPlan);
                        if (isSubmit) {
                            search();
                        }
                    }
                }
                break;
            case ChoiceReservoirActivity.resultCode:
                if (null != data) {
                    boolean isSelectAll = data.getBooleanExtra(ChoiceReservoirActivity.isAllReservoir, false);
                    boolean isKeyBack = data.getBooleanExtra(ChoiceReservoirActivity.isKeyBack,false);
//                    intent.putExtra("reservoirId",reservoirId);
//                    intent.putExtra("reservoir",reservoir);
                    String backReservoirId = data.getStringExtra("reservoirId");
                    String reservoir = data.getStringExtra("reservoir");
                    if (isKeyBack){
                        return;
                    }
                    if (isSelectAll) {
                        tvReservoir.setText("全部");
                        this.reservoirId = "";
                        return;
                    }
                    if (!reservoirId.equals(backReservoirId)) {
                        tvReservoir.setText(reservoir);
                        reservoirId = backReservoirId;
                    }
                }
//                ReservoirBean defaultReservoir = UserManager.getInstance().getDefaultReservoir();
//                if (!reservoirId.equals(defaultReservoir.getReservoirId())) {
//                    tvReservoir.setText(defaultReservoir.getReservoir());
//                    reservoirId = defaultReservoir.getReservoirId();
//                }
                break;
            default:
                break;
        }
    }
}
