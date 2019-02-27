package com.tepia.main.model.jishu.threepoint;

import com.tepia.base.http.BaseResponse;

import java.util.List;

/**
  * Created by      Android studio
  *
  * @author :wwj (from Center Of Wuhan)
  * Date    :2019/2/13
  * Version :1.0
  * 功能描述 :  水情历史数据
 **/

public class WaterHistoryResponse extends BaseResponse {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * stcd : 90001153
         * tm : 2018-04-11 00:00
         * rz : 939.9
         * w : 158.699
         * stnm : 东风水库
         */

        private String stcd;
        private String tm;
        private double rz;
        private double w;
        private String stnm;

        public String getStcd() {
            return stcd;
        }

        public void setStcd(String stcd) {
            this.stcd = stcd;
        }

        public String getTm() {
            return tm;
        }

        public void setTm(String tm) {
            this.tm = tm;
        }

        public double getRz() {
            return rz;
        }

        public void setRz(double rz) {
            this.rz = rz;
        }

        public double getW() {
            return w;
        }

        public void setW(double w) {
            this.w = w;
        }

        public String getStnm() {
            return stnm;
        }

        public void setStnm(String stnm) {
            this.stnm = stnm;
        }
    }
}
