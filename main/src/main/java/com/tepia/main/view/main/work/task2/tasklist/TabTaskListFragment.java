package com.tepia.main.view.main.work.task2.tasklist;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.loadmore.SimpleLoadMoreView;
import com.google.gson.Gson;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.MVPBaseFragment;
import com.tepia.base.utils.DoubleClickUtil;
import com.tepia.base.utils.ResUtils;
import com.tepia.base.utils.Utils;
import com.tepia.main.R;
import com.tepia.main.model.task.bean.TaskBean;
import com.tepia.main.view.main.work.task.tasklist.AdapterTaskList;
import com.tepia.main.view.main.work.task.tasklist.TabTaskListContract;
import com.tepia.main.view.main.work.task.tasklist.TabTaskListPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体的任务列表
 *
 * @author update on 2018/7/12 by ly
 */


public class TabTaskListFragment extends MVPBaseFragment<TabTaskListContract.View, TabTaskListPresenter> implements TabTaskListContract.View {
    private int taskStatus;

    private RecyclerView rvTaskList;

    private AdapterTaskList adapterTaskList;
    private List<TaskBean> datalist = new ArrayList<>();
    private SwipeRefreshLayout srl;
    private String type = "";
    private final static String keyArgu = "taskStatus";
    private final static String keyType = "keyType";
    public int total = 0;

    public TabTaskListFragment() {
        super();
    }

    public static final TabTaskListFragment newInstance(int taskStatus, String type) {
        TabTaskListFragment f = new TabTaskListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(keyArgu, taskStatus);
        bundle.putString(keyType, type);
        f.setArguments(bundle);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.taskStatus = getArguments().getInt(keyArgu);
        this.type = getArguments().getString(keyType);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tab_task_list;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {

        rvTaskList = view.findViewById(R.id.rv_task_list);
        srl = view.findViewById(R.id.srl);
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srl.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPresenter.getTaskList(taskStatus, type, false, "");
                        srl.setRefreshing(false);
                    }
                }, 1000);
            }
        });
        initListView();
    }

    @Override
    protected void initRequestData() {
        if (datalist != null) {
            mPresenter.getTaskList(taskStatus, type, true, getString(R.string.data_loading));
        } else {
            mPresenter.getTaskList(taskStatus, type, true, getString(R.string.data_loading));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.updateTaskList(taskStatus, type, datalist.size(), false, getString(R.string.data_loading));
        }
    }

    @Override
    public void getTaskListSuccess(int total, List<TaskBean> data) {
        adapterTaskList.setNewData(data);
        this.total = total;
        if (data == null || data.size() == 0) {
            adapterTaskList.getData().clear();
            adapterTaskList.notifyDataSetChanged();
            View view = LayoutInflater.from(Utils.getContext()).inflate(R.layout.view_empty_list_view, null);
            adapterTaskList.setEmptyView(view);
        }
        datalist = adapterTaskList.getData();
    }

    @Override
    public void getTaskListMoreSuccess(int i, List<TaskBean> data) {
        adapterTaskList.loadMoreComplete();
        for (int j = 0; j < data.size(); j++) {
            if (!adapterTaskList.getData().contains(data.get(j))) {
                adapterTaskList.addData(i + j, data.get(j));
            } else {
                adapterTaskList.setData(i + j, data.get(j));
            }
        }
        datalist = adapterTaskList.getData();
    }

    @Override
    public void getTaskListMoreFail() {
        adapterTaskList.loadMoreComplete();
    }

    @Override
    public void updateTaskListSuccess(List<TaskBean> data) {
        adapterTaskList.loadMoreComplete();
        for (int j = 0; j < data.size(); j++) {
            if (!adapterTaskList.getData().contains(data.get(j))) {
                adapterTaskList.addData(j, data.get(j));
            } else {
                adapterTaskList.setData(j, data.get(j));
            }
        }
        datalist = adapterTaskList.getData();
    }

    @Override
    public void deleteWorkSuccess(String workOrderId) {
        adapterTaskList.remove(adapterTaskList.getItemPositoin(workOrderId));
    }

    private void initListView() {
        rvTaskList.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterTaskList = new AdapterTaskList(getContext(), R.layout.lv_task_list_item, new ArrayList<TaskBean>());
        rvTaskList.setAdapter(adapterTaskList);
        rvTaskList.setNestedScrollingEnabled(false);
        adapterTaskList.setLoadMoreView(new SimpleLoadMoreView());
        adapterTaskList.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                ARouter.getInstance().build(AppRoutePath.app_task_detail_2)
                        .withString("workOrderId", adapterTaskList.getData().get(position).getWorkOrderId())
                        .withString("taskBean", new Gson().toJson(adapterTaskList.getData().get(position)))
                        .navigation();
            }
        });
        adapterTaskList.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                if (adapterTaskList.getData().get(position).getExecuteStatus().equals("0")) {
                    new AlertDialog.Builder(getBaseActivity())
                            .setMessage("确定要删除这条工单吗？")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    mPresenter.deleteWork(adapterTaskList.getData().get(position).getWorkOrderId());
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            }).show();
                }
                return false;
            }
        });
        adapterTaskList.openLoadAnimation();
        adapterTaskList.setEnableLoadMore(true);
        adapterTaskList.setLoadMoreView(new SimpleLoadMoreView());
        adapterTaskList.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                rvTaskList.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mPresenter.isCanLoadMore) {
                            mPresenter.getTaskListMore(taskStatus, type, false, "");
                        } else {
                            adapterTaskList.loadMoreEnd();
                        }
                    }
                }, 1000);
            }
        }, rvTaskList);
    }


    public void setType(String type) {
        this.type = type;
        mPresenter.getTaskList(taskStatus, type, true, ResUtils.getString(R.string.data_loading));
    }
}
