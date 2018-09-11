package com.tepia.main.model.map;

import com.tepia.base.http.BaseResponse;

import java.io.Serializable;
import java.util.List;

/**
 * 所有水库
 * @author 44822
 */
public class ReservoirResponse extends BaseResponse implements Serializable{

    /**
     * code : 0
     * count : 0
     * data : [{}] */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * reservoirId : 66fb3d579d084daf8a7d35d9d9612210
         * reservoirCode : bz000009
         * areaCode : 520321
         * reservoir : 东风水库
         * reservoirType : 1
         * reservoirAddress : 南白镇
         * reservoirLongitude : 106.806111
         * reservoirLatitude : 27.490556
         * manageDepId : 播州区水库局东风水库管理所
         * buildEndDate : 196405
         * normalImpoundedLevel : 939.7
         * deadWaterLevel : 931.6
         * deathLevelVolume : 5.0
         * effectiveVolume : 120.0
         * designFloodWaterLevel : 941.3
         * checkFloodWaterLevel : 942.2
         * rainwaterCollectingArea : 18.9
         * contractStartDate : 2018-01-01 00:00:00
         * contractEndDate : 2018-12-31 00:00:00
         * status : 0
         * areaName : 遵义县
         * rz : 948.21
         * w : 122.3
         * reservoirProduce : 中桥水库
         * locatedBasin :
         * locatedRiver :
         * locatedBrook :
         * mainFunction : 1
         * buildStartDate : 灌溉,旅游,饮用
         * yearCleanCount : 36
         * yearMaintainCount : 48
         * remarks :  遵义市播州区中桥水库是一个小型水库，以灌溉、饮用为主
         * createBy :
         * createDate : 2018-06-20 09:46:00
         * updateBy :
         */

        private String reservoirId;
        private String reservoirCode;
        private String areaCode;
        private String reservoir;
        private String reservoirType;
        private String reservoirAddress;
        private String reservoirLongitude;
        private String reservoirLatitude;
        private String manageDepId;
        private String buildEndDate;
        private double normalImpoundedLevel;
        private double deadWaterLevel;
        private double deathLevelVolume;
        private double effectiveVolume;
        private double designFloodWaterLevel;
        private double checkFloodWaterLevel;
        private double rainwaterCollectingArea;
        private String contractStartDate;
        private String contractEndDate;
        private String status;
        private String areaName;
        private String rz;
        private String w;
        private String reservoirProduce;
        private String locatedBasin;
        private String locatedRiver;
        private String locatedBrook;
        private String mainFunction;
        private String buildStartDate;
        private int yearCleanCount;
        private int yearMaintainCount;
        private String remarks;
        private String createBy;
        private String createDate;
        private String updateBy;

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

        public String getAreaCode() {
            return areaCode;
        }

        public void setAreaCode(String areaCode) {
            this.areaCode = areaCode;
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

        public String getManageDepId() {
            return manageDepId;
        }

        public void setManageDepId(String manageDepId) {
            this.manageDepId = manageDepId;
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

        public double getDeadWaterLevel() {
            return deadWaterLevel;
        }

        public void setDeadWaterLevel(double deadWaterLevel) {
            this.deadWaterLevel = deadWaterLevel;
        }

        public double getDeathLevelVolume() {
            return deathLevelVolume;
        }

        public void setDeathLevelVolume(double deathLevelVolume) {
            this.deathLevelVolume = deathLevelVolume;
        }

        public double getEffectiveVolume() {
            return effectiveVolume;
        }

        public void setEffectiveVolume(double effectiveVolume) {
            this.effectiveVolume = effectiveVolume;
        }

        public double getDesignFloodWaterLevel() {
            return designFloodWaterLevel;
        }

        public void setDesignFloodWaterLevel(double designFloodWaterLevel) {
            this.designFloodWaterLevel = designFloodWaterLevel;
        }

        public double getCheckFloodWaterLevel() {
            return checkFloodWaterLevel;
        }

        public void setCheckFloodWaterLevel(double checkFloodWaterLevel) {
            this.checkFloodWaterLevel = checkFloodWaterLevel;
        }

        public double getRainwaterCollectingArea() {
            return rainwaterCollectingArea;
        }

        public void setRainwaterCollectingArea(double rainwaterCollectingArea) {
            this.rainwaterCollectingArea = rainwaterCollectingArea;
        }

        public String getContractStartDate() {
            return contractStartDate;
        }

        public void setContractStartDate(String contractStartDate) {
            this.contractStartDate = contractStartDate;
        }

        public String getContractEndDate() {
            return contractEndDate;
        }

        public void setContractEndDate(String contractEndDate) {
            this.contractEndDate = contractEndDate;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public String getRz() {
            return rz;
        }

        public void setRz(String rz) {
            this.rz = rz;
        }

        public String getW() {
            return w;
        }

        public void setW(String w) {
            this.w = w;
        }

        public String getReservoirProduce() {
            return reservoirProduce;
        }

        public void setReservoirProduce(String reservoirProduce) {
            this.reservoirProduce = reservoirProduce;
        }

        public String getLocatedBasin() {
            return locatedBasin;
        }

        public void setLocatedBasin(String locatedBasin) {
            this.locatedBasin = locatedBasin;
        }

        public String getLocatedRiver() {
            return locatedRiver;
        }

        public void setLocatedRiver(String locatedRiver) {
            this.locatedRiver = locatedRiver;
        }

        public String getLocatedBrook() {
            return locatedBrook;
        }

        public void setLocatedBrook(String locatedBrook) {
            this.locatedBrook = locatedBrook;
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

        public int getYearCleanCount() {
            return yearCleanCount;
        }

        public void setYearCleanCount(int yearCleanCount) {
            this.yearCleanCount = yearCleanCount;
        }

        public int getYearMaintainCount() {
            return yearMaintainCount;
        }

        public void setYearMaintainCount(int yearMaintainCount) {
            this.yearMaintainCount = yearMaintainCount;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }
    }
}
