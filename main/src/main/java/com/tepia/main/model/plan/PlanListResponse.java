package com.tepia.main.model.plan;

import com.tepia.base.http.BaseResponse;

import java.io.Serializable;
import java.util.List;

/**
 * 巡检计划
 * @author 44822
 */
public class PlanListResponse extends BaseResponse{

    /**
     * code : 0
     * count : 0
     * data : {}
     * */

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
         * total : 55
         * pages : 6  总页数
         * list : []
         *  prePage : 0
         * nextPage : 2
         * isFirstPage : true
         * isLastPage : false
         * hasPreviousPage : false
         * hasNextPage : true
         * navigatePages : 8
         * navigatepageNums : [1,2,3,4,5,6]
         * navigateFirstPage : 1
         * navigateLastPage : 6
         * firstPage : 1
         * lastPage : 6
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
             * planId : d215612a29114a54910fe8c65f3d0c6e
             * planType : 1
             * reservoirId : 66fb3d579d084daf8a7d35d9d9612213
             * planName : 魏爽-测试地图工单生成工单
             * planTimes : 2018-08-29 00:00:00—2018-08-30 00:00:00,2018-08-30 00:00:00—2018-08-31 00:00:00,2018-08-31 00:00:00—2018-09-01 00:00:00
             * isGenerate : 1
             * workOrderCount : 3
             * remarks : 魏爽-测试地图工单生成工单
             * status : 0
             * createDate : 2018-08-29 19:30:49
             * operationType : 1
             * reservoirName : 绿竹坝水库
             */

            private String planId;
            private String planType;
            private String reservoirId;
            private String planName;
            private String planTimes;
            private String isGenerate;
            private int workOrderCount;
            private String remarks;
            private String status;
            private String createDate;
            private String operationType;
            private String reservoirName;

            public String getPlanId() {
                return planId;
            }

            public void setPlanId(String planId) {
                this.planId = planId;
            }

            public String getPlanType() {
                return planType;
            }

            public void setPlanType(String planType) {
                this.planType = planType;
            }

            public String getReservoirId() {
                return reservoirId;
            }

            public void setReservoirId(String reservoirId) {
                this.reservoirId = reservoirId;
            }

            public String getPlanName() {
                return planName;
            }

            public void setPlanName(String planName) {
                this.planName = planName;
            }

            public String getPlanTimes() {
                return planTimes;
            }

            public void setPlanTimes(String planTimes) {
                this.planTimes = planTimes;
            }

            public String getIsGenerate() {
                return isGenerate;
            }

            public void setIsGenerate(String isGenerate) {
                this.isGenerate = isGenerate;
            }

            public int getWorkOrderCount() {
                return workOrderCount;
            }

            public void setWorkOrderCount(int workOrderCount) {
                this.workOrderCount = workOrderCount;
            }

            public String getRemarks() {
                return remarks;
            }

            public void setRemarks(String remarks) {
                this.remarks = remarks;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public String getOperationType() {
                return operationType;
            }

            public void setOperationType(String operationType) {
                this.operationType = operationType;
            }

            public String getReservoirName() {
                return reservoirName;
            }

            public void setReservoirName(String reservoirName) {
                this.reservoirName = reservoirName;
            }

            @Override
            public String toString() {
                return "ListBean{" +
                        "planId='" + planId + '\'' +
                        ", planType='" + planType + '\'' +
                        ", reservoirId='" + reservoirId + '\'' +
                        ", planName='" + planName + '\'' +
                        ", planTimes='" + planTimes + '\'' +
                        ", isGenerate='" + isGenerate + '\'' +
                        ", workOrderCount=" + workOrderCount +
                        ", remarks='" + remarks + '\'' +
                        ", status='" + status + '\'' +
                        ", createDate='" + createDate + '\'' +
                        ", operationType='" + operationType + '\'' +
                        ", reservoirName='" + reservoirName + '\'' +
                        '}';
            }
        }
    }
}
