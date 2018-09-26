package com.tepia.main.view.maincommon.reservoirs.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnErrorListener;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.base.utils.AppManager;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.view.dialog.loading.SimpleLoadDialog;
import com.tepia.main.R;
import com.tepia.main.model.reserviros.OperationPlanBean;
import com.tepia.main.utils.ShowOfficeFileUtils;
import com.tepia.main.utils.ShowPDFUtils;
import com.tepia.main.view.maincommon.reservoirs.ReservoirsFragment;
import com.tepia.main.view.maincommon.reservoirs.mvpreservoir.ReserviorContract;
import com.tepia.main.view.maincommon.reservoirs.mvpreservoir.ReserviorPresent;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by      Android studio
 *
 * @author :ly (from Center Of Wuhan)
 * Date    :2018-9-18
 * Version :1.0
 * 功能描述 :调度运行方案页面
 **/
public class OperationPlanActivity extends MVPBaseActivity<ReserviorContract.View, ReserviorPresent> implements ReserviorContract.View<OperationPlanBean> {

    private WebView webview;
    private LinearLayout rootEmptyLy;
    private PDFView pdfView;
    private TextView tv_empty_view_text;
    private static final String value_one = "1";

    @Override
    public int getLayoutId() {
        return R.layout.activity_operation;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String  selectstr = getIntent().getStringExtra("select");
        if(value_one.equals(selectstr)) {
            setCenterTitle("调度运行方案");
        }else{
            setCenterTitle("水库安全管理应急预案");
        }
        showBack();

        webview = findViewById(R.id.webview);
        rootEmptyLy = findViewById(R.id.rootEmptyLy);
        pdfView = findViewById(R.id.pdfview);
        tv_empty_view_text = findViewById(R.id.tv_empty_view_text);
        String reservoirId = getIntent().getStringExtra(ReservoirsFragment.RESERVOIRId);
        if(value_one.equals(selectstr)) {
            mPresenter.getFloodControlByReservoir(reservoirId);
        }else{
            mPresenter.getEmergencyByReservoir(reservoirId);
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



    @Override
    public void success(OperationPlanBean data) {
        String downloadUrl = data.getData().getFilePath();
//        downloadUrl = "http://tepia-skgj.oss-cn-beijing.aliyuncs.com/PC/safeReport/2018-09/18/04200170041121440060 (1).pdf";
         if (downloadUrl.endsWith(".pdf")) {
            SimpleLoadDialog simpleLoadDialog = new SimpleLoadDialog(AppManager.getInstance().getCurrentActivity(), "正在加载pdf文件", true);
            simpleLoadDialog.show();
            pdfView.setVisibility(View.VISIBLE);
            webview.setVisibility(View.GONE);
            String finalDownloadUrl = downloadUrl;

            new Thread(new Runnable() {
                @Override
                public void run() {
                     ShowPDFUtils.showPDF(pdfView,simpleLoadDialog,finalDownloadUrl);

                }
            }).start();

        } else {
            ShowOfficeFileUtils.showOffice(webview,downloadUrl);
        }


    }

    @Override
    public void failure(String msg) {
        rootEmptyLy.setVisibility(View.VISIBLE);
        webview.setVisibility(View.GONE);
        pdfView.setVisibility(View.GONE);
    }
}
