package com.tepia.main.model.jishu.admin;

import com.tepia.base.http.BaseResponse;

import java.io.Serializable;
import java.util.List;

/**
 * Created by      Intellij IDEA
 *  行政运维工单
 * @author :       wwj
 * Date    :       2018-09-28
 * Time    :       19:03
 * Version :       1.0
 * Company :       北京太比雅科技(武汉研发中心)
 **/
public class AdminWorkOrderResponse extends BaseResponse{

    /**
     * data : {"pageNum":1,"pageSize":10,"size":2,"startRow":1,"endRow":2,"total":2,"pages":1,"list":[{"date":"2018-09","done_num":4,"non_num":22,"totals":26,"reservoirId":"66fb3d579d084daf8a7d35d9d9612213","reservoirName":"绿竹坝水库"},{"date":"2018-09","done_num":0,"non_num":9,"totals":9,"reservoirId":"6942292cad144bds97fa6c31f96ee684","reservoirName":"沙坝水库"}],"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"firstPage":1,"lastPage":1}
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
         * size : 2
         * startRow : 1
         * endRow : 2
         * total : 2
         * pages : 1
         * list : [{"date":"2018-09","done_num":4,"non_num":22,"totals":26,"reservoirId":"66fb3d579d084daf8a7d35d9d9612213","reservoirName":"绿竹坝水库"},{"date":"2018-09","done_num":0,"non_num":9,"totals":9,"reservoirId":"6942292cad144bds97fa6c31f96ee684","reservoirName":"沙坝水库"}]
         * prePage : 0
         * nextPage : 0
         * isFirstPage : true
         * isLastPage : true
         * hasPreviousPage : false
         * hasNextPage : false
         * navigatePages : 8
         * navigatepageNums : [1]
         * navigateFirstPage : 1
         * navigateLastPage : 1
         * firstPage : 1
         * lastPage : 1
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
        private int firstPage;
        private int lastPage;
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

        public int getFirstPage() {
            return firstPage;
        }

        public void setFirstPage(int firstPage) {
            this.firstPage = firstPage;
        }

        public int getLastPage() {
            return lastPage;
        }

        public void setLastPage(int lastPage) {
            this.lastPage = lastPage;
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

        public static class ListBean implements Serializable{
            /**
             * date : 2018-09
             * done_num : 4
             * non_num : 22
             * totals : 26
             * reservoirId : 66fb3d579d084daf8a7d35d9d9612213
             * reservoirName : 绿竹坝水库
             */

            private String date;
            private int done_num;
            private int non_num;
            private int totals;
            private String reservoirId;
            private String reservoirName;
            private String areaName;
            private double doneRate;//该值除以reservoirNum得到该镇的完成率
            private int reservoirNum;//水库数量
            private int nonNum;
            private int doneNum;
            private String areaCode;

            public String getAreaCode() {
                return areaCode;
            }

            public void setAreaCode(String areaCode) {
                this.areaCode = areaCode;
            }

            public int getNonNum() {
                return nonNum;
            }

            public void setNonNum(int nonNum) {
                this.nonNum = nonNum;
            }

            public int getDoneNum() {
                return doneNum;
            }

            public void setDoneNum(int doneNum) {
                this.doneNum = doneNum;
            }

            public String getAreaName() {
                return areaName;
            }

            public void setAreaName(String areaName) {
                this.areaName = areaName;
            }

            public double getDoneRate() {
                return doneRate;
            }

            public void setDoneRate(double doneRate) {
                this.doneRate = doneRate;
            }

            public int getReservoirNum() {
                return reservoirNum;
            }

            public void setReservoirNum(int reservoirNum) {
                this.reservoirNum = reservoirNum;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public int getDone_num() {
                return done_num;
            }

            public void setDone_num(int done_num) {
                this.done_num = done_num;
            }

            public int getNon_num() {
                return non_num;
            }

            public void setNon_num(int non_num) {
                this.non_num = non_num;
            }

            public int getTotals() {
                return totals;
            }

            public void setTotals(int totals) {
                this.totals = totals;
            }

            public String getReservoirId() {
                return reservoirId;
            }

            public void setReservoirId(String reservoirId) {
                this.reservoirId = reservoirId;
            }

            public String getReservoirName() {
                return reservoirName;
            }

            public void setReservoirName(String reservoirName) {
                this.reservoirName = reservoirName;
            }
        }
    }
}
