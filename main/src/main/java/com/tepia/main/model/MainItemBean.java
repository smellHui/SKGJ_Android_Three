package com.tepia.main.model;

/**
 * 首页图标item
 * Created by ly on 2018/7/10.
 */

public class MainItemBean {

    private String num;
    private Integer iconRes;
    private String iconName;
    private String menuHref;

    public MainItemBean() {
    }

    public MainItemBean(int icon_news, String iconName) {
//        super(icon_news,iconName);
        this.iconRes = icon_news;
        this.iconName = iconName;
    }

    public MainItemBean(String num, Integer iconRes, String iconName) {
        this.num = num;
        this.iconRes = iconRes;
        this.iconName = iconName;
    }

    public MainItemBean(String num, Integer iconRes, String iconName,String menuHref) {
        this.num = num;
        this.iconRes = iconRes;
        this.iconName = iconName;
        this.menuHref = menuHref;
    }

    public Integer getIconRes() {
        return iconRes;
    }

    public void setIconRes(Integer iconRes) {
        this.iconRes = iconRes;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getNum() {
        return num;
    }

    public String getMenuHref() {
        return menuHref;
    }

    public void setMenuHref(String menuHref) {
        this.menuHref = menuHref;
    }
}
