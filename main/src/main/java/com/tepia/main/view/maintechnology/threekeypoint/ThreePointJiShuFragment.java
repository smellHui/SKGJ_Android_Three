package com.tepia.main.view.maintechnology.threekeypoint;


import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.BaseCommonFragment;
import com.tepia.main.R;
import com.tepia.main.view.maintechnology.threekeypoint.adapter.ThreePointTabPageAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author         :      zhang xinhua
 * Version         :       1.0
 * 功能描述        :       主页三  三个重点 技术责任人
 **/
@Route(path = AppRoutePath.app_main_fragment_threekey_jishu)
public class ThreePointJiShuFragment extends BaseCommonFragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String[] tpTabNames = {"检测预报", "调度运用", "应急预案"};
    private List<ThreePointListFragment> mFragments = new ArrayList<>();
    public ThreePointJiShuFragment() {
        // Required empty public constructor
    }



    @Override
    protected int getLayoutId() {
        return R.layout.fragment_jishu_three_point;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        setCenterTitle(getString(R.string.main_threepoint));
        getRightTianqi().setVisibility(View.VISIBLE);
//        ImageView ivLeft = findView(R.id.iv_left);
//        ivLeft.setOnClickListener(v-> ARouter.getInstance().build(AppRoutePath.app_weather_forecast).navigation());
        tabLayout = findView(R.id.tp_tabLayout);
        viewPager = findView(R.id.tp_viewPager);
        initTabLayout();
        initViewPager();
        initListener();
    }

    private void initListener() {
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
    }

    private void initViewPager() {
        for (int i = 0; i < tpTabNames.length; i++) {
            ThreePointListFragment fragment = new ThreePointListFragment();
            mFragments.add(fragment);
        }
        ThreePointTabPageAdapter tabPageAdapter = new ThreePointTabPageAdapter(getFragmentManager(),mFragments);
        viewPager.setAdapter(tabPageAdapter);
    }

    private void initTabLayout() {
        tabLayout.addTab(tabLayout.newTab().setCustomView(getTabCustomView(tpTabNames[0], R.drawable.bg_three_point_tab)));
        tabLayout.addTab(tabLayout.newTab().setCustomView(getTabCustomView(tpTabNames[1], R.drawable.bg_three_point_01_tab)));
        tabLayout.addTab(tabLayout.newTab().setCustomView(getTabCustomView(tpTabNames[2], R.drawable.bg_three_point_02_tab)));
    }

    private View getTabCustomView(String name, int id) {
        View view = getLayoutInflater().inflate(R.layout.operation_tab_custom_view, null);
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
