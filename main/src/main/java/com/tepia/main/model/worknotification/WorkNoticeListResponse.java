package com.tepia.main.model.worknotification;

import com.tepia.base.http.BaseResponse;

import java.util.List;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2018-09-29
 * Time            :       17:21
 * Version         :       1.0
 * 功能描述        :
 **/
public class WorkNoticeListResponse extends BaseResponse {

    /**
     * data : {"pageNum":1,"pageSize":10,"size":2,"startRow":1,"endRow":2,"total":2,"pages":1,"list":[{"id":"89e744302710403d9ad9e322768e507e","reservoirIds":"\"66fb3d579d084daf8a7d35d9d9612213,6942292cad144bds97fa6c31f96ee684\"","noticeTitle":"\"紧急通知\"","noticeContent":"\"紧急紧急紧急紧急\"","createBy":"c6fc4372f7e948e38c7e1ea0296facf4","createDate":"2018-09-29 17:00:48","userName":"李维保"},{"id":"bf6eda50c61d43fdb32fd477e4811c5e","reservoirIds":"\"66fb3d579d084daf8a7d35d9d9612213,6942292cad144bds97fa6c31f96ee684\"","noticeTitle":"\"重要通知\"","noticeContent":"\"请注意水位，随时反馈\"","createBy":"c6fc4372f7e948e38c7e1ea0296facf4","createDate":"2018-09-29 17:02:05","userName":"李维保"}],"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"lastPage":1,"firstPage":1}
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
         * list : [{"id":"89e744302710403d9ad9e322768e507e","reservoirIds":"\"66fb3d579d084daf8a7d35d9d9612213,6942292cad144bds97fa6c31f96ee684\"","noticeTitle":"\"紧急通知\"","noticeContent":"\"紧急紧急紧急紧急\"","createBy":"c6fc4372f7e948e38c7e1ea0296facf4","createDate":"2018-09-29 17:00:48","userName":"李维保"},{"id":"bf6eda50c61d43fdb32fd477e4811c5e","reservoirIds":"\"66fb3d579d084daf8a7d35d9d9612213,6942292cad144bds97fa6c31f96ee684\"","noticeTitle":"\"重要通知\"","noticeContent":"\"请注意水位，随时反馈\"","createBy":"c6fc4372f7e948e38c7e1ea0296facf4","createDate":"2018-09-29 17:02:05","userName":"李维保"}]
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
         * lastPage : 1
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
        private List<WorkNoticeBean> list;
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

        public List<WorkNoticeBean> getList() {
            return list;
        }

        public void setList(List<WorkNoticeBean> list) {
            this.list = list;
        }

        public List<Integer> getNavigatepageNums() {
            return navigatepageNums;
        }

        public void setNavigatepageNums(List<Integer> navigatepageNums) {
            this.navigatepageNums = navigatepageNums;
        }

    }
}
