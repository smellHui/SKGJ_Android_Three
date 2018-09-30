package com.tepia.main.view.maincommon.setting.worknotification.worknotificationdetail.leader;


import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.main.R;
import com.tepia.main.databinding.ActivityWorkNotificationDetailBinding;
import com.tepia.main.model.worknotification.WorkNoticeBean;


/**
 * @author :      zhang xinhua
 * Version         :       1.0
 * 功能描述        :
 **/
@Route(path = AppRoutePath.app_work_notification_leader_detail)
public class WorkNotificationDetailActivity extends MVPBaseActivity<WorkNotificationDetailContract.View, WorkNotificationDetailPresenter> implements WorkNotificationDetailContract.View {

    private ActivityWorkNotificationDetailBinding mBinding;
    private AdapterFeedBackList adapterFeedBackList;
    private String noticeId;

    @Override
    public int getLayoutId() {
        return R.layout.activity_work_notification_detail;
    }

    @Override
    public void initView() {
        setCenterTitle("工作通知详情");
        showBack();
        mBinding = DataBindingUtil.bind(mRootView);
        initListView();
    }

    private void initListView() {
        mBinding.rvFeedBackList.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterFeedBackList = new AdapterFeedBackList(R.layout.lv_feed_back_item, null);
        mBinding.rvFeedBackList.setAdapter(adapterFeedBackList);
    }

    @Override
    public void initData() {

        noticeId = getIntent().getStringExtra("noticeId");
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {

        mPresenter.getWorkNoticeDetail(noticeId);
    }

    @Override
    public void getWorkNoticeDetailSuccess(WorkNoticeBean data) {
        mBinding.tvTitle.setText("通知标题:" + data.getNoticeTitle());
        mBinding.tvDesc.setText("通知内容:" + data.getNoticeContent());
        mBinding.tvPeople.setText("通知发布人:" + data.getUserName());
        mBinding.tvTime.setText("通知时间:" + data.getCreateDate());
        mBinding.tvTime.setVisibility(View.GONE);
        mBinding.tvStatus.setVisibility(View.GONE);
        adapterFeedBackList.setNewData(data.getFeedbackList());
    }
}
