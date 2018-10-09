package com.tepia.main.view.maintechnology.yunwei.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.tepia.base.mvp.BaseCommonFragment;
import com.tepia.main.view.maintechnology.yunwei.OperationListFragment;

import java.util.List;

/**
  * Created by      Android studio
  *
  * @author :wwj (from Center Of Wuhan)
  * Date    :2018/10/9
  * Version :1.0
  * 功能描述 :运维
 **/

public class OperationTabPageAdapter extends FragmentPagerAdapter {
    private List<BaseCommonFragment> mFragments;
    public OperationTabPageAdapter(FragmentManager fm, List<BaseCommonFragment> fragments) {
        super(fm);
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return  mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
