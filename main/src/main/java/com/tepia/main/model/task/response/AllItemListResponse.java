package com.tepia.main.model.task.response;

import com.tepia.base.http.BaseResponse;
import com.tepia.main.model.task.bean.TaskItemBean;

import java.util.List;

public class AllItemListResponse extends BaseResponse{

    private List<TaskItemBean> data;

    public List<TaskItemBean> getData() {
        return data;
    }

    public void setData(List<TaskItemBean> data) {
        this.data = data;
    }

}
