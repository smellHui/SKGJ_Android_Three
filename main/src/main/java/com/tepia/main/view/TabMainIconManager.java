package com.tepia.main.view;

import com.tepia.main.ConfigConsts;
import com.tepia.main.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2018-09-19
 * Time            :       20:09
 * Version         :       1.0
 * 功能描述        :
 **/
public class TabMainIconManager {
    private static final TabMainIconManager ourInstance = new TabMainIconManager();

    private Map<String,Integer> imageIconMap ;
    public static TabMainIconManager getInstance() {
        return ourInstance;
    }

    private TabMainIconManager() {
        imageIconMap = new HashMap<>();
        imageIconMap.put("100", R.drawable.selector_tabbar_home_page);
        imageIconMap.put("110", R.drawable.selector_tabbar_yunwei);
        imageIconMap.put("120", R.drawable.selector_tabbar_report);
        imageIconMap.put("030", R.drawable.selector_tabbar_shuiku);
        imageIconMap.put("040", R.drawable.selector_tabbar_mine);
        imageIconMap.put("111", R.drawable.selector_tabbar_yunwei);
        imageIconMap.put("112", R.drawable.selector_tabbar_yunwei);
        imageIconMap.put("113", R.drawable.selector_tabbar_yunwei);
        imageIconMap.put("200", R.drawable.selector_tabbar_home_page);
        imageIconMap.put(ConfigConsts.TECHNOLOGRROLE, R.drawable.selector_tabbar_yunwei);
        imageIconMap.put("220", R.drawable.selector_tabbar_threepoint);
        imageIconMap.put("300", R.drawable.selector_tabbar_home_page);
        imageIconMap.put("310", R.drawable.selector_tabbar_yunwei);
        imageIconMap.put("320", R.drawable.selector_tabbar_threepoint);
        //综合监控
        imageIconMap.put("321", R.drawable.selector_tabbar_look);
    }

    public Integer getIcon(String menuIcon) {
        return imageIconMap.get(menuIcon);
    }
}
