package com.tepia.main.view.maincommon.setting.worknotification.worknotificationlist.leader;


import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.loadmore.SimpleLoadMoreView;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.base.utils.DoubleClickUtil;
import com.tepia.base.utils.Utils;
import com.tepia.main.R;
import com.tepia.main.databinding.ActivityWorkNotificationListBinding;
import com.tepia.main.model.detai.ReservoirBean;
import com.tepia.main.model.worknotification.WorkNoticeBean;
import com.tepia.main.model.worknotification.WorkNoticeListResponse;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

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
        getRithtTv().setVisibility(View.VISIBLE);
        getRithtTv().setTextColor(Color.GRAY);
        getRithtTv().setText("发布通知");
        getRithtTv().setTextSize(16);
        mBinding = DataBindingUtil.bind(mRootView);
        initListView();
        EventBus.getDefault().register(this);
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
                mPresenter.getWorkNoticeList();
            }
        });
        adapterWorkNotificationList.openLoadAnimation();
        adapterWorkNotificationList.setEnableLoadMore(true);
        adapterWorkNotificationList.setLoadMoreView(new SimpleLoadMoreView());
        adapterWorkNotificationList.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mBinding.rvWorkNotificationList.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mPresenter.isCanLoadMore) {
                            mPresenter.getWorkNoticeListMore();

                        } else {
                            adapterWorkNotificationList.loadMoreEnd();
                        }
                    }
                }, 1000);
            }
        },mBinding.rvWorkNotificationList);
        getRithtTv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DoubleClickUtil.isFastDoubleClick()){
                    return;
                }
                ARouter.getInstance().build(AppRoutePath.app_add_work_notification).navigation();
            }
        });
    }

    @Subscribe
    public void getEventBus(Integer num) {
        if (num != null && num == 500) {
            mPresenter.getWorkNoticeList();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.getWorkNoticeList();
    }

    @Override
    protected void initRequestData() {

    }

    @Override
    public void getWorkNoticeListSuccess(List<WorkNoticeBean> list) {
        adapterWorkNotificationList.setNewData(list);
        if (list == null || list.size() == 0) {
            adapterWorkNotificationList.getData().clear();
            adapterWorkNotificationList.notifyDataSetChanged();
            View view = LayoutInflater.from(Utils.getContext()).inflate(R.layout.view_empty_list_view, null);
            adapterWorkNotificationList.setEmptyView(view);
        }
    }

    @Override
    public void getWorkNoticeListSuccessMore(WorkNoticeListResponse.DataBean dataBean) {
        int i = dataBean.getStartRow() - 1;
        for (int j = 0; j < dataBean.getList().size(); j++) {
            if (!adapterWorkNotificationList.getData().contains(dataBean.getList().get(j))) {
                adapterWorkNotificationList.addData(i + j, dataBean.getList().get(j));
            } else {
                adapterWorkNotificationList.setData(i + j, dataBean.getList().get(j));
            }
        }
        adapterWorkNotificationList.loadMoreComplete();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
