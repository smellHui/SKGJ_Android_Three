package com.tepia.main.view.mainworker.yunwei;


import android.databinding.DataBindingUtil;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.BaseCommonFragment;
import com.tepia.main.R;
import com.tepia.main.databinding.FragmentYunWeiXunchaBinding;

/**
 * @author :      zhang xinhua
 * Version         :       1.0
 * 功能描述        :        主页二 运维页 巡查责任人
 **/
@Route(path = AppRoutePath.app_main_fragment_yunwei)
public class YunWeiXunChaFragment extends BaseCommonFragment {


    private TabLayout tlTitle;
    /**
     *
     */
    private FragmentYunWeiXunchaBinding mBinding;

    public YunWeiXunChaFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_yun_wei_xuncha;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        setCenterTitle(getString(R.string.main_yunwei));
        getRightTianqi().setVisibility(View.VISIBLE);
        tlTitle = (TabLayout)view.findViewWithTag("tl_title");
        mBinding = DataBindingUtil.bind(view);
    }

    @Override
    protected void initRequestData() {

    }

}
