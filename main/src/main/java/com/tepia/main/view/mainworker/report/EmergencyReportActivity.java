package com.tepia.main.view.mainworker.report;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.tepia.base.mvp.BaseActivity;
import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.base.utils.SPUtils;
import com.tepia.main.R;

/**
  * Created by      Android studio
  *
  * @author :ly (from Center Of Wuhan)
  * Date    :2018-9-20
  * Version :1.0
  * 功能描述 : 应急上报页面activity(内含fragment)
 **/
public class EmergencyReportActivity extends BaseActivity {



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
    private EmergenceReportFragment emergenceReportFragment;

    /**
     * 事件上报
     */
    private void initQuestionFragment() {
        transaction = getSupportFragmentManager().beginTransaction();
        emergenceReportFragment = new EmergenceReportFragment();
        Bundle bundle = getIntent().getExtras();
        emergenceReportFragment.setArguments(bundle);

        transaction.replace(R.id.fl_container, emergenceReportFragment);
        transaction.show(emergenceReportFragment);

        if (!isFinishing()) {
            transaction.commitAllowingStateLoss();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
