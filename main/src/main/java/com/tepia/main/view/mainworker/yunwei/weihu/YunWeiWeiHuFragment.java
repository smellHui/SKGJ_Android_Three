package com.tepia.main.view.mainworker.yunwei.weihu;


import android.databinding.DataBindingUtil;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tepia.base.AppRoutePath;
import com.tepia.base.common.CommonFragmentPagerAdapter;
import com.tepia.base.mvp.BaseCommonFragment;
import com.tepia.main.R;
import com.tepia.main.databinding.FragmentYunWeiXunchaBinding;
import com.tepia.main.view.mainworker.yunwei.startyunwei.StartYunWeiFragment;

import java.util.List;

/**
 * @author :      zhang xinhua
 * Version         :       1.0
 * 功能描述        :        主页二 运维页 巡查责任人
 **/
@Route(path = AppRoutePath.app_main_fragment_yunwei_weihu)
public class YunWeiWeiHuFragment extends BaseCommonFragment {


    private TabLayout tlTitle;
    /**
     *
     */
    private FragmentYunWeiXunchaBinding mBinding;
    private List<BaseCommonFragment> mFragments;

    public YunWeiWeiHuFragment() {
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
        tlTitle = (TabLayout) view.findViewWithTag("tl_title");
        mBinding = DataBindingUtil.bind(view);
        initRLTitle();
        initViewPager();
        tlTitle.setupWithViewPager(mBinding.vpContainer);
    }

    private void initViewPager() {
        CommonFragmentPagerAdapter adapter = new CommonFragmentPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new StartYunWeiFragment());
        adapter.addFragment(new StartYunWeiFragment());
        mBinding.vpContainer.setAdapter(adapter);

    }

    private void initRLTitle() {
        tlTitle.addTab(tlTitle.newTab().setCustomView(getTabCustomView("开始运维", R.drawable.bg_operation_tab)));
        tlTitle.addTab(tlTitle.newTab().setCustomView(getTabCustomView("运维记录", R.drawable.bg_operation_tab)));
    }
    private View getTabCustomView(String name, int id) {
        View view = getLayoutInflater().inflate(R.layout.operation_tab_custom_view2, null);
        TextView tab_tv = (TextView) view.findViewById(R.id.tab_tv);
        tab_tv.setText(name);
        ImageView tab_iv = view.findViewById(R.id.tab_iv);
        tab_iv.setBackgroundResource(id);
        return view;
    }
    @Override
    protected void initRequestData() {

    }

}
