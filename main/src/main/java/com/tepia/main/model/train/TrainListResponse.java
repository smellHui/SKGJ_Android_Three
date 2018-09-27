package com.tepia.main.model.train;

import com.tepia.base.http.BaseResponse;

import java.util.List;

/**
 * Created by      Intellij IDEA
 * 培训列表
 * @author :       wwj
 * Date    :       2018-09-27
 * Time    :       15:21
 * Version :       1.0
 * Company :       北京太比雅科技(武汉研发中心)
 **/
public class TrainListResponse extends BaseResponse{

    /**
     * data : {"pageNum":1,"pageSize":10,"size":1,"startRow":1,"endRow":1,"total":1,"pages":1,"list":[{"id":"2e729e0243904a66864824a53ee31408","trainTitle":"人工智能","position":"上海","trainDate":"2018-09-19 10:00:00","trainContent":"世界人工智能大会召开","organizeCompany":"政府"}],"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"firstPage":1,"lastPage":1}
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
         * size : 1
         * startRow : 1
         * endRow : 1
         * total : 1
         * pages : 1
         * list : [{"id":"2e729e0243904a66864824a53ee31408","trainTitle":"人工智能","position":"上海","trainDate":"2018-09-19 10:00:00","trainContent":"世界人工智能大会召开","organizeCompany":"政府"}]
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

        public static class ListBean {
            /**
             * id : 2e729e0243904a66864824a53ee31408
             * trainTitle : 人工智能
             * position : 上海
             * trainDate : 2018-09-19 10:00:00
             * trainContent : 世界人工智能大会召开
             * organizeCompany : 政府
             */

            private String id;
            private String trainTitle;
            private String position;
            private String trainDate;
            private String trainContent;
            private String organizeCompany;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTrainTitle() {
                return trainTitle;
            }

            public void setTrainTitle(String trainTitle) {
                this.trainTitle = trainTitle;
            }

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }

            public String getTrainDate() {
                return trainDate;
            }

            public void setTrainDate(String trainDate) {
                this.trainDate = trainDate;
            }

            public String getTrainContent() {
                return trainContent;
            }

            public void setTrainContent(String trainContent) {
                this.trainContent = trainContent;
            }

            public String getOrganizeCompany() {
                return organizeCompany;
            }

            public void setOrganizeCompany(String organizeCompany) {
                this.organizeCompany = organizeCompany;
            }
        }
    }
}
