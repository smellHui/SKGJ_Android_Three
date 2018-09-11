package com.tepia.main.view.main.question.problemlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.main.R;
import com.tepia.main.model.question.AllproblemBean;
import com.tepia.main.utils.EmptyLayoutUtil;
import com.tepia.main.view.main.question.QuestionContract;
import com.tepia.main.view.main.question.QuestionPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * 问题列表
 *
 * @author ly
 * @date 2018/7/30
 */
@Route(path = AppRoutePath.app_question_list)
public class ProblemListActivity extends MVPBaseActivity<QuestionContract.View, QuestionPresenter> implements QuestionContract.View<AllproblemBean> {
    public static String key_problem = "key_problem";
    private RecyclerView baseinfoRecV;
    private SwipeRefreshLayout srl;

    private AdapterProblemList adapterPatrolLoggerList;
    private List<AllproblemBean.DataBean.ListBean> dataList = new ArrayList<>();

    private int pageSize = 10;
    private int currentPage = 1;
    private int mCurrentCounter = 0;
    private boolean first;
    private boolean isloadmore;
//    private boolean isrefresh;//first

    @Override
    public int getLayoutId() {
        return R.layout.problem_list_layout;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCenterTitle("事件日志列表");
        showBack();
        baseinfoRecV = findViewById(R.id.problemlistRy);
        srl = findViewById(R.id.srl);
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srl.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        search(false);
                        srl.setRefreshing(false);
                    }
                }, 1000);
            }
        });
        initListView();
        search(true);
    }

    @Override
    public void initView() {

    }


    private void initListView() {
        baseinfoRecV.setLayoutManager(new LinearLayoutManager(getContext()));

        adapterPatrolLoggerList = new AdapterProblemList(getContext(), R.layout.lv_task_list_item_problemlist, dataList);
        adapterPatrolLoggerList.openLoadAnimation();
        baseinfoRecV.setAdapter(adapterPatrolLoggerList);
        adapterPatrolLoggerList.setOnItemClickListener((adapter, view, position) -> {
            Intent intent = new Intent(ProblemListActivity.this, ProblemDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString(key_problem, dataList.get(position).getProblemId());
            intent.putExtras(bundle);
            startActivity(intent);

        });
        adapterPatrolLoggerList.setOnLoadMoreListener(() -> {
            baseinfoRecV.postDelayed(() -> {
                currentPage++;
                first = false;
                isloadmore = true;
                mPresenter.listAllProblem("", "", "", String.valueOf(currentPage), String.valueOf(pageSize), false);


            }, 1000);
        }, baseinfoRecV);
    }

    private void search(boolean isshowloadiing) {
        adapterPatrolLoggerList.setEnableLoadMore(false);
        dataList.clear();
        adapterPatrolLoggerList.notifyDataSetChanged();
        currentPage = 1;
        first = true;
        isloadmore = false;
        if (mPresenter != null) {
            mPresenter.listAllProblem("", "", "", String.valueOf(currentPage), String.valueOf(pageSize), isshowloadiing);
        }
    }

    @Override
    public void initData() {

    }


    @Override
    protected void initListener() {
    }


    @Override
    protected void initRequestData() {
    }

    @Override
    public void success(AllproblemBean allproblemBean) {
        AllproblemBean.DataBean dataBean = allproblemBean.getData();
        List<AllproblemBean.DataBean.ListBean> data = dataBean.getList();
        int totalSize = dataBean.getTotal();
        if (first) {
            //首次加载
            if (data == null || data.size() == 0) {
//                        showEmptyView();
                adapterPatrolLoggerList.setEmptyView(EmptyLayoutUtil.show(getString(R.string.empty_tv)));
            } else {
                dataList.clear();
                //首次加载
                dataList.addAll(data);
                adapterPatrolLoggerList.notifyDataSetChanged();
            }
            adapterPatrolLoggerList.setEnableLoadMore(true);
        } else if (isloadmore) {
            adapterPatrolLoggerList.addData(data);
            mCurrentCounter = adapterPatrolLoggerList.getData().size();
            if (mCurrentCounter >= totalSize) {
                //数据全部加载完毕
                adapterPatrolLoggerList.loadMoreEnd();
                return;
            }
            adapterPatrolLoggerList.loadMoreComplete();
        }
    }

    @Override
    public void failure(String msg) {
        adapterPatrolLoggerList.setEmptyView(EmptyLayoutUtil.show(msg));


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dataList != null) {
            dataList.clear();
            dataList = null;
        }
    }
}
