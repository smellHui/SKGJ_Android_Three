package com.tepia.main.model.detai;

import com.tepia.base.http.BaseResponse;

import java.util.List;

/**
 * 水质站stcd查询后bean
 * Created by      Intellij IDEA
 *
 * @author :       liying
 *         Date    :       2018-08-24
 *         Time    :       下午3:27
 *         Version :       1.0
 *         Company :       北京太比雅科技(武汉研发中心)
 **/

public class WaterQualityDetailBean extends BaseResponse{

    /**
     * data : {"stcd":"00000001","stnm":"鸭溪镇政府","lgtd":"106.671031","lttd":"27.582158","stlc":"鸭溪镇雷泉社区","addvcd":"520321118297000","sttp":"WQ","admauth":"播州区水务局","nt":"水质监测点","stWqRS":[{"stcd":"00000001","spt":"2018-08-09 21:18:33","wt":26.7,"ph":8.41,"turb":0.119368,"cl2":0.277728,"dataType":"0"}],"areaName":"播州区"}
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
         * stcd : 00000001
         * stnm : 鸭溪镇政府
         * lgtd : 106.671031
         * lttd : 27.582158
         * stlc : 鸭溪镇雷泉社区
         * addvcd : 520321118297000
         * sttp : WQ
         * admauth : 播州区水务局
         * nt : 水质监测点
         * stWqRS : [{"stcd":"00000001","spt":"2018-08-09 21:18:33","wt":26.7,"ph":8.41,"turb":0.119368,"cl2":0.277728,"dataType":"0"}]
         * areaName : 播州区
         */

        private String stcd;
        private String stnm;
        private String lgtd;
        private String lttd;
        private String stlc;
        private String addvcd;
        private String sttp;
        private String admauth;
        private String nt;
        private String areaName;
        private List<StWqRSBean> stWqRS;

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

        public List<StWqRSBean> getStWqRS() {
            return stWqRS;
        }

        public void setStWqRS(List<StWqRSBean> stWqRS) {
            this.stWqRS = stWqRS;
        }

        public static class StWqRSBean {
            /**
             * stcd : 00000001
             * spt : 2018-08-09 21:18:33
             * wt : 26.7
             * ph : 8.41
             * turb : 0.119368
             * cl2 : 0.277728
             * dataType : 0
             */

            private String stcd;
            private String spt;
            private double wt;
            private double ph;
            private double turb;
            private double cl2;
            private String dataType;

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
        }
    }
}
