package com.tepia.main.model.map;

import com.tepia.base.http.BaseResponse;
import com.tepia.main.model.detai.ReservoirBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 水库列表
 * @author 44822
 */
public class ReservoirListResponse extends BaseResponse {

    /**
     * code : 0
     * count : 0
     * data : []
     * */

    private ArrayList<ReservoirBean> data;

    public ArrayList<ReservoirBean> getData() {
        return data;
    }

    public void setData(ArrayList<ReservoirBean> data) {
        this.data = data;
    }

}
