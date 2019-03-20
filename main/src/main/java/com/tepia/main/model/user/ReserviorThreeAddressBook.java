package com.tepia.main.model.user;

import com.tepia.base.http.BaseResponse;

import java.util.List;

/**
 * Created by      android studio
 *
 * @author :       ly
 * Date            :       2019-03-19
 * Time            :       下午4:42
 * Version         :       1.0
 * location        :       武汉研发中心
 * 功能描述         :
 **/
public class ReserviorThreeAddressBook extends BaseResponse {


    /**
     * data : {"pageNum":1,"pageSize":10,"size":3,"startRow":1,"endRow":3,"total":3,"pages":1,"list":[{"userName":"李小青","mobile":"13502640567","jobName":"乡镇领导"},{"userName":"曾科","mobile":"13539123030","jobName":"乡镇领导"},{"userName":"郭周正","mobile":"13923683949","jobName":"县三防成员"}]}
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
         * list : [{"userName":"李小青","mobile":"13502640567","jobName":"乡镇领导"},{"userName":"曾科","mobile":"13539123030","jobName":"乡镇领导"},{"userName":"郭周正","mobile":"13923683949","jobName":"县三防成员"}]
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
