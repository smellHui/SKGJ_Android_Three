package com.tepia.main.view.mainworker.report;


import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.BaseCommonFragment;
import com.tepia.main.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author         :      zhang xinhua
 * Version         :       1.0
 * 功能描述        :       主页三 上报页 巡查责任人
 **/
@Route(path = AppRoutePath.app_main_fragment_shangbao_xuncha)
public class ReportFragment extends BaseCommonFragment{

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<Fragment> fragments ;
    private ArrayList<String> titles ;

    private LinearLayout root_dialog_shangbao;
    public ReportFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_report;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        setCenterTitle(getString(R.string.main_shangbao));
        getRightTianqi().setVisibility(View.VISIBLE);
        tabLayout = findView(R.id.tab);
        viewPager =findView(R.id.viewpager);
        root_dialog_shangbao =findView(R.id.root_dialog_shangbao);

        fragments = new ArrayList<Fragment>();
        titles = new ArrayList<String>();
        initViewpager();
    }

    @Override
    protected void initRequestData() {

    }

    private void initViewpager(){
        fragments.add(new WaterLevelFragment());
        fragments.add(new EmergencyFragment());
        titles.add("水位");
        titles.add("应急情况");
        MyPagerAdapter adpter = new MyPagerAdapter(getBaseActivity().getSupportFragmentManager(),getBaseActivity(),fragments,titles);
        viewPager.setAdapter(adpter);
        new_tab();

    }

    private void new_tab(){
        tabLayout.addTab(tabLayout.newTab().setCustomView(tab_icon("水位",R.drawable.bg_operation_tab)));
        tabLayout.addTab(tabLayout.newTab().setCustomView(tab_icon("应急情况",R.drawable.bg_operation_tab_01)));

        //Tablayout自定义view绑定ViewPager 自定义view时使用 tabLayout.setupWithViewPager(viewPager);方法关联无效，通过以下方法进行viewpager和tablayout的关联
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

    }

    private View tab_icon(String name,int iconID){
        View newtab =  LayoutInflater.from(getBaseActivity()).inflate(R.layout.tablayout_my,null);
        TextView tv = (TextView) newtab.findViewById(R.id.tabtext);
        tv.setText(name);
        ImageView im = (ImageView)newtab.findViewById(R.id.tabicon);
        im.setImageResource(iconID);
        return newtab;
    }



    public class MyPagerAdapter extends FragmentPagerAdapter {
        private Context context;
        private List<Fragment> fragmentList;
        private List<String> list_Title;

        public MyPagerAdapter(FragmentManager fm, Context context, List<Fragment> fragmentList, List<String> list_Title) {
            super(fm);
            this.context = context;
            this.fragmentList = fragmentList;
            this.list_Title = list_Title;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        /**
         * //此方法用来显示tab上的名字
         *
         * @param position
         * @return
         */
        @Override
        public CharSequence getPageTitle(int position) {
            return list_Title.get(position);
        }
    }





}