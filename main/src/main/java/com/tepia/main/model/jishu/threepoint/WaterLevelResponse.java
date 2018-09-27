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
     * data : {"pageNum":1,"pageSize":10,"size":10,"startRow":1,"endRow":10,"total":3650,"pages":365,"list":[{"stcd":"90001153","tm":"2018-04-17 13:30:00","rz":939.91,"w":158.7,"stnm":"东风水库"},{"stcd":"90001153","tm":"2018-04-17 13:15:00","rz":939.91,"w":158.7,"stnm":"东风水库"},{"stcd":"90001153","tm":"2018-04-17 13:00:00","rz":939.91,"w":158.7,"stnm":"东风水库"},{"stcd":"90001153","tm":"2018-04-17 12:45:00","rz":939.91,"w":158.7,"stnm":"东风水库"},{"stcd":"90001153","tm":"2018-04-17 12:30:00","rz":939.91,"w":158.7,"stnm":"东风水库"},{"stcd":"90001153","tm":"2018-04-17 12:15:00","rz":939.91,"w":158.7,"stnm":"东风水库"},{"stcd":"90001153","tm":"2018-04-17 12:00:00","rz":939.91,"w":158.7,"stnm":"东风水库"},{"stcd":"90001153","tm":"2018-04-17 11:45:00","rz":939.92,"w":159.067,"stnm":"东风水库"},{"stcd":"90001153","tm":"2018-04-17 11:30:00","rz":939.92,"w":159.067,"stnm":"东风水库"},{"stcd":"90001153","tm":"2018-04-17 11:15:00","rz":939.93,"w":159.433,"stnm":"东风水库"}],"prePage":0,"nextPage":2,"isFirstPage":true,"isLastPage":false,"hasPreviousPage":false,"hasNextPage":true,"navigatePages":8,"navigatepageNums":[1,2,3,4,5,6,7,8],"navigateFirstPage":1,"navigateLastPage":8,"lastPage":8,"firstPage":1}
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
         * total : 3650
         * pages : 365
         * list : [{"stcd":"90001153","tm":"2018-04-17 13:30:00","rz":939.91,"w":158.7,"stnm":"东风水库"},{"stcd":"90001153","tm":"2018-04-17 13:15:00","rz":939.91,"w":158.7,"stnm":"东风水库"},{"stcd":"90001153","tm":"2018-04-17 13:00:00","rz":939.91,"w":158.7,"stnm":"东风水库"},{"stcd":"90001153","tm":"2018-04-17 12:45:00","rz":939.91,"w":158.7,"stnm":"东风水库"},{"stcd":"90001153","tm":"2018-04-17 12:30:00","rz":939.91,"w":158.7,"stnm":"东风水库"},{"stcd":"90001153","tm":"2018-04-17 12:15:00","rz":939.91,"w":158.7,"stnm":"东风水库"},{"stcd":"90001153","tm":"2018-04-17 12:00:00","rz":939.91,"w":158.7,"stnm":"东风水库"},{"stcd":"90001153","tm":"2018-04-17 11:45:00","rz":939.92,"w":159.067,"stnm":"东风水库"},{"stcd":"90001153","tm":"2018-04-17 11:30:00","rz":939.92,"w":159.067,"stnm":"东风水库"},{"stcd":"90001153","tm":"2018-04-17 11:15:00","rz":939.93,"w":159.433,"stnm":"东风水库"}]
         * prePage : 0
         * nextPage : 2
         * isFirstPage : true
         * isLastPage : false
         * hasPreviousPage : false
         * hasNextPage : true
         * navigatePages : 8
         * navigatepageNums : [1,2,3,4,5,6,7,8]
         * navigateFirstPage : 1
         * navigateLastPage : 8
         * lastPage : 8
         * firstPage : 1
         */

        private int pageNum;
        private int pageSize;
        private int size;
        private int startRow;
        private int endRow;
        private int total;
        private int pages;
        private int prePage;
        private int nextPage;
        private boolean isFirstPage;
        private boolean isLastPage;
        private boolean hasPreviousPage;
        private boolean hasNextPage;
        private int navigatePages;
        private int navigateFirstPage;
        private int navigateLastPage;
        private int lastPage;
        private int firstPage;
        private List<ListBean> list;
        private List<Integer> navigatepageNums;

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

        public int getPrePage() {
            return prePage;
        }

        public void setPrePage(int prePage) {
            this.prePage = prePage;
        }

        public int getNextPage() {
            return nextPage;
        }

        public void setNextPage(int nextPage) {
            this.nextPage = nextPage;
        }

        public boolean isIsFirstPage() {
            return isFirstPage;
        }

        public void setIsFirstPage(boolean isFirstPage) {
            this.isFirstPage = isFirstPage;
        }

        public boolean isIsLastPage() {
            return isLastPage;
        }

        public void setIsLastPage(boolean isLastPage) {
            this.isLastPage = isLastPage;
        }

        public boolean isHasPreviousPage() {
            return hasPreviousPage;
        }

        public void setHasPreviousPage(boolean hasPreviousPage) {
            this.hasPreviousPage = hasPreviousPage;
        }

        public boolean isHasNextPage() {
            return hasNextPage;
        }

        public void setHasNextPage(boolean hasNextPage) {
            this.hasNextPage = hasNextPage;
        }

        public int getNavigatePages() {
            return navigatePages;
        }

        public void setNavigatePages(int navigatePages) {
            this.navigatePages = navigatePages;
        }

        public int getNavigateFirstPage() {
            return navigateFirstPage;
        }

        public void setNavigateFirstPage(int navigateFirstPage) {
            this.navigateFirstPage = navigateFirstPage;
        }

        public int getNavigateLastPage() {
            return navigateLastPage;
        }

        public void setNavigateLastPage(int navigateLastPage) {
            this.navigateLastPage = navigateLastPage;
        }

        public int getLastPage() {
            return lastPage;
        }

        public void setLastPage(int lastPage) {
            this.lastPage = lastPage;
        }

        public int getFirstPage() {
            return firstPage;
        }

        public void setFirstPage(int firstPage) {
            this.firstPage = firstPage;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public List<Integer> getNavigatepageNums() {
            return navigatepageNums;
        }

        public void setNavigatepageNums(List<Integer> navigatepageNums) {
            this.navigatepageNums = navigatepageNums;
        }

        public static class ListBean {
            /**
             * stcd : 90001153
             * tm : 2018-04-17 13:30:00
             * rz : 939.91
             * w : 158.7
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
