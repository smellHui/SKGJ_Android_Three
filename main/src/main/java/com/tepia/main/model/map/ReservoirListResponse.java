package com.tepia.main.model.map;

import com.tepia.base.http.BaseResponse;

import java.util.List;

/**
 * 水库列表
 * @author 44822
 */
public class ReservoirListResponse extends BaseResponse {

    /**
     * code : 0
     * count : 0
     * data : []
     * */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * reservoirId : 09e903f9767f4be19512c6106a48dcc7
         * reservoir : "枫青水库"
         * reservoirLongitude : "106.5275"
         * reservoirLatitude : "27.621389"
         */

        private String reservoirId;
        private String reservoir;
        private String reservoirLongitude;
        private String reservoirLatitude;

        public String getReservoirId() {
            return reservoirId;
        }

        public void setReservoirId(String reservoirId) {
            this.reservoirId = reservoirId;
        }

        public String getReservoir() {
            return reservoir;
        }

        public void setReservoir(String reservoir) {
            this.reservoir = reservoir;
        }

        public String getReservoirLongitude() {
            return reservoirLongitude;
        }

        public void setReservoirLongitude(String reservoirLongitude) {
            this.reservoirLongitude = reservoirLongitude;
        }

        public String getReservoirLatitude() {
            return reservoirLatitude;
        }

        public void setReservoirLatitude(String reservoirLatitude) {
            this.reservoirLatitude = reservoirLatitude;
        }
    }
}
