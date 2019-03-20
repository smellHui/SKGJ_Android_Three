package com.tepia.main.model.user;

import com.tepia.base.http.BaseResponse;

import java.util.List;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2018-09-26
 * Time            :       14:14
 * Version         :       1.0
 * 功能描述        :
 **/
public class AddressBookResponse extends BaseResponse {

    /**
     * data : {"pageNum":1,"pageSize":10,"size":7,"startRow":1,"endRow":7,"total":38,"pages":4,"list":[{"userName":"张巡维保","mobile":"13838384382","reservoirs":[{"reservoir":"绿竹坝水库"}],"jobs":[{"jobName":"维修养护人员"},{"jobName":"巡检人员"},{"jobName":"保洁人员"}]},{"userName":"张四","mobile":"13838384382","reservoirs":[{"reservoir":"绿竹坝水库"}],"jobs":[{"jobName":"巡检人员"}]},{"userName":"张一","mobile":"13838384382","reservoirs":[{"reservoir":"绿竹坝水库"}],"jobs":[{"jobName":"行政负责人"}]},{"userName":"李四","mobile":"13838384382","reservoirs":[{"reservoir":"沙坝水库"}],"jobs":[{"jobName":"巡检人员"}]},{"userName":"张二","mobile":"13838384382","reservoirs":[{"reservoir":"沙坝水库"}],"jobs":[{"jobName":"巡查负责人"}]},{"userName":"张巡保","mobile":"13838384382","reservoirs":[{"reservoir":"绿竹坝水库"}],"jobs":[{"jobName":"巡检人员"},{"jobName":"保洁人员"}]},{"userName":"张维保","mobile":"13838384382","reservoirs":[{"reservoir":"绿竹坝水库"}],"jobs":[{"jobName":"维修养护人员"}]}],"prePage":0,"nextPage":2,"isFirstPage":true,"isLastPage":false,"hasPreviousPage":false,"hasNextPage":true,"navigatePages":8,"navigatepageNums":[1,2,3,4],"navigateFirstPage":1,"navigateLastPage":4,"lastPage":4,"firstPage":1}
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
         * size : 7
         * startRow : 1
         * endRow : 7
         * total : 38
         * pages : 4
         * list : [{"userName":"张巡维保","mobile":"13838384382","reservoirs":[{"reservoir":"绿竹坝水库"}],"jobs":[{"jobName":"维修养护人员"},{"jobName":"巡检人员"},{"jobName":"保洁人员"}]},{"userName":"张四","mobile":"13838384382","reservoirs":[{"reservoir":"绿竹坝水库"}],"jobs":[{"jobName":"巡检人员"}]},{"userName":"张一","mobile":"13838384382","reservoirs":[{"reservoir":"绿竹坝水库"}],"jobs":[{"jobName":"行政负责人"}]},{"userName":"李四","mobile":"13838384382","reservoirs":[{"reservoir":"沙坝水库"}],"jobs":[{"jobName":"巡检人员"}]},{"userName":"张二","mobile":"13838384382","reservoirs":[{"reservoir":"沙坝水库"}],"jobs":[{"jobName":"巡查负责人"}]},{"userName":"张巡保","mobile":"13838384382","reservoirs":[{"reservoir":"绿竹坝水库"}],"jobs":[{"jobName":"巡检人员"},{"jobName":"保洁人员"}]},{"userName":"张维保","mobile":"13838384382","reservoirs":[{"reservoir":"绿竹坝水库"}],"jobs":[{"jobName":"维修养护人员"}]}]
         * prePage : 0
         * nextPage : 2
         * isFirstPage : true
         * isLastPage : false
         * hasPreviousPage : false
         * hasNextPage : true
         * navigatePages : 8
         * navigatepageNums : [1,2,3,4]
         * navigateFirstPage : 1
         * navigateLastPage : 4
         * lastPage : 4
         * firstPage : 1
         */

        private int pageNum;
        private int pageSize;
        private int size;
        private int startRow;
        private int endRow;
        private int total;
        private int pages;

        private List<ContactBean> list;

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

        public List<ContactBean> getList() {
            return list;
        }

        public void setList(List<ContactBean> list) {
            this.list = list;
        }


    }
}
