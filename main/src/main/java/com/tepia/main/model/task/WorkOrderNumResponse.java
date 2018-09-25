package com.tepia.main.model.task;

import com.tepia.base.http.BaseResponse;
import com.tepia.main.model.task.bean.WorkOrderNumBean;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2018-09-25
 * Time            :       11:30
 * Version         :       1.0
 * 功能描述        :
 **/
public class WorkOrderNumResponse extends BaseResponse{

    /**
     * data : {"doneNum":0,"totals":0}
     */

    private WorkOrderNumBean data;

    public WorkOrderNumBean getData() {
        return data;
    }

    public void setData(WorkOrderNumBean data) {
        this.data = data;
    }


}
