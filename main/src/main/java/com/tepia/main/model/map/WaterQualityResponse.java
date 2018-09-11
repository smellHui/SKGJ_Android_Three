package com.tepia.main.model.map;

import com.tepia.base.http.BaseResponse;

import java.io.Serializable;
import java.util.List;


/**
 * 水质站
 * @author 44822
 */
public class WaterQualityResponse extends BaseResponse implements Serializable{

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
         * stcd : 00000001
         * spt : 2018-07-11 01:39:47
         * wt : 26.7
         * ph : 8.46
         * turb : 0.107651
         * cl2 : 0.298591
         * dataType : 0
         * stStbprpB : {}
         * nh3n : 0.1083
         * cod : 15.2605
         */

        private String stcd;
        private String spt;
        private double wt;
        private double ph;
        private double turb;
        private double cl2;
        private String dataType;
        private StStbprpBBean stStbprpB;
        private double nh3n;
        private double cod;

        public String getStcd() {
            return stcd;
        }

        public void setStcd(String stcd) {
            this.stcd = stcd;
        }

        public String getSpt() {
            return spt;
        }

        public void setSpt(String spt) {
            this.spt = spt;
        }

        public double getWt() {
            return wt;
        }

        public void setWt(double wt) {
            this.wt = wt;
        }

        public double getPh() {
            return ph;
        }

        public void setPh(double ph) {
            this.ph = ph;
        }

        public double getTurb() {
            return turb;
        }

        public void setTurb(double turb) {
            this.turb = turb;
        }

        public double getCl2() {
            return cl2;
        }

        public void setCl2(double cl2) {
            this.cl2 = cl2;
        }

        public String getDataType() {
            return dataType;
        }

        public void setDataType(String dataType) {
            this.dataType = dataType;
        }

        public StStbprpBBean getStStbprpB() {
            return stStbprpB;
        }

        public void setStStbprpB(StStbprpBBean stStbprpB) {
            this.stStbprpB = stStbprpB;
        }

        public double getNh3n() {
            return nh3n;
        }

        public void setNh3n(double nh3n) {
            this.nh3n = nh3n;
        }

        public double getCod() {
            return cod;
        }

        public void setCod(double cod) {
            this.cod = cod;
        }

        public static class StStbprpBBean {
            /**
             * stcd : 00000001
             * stnm : 鸭溪镇政府水质监测点
             * lgtd : 106.671031
             * lttd : 27.582158
             * stlc : 鸭溪镇雷泉社区
             * addvcd : 520321118297000
             * sttp : WQ
             * admauth : 播州区水务局
             */

            private String stcd;
            private String stnm;
            private String lgtd;
            private String lttd;
            private String stlc;
            private String addvcd;
            private String sttp;
            private String admauth;

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
        }
    }
}
