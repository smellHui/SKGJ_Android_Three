package com.tepia.main.view.maincommon.reservoirs.detail;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.tepia.base.mvp.BaseActivity;
import com.tepia.main.R;
/**
  * Created by      Android studio
  *
  * @author :ly (from Center Of Wuhan)
  * Date    :2018-9-18
  * Version :1.0
  * 功能描述 :调度运行方案页面
 **/
public class OperationPlanActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_operation;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCenterTitle("调度运行方案");
        showBack();
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
}
