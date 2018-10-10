package com.tepia.main.view.maintechnology.yunwei;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.BaseCommonFragment;
import com.tepia.main.R;
import com.tepia.main.view.maintechnology.yunwei.adapter.OperationTabPageAdapter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author :      zhang xinhua
 * Version         :       1.0
 * 功能描述        :       主页二 运维页 技术责任人
 **/
@Route(path = AppRoutePath.app_main_fragment_yunwei_jishu)
public class YunWeiJiShuFragment extends BaseCommonFragment {
    private String[] tabNames = {"巡检", "维修养护", "保洁", "上报"};
    private List<BaseCommonFragment> mFragments = new ArrayList<>();
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private OperationReportFragment operationReportFragment;

    public YunWeiJiShuFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_jishu_yun_wei;
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
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                int position = tab.getPosition();
//                viewPager.setCurrentItem(position);
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
    }

    private void initViewPager() {
        for (int i = 0; i < tabNames.length-1; i++) {
            OperationListFragment fragment = new OperationListFragment();
            Bundle bundle = new Bundle();
            bundle.putString("type",tabNames[i]);
            fragment.setArguments(bundle);
            mFragments.add(fragment);
        }
        operationReportFragment = new OperationReportFragment();
        mFragments.add(operationReportFragment);
        OperationTabPageAdapter tabPageAdapter = new OperationTabPageAdapter(getFragmentManager(), mFragments);
        viewPager.setAdapter(tabPageAdapter);
    }

    private void initTabLayout() {
        tabLayout.addTab(tabLayout.newTab().setCustomView(getTabCustomView(tabNames[0], R.drawable.bg_operation_tab)));
        tabLayout.addTab(tabLayout.newTab().setCustomView(getTabCustomView(tabNames[1], R.drawable.bg_operation_tab_01)));
        tabLayout.addTab(tabLayout.newTab().setCustomView(getTabCustomView(tabNames[2], R.drawable.bg_operation_tab_02)));
        tabLayout.addTab(tabLayout.newTab().setCustomView(getTabCustomView(tabNames[3], R.drawable.bg_operation_tab_03)));
        tabLayout.post(() -> {
            try {
                //拿到tabLayout的mTabStrip属性
                Field mTabStripField = tabLayout.getClass().getDeclaredField("mTabStrip");
                mTabStripField.setAccessible(true);

                LinearLayout mTabStrip = (LinearLayout) mTabStripField.get(tabLayout);
                for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                    View tabView = mTabStrip.getChildAt(i);
                    //拿到tabView的mCustomView属性
                    Field mCustomViewField = tabView.getClass().getDeclaredField("mCustomView");
                    mCustomViewField.setAccessible(true);
                    View mCustomView = (View) mCustomViewField.get(tabView);
                    tabView.setPadding(0, 0, 0, 0);
                    int width = 0;
                    width = mCustomView.getWidth();
                    if (width == 0) {
                        mCustomView.measure(0, 0);
                        width = mCustomView.getMeasuredWidth();
                    }
                    if (i==1){
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                        params.weight = (float) 1.5;
                        tabView.setLayoutParams(params);
                        tabView.invalidate();
                    }else {
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                        params.weight = 1;
                        tabView.setLayoutParams(params);
                        tabView.invalidate();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private View getTabCustomView(String name, int id) {
        View view = getLayoutInflater().inflate(R.layout.operation_tab_custom_view, null);
        TextView tab_tv = (TextView) view.findViewById(R.id.tab_tv);
        tab_tv.setText(name);
        ImageView tab_iv = view.findViewById(R.id.tab_iv);
        tab_iv.setImageResource(id);
        return view;
    }

    @Override
    protected void initRequestData() {
//        refreshPage();
    }

    private void refreshPage() {
        if (viewPager.getCurrentItem()<=2){
            OperationListFragment operationListFragment = (OperationListFragment) mFragments.get(viewPager.getCurrentItem());
            operationListFragment.initRequestResponse();
        }else {
            operationReportFragment.initRequestResponse();
        }
    }


}
