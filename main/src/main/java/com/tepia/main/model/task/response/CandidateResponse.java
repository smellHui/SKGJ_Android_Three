package com.tepia.main.model.task.response;

import com.tepia.base.http.BaseResponse;
import com.tepia.main.model.task.bean.PeopleBean;

import java.util.List;

public class CandidateResponse extends BaseResponse {

    private List<PeopleBean> data;

    public List<PeopleBean> getData() {
        return data;
    }

    public void setData(List<PeopleBean> data) {
        this.data = data;
    }

}
