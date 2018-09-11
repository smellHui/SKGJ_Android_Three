package com.tepia.main.model.question;

import com.tepia.base.http.BaseResponse;

import java.util.List;

/**
 * 待确认问题
 * Created by      Intellij IDEA
 *
 * @author :       liying
 *         Date    :       2018-09-05
 *         Time    :       下午2:16
 *         Version :       1.0
 *         Company :       北京太比雅科技(武汉研发中心)
 **/

public class ProblemCallListBean extends BaseResponse {

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
         * size : 4
         * startRow : 1
         * endRow : 4
         * total : 4
         * pages : 1
         * list : [{"problemId":"96f97799706b4ffbb1f979df49a4b69b","problemType":"2","reservoirId":"6942292cad144bds97fa6c31f96ee684","problemTitle":"沙坝水库201808巡检工单问题-1","problemDescription":"沙坝水库201808巡检工单审核异常问题 > 存在问题","problemSource":"1","problemSourceUserId":"8c72c748355547adb894d99c2fec0bd4","problemCofirmType":"2","problemStatus":"4","sourceId":"ea427712adeb4c80a8e613a1486e55cc","isMakePlan":"0","planType":"2","status":"0","createBy":"474ddcf5df1146d4b683167f482d7a7d","createDate":"2018-08-30 11:37:42","updateBy":"d76b251318494686a4c799f34ccba039","updateDate":"2018-09-03 09:25:13","reservoirName":"沙坝水库","userName":"杨明炎","isVerify":"0","bizProblemFlows":[]},{"problemId":"8c9a0555ae874503960834f3b425eb3c","problemType":"3","reservoirId":"6942292cad144bds97fa6c31f96ee685","problemTitle":"上报问题web端-k","problemDescription":"测试web端上报问题，3图，有定位","problemSource":"3","problemSourceUserId":"474ddcf5df1146d4b683167f482d7a7d","problemLgtd":"107.117106","problemLttd":"27.489346","problemStatus":"0","isMakePlan":"0","status":"0","createBy":"474ddcf5df1146d4b683167f482d7a7d","createDate":"2018-08-25 10:45:51","reservoirName":"葡萄水库","userName":"播州管理员","isVerify":"0","bizProblemFlows":[]}]
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
        private List<ProblemDetailBean.DataBean> list;
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

        public List<ProblemDetailBean.DataBean> getList() {
            return list;
        }

        public void setList(List<ProblemDetailBean.DataBean> list) {
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
