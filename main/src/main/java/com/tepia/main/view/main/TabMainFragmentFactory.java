package com.tepia.main.view.main;


import com.tepia.main.view.main.map.MapArcgis2Fragment;
import com.tepia.main.view.main.map.MapArcgisFragment;
import com.tepia.main.view.main.setting.SettingFragment;
import com.tepia.main.view.main.work.WorkFragment;
import com.tepia.main.view.main.work.WorkFragmentNew;

/*************************************************************
 * Created by OCN.YAN                                        *
 * 主要功能:基类                                              *
 * 项目名:贵州水务                                            *
 * 包名:com.elegant.river_system.vm.base                        *
 * 创建时间:2017年10月12日11:16                               *
 * 更新时间:2017年10月24日11:16                               *
 * 版本号:1.1.0                                              *
 *************************************************************/
public class TabMainFragmentFactory {


    private static TabMainFragmentFactory mInstance;

    public static TabMainFragmentFactory getInstance() {
        if (mInstance == null) {
            synchronized (TabMainFragmentFactory.class) {
                if (mInstance == null) {
                    mInstance = new TabMainFragmentFactory();
                }
            }
        }
        return mInstance;
    }

    /**
     * 工作模块
     */
    public WorkFragment mTabMainTaskFragment;

    public WorkFragment getTabMainTaskFragment() {
        if (mTabMainTaskFragment == null) {
            synchronized (WorkFragment.class) {
                if (mTabMainTaskFragment == null) {
                    mTabMainTaskFragment = new WorkFragment();
                }
            }
        }
        return mTabMainTaskFragment;
    }


    public WorkFragmentNew workFragmentNew;

    public WorkFragmentNew getWorkFragmentNew() {
        if (workFragmentNew == null) {
            synchronized (WorkFragmentNew.class) {
                if (workFragmentNew == null) {
                    workFragmentNew = new WorkFragmentNew();
                }
            }
        }
        return workFragmentNew;
    }




    /**
     * 地图
     */
    public MapArcgisFragment mapArcgisFragment;

    public MapArcgisFragment getMapArcgisFragment() {
        if (mapArcgisFragment == null) {
            synchronized (MapArcgisFragment.class) {
                if (mapArcgisFragment == null) {
                    mapArcgisFragment = new MapArcgisFragment();
                }
            }
        }
        return mapArcgisFragment;
    }

    /**
     * 设置页面
     */
    public SettingFragment settingFragment;
    public SettingFragment getSettingFragment(){
        if (settingFragment == null) {
            synchronized (SettingFragment.class){
                if (settingFragment == null) {
                    settingFragment = new SettingFragment();
                }
            }
        }
        return settingFragment;
    }

    /**
     * 清空静态变量fragment
     */
    public  void clearFragment(){
        if (mTabMainTaskFragment != null) {
            mTabMainTaskFragment = null;
        }

        if (settingFragment != null) {
            settingFragment = null;
        }
    }


}
