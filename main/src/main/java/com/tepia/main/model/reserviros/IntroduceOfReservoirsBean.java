package com.tepia.main.model.reserviros;

import com.tepia.base.http.BaseResponse;

/**
 * Created by      android studio
 *
 * @author :       ly
 * Date            :       2018-09-25
 * Time            :       下午3:53
 * Version         :       1.0
 * location        :       武汉研发中心
 * 功能描述         :       水库简介
 **/
public class IntroduceOfReservoirsBean extends BaseResponse{

    /**
     * data : {"reservoir":"绿竹坝水库","reservoirAddress":"贵州省播州区乐山镇","reservoirLongitude":"106.6844977","reservoirLatitude":"27.66984378","mainFunction":"防洪、灌溉、旅游","buildStartDate":"2012-11-01","buildEndDate":"2015-03-01","normalImpoundedLevel":983,"damType":"0","damLength":186.5,"damHeight":48,"damWidth":7,"damCrestElevation":986,"damBotmMaxWidth":132.61,"capacityCoefficient":0.53}
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
         * reservoir : 绿竹坝水库
         * reservoirAddress : 贵州省播州区乐山镇
         * reservoirLongitude : 106.6844977
         * reservoirLatitude : 27.66984378
         * mainFunction : 防洪、灌溉、旅游
         * buildStartDate : 2012-11-01
         * buildEndDate : 2015-03-01
         * normalImpoundedLevel : 983.0
         * damType : 0
         * damLength : 186.5
         * damHeight : 48.0
         * damWidth : 7.0
         * damCrestElevation : 986.0
         * damBotmMaxWidth : 132.61
         * capacityCoefficient : 0.53
         */

        /**
         * 水库名
         */
        private String reservoir;
        private String reservoirProduce;
        private String reservoirType;
        private String areaName;
        /**
         * 水库地址
         */
        private String reservoirAddress;
        private String reservoirLongitude;
        private String reservoirLatitude;
        /**
         * 主要功能
         */
        private String mainFunction;
        /**
         * 开始建设时间
         */
        private String buildStartDate;
        /**
         * 竣工时间
         */
        private String buildEndDate;
        /**
         * 正常蓄水位
         */
        private double normalImpoundedLevel;
        /**
         * 大坝类型
         */
        private String damType;
        /**
         * 坝长
         */
        private double damLength;
        /**
         * 坝高
         */
        private double damHeight;
        /**
         * 坝宽
         */
        private double damWidth;
        /**
         * 坝顶高程
         */
        private double damCrestElevation;
        /**
         * 坝底最大宽度
         */
        private double damBotmMaxWidth;
        /**
         * 库容系数
         */
        private double capacityCoefficient;

        public String getReservoirProduce() {
            return reservoirProduce;
        }

        public void setReservoirProduce(String reservoirProduce) {
            this.reservoirProduce = reservoirProduce;
        }

        public String getReservoir() {
            return reservoir;
        }

        public void setReservoir(String reservoir) {
            this.reservoir = reservoir;
        }

        public String getReservoirAddress() {
            return reservoirAddress;
        }

        public void setReservoirAddress(String reservoirAddress) {
            this.reservoirAddress = reservoirAddress;
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

        public String getMainFunction() {
            return mainFunction;
        }

        public void setMainFunction(String mainFunction) {
            this.mainFunction = mainFunction;
        }

        public String getBuildStartDate() {
            return buildStartDate;
        }

        public void setBuildStartDate(String buildStartDate) {
            this.buildStartDate = buildStartDate;
        }

        public String getBuildEndDate() {
            return buildEndDate;
        }

        public void setBuildEndDate(String buildEndDate) {
            this.buildEndDate = buildEndDate;
        }

        public double getNormalImpoundedLevel() {
            return normalImpoundedLevel;
        }

        public void setNormalImpoundedLevel(double normalImpoundedLevel) {
            this.normalImpoundedLevel = normalImpoundedLevel;
        }

        public String getDamType() {
            return damType;
        }

        public void setDamType(String damType) {
            this.damType = damType;
        }

        public double getDamLength() {
            return damLength;
        }

        public void setDamLength(double damLength) {
            this.damLength = damLength;
        }

        public double getDamHeight() {
            return damHeight;
        }

        public void setDamHeight(double damHeight) {
            this.damHeight = damHeight;
        }

        public double getDamWidth() {
            return damWidth;
        }

        public void setDamWidth(double damWidth) {
            this.damWidth = damWidth;
        }

        public double getDamCrestElevation() {
            return damCrestElevation;
        }

        public void setDamCrestElevation(double damCrestElevation) {
            this.damCrestElevation = damCrestElevation;
        }

        public double getDamBotmMaxWidth() {
            return damBotmMaxWidth;
        }

        public void setDamBotmMaxWidth(double damBotmMaxWidth) {
            this.damBotmMaxWidth = damBotmMaxWidth;
        }

        public double getCapacityCoefficient() {
            return capacityCoefficient;
        }

        public void setCapacityCoefficient(double capacityCoefficient) {
            this.capacityCoefficient = capacityCoefficient;
        }

        public String getReservoirType() {
            return reservoirType;
        }

        public void setReservoirType(String reservoirType) {
            this.reservoirType = reservoirType;
        }

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }
    }
}
