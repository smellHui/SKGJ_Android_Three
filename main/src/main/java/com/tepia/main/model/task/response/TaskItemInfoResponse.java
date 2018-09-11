package com.tepia.main.model.task.response;

import com.tepia.main.model.task.bean.PositionNamesBean;
import com.tepia.main.model.task.bean.TaskItemBean;

import java.util.List;

/**
 * Created by Joeshould on 2018/5/24.
 */

public class TaskItemInfoResponse {



    /**
     * item : {"id":"27664fe7-3c50-442f-b6db-91f1f15167b3","orderNum":1,"executeSituation":0,"executeName":"","isOver":"hide","positionName":"坝顶","itemName":"有无裂缝、异常变形、积水或杂草丛生等现象","content":"","time":"","routingItemResult":1,"showType":[{"name":"无","value":"1","checked":true},{"name":"有","value":"0","checked":false}],"resultType":"1,2,3","positionNames":[{"title":"清底河水库"},{"title":"坝体"},{"title":"坝顶"}],"resultTypes":[{"title":"选"},{"title":"文"},{"title":"图"}],"hasChoice":true,"hasText":true,"hasImgs":true,"pItemId":"","nItemId":"a6385abb-faed-456d-b615-35052458afb3"}
     * status : true
     * msg : 获取数据成功
     */

    private TaskItemBean item;
    private boolean status;
    private String msg;

    public TaskItemBean getItem() {
        return item;
    }

    public void setItem(TaskItemBean item) {
        this.item = item;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
