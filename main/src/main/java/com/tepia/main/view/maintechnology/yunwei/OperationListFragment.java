package com.tepia.main.view.maintechnology.yunwei;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jzxiang.pickerview.data.Type;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.MVPBaseFragment;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.NetUtil;
import com.tepia.base.utils.TimeFormatUtils;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.utils.Utils;
import com.tepia.base.view.dialog.basedailog.ActionSheetDialog;
import com.tepia.base.view.dialog.basedailog.OnOpenItemClick;
import com.tepia.main.R;
import com.tepia.main.model.detai.ReservoirBean;
import com.tepia.main.model.jishu.yunwei.WorkOrderListResponse;
import com.tepia.main.model.jishu.yunwei.WorkOrderNumResponse;
import com.tepia.main.model.user.UserManager;
import com.tepia.main.utils.EmptyLayoutUtil;
import com.tepia.main.utils.TimePickerDialogUtil;
import com.tepia.main.view.maintechnology.yunwei.adapter.MyOperationListAdapter;
import com.tepia.main.view.maintechnology.yunwei.presenter.YunWeiJiShuContract;
import com.tepia.main.view.maintechnology.yunwei.presenter.YunWeiJiShuPresenter;
import com.tepia.main.view.mainworker.yunwei.xunjian.YunWeiXunJianFragment;

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
  * 功能描述 : 技术责任人 运维("巡检", "维修养护", "保洁")
 **/

public class OperationListFragment extends MVPBaseFragment<YunWeiJiShuContract.View, YunWeiJiShuPresenter> {
    private String[] tabNames = {"巡检", "维修养护", "保洁", "上报"};
    private ArrayAdapter spinnerAdapter;
    private TextView tvOperationTask;
    private Spinner spinner;
    private String[] reservoirs;
    private int spinnerPosition;
    private RecyclerView rv;
    private String typeStr;
    private String operationType;
    private TextView tvStartDate;
    private List<WorkOrderListResponse.DataBean.ListBean> dataList = new ArrayList<>();
    private MyOperationListAdapter rvAdapter;
    private int pageSize = 10;
    private int currentPage = 1;
    private boolean isloadmore;
    private boolean first;
    private int mCurrentCounter = 0;
    private String reservoirId;
    private String startDate;
    private String endDate;
    private String typeName;
    private TextView tvComplete;
    private TextView tvPercent;
    private ArrayList<ReservoirBean> localReservoirList;
    private YunWeiJiShuPresenter yunWeiJiShuPresenter;
    private ReservoirBean selectedResrvoir;
    private TextView tvReservoir;
    private TextView tvEndDate;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_operation_list;
    }

    @Override
    protected void initData() {
        localReservoirList = UserManager.getInstance().getLocalReservoirList();
        mPresenter.attachView(new YunWeiJiShuContract.View<WorkOrderListResponse>() {
            @Override
            public void success(WorkOrderListResponse workOrderListResponse) {
//                LogUtil.i("个数" + workOrderListResponse.getCode());
                WorkOrderListResponse.DataBean dataBean = workOrderListResponse.getData();
                List<WorkOrderListResponse.DataBean.ListBean> data = dataBean.getList();
                int totalSize = dataBean.getTotal();
                int pages = dataBean.getPages();
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
                    if (pages==1){
                        //只有一页
                        rvAdapter.loadMoreEnd();
                        return;
                    }
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
                    rvAdapter.setEmptyView(EmptyLayoutUtil.show(msg));
                }
            }

            @Override
            public Context getContext() {
                return null;
            }
        });
        yunWeiJiShuPresenter = new YunWeiJiShuPresenter();
        yunWeiJiShuPresenter.attachView(new YunWeiJiShuContract.View<WorkOrderNumResponse>() {
            @Override
            public void success(WorkOrderNumResponse workOrderNumResponse) {
                int doneNum = workOrderNumResponse.getData().getDoneNum();
                int totals = workOrderNumResponse.getData().getTotals();
                setSpanned(doneNum,totals);
            }

            @Override
            public void failure(String msg) {
                setSpanned(0,0);
            }

            @Override
            public Context getContext() {
                return null;
            }
        });
    }

    @Override
    protected void initView(View view) {
        typeStr = getArguments().getString("type");
//        LogUtil.i("typeStr:" + typeStr);
        if (tabNames[3].equals(typeStr)) {
            operationType = "";
        } else if (tabNames[0].equals(typeStr)) {
            operationType = "1";
            typeName = "巡检";
        } else if (tabNames[1].equals(typeStr)) {
            operationType = "2";
            typeName = "维护";
        } else if (tabNames[2].equals(typeStr)) {
            operationType = "3";
            typeName = "保洁";
        }
        tvOperationTask = findView(R.id.tv_operation_task);
        tvComplete = findView(R.id.tv_complete);
        tvPercent = findView(R.id.tv_percent);
//        spinner = findView(R.id.operation_spinner);
        rv = findView(R.id.rv_operation_list);
        tvStartDate = findView(R.id.tv_start_date);
        tvEndDate = findView(R.id.tv_end_date);
        tvReservoir = findView(R.id.tv_reservoir);
        tvReservoir.setOnClickListener(v -> showSelectReservoir());
        initStartDate();
        initEndDate();
//        initSpinner();
        initRecyclerView();
        initSearch();
        initRequestResponse();
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
            if (current!=null&&current.length()>0){
                currentLong = strToLong(current);
            }
            timePickerDialogUtil.initTimePickerSetStartAndEnd((timePickerView, millseconds) -> {
                String text = timePickerDialogUtil.getDateToString(millseconds);
                tvEndDate.setText(text);
            }, Type.ALL, System.currentTimeMillis() - fiveYears, System.currentTimeMillis() + fiveYears, R.color.color_load_blue);
            if (timePickerDialogUtil.startDialog != null) {
                timePickerDialogUtil.startDialog = null;
            }
            if (currentLong != 0) {
                timePickerDialogUtil.builder.setCurrentMillseconds(currentLong);
            }
            timePickerDialogUtil.builder.setTitleStringId("请选择结束时间");
            timePickerDialogUtil.startDialog = timePickerDialogUtil.builder.build();
            timePickerDialogUtil.startDialog.show(getFragmentManager(), "all");
        });
    }

    private void initRequestResponse() {

        if (!isFirstLoad) {
            rvAdapter.setEnableLoadMore(false);
            currentPage = 1;
            isloadmore = false;
            first = true;
            if (mPresenter != null&&yunWeiJiShuPresenter!=null) {
                mPresenter.getNoProcessWorkOrderList(reservoirId, operationType, "", "", String.valueOf(currentPage), String.valueOf(pageSize), true);
                yunWeiJiShuPresenter.getWorkOrderNumByJs("",operationType,"");
            }
        }
    }

    private void initSearch() {
        Button btConfirm = findView(R.id.bt_confirm);
        btConfirm.setOnClickListener(v -> {
            dataList.clear();
            if (spinnerPosition==0){
                reservoirId = "";
            }else {
                reservoirId = localReservoirList.get(spinnerPosition-1).getReservoirId();
            }
            startDate = tvStartDate.getText().toString();
            endDate = tvEndDate.getText().toString();
//            LogUtil.i("查询时间:"+startDate);
            rvAdapter.setEnableLoadMore(false);
            currentPage = 1;
            isloadmore = false;
            first = true;
            if(!NetUtil.isNetworkConnected(Utils.getContext())){
                ToastUtils.shortToast(R.string.no_network);
            }else {
                if (mPresenter != null) {
                    mPresenter.getNoProcessWorkOrderList(reservoirId, operationType, startDate, endDate, String.valueOf(currentPage), String.valueOf(pageSize), true);
                }
                yunWeiJiShuPresenter.getWorkOrderNumByJs(reservoirId,operationType,startDate);
            }
        });
    }

    private TimePickerDialogUtil timePickerDialogUtil;
    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

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
            if (current!=null&&current.length()>0){
                currentLong = strToLong(current);
            }
            timePickerDialogUtil.initTimePickerSetStartAndEnd((timePickerView, millseconds) -> {
                String text = timePickerDialogUtil.getDateToString(millseconds);
                tvStartDate.setText(text);
            }, Type.ALL, System.currentTimeMillis() - fiveYears, System.currentTimeMillis() + fiveYears, R.color.color_load_blue);
            if (timePickerDialogUtil.startDialog != null) {
                timePickerDialogUtil.startDialog = null;
            }
            if (currentLong != 0) {
                timePickerDialogUtil.builder.setCurrentMillseconds(currentLong);
            }
            timePickerDialogUtil.builder.setTitleStringId("请选择开始时间");
            timePickerDialogUtil.startDialog = timePickerDialogUtil.builder.build();
            timePickerDialogUtil.startDialog.show(getFragmentManager(), "all");
        });
    }

    public static long strToLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        //继续转换得到秒数的long型
        long lTime = strtodate.getTime();
        return lTime;
    }

    private void initRecyclerView() {
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvAdapter = new MyOperationListAdapter(R.layout.operation_list_item, dataList);
        rvAdapter.openLoadAnimation();
        rv.setAdapter(rvAdapter);
        rvAdapter.setOnLoadMoreListener(() -> {
            rv.postDelayed(() -> {
                currentPage++;
                first = false;
                isloadmore = true;
                //加载更多数据
                loadDataOrMore(false);
            },1000);
        },rv);
        rvAdapter.setOnItemClickListener((adapter, view, position) -> {
            LogUtil.i("position:"+position);
            ARouter.getInstance().build(AppRoutePath.app_task_detail)
                    .withString("workOrderId", dataList.get(position).getWorkOrderId())
                    .navigation();
        });
    }

    private void loadDataOrMore(boolean isShowLoading) {
        if (mPresenter != null) {
            mPresenter.getNoProcessWorkOrderList(reservoirId, operationType, startDate, "", String.valueOf(currentPage), String.valueOf(pageSize), isShowLoading);
        }
    }

    //    @RequiresApi(api = Build.VERSION_CODES.N)
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

    boolean isFirstLoad;

    @Override
    protected void initRequestData() {

    }

    private void setSpanned(int doneNum,int totals){
        int percent=0;
        if (doneNum!=0&&totals!=0){
            percent = doneNum*100/totals;
        }
        String str=typeName+"任务:<font color='#e3654d'>"+totals+"次</font>";
        String totalStr="完成"+typeName+":<font color='#e3654d'>"+doneNum+"次</font>";
        String percentStr = "完成率:<font color='#e3654d'>"+percent+"%</font>";
        Spanned result;
        Spanned complete;
        Spanned percentSd;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(str, Html.FROM_HTML_MODE_LEGACY);
            complete = Html.fromHtml(totalStr, Html.FROM_HTML_MODE_LEGACY);
            percentSd= Html.fromHtml(percentStr, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(str);
            complete = Html.fromHtml(totalStr);
            percentSd = Html.fromHtml(percentStr);
        }
        tvOperationTask.setText(result);
        tvComplete.setText(complete);
        tvPercent.setText(percentSd);
    }

    private void showSelectReservoir() {
        if (localReservoirList != null) {
            String[] stringItems = new String[localReservoirList.size()+1];
            for (int i = 0; i < localReservoirList.size(); i++) {
                stringItems[i+1] = localReservoirList.get(i).getReservoir();
            }
            stringItems[0] = "全部";
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
                    com.tepia.main.model.user.UserManager.getInstance().saveDefaultReservoir(selectedResrvoir);
                    tvReservoir.setText(stringItems[position]);
                    dialog.dismiss();
                }
            });
        }
    }
}
