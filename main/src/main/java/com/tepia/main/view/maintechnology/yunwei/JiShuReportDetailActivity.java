package com.tepia.main.view.maintechnology.yunwei;

import android.content.Context;
import android.widget.TextView;

import com.tepia.base.mvp.BaseActivity;
import com.tepia.base.utils.ToastUtils;
import com.tepia.main.R;
import com.tepia.main.model.jishu.yunwei.JiShuRePortDetailResponse;
import com.tepia.main.model.jishu.yunwei.OperationReportListResponse;
import com.tepia.main.view.maintechnology.yunwei.presenter.YunWeiJiShuContract;
import com.tepia.main.view.maintechnology.yunwei.presenter.YunWeiJiShuPresenter;

/**
 * Created by      Intellij IDEA
 *
 * @author :       wwj
 * Date    :       2018-09-26
 * Time    :       09:20
 * Version :       1.0
 * Company :       北京太比雅科技(武汉研发中心)
 **/
public class JiShuReportDetailActivity extends BaseActivity {

    private TextView tvTitle;
    private TextView tvName;
    private TextView tvTime;
    private TextView tvDescribe;
    private YunWeiJiShuPresenter mPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_jishu_report_detail;
    }

    @Override
    public void initView() {
        setCenterTitle("上报详情");
        showBack();
        tvTitle = findViewById(R.id.tv_title);
        tvName = findViewById(R.id.tv_name);
        tvTime = findViewById(R.id.tv_time);
        tvDescribe = findViewById(R.id.tv_describe);
        mPresenter = new YunWeiJiShuPresenter();
        OperationReportListResponse.DataBean.ListBean item = (OperationReportListResponse.DataBean.ListBean) getIntent().getSerializableExtra("item");
        if (item!=null){
          initDetail(item);
        }
    }

    private void initDetail(OperationReportListResponse.DataBean.ListBean item) {
        String problemId = item.getProblemId();
        mPresenter.attachView(new YunWeiJiShuContract.View<JiShuRePortDetailResponse>() {
            @Override
            public void success(JiShuRePortDetailResponse jiShuRePortDetailResponse) {
                JiShuRePortDetailResponse.DataBean data = jiShuRePortDetailResponse.getData();
                initDetails(data);
            }

            @Override
            public void failure(String msg) {
                ToastUtils.shortToast(msg);
            }

            @Override
            public Context getContext() {
                return null;
            }
        });
        mPresenter.getDetailedProblemInfoByProblemId(problemId);
    }

    private void initDetails(JiShuRePortDetailResponse.DataBean data) {
        tvTitle.setText(data.getProblemTitle());
        String reportName = "上报人:"+data.getProblemSourceUserName();
        tvName.setText(reportName);
        String reportTime = "上报时间:"+data.getCreateDate();
        tvTime.setText(reportTime);
        tvDescribe.setText(data.getProblemDescription());
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
}
