package com.tepia.main.view.maincommon.reservoirs.detail;

import android.app.Activity;
import android.os.Bundle;

import com.tepia.base.mvp.BaseActivity;
import com.tepia.main.R;

/**
 * Created by      android studio
 *
 * @author         :       ly(from Wuhan)
 * Date            :       ${YEAR}-${MONTH}-${DAY}
 * Time            :       ${TIME}
 * Version         :       1.0
 * 功能描述         :       水库简介
 *
 **/
public class JianjieOfReservoirsActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_jianjie_of_reservoirs;
    }

    @Override
    public void initView() {
        setCenterTitle("水库简介");
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
