package com.tepia.main.model.detai;

import com.tepia.base.http.BaseResponse;

import java.util.List;

/**
 * 依据stcd查询
 * Created by      Intellij IDEA
 *
 * @author :       liying
 *         Date    :       2018-08-24
 *         Time    :       下午5:30
 *         Version :       1.0
 *         Company :       北京太比雅科技(武汉研发中心)
 **/

public class LiuliangDetailBean extends BaseResponse {

    /**
     * data : {"stcd":"00170405","stnm":"五株井水库","lgtd":"107.033611","lttd":"27.465556","stlc":"团溪镇福禄村","addvcd":"520321112207000","dtmnm":"                ","sttp":"ZQ","frgrd":"","admauth":"遵义灌区管理局","stbk":"","phcd":"      ","nt":"流量监测点","stRiverRs":[{"stcd":"00170405","tm":"2018-08-09 21:00:00","q":0,"syncflag":"1"}],"areaName":"播州区"}
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
         * stcd : 00170405
         * stnm : 五株井水库
         * lgtd : 107.033611
         * lttd : 27.465556
         * stlc : 团溪镇福禄村
         * addvcd : 520321112207000
         * dtmnm :
         * sttp : ZQ
         * frgrd :
         * admauth : 遵义灌区管理局
         * stbk :
         * phcd :
         * nt : 流量监测点
         * stRiverRs : [{"stcd":"00170405","tm":"2018-08-09 21:00:00","q":0,"syncflag":"1"}]
         * areaName : 播州区
         */

        private String stcd;
        private String stnm;
        private String lgtd;
        private String lttd;
        private String stlc;
        private String addvcd;
        private String dtmnm;
        private String sttp;
        private String frgrd;
        private String admauth;
        private String stbk;
        private String phcd;
        private String nt;
        private String areaName;
        private List<StRiverRsBean> stRiverRs;

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

        public String getDtmnm() {
            return dtmnm;
        }

        public void setDtmnm(String dtmnm) {
            this.dtmnm = dtmnm;
        }

        public String getSttp() {
            return sttp;
        }

        public void setSttp(String sttp) {
            this.sttp = sttp;
        }

        public String getFrgrd() {
            return frgrd;
        }

        public void setFrgrd(String frgrd) {
            this.frgrd = frgrd;
        }

        public String getAdmauth() {
            return admauth;
        }

        public void setAdmauth(String admauth) {
            this.admauth = admauth;
        }

        public String getStbk() {
            return stbk;
        }

        public void setStbk(String stbk) {
            this.stbk = stbk;
        }

        public String getPhcd() {
            return phcd;
        }

        public void setPhcd(String phcd) {
            this.phcd = phcd;
        }

        public String getNt() {
            return nt;
        }

        public void setNt(String nt) {
            this.nt = nt;
        }

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public List<StRiverRsBean> getStRiverRs() {
            return stRiverRs;
        }

        public void setStRiverRs(List<StRiverRsBean> stRiverRs) {
            this.stRiverRs = stRiverRs;
        }

        public static class StRiverRsBean {
            /**
             * stcd : 00170405
             * tm : 2018-08-09 21:00:00
             * q : 0
             * syncflag : 1
             */

            private String stcd;
            private String tm;
            private double q;
            private String syncflag;

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

            public double getQ() {
                return q;
            }

            public void setQ(double q) {
                this.q = q;
            }

            public String getSyncflag() {
                return syncflag;
            }

            public void setSyncflag(String syncflag) {
                this.syncflag = syncflag;
            }
        }
    }
}
