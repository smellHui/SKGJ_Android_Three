package com.tepia.main.view.maintechnology.yunwei;


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
 * 功能描述        :       主页二 运维页 技术责任人
 **/
@Route(path = AppRoutePath.app_main_fragment_yunwei_jishu)
public class YunWeiJiShuFragment extends BaseCommonFragment {


    public YunWeiJiShuFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_yun_wei;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
      setCenterTitle(getString(R.string.main_yunwei));
    }

    @Override
    protected void initRequestData() {

    }

}
