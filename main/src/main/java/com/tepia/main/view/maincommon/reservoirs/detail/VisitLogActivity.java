package com.tepia.main.view.maincommon.reservoirs.detail;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.base.utils.DoubleClickUtil;
import com.tepia.base.utils.NetUtil;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.utils.Utils;
import com.tepia.main.R;
import com.tepia.main.databinding.ActivityVisitLogBinding;
import com.tepia.main.model.question.AllproblemBean;
import com.tepia.main.model.reserviros.VisitLogBean;
import com.tepia.main.utils.EmptyLayoutUtil;
import com.tepia.main.view.main.question.problemlist.ProblemDetailActivity;
import com.tepia.main.view.main.question.problemlist.ProblemListActivity;
import com.tepia.main.view.maincommon.reservoirs.ReservoirsFragment;
import com.tepia.main.view.maincommon.reservoirs.detailadapter.AdapterVisitLog;
import com.tepia.main.view.maincommon.reservoirs.mvpreservoir.VisitLogContract;
import com.tepia.main.view.maincommon.reservoirs.mvpreservoir.VisitLogPresenter;
import com.tepia.main.view.maincommon.setting.train.AddTrainActivity;
import com.tepia.main.view.maincommon.setting.train.TrainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by      Android studio
 *
 * @author :ly (from Center Of Wuhan)
 * Date    :
 * Version :1.0
 * 功能描述 : 到访日志
 **/
public class VisitLogActivity extends MVPBaseActivity<VisitLogContract.View, VisitLogPresenter> implements VisitLogContract.View<VisitLogBean> {
    public static String key_visit_log = "key_visit_log";

    private ActivityVisitLogBinding activityVisitLogBinding;
    private AdapterVisitLog adapterVisitLog;
    private List<VisitLogBean.DataBean.ListBean> dataList = new ArrayList<>();
    private String reservoirId;

    private int pageSize = 10;
    private int currentPage = 1;
    private int mCurrentCounter = 0;
    private boolean first;
    private boolean isloadmore;
    public static int REQUEST_DELETE_CODE = 200;


    @Override
    public int getLayoutId() {
        return R.layout.activity_visit_log;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityVisitLogBinding = DataBindingUtil.bind(mRootView);

        setCenterTitle("到访日志");
        showBack();
        getRithtTv().setVisibility(View.VISIBLE);
        getRithtTv().setTextColor(Color.GRAY);
        getRithtTv().setText("新增到访");
        getRithtTv().setTextSize(16);
        getRithtTv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                startActivityForResult(new Intent(VisitLogActivity.this, VisitLogAddActivity.class), REQUEST_DELETE_CODE);
            }
        });
        initRec();
        reservoirId = getIntent().getStringExtra(ReservoirsFragment.RESERVOIRId);
        String reservoirName = getIntent().getStringExtra(ReservoirsFragment.RESERVOIRNAME);
        TextView nameTv = findViewById(R.id.nameTv);
        nameTv.setText(reservoirName);
        search(true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_DELETE_CODE) {
            search(true);
        }
    }

    @Override
    public void initView() {

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

    /**
     * 初始化recycleview
     */
    private void initRec() {
        activityVisitLogBinding.visitlogRec.setLayoutManager(new LinearLayoutManager(this));
        adapterVisitLog = new AdapterVisitLog(this, R.layout.activity_visit_log_item, dataList);
        activityVisitLogBinding.visitlogRec.setAdapter(adapterVisitLog);
        adapterVisitLog.setOnItemClickListener((adapter, view, position) -> {
            if (!NetUtil.isNetworkConnected(Utils.getContext())) {
                ToastUtils.shortToast(R.string.no_network);
                return;
            }
            if (DoubleClickUtil.isFastDoubleClick()) {
                return;
            }

            Intent intent = new Intent(VisitLogActivity.this, VisitLogDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString(key_visit_log, dataList.get(position).getId());
            intent.putExtras(bundle);
            startActivity(intent);

        });
        adapterVisitLog.setOnLoadMoreListener(() -> {
            activityVisitLogBinding.visitlogRec.postDelayed(() -> {
                currentPage++;
                first = false;
                isloadmore = true;
                mPresenter.getPageList(reservoirId, String.valueOf(currentPage), String.valueOf(pageSize), false);


            }, 1000);
        }, activityVisitLogBinding.visitlogRec);
    }

    private void search(boolean isshowloadiing) {
        adapterVisitLog.setEnableLoadMore(false);
        dataList.clear();
        adapterVisitLog.notifyDataSetChanged();
        currentPage = 1;
        first = true;
        isloadmore = false;
        if (mPresenter != null) {
            mPresenter.getPageList(reservoirId, String.valueOf(currentPage), String.valueOf(pageSize), isshowloadiing);

        }
    }

    @Override
    public void success(VisitLogBean visitLogBean) {
        VisitLogBean.DataBean dataBean = visitLogBean.getData();

        List<VisitLogBean.DataBean.ListBean> data = dataBean.getList();
        int totalSize = dataBean.getTotal();
        if (first) {
            //首次加载
            if (data == null || data.size() == 0) {
//                        showEmptyView();
                adapterVisitLog.setEmptyView(EmptyLayoutUtil.show(getString(R.string.empty_tv)));
            } else {
                dataList.clear();
                //首次加载
                dataList.addAll(data);
                adapterVisitLog.notifyDataSetChanged();
            }
            adapterVisitLog.setEnableLoadMore(true);
        } else if (isloadmore) {

            adapterVisitLog.addData(data);
            mCurrentCounter = adapterVisitLog.getData().size();
            if (mCurrentCounter >= totalSize) {
                //数据全部加载完毕
                adapterVisitLog.loadMoreEnd();
                return;
            }
            adapterVisitLog.loadMoreComplete();

        }
    }

    @Override
    public void failure(String msg) {
        adapterVisitLog.setEmptyView(EmptyLayoutUtil.show(msg));

    }
}
