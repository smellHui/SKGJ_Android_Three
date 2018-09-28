package com.tepia.main.view.mainadminister.yunwei;


import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.BaseCommonFragment;
import com.tepia.main.R;

/**
 * @author         :      zhang xinhua
 * Version         :       1.0
 * 功能描述        :      主页二 行政责任人 运维页
 **/
@Route(path = AppRoutePath.app_main_fragment_yunwei_xingzheng)
public class YunWeiXingZhengFragment extends BaseCommonFragment {


    public YunWeiXingZhengFragment() {
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
