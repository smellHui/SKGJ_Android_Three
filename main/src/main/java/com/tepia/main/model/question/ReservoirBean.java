package com.tepia.main.model.question;

import com.tepia.base.http.BaseResponse;

import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 事件上报时接口调用水库实体
 * @author BLiYing
 * @date 2018/5/28
 */

public class ReservoirBean extends BaseResponse implements Serializable {


    private List<ReservoirDateBean> data;

    public List<ReservoirDateBean> getData() {
        return data;
    }



}
