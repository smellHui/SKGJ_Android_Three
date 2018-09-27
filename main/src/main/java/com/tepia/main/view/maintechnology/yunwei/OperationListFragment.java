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

import com.jzxiang.pickerview.data.Type;
import com.tepia.base.mvp.MVPBaseFragment;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.TimeFormatUtils;
import com.tepia.base.utils.ToastUtils;
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
 * Created by      Intellij IDEA
 * 运维列表
 *
 * @author :       wwj
 * Date    :       2018-09-18
 * Time    :       10:31
 * Version :       1.0
 * Company :       北京太比雅科技(武汉研发中心)
 **/
public class OperationListFragment extends MVPBaseFragment<YunWeiJiShuContract.View, YunWeiJiShuPresenter> {
    private String[] tabNames = {"巡检", "保洁", "维修养护", "上报"};
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
    private String typeName;
    private TextView tvComplete;
    private TextView tvPercent;
    private ArrayList<ReservoirBean> localReservoirList;
    private YunWeiJiShuPresenter yunWeiJiShuPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_operation_list;
    }

    @Override
    protected void initData() {

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
            LogUtil.i("保洁");
            typeName = "保洁";
        } else if (tabNames[2].equals(typeStr)) {
            operationType = "3";
            typeName = "养护";
        }
        tvOperationTask = findView(R.id.tv_operation_task);
        tvComplete = findView(R.id.tv_complete);
        tvPercent = findView(R.id.tv_percent);
        spinner = findView(R.id.operation_spinner);
        rv = findView(R.id.rv_operation_list);
        tvStartDate = findView(R.id.tv_start_date);
        initStartDate();
        initSpinner();
        initRecyclerView();
        initSearch();
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
//            LogUtil.i("查询时间:"+startDate);
            rvAdapter.setEnableLoadMore(false);
            currentPage = 1;
            isloadmore = false;
            first = true;
            if (mPresenter != null) {
                mPresenter.getNoProcessWorkOrderList(reservoirId, operationType, startDate, "", String.valueOf(currentPage), String.valueOf(pageSize), true);
            }
            yunWeiJiShuPresenter.getWorkOrderNumByJs(reservoirId,operationType,startDate);
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
        tvStartDate.setText(TimeFormatUtils.dateToStrLong(lastDate));
        tvStartDate.setOnClickListener(v -> {
            String current = (String) tvStartDate.getText();
            long currentLong = 0;
            currentLong = strToLong(current);
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
            timePickerDialogUtil.builder.setTitleStringId(getString(R.string.starttimeTitle));
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
    }

    private void loadDataOrMore(boolean isShowLoading) {
        if (mPresenter != null) {
            mPresenter.getNoProcessWorkOrderList(reservoirId, operationType, startDate, "", String.valueOf(currentPage), String.valueOf(pageSize), isShowLoading);
        }
    }

    //    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initSpinner() {
        localReservoirList = UserManager.getInstance().getLocalReservoirList();
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
        mPresenter.attachView(new YunWeiJiShuContract.View<WorkOrderListResponse>() {
            @Override
            public void success(WorkOrderListResponse workOrderListResponse) {
//                LogUtil.i("个数" + workOrderListResponse.getCode());
                WorkOrderListResponse.DataBean dataBean = workOrderListResponse.getData();
                List<WorkOrderListResponse.DataBean.ListBean> data = dataBean.getList();
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
        if (!isFirstLoad) {
            rvAdapter.setEnableLoadMore(false);
            currentPage = 1;
            isloadmore = false;
            first = true;
            if (mPresenter != null&&yunWeiJiShuPresenter!=null) {
                mPresenter.getNoProcessWorkOrderList(reservoirId, operationType, "", "", String.valueOf(currentPage), String.valueOf(pageSize), false);
                yunWeiJiShuPresenter.getWorkOrderNumByJs("",operationType,"");
            }
        }

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
}
