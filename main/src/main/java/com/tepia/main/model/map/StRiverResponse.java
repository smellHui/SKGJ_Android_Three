package com.tepia.main.model.map;

import com.tepia.base.http.BaseResponse;

import java.util.List;


/**
 * 所有流量监测点
 * @author 44822
 */
public class StRiverResponse extends BaseResponse{



    /**
     * code : 0
     * count : 0
     * data : []
     **/

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * stcd : 00170407
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

        public static class StStbprpBBean {
            /**
             * stcd : 00170407
             * stnm : 西坪水厂
             * lgtd : 107.222222
             * lttd : 27.524722
             * stlc : 西坪镇居委会
             * addvcd : 520321114001000
             * dtmnm :
             * sttp : ZQ
             * frgrd :
             * admauth : 遵义灌区管理局
             * stbk :
             * phcd :
             * nt : 流量监测点
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
            /**
             * 新增 on 2018/8/16
             */
            private List<StRiverRsBean> stRiverRs;


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

            public static class StRiverRsBean {
                /**
                 * stcd : 00005033
                 * tm : 2018-08-09 21:00:00
                 * q : 0
                 * syncflag : 1
                 */

                private String stcd;
                private String tm;
                private int q;
                private String syncflag;

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

                public int getQ() {
                    return q;
                }

                public void setQ(int q) {
                    this.q = q;
                }

                public String getSyncflag() {
                    return syncflag;
                }

                public void setSyncflag(String syncflag) {
                    this.syncflag = syncflag;
                }
            }
        }
    }
}
