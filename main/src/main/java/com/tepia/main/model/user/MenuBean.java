package com.tepia.main.model.user;

import com.tepia.base.http.BaseResponse;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.util.List;

/**
 * Created by      Intellij IDEA
 *
 * @author :       liying
 *         Date    :       2018-08-28
 *         Time    :       下午4:02
 *         Version :       1.0
 *         Company :       北京太比雅科技(武汉研发中心)
 **/

public class MenuBean extends BaseResponse implements Serializable {

    private List<MenuData> data;

    public List<MenuData> getData() {
        return data;
    }

    public void setData(List<MenuData> data) {
        this.data = data;
    }


}
