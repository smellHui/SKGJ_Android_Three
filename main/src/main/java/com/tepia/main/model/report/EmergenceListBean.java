package com.tepia.main.model.report;

import com.tepia.base.http.BaseResponse;

import java.util.List;

/**
 * Created by      android studio
 *
 * @author :       ly
 * Date            :       2018-09-28
 * Time            :       上午10:08
 * Version         :       1.0
 * location        :       武汉研发中心
 * 功能描述         :       应急情况列表
 **/
public class EmergenceListBean extends BaseResponse{

    /**
     * data : {"pageNum":1,"pageSize":10,"size":3,"startRow":1,"endRow":3,"total":3,"pages":1,"list":[{"problemTitle":"测试应急上报图片和文件-apizza","problemStatus":"4","createDate":"2018-09-26 14:10:18","reservoirName":"绿竹坝水库","isVerify":"0"}]}
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
         * size : 3
         * startRow : 1
         * endRow : 3
         * total : 3
         * pages : 1
         * list : [{"problemTitle":"测试应急上报图片和文件-apizza","problemStatus":"4","createDate":"2018-09-26 14:10:18","reservoirName":"绿竹坝水库","isVerify":"0"}]
         */

        private int pageNum;
        private int pageSize;
        private int size;
        private int startRow;
        private int endRow;
        private int total;
        private int pages;
        private List<ListBean> list;

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

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * problemTitle : 测试应急上报图片和文件-apizza
             * problemStatus : 4
             * createDate : 2018-09-26 14:10:18
             * reservoirName : 绿竹坝水库
             * isVerify : 0
             */

            private String problemTitle;
            private String problemStatus;
            private String createDate;
            private String reservoirName;
            private String isVerify;

            public String getProblemTitle() {
                return problemTitle;
            }

            public void setProblemTitle(String problemTitle) {
                this.problemTitle = problemTitle;
            }

            public String getProblemStatus() {
                return problemStatus;
            }

            public void setProblemStatus(String problemStatus) {
                this.problemStatus = problemStatus;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
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
        }
    }
}
