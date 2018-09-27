package com.tepia.main.model.jishu.threepoint;

import com.tepia.base.http.BaseResponse;

import java.util.List;

/**
 * Created by      Intellij IDEA
 *  雨情列表
 * @author :       wwj
 * Date    :       2018-09-26
 * Time    :       14:25
 * Version :       1.0
 * Company :       北京太比雅科技(武汉研发中心)
 **/
public class RainConditionResponse extends BaseResponse{

    /**
     * data : {"pageNum":1,"pageSize":10,"size":10,"startRow":1,"endRow":10,"total":6126,"pages":613,"list":[{"stcd":"60823798","tm":"2018-08-09 19:00:00","drp":0,"stnm":"石关"},{"stcd":"60823798","tm":"2018-08-09 18:00:00","drp":0,"stnm":"石关"},{"stcd":"60823798","tm":"2018-08-09 17:00:00","drp":0,"stnm":"石关"},{"stcd":"60823798","tm":"2018-08-09 16:00:00","drp":0,"stnm":"石关"},{"stcd":"60823798","tm":"2018-08-09 15:00:00","drp":0,"stnm":"石关"},{"stcd":"60823798","tm":"2018-08-09 14:00:00","drp":0,"stnm":"石关"},{"stcd":"60823798","tm":"2018-08-09 13:00:00","drp":0,"stnm":"石关"},{"stcd":"60823798","tm":"2018-08-09 12:00:00","drp":0,"stnm":"石关"},{"stcd":"60823798","tm":"2018-08-09 11:00:00","drp":0,"stnm":"石关"},{"stcd":"60823798","tm":"2018-08-09 10:00:00","drp":0,"stnm":"石关"}],"prePage":0,"nextPage":2,"isFirstPage":true,"isLastPage":false,"hasPreviousPage":false,"hasNextPage":true,"navigatePages":8,"navigatepageNums":[1,2,3,4,5,6,7,8],"navigateFirstPage":1,"navigateLastPage":8,"lastPage":8,"firstPage":1}
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
         * total : 6126
         * pages : 613
         * list : [{"stcd":"60823798","tm":"2018-08-09 19:00:00","drp":0,"stnm":"石关"},{"stcd":"60823798","tm":"2018-08-09 18:00:00","drp":0,"stnm":"石关"},{"stcd":"60823798","tm":"2018-08-09 17:00:00","drp":0,"stnm":"石关"},{"stcd":"60823798","tm":"2018-08-09 16:00:00","drp":0,"stnm":"石关"},{"stcd":"60823798","tm":"2018-08-09 15:00:00","drp":0,"stnm":"石关"},{"stcd":"60823798","tm":"2018-08-09 14:00:00","drp":0,"stnm":"石关"},{"stcd":"60823798","tm":"2018-08-09 13:00:00","drp":0,"stnm":"石关"},{"stcd":"60823798","tm":"2018-08-09 12:00:00","drp":0,"stnm":"石关"},{"stcd":"60823798","tm":"2018-08-09 11:00:00","drp":0,"stnm":"石关"},{"stcd":"60823798","tm":"2018-08-09 10:00:00","drp":0,"stnm":"石关"}]
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
             * stcd : 60823798
             * tm : 2018-08-09 19:00:00
             * drp : 0.0
             * stnm : 石关
             */

            private String stcd;
            private String tm;
            private double drp;
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

            public double getDrp() {
                return drp;
            }

            public void setDrp(double drp) {
                this.drp = drp;
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
