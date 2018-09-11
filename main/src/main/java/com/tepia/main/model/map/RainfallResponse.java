package com.tepia.main.model.map;

import com.tepia.base.http.BaseResponse;

import java.io.Serializable;
import java.util.List;

/**
 * 所有雨量站
 */
public class RainfallResponse extends BaseResponse implements Serializable{

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
         * stcd : 60524528
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
             * stcd : 60524528
             * stnm : 青坑
             * lgtd : 107.23
             * lttd : 27.47
             * stlc : 铁厂镇青坑村
             * addvcd : 520321113203000
             * dtmnm :
             * sttp : PP
             * frgrd :
             * admauth : 播州区水务局
             * stbk :
             * phcd :
             * nt : 雨量监测站
             * areaName : 遵义县
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
        }
    }
}
