package com.tepia.main.model.map;

import com.tepia.base.http.BaseResponse;

import java.util.List;

/**
 * 流量（ZQ）、水质（WQ）、雨量(PP)、水位(RR)、 图像(II)
 * @author 44822
 */
public class MapCommonResponse extends BaseResponse {

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

    public static class DataBean {
        /**
         * stcd : 00000001
         * stnm : 鸭溪镇政府水质监测点
         * lgtd : 106.671031
         * lttd : 27.582158
         */

        private String stcd;
        private String stnm;
        private String lgtd;
        private String lttd;

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
    }
}
