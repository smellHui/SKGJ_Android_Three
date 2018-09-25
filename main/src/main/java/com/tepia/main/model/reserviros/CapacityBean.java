package com.tepia.main.model.reserviros;

import com.tepia.base.http.BaseResponse;

import java.util.List;

/**
 * Created by      android studio
 *
 * @author :       ly
 * Date            :       2018-09-25
 * Time            :       下午3:46
 * Version         :       1.0
 * location        :       武汉研发中心
 * 功能描述         :       库容曲线实体
 **/
public class CapacityBean extends BaseResponse {

    /**
     * data : {"data":[{"waterLevel":10086.24,"storageCapacity":112.12}]}
     */

    private DataBeanX data;

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        private List<DataBean> data;

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * waterLevel : 10086.24
             * storageCapacity : 112.12
             */

            private double waterLevel;
            private double storageCapacity;

            public double getWaterLevel() {
                return waterLevel;
            }

            public void setWaterLevel(double waterLevel) {
                this.waterLevel = waterLevel;
            }

            public double getStorageCapacity() {
                return storageCapacity;
            }

            public void setStorageCapacity(double storageCapacity) {
                this.storageCapacity = storageCapacity;
            }
        }
    }
}
