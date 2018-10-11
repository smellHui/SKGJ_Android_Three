package com.tepia.main.view.mainworker.homepage;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.just.library.AgentWeb;
import com.just.library.AgentWebView;
import com.just.library.ChromeClientCallbackManager;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.main.R;
import com.tepia.main.databinding.ActivityReservorVrBinding;
import com.tepia.main.databinding.ActivitySelectReservorsBinding;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2018-10-11
 * Time            :       18:17
 * Version         :       1.0
 * 功能描述        :
 **/
@Route(path = AppRoutePath.app_reservoir_vr)
public class ReservoirVrActivity extends BaseActivity {
    private WebView webVr;
    private String vrUrl;
    private ActivityReservorVrBinding mBinding;
    private ChromeClientCallbackManager.ReceivedTitleCallback mCallback;

    @Override
    public int getLayoutId() {
        return R.layout.activity_reservor_vr;
    }

    @Override
    public void initView() {
        mBinding = DataBindingUtil.bind(mRootView);
    }

    @Override
    public void initData() {
        vrUrl = getIntent().getStringExtra("vrUrl");
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {
//        mBinding.webVr.loadUrl(vrUrl);
        AgentWeb.with(this)//传入Activity
                .setAgentWebParent(mBinding.webVr, new LinearLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams
                .useDefaultIndicator()// 使用默认进度条
                .defaultProgressBarColor() // 使用默认进度条颜色
                .setReceivedTitleCallback(mCallback) //设置 Web 页面的 title 回调
                .createAgentWeb()//
                .ready()
                .go(vrUrl);

    }
}
