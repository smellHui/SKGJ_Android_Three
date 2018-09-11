package com.tepia.main.model.user;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by      Intellij IDEA
 *
 * @author :       liying
 *         Date    :       2018-08-28
 *         Time    :       下午5:28
 *         Version :       1.0
 *         Company :       北京太比雅科技(武汉研发中心)
 **/

public class ChildrenBean implements Serializable {
    /**
     * expand : false
     * disabled : false
     * disableCheckbox : false
     * selected : false
     * checked : false
     * menuCode : 4698fe2a2c804b60948874a8447b4413
     * parentCode : db8a35f75d9547dfa8274adb51b83e97
     * menuName : 事件上报
     * menuType : 1
     * menuHref : /app/question
     * menuIcon : 21
     */

    private String menuCode;
    private String parentCode;
    private String menuName;
    private String menuType;
    private String menuHref;
    private String menuIcon;

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getMenuHref() {
        return menuHref;
    }

    public void setMenuHref(String menuHref) {
        this.menuHref = menuHref;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

}
