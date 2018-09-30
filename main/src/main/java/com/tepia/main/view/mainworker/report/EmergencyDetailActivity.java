package com.tepia.main.view.mainworker.report;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.base.utils.SPUtils;
import com.tepia.main.ConfigConsts;
import com.tepia.main.R;
import com.tepia.main.common.pickview.OnItemClickListener;
import com.tepia.main.common.pickview.PhotoRecycleViewAdapter;
import com.tepia.main.common.pickview.RecyclerItemClickListener;
import com.tepia.main.model.report.EmergenceListBean;
import com.tepia.main.view.main.question.QuestionNewFragment;
import com.tepia.photo_picker.PhotoPicker;
import com.tepia.photo_picker.PhotoPreview;

import java.util.ArrayList;
import java.util.List;

/**
  * Created by      Android studio
  *
  * @author :ly (from Center Of Wuhan)
  * Date    :2018-9-20
  * Version :1.0
  * 功能描述 : 应急上报页面
 **/
public class EmergencyDetailActivity extends MVPBaseActivity<ReportContract.View,ReportPresenter> {

    protected static String key_Title = "key_Title";
    protected static String key_Content = "key_Content";

    @Override
    public int getLayoutId() {
        return R.layout.activity_emergency_detail;
    }

    @Override
    public void initView() {

        initQuestionFragment();

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

    private FragmentTransaction transaction;
    private EmergenceDetaliFragment questionFragment;

    /**
     * 事件上报
     */
    private void initQuestionFragment() {
        transaction = getSupportFragmentManager().beginTransaction();
        questionFragment = new EmergenceDetaliFragment();
        transaction.replace(R.id.fl_container, questionFragment);
        transaction.show(questionFragment);
        transaction.commitAllowingStateLoss();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SPUtils.getInstance().remove(key_Title);
        SPUtils.getInstance().remove(key_Content);
    }
}
