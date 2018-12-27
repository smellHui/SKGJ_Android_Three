package com.tepia.main.model.reserviros;

import com.tepia.base.http.BaseResponse;

import java.util.List;

/**
 * Created by      android studio
 *
 * @author :       ly
 * Date            :       2018-12-27
 * Time            :       上午10:27
 * Version         :       1.0
 * location        :       武汉研发中心
 * 功能描述         :       汛期水位实体
 **/
public class FloodSeasonBean extends BaseResponse {

    /**
     * data : {"pageNum":1,"pageSize":10,"size":2,"total":2,"pages":1,"list":[{"id":"1b250bf5826c45d5be71f36914224dfe","reservoirId":"66fb3d579d084daf8a7d35d9d9612213","floodYearMonth":"2018-12","floodLevel":666,"createDate":"2018-12-26 16:21:04","floodSeasonWaterLevel":973},{"id":"7e52e3c6cbcc40c2a5d9dd36800a82eb","reservoirId":"66fb3d579d084daf8a7d35d9d9612213","floodYearMonth":"2018-04","floodLevel":973.4,"createDate":"2018-12-27 09:31:21","floodSeasonWaterLevel":973}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    private void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * pageNum : 1
         * pageSize : 10
         * size : 2
         * total : 2
         * pages : 1
         * list : [{"id":"1b250bf5826c45d5be71f36914224dfe","reservoirId":"66fb3d579d084daf8a7d35d9d9612213","floodYearMonth":"2018-12","floodLevel":666,"createDate":"2018-12-26 16:21:04","floodSeasonWaterLevel":973},{"id":"7e52e3c6cbcc40c2a5d9dd36800a82eb","reservoirId":"66fb3d579d084daf8a7d35d9d9612213","floodYearMonth":"2018-04","floodLevel":973.4,"createDate":"2018-12-27 09:31:21","floodSeasonWaterLevel":973}]
         */

        private int pageNum;
        private int pageSize;
        private int size;
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
             * id : 1b250bf5826c45d5be71f36914224dfe
             * reservoirId : 66fb3d579d084daf8a7d35d9d9612213
             * floodYearMonth : 2018-12
             * floodLevel : 666
             * createDate : 2018-12-26 16:21:04
             * floodSeasonWaterLevel : 973
             */

            private String id;
            private String reservoirId;
            private String floodYearMonth;
            private String floodLevel;
            private String createDate;
            private String floodSeasonWaterLevel;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getReservoirId() {
                return reservoirId;
            }

            public void setReservoirId(String reservoirId) {
                this.reservoirId = reservoirId;
            }

            public String getFloodYearMonth() {
                return floodYearMonth;
            }

            public void setFloodYearMonth(String floodYearMonth) {
                this.floodYearMonth = floodYearMonth;
            }

            public String getFloodLevel() {
                return floodLevel;
            }

            public void setFloodLevel(String floodLevel) {
                this.floodLevel = floodLevel;
            }

            public String getFloodSeasonWaterLevel() {
                return floodSeasonWaterLevel;
            }

            public void setFloodSeasonWaterLevel(String floodSeasonWaterLevel) {
                this.floodSeasonWaterLevel = floodSeasonWaterLevel;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

        }
    }
}
