package com.tepia.main.model.detai;

import com.tepia.base.http.BaseResponse;

import java.io.Serializable;
import java.util.List;

/**
 * 水质站详情
 *
 * @author liying
 * @date 2018/7/25
 */

public class WaterPhBean extends BaseResponse implements Serializable{

    /**
     * code : 0
     * count : 0
     * {"stcd":"00000001","spt":"2018-07-11 00:00:46","wt":27.5,"ph":8.41,"turb":0.104654,"cl2":0.282306,"dataType":"0"}
     */

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
         * spt : 2018-07-11 00:00:46
         * wt : 27.5
         * ph : 8.41
         * turb : 0.104654
         * cl2 : 0.282306
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
