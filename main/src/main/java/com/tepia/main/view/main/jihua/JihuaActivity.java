package com.tepia.main.view.main.jihua;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.main.R;

@Route(path = AppRoutePath.app_gongdan_jihua)
public class JihuaActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_jihua;
    }

    @Override
    public void initView() {
        setCenterTitle(getString(R.string.shuikuxunjian));
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
