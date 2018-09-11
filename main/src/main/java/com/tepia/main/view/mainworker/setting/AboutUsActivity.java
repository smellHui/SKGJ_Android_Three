package com.tepia.main.view.mainworker.setting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.tepia.base.mvp.BaseActivity;
import com.tepia.base.utils.NetUtil;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.utils.Utils;
import com.tepia.main.APPCostant;
import com.tepia.main.R;

/**
 * 关务我们
 * @author ly on 2018/7/17
 */
public class AboutUsActivity extends BaseActivity {
    private ProgressBar progressBar1;
    private WebView wView;
    @Override
    public int getLayoutId() {
        return R.layout.activity_about_us;
    }

    @Override
    public void initView() {
        setCenterTitle(getString(R.string.setting_v_about));
        showBack();
        progressBar1 = findViewById(R.id.progressBar1);
        wView = findViewById(R.id.share_wv);
        setView();
    }

    private void setView(){
        if(!NetUtil.isNetworkConnected(Utils.getContext())){
            ToastUtils.shortToast(R.string.no_network);
        }
        WebSettings wSet = wView.getSettings();
        wSet.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        wSet.setJavaScriptEnabled(true);
        // 关闭缓存
        wSet.setAppCacheEnabled(false);
        //设置 缓存模式 LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
        wSet.setCacheMode(WebSettings.LOAD_NO_CACHE);
        wSet.setLoadWithOverviewMode(true);
        wSet.setUseWideViewPort(true);
        //设定支持缩放
        wSet.setSupportZoom(true);
        // 设置出现缩放工具
        wSet.setBuiltInZoomControls(true);
        wSet.setDisplayZoomControls(false);
        wView.loadUrl(APPCostant.WEB_COMPANY);
        wView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if(newProgress==100){
                    //加载完网页进度条消失
                    progressBar1.setVisibility(View.GONE);
                }
                else{
                    //开始加载网页时显示进度条
                    progressBar1.setVisibility(View.VISIBLE);
                    //设置进度值
                    progressBar1.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
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
