package com.tepia.main.view.mainworker.homepage;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.just.library.AgentWeb;
import com.just.library.AgentWebSettings;
import com.just.library.ChromeClientCallbackManager;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.main.R;
import com.tepia.main.databinding.ActivityRealtimeWaterlevelBinding;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2018-10-16
 * Time            :       11:39
 * Version         :       1.0
 * 功能描述        :       实时水位库容曲线
 **/
@Route(path = AppRoutePath.app_RealTime_WaterLevel_StorageCapacity)
public class RealTimeWaterLevelStorageCapacityActivity extends BaseActivity{
    private ActivityRealtimeWaterlevelBinding mBinding;
    private String url;
    private ChromeClientCallbackManager.ReceivedTitleCallback mCallback;

    @Override
    public int getLayoutId() {
        return R.layout.activity_realtime_waterlevel;
    }

    @Override
    public void initView() {
        mBinding = DataBindingUtil.bind(mRootView);

        setCenterTitle("实时水位库容");
        showBack();
    }

    @Override
    public void initData() {
        url = getIntent().getStringExtra("laodUrl");
//        url = "\"https://www.jd.com/\"";
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {
        AgentWeb.with(this)//传入Activity
                .setAgentWebParent(mBinding.web, new LinearLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams
                .useDefaultIndicator()// 使用默认进度条
                .defaultProgressBarColor() // 使用默认进度条颜色
                .setReceivedTitleCallback(mCallback) //设置 Web 页面的 title 回调
                .setSecutityType(AgentWeb.SecurityType.strict)
                .setAgentWebSettings(new AgentWebSettings() {
                    @Override
                    public AgentWebSettings toSetting(WebView web) {

                        //支持javascript
                        web.getSettings().setJavaScriptEnabled(true);
                        // 设置可以支持缩放
                        web.getSettings().setSupportZoom(true);
                        // 设置出现缩放工具
                        web.getSettings().setBuiltInZoomControls(true);
                        //扩大比例的缩放
                        web.getSettings().setUseWideViewPort(true);
                        //自适应屏幕
                        web.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
                        web.getSettings().setLoadWithOverviewMode(true);
                        web.getSettings().setDisplayZoomControls(false);
                        return null;
                    }

                    @Override
                    public WebSettings getWebSettings() {
                        return null;
                    }
                })
                .createAgentWeb()//
                .ready()
                .go(url);
    }
}
