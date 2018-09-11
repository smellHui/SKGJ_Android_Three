package com.tepia.main.model.task.response;

import com.tepia.base.http.BaseResponse;
import com.tepia.main.model.task.bean.TaskBean;

import java.util.List;

/**
 * Created by Joeshould on 2018/5/23.
 */

public class TaskDetailResponse extends BaseResponse {

    /**

     * data : {"workOrderId":"2ea608d478894397a0fe5eeca6874bc1","workOrderCode":"XJ201807191505312468218","workOrderName":"风岩水库201807巡检","planStartTime":"2018-07-23 00:00:00","planEndTime":"2018-07-23 00:00:00","executeStatus":"1","bizPlanInfo":{"planType":"2","planName":"2018-07-20风岩水库日常巡检计划","operationType":"1"},"reservoirName":"风岩水库","bizReservoirWorkOrderItems":[{"itemId":"3a4e423658f0468db6c40ad22995e640","workOrderId":"2ea608d478894397a0fe5eeca6874bc1","positionId":"089b678b89e046f0a86cd85dd2fecdba","superviseItemCode":"SK-QSB-1-1","reservoirSuperviseSequence":1,"treeNames":"土石坝/坝体/坝坡","superviseItemName":"消防器材"}]}
     */

    private TaskBean data;



    public TaskBean getData() {
        return data;
    }

    public void setData(TaskBean data) {
        this.data = data;
    }


}
