package com.tepia.main.view.main.question.problemconfigure;


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
import com.tepia.main.model.question.ProblemCallListBean;
import com.tepia.main.model.question.ProblemDetailBean;
import com.tepia.main.utils.EmptyLayoutUtil;
import com.tepia.main.view.main.question.QuestionContract;
import com.tepia.main.view.main.question.QuestionPresenter;
import com.tepia.main.view.main.question.problemlist.AdapterProblemCallList;
import com.tepia.main.view.main.question.problemlist.ProblemDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 待审核问题列表页面
 * Created by      Intellij IDEA
 *
 * @author :       liying
 *         Date    :       2018-09-05
 *         Time    :       下午2:16
 *         Version :       1.0
 *         Company :       北京太比雅科技(武汉研发中心)
 **/
@Route(path = AppRoutePath.app_up_question_configure_list)
public class ProblemConfigureListActivity extends MVPBaseActivity<QuestionContract.View, QuestionPresenter> implements QuestionContract.View<ProblemCallListBean> {

    public static String key_configure = "key_configure";
    public static boolean has_success_configure = false;
    private int position_item = -1;
    private RecyclerView baseinfoRecV;
    private SwipeRefreshLayout srl;

    private AdapterProblemCallList adapterPatrolLoggerList;
    private List<ProblemDetailBean.DataBean> dataList = new ArrayList<>();

    private int pageSize = 10;
    private int currentPage = 1;
    private int mCurrentCounter = 0;
    private boolean first;
    private boolean isloadmore;
    private String reservoirName = "", problemType = "", problemTitle = "", problemCofirmType = "", problemStatus = "",
             startDate = "", endDate = "";

    @Override
    public int getLayoutId() {
        return R.layout.activity_problem_call_list;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCenterTitle("待审核事件列表");
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

    @Override
    public void initData() {

    }

    private void initListView() {
        baseinfoRecV.setLayoutManager(new LinearLayoutManager(getContext()));

        adapterPatrolLoggerList = new AdapterProblemCallList(getContext(), R.layout.lv_task_list_item_problemcall_list, dataList,1);
        adapterPatrolLoggerList.openLoadAnimation();
        baseinfoRecV.setAdapter(adapterPatrolLoggerList);
        adapterPatrolLoggerList.setOnItemClickListener((adapter, view, position) -> {
            Intent intent = new Intent(this, ProblemDetailActivity.class);
            Bundle bundle = new Bundle();
//            bundle.putString(key_configure, dataList.get(position).getProblemId());
            bundle.putSerializable(key_configure,dataList.get(position));
            position_item = position;
            intent.putExtras(bundle);
            startActivity(intent);

        });
        adapterPatrolLoggerList.setOnLoadMoreListener(() -> {
            baseinfoRecV.postDelayed(() -> {
                currentPage++;
                first = false;
                isloadmore = true;
                //reservoirName,problemCofirmType,problemStatus,problemSource,startDate,endDate,currentPage,pag
                mPresenter.getProblemExamineList(reservoirName, problemType,problemTitle,problemCofirmType, problemStatus,startDate,endDate, String.valueOf(currentPage), String.valueOf(pageSize), false);


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
            mPresenter.getProblemExamineList(reservoirName, problemType,problemTitle,problemCofirmType, problemStatus,startDate,endDate, String.valueOf(currentPage), String.valueOf(pageSize), isshowloadiing);

        }
    }




    @Override
    protected void initListener() {
    }


    @Override
    protected void initRequestData() {
        if(has_success_configure){
            has_success_configure = false;
            dataList.remove(position_item);
            position_item = -1;
            adapterPatrolLoggerList.notifyDataSetChanged();
        }
//        mPresenter.getProblemExamineList(reservoirName, problemType,problemTitle,problemCofirmType, problemStatus,startDate,endDate, String.valueOf(currentPage), String.valueOf(pageSize), false);

    }





    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dataList != null) {
            dataList.clear();
            dataList = null;
        }
    }

    @Override
    public void success(ProblemCallListBean problemCallListBean) {
        ProblemCallListBean.DataBean dataBean = problemCallListBean.getData();
        List<ProblemDetailBean.DataBean> data = dataBean.getList();
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
}
