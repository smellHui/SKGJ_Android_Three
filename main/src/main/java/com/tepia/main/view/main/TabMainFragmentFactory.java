package com.tepia.main.view.main;


import com.tepia.main.view.main.map.MapArcgisFragment;
import com.tepia.main.view.maintechnology.threekeypoint.ThreePointFragment;
import com.tepia.main.view.maintechnology.yunwei.YunWeiFragment;
import com.tepia.main.view.mainworker.homepage.HomeFragment;
import com.tepia.main.view.mainworker.reservoirs.ReservoirsFragment;
import com.tepia.main.view.mainworker.setting.SettingFragment;
import com.tepia.main.view.main.work.WorkFragment;
import com.tepia.main.view.main.work.WorkFragmentNew;
import com.tepia.main.view.mainworker.shangbao.ShangbaoFragment;
import com.tepia.main.view.mainworker.xuncha.XunchaFragment;

/**
 * @author : liying
 * Date    : 2018-9-11
 * Version : 1.0
 * 功能描述 : 工厂
 */
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
    public HomeFragment homeFragment;

    public HomeFragment getHomeFragment() {
        if (homeFragment == null) {
            synchronized (HomeFragment.class) {
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                }
            }
        }
        return homeFragment;
    }


    public ReservoirsFragment reservoirsFragment;

    public ReservoirsFragment getReservoirsFragment() {
        if (reservoirsFragment == null) {
            synchronized (ReservoirsFragment.class) {
                if (reservoirsFragment == null) {
                    reservoirsFragment = new ReservoirsFragment();
                }
            }
        }
        return reservoirsFragment;
    }

    /**
     * 上报
     */
    public ShangbaoFragment shangbaoFragment;

    public ShangbaoFragment getShangbaoFragment() {
        if (shangbaoFragment == null) {
            synchronized (ShangbaoFragment.class) {
                if (shangbaoFragment == null) {
                    shangbaoFragment = new ShangbaoFragment();
                }
            }
        }
        return shangbaoFragment;
    }

    /**
     * 巡检
     */
    public XunchaFragment xunchaFragment;

    public XunchaFragment getXunchaFragment() {
        if (xunchaFragment == null) {
            synchronized (XunchaFragment.class) {
                if (xunchaFragment == null) {
                    xunchaFragment = new XunchaFragment();
                }
            }
        }
        return xunchaFragment;
    }


    /**
     * 运维
     */
    public YunWeiFragment yunWeiFragment;

    public YunWeiFragment getYunWeiFragment() {
        if (yunWeiFragment == null) {
            synchronized (YunWeiFragment.class) {
                if (yunWeiFragment == null) {
                    yunWeiFragment = new YunWeiFragment();
                }
            }
        }
        return yunWeiFragment;
    }

    /**
     * 三个重点
     */
    public ThreePointFragment threePointFragment;

    public ThreePointFragment getThreePointFragment() {
        if (threePointFragment == null) {
            synchronized (ThreePointFragment.class) {
                if (threePointFragment == null) {
                    threePointFragment = new ThreePointFragment();
                }
            }
        }
        return threePointFragment;
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
        if (threePointFragment != null) {
            threePointFragment = null;
        }
        if (yunWeiFragment != null) {
            yunWeiFragment = null;
        }
        if (homeFragment != null) {
            homeFragment = null;
        }
        if (reservoirsFragment != null) {
            reservoirsFragment = null;
        }
        if (settingFragment != null) {
            settingFragment = null;
        }
        if (shangbaoFragment != null) {
            shangbaoFragment = null;
        }
        if (xunchaFragment != null) {
            xunchaFragment = null;
        }
    }





}
