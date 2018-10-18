package com.tepia.main.view.maincommon.reservoirs.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.base.utils.AppManager;
import com.tepia.base.utils.DoubleClickUtil;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.view.dialog.loading.SimpleLoadDialog;
import com.tepia.main.R;
import com.tepia.main.model.detai.ReservoirBean;
import com.tepia.main.model.reserviros.OperationPlanBean;
import com.tepia.main.view.maincommon.reservoirs.ReservoirsFragment;
import com.tepia.main.view.maincommon.reservoirs.mvpreservoir.ReserviorContract;
import com.tepia.main.view.maincommon.reservoirs.mvpreservoir.ReserviorPresent;
import com.tepia.main.view.maincommon.setting.DownLoadActivity;

import org.w3c.dom.Text;

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
 * 功能描述 :调度运行方案页面/水库安全管理应急预案/预览页面
 **/
public class OperationPlanActivity extends MVPBaseActivity<ReserviorContract.View, ReserviorPresent> implements ReserviorContract.View<OperationPlanBean> {

    private WebView webview;
    private LinearLayout rootEmptyLy;
    private TextView tv_empty_view_text;
    private TextView nameTv;
    private static final String value_one = "1";
    private static final String value_two = "2";
    public static final String value_preview = "100";
    public static final String PREVIEW_PATH = "PREVIEW_PATH";
    private ImageView officeIv;
    private TextView officeTitleTv;
    private ImageView officePreviewTv;
    private ImageView officeDownloadTv;
    private LinearLayout officeLy;

    @Override
    public int getLayoutId() {
        return R.layout.activity_operation;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        officeLy = findViewById(R.id.officeLy);
        String  selectstr = getIntent().getStringExtra("select");
        if(value_one.equals(selectstr)) {
            setCenterTitle("调度运行方案");
        }else if(value_two.equals(selectstr)) {
            setCenterTitle("水库安全管理应急预案");
        }else if(value_preview.equals(selectstr)){
            setCenterTitle("文件预览");
            officeLy.setVisibility(View.GONE);
        }
        showBack();

        nameTv = findViewById(R.id.nameTv);
        webview = findViewById(R.id.webview);
        rootEmptyLy = findViewById(R.id.rootEmptyLy);
        tv_empty_view_text = findViewById(R.id.tv_empty_view_text);
        officeIv = findViewById(R.id.officeIv);
        officeTitleTv = findViewById(R.id.officeTitleTv);
        officePreviewTv = findViewById(R.id.officePreviewTv);
        officePreviewTv.setVisibility(View.GONE);
        officeDownloadTv = findViewById(R.id.officeDownloadTv);
        ReservoirBean reservoirBean = com.tepia.main.model.user.UserManager.getInstance().getDefaultReservoir();
        String reservoirName = reservoirBean.getReservoir();
        nameTv.setText(reservoirName);
        if(value_one.equals(selectstr)) {
            String reservoirId = reservoirBean.getReservoirId();
            mPresenter.getFloodControlByReservoir(reservoirId);
        }else if(value_two.equals(selectstr)){
            String reservoirId = reservoirBean.getReservoirId();
            mPresenter.getEmergencyByReservoir(reservoirId);
        }else if(value_preview.equals(selectstr)){
            String  filepathstr = getIntent().getStringExtra(PREVIEW_PATH);
            if(TextUtils.isEmpty(filepathstr)){
                showEmpty("文件路径为空");
                return;
            }
//            showFile(filepathstr);
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
        if(data.getData() == null){
            showEmpty("暂无数据");

            return;
        }
        String downloadUrl = data.getData().getFilePath();
        if(TextUtils.isEmpty(downloadUrl)){
            showEmpty("文件路径为空");
            return;
        }
//        showFile(downloadUrl);
        OperationPlanBean.DataBean dataBean = data.getData();
        if(!TextUtils.isEmpty(downloadUrl)){
            if (downloadUrl.endsWith(".doc") || downloadUrl.endsWith(".docx")) {
                officeIv.setImageResource(R.drawable.jianjie_word);
            }else if (downloadUrl.endsWith(".xls") || downloadUrl.endsWith(".xlsx")) {
                officeIv.setImageResource(R.drawable.jianjie_excel);
            }else if (downloadUrl.endsWith(".ppt") || downloadUrl.endsWith(".pptx")) {
                officeIv.setImageResource(R.drawable.jianjie_ppt);
            }else if (downloadUrl.endsWith(".pdf") ) {
                officeIv.setImageResource(R.drawable.jianjie_pdf);
            }else if(downloadUrl.endsWith(".txt")){
                officeIv.setImageResource(R.drawable.jianjie_txt);
                webview.setVisibility(View.GONE);
            }
        }
        officeTitleTv.setText(dataBean.getFileName());
        officeLy.setOnClickListener(v -> {
            if (!DoubleClickUtil.isFastDoubleClick()){
                Intent intent = new Intent(OperationPlanActivity.this, DownLoadActivity.class);
                DownLoadActivity.setIntent(intent,dataBean.getFileName(),dataBean.getFilePath());
                startActivity(intent);
            }
        });

    }



    @Override
    public void failure(String msg) {
        showEmpty(msg);
    }

    private void showEmpty(String msg){
        officeLy.setVisibility(View.GONE);
        rootEmptyLy.setVisibility(View.VISIBLE);
        tv_empty_view_text.setText(msg);
        webview.setVisibility(View.GONE);
    }
}
