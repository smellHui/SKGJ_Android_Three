package com.tepia.main.view;


import com.alibaba.android.arouter.launcher.ARouter;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.BaseCommonFragment;
import com.tepia.main.R;
import com.tepia.main.view.main.map.MapArcgisFragment;
import com.tepia.main.view.maincommon.reservoirs.ReservoirsFragment;
import com.tepia.main.view.maincommon.setting.SettingFragment;
import com.tepia.main.view.maintechnology.threekeypoint.ThreePointJiShuFragment;
import com.tepia.main.view.maintechnology.yunwei.YunWeiJiShuFragment;
import com.tepia.main.view.mainworker.homepage.HomeXunChaFragment;
import com.tepia.main.view.mainworker.shangbao.ShangbaoFragment;
import com.tepia.main.view.mainworker.xuncha.XunchaFragment;

import java.util.ArrayList;

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

    public SettingFragment getSettingFragment() {
        if (settingFragment == null) {
            synchronized (SettingFragment.class) {
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
    public void clearFragment() {
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

    ArrayList<String> titles = new ArrayList();

    public ArrayList<String> getTitles(String valuestr) {

        switch (valuestr) {
            case "1":
                titles.add("首页");
                titles.add("巡查");
                titles.add("上报");
                titles.add("水库");
                titles.add("我的");

                break;
            default:
                titles.add("首页");
                titles.add("运维");
                titles.add("三个重点");
                titles.add("水库");
                titles.add("我的");
                break;
        }
        return titles;
    }

    public ArrayList<String> getTitles() {


        return titles;
    }

    ArrayList imageIds = new ArrayList<Integer>();

    public ArrayList<Integer> getImageIds(String valuestr) {

        switch (valuestr) {
            case "1":
                imageIds.add(R.drawable.selector_tabbar_bus);
                imageIds.add(R.drawable.selector_tabbar_bus);
                imageIds.add(R.drawable.selector_tabbar_bus);
                imageIds.add(R.drawable.selector_tabbar_bus);
                imageIds.add(R.drawable.selector_tabbar_bus);
                break;
            default:
                imageIds.add(R.drawable.selector_tabbar_bus);
                imageIds.add(R.drawable.selector_tabbar_bus);
                imageIds.add(R.drawable.selector_tabbar_bus);
                imageIds.add(R.drawable.selector_tabbar_bus);
                imageIds.add(R.drawable.selector_tabbar_bus);
                break;
        }
        return imageIds;
    }

    public ArrayList<Integer> getImageIds() {
        return imageIds;
    }

    ArrayList<BaseCommonFragment> tabMianfragments;

    public ArrayList<? extends BaseCommonFragment> getMainFragments(String valuestr) {

        switch (valuestr) {
            case "1":
                tabMianfragments.add(getHomeXunChaFragment());
                tabMianfragments.add(getXunchaFragment());
                tabMianfragments.add(getShangbaoFragment());
                tabMianfragments.add(getReservoirsFragment());
                tabMianfragments.add(getSettingFragment());
                break;
            default:
                tabMianfragments.add(getHomeXunChaFragment());
                tabMianfragments.add(getXunchaFragment());
                tabMianfragments.add(getThreePointJiShuFragment());
                tabMianfragments.add(getReservoirsFragment());
                tabMianfragments.add(getSettingFragment());
                break;
        }
        return tabMianfragments;
    }

    public ArrayList<? extends BaseCommonFragment> getMainFragments() {
        return tabMianfragments;
    }

    public void setMenuData(ArrayList<MenuItemBean> menuData) {
        if (menuData == null) {
            return;
        }
        if (tabMianfragments == null) {
            tabMianfragments = new ArrayList();
        }
        if (titles == null) {
            titles = new ArrayList<>();
        }
        if (imageIds == null) {
            imageIds = new ArrayList<>();
        }
        for (int i = 0; i < menuData.size(); i++) {
            BaseCommonFragment fragment = getRouteFragment(menuData.get(i));
            if (fragment != null) {
                tabMianfragments.add(fragment);
                titles.add(menuData.get(i).getMenuName());
                imageIds.add(R.drawable.selector_tabbar_bus);
            }

        }
    }

    private BaseCommonFragment getRouteFragment(MenuItemBean menuItemBean) {

        return (BaseCommonFragment) ARouter.getInstance().build(menuItemBean.getMenuHref()).navigation();
    }

    public void setMenuData(String valuestr) {
        ArrayList<MenuItemBean> menuData = new ArrayList<>();
        switch (valuestr) {
            case "1": {
                MenuItemBean bean = new MenuItemBean();
                bean.setMenuHref(AppRoutePath.app_main_fragment_home_xuncha);
                bean.setMenuName("首页");
                bean.setMenuCode("100");
                bean.setMenuIcon("100");
                menuData.add(bean);
            }
            {
                MenuItemBean bean = new MenuItemBean();
                bean.setMenuHref(AppRoutePath.app_main_fragment_yunwei);
                bean.setMenuName("运维");
                bean.setMenuCode("110");
                bean.setMenuIcon("110");
                ArrayList<MenuItemBean> childs = new ArrayList<>();
                {
                    MenuItemBean child = new MenuItemBean();
                    child.setMenuHref(AppRoutePath.app_main_fragment_yunwei_xunjian);
                    child.setMenuName("巡检");
                    child.setMenuCode("111");
                    child.setMenuIcon("111");
                    childs.add(child);
                }
                bean.setChildren(childs);
                menuData.add(bean);
            }
            {
                MenuItemBean bean = new MenuItemBean();
                bean.setMenuHref(AppRoutePath.app_main_fragment_shangbao_xuncha);
                bean.setMenuName("上报");
                bean.setMenuCode("120");
                bean.setMenuIcon("120");
                menuData.add(bean);
            }
            {
                MenuItemBean bean = new MenuItemBean();
                bean.setMenuHref(AppRoutePath.app_main_fragment_reservoirs);
                bean.setMenuName("水库");
                bean.setMenuCode("030");
                bean.setMenuIcon("030");
                menuData.add(bean);
            }
            {
                MenuItemBean bean = new MenuItemBean();
                bean.setMenuHref(AppRoutePath.app_main_fragment_mine);
                bean.setMenuName("我的");
                bean.setMenuCode("040");
                bean.setMenuIcon("040");
                menuData.add(bean);
            }
            break;
            case "2": {
                MenuItemBean bean = new MenuItemBean();
                bean.setMenuHref(AppRoutePath.app_main_fragment_home_jishu);
                bean.setMenuName("首页");
                bean.setMenuCode("200");
                bean.setMenuIcon("200");
                menuData.add(bean);
            }
            {
                MenuItemBean bean = new MenuItemBean();
                bean.setMenuHref(AppRoutePath.app_main_fragment_yunwei_jishu);
                bean.setMenuName("运维");
                bean.setMenuCode("210");
                bean.setMenuIcon("210");

                menuData.add(bean);
            }
            {
                MenuItemBean bean = new MenuItemBean();
                bean.setMenuHref(AppRoutePath.app_main_fragment_threekey_jishu);
                bean.setMenuName("三个重点");
                bean.setMenuCode("220");
                bean.setMenuIcon("220");
                menuData.add(bean);
            }
            {
                MenuItemBean bean = new MenuItemBean();
                bean.setMenuHref(AppRoutePath.app_main_fragment_reservoirs);
                bean.setMenuName("水库");
                bean.setMenuCode("030");
                bean.setMenuIcon("030");
                menuData.add(bean);
            }
            {
                MenuItemBean bean = new MenuItemBean();
                bean.setMenuHref(AppRoutePath.app_main_fragment_mine);
                bean.setMenuName("我的");
                bean.setMenuCode("040");
                bean.setMenuIcon("040");
                menuData.add(bean);
            }
            break;
            case "3":

            default: {
                MenuItemBean bean = new MenuItemBean();
                bean.setMenuHref(AppRoutePath.app_main_fragment_home_admin);
                bean.setMenuName("首页");
                bean.setMenuCode("300");
                bean.setMenuIcon("300");
                menuData.add(bean);
            }
            {
                MenuItemBean bean = new MenuItemBean();
                bean.setMenuHref(AppRoutePath.app_main_fragment_yunwei_xingzheng);
                bean.setMenuName("运维");
                bean.setMenuCode("310");
                bean.setMenuIcon("310");

                menuData.add(bean);
            }
            {
                MenuItemBean bean = new MenuItemBean();
                bean.setMenuHref(AppRoutePath.app_main_fragment_threekey_xingzheng);
                bean.setMenuName("三个重点");
                bean.setMenuCode("320");
                bean.setMenuIcon("320");
                menuData.add(bean);
            }
            {
                MenuItemBean bean = new MenuItemBean();
                bean.setMenuHref(AppRoutePath.app_main_fragment_reservoirs);
                bean.setMenuName("水库");
                bean.setMenuCode("030");
                bean.setMenuIcon("030");
                menuData.add(bean);
            }
            {
                MenuItemBean bean = new MenuItemBean();
                bean.setMenuHref(AppRoutePath.app_main_fragment_mine);
                bean.setMenuName("我的");
                bean.setMenuCode("040");
                bean.setMenuIcon("040");
                menuData.add(bean);
            }
            break;
        }
        setMenuData(menuData);
    }
}
