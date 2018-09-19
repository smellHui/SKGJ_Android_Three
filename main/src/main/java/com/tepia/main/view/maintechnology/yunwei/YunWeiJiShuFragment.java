package com.tepia.main.view.maintechnology.yunwei;


import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.BaseCommonFragment;
import com.tepia.main.R;
import com.tepia.main.view.maintechnology.yunwei.adapter.OperationTabPageAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :      zhang xinhua
 * Version         :       1.0
 * 功能描述        :       主页二 运维页 技术责任人
 **/
@Route(path = AppRoutePath.app_main_fragment_yunwei_jishu)
public class YunWeiJiShuFragment extends BaseCommonFragment {
    private String[] tabNames = {"巡检", "保洁", "维修养护", "上报"};
    private List<OperationListFragment> mFragments = new ArrayList<>();
    private TabLayout tabLayout;
    private ViewPager viewPager;

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
        getRightTianqi().setVisibility(View.VISIBLE);
        tabLayout = findView(R.id.tabLayout);
        viewPager = findView(R.id.viewPager);
        initTabLayout();
        initViewPager();
        initListener();
    }

    private void initListener() {
        //将TabLayout和ViewPager关联
//        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                tabLayout.setScrollPosition(position,positionOffset,true);
            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initViewPager() {
        for (int i = 0; i < tabNames.length; i++) {
            OperationListFragment fragment = new OperationListFragment();
            mFragments.add(fragment);
        }
        OperationTabPageAdapter tabPageAdapter = new OperationTabPageAdapter(getFragmentManager(),mFragments);
        viewPager.setAdapter(tabPageAdapter);
    }

    private void initTabLayout() {
        tabLayout.addTab(tabLayout.newTab().setCustomView(getTabCustomView(tabNames[0], R.drawable.bg_operation_tab)));
        tabLayout.addTab(tabLayout.newTab().setCustomView(getTabCustomView(tabNames[1], R.drawable.bg_operation_tab_01)));
        tabLayout.addTab(tabLayout.newTab().setCustomView(getTabCustomView(tabNames[2], R.drawable.bg_operation_tab_02)));
        tabLayout.addTab(tabLayout.newTab().setCustomView(getTabCustomView(tabNames[3], R.drawable.bg_operation_tab_03)));
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
