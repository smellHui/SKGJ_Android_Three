package com.tepia.main.view.main.map.adapter.near;

import com.tepia.base.http.BaseResponse;

import java.util.List;

/**
  * Created by      Android studio
  * @author :wwj (from Center Of Wuhan)
  * Date    :2018/11/22
  * Version :1.0
  * 功能描述 :
 **/
public class NearReservoirResponse extends BaseResponse {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * reservoirId : 6942292cad144bds97fa6c31f96ee762
         * reservoirCode : NTC520321079T
         * reservoir : 铜锣井水库
         * reservoirType : 2
         * reservoirLongitude : 107.036111
         * reservoirLatitude : 27.470833
         * nearby : 299.0
         */

        private String reservoirId;
        private String reservoirCode;
        private String reservoir;
        private String reservoirType;
        private String reservoirLongitude;
        private String reservoirLatitude;
        private double nearby;

        public String getReservoirId() {
            return reservoirId;
        }

        public void setReservoirId(String reservoirId) {
            this.reservoirId = reservoirId;
        }

        public String getReservoirCode() {
            return reservoirCode;
        }

        public void setReservoirCode(String reservoirCode) {
            this.reservoirCode = reservoirCode;
        }

        public String getReservoir() {
            return reservoir;
        }

        public void setReservoir(String reservoir) {
            this.reservoir = reservoir;
        }

        public String getReservoirType() {
            return reservoirType;
        }

        public void setReservoirType(String reservoirType) {
            this.reservoirType = reservoirType;
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

        public double getNearby() {
            return nearby;
        }

        public void setNearby(double nearby) {
            this.nearby = nearby;
        }
    }
}
