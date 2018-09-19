package com.tepia.main.view.maincommon.reservoirs.detail;

import android.app.Activity;
import android.os.Bundle;

import com.tepia.base.mvp.BaseActivity;
import com.tepia.main.R;

public class ItemSafeJiandingActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_item_safe_jianding;
    }

    @Override
    public void initView() {
        setCenterTitle("安全鉴定详情");
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
