package com.tepia.main.model.worknotification;

import com.tepia.base.http.BaseResponse;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2018-09-30
 * Time            :       11:17
 * Version         :       1.0
 * 功能描述        :
 **/
public class WorkNoticeDetailResponse extends BaseResponse{
    WorkNoticeBean data;

    public WorkNoticeBean getData() {
        return data;
    }

    public void setData(WorkNoticeBean data) {
        this.data = data;
    }
}
