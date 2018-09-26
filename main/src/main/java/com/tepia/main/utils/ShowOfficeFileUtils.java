package com.tepia.main.utils;

import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.tepia.base.utils.ToastUtils;

/**
 * Created by      android studio
 *
 * @author :       ly
 * Date            :       2018-09-26
 * Time            :       下午3:42
 * Version         :       1.0
 * location        :       武汉研发中心
 * 功能描述         :       office文件在线显示
 **/
public class ShowOfficeFileUtils {

    public static void showOffice(WebView webview,String downloadUrl){
        boolean contains_office = downloadUrl.endsWith(".doc")
                || downloadUrl.endsWith(".ppt")
                || downloadUrl.endsWith(".xls")
                || downloadUrl.endsWith(".docx")
                || downloadUrl.endsWith(".pptx")
                || downloadUrl.endsWith(".xlsx");
        if(contains_office) {
            String Office_url = "https://view.officeapps.live.com/op/view.aspx?src=".concat(downloadUrl);
            webview.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    return false;
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                }
            });
            webview.getSettings().setJavaScriptEnabled(true);
            webview.loadUrl(Office_url);
        }else{
            ToastUtils.shortToast("非标准的word,excel或ppt文件，无法显示");
        }
    }
}
