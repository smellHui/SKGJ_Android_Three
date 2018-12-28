package com.tepia.main.view.mainadminister.yunwei;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.main.R;
import com.tepia.main.model.jishu.admin.AdminWorkOrderResponse;
import com.tepia.main.model.jishu.yunwei.WorkOrderListResponse;
import com.tepia.main.model.user.UserInfoBean;
import com.tepia.main.model.user.UserManager;
import com.tepia.main.utils.EmptyLayoutUtil;
import com.tepia.main.view.mainadminister.yunwei.adapter.MyAdminOperationListAdapter;
import com.tepia.main.view.maintechnology.yunwei.adapter.MyOperationListAdapter;
import com.tepia.main.view.maintechnology.yunwei.presenter.YunWeiJiShuContract;
import com.tepia.main.view.maintechnology.yunwei.presenter.YunWeiJiShuPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by      Android studio
 *
 * @author :wwj (from Center Of Wuhan)
 * Date    :2018/12/27
 * Version :1.0
 * 功能描述 :乡镇对应水库列表
 **/

public class TownOperationActivity extends BaseActivity {
    private String[] tabNames = {"巡检", "维修养护", "保洁", "上报"};
    private YunWeiJiShuPresenter mPresenter;
    private RecyclerView rv;
    private List<AdminWorkOrderResponse.DataBean.ListBean> dataList = new ArrayList<>();
    private MyAdminOperationListAdapter rvAdapter;
    private SwipeRefreshLayout srl;
    private String operationType;
    private String areaCode;//乡镇编码
    private String queryDate;
    private String townName;

    @Override
    public int getLayoutId() {
        return R.layout.activity_town_operation;
    }

    public static Intent setIntent(Intent intent, String operationType, String areaCode,String queryDate,String townName) {
        intent.putExtra("operationType", operationType);
        intent.putExtra("areaCode", areaCode);
        intent.putExtra("queryDate", queryDate);
        intent.putExtra("townName", townName);
        return intent;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        operationType = intent.getStringExtra("operationType");
        if ("1".equals(operationType)) {
            setCenterTitle(tabNames[0]);
        } else if ("2".equals(operationType)) {
            setCenterTitle(tabNames[1]);
        } else if ("3".equals(operationType)) {
            setCenterTitle(tabNames[2]);
        }
        showBack();
        areaCode = intent.getStringExtra("areaCode");
        queryDate = intent.getStringExtra("queryDate");
        townName = intent.getStringExtra("townName");
        TextView tvTown = findViewById(R.id.tv_town);
        TextView tvStartDate = findViewById(R.id.tv_start_date);
        tvTown.setText(townName==null?"":townName);
        tvStartDate.setText(queryDate==null?"":queryDate);
        initRecyclerView();
        initRequestResponse();
    }

    private void initRecyclerView() {
        rv = findViewById(R.id.rv_operation_list);
        srl = findViewById(R.id.srl);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rvAdapter = new MyAdminOperationListAdapter(R.layout.admin_operation_list_item, dataList);
        rv.setAdapter(rvAdapter);
        srl.setOnRefreshListener(() -> {
            if (mPresenter != null) {
                mPresenter.getWorkOrderListByAreaCode(operationType, areaCode, queryDate, false);
            }
        });
        rvAdapter.setOnItemClickListener((adapter, view, position) -> {
            Intent bundle = new Intent(TownOperationActivity.this, AdminOperationActivity.class);
            bundle.putExtra("item", dataList.get(position));
            bundle.putExtra("operationType", operationType);
            startActivity(bundle);
        });
    }

    private void initRequestResponse() {
        if (mPresenter != null) {
            mPresenter.getWorkOrderListByAreaCode(operationType, areaCode, queryDate, true);
        }
    }

    @Override
    public void initData() {
        mPresenter = new YunWeiJiShuPresenter();
        mPresenter.attachView(new YunWeiJiShuContract.View<AdminWorkOrderResponse>() {
            @Override
            public void success(AdminWorkOrderResponse data) {
                AdminWorkOrderResponse.DataBean dataBean = data.getData();
                if (null != dataBean) {
                    List<AdminWorkOrderResponse.DataBean.ListBean> list = dataBean.getList();
                    if (null != list && list.size() > 0) {
                        dataList.clear();
                        dataList.addAll(list);
                        rvAdapter.notifyDataSetChanged();
                    } else {
                        dataList.clear();
                        rvAdapter.setEmptyView(EmptyLayoutUtil.show("暂无数据"));
                        rvAdapter.notifyDataSetChanged();
                    }
                } else {
                    dataList.clear();
                    rvAdapter.setEmptyView(EmptyLayoutUtil.show("暂无数据"));
                    rvAdapter.notifyDataSetChanged();
                }
                srl.setRefreshing(false);
            }

            @Override
            public void failure(String msg) {
                srl.setRefreshing(false);
                dataList.clear();
                rvAdapter.setEmptyView(EmptyLayoutUtil.show(msg));
                rvAdapter.notifyDataSetChanged();
            }

            @Override
            public Context getContext() {
                return null;
            }
        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {

    }
}
