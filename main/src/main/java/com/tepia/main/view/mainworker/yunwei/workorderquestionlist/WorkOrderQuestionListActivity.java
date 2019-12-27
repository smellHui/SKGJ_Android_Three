package com.tepia.main.view.mainworker.yunwei.workorderquestionlist;


import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.main.ConfigConsts;
import com.tepia.main.R;
import com.tepia.main.databinding.ActivityWorkOrderQuestionListBinding;
import com.tepia.main.model.report.EmergenceListBean;
import com.tepia.main.utils.EmptyLayoutUtil;
import com.tepia.main.view.mainworker.report.EmergenceShowDetailActivity;
import com.tepia.main.view.mainworker.report.Wrap.FeedbackEvent;
import com.tepia.main.view.mainworker.report.adapter.AdapterEmergency;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * @author :      zhang xinhua
 * Version         :       1.0
 * 功能描述        :       工单产生的问题 列表
 **/
@Route(path = AppRoutePath.app_work_order_question_list)
public class WorkOrderQuestionListActivity extends MVPBaseActivity<WorkOrderQuestionListContract.View, WorkOrderQuestionListPresenter> implements WorkOrderQuestionListContract.View {

    private String workOrderId;
    private ActivityWorkOrderQuestionListBinding mBinding;
    private AdapterEmergency adapterShuiweiReservoirs;

    @Override
    public int getLayoutId() {
        return R.layout.activity_work_order_question_list;
    }

    @Override
    public void initView() {
        setCenterTitle("工单问题列表");
        showBack();
        mBinding = DataBindingUtil.bind(mRootView);
        initListView();
    }

    private void initListView() {
        mBinding.yingjiRec.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterShuiweiReservoirs = new AdapterEmergency(getContext(),R.layout.fragment_shuiwei_yingji_head_layout,null);
        mBinding.yingjiRec.setAdapter(adapterShuiweiReservoirs);
        adapterShuiweiReservoirs.setOnItemClickListener((adapter, view, position) -> {
            Intent intent = new Intent();
            intent.setClass(getContext(),EmergenceShowDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString(ConfigConsts.emergence,adapterShuiweiReservoirs.getData().get(position).getProblemId());
            bundle.putString("problemStatus", adapterShuiweiReservoirs.getData().get(position).getProblemStatus());
            intent.putExtras(bundle);
            startActivity(intent);


        });
    }

    @Override
    public void initData() {
        EventBus.getDefault().register(this);
        workOrderId = getIntent().getStringExtra("workOrderId");
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {
        mPresenter.getProblemList(workOrderId);
    }

    @Override
    public void getProblemListSuccess(EmergenceListBean waterLevelResponse) {
       adapterShuiweiReservoirs.setNewData(waterLevelResponse.getData().getList());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void feedBackEvent(FeedbackEvent feedbackEvent){
        initRequestData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
