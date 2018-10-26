package com.tepia.main.view.maincommon.reservoirs.detail;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.tepia.base.mvp.BaseActivity;
import com.tepia.base.utils.SPUtils;
import com.tepia.main.R;
import com.tepia.main.view.mainworker.report.EmergenceReportFragment;

/**
  * Created by      Android studio
  *
  * @author :ly (from Center Of Wuhan)
  * Date    :2018-10-26
  * Version :1.0
  * 功能描述 : 添加到访日志ctivity(内含fragment)
 **/
public class VisitLogAddActivity extends BaseActivity {

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
    private VisitLogAddFragment visitLogAddFragment;

    /**
     * 事件上报
     */
    private void initQuestionFragment() {
        transaction = getSupportFragmentManager().beginTransaction();
        visitLogAddFragment = new VisitLogAddFragment();
        transaction.replace(R.id.fl_container, visitLogAddFragment);
        transaction.show(visitLogAddFragment);
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
