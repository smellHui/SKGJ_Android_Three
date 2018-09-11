package com.tepia.main.model.detai;

import com.tepia.base.http.BaseResponse;

import java.io.Serializable;
import java.util.List;

/**
 * 水位站
 * @author liying
 * @date 2018/7/27
 */

public class WaterlevelBean extends BaseResponse implements Serializable{

    /**
     * data : {"stcd":"90003650","stnm":"八幅堰水库主坝","lgtd":"106.817","lttd":"27.448611","stlc":"苟江镇天明村","addvcd":"520321103203000","dtmnm":"                ","sttp":"RR","frgrd":"","admauth":"播州区水务局","stbk":"","phcd":"      ","nt":"水位监测点","stRsvrRS":[{"stcd":"90003650","tm":"2018-05-05 00:00:00","rz":902.0867,"w":24.92},{"stcd":"90003650","tm":"2018-05-06 00:00:00","rz":902.0963,"w":23.407017}]}
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
         * stcd : 90003650
         * stnm : 八幅堰水库主坝
         * lgtd : 106.817
         * lttd : 27.448611
         * stlc : 苟江镇天明村
         * addvcd : 520321103203000
         * dtmnm :
         * sttp : RR
         * frgrd :
         * admauth : 播州区水务局
         * stbk :
         * phcd :
         * nt : 水位监测点
         * stRsvrRS : [{"stcd":"90003650","tm":"2018-05-05 00:00:00","rz":902.0867,"w":24.92},{"stcd":"90003650","tm":"2018-05-06 00:00:00","rz":902.0963,"w":23.407017}]
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
        private String reservoirName;
        private List<StRsvrRSBean> stRsvrRS;

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

        public List<StRsvrRSBean> getStRsvrRS() {
            return stRsvrRS;
        }

        public void setStRsvrRS(List<StRsvrRSBean> stRsvrRS) {
            this.stRsvrRS = stRsvrRS;
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

        public static class StRsvrRSBean implements Serializable{
            /**
             * stcd : 90003650
             * tm : 2018-05-05 00:00:00
             * rz : 902.0867
             * w : 24.92
             */

            private String stcd;
            private String tm;
            private double rz;
            private double w;

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
        }
    }
}
