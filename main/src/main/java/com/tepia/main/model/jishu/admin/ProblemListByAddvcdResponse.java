package com.tepia.main.model.jishu.admin;

import com.tepia.base.http.BaseResponse;

import java.util.List;

/**
  * Created by      Android studio
  *
  * @author :wwj (from Center Of Wuhan)
  * Date    :2018/12/28
  * Version :1.0
  * 功能描述 :  根据行政编码统计水库问题总数和处理完成数
 **/

public class ProblemListByAddvcdResponse extends BaseResponse {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * date : 2018-12
         * done_num : 0
         * non_num : 3
         * totals : 3
         * reservoirId : 3587532674c1418499e3f7372965af33
         * reservoirName : 大坑水库
         */

        private String date;
        private int done_num;
        private int non_num;
        private int totals;
        private String reservoirId;
        private String reservoirName;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getDone_num() {
            return done_num;
        }

        public void setDone_num(int done_num) {
            this.done_num = done_num;
        }

        public int getNon_num() {
            return non_num;
        }

        public void setNon_num(int non_num) {
            this.non_num = non_num;
        }

        public int getTotals() {
            return totals;
        }

        public void setTotals(int totals) {
            this.totals = totals;
        }

        public String getReservoirId() {
            return reservoirId;
        }

        public void setReservoirId(String reservoirId) {
            this.reservoirId = reservoirId;
        }

        public String getReservoirName() {
            return reservoirName;
        }

        public void setReservoirName(String reservoirName) {
            this.reservoirName = reservoirName;
        }
    }
}
