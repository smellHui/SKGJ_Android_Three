package com.tepia.main.model.task;

import com.tepia.base.http.BaseResponse;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2018-09-27
 * Time            :       14:43
 * Version         :       1.0
 * 功能描述        :
 **/
public class UnfinishedNumResponse extends BaseResponse {

    /**
     * data : {"work_order_id":"bad8c0f2f74848baa82d2b9fa7810d70","totals":1}
     */

    private DataBean data = new DataBean();

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * work_order_id : bad8c0f2f74848baa82d2b9fa7810d70
         * totals : 1
         */

        private String work_order_id;
        private int totals;

        public String getWork_order_id() {
            return work_order_id;
        }

        public void setWork_order_id(String work_order_id) {
            this.work_order_id = work_order_id;
        }

        public int getTotals() {
            return totals;
        }

        public void setTotals(int totals) {
            this.totals = totals;
        }
    }
}
