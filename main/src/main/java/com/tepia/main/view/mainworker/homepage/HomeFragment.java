package com.tepia.main.view.mainworker.homepage;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
public class HomeFragment extends BaseCommonFragment {


    public HomeFragment() {
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

    }

    @Override
    protected void initRequestData() {

    }

}
