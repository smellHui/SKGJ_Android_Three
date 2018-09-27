package com.tepia.main.view.maintechnology.threekeypoint.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tepia.base.mvp.BaseCommonFragment;
import com.tepia.main.view.maintechnology.threekeypoint.ThreePointListFragment;

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
public class ThreePointTabPageAdapter extends FragmentPagerAdapter {
    private List<BaseCommonFragment> mFragments;
    public ThreePointTabPageAdapter(FragmentManager fm, List<BaseCommonFragment> fragments) {
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
