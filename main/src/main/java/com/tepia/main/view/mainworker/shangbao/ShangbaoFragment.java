package com.tepia.main.view.mainworker.shangbao;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.BaseCommonFragment;
import com.tepia.main.R;

/**
 * @author         :      zhang xinhua
 * Version         :       1.0
 * 功能描述        :       主页三 上报页 巡查责任人
 **/
@Route(path = AppRoutePath.app_main_fragment_shangbao_xuncha)
public class ShangbaoFragment extends BaseCommonFragment {


    public ShangbaoFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shangbao;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        setCenterTitle(getString(R.string.main_shangbao));
    }

    @Override
    protected void initRequestData() {

    }
}
