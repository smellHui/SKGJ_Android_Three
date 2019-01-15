package com.tepia.main.model.reserviros;

import com.tepia.base.http.BaseResponse;

/**
 * Created by      android studio
 *
 * @author :       ly
 * Date            :       2019-01-15
 * Time            :       上午10:05
 * Version         :       1.0
 * location        :       武汉研发中心
 * 功能描述         :       查询水库汛限水位实体
 **/
public class CurrentFloodSeasonBean extends BaseResponse {

    /**
     * data : 255.0
     */

    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
