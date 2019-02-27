package com.tepia.main.model.jishu.threepoint;

import com.tepia.base.http.BaseResponse;

import java.util.List;

/**
  * Created by      Android studio
  *
  * @author :wwj (from Center Of Wuhan)
  * Date    :2019/2/13
  * Version :1.0
  * 功能描述 :  雨情历史数据
 **/

public class RainHistoryResponse extends BaseResponse {


    /**
     * data : {"stcd":"60823798","stnm":"石关","lgtd":"106.39","lttd":"27.53","stlc":"泮水镇青丰村","addvcd":"520321122200000","sttp":"PP","admauth":"播州区水务局","stPptnRs":[{"stcd":"60823798","tm":"2018-08-09 00:00","drp":0},{"stcd":"60823798","tm":"2018-08-10 00:00","drp":0},{"stcd":"60823798","tm":"2018-08-11 00:00","drp":0},{"stcd":"60823798","tm":"2018-08-12 00:00","drp":0},{"stcd":"60823798","tm":"2018-08-13 00:00","drp":0},{"stcd":"60823798","tm":"2018-08-14 00:00","drp":0},{"stcd":"60823798","tm":"2018-08-15 00:00","drp":0},{"stcd":"60823798","tm":"2018-08-16 00:00","drp":0}]}
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
         * stcd : 60823798
         * stnm : 石关
         * lgtd : 106.39
         * lttd : 27.53
         * stlc : 泮水镇青丰村
         * addvcd : 520321122200000
         * sttp : PP
         * admauth : 播州区水务局
         * stPptnRs : [{"stcd":"60823798","tm":"2018-08-09 00:00","drp":0},{"stcd":"60823798","tm":"2018-08-10 00:00","drp":0},{"stcd":"60823798","tm":"2018-08-11 00:00","drp":0},{"stcd":"60823798","tm":"2018-08-12 00:00","drp":0},{"stcd":"60823798","tm":"2018-08-13 00:00","drp":0},{"stcd":"60823798","tm":"2018-08-14 00:00","drp":0},{"stcd":"60823798","tm":"2018-08-15 00:00","drp":0},{"stcd":"60823798","tm":"2018-08-16 00:00","drp":0}]
         */

        private String stcd;
        private String stnm;
        private String lgtd;
        private String lttd;
        private String stlc;
        private String addvcd;
        private String sttp;
        private String admauth;
        private List<StPptnRsBean> stPptnRs;

        public String getStcd() {
            return stcd;
        }

        public void setStcd(String stcd) {
            this.stcd = stcd;
        }

        public String getStnm() {
            return stnm;
        }

        public void setStnm(String stnm) {
            this.stnm = stnm;
        }

        public String getLgtd() {
            return lgtd;
        }

        public void setLgtd(String lgtd) {
            this.lgtd = lgtd;
        }

        public String getLttd() {
            return lttd;
        }

        public void setLttd(String lttd) {
            this.lttd = lttd;
        }

        public String getStlc() {
            return stlc;
        }

        public void setStlc(String stlc) {
            this.stlc = stlc;
        }

        public String getAddvcd() {
            return addvcd;
        }

        public void setAddvcd(String addvcd) {
            this.addvcd = addvcd;
        }

        public String getSttp() {
            return sttp;
        }

        public void setSttp(String sttp) {
            this.sttp = sttp;
        }

        public String getAdmauth() {
            return admauth;
        }

        public void setAdmauth(String admauth) {
            this.admauth = admauth;
        }

        public List<StPptnRsBean> getStPptnRs() {
            return stPptnRs;
        }

        public void setStPptnRs(List<StPptnRsBean> stPptnRs) {
            this.stPptnRs = stPptnRs;
        }

        public static class StPptnRsBean {
            /**
             * stcd : 60823798
             * tm : 2018-08-09 00:00
             * drp : 0.0
             */

            private String stcd;
            private String tm;
            private double drp;

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

            public double getDrp() {
                return drp;
            }

            public void setDrp(double drp) {
                this.drp = drp;
            }
        }
    }
}
