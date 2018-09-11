package com.tepia.main.view.main.work.task2.tasklist;




/**
 * @author
 * update on 2018/7/12 by ly
 */
public class TabTaskFragmentFactory {


    private static TabTaskFragmentFactory mInstance;

    public static TabTaskFragmentFactory getInstance() {
        if (mInstance == null) {
            synchronized (TabTaskFragmentFactory.class) {
                if (mInstance == null) {
                    mInstance = new TabTaskFragmentFactory();
                }
            }
        }
        return mInstance;
    }

    /**
     *
     */
    public TabTaskListFragment getTaskListFragment1(int taskStatus,String type) {
        TabTaskListFragment mTabTaskListFragment1 = null;
        if (mTabTaskListFragment1 == null) {
            synchronized (TabTaskFragmentFactory.class) {
                if (mTabTaskListFragment1 == null) {
                    mTabTaskListFragment1 = TabTaskListFragment.newInstance(taskStatus,type);
                }
            }
        }
        return mTabTaskListFragment1;
    }

   /* *//**
     *
     *//*
    public TabTaskListFragment mTabTaskListFragment2;

    public TabTaskListFragment getTaskListFragment2() {
        if (mTabTaskListFragment2 == null) {
            synchronized (TabTaskFragmentFactory.class) {
                if (mTabTaskListFragment2 == null) {
                    mTabTaskListFragment2 = TabTaskListFragment.newInstance(2);
                }
            }
        }
        return mTabTaskListFragment2;
    }

    *//**
     *
     *//*
    public TabTaskListFragment mTabTaskListFragment3;

    public TabTaskListFragment getTaskListFragment3() {
        if (mTabTaskListFragment3 == null) {
            synchronized (TabTaskFragmentFactory.class) {
                if (mTabTaskListFragment3 == null) {
                    mTabTaskListFragment3 = TabTaskListFragment.newInstance(3);
                }
            }
        }
        return mTabTaskListFragment3;
    }

    *//**
     *
     *//*
    public TabTaskListFragment mTabTaskListFragment4;

    public TabTaskListFragment getTaskListFragment4() {
        if (mTabTaskListFragment4 == null) {
            synchronized (TabTaskFragmentFactory.class) {
                if (mTabTaskListFragment4 == null) {
                    mTabTaskListFragment4 =TabTaskListFragment.newInstance(4);
                }
            }
        }
        return mTabTaskListFragment4;
    }*/

}
