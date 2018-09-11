package com.tepia.main.model.task.response;

import com.tepia.base.http.BaseResponse;
import com.tepia.main.model.task.bean.TaskBean;
import com.tepia.main.model.task.bean.TaskItemBean;

import java.util.ArrayList;

/**
 * Created by Joeshould on 2018/7/20.
 */

public class TaskItemDetailResponse extends BaseResponse {
    private TaskItemBean data;

    public TaskItemBean getData() {
        return data;
    }

    public void setData(TaskItemBean data) {
        this.data = data;
    }
}
