package com.tepia.main.view;


import com.alibaba.android.arouter.launcher.ARouter;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.BaseCommonFragment;
import com.tepia.base.utils.SPUtils;
import com.tepia.main.CacheConsts;
import com.tepia.main.ConfigConsts;
import com.tepia.main.R;
import com.tepia.main.view.main.map.MapArcgisFragment;
import com.tepia.main.view.maincommon.reservoirs.ReservoirsFragment;
import com.tepia.main.view.maincommon.setting.SettingFragment;
import com.tepia.main.view.maintechnology.threekeypoint.ThreePointJiShuFragment;
import com.tepia.main.view.maintechnology.yunwei.YunWeiJiShuFragment;
import com.tepia.main.view.mainworker.homepage.HomeXunChaFragment;
import com.tepia.main.view.mainworker.report.ReportFragment;


import java.util.ArrayList;
import java.util.List;

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
     * 清空静态变量fragment
     */
    public void clearFragment() {


        if (tabMianfragments != null) {
            tabMianfragments.clear();
        }

    }

    ArrayList<String> titles = new ArrayList();

    public ArrayList<String> getTitles() {


        return titles;
    }

    ArrayList imageIds = new ArrayList<Integer>();


    public ArrayList<Integer> getImageIds() {
        return imageIds;
    }

    ArrayList<BaseCommonFragment> tabMianfragments;


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
        tabMianfragments.clear();
        titles.clear();
        imageIds.clear();
        for (int i = 0; i < menuData.size(); i++) {
            BaseCommonFragment fragment = getRouteFragment(menuData.get(i));
            if ("110".equals(menuData.get(i).getMenuIcon())) {
                List<MenuItemBean> items = menuData.get(i).getChildren();
                if (items != null && items.size() == 1) {
                    BaseCommonFragment fragment1 = getRouteFragment(items.get(0));
                    if (fragment1 != null) {
                        tabMianfragments.add(fragment1);
                        titles.add(items.get(0).getMenuName());
                        if (TabMainIconManager.getInstance().getIcon(menuData.get(i).getMenuIcon()) == null) {
                            imageIds.add(R.drawable.selector_tabbar_bus);
                        } else {
                            String menuIcon = menuData.get(i).getMenuIcon();
                            if("321".equals(menuIcon)){
                                //表示有综合监控页面存在
                                SPUtils.getInstance().putBoolean(CacheConsts.haslook,true);
                            }
                            imageIds.add(TabMainIconManager.getInstance().getIcon(menuIcon));
                        }
                    }
                } else {
                    tabMianfragments.add(fragment);
                    titles.add(menuData.get(i).getMenuName());
                    if (TabMainIconManager.getInstance().getIcon(menuData.get(i).getMenuIcon()) == null) {
                        imageIds.add(R.drawable.selector_tabbar_bus);
                    } else {
                        String menuIcon = menuData.get(i).getMenuIcon();
                        if("321".equals(menuIcon)){
                            //表示有综合监控页面存在
                            SPUtils.getInstance().putBoolean(CacheConsts.haslook,true);
                        }
                        imageIds.add(TabMainIconManager.getInstance().getIcon(menuIcon));
//                        imageIds.add(TabMainIconManager.getInstance().getIcon(menuData.get(i).getMenuIcon()));
                    }
                }
            } else if (fragment != null) {
                tabMianfragments.add(fragment);
                titles.add(menuData.get(i).getMenuName());
                if (TabMainIconManager.getInstance().getIcon(menuData.get(i).getMenuIcon()) == null) {
                    imageIds.add(R.drawable.selector_tabbar_bus);
                } else {
                    String menuIcon = menuData.get(i).getMenuIcon();
                    if("321".equals(menuIcon)){
                        //表示有综合监控页面存在
                        SPUtils.getInstance().putBoolean(CacheConsts.haslook,true);
                    }
                    imageIds.add(TabMainIconManager.getInstance().getIcon(menuIcon));
//                    imageIds.add(TabMainIconManager.getInstance().getIcon(menuData.get(i).getMenuIcon()));
                }
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
                {
                    MenuItemBean child = new MenuItemBean();
                    child.setMenuHref(AppRoutePath.app_main_fragment_yunwei_xunjian);
                    child.setMenuName("保洁");
                    child.setMenuCode("112");
                    child.setMenuIcon("112");
                    childs.add(child);
                }
                {
                    MenuItemBean child = new MenuItemBean();
                    child.setMenuHref(AppRoutePath.app_main_fragment_yunwei_xunjian);
                    child.setMenuName("维护");
                    child.setMenuCode("113");
                    child.setMenuIcon("113");
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
                bean.setMenuCode(ConfigConsts.TECHNOLOGRROLE);
                bean.setMenuIcon(ConfigConsts.TECHNOLOGRROLE);

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
