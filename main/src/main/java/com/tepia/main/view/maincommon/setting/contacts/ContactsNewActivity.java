package com.tepia.main.view.maincommon.setting.contacts;

import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tepia.base.AppRoutePath;
import com.tepia.base.common.CommonFragmentPagerAdapter;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.main.R;
import com.tepia.main.common.ViewPagerCompat;

/**
  * Created by      Android studio
  *
  * @author :ly (from Center Of Wuhan)
  * 创建时间 :2019-3-19
  * 更新时间 :
  * Version :1.0
  * 功能描述 :通讯录
 **/
@Route(path = AppRoutePath.app_contacts)
public class ContactsNewActivity extends BaseActivity {

    private ViewPagerCompat vpPatrolDetailViewPager;
    private TabLayout tabLayout;

    @Override
    public int getLayoutId() {
        return R.layout.activity_contacts_new;
    }

    @Override
    public void initView() {
        setCenterTitle("通讯录");
        showBack();
        tabLayout = findViewById(R.id.tp_tabLayout);
        vpPatrolDetailViewPager = findViewById(R.id.contact_viewPager);
        initTabLayout();
        initViewPager();
    }

    @Override
    public void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {

    }

    private void initTabLayout() {
        tabLayout.addTab(tabLayout.newTab().setText("水库"));
        tabLayout.addTab(tabLayout.newTab().setText("防汛人员"));
//        tabLayout.addTab(tabLayout.newTab().setCustomView(getTabCustomView(tpTabNames[0], R.drawable.bg_log_river_tab)));
//        tabLayout.addTab(tabLayout.newTab().setCustomView(getTabCustomView(tpTabNames[1], R.drawable.bg_log_river_baseinfo_tab)));
        //设置分割线
        LinearLayout linearLayout = (LinearLayout) tabLayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerPadding(40);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(getApplicationContext(),
                R.drawable.sp_exposure_select));
    }



    /**
     * 初始化各个页面并和viewpager绑定
     */
    private void initViewPager() {
        CommonFragmentPagerAdapter adapter = new CommonFragmentPagerAdapter(getSupportFragmentManager());

        BookFirstFragment bookFirstFragment = new BookFirstFragment();
        BookSecFragment bookSecFragment = new BookSecFragment();
        adapter.addFragment(bookFirstFragment);
        adapter.addFragment(bookSecFragment);
        vpPatrolDetailViewPager.setAdapter(adapter);
        vpPatrolDetailViewPager.setCurrentItem(0);

        vpPatrolDetailViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(vpPatrolDetailViewPager));

    }
}
