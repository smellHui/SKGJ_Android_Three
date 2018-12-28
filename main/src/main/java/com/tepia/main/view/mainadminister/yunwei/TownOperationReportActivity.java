package com.tepia.main.view.mainadminister.yunwei;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.tepia.base.mvp.BaseActivity;
import com.tepia.main.R;
import com.tepia.main.model.jishu.admin.AdminWorkOrderResponse;
import com.tepia.main.model.jishu.admin.ProblemListByAddvcdResponse;
import com.tepia.main.utils.EmptyLayoutUtil;
import com.tepia.main.view.mainadminister.yunwei.adapter.TownOperationReportAdapter;
import com.tepia.main.view.maintechnology.yunwei.presenter.YunWeiJiShuContract;
import com.tepia.main.view.maintechnology.yunwei.presenter.YunWeiJiShuPresenter;

import java.util.ArrayList;
import java.util.List;

/**
  * Created by      Android studio
  *
  * @author :wwj (from Center Of Wuhan)
  * Date    :2018/12/28
  * Version :1.0
  * 功能描述 :  乡镇对应的上报列表
 **/

public class TownOperationReportActivity extends BaseActivity {
    private String areaCode;//乡镇编码
    private String queryDate;
    private String townName;
    private RecyclerView rv;
    private SwipeRefreshLayout srl;
    private List<ProblemListByAddvcdResponse.DataBean> dataList = new ArrayList<>();
    private TownOperationReportAdapter rvAdapter;
    private YunWeiJiShuPresenter mPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_town_operation;
    }

    public static Intent setIntent(Intent intent, String areaCode, String queryDate, String townName) {
        intent.putExtra("areaCode", areaCode);
        intent.putExtra("queryDate", queryDate);
        intent.putExtra("townName", townName);
        return intent;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        setCenterTitle("上报问题列表");
        showBack();
        TextView tvTitleDone = findViewById(R.id.tv_title_done);
        tvTitleDone.setText("处理数");
        TextView tvTitleTotals = findViewById(R.id.tv_title_totals);
        tvTitleTotals.setText("上报数");
        TextView tvTitleStatus = findViewById(R.id.tv_title_status);
        tvTitleStatus.setText("处理率");
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
        rvAdapter = new TownOperationReportAdapter(R.layout.admin_operation_list_item, dataList);
        rv.setAdapter(rvAdapter);
        srl.setOnRefreshListener(() -> {
            if (mPresenter != null) {
                mPresenter.getProblemListByAddvcd(areaCode, queryDate, false);
            }
        });
        rvAdapter.setOnItemClickListener((adapter, view, position) -> {
            Intent bundle = new Intent(TownOperationReportActivity.this, AdminOperationReportActivity.class);
            //AdminWorkOrderResponse.DataBean.ListBean
            AdminWorkOrderResponse.DataBean.ListBean listBean = new AdminWorkOrderResponse.DataBean.ListBean();
            ProblemListByAddvcdResponse.DataBean dataBean = dataList.get(position);
            listBean.setReservoirId(dataBean.getReservoirId());
            listBean.setReservoirName(dataBean.getReservoirName());
            listBean.setDate(dataBean.getDate());
            bundle.putExtra("item", listBean);
            startActivity(bundle);
        });
    }

    private void initRequestResponse() {
        if (mPresenter != null) {
            mPresenter.getProblemListByAddvcd(areaCode, queryDate, true);
        }
    }

    @Override
    public void initData() {
        mPresenter = new YunWeiJiShuPresenter();
        mPresenter.attachView(new YunWeiJiShuContract.View<ProblemListByAddvcdResponse>() {
            @Override
            public void success(ProblemListByAddvcdResponse data) {
                List<ProblemListByAddvcdResponse.DataBean> list = data.getData();
                if (null != list && list.size() > 0) {
                        dataList.clear();
                        dataList.addAll(list);
                        rvAdapter.notifyDataSetChanged();
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
