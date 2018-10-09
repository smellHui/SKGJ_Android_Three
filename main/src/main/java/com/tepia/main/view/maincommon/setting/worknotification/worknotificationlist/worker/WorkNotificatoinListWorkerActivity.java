package com.tepia.main.view.maincommon.setting.worknotification.worknotificationlist.worker;


import android.databinding.DataBindingUtil;
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
import com.tepia.main.model.worknotification.FeedBackWorkNoticeBean;
import com.tepia.main.model.worknotification.FeedBackWorkNoticeListResponse;


import java.util.List;


/**
 * @author :      zhang xinhua
 * Version         :       1.0
 * 功能描述        :工作通知页面 工作人员
 **/
@Route(path = AppRoutePath.app_work_notification_worker)
public class WorkNotificatoinListWorkerActivity extends MVPBaseActivity<WorkNotificatoinListWorkerContract.View, WorkNotificatoinListWorkerPresenter> implements WorkNotificatoinListWorkerContract.View {

    private ActivityWorkNotificationListBinding mBinding;
    private AdapterWorkNotificationWorkerList adapterWorkNotificationWorkerList;

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
        adapterWorkNotificationWorkerList = new AdapterWorkNotificationWorkerList(R.layout.lv_work_notification_item_worker, null);
        mBinding.rvWorkNotificationList.setAdapter(adapterWorkNotificationWorkerList);
    }

    @Override
    public void initData() {

    }

    @Override
    protected void initListener() {
        adapterWorkNotificationWorkerList.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                ARouter.getInstance().build(AppRoutePath.app_work_notification_worker_detail)
                        .withString("noticeFeedbackId", adapterWorkNotificationWorkerList.getData().get(position).getId())
                        .withString("noticeId", adapterWorkNotificationWorkerList.getData().get(position).getBizWorkNotice().getId())
                        .navigation();
            }
        });
        adapterWorkNotificationWorkerList.openLoadAnimation();
        adapterWorkNotificationWorkerList.setEnableLoadMore(true);
        adapterWorkNotificationWorkerList.setLoadMoreView(new SimpleLoadMoreView());
        adapterWorkNotificationWorkerList.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mBinding.rvWorkNotificationList.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mPresenter.isCanLoadMore) {
                            mPresenter.getWorkNoticeListMore();

                        } else {
                            adapterWorkNotificationWorkerList.loadMoreEnd();
                        }
                    }
                }, 1000);
            }
        },mBinding.rvWorkNotificationList);
        mBinding.srflContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mBinding.srflContainer.setRefreshing(false);
                mPresenter.getWorkNoticeListMore();
            }
        });
    }

    @Override
    protected void initRequestData() {
        mPresenter.getWorkNoticeWorkerList();
    }

    @Override
    public void getWorkNoticeWorkerListSuccess(List<FeedBackWorkNoticeBean> list) {
        adapterWorkNotificationWorkerList.setNewData(list);
        if (list == null || list.size() == 0) {
            adapterWorkNotificationWorkerList.getData().clear();
            adapterWorkNotificationWorkerList.notifyDataSetChanged();
            View view = LayoutInflater.from(Utils.getContext()).inflate(R.layout.view_empty_list_view, null);
            adapterWorkNotificationWorkerList.setEmptyView(view);
        }
    }

    @Override
    public void getWorkNoticeWorkerListMoreSuccess(FeedBackWorkNoticeListResponse.DataBean dataBean) {
        int i = dataBean.getStartRow() - 1;
        for (int j = 0; j < dataBean.getList().size(); j++) {
            if (!adapterWorkNotificationWorkerList.getData().contains(dataBean.getList().get(j))) {
                adapterWorkNotificationWorkerList.addData(i + j, dataBean.getList().get(j));
            } else {
                adapterWorkNotificationWorkerList.setData(i + j, dataBean.getList().get(j));
            }
        }
        adapterWorkNotificationWorkerList.loadMoreComplete();
    }
}
