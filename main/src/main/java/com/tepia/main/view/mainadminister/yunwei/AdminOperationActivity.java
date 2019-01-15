package com.tepia.main.view.mainadminister.yunwei;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.ToastUtils;
import com.tepia.main.APPCostant;
import com.tepia.main.R;
import com.tepia.main.model.jishu.admin.AdminWorkOrderResponse;
import com.tepia.main.model.jishu.yunwei.WorkOrderListResponse;
import com.tepia.main.utils.EmptyLayoutUtil;
import com.tepia.main.view.maintechnology.yunwei.adapter.MyOperationListAdapter;
import com.tepia.main.view.maintechnology.yunwei.presenter.YunWeiJiShuContract;
import com.tepia.main.view.maintechnology.yunwei.presenter.YunWeiJiShuPresenter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by      Android studio
 *
 * @author :wwj (from Center Of Wuhan)
 * Date    :2018/10/9
 * Version :1.0
 * 功能描述 :行政运维具体月份
 **/
@Route(path = AppRoutePath.app_admin_operation)
public class AdminOperationActivity extends BaseActivity {
    private String[] tabNames = {"巡检", "维修养护", "保洁", "上报"};
    private YunWeiJiShuPresenter mPresenter;
    private int pageSize = 10;
    private int currentPage = 1;
    private boolean isloadmore;
    private boolean first;
    private int mCurrentCounter = 0;
    private String startDate;
    private String endDate;
    private List<WorkOrderListResponse.DataBean.ListBean> dataList = new ArrayList<>();
    private MyOperationListAdapter rvAdapter;
    private RecyclerView rv;
    private String reservoirId;
    private String operationType;
    private AdminWorkOrderResponse.DataBean.ListBean item;
    private SwipeRefreshLayout srl = null;
    private TextView tvReservoir;
    private TextView tvStartDate;
    private String executeStatus = "";

    @Override
    public int getLayoutId() {
        return R.layout.activity_admin_operation;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        operationType = intent.getStringExtra("operationType");
        executeStatus = intent.getStringExtra("executeStatus");
        item = (AdminWorkOrderResponse.DataBean.ListBean) intent.getSerializableExtra("item");
//        LogUtil.i(operationType +"-----"+ item.getDate());
        if ("1".equals(operationType)) {
            setCenterTitle(tabNames[0]);
        } else if ("2".equals(operationType)) {
            setCenterTitle(tabNames[1]);
        } else if ("3".equals(operationType)) {
            setCenterTitle(tabNames[2]);
        }
        showBack();
        rv = findViewById(R.id.rv_operation_list);
        srl = (SwipeRefreshLayout) findViewById(R.id.srl);
        tvReservoir = findViewById(R.id.tv_reservoir);
        tvStartDate = findViewById(R.id.tv_start_date);
        initRecyclerView();
        srl.setOnRefreshListener(() -> {
            rvAdapter.setEnableLoadMore(false);
            currentPage = 1;
            first = true;
            isloadmore = false;
            if (mPresenter != null) {
                mPresenter.getNoProcessWorkOrderList(reservoirId, operationType, startDate, endDate, String.valueOf(currentPage), String.valueOf(pageSize), executeStatus, false);
            }
        });
        /*srl.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light);*/
        initRequestResponse();
    }

    private int getDayOfMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.set(year, month, 0); //输入类型为int类型
        int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
//        LogUtil.i("一个月的天数:"+dayOfMonth);
        return dayOfMonth;
    }

    private void initRequestResponse() {
        if (item != null) {
            tvReservoir.setText(item.getReservoirName());
            tvStartDate.setText(item.getDate());
            String date = item.getDate();
            try {
                String[] split = date.split("-");
                if (split.length == 2) {
                    int dayOfMonth = getDayOfMonth(Integer.valueOf(split[0]), Integer.valueOf(split[1]));
                    startDate = date + "-01 00:00:00";
                    endDate = date + "-" + dayOfMonth + " 23:59:59";
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            reservoirId = item.getReservoirId();
            rvAdapter.setEnableLoadMore(false);
            currentPage = 1;
            isloadmore = false;
            first = true;
            if (mPresenter != null) {
                mPresenter.getNoProcessWorkOrderList(reservoirId, operationType, startDate, endDate, String.valueOf(currentPage), String.valueOf(pageSize), executeStatus, true);
            }
        }
    }

    private void initRecyclerView() {
        rv.setLayoutManager(new LinearLayoutManager(this));
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
            }, 1000);
        }, rv);
        rvAdapter.setOnItemClickListener((adapter, view, position) -> {
            LogUtil.i("position:" + position);
            ARouter.getInstance().build(AppRoutePath.app_task_detail)
                    .withString("workOrderId", dataList.get(position).getWorkOrderId())
                    .navigation();
        });
    }

    @Override
    public void initData() {
        mPresenter = new YunWeiJiShuPresenter();
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
                    srl.setRefreshing(false);
                    if (pages == 1) {
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
            }

            @Override
            public void failure(String msg) {
                ToastUtils.shortToast(msg);
                if (isloadmore) {
                    if (currentPage > 1) {
                        currentPage--;
                        rvAdapter.loadMoreFail();
                    }
                } else {
                    srl.setRefreshing(false);
                    rvAdapter.setEmptyView(EmptyLayoutUtil.show(msg));
                }
            }

            @Override
            public Context getContext() {
                return null;
            }
        });
    }

    private void loadDataOrMore(boolean isShowLoading) {
        if (mPresenter != null) {
            mPresenter.getNoProcessWorkOrderList(reservoirId, operationType, startDate, "", String.valueOf(currentPage), String.valueOf(pageSize),executeStatus, isShowLoading);
        }
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {

    }
}
