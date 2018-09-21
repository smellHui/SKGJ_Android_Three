package com.tepia.main.model.detai;

import com.tepia.base.http.BaseResponse;

/**
 * Created by liying on 2018/8/21.
 */

public class ReservoirDetailBean extends BaseResponse {

    /**
     * data : {"reservoirId":"66fb3d579d084daf8a7d35d9d9612213","reservoirCode":"NTC520321019L","reservoirProduce":"","areaCode":"520321","reservoir":"绿竹坝水库","reservoirType":"2","reservoirAddress":"贵州省播州区乐山镇","reservoirLongitude":"106.684497","reservoirLatitude":"27.669843","manageDepId":"遵义市水利局","locatedBasin":"","locatedRiver":"","locatedBrook":"乐民河上游","mainFunction":"防洪、灌溉、旅游","buildStartDate":"2012-11-01","buildEndDate":"2015-03-01","normalImpoundedLevel":983,"damType":"0","damLength":186.5,"damHeight":48,"damWidth":7,"damCrestElevation":986,"damBotmMaxWidth":132.61,"deadWaterLevel":954,"deathLevelVolume":15,"waterLevelVolume":463,"effectiveVolume":448,"designFloodWaterLevel":984.63,"checkFloodWaterLevel":985.37,"rainwaterCollectingArea":448,"capacityCoefficient":0.53,"lrrigatedFarmlandArea":43280,"avgIrrigationWaterSupply":528,"drinkingWaterSupply":39.8,"avgTotalWaterSupply":568,"utilizationRateOfDevelopment":66.7,"spillwayType":"开敞式溢洪道","spillwayLength":197,"diversionCanalLength":32,"canalBottomElevation":981.5,"overflowMode":"自由溢流","weirCrestElevation":983,"remarks":"80002002","status":"0","createDate":"2018-08-14 22:51:04","updateBy":"超级管理员","updateDate":"2018-08-21 11:30:39","areaName":"播州区"}
     */

    private ReservoirBean data;

    public ReservoirBean getData() {
        return data;
    }

    public void setData(ReservoirBean data) {
        this.data = data;
    }

}
