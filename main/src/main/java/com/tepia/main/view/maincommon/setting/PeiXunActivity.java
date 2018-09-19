package com.tepia.main.view.maincommon.setting;

import android.app.Activity;
import android.os.Bundle;

import com.tepia.base.mvp.BaseActivity;
import com.tepia.main.R;

/**
  * Created by      Android studio
  *
  * @author :ly (from Center Of Wuhan)
  * Date    :2018-9-19
  * Version :1.0
  * 功能描述 :工作培训
 **/
public class PeiXunActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_pei_xun;
    }

    @Override
    public void initView() {
        setCenterTitle("工作培训");
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
