package com.tepia.main.model.reserviros;

import com.tepia.base.http.BaseResponse;
import com.tepia.main.model.user.homepageinfo.HomeGetReservoirInfoBean;

import java.util.List;

/**
 * Created by      android studio
 *
 * @author :       ly
 * Date            :       2018-09-25
 * Time            :       下午3:46
 * Version         :       1.0
 * location        :       武汉研发中心
 * 功能描述         :       库容曲线实体
 **/
public class CapacityBean extends BaseResponse {


    private List<HomeGetReservoirInfoBean.StorageCapacityBean> data;

    public List<HomeGetReservoirInfoBean.StorageCapacityBean> getData() {
        return data;
    }

    public void setData(List<HomeGetReservoirInfoBean.StorageCapacityBean> data) {
        this.data = data;
    }


}
