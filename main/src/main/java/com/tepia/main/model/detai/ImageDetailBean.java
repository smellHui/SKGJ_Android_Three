package com.tepia.main.model.detai;

import com.tepia.base.http.BaseResponse;

import java.util.List;

/**
 * 图像站stcd
 * Created by      Intellij IDEA
 *
 * @author :       liying
 *         Date    :       2018-08-24
 *         Time    :       下午8:36
 *         Version :       1.0
 *         Company :       北京太比雅科技(武汉研发中心)
 **/

public class ImageDetailBean extends BaseResponse{

    /**
     * data : {"stcd":"10170401","stnm":"安福庄水库","lgtd":"107.104151","lttd":"27.403797","stlc":"茅栗镇富兴村","addvcd":"520321","dtmnm":"                ","sttp":"II","frgrd":"","admauth":"播州区水务局","stbk":"","phcd":"      ","nt":"图像监测站","stPictureRS":[{"stcd":"10170401","tm":"2018-08-09 20:18:53","picpath":"http://119.1.151.132:3003/20180809\\0000170401-01-03-180809200000.jpg"}],"areaName":"播州区"}
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
         * stcd : 10170401
         * stnm : 安福庄水库
         * lgtd : 107.104151
         * lttd : 27.403797
         * stlc : 茅栗镇富兴村
         * addvcd : 520321
         * dtmnm :
         * sttp : II
         * frgrd :
         * admauth : 播州区水务局
         * stbk :
         * phcd :
         * nt : 图像监测站
         * stPictureRS : [{"stcd":"10170401","tm":"2018-08-09 20:18:53","picpath":"http://119.1.151.132:3003/20180809\\0000170401-01-03-180809200000.jpg"}]
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
        private List<StPictureRSBean> stPictureRS;

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

        public List<StPictureRSBean> getStPictureRS() {
            return stPictureRS;
        }

        public void setStPictureRS(List<StPictureRSBean> stPictureRS) {
            this.stPictureRS = stPictureRS;
        }

        public static class StPictureRSBean {
            /**
             * stcd : 10170401
             * tm : 2018-08-09 20:18:53
             * picpath : http://119.1.151.132:3003/20180809\0000170401-01-03-180809200000.jpg
             */

            private String stcd;
            private String tm;
            private String picpath;

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

            public String getPicpath() {
                return picpath;
            }

            public void setPicpath(String picpath) {
                this.picpath = picpath;
            }
        }
    }
}
