package com.tepia.main.view.maincommon.setting.worknotification.worknotificationdetail.worker;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.base.utils.ToastUtils;
import com.tepia.main.R;
import com.tepia.main.databinding.ActivityWorkNotificationDetailBinding;
import com.tepia.main.model.image.ImageInfoBean;
import com.tepia.main.model.worknotification.WorkNoticeBean;
import com.tepia.main.view.maincommon.reservoirs.detail.OperationPlanActivity;
import com.tepia.main.view.maincommon.setting.worknotification.addworknotification.MyFIleListAdapter;
import com.tepia.main.view.maincommon.setting.worknotification.worknotificationdetail.leader.AdapterFeedBackList;
import com.tepia.main.view.maincommon.setting.worknotification.worknotificationdetail.leader.WorkNotificationDetailActivity;
import com.tepia.main.view.mainworker.report.adapter.ImageShowAdapter;
import com.tepia.photo_picker.PhotoPreview;

import java.util.ArrayList;
import java.util.List;


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


    private List<ImageInfoBean> filesBeanList = new ArrayList<>();
    private MyFIleListAdapter myFIleListAdapter;
    private ArrayList<String> images = new ArrayList<>();
    private ImageShowAdapter imageShowAdapter;

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
        initRecycle();
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
                if (TextUtils.isEmpty(mBinding.rtFeedback.getText().toString())) {
                    ToastUtils.shortToast("请输入反馈内容");
                } else {
                    mPresenter.feedBackWorkNotice(noticeFeedbackId, mBinding.rtFeedback.getText().toString());
                }
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
        mBinding.tvPeople.setText("" + data.getUserName());
        mBinding.tvTime.setText("" + data.getCreateDate());
        mBinding.tvReservoir.setVisibility(View.GONE);
        mBinding.loStatus.setVisibility(View.VISIBLE);
        adapterFeedBackList.setNewData(data.getFeedbackList());
        if (data.getFeedBackStatus() != null && data.getFeedBackStatus().equals("0")) {
            mBinding.loFeedBack.setVisibility(View.VISIBLE);
            mBinding.tvStatus.setText("未反馈");
        } else {
            mBinding.loFeedBack.setVisibility(View.GONE);
            mBinding.tvStatus.setText("已反馈");
        }
        refreshView(data);
    }

    @Override
    public void feedBackWorkNoticeSuccess() {
        mBinding.loFeedBack.setVisibility(View.GONE);
        mPresenter.getWorkNoticeDetail(noticeFeedbackId, noticeId);
    }

    private void initRecycle() {
        //创建LinearLayoutManager
        LinearLayoutManager manager = new LinearLayoutManager(this);
        //设置为横向滑动
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //设置
        mBinding.peixunRec.setLayoutManager(manager);
        imageShowAdapter = new ImageShowAdapter(this, R.layout.activity_pei_xun_item, images);
        mBinding.peixunRec.setAdapter(imageShowAdapter);
        //设置附件详情
        mBinding.rvFilePick.setLayoutManager(new LinearLayoutManager(this));
        myFIleListAdapter = new MyFIleListAdapter(R.layout.common_office_layout, filesBeanList);
        mBinding.rvFilePick.setAdapter(myFIleListAdapter);
        myFIleListAdapter.setOnPreviewTvClickListener((v, adapterPosition, item) -> {
            Intent intent = new Intent();
            intent.setClass(WorkNotificationDetailWorkerActivity.this, OperationPlanActivity.class);
            intent.putExtra("select", OperationPlanActivity.value_preview);
            intent.putExtra(OperationPlanActivity.PREVIEW_PATH, item.getFilePath());
            startActivity(intent);
        });
    }

    public void refreshView(WorkNoticeBean data) {
        //刷新图片
        List<ImageInfoBean> imageFiles = data.getImages();
        if (imageFiles != null && imageFiles.size() > 0) {
            images.clear();
            for (int i = 0; i < imageFiles.size(); i++) {
                images.add(imageFiles.get(i).getFilePath());
            }
            imageShowAdapter.notifyDataSetChanged();
            imageShowAdapter.setOnItemClickListener((adapter, view, position) -> PhotoPreview.builder()
                    .setPhotos(images)
                    .setShowDeleteButton(false)
                    .setCurrentItem(position)
                    .start(WorkNotificationDetailWorkerActivity.this));
        }
        //刷新附件
        List<ImageInfoBean> files1 = data.getFiles();
        if (files1 != null && files1.size() > 0) {
            filesBeanList.clear();
            for (int i = 0; i < files1.size(); i++) {
                filesBeanList.add(files1.get(i));
            }
            myFIleListAdapter.notifyDataSetChanged();
        } else {

        }
    }
}
