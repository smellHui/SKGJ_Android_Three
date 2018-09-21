package com.tepia.main.model.user;

import com.tepia.base.http.BaseResponse;
import com.tepia.main.model.user.homepageinfo.HomeGetReservoirInfoBean;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2018-09-21
 * Time            :       14:47
 * Version         :       1.0
 * 功能描述        :
 **/
public class HomeGetReservoirInfoResponse extends BaseResponse{


    /**
     * data : {"executeFrequency":{"maintain":{"noFlood":1,"flood":5},"clean":{"noFlood":2,"flood":6},"inspection":{"noFlood":1,"flood":5}},"material":[{"id":"ab432a971b87488091fea949e2c0633e","meName":"器材1","meType":"1","meTotals":"1套","position":"绿竹坝仓库"}],"personDuty":[{"jobName":"行政负责人","mobile":"","userName":"播州区管理科长"}],"reservoirWaterLevel":{"floodSeasonStartDate":"05-01","floodSeasonWaterLevel":973,"checkFloodWaterLevel":985.37,"dead_waterLevel":954,"isFlood":false,"designFloodWaterLevel":984.63,"reservoirId":"66fb3d579d084daf8a7d35d9d9612213","normalImpoundedLevel":983,"damCrestElevation":986,"floodSeasonEndDate":"05-31","stcd":"asdfasdf","tm":"2018-01-02 00:00:00","rz":"1000","w":"20"},"storageCapacity":[{"waterLevel":0,"storageCapacity":0}],"OAMStatistics":{"maintain":{"problemCount":0,"processedProblem":0,"workOrderCount":1,"notProcessedProblem":0},"clean":{"problemCount":0,"processedProblem":0,"workOrderCount":0,"notProcessedProblem":0},"inspection":{"problemCount":0,"processedProblem":0,"workOrderCount":0,"notProcessedProblem":0}}}
     */

    private HomeGetReservoirInfoBean data;

    public HomeGetReservoirInfoBean getData() {
        return data;
    }

    public void setData(HomeGetReservoirInfoBean data) {
        this.data = data;
    }

}
