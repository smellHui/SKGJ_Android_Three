package com.tepia.main.model.map;

import com.tepia.base.http.BaseResponse;

import java.io.Serializable;
import java.util.List;

/**
 * 所有水位监测站
 * @author 44822
 */
public class WaterLevelResponse extends BaseResponse{

    /**
     * code : 0
     * count : 0
     * data : []
     * */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * stcd : 90003700
         * stStbprpB : {}
         * */

        private String stcd;
        private StStbprpBBean stStbprpB;

        public String getStcd() {
            return stcd;
        }

        public void setStcd(String stcd) {
            this.stcd = stcd;
        }

        public StStbprpBBean getStStbprpB() {
            return stStbprpB;
        }

        public void setStStbprpB(StStbprpBBean stStbprpB) {
            this.stStbprpB = stStbprpB;
        }

        public static class StStbprpBBean implements Serializable{
            /**
             * stcd : 90003700
             * stnm : 永红水库
             * lgtd : 106.795
             * lttd : 27.598611
             * stlc : 龙坑街道办事处八里社区
             * addvcd : 520321804298000
             * dtmnm :
             * sttp : RR
             * frgrd :
             * admauth : 播州区水务局
             * stbk :
             * phcd :
             * nt : 水位监测点
             * areaName : 遵义县
             * reservoirName : 永红水库
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

            public String getReservoirName() {
                return reservoirName;
            }

            public void setReservoirName(String reservoirName) {
                this.reservoirName = reservoirName;
            }
        }
    }
}
