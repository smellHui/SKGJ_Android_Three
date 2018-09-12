package com.tepia.main.view.mainadminister.threekeypoint;


import android.support.v4.app.Fragment;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.BaseCommonFragment;
import com.tepia.main.R;

/**
 * @author         :      zhang xinhua
 * Version         :       1.0
 * 功能描述        :      主页三   三个重点  行政责任人
 **/
@Route(path = AppRoutePath.app_main_fragment_threekey_xingzheng)
public class ThreePointXingZhengFragment extends BaseCommonFragment {


    public ThreePointXingZhengFragment() {
        // Required empty public constructor
    }



    @Override
    protected int getLayoutId() {
        return R.layout.fragment_three_point;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
       setCenterTitle(getString(R.string.main_threepoint));
    }

    @Override
    protected void initRequestData() {

    }

}
