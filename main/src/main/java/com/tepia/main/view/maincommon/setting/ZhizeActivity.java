package com.tepia.main.view.maincommon.setting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tepia.base.mvp.BaseActivity;
import com.tepia.main.R;

public class ZhizeActivity extends BaseActivity {


    @Override
    public int getLayoutId() {
        return R.layout.activity_zhize;
    }

    @Override
    public void initView() {
       setCenterTitle("工作职责");
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
