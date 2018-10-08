package com.tepia.main.view.maincommon.setting.worknotification.worknotificationdetail.worker;


import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.main.R;
import com.tepia.main.databinding.ActivityWorkNotificationDetailBinding;
import com.tepia.main.model.worknotification.WorkNoticeBean;
import com.tepia.main.view.maincommon.setting.worknotification.worknotificationdetail.leader.AdapterFeedBackList;


/**
 * @author :      zhang xinhua
 * Version         :       1.0
 * 功能描述        :
 **/
@Route(path = AppRoutePath.app_work_notification_worker_detail)
public class WorkNotificationDetailWorkerActivity extends MVPBaseActivity<WorkNotificationDetailWorkerContract.View, WorkNotificationDetailWorkerPresenter> implements WorkNotificationDetailWorkerContract.View {
    private ActivityWorkNotificationDetailBinding mBinding;
    private AdapterFeedBackList adapterFeedBackList;
    private String noticeId;
    private String noticeFeedbackId;

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
        noticeFeedbackId = getIntent().getStringExtra("noticeFeedbackId");
        noticeId = getIntent().getStringExtra("noticeId");
    }

    @Override
    protected void initListener() {
        mBinding.tvFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.feedBackWorkNotice(noticeFeedbackId,mBinding.rtFeedback.getText().toString());
            }
        });
    }

    @Override
    protected void initRequestData() {
        mPresenter.getWorkNoticeDetail(noticeFeedbackId, noticeId);
    }

    @Override
    public void getWorkNoticeDetailSuccess(WorkNoticeBean data) {
        mBinding.tvTitle.setText("" + data.getNoticeTitle());
        mBinding.tvDesc.setText("" + data.getNoticeContent());
        mBinding.tvPeople.setText("通知发布人:\t\t" + data.getUserName());
        mBinding.tvTime.setText("通知时间:\t\t\t" + data.getCreateDate());
        mBinding.tvReservoir.setVisibility(View.GONE);
        mBinding.tvStatus.setVisibility(View.GONE);
        adapterFeedBackList.setNewData(data.getFeedbackList());
        if (data.getFeedBackStatus() != null && data.getFeedBackStatus().equals("0")) {
            mBinding.loFeedBack.setVisibility(View.VISIBLE);
        }else {
            mBinding.loFeedBack.setVisibility(View.GONE);

        }
    }

    @Override
    public void feedBackWorkNoticeSuccess() {
        mBinding.loFeedBack.setVisibility(View.GONE);
        mPresenter.getWorkNoticeDetail(noticeFeedbackId,noticeId);
    }
}
