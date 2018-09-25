package com.tepia.main.model.reserviros;

import com.tepia.base.http.BaseResponse;

import java.util.List;

/**
 * Created by      android studio
 *
 * @author :       ly
 * Date            :       2018-09-25
 * Time            :       上午10:27
 * Version         :       1.0
 * location        :       武汉研发中心
 * 功能描述         :
 **/
public class VisitLogBean extends BaseResponse{

    /**
     * data : {"pageNum":1,"pageSize":10,"size":4,"startRow":1,"endRow":4,"total":4,"pages":1,"list":[{"id":"06fae45ce6674af4849b7c15033750bd","visitTime":"2018-09-18 00:00:00","reservoir":"绿竹坝水库","userName":"鲁志平","roleName":"播州区运维中心主管"},{"id":"1bf5f1cf3d044f91bd163ca80c4a3f30","visitTime":"2018-09-17 00:00:00","reservoir":"绿竹坝水库","userName":"鲁志平","roleName":"播州区运维中心主管"},{"id":"3170951aa30346fe93ea4b18043e62cf","visitTime":"2018-09-03 10:10:10","reservoir":"绿竹坝水库","userName":"播州管理员","roleName":"播州区管理员"},{"id":"5e499c6632e84c0c80d444cfa1eaf7d0","visitTime":"2018-09-19 00:00:00","reservoir":"绿竹坝水库","userName":"超级管理员","roleName":"超级管理员"}]}
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
         * size : 4
         * startRow : 1
         * endRow : 4
         * total : 4
         * pages : 1
         * list : [{"id":"06fae45ce6674af4849b7c15033750bd","visitTime":"2018-09-18 00:00:00","reservoir":"绿竹坝水库","userName":"鲁志平","roleName":"播州区运维中心主管"},{"id":"1bf5f1cf3d044f91bd163ca80c4a3f30","visitTime":"2018-09-17 00:00:00","reservoir":"绿竹坝水库","userName":"鲁志平","roleName":"播州区运维中心主管"},{"id":"3170951aa30346fe93ea4b18043e62cf","visitTime":"2018-09-03 10:10:10","reservoir":"绿竹坝水库","userName":"播州管理员","roleName":"播州区管理员"},{"id":"5e499c6632e84c0c80d444cfa1eaf7d0","visitTime":"2018-09-19 00:00:00","reservoir":"绿竹坝水库","userName":"超级管理员","roleName":"超级管理员"}]
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
             * id : 06fae45ce6674af4849b7c15033750bd
             * visitTime : 2018-09-18 00:00:00
             * reservoir : 绿竹坝水库
             * userName : 鲁志平
             * roleName : 播州区运维中心主管
             */

            private String id;
            private String visitTime;
            private String reservoir;
            private String userName;
            private String roleName;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getVisitTime() {
                return visitTime;
            }

            public void setVisitTime(String visitTime) {
                this.visitTime = visitTime;
            }

            public String getReservoir() {
                return reservoir;
            }

            public void setReservoir(String reservoir) {
                this.reservoir = reservoir;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getRoleName() {
                return roleName;
            }

            public void setRoleName(String roleName) {
                this.roleName = roleName;
            }
        }
    }
}
