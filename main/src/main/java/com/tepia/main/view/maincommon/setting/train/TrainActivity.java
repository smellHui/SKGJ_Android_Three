package com.tepia.main.view.maincommon.setting.train;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.base.utils.DoubleClickUtil;
import com.tepia.main.R;
import com.tepia.main.model.train.TrainContract;
import com.tepia.main.model.train.TrainListResponse;
import com.tepia.main.model.train.TrainPresenter;
import com.tepia.main.utils.EmptyLayoutUtil;
import com.tepia.main.view.main.jihua.AddPlanActivity;
import com.tepia.main.view.maincommon.setting.adapter.AdapterTrainDetail;
import com.tepia.main.view.maincommon.setting.adapter.TrainListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by      Android studio
 *
 * @author :ly (from Center Of Wuhan)
 * Date    :2018-9-19
 * Version :1.0
 * 功能描述 :工作培训
 **/
public class TrainActivity extends BaseActivity {

    private List<Integer> images = new ArrayList<>();

    private AdapterTrainDetail adapterPeixunDetail;
    private RecyclerView peixunRec;
    private RecyclerView rvTrainList;
    private List<TrainListResponse.DataBean.ListBean> dataList = new ArrayList<>();
    private TrainListAdapter trainListAdapter;
    private SwipeRefreshLayout srl;
    private int pageSize = 10;
    private int currentPage = 1;
    private int mCurrentCounter = 0;
    private boolean first;
    private boolean isloadmore;
    private TrainPresenter mPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_train_list;
    }

    @Override
    public void initView() {
        setCenterTitle("我的培训");
        showBack();
        getRithtTv().setVisibility(View.VISIBLE);
        getRithtTv().setTextColor(Color.GRAY);
        getRithtTv().setText("新增");
        getRithtTv().setTextSize(16);
        getRithtTv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DoubleClickUtil.isFastDoubleClick()){
                    return;
                }
                startActivityForResult(new Intent(TrainActivity.this, AddTrainActivity.class), 1);
            }
        });
     /*     peixunRec = findViewById(R.id.peixunRec);
        images.add(R.drawable.jianjie_banner0);
        images.add(R.drawable.jianjie_banner1);
        images.add(R.drawable.jianjie_banner2);
        images.add(R.drawable.jianjie_banner3);
        images.add(R.drawable.jianjie_banner0);
        images.add(R.drawable.jianjie_banner1);
        images.add(R.drawable.jianjie_banner2);
        images.add(R.drawable.jianjie_banner3);
        images.add(R.drawable.jianjie_banner0);
        images.add(R.drawable.jianjie_banner1);
        images.add(R.drawable.jianjie_banner2);
        images.add(R.drawable.jianjie_banner3);
        images.add(R.drawable.jianjie_banner0);
        images.add(R.drawable.jianjie_banner1);
        images.add(R.drawable.jianjie_banner2);
        images.add(R.drawable.jianjie_banner3);

        //创建LinearLayoutManager
        LinearLayoutManager manager = new LinearLayoutManager(this);
        //设置为横向滑动
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //设置
        peixunRec.setLayoutManager(manager);
        adapterPeixunDetail = new AdapterTrainDetail(this,R.layout.activity_pei_xun_item,images);
        peixunRec.setAdapter(adapterPeixunDetail);
*/
        srl = findViewById(R.id.srl);
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(view1 -> startActivityForResult(new Intent(TrainActivity.this, AddTrainActivity.class), 1));
        initSwipeRefreshLayout();
        initRecycleView();
    }

    private void initRecycleView() {
//        for (int i = 0; i < 10; i++) {
//            dataList.add(new TrainListResponse.DataBean.ListBean());
//        }
        rvTrainList = findViewById(R.id.rv_train_list);
        rvTrainList.setLayoutManager(new LinearLayoutManager(this));
        trainListAdapter = new TrainListAdapter(R.layout.train_list_item, dataList);
        trainListAdapter.openLoadAnimation();
        rvTrainList.setAdapter(trainListAdapter);
        trainListAdapter.setOnLoadMoreListener(() -> {
            rvTrainList.postDelayed(() -> {
                currentPage++;
                first = false;
                isloadmore = true;
                //加载更多数据
                loadDataOrMore();
            },1000);
        },rvTrainList);
        trainListAdapter.setOnItemClickListener((adapter, view, position) -> {
            Intent bundle = new Intent(this,TrainDetailActivity.class);
            bundle.putExtra("item",dataList.get(position));
            startActivity(bundle);
        });
    }

    private void loadDataOrMore() {
        mPresenter.getTrainList(String.valueOf(currentPage),String.valueOf(pageSize));
    }

    private void initSwipeRefreshLayout() {
        /*srl.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light);*/
        srl.setOnRefreshListener(() -> {
            srl.postDelayed(() -> {
                search();
//                srl.setRefreshing(false);
            }, 500);
        });
        srl.setRefreshing(true);
        srl.postDelayed(new Runnable() {
            @Override
            public void run() {
                search();
            }
        },500);
    }

    private void search() {
            trainListAdapter.setEnableLoadMore(false);
            currentPage = 1;
            first = true;
            isloadmore = false;
            loadDataOrMore();
    }

    @Override
    public void initData() {
        mPresenter = new TrainPresenter();
        mPresenter.attachView(new TrainContract.View<TrainListResponse>() {
            @Override
            public void success(TrainListResponse trainListResponse) {
                TrainListResponse.DataBean dataBean = trainListResponse.getData();
                List<TrainListResponse.DataBean.ListBean> data = dataBean.getList();
                int totalSize = dataBean.getTotal();
                int pages = dataBean.getPages();
                if (first){
                    //首次加载
                    if (data==null||data.size()==0){
                        dataList.clear();
                        trainListAdapter.setEmptyView(EmptyLayoutUtil.show(getString(R.string.empty_tv)));
                        trainListAdapter.notifyDataSetChanged();
                    }else {
                        dataList.clear();
                        dataList.addAll(data);
                        trainListAdapter.notifyDataSetChanged();
                        first = false;
                    }
                    trainListAdapter.setEnableLoadMore(true);
                    srl.setRefreshing(false);
                    if (pages==1){
                        //只有一页
                        trainListAdapter.loadMoreEnd();
                        return;
                    }
                }else if (isloadmore){
                    dataList.addAll(data);
                    trainListAdapter.notifyDataSetChanged();
                    if (mCurrentCounter >= totalSize) {
                        //数据全部加载完毕
                        trainListAdapter.loadMoreEnd();
                        return;
                    }
                    trainListAdapter.loadMoreComplete();
                }
            }

            @Override
            public void failure(String msg) {
                if (first){
                    srl.setRefreshing(false);
                }
                trainListAdapter.setEmptyView(EmptyLayoutUtil.show(msg));
            }

            @Override
            public Context getContext() {
                return null;
            }
        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case 1:
                //新增培训返回
                Bundle extras = data.getExtras();
                if (null!=extras){
                    boolean isAddPlan =extras.getBoolean("isAddPlan");
//                    LogUtil.i("isAddPlan:"+isAddPlan);
                    if (isAddPlan){
                        search();
                    }
                }
        }
    }
}
