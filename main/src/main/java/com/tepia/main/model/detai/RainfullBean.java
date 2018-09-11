package com.tepia.main.model.detai;

import com.tepia.base.http.BaseResponse;

import java.io.Serializable;
import java.util.List;

/**
 * 雨量站
 * @author liying
 * @date 2018/7/26
 */

public class RainfullBean extends BaseResponse implements Serializable {

    /**
     * data : {"stcd":"60823648","stnm":"高视","lgtd":"106.71","lttd":"27.6","stlc":"鸭溪镇高枧村","addvcd":"520321118216000","dtmnm":"                ","sttp":"PP","frgrd":"","admauth":"播州区水务局","stbk":"","phcd":"      ","nt":"雨量监测站","stPptnRs":[{"stcd":"60823648","tm":"2018-07-11 00:00:00","drp":0},{"stcd":"60823648","tm":"2018-07-12 00:00:00","drp":0},{"stcd":"60823648","tm":"2018-07-13 00:00:00","drp":0},{"stcd":"60823648","tm":"2018-07-14 00:00:00","drp":0},{"stcd":"60823648","tm":"2018-07-15 00:00:00","drp":0}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * stcd : 60823648
         * stnm : 高视
         * lgtd : 106.71
         * lttd : 27.6
         * stlc : 鸭溪镇高枧村
         * addvcd : 520321118216000
         * dtmnm :
         * sttp : PP
         * frgrd :
         * admauth : 播州区水务局
         * stbk :
         * phcd :
         * nt : 雨量监测站
         * stPptnRs : [{"stcd":"60823648","tm":"2018-07-11 00:00:00"},{"stcd":"60823648","tm":"2018-07-12 00:00:00","drp":0},{"stcd":"60823648","tm":"2018-07-13 00:00:00","drp":0},{"stcd":"60823648","tm":"2018-07-14 00:00:00","drp":0},{"stcd":"60823648","tm":"2018-07-15 00:00:00","drp":0}]
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

        public List<StPptnRsBean> getStPptnRs() {
            return stPptnRs;
        }

        public void setStPptnRs(List<StPptnRsBean> stPptnRs) {
            this.stPptnRs = stPptnRs;
        }

        public static class StPptnRsBean {
            /**
             * stcd : 60823648
             * tm : 2018-07-11 00:00:00
             * drp : 0
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
