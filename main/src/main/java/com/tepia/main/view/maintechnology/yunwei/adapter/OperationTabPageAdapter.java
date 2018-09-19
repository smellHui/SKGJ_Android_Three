package com.tepia.main.view.maintechnology.yunwei.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.tepia.main.view.maintechnology.yunwei.OperationListFragment;

import java.util.List;

/**
 * Created by      Intellij IDEA
 *
 * @author :       wwj
 * Date    :       2018-09-18
 * Time    :       14:06
 * Version :       1.0
 * Company :       北京太比雅科技(武汉研发中心)
 **/
public class OperationTabPageAdapter extends FragmentPagerAdapter {
    private List<OperationListFragment> mFragments;
    public OperationTabPageAdapter(FragmentManager fm, List<OperationListFragment> fragments) {
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
