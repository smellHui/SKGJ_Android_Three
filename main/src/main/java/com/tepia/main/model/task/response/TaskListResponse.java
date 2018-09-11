package com.tepia.main.model.task.response;

import com.tepia.base.http.BaseResponse;
import com.tepia.main.model.task.bean.TaskBean;

import java.util.List;

/**
 * Created by Joeshould on 2018/5/23.
 */

public class TaskListResponse extends BaseResponse {


    /**
     * code : 0
     * count : 0
     * data : {"pageNum":1,"pageSize":100,"size":12,"startRow":1,"endRow":12,"total":12,"pages":1,"list":[{"workOrderId":"2ea608d478894397a0fe5eeca6874bc1","workOrderCode":"XJ201807191505312468218","workOrderName":"风岩水库201807巡检","reservoirId":"66fb3d579d084daf8a7d35d9d9612213","planId":"3b7c5ac245af40629f61740a36dfe897","planStartTime":"2018-07-23 00:00:00","planEndTime":"2018-07-23 00:00:00","executorId":"a623db0bdb92434ebe6553561aaf2eef","executeStatus":"1","status":"0","createBy":"a4e0ea61ccf8428ba84e092af1a84dcd","createDate":"2018-07-19 15:05:31","bizPlanInfo":{"planId":"3b7c5ac245af40629f61740a36dfe897","planType":"2","reservoirId":"66fb3d579d084daf8a7d35d9d9612213","planName":"2018-07-20风岩水库日常巡检计划","operationType":"1"}}],"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"firstPage":1,"lastPage":1}
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
         * pageSize : 100
         * size : 12
         * startRow : 1
         * endRow : 12
         * total : 12
         * pages : 1
         * list : [{"workOrderId":"2ea608d478894397a0fe5eeca6874bc1","workOrderCode":"XJ201807191505312468218","workOrderName":"风岩水库201807巡检","reservoirId":"66fb3d579d084daf8a7d35d9d9612213","planId":"3b7c5ac245af40629f61740a36dfe897","planStartTime":"2018-07-23 00:00:00","planEndTime":"2018-07-23 00:00:00","executorId":"a623db0bdb92434ebe6553561aaf2eef","executeStatus":"1","status":"0","createBy":"a4e0ea61ccf8428ba84e092af1a84dcd","createDate":"2018-07-19 15:05:31","bizPlanInfo":{"planId":"3b7c5ac245af40629f61740a36dfe897","planType":"2","reservoirId":"66fb3d579d084daf8a7d35d9d9612213","planName":"2018-07-20风岩水库日常巡检计划","operationType":"1"}}]
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
        private List<TaskBean> list;
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

        public List<TaskBean> getList() {
            return list;
        }

        public void setList(List<TaskBean> list) {
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
