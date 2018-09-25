package com.tepia.main.model.jishu.yunwei;

import com.tepia.base.http.BaseResponse;

/**
 * Created by      Intellij IDEA
 *
 * @author :       wwj
 * Date    :       2018-09-25
 * Time    :       13:19
 * Version :       1.0
 * Company :       北京太比雅科技(武汉研发中心)
 **/
public class WorkOrderNumResponse extends BaseResponse{

    /**
     * code : 0
     * count : 0
     * data : {"doneNum":0,"totals":0}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * doneNum : 0
         * totals : 0
         */

        private int doneNum;
        private int totals;

        public int getDoneNum() {
            return doneNum;
        }

        public void setDoneNum(int doneNum) {
            this.doneNum = doneNum;
        }

        public int getTotals() {
            return totals;
        }

        public void setTotals(int totals) {
            this.totals = totals;
        }
    }
}
