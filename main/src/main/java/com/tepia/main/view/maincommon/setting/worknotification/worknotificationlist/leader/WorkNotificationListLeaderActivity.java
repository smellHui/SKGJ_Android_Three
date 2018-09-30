package com.tepia.main.view.maincommon.setting.worknotification.worknotificationlist.leader;


import android.databinding.DataBindingUtil;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.main.R;
import com.tepia.main.databinding.ActivityWorkNotificationListBinding;
import com.tepia.main.model.worknotification.WorkNoticeBean;


import java.util.List;


/**
 * @author :      zhang xinhua
 * Version         :       1.0
 * 功能描述        : 工作通知页面 县领导
 **/
@Route(path = AppRoutePath.app_work_notification_leader)
public class WorkNotificationListLeaderActivity extends MVPBaseActivity<WorkNotificationListContract.View, WorkNotificationListPresenter> implements WorkNotificationListContract.View {
    ActivityWorkNotificationListBinding mBinding;
    private AdapterWorkNotificationList adapterWorkNotificationList;

    @Override
    public int getLayoutId() {
        return R.layout.activity_work_notification_list;
    }

    @Override
    public void initView() {
        setCenterTitle("工作通知");
        showBack();
        mBinding = DataBindingUtil.bind(mRootView);
        initListView();
    }

    private void initListView() {
        mBinding.rvWorkNotificationList.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterWorkNotificationList = new AdapterWorkNotificationList(R.layout.lv_work_notification_item, null);
        mBinding.rvWorkNotificationList.setAdapter(adapterWorkNotificationList);
    }

    @Override
    public void initData() {

    }

    @Override
    protected void initListener() {
        adapterWorkNotificationList.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ARouter.getInstance().build(AppRoutePath.app_work_notification_leader_detail)
                        .withString("noticeId",adapterWorkNotificationList.getData().get(position).getId())
                        .navigation();
            }
        });
        mBinding.srflContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mBinding.srflContainer.setRefreshing(false);
            }
        });
    }

    @Override
    protected void initRequestData() {
        mPresenter.getWorkNoticeList();
    }

    @Override
    public void getWorkNoticeListSuccess(List<WorkNoticeBean> list) {
        adapterWorkNotificationList.setNewData(list);
    }
}
