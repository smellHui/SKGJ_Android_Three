package com.tepia.main.model.weather;

import com.tepia.base.http.BaseResponse;

import java.util.List;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2018-09-12
 * Time            :       21:25
 * Version         :       1.0
 * 功能描述        :
 **/
public class WeatherResponse extends BaseResponse {

    /**
     * status : 0
     * result : {"city":"武汉","cityid":"179","citycode":"101200101","date":"2018-09-12","week":"星期三","weather":"多云","temp":"23","temphigh":"29","templow":"21","img":"1","humidity":"86","pressure":"1009","windspeed":"2.0","winddirect":"东风","windpower":"1级","updatetime":"2018-09-12 19:15:00","index":[{"iname":"空调指数","ivalue":"部分时间开启","detail":"天气热，到中午的时候您将会感到有点热，因此建议在午后较热时开启制冷空调。"},{"iname":"运动指数","ivalue":"适宜","detail":"天气较好，赶快投身大自然参与户外运动，尽情感受运动的快乐吧。"},{"iname":"紫外线指数","ivalue":"弱","detail":"紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。"},{"iname":"感冒指数","ivalue":"少发","detail":"各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。"},{"iname":"洗车指数","ivalue":"不宜","detail":"不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"},{"iname":"空气污染扩散指数","ivalue":"中","detail":"气象条件对空气污染物稀释、扩散和清除无明显影响，易感人群应适当减少室外活动时间。"},{"iname":"穿衣指数","ivalue":"热","detail":"天气热，建议着短裙、短裤、短薄外套、T恤等夏季服装。"}],"aqi":{"so2":"15","so224":"12","no2":"46","no224":"58","co":"1.250","co24":"1.020","o3":"124","o38":"128","o324":"128","pm10":"83","pm1024":"76","pm2_5":"51","pm2_524":"45","iso2":"6","ino2":"24","ico":"13","io3":"40","io38":"74","ipm10":"67","ipm2_5":"71","aqi":"74","primarypollutant":"O3","quality":"良","timepoint":"2018-09-12 18:00:00","aqiinfo":{"level":"二级","color":"#FFFF00","affect":"空气质量可接受，但某些污染物可能对极少数异常敏感人群健康有较弱影响","measure":"极少数异常敏感人群应减少户外活动"}},"daily":[{"date":"2018-09-12","week":"星期三","sunrise":"06:04","sunset":"18:34","night":{"weather":"阵雨","templow":"21","img":"3","winddirect":"东北风","windpower":"微风"},"day":{"weather":"多云","temphigh":"29","img":"1","winddirect":"东北风","windpower":"微风"}},{"date":"2018-09-13","week":"星期四","sunrise":"06:05","sunset":"18:32","night":{"weather":"多云","templow":"21","img":"1","winddirect":"东北风","windpower":"微风"},"day":{"weather":"多云","temphigh":"28","img":"1","winddirect":"东北风","windpower":"微风"}},{"date":"2018-09-14","week":"星期五","sunrise":"06:05","sunset":"18:31","night":{"weather":"晴","templow":"23","img":"0","winddirect":"东北风","windpower":"微风"},"day":{"weather":"多云","temphigh":"32","img":"1","winddirect":"东风","windpower":"微风"}},{"date":"2018-09-15","week":"星期六","sunrise":"06:06","sunset":"18:30","night":{"weather":"多云","templow":"22","img":"1","winddirect":"北风","windpower":"4-5级"},"day":{"weather":"多云","temphigh":"33","img":"1","winddirect":"北风","windpower":"4-5级"}},{"date":"2018-09-16","week":"星期日","sunrise":"06:06","sunset":"18:28","night":{"weather":"多云","templow":"22","img":"1","winddirect":"北风","windpower":"4-5级"},"day":{"weather":"多云","temphigh":"32","img":"1","winddirect":"北风","windpower":"4-5级"}},{"date":"2018-09-17","week":"星期一","sunrise":"06:07","sunset":"18:27","night":{"weather":"多云","templow":"23","img":"1","winddirect":"东北风","windpower":"3-5级"},"day":{"weather":"多云","temphigh":"32","img":"1","winddirect":"东北风","windpower":"4-5级"}},{"date":"2018-09-18","week":"星期二","sunrise":"06:07","sunset":"18:26","night":{"weather":"小雨","templow":"21","img":"7","winddirect":"持续无风向","windpower":"微风"},"day":{"weather":"多云","temphigh":"31","img":"1","winddirect":"东风","windpower":"微风"}}],"hourly":[{"time":"19:00","weather":"多云","temp":"25","img":"1"},{"time":"20:00","weather":"多云","temp":"24","img":"1"},{"time":"21:00","weather":"多云","temp":"24","img":"1"},{"time":"22:00","weather":"多云","temp":"23","img":"1"},{"time":"23:00","weather":"多云","temp":"23","img":"1"},{"time":"0:00","weather":"多云","temp":"23","img":"1"},{"time":"1:00","weather":"多云","temp":"22","img":"1"},{"time":"2:00","weather":"多云","temp":"22","img":"1"},{"time":"3:00","weather":"阵雨","temp":"22","img":"3"},{"time":"4:00","weather":"阵雨","temp":"21","img":"3"},{"time":"5:00","weather":"多云","temp":"22","img":"1"},{"time":"6:00","weather":"多云","temp":"22","img":"1"},{"time":"7:00","weather":"多云","temp":"22","img":"1"},{"time":"8:00","weather":"多云","temp":"22","img":"1"},{"time":"9:00","weather":"多云","temp":"23","img":"1"},{"time":"10:00","weather":"多云","temp":"24","img":"1"},{"time":"11:00","weather":"多云","temp":"24","img":"1"},{"time":"12:00","weather":"多云","temp":"25","img":"1"},{"time":"13:00","weather":"多云","temp":"27","img":"1"},{"time":"14:00","weather":"多云","temp":"27","img":"1"},{"time":"15:00","weather":"多云","temp":"28","img":"1"},{"time":"16:00","weather":"多云","temp":"28","img":"1"},{"time":"17:00","weather":"多云","temp":"27","img":"1"},{"time":"18:00","weather":"多云","temp":"27","img":"1"}]}
     */

    private String status;
    private WeahterBean result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public WeahterBean getResult() {
        return result;
    }

    public void setResult(WeahterBean result) {
        this.result = result;
    }

}
