package com.tepia.main.view.main;


import com.tepia.main.view.main.map.MapArcgisFragment;
import com.tepia.main.view.maintechnology.threekeypoint.ThreePointJiShuFragment;
import com.tepia.main.view.maintechnology.yunwei.YunWeiJiShuFragment;
import com.tepia.main.view.mainworker.homepage.HomeXunChaFragment;
import com.tepia.main.view.maincommon.reservoirs.ReservoirsFragment;
import com.tepia.main.view.maincommon.setting.SettingFragment;
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
    public HomeXunChaFragment homeXunChaFragment;

    public HomeXunChaFragment getHomeXunChaFragment() {
        if (homeXunChaFragment == null) {
            synchronized (HomeXunChaFragment.class) {
                if (homeXunChaFragment == null) {
                    homeXunChaFragment = new HomeXunChaFragment();
                }
            }
        }
        return homeXunChaFragment;
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
    public YunWeiJiShuFragment yunWeiJiShuFragment;

    public YunWeiJiShuFragment getYunWeiJiShuFragment() {
        if (yunWeiJiShuFragment == null) {
            synchronized (YunWeiJiShuFragment.class) {
                if (yunWeiJiShuFragment == null) {
                    yunWeiJiShuFragment = new YunWeiJiShuFragment();
                }
            }
        }
        return yunWeiJiShuFragment;
    }

    /**
     * 三个重点
     */
    public ThreePointJiShuFragment threePointJiShuFragment;

    public ThreePointJiShuFragment getThreePointJiShuFragment() {
        if (threePointJiShuFragment == null) {
            synchronized (ThreePointJiShuFragment.class) {
                if (threePointJiShuFragment == null) {
                    threePointJiShuFragment = new ThreePointJiShuFragment();
                }
            }
        }
        return threePointJiShuFragment;
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
        if (threePointJiShuFragment != null) {
            threePointJiShuFragment = null;
        }
        if (yunWeiJiShuFragment != null) {
            yunWeiJiShuFragment = null;
        }
        if (homeXunChaFragment != null) {
            homeXunChaFragment = null;
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
