package com.tepia.main.model.question;

import com.tepia.base.http.BaseResponse;

import java.util.List;

/**
 * 问题上报事件列表
 * @author liying
 * @date 2018/7/30
 */

public class AllproblemBean extends BaseResponse {

    /**
     * data : {"pageNum":1,"pageSize":10,"size":2,"startRow":1,"endRow":2,"total":2,"pages":1,"list":[{"problemId":"6713ba2c1f17491488c6d0e69f1681af","problemType":"1","problemTypeName":"巡检","reservoirId":"66fb3d579d084daf8a7d35d9d9612211",
     * "problemTitle":"枫青水库问题111","problemDescription":"枫青水库大坝出现杂草111","problemSource":"2","problemSourceUserId":"a623db0bdb92434ebe6553561aaf2eef","problemStatus":"0","isMakePlan":"0","status":"0","createBy":"bozhou","createDate":"2018-07-26 12:03:06","updateBy":"bozhou","updateDate":"2018-07-26 12:03:06","reservoirName":"枫青水库","isVerify":"0","problemStatusName":"待确认"},
     *
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
         * list : [{"problemId":"6713ba2c1f17491488c6d0e69f1681af","problemType":"1","problemTypeName":"巡检","reservoirId":"66fb3d579d084daf8a7d35d9d9612211","problemTitle":"枫青水库问题111","problemDescription":"枫青水库大坝出现杂草111","problemSource":"2","problemSourceUserId":"a623db0bdb92434ebe6553561aaf2eef","problemStatus":"0","isMakePlan":"0","status":"0",
         * "createBy":"bozhou","createDate":"2018-07-26 12:03:06","updateBy":"bozhou","updateDate":"2018-07-26 12:03:06","reservoirName":"枫青水库","isVerify":"0","problemStatusName":"待确认"},
         *
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
             * problemId : 6713ba2c1f17491488c6d0e69f1681af
             * problemType : 1
             * problemTypeName : 巡检
             * reservoirId : 66fb3d579d084daf8a7d35d9d9612211
             * problemTitle : 枫青水库问题111
             * problemDescription : 枫青水库大坝出现杂草111
             * problemSource : 2
             * problemSourceUserId : a623db0bdb92434ebe6553561aaf2eef
             * problemStatus : 0
             * isMakePlan : 0
             * status : 0
             * createBy : bozhou
             * createDate : 2018-07-26 12:03:06
             * updateBy : bozhou
             * updateDate : 2018-07-26 12:03:06
             * reservoirName : 枫青水库
             * isVerify : 0
             * problemStatusName : 待确认
             */

            private String problemId;
            private String problemType;
            private String problemTypeName;
            private String reservoirId;
            private String problemTitle;
            private String problemDescription;
            private String problemSource;
            private String problemSourceUserId;
            private String problemStatus;
            private String isMakePlan;
            private String status;
            private String createBy;
            private String createDate;
            private String updateBy;
            private String updateDate;
            private String reservoirName;
            private String isVerify;
            private String problemStatusName;

            public String getProblemId() {
                return problemId;
            }

            public void setProblemId(String problemId) {
                this.problemId = problemId;
            }

            public String getProblemType() {
                return problemType;
            }

            public void setProblemType(String problemType) {
                this.problemType = problemType;
            }

            public String getProblemTypeName() {
                return problemTypeName;
            }

            public void setProblemTypeName(String problemTypeName) {
                this.problemTypeName = problemTypeName;
            }

            public String getReservoirId() {
                return reservoirId;
            }

            public void setReservoirId(String reservoirId) {
                this.reservoirId = reservoirId;
            }

            public String getProblemTitle() {
                return problemTitle;
            }

            public void setProblemTitle(String problemTitle) {
                this.problemTitle = problemTitle;
            }

            public String getProblemDescription() {
                return problemDescription;
            }

            public void setProblemDescription(String problemDescription) {
                this.problemDescription = problemDescription;
            }

            public String getProblemSource() {
                return problemSource;
            }

            public void setProblemSource(String problemSource) {
                this.problemSource = problemSource;
            }

            public String getProblemSourceUserId() {
                return problemSourceUserId;
            }

            public void setProblemSourceUserId(String problemSourceUserId) {
                this.problemSourceUserId = problemSourceUserId;
            }

            public String getProblemStatus() {
                return problemStatus;
            }

            public void setProblemStatus(String problemStatus) {
                this.problemStatus = problemStatus;
            }

            public String getIsMakePlan() {
                return isMakePlan;
            }

            public void setIsMakePlan(String isMakePlan) {
                this.isMakePlan = isMakePlan;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getCreateBy() {
                return createBy;
            }

            public void setCreateBy(String createBy) {
                this.createBy = createBy;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public String getUpdateBy() {
                return updateBy;
            }

            public void setUpdateBy(String updateBy) {
                this.updateBy = updateBy;
            }

            public String getUpdateDate() {
                return updateDate;
            }

            public void setUpdateDate(String updateDate) {
                this.updateDate = updateDate;
            }

            public String getReservoirName() {
                return reservoirName;
            }

            public void setReservoirName(String reservoirName) {
                this.reservoirName = reservoirName;
            }

            public String getIsVerify() {
                return isVerify;
            }

            public void setIsVerify(String isVerify) {
                this.isVerify = isVerify;
            }

            public String getProblemStatusName() {
                return problemStatusName;
            }

            public void setProblemStatusName(String problemStatusName) {
                this.problemStatusName = problemStatusName;
            }
        }
    }
}
