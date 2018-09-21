package com.tepia.main.view.maintechnology.homepage;


import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.BaseCommonFragment;
import com.tepia.main.R;

/**
 * 首页
 * Created by      Intellij IDEA
 *
 * @author :       liying
 *         Date    :       2018-09-05
 *         Time    :       下午2:16
 *         Version :       1.0
 *         Company :       北京太比雅科技(武汉研发中心)
 **/

//@Route(path = AppRoutePath.app_main_fragment_home_jishu)
public class HomeJiShuFragment extends BaseCommonFragment {


    public HomeJiShuFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        setCenterTitle(getString(R.string.main_home));
        getRightTianqi().setVisibility(View.VISIBLE);


    }

    @Override
    protected void initRequestData() {

    }

}
