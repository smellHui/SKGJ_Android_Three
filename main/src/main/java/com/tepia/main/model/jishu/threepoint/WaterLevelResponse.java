package com.tepia.main.model.jishu.threepoint;

import com.tepia.base.http.BaseResponse;

import java.util.List;

/**
 * Created by      Intellij IDEA
 *  水情
 * @author :       wwj
 * Date    :       2018-09-26
 * Time    :       15:01
 * Version :       1.0
 * Company :       北京太比雅科技(武汉研发中心)
 **/
public class WaterLevelResponse extends BaseResponse{


    /**
     * data : {"pageNum":1,"pageSize":10,"size":10,"startRow":1,"endRow":10,"total":3653,"pages":366,"list":[{"stcd":"90001153","tm":"2018-09-28 14:46:00","rz":1111,"stnm":"东风水库"}]}
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
         * pageNum : 1
         * pageSize : 10
         * size : 10
         * startRow : 1
         * endRow : 10
         * total : 3653
         * pages : 366
         * list : [{"stcd":"90001153","tm":"2018-09-28 14:46:00","rz":1111,"stnm":"东风水库"}]
         */

        private int pageNum;
        private int pageSize;
        private int size;
        private int startRow;
        private int endRow;
        private int total;
        private int pages;
        private List<ListBean> list;

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getStartRow() {
            return startRow;
        }

        public void setStartRow(int startRow) {
            this.startRow = startRow;
        }

        public int getEndRow() {
            return endRow;
        }

        public void setEndRow(int endRow) {
            this.endRow = endRow;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * stcd : 90001153
             * tm : 2018-09-28 14:46:00
             * rz : 1111
             * stnm : 东风水库
             */

            private String stcd;
            private String tm;
            private double rz;
            private double w;
            private String stnm;

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

            public String getStnm() {
                return stnm;
            }

            public void setStnm(String stnm) {
                this.stnm = stnm;
            }
        }
    }
}
