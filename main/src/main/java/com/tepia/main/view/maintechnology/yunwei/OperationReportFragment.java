package com.tepia.main.view.maintechnology.yunwei;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.jzxiang.pickerview.data.Type;
import com.tepia.base.mvp.MVPBaseFragment;
import com.tepia.base.utils.TimeFormatUtils;
import com.tepia.base.utils.ToastUtils;
import com.tepia.main.R;
import com.tepia.main.model.detai.ReservoirBean;
import com.tepia.main.model.jishu.yunwei.OperationReportListResponse;
import com.tepia.main.model.user.UserManager;
import com.tepia.main.utils.EmptyLayoutUtil;
import com.tepia.main.utils.TimePickerDialogUtil;
import com.tepia.main.view.maintechnology.yunwei.adapter.MyOperationReportListAdapter;
import com.tepia.main.view.maintechnology.yunwei.presenter.YunWeiJiShuContract;
import com.tepia.main.view.maintechnology.yunwei.presenter.YunWeiJiShuPresenter;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by      Intellij IDEA
 * 运维上报列表
 *
 * @author :       wwj
 * Date    :       2018-09-25
 * Time    :       18:35
 * Version :       1.0
 * Company :       北京太比雅科技(武汉研发中心)
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

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_operation_list;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        LinearLayout llListTitle = findView(R.id.ll_list_title);
        llListTitle.setVisibility(View.GONE);
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
            rvAdapter.setEnableLoadMore(false);
            currentPage = 1;
            isloadmore = false;
            first = true;
            if (mPresenter!=null){
                if (mPresenter != null) {
                    mPresenter.getProblemList(reservoirId,"",startDate,"",String.valueOf(currentPage),String.valueOf(pageSize),"",true);
                }
            }
        });
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
            },1000);
        },rv);
    }

    private void loadDataOrMore(boolean isShowLoading) {
        if (mPresenter != null) {
            mPresenter.getProblemList(reservoirId,"",startDate,"",String.valueOf(currentPage),String.valueOf(pageSize),"",isShowLoading);
        }
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


    public static long strToLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
                    rvAdapter.setEmptyView(EmptyLayoutUtil.show(msg));
                }
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
            if (mPresenter != null) {
                mPresenter.getProblemList(reservoirId,"","","",String.valueOf(currentPage),String.valueOf(pageSize),"",false);
            }
        }
    }
}
