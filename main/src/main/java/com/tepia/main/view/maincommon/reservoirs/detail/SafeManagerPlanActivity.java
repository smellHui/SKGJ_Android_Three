package com.tepia.main.view.maincommon.reservoirs.detail;

import android.app.Activity;
import android.os.Bundle;

import com.tepia.base.mvp.BaseActivity;
import com.tepia.main.R;
/**
  * Created by      Android studio
  *
  * @author :ly (from Center Of Wuhan)
  * Date    :2018-9-18
  * Version :1.0
  * 功能描述 :安全管理预案页面
 **/
public class SafeManagerPlanActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_safe_manager_plan;
    }

    @Override
    public void initView() {
        setCenterTitle("水库安全管理应急预案");
        showBack();

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
