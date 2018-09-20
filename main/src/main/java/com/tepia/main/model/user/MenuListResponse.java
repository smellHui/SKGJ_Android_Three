package com.tepia.main.model.user;

import com.tepia.base.http.BaseResponse;
import com.tepia.main.view.MenuItemBean;

import java.util.ArrayList;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2018-09-20
 * Time            :       17:52
 * Version         :       1.0
 * 功能描述        :
 **/
public class MenuListResponse extends BaseResponse{
    public ArrayList<MenuItemBean> data;

    public ArrayList<MenuItemBean> getData() {
        return data;
    }

    public void setData(ArrayList<MenuItemBean> data) {
        this.data = data;
    }
}
