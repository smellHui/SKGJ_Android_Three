package com.tepia.main.view.mainworker.yunwei;


import android.support.v4.app.Fragment;
import android.view.View;

import com.tepia.base.mvp.BaseCommonFragment;
import com.tepia.main.R;

/**
 * @author         :      zhang xinhua
 * Version         :       1.0
 * 功能描述        :        主页二 运维页 巡查责任人
 **/
public class YunWeiXunChaFragment extends BaseCommonFragment {


    public YunWeiXunChaFragment() {
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
