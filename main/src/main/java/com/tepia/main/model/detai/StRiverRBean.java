package com.tepia.main.model.detai;

import com.tepia.base.http.BaseResponse;

import java.io.Serializable;
import java.util.List;

/**
 * 流量站
 * @author liying
 * @date 2018/7/24
 */

public class StRiverRBean extends BaseResponse implements Serializable{


    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {


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
        private List<StRiverRsBean> stRiverRs;

        private String areaName;
        private String reservoirName;

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

        public List<StRiverRsBean> getStRiverRs() {
            return stRiverRs;
        }

        public void setStRiverRs(List<StRiverRsBean> stRiverRs) {
            this.stRiverRs = stRiverRs;
        }

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public String getReservoirName() {
            return reservoirName;
        }

        public void setReservoirName(String reservoirName) {
            this.reservoirName = reservoirName;
        }

        public static class StRiverRsBean {
            /**
             * stcd : 00170415
             * tm : 2018-01-01 00:00:00
             * q : 958.08
             */

            private String stcd;
            private String tm;
            private double q;

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
        }
    }
}
