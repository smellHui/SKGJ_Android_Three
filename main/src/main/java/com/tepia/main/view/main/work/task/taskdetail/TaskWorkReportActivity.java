package com.tepia.main.view.main.work.task.taskdetail;

import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.base.view.web.AgentWeb;
import com.tepia.main.R;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2018-12-04
 * Time            :       14:25
 * Version         :       1.0
 * 功能描述        : 工单报告
 **/
@Route(path = AppRoutePath.app_task_work_report)
public class TaskWorkReportActivity extends BaseActivity {
    private String workReportUrl;
    private WebView wvPdfContainer;

    @Override
    public int getLayoutId() {
        return R.layout.activity_task_work_report;
    }

    @Override
    public void initView() {
        setCenterTitle("工单报告");
        showBack();
        wvPdfContainer = findViewById(R.id.wv_pdf_container);
    }

    @Override
    public void initData() {
        workReportUrl = getIntent().getStringExtra("workReportUrl");
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {
        WebSettings webSettings = wvPdfContainer.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        wvPdfContainer.loadUrl("http://docs.google.com/gview?embedded=true&;url=" + workReportUrl);
//        com.just.library.AgentWeb.with(this)//传入Activity
//                .setAgentWebParent(wvPdfContainer, new LinearLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams
//                .useDefaultIndicator()// 使用默认进度条
//                .defaultProgressBarColor() // 使用默认进度条颜色
//
//                .createAgentWeb()//
//                .ready()
//                .go(workReportUrl);

    }
}
