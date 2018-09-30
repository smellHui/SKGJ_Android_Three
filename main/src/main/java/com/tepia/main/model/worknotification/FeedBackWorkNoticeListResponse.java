package com.tepia.main.model.worknotification;

import com.tepia.base.http.BaseResponse;

import java.util.List;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2018-09-30
 * Time            :       10:31
 * Version         :       1.0
 * 功能描述        :
 **/
public class FeedBackWorkNoticeListResponse extends BaseResponse{

    /**
     * data : {"pageNum":1,"pageSize":5,"size":5,"startRow":1,"endRow":5,"total":7,"pages":2,"list":[{"id":"8e5c1b07e4db473da24df85fb16663bb","reservoirId":"6942292cad144bds97fa6c31f96ee698","status":"1","bizWorkNotice":{"id":"371d769c56384a41a18332e9bd9798e3","reservoirIds":"66fb3d579d084daf8a7d35d9d9612213,6942292cad144bds97fa6c31f96ee684,6942292cad144bds97fa6c31f96ee687,6942292cad144bds97fa6c31f96ee698","noticeTitle":"紧急通知紧急通知","noticeContent":"反馈水位是否过警戒线a反馈水位是否过警戒线a反馈水位是否过警戒线a反馈水位是否过警戒线a反馈水位是否过警戒线a反馈水位是否过警戒线a反馈水位是否过警戒线a反馈水位是否过警戒线a","createBy":"501bb19b45d44caf9807d6ba55db27ae","createDate":"2018-09-28 17:34:15","userName":"超级管理员"},"reservoirName":"东风水库"},{"id":"ee702a7c96394fe983f6882c0edbe3ac","reservoirId":"6942292cad144bds97fa6c31f96ee684","status":"1","bizWorkNotice":{"id":"371d769c56384a41a18332e9bd9798e3","reservoirIds":"66fb3d579d084daf8a7d35d9d9612213,6942292cad144bds97fa6c31f96ee684,6942292cad144bds97fa6c31f96ee687,6942292cad144bds97fa6c31f96ee698","noticeTitle":"紧急通知紧急通知","noticeContent":"反馈水位是否过警戒线a反馈水位是否过警戒线a反馈水位是否过警戒线a反馈水位是否过警戒线a反馈水位是否过警戒线a反馈水位是否过警戒线a反馈水位是否过警戒线a反馈水位是否过警戒线a","createBy":"501bb19b45d44caf9807d6ba55db27ae","createDate":"2018-09-28 17:34:15","userName":"超级管理员"},"reservoirName":"沙坝水库"},{"id":"fa423da155a1413d98a9a7efa3c9e29a","reservoirId":"66fb3d579d084daf8a7d35d9d9612213","status":"1","bizWorkNotice":{"id":"371d769c56384a41a18332e9bd9798e3","reservoirIds":"66fb3d579d084daf8a7d35d9d9612213,6942292cad144bds97fa6c31f96ee684,6942292cad144bds97fa6c31f96ee687,6942292cad144bds97fa6c31f96ee698","noticeTitle":"紧急通知紧急通知","noticeContent":"反馈水位是否过警戒线a反馈水位是否过警戒线a反馈水位是否过警戒线a反馈水位是否过警戒线a反馈水位是否过警戒线a反馈水位是否过警戒线a反馈水位是否过警戒线a反馈水位是否过警戒线a","createBy":"501bb19b45d44caf9807d6ba55db27ae","createDate":"2018-09-28 17:34:15","userName":"超级管理员"},"reservoirName":"绿竹坝水库"},{"id":"db39a851bf02485eb45bcd3bd01e71d9","reservoirId":"6942292cad144bds97fa6c31f96ee684","status":"1","bizWorkNotice":{"id":"dd37fb25e12443c09c27dbe129cdcef8","reservoirIds":"66fb3d579d084daf8a7d35d9d9612213,6942292cad144bds97fa6c31f96ee684,6942292cad144bds97fa6c31f96ee685","noticeTitle":"0929领导视察通知。","noticeContent":"0929领导视察通知。请做好各项准备！","createBy":"501bb19b45d44caf9807d6ba55db27ae","createDate":"2018-09-29 09:14:15","userName":"超级管理员"},"reservoirName":"沙坝水库"},{"id":"f9eaca099009445a8c98be6210855f9a","reservoirId":"66fb3d579d084daf8a7d35d9d9612213","status":"0","bizWorkNotice":{"id":"dd37fb25e12443c09c27dbe129cdcef8","reservoirIds":"66fb3d579d084daf8a7d35d9d9612213,6942292cad144bds97fa6c31f96ee684,6942292cad144bds97fa6c31f96ee685","noticeTitle":"0929领导视察通知。","noticeContent":"0929领导视察通知。请做好各项准备！","createBy":"501bb19b45d44caf9807d6ba55db27ae","createDate":"2018-09-29 09:14:15","userName":"超级管理员"},"reservoirName":"绿竹坝水库"}],"prePage":0,"nextPage":2,"isFirstPage":true,"isLastPage":false,"hasPreviousPage":false,"hasNextPage":true,"navigatePages":8,"navigatepageNums":[1,2],"navigateFirstPage":1,"navigateLastPage":2,"firstPage":1,"lastPage":2}
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
         * pageSize : 5
         * size : 5
         * startRow : 1
         * endRow : 5
         * total : 7
         * pages : 2
         * list : [{"id":"8e5c1b07e4db473da24df85fb16663bb","reservoirId":"6942292cad144bds97fa6c31f96ee698","status":"1","bizWorkNotice":{"id":"371d769c56384a41a18332e9bd9798e3","reservoirIds":"66fb3d579d084daf8a7d35d9d9612213,6942292cad144bds97fa6c31f96ee684,6942292cad144bds97fa6c31f96ee687,6942292cad144bds97fa6c31f96ee698","noticeTitle":"紧急通知紧急通知","noticeContent":"反馈水位是否过警戒线a反馈水位是否过警戒线a反馈水位是否过警戒线a反馈水位是否过警戒线a反馈水位是否过警戒线a反馈水位是否过警戒线a反馈水位是否过警戒线a反馈水位是否过警戒线a","createBy":"501bb19b45d44caf9807d6ba55db27ae","createDate":"2018-09-28 17:34:15","userName":"超级管理员"},"reservoirName":"东风水库"},{"id":"ee702a7c96394fe983f6882c0edbe3ac","reservoirId":"6942292cad144bds97fa6c31f96ee684","status":"1","bizWorkNotice":{"id":"371d769c56384a41a18332e9bd9798e3","reservoirIds":"66fb3d579d084daf8a7d35d9d9612213,6942292cad144bds97fa6c31f96ee684,6942292cad144bds97fa6c31f96ee687,6942292cad144bds97fa6c31f96ee698","noticeTitle":"紧急通知紧急通知","noticeContent":"反馈水位是否过警戒线a反馈水位是否过警戒线a反馈水位是否过警戒线a反馈水位是否过警戒线a反馈水位是否过警戒线a反馈水位是否过警戒线a反馈水位是否过警戒线a反馈水位是否过警戒线a","createBy":"501bb19b45d44caf9807d6ba55db27ae","createDate":"2018-09-28 17:34:15","userName":"超级管理员"},"reservoirName":"沙坝水库"},{"id":"fa423da155a1413d98a9a7efa3c9e29a","reservoirId":"66fb3d579d084daf8a7d35d9d9612213","status":"1","bizWorkNotice":{"id":"371d769c56384a41a18332e9bd9798e3","reservoirIds":"66fb3d579d084daf8a7d35d9d9612213,6942292cad144bds97fa6c31f96ee684,6942292cad144bds97fa6c31f96ee687,6942292cad144bds97fa6c31f96ee698","noticeTitle":"紧急通知紧急通知","noticeContent":"反馈水位是否过警戒线a反馈水位是否过警戒线a反馈水位是否过警戒线a反馈水位是否过警戒线a反馈水位是否过警戒线a反馈水位是否过警戒线a反馈水位是否过警戒线a反馈水位是否过警戒线a","createBy":"501bb19b45d44caf9807d6ba55db27ae","createDate":"2018-09-28 17:34:15","userName":"超级管理员"},"reservoirName":"绿竹坝水库"},{"id":"db39a851bf02485eb45bcd3bd01e71d9","reservoirId":"6942292cad144bds97fa6c31f96ee684","status":"1","bizWorkNotice":{"id":"dd37fb25e12443c09c27dbe129cdcef8","reservoirIds":"66fb3d579d084daf8a7d35d9d9612213,6942292cad144bds97fa6c31f96ee684,6942292cad144bds97fa6c31f96ee685","noticeTitle":"0929领导视察通知。","noticeContent":"0929领导视察通知。请做好各项准备！","createBy":"501bb19b45d44caf9807d6ba55db27ae","createDate":"2018-09-29 09:14:15","userName":"超级管理员"},"reservoirName":"沙坝水库"},{"id":"f9eaca099009445a8c98be6210855f9a","reservoirId":"66fb3d579d084daf8a7d35d9d9612213","status":"0","bizWorkNotice":{"id":"dd37fb25e12443c09c27dbe129cdcef8","reservoirIds":"66fb3d579d084daf8a7d35d9d9612213,6942292cad144bds97fa6c31f96ee684,6942292cad144bds97fa6c31f96ee685","noticeTitle":"0929领导视察通知。","noticeContent":"0929领导视察通知。请做好各项准备！","createBy":"501bb19b45d44caf9807d6ba55db27ae","createDate":"2018-09-29 09:14:15","userName":"超级管理员"},"reservoirName":"绿竹坝水库"}]
         * prePage : 0
         * nextPage : 2
         * isFirstPage : true
         * isLastPage : false
         * hasPreviousPage : false
         * hasNextPage : true
         * navigatePages : 8
         * navigatepageNums : [1,2]
         * navigateFirstPage : 1
         * navigateLastPage : 2
         * firstPage : 1
         * lastPage : 2
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
        private List<FeedBackWorkNoticeBean> list;
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

        public List<FeedBackWorkNoticeBean> getList() {
            return list;
        }

        public void setList(List<FeedBackWorkNoticeBean> list) {
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
