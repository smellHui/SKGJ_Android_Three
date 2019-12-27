package com.tepia.main.model.jishu.feedback;

import com.tepia.base.http.BaseResponse;

import java.util.List;

/**
 * Author:xch
 * Date:2019/12/27
 * Description:
 */
public class FeedbackListResponse extends BaseResponse {

    private List<FeedbackDetailResponse> data;

    public List<FeedbackDetailResponse> getData() {
        return data;
    }

    public void setData(List<FeedbackDetailResponse> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "FeedbackListResponse{" +
                "data=" + data +
                '}';
    }
}
