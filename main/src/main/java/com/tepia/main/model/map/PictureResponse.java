package com.tepia.main.model.map;

import com.tepia.base.http.BaseResponse;

import java.io.Serializable;
import java.util.List;

/**
 * 图像站
 * @author 44822
 */
public class PictureResponse extends BaseResponse{

    /**
     * code : 0
     * count : 0
     * data : {}
     * */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * fileServerUrl : http://119.1.151.132:3003/
         * stPictureRs : []
         * */

        private String fileServerUrl;
        private List<StPictureRsBean> stPictureRs;

        public String getFileServerUrl() {
            return fileServerUrl;
        }

        public void setFileServerUrl(String fileServerUrl) {
            this.fileServerUrl = fileServerUrl;
        }

        public List<StPictureRsBean> getStPictureRs() {
            return stPictureRs;
        }

        public void setStPictureRs(List<StPictureRsBean> stPictureRs) {
            this.stPictureRs = stPictureRs;
        }

        public static class StPictureRsBean implements Serializable{
            /**
             * stcd : 10170406
             * stnm : 五株井水库（二）
             * lgtd : 107.035556
             * lttd : 27.4675
             * stlc : 团溪镇福禄村
             * addvcd : 520321112207000
             * dtmnm :
             * sttp : II
             * frgrd :
             * admauth : 播州区水务局
             * stbk :
             * phcd :
             * stPictureRS : []
             * */

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

            public List<StPictureRSBean> getStPictureRS() {
                return stPictureRS;
            }

            public void setStPictureRS(List<StPictureRSBean> stPictureRS) {
                this.stPictureRS = stPictureRS;
            }

            public static class StPictureRSBean implements Serializable{
                /**
                 * stcd : 10170406
                 * tm : 2018-07-10 16:20:45
                 * pictm : 2018-07-10 16:00:00
                 * picpath : 20180710\0000170406-01-03-180710160000.jpg
                 */

                private String stcd;
                private String tm;
                private String pictm;
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

                public String getPictm() {
                    return pictm;
                }

                public void setPictm(String pictm) {
                    this.pictm = pictm;
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
}
