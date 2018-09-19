package com.tepia.main.model.weather;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2018-09-19
 * Time            :       15:07
 * Version         :       1.0
 * 功能描述        :
 **/
public  class Weather2Bean {
    /**
     * f4 : {"day_wind_power":"3-4级 5.5~7.9m/s","night_wind_power":"0-3级 <5.4m/s","night_weather_code":"01","day_weather":"多云","sun_begin_end":"06:11|18:20","air_press":"1013 hPa","index":{"yh":{"desc":"不用担心天气来调皮捣乱而影响了兴致。 ","title":"较适宜"},"zs":{"desc":"气温不高，中暑几率极低。","title":"不容易中暑"},"cl":{"desc":"推荐您进行室内运动。","title":"较适宜"},"travel":{"desc":"天热注意防晒，可选择水上娱乐项目。","title":"较适宜"},"ag":{"desc":"暂缺","title":"暂缺"},"beauty":{"desc":"请选用露质面霜打底，水质无油粉底霜。","title":"去油"},"pj":{"desc":"天气炎热，可适量饮用啤酒，不要过量。","title":"适宜"},"dy":{"desc":"天气稍热会对垂钓产生一定影响。","title":"较适宜"},"nl":{"desc":"暂缺","title":"暂缺"},"pk":{"desc":"气温略高，放风筝时戴上太阳帽。","title":"较适宜"},"uv":{"desc":"辐射较弱，涂擦SPF12-15、PA+护肤品","title":"弱"},"aqi":{"desc":"空气很好，可以外出活动，呼吸新鲜空气","title":"优质"},"gj":{"desc":"风稍大，出门逛街前记得给秀发定型。","title":"较适宜"},"ac":{"desc":"暂缺","title":"暂缺"},"mf":{"desc":"暂缺","title":"暂缺"},"ls":{"desc":"天气阴沉，不太适宜晾晒。","title":"不适宜"},"glass":{"desc":"不需要佩戴","title":"不需要"},"xq":{"desc":"温度适宜，心情会不错。","title":"好"},"clothes":{"desc":"建议穿长袖衬衫单裤等服装","title":"舒适"},"sports":{"desc":"推荐您进行室内运动。","title":"较适宜"},"hc":{"desc":"风大，需注意及时添衣。","title":"较适宜"},"comfort":{"desc":"偏热或较热，部分人感觉不舒适","title":"较差"},"wash_car":{"desc":"无雨且风力较小，易保持清洁度","title":"较适宜"},"cold":{"desc":"感冒机率较低，避免长期处于空调屋中。","title":"少发"}},"day_weather_code":"01","night_weather":"多云","night_weather_pic":"http://app1.showapi.com/weather/icon/night/01.png","day":"20180922","day_wind_direction":"东北风","jiangshui":"2%","day_air_temperature":"25","night_wind_direction":"东北风","weekday":6,"ziwaixian":"弱","night_air_temperature":"14","day_weather_pic":"http://app1.showapi.com/weather/icon/day/01.png","3hourForcast":[{"temperature":"23","wind_direction":"北风","temperature_min":"19","hour":"8时-14时","weather_pic":"http://app1.showapi.com/weather/icon/day/07.png","temperature_max":"24","wind_power":"<3级,3","weather":"小雨"},{"temperature":"24","wind_direction":"东北风","temperature_min":"20","hour":"14时-20时","weather_pic":"http://app1.showapi.com/weather/icon/day/01.png","temperature_max":"24","wind_power":"3-4级,2","weather":"多云"},{"temperature":"20","wind_direction":"东北风","temperature_min":"16","hour":"20时-2时","weather_pic":"http://app1.showapi.com/weather/icon/night/01.png","temperature_max":"24","wind_power":"3-4级,0","weather":"多云"},{"temperature":"16","wind_direction":"东北风","temperature_min":"16","hour":"2时-8时","weather_pic":"http://app1.showapi.com/weather/icon/night/01.png","temperature_max":"20","wind_power":"<3级,0","weather":"多云"}]}
     * f5 : {"day_wind_power":"3-4级 5.5~7.9m/s","night_wind_power":"3-4级 5.5~7.9m/s","night_weather_code":"00","day_weather":"晴","sun_begin_end":"06:11|18:19","air_press":"1013 hPa","index":{"yh":{"desc":"不用担心天气来调皮捣乱而影响了兴致。 ","title":"较适宜"},"zs":{"desc":"气温不高，中暑几率极低。","title":"不容易中暑"},"cl":{"desc":"天气炎热，建议停止户外运动。","title":"较不宜"},"travel":{"desc":"天气很热，如外出可选择水上娱乐项目。","title":"较不宜"},"ag":{"desc":"暂缺","title":"暂缺"},"beauty":{"desc":"请选用露质面霜打底，水质无油粉底霜。","title":"去油"},"pj":{"desc":"天气炎热，可适量饮用啤酒，不要过量。","title":"适宜"},"dy":{"desc":"天气稍热会对垂钓产生一定影响。","title":"较适宜"},"nl":{"desc":"暂缺","title":"暂缺"},"pk":{"desc":"气温略高，放风筝时戴上太阳帽。","title":"较适宜"},"uv":{"desc":"涂擦SPF大于15、PA+防晒护肤品","title":"强"},"aqi":{"desc":"空气很好，可以外出活动，呼吸新鲜空气","title":"优质"},"gj":{"desc":"风稍大，出门逛街前记得给秀发定型。","title":"较适宜"},"ac":{"desc":"暂缺","title":"暂缺"},"mf":{"desc":"暂缺","title":"暂缺"},"ls":{"desc":"天气不错，抓紧时机让衣物晒太阳吧。","title":"较适宜"},"glass":{"desc":"必要佩戴","title":"必要"},"xq":{"desc":"温度适宜，心情会不错。","title":"好"},"clothes":{"desc":"适合穿T恤、短薄外套等夏季服装","title":"热"},"sports":{"desc":"天气炎热，建议停止户外运动。","title":"较不宜"},"hc":{"desc":"风稍大会对划船产生一定影响。","title":"较适宜"},"comfort":{"desc":"偏热或较热，部分人感觉不舒适","title":"较差"},"wash_car":{"desc":"无雨且风力较小，易保持清洁度","title":"较适宜"},"cold":{"desc":"感冒机率较低，避免长期处于空调屋中。","title":"少发"}},"day_weather_code":"00","night_weather":"晴","night_weather_pic":"http://app1.showapi.com/weather/icon/night/00.png","day":"20180923","day_wind_direction":"东北风","jiangshui":"0%","day_air_temperature":"28","night_wind_direction":"东北风","weekday":7,"ziwaixian":"强","night_air_temperature":"15","day_weather_pic":"http://app1.showapi.com/weather/icon/day/00.png","3hourForcast":[{"temperature":"19","wind_direction":"东北风","temperature_min":"16","hour":"8时-14时","weather_pic":"http://app1.showapi.com/weather/icon/day/01.png","temperature_max":"28","wind_power":"<3级,2","weather":"多云"},{"temperature":"28","wind_direction":"东北风","temperature_min":"19","hour":"14时-20时","weather_pic":"http://app1.showapi.com/weather/icon/day/00.png","temperature_max":"28","wind_power":"3-4级,1","weather":"晴"},{"temperature":"21","wind_direction":"东北风","temperature_min":"16","hour":"20时-2时","weather_pic":"http://app1.showapi.com/weather/icon/night/00.png","temperature_max":"28","wind_power":"3-4级,0","weather":"晴"},{"temperature":"16","wind_direction":"东北风","temperature_min":"16","hour":"2时-8时","weather_pic":"http://app1.showapi.com/weather/icon/night/00.png","temperature_max":"21","wind_power":"<3级,0","weather":"晴"}]}
     * hourDataList : [{"wind_direction":"南风","aqi":"48","weather_pic":"http://app1.showapi.com/weather/icon/night/00.png","wind_power":"1级","temperature_time":"00:00","weather_code":"00","temperature":"27","sd":"90%","aqiDetail":{"quality":"优质","aqi":"48","pm10":"47","area":"武汉","co":"0.8","o3":"41","so2":"5","no2":"39","primary_pollutant":"","o3_8h":"78","num":"211","pm2_5":"21"},"weather":"晴"},{"wind_direction":"静风","aqi":"43","weather_pic":"http://app1.showapi.com/weather/icon/night/00.png","wind_power":"0级","temperature_time":"00:30","weather_code":"00","temperature":"27","sd":"92%","aqiDetail":{"quality":"优质","aqi":"43","pm10":"43","area":"武汉","co":"0.789","o3":"41","so2":"5","no2":"34","primary_pollutant":"","o3_8h":"70","num":"190","pm2_5":"22"},"weather":"晴"},{"wind_direction":"南风","aqi":"43","weather_pic":"http://app1.showapi.com/weather/icon/night/01.png","wind_power":"1级","temperature_time":"01:00","weather_code":"01","temperature":"27","sd":"91%","aqiDetail":{"quality":"优质","aqi":"43","pm10":"43","area":"武汉","co":"0.789","o3":"41","so2":"5","no2":"34","primary_pollutant":"","o3_8h":"70","num":"190","pm2_5":"22"},"weather":"多云"},{"wind_direction":"静风","aqi":"37","weather_pic":"http://app1.showapi.com/weather/icon/night/01.png","wind_power":"0级","temperature_time":"01:30","weather_code":"01","temperature":"26","sd":"91%","aqiDetail":{"quality":"优质","aqi":"37","pm10":"37","area":"武汉","co":"0.8","o3":"41","so2":"5","no2":"31","primary_pollutant":"","o3_8h":"62","num":"160","pm2_5":"19"},"weather":"多云"},{"wind_direction":"东南风 ","aqi":"37","weather_pic":"http://app1.showapi.com/weather/icon/night/01.png","wind_power":"1级","temperature_time":"02:00","weather_code":"01","temperature":"26","sd":"92%","aqiDetail":{"quality":"优质","aqi":"37","pm10":"37","area":"武汉","co":"0.8","o3":"41","so2":"5","no2":"31","primary_pollutant":"","o3_8h":"62","num":"160","pm2_5":"19"},"weather":"多云"},{"wind_direction":"东南风 ","aqi":"32","weather_pic":"http://app1.showapi.com/weather/icon/night/01.png","wind_power":"1级","temperature_time":"02:30","weather_code":"01","temperature":"26","sd":"92%","aqiDetail":{"quality":"优质","aqi":"32","pm10":"30","area":"武汉","co":"0.775","o3":"32","so2":"5","no2":"33","primary_pollutant":"","o3_8h":"41","num":"136","pm2_5":"17"},"weather":"多云"},{"wind_direction":"静风","aqi":"32","weather_pic":"http://app1.showapi.com/weather/icon/night/01.png","wind_power":"0级","temperature_time":"03:00","weather_code":"01","temperature":"26","sd":"93%","aqiDetail":{"quality":"优质","aqi":"32","pm10":"30","area":"武汉","co":"0.775","o3":"32","so2":"5","no2":"33","primary_pollutant":"","o3_8h":"41","num":"136","pm2_5":"17"},"weather":"多云"},{"wind_direction":"静风","aqi":"29","weather_pic":"http://app1.showapi.com/weather/icon/night/01.png","wind_power":"0级","temperature_time":"03:30","weather_code":"01","temperature":"26","sd":"93%","aqiDetail":{"quality":"优质","aqi":"29","pm10":"29","area":"武汉","co":"0.737","o3":"23","so2":"5","no2":"37","primary_pollutant":"","o3_8h":"28","num":"114","pm2_5":"15"},"weather":"多云"},{"wind_direction":"静风","aqi":"29","weather_pic":"http://app1.showapi.com/weather/icon/night/02.png","wind_power":"0级","temperature_time":"04:00","weather_code":"02","temperature":"26","sd":"93%","aqiDetail":{"quality":"优质","aqi":"29","pm10":"29","area":"武汉","co":"0.737","o3":"23","so2":"5","no2":"37","primary_pollutant":"","o3_8h":"28","num":"114","pm2_5":"15"},"weather":"阴"},{"wind_direction":"东南风 ","aqi":"38","weather_pic":"http://app1.showapi.com/weather/icon/night/02.png","wind_power":"1级","temperature_time":"04:30","weather_code":"02","temperature":"26","sd":"94%","aqiDetail":{"quality":"优质","aqi":"38","pm10":"37","area":"武汉","co":"0.738","o3":"14","so2":"5","no2":"43","primary_pollutant":"","o3_8h":"23","num":"174","pm2_5":"15"},"weather":"阴"},{"wind_direction":"静风","aqi":"38","weather_pic":"http://app1.showapi.com/weather/icon/night/02.png","wind_power":"0级","temperature_time":"05:00","weather_code":"02","temperature":"26","sd":"93%","aqiDetail":{"quality":"优质","aqi":"38","pm10":"37","area":"武汉","co":"0.738","o3":"14","so2":"5","no2":"43","primary_pollutant":"","o3_8h":"23","num":"174","pm2_5":"15"},"weather":"阴"},{"wind_direction":"东南风 ","aqi":"40","weather_pic":"http://app1.showapi.com/weather/icon/night/02.png","wind_power":"1级","temperature_time":"05:30","weather_code":"02","temperature":"26","sd":"94%","aqiDetail":{"quality":"优质","aqi":"40","pm10":"39","area":"武汉","co":"0.75","o3":"10","so2":"5","no2":"41","primary_pollutant":"","o3_8h":"20","num":"202","pm2_5":"14"},"weather":"阴"},{"wind_direction":"静风","aqi":"40","weather_pic":"http://app1.showapi.com/weather/icon/night/02.png","wind_power":"0级","temperature_time":"06:00","weather_code":"02","temperature":"26","sd":"95%","aqiDetail":{"quality":"优质","aqi":"40","pm10":"39","area":"武汉","co":"0.75","o3":"10","so2":"5","no2":"41","primary_pollutant":"","o3_8h":"20","num":"202","pm2_5":"14"},"weather":"阴"},{"wind_direction":"西南风","aqi":"40","weather_pic":"http://app1.showapi.com/weather/icon/night/02.png","wind_power":"1级","temperature_time":"06:30","weather_code":"02","temperature":"26","sd":"94%","aqiDetail":{"quality":"优质","aqi":"40","pm10":"39","area":"武汉","co":"0.763","o3":"12","so2":"5","no2":"38","primary_pollutant":"","o3_8h":"18","num":"202","pm2_5":"16"},"weather":"阴"},{"wind_direction":"东南风 ","aqi":"40","weather_pic":"http://app1.showapi.com/weather/icon/day/01.png","wind_power":"1级","temperature_time":"07:00","weather_code":"01","temperature":"26","sd":"96%","aqiDetail":{"quality":"优质","aqi":"40","pm10":"39","area":"武汉","co":"0.763","o3":"12","so2":"5","no2":"38","primary_pollutant":"","o3_8h":"18","num":"202","pm2_5":"16"},"weather":"多云"},{"wind_direction":"南风","aqi":"39","weather_pic":"http://app1.showapi.com/weather/icon/day/01.png","wind_power":"1级","temperature_time":"07:34","weather_code":"01","temperature":"25","sd":"96%","aqiDetail":{"quality":"优质","aqi":"39","pm10":"37","area":"武汉","co":"0.812","o3":"9","so2":"5","no2":"40","primary_pollutant":"","o3_8h":"17","num":"191","pm2_5":"14"},"weather":"多云"},{"wind_direction":"南风","aqi":"39","weather_pic":"http://app1.showapi.com/weather/icon/day/01.png","wind_power":"1级","temperature_time":"08:00","weather_code":"01","temperature":"26","sd":"97%","aqiDetail":{"quality":"优质","aqi":"39","pm10":"37","area":"武汉","co":"0.812","o3":"9","so2":"5","no2":"40","primary_pollutant":"","o3_8h":"17","num":"191","pm2_5":"14"},"weather":"多云"},{"wind_direction":"南风","aqi":"38","weather_pic":"http://app1.showapi.com/weather/icon/day/01.png","wind_power":"1级","temperature_time":"08:30","weather_code":"01","temperature":"26","sd":"92%","aqiDetail":{"quality":"优质","aqi":"38","pm10":"38","area":"武汉","co":"0.767","o3":"10","so2":"5","no2":"37","primary_pollutant":"","o3_8h":"16","num":"181","pm2_5":"15"},"weather":"多云"},{"wind_direction":"北风","aqi":"42","weather_pic":"http://app1.showapi.com/weather/icon/day/02.png","wind_power":"1级","temperature_time":"09:03","weather_code":"02","temperature":"26","sd":"85%","aqiDetail":{"quality":"优质","aqi":"42","pm10":"40","area":"武汉","co":"0.822","o3":"16","so2":"6","no2":"36","primary_pollutant":"","o3_8h":"16","num":"198","pm2_5":"18"},"weather":"阴"},{"wind_direction":"北风","aqi":"42","weather_pic":"http://app1.showapi.com/weather/icon/day/02.png","wind_power":"1级","temperature_time":"09:30","weather_code":"02","temperature":"26","sd":"85%","aqiDetail":{"quality":"优质","aqi":"42","pm10":"40","area":"武汉","co":"0.822","o3":"16","so2":"6","no2":"36","primary_pollutant":"","o3_8h":"16","num":"198","pm2_5":"18"},"weather":"阴"},{"wind_direction":"北风","aqi":"42","weather_pic":"http://app1.showapi.com/weather/icon/day/02.png","wind_power":"1级","temperature_time":"10:01","weather_code":"02","temperature":"26","sd":"85%","aqiDetail":{"quality":"优质","aqi":"42","pm10":"40","area":"武汉","co":"0.822","o3":"16","so2":"6","no2":"36","primary_pollutant":"","o3_8h":"16","num":"198","pm2_5":"18"},"weather":"阴"},{"wind_direction":"北风","aqi":"43","weather_pic":"http://app1.showapi.com/weather/icon/day/02.png","wind_power":"1级","temperature_time":"10:30","weather_code":"02","temperature":"26","sd":"85%","aqiDetail":{"quality":"优质","aqi":"43","pm10":"44","area":"武汉","co":"0.856","o3":"24","so2":"6","no2":"36","primary_pollutant":"","o3_8h":"16","num":"183","pm2_5":"23"},"weather":"阴"},{"wind_direction":"北风","aqi":"43","weather_pic":"http://app1.showapi.com/weather/icon/day/02.png","wind_power":"1级","temperature_time":"11:01","weather_code":"02","temperature":"26","sd":"85%","aqiDetail":{"quality":"优质","aqi":"43","pm10":"44","area":"武汉","co":"0.856","o3":"24","so2":"6","no2":"36","primary_pollutant":"","o3_8h":"16","num":"183","pm2_5":"23"},"weather":"阴"},{"wind_direction":"西南风","aqi":"50","weather_pic":"http://app1.showapi.com/weather/icon/day/01.png","wind_power":"2级","temperature_time":"11:30","weather_code":"01","temperature":"31","sd":"70%","aqiDetail":{"quality":"优质","aqi":"50","pm10":"48","area":"武汉","co":"0.87","o3":"42","so2":"7","no2":"37","primary_pollutant":"","o3_8h":"22","num":"222","pm2_5":"26"},"weather":"多云"},{"wind_direction":"南风","aqi":"50","weather_pic":"http://app1.showapi.com/weather/icon/day/01.png","wind_power":"3级","temperature_time":"12:00","weather_code":"01","temperature":"31","sd":"69%","aqiDetail":{"quality":"优质","aqi":"50","pm10":"48","area":"武汉","co":"0.87","o3":"42","so2":"7","no2":"37","primary_pollutant":"","o3_8h":"22","num":"222","pm2_5":"26"},"weather":"多云"},{"wind_direction":"西南风","aqi":"53","weather_pic":"http://app1.showapi.com/weather/icon/day/01.png","wind_power":"3级","temperature_time":"12:30","weather_code":"01","temperature":"32","sd":"67%","aqiDetail":{"quality":"良好","aqi":"53","pm10":"53","area":"武汉","co":"0.87","o3":"75","so2":"7","no2":"32","primary_pollutant":"颗粒物(PM10)","o3_8h":"30","num":"254","pm2_5":"30"},"weather":"多云"},{"wind_direction":"西南风","aqi":"53","weather_pic":"http://app1.showapi.com/weather/icon/day/01.png","wind_power":"2级","temperature_time":"13:00","weather_code":"01","temperature":"32","sd":"66%","aqiDetail":{"quality":"良好","aqi":"53","pm10":"53","area":"武汉","co":"0.87","o3":"75","so2":"7","no2":"32","primary_pollutant":"颗粒物(PM10)","o3_8h":"30","num":"254","pm2_5":"30"},"weather":"多云"},{"wind_direction":"西南风","aqi":"51","weather_pic":"http://app1.showapi.com/weather/icon/day/01.png","wind_power":"2级","temperature_time":"13:30","weather_code":"01","temperature":"32","sd":"63%","aqiDetail":{"quality":"良好","aqi":"51","pm10":"52","area":"武汉","co":"0.822","o3":"114","so2":"7","no2":"17","primary_pollutant":"颗粒物(PM10)","o3_8h":"46","num":"258","pm2_5":"26"},"weather":"多云"},{"wind_direction":"西南风","aqi":"51","weather_pic":"http://app1.showapi.com/weather/icon/day/01.png","wind_power":"2级","temperature_time":"14:00","weather_code":"01","temperature":"33","sd":"62%","aqiDetail":{"quality":"良好","aqi":"51","pm10":"52","area":"武汉","co":"0.822","o3":"114","so2":"7","no2":"17","primary_pollutant":"颗粒物(PM10)","o3_8h":"46","num":"258","pm2_5":"26"},"weather":"多云"},{"wind_direction":"西南风","aqi":"46","weather_pic":"http://app1.showapi.com/weather/icon/day/01.png","wind_power":"3级","temperature_time":"14:30","weather_code":"01","temperature":"33","sd":"63%","aqiDetail":{"quality":"优质","aqi":"46","pm10":"45","area":"武汉","co":"0.78","o3":"120","so2":"6","no2":"14","primary_pollutant":"","o3_8h":"53","num":"245","pm2_5":"27"},"weather":"多云"},{"wind_direction":"西南风","aqi":"46","weather_pic":"http://app1.showapi.com/weather/icon/day/01.png","wind_power":"3级","temperature_time":"15:00","weather_code":"01","temperature":"33","sd":"57%","aqiDetail":{"quality":"优质","aqi":"46","pm10":"45","area":"武汉","co":"0.78","o3":"120","so2":"6","no2":"14","primary_pollutant":"","o3_8h":"53","num":"245","pm2_5":"27"},"weather":"多云"}]
     * f2 : {"day_wind_power":"3-4级 5.5~7.9m/s","night_wind_power":"3-4级 5.5~7.9m/s","night_weather_code":"07","day_weather":"小雨","sun_begin_end":"06:10|18:23","air_press":"1013 hPa","index":{"yh":{"desc":"建议尽量不要去室外约会。","title":"较不适宜"},"zs":{"desc":"气温不高，中暑几率极低。","title":"不容易中暑"},"cl":{"desc":"有降水,推荐您在室内进行休闲运动。","title":"较不宜"},"travel":{"desc":"有降水气温高，注意防暑降温携带雨具。","title":"较不宜"},"ag":{"desc":"暂缺","title":"暂缺"},"beauty":{"desc":"请选用保湿型霜类化妆品。","title":"保湿"},"pj":{"desc":"天气炎热，可适量饮用啤酒，不要过量。","title":"适宜"},"dy":{"desc":"天气不好，不适合垂钓","title":"不适宜钓鱼"},"nl":{"desc":"暂缺","title":"暂缺"},"pk":{"desc":"有雨，天气不好，不适宜放风筝。","title":"不适宜"},"uv":{"desc":"辐射较弱，涂擦SPF12-15、PA+护肤品","title":"弱"},"aqi":{"desc":"空气很好，可以外出活动，呼吸新鲜空气","title":"优质"},"gj":{"desc":"有降水，出门带雨具并注意防雷。","title":"不适宜"},"ac":{"desc":"暂缺","title":"暂缺"},"mf":{"desc":"暂缺","title":"暂缺"},"ls":{"desc":"有降水会淋湿衣物，不适宜晾晒。","title":"不适宜"},"glass":{"desc":"不需要佩戴","title":"不需要"},"xq":{"desc":"天气阴沉，会感觉压抑，情绪低落。","title":"较差"},"clothes":{"desc":"适合穿T恤、短薄外套等夏季服装","title":"热"},"sports":{"desc":"有降水,推荐您在室内进行休闲运动。","title":"较不宜"},"hc":{"desc":"天气不好，建议选择别的娱乐方式。","title":"不适宜"},"comfort":{"desc":"偏热或较热，部分人感觉不舒适","title":"较差"},"wash_car":{"desc":"有雨，雨水和泥水会弄脏爱车","title":"不宜"},"cold":{"desc":"天冷湿度大，注意增加衣服。","title":"易发"}},"day_weather_code":"07","night_weather":"小雨","night_weather_pic":"http://app1.showapi.com/weather/icon/night/07.png","day":"20180920","day_wind_direction":"北风","jiangshui":"86%","day_air_temperature":"29","night_wind_direction":"北风","weekday":4,"ziwaixian":"弱","night_air_temperature":"20","day_weather_pic":"http://app1.showapi.com/weather/icon/day/07.png","3hourForcast":[{"temperature":"26","wind_direction":"东北风","temperature_min":"25","hour":"8时-11时","weather_pic":"http://app1.showapi.com/weather/icon/day/07.png","temperature_max":"28","wind_power":"<3级,3","weather":"小雨"},{"temperature":"28","wind_direction":"北风","temperature_min":"26","hour":"11时-14时","weather_pic":"http://app1.showapi.com/weather/icon/day/07.png","temperature_max":"29","wind_power":"<3级,3","weather":"小雨"},{"temperature":"29","wind_direction":"北风","temperature_min":"28","hour":"14时-17时","weather_pic":"http://app1.showapi.com/weather/icon/day/07.png","temperature_max":"29","wind_power":"<3级,3","weather":"小雨"},{"temperature":"28","wind_direction":"北风","temperature_min":"28","hour":"17时-20时","weather_pic":"http://app1.showapi.com/weather/icon/day/08.png","temperature_max":"29","wind_power":"3-4级,3","weather":"中雨"},{"temperature":"28","wind_direction":"北风","temperature_min":"27","hour":"20时-23时","weather_pic":"http://app1.showapi.com/weather/icon/night/07.png","temperature_max":"28","wind_power":"3-4级,0","weather":"小雨"},{"temperature":"27","wind_direction":"北风","temperature_min":"25","hour":"23时-2时","weather_pic":"http://app1.showapi.com/weather/icon/night/07.png","temperature_max":"28","wind_power":"3-4级,0","weather":"小雨"},{"temperature":"25","wind_direction":"北风","temperature_min":"22","hour":"2时-5时","weather_pic":"http://app1.showapi.com/weather/icon/night/07.png","temperature_max":"27","wind_power":"3-4级,0","weather":"小雨"},{"temperature":"22","wind_direction":"北风","temperature_min":"20","hour":"5时-8时","weather_pic":"http://app1.showapi.com/weather/icon/night/07.png","temperature_max":"25","wind_power":"3-4级,0","weather":"小雨"}]}
     * f6 : {"day_wind_power":"3-4级 5.5~7.9m/s","night_wind_power":"3-4级 5.5~7.9m/s","night_weather_code":"01","day_weather":"多云","sun_begin_end":"06:12|18:18","air_press":"1013 hPa","index":{"yh":{"desc":"不用担心天气来调皮捣乱而影响了兴致。 ","title":"较适宜"},"zs":{"desc":"气温不高，中暑几率极低。","title":"不容易中暑"},"cl":{"desc":"推荐您进行室内运动。","title":"较适宜"},"travel":{"desc":"天气很热，如外出可选择水上娱乐项目。","title":"较不宜"},"ag":{"desc":"暂缺","title":"暂缺"},"beauty":{"desc":"请选用露质面霜打底，水质无油粉底霜。","title":"去油"},"pj":{"desc":"天气炎热，可适量饮用啤酒，不要过量。","title":"适宜"},"dy":{"desc":"天气稍热会对垂钓产生一定影响。","title":"较适宜"},"nl":{"desc":"暂缺","title":"暂缺"},"pk":{"desc":"气温略高，放风筝时戴上太阳帽。","title":"较适宜"},"uv":{"desc":"辐射较弱，涂擦SPF12-15、PA+护肤品","title":"弱"},"aqi":{"desc":"空气很好，可以外出活动，呼吸新鲜空气","title":"优质"},"gj":{"desc":"风稍大，出门逛街前记得给秀发定型。","title":"较适宜"},"ac":{"desc":"暂缺","title":"暂缺"},"mf":{"desc":"暂缺","title":"暂缺"},"ls":{"desc":"天气阴沉，不太适宜晾晒。","title":"不适宜"},"glass":{"desc":"不需要佩戴","title":"不需要"},"xq":{"desc":"温度适宜，心情会不错。","title":"好"},"clothes":{"desc":"建议穿长袖衬衫单裤等服装","title":"舒适"},"sports":{"desc":"推荐您进行室内运动。","title":"较适宜"},"hc":{"desc":"风稍大会对划船产生一定影响。","title":"较适宜"},"comfort":{"desc":"偏热或较热，部分人感觉不舒适","title":"较差"},"wash_car":{"desc":"无雨且风力较小，易保持清洁度","title":"较适宜"},"cold":{"desc":"感冒机率较低，避免长期处于空调屋中。","title":"少发"}},"day_weather_code":"01","night_weather":"多云","night_weather_pic":"http://app1.showapi.com/weather/icon/night/01.png","day":"20180924","day_wind_direction":"东北风","jiangshui":"13%","day_air_temperature":"25","night_wind_direction":"东北风","weekday":1,"ziwaixian":"弱","night_air_temperature":"16","day_weather_pic":"http://app1.showapi.com/weather/icon/day/01.png","3hourForcast":[{"temperature":"18","wind_direction":"东北风","temperature_min":"16","hour":"8时-14时","weather_pic":"http://app1.showapi.com/weather/icon/day/00.png","temperature_max":"25","wind_power":"3-4级,1","weather":"晴"},{"temperature":"25","wind_direction":"东北风","temperature_min":"18","hour":"14时-20时","weather_pic":"http://app1.showapi.com/weather/icon/day/01.png","temperature_max":"25","wind_power":"<3级,2","weather":"多云"},{"temperature":"19","wind_direction":"东北风","temperature_min":"17","hour":"20时-2时","weather_pic":"http://app1.showapi.com/weather/icon/night/01.png","temperature_max":"25","wind_power":"3-4级,0","weather":"多云"},{"temperature":"17","wind_direction":"东北风","temperature_min":"17","hour":"2时-8时","weather_pic":"http://app1.showapi.com/weather/icon/night/01.png","temperature_max":"19","wind_power":"3-4级,0","weather":"多云"}]}
     * time : 20180919113000
     * cityInfo : {"c4":"wuhan","c17":"+8","c5":"武汉","c6":"hubei","latitude":30.573,"c7":"湖北","c1":"101200101","c12":"430000","c2":"wuhan","c15":"27","c8":"china","c9":"中国","longitude":114.279,"c3":"武汉","c11":"027","c16":"AZ9270","c10":"1"}
     * f7 : {"day_wind_power":"0-3级 <5.4m/s","night_wind_power":"0-3级 <5.4m/s","night_weather_code":"07","day_weather":"小雨","sun_begin_end":"06:12|18:17","air_press":"1013 hPa","index":{"yh":{"desc":"建议尽量不要去室外约会。","title":"较不适宜"},"zs":{"desc":"气温不高，中暑几率极低。","title":"不容易中暑"},"cl":{"desc":"有降水,推荐您在室内进行休闲运动。","title":"较不宜"},"travel":{"desc":"天热注意防晒，可选择水上娱乐项目。","title":"较适宜"},"ag":{"desc":"暂缺","title":"暂缺"},"beauty":{"desc":"请选用保湿型霜类化妆品。","title":"保湿"},"pj":{"desc":"天气炎热，可适量饮用啤酒，不要过量。","title":"适宜"},"dy":{"desc":"天气不好，不适合垂钓","title":"不适宜钓鱼"},"nl":{"desc":"暂缺","title":"暂缺"},"pk":{"desc":"有雨，天气不好，不适宜放风筝。","title":"不适宜"},"uv":{"desc":"辐射弱，涂擦SPF8-12防晒护肤品","title":"最弱"},"aqi":{"desc":"空气很好，可以外出活动，呼吸新鲜空气","title":"优质"},"gj":{"desc":"有降水，出门带雨具并注意防雷。","title":"不适宜"},"ac":{"desc":"暂缺","title":"暂缺"},"mf":{"desc":"暂缺","title":"暂缺"},"ls":{"desc":"有降水会淋湿衣物，不适宜晾晒。","title":"不适宜"},"glass":{"desc":"不需要佩戴","title":"不需要"},"xq":{"desc":"雨水可能会使心绪无端地挂上轻愁。","title":"较差"},"clothes":{"desc":"建议穿长袖衬衫单裤等服装","title":"舒适"},"sports":{"desc":"有降水,推荐您在室内进行休闲运动。","title":"较不宜"},"hc":{"desc":"天气不好，建议选择别的娱乐方式。","title":"不适宜"},"comfort":{"desc":"热，感觉不舒适","title":"较差"},"wash_car":{"desc":"有雨，雨水和泥水会弄脏爱车","title":"不宜"},"cold":{"desc":"天冷湿度大，注意增加衣服。","title":"易发"}},"day_weather_code":"07","night_weather":"小雨","night_weather_pic":"http://app1.showapi.com/weather/icon/night/07.png","day":"20180925","day_wind_direction":"东北风","jiangshui":"88%","day_air_temperature":"25","night_wind_direction":"无持续风向","weekday":2,"ziwaixian":"最弱","night_air_temperature":"18","day_weather_pic":"http://app1.showapi.com/weather/icon/day/07.png","3hourForcast":[{"temperature":"19","wind_direction":"东北风","temperature_min":"17","hour":"8时-14时","weather_pic":"http://app1.showapi.com/weather/icon/day/01.png","temperature_max":"25","wind_power":"3-4级,3","weather":"多云"},{"temperature":"25","wind_direction":"东北风","temperature_min":"19","hour":"14时-20时","weather_pic":"http://app1.showapi.com/weather/icon/day/08.png","temperature_max":"25","wind_power":"<3级,3","weather":"中雨"},{"temperature":"22","wind_direction":"东北风","temperature_min":"18","hour":"20时-2时","weather_pic":"http://app1.showapi.com/weather/icon/night/08.png","temperature_max":"25","wind_power":"<3级,0","weather":"中雨"},{"temperature":"18","wind_direction":"无持续风向","temperature_min":"18","hour":"2时-8时","weather_pic":"http://app1.showapi.com/weather/icon/night/02.png","temperature_max":"22","wind_power":"<3级,0","weather":"阴"}]}
     * now : {"wind_direction":"西南风","aqi":"46","weather_pic":"http://app1.showapi.com/weather/icon/day/01.png","wind_power":"3级","temperature_time":"15:00","weather_code":"01","temperature":"33","sd":"57%","aqiDetail":{"quality":"优质","aqi":"46","pm10":"45","area":"武汉","co":"0.78","o3":"120","so2":"6","no2":"14","primary_pollutant":"","o3_8h":"53","num":"245","pm2_5":"27"},"weather":"多云"}
     * alarmList : [{"issueTime":"2018-09-18 17:48:00","city":"武汉市","issueContent":"武汉市气象台2018年09月18日17时48分发布雷电黄色预警信号：预计未来6小时，江夏、黄陂、新洲局部有雷电活动，雨量30-50毫米，阵风6-8级，请注意防范。（预警信息来源：国家预警信息发布中心）","province":"湖北省","signalLevel":"黄色","signalType":"雷电"},{"issueTime":"2018-09-18 17:35:00","city":"","issueContent":"武汉中心气象台2018年09月18日17时35分发布雷电黄色预警信号：预计未来6小时，黄石地区、江夏、黄陂、新洲、黄冈、红安、麻城、鄂州局部有雷电活动，雨量30-50毫米，阵风6-8级，请注意防范。（预警信息来源：国家预警信息发布中心）","province":"湖北省","signalLevel":"黄色","signalType":"雷电"},{"issueTime":"2018-09-18 16:08:00","city":"","issueContent":"武汉中心气象台2018年09月18日16时08分发布暴雨橙色预警信号：预计未来3小时，通城、通山局部有50毫米以上降水，伴有雷电大风，山区山洪、地质灾害、中小河流洪水气象风险较高，请注意防范。（预警信息来源：国家预警信息发布中心）","province":"湖北省","signalLevel":"橙色","signalType":"暴雨"},{"issueTime":"2018-09-18 14:35:00","city":"","issueContent":"武汉中心气象台2018年09月18日14时35分发布雷电黄色预警信号：预计未来6小时，咸宁地区局部有雷电活动，雨量20-40毫米，阵风6-8级，请注意防范。（预警信息来源：国家预警信息发布中心）","province":"湖北省","signalLevel":"黄色","signalType":"雷电"},{"issueTime":"2018-09-18 04:23:00","city":"","issueContent":"武汉中心气象台9月18日4时23分发布暴雨橙色预警信号:预计未来3小时，钟祥局部有50毫米以上降水，伴有雷电大风，山洪、地质灾害、中小河流洪水气象风险较高，请注意防范。（预警信息来源：国家预警信息发布中心）","province":"湖北省","signalLevel":"橙色","signalType":"暴雨"},{"issueTime":"2018-09-18 02:35:00","city":"","issueContent":"武汉中心气象台9月18日2时35分发布雷电黄色预警信号:预计未来6小时，宜昌、夷陵区、长阳、当阳、远安局部有雷电活动，雨量30-50毫米，阵风6-8级，请注意防范。（预警信息来源：国家预警信息发布中心）","province":"湖北省","signalLevel":"黄色","signalType":"雷电"},{"issueTime":"2018-09-18 02:27:00","city":"","issueContent":"武汉中心气象台9月18日2时27分发布暴雨橙色预警信号:预计未来3小时，枝江局部有50毫米以上降水，伴有雷电大风，中小河流洪水气象风险较高，请注意防范。（预警信息来源：国家预警信息发布中心）","province":"湖北省","signalLevel":"橙色","signalType":"暴雨"},{"issueTime":"2018-09-18 00:39:00","city":"","issueContent":"武汉中心气象台9月18日0时39分发布雷电黄色预警信号:预计未来6小时，五峰、宜都、枝江、荆州、公安、松滋、荆门、钟祥、阳新、武穴、黄梅、通山局部有雷电活动，雨量30-50毫米，阵风6-8级，请注意防范。（预警信息来源：国家预警信息发布中心）","province":"湖北省","signalLevel":"黄色","signalType":"雷电"},{"issueTime":"2018-09-17 21:31:00","city":"","issueContent":"武汉中心气象台9月17日21时31分发布雷电黄色预警信号:预计未来6小时，赤壁、崇阳、通城、洪湖局部有雷电活动，雨量30-50毫米，阵风6-8级，请注意防范。（预警信息来源：国家预警信息发布中心）","province":"湖北省","signalLevel":"黄色","signalType":"雷电"}]
     * f3 : {"day_wind_power":"4-5级 8.0~10.7m/s","night_wind_power":"0-3级 <5.4m/s","night_weather_code":"07","day_weather":"小雨","sun_begin_end":"06:10|18:22","air_press":"1013 hPa","index":{"yh":{"desc":"建议尽量不要去室外约会。","title":"较不适宜"},"zs":{"desc":"气温不高，中暑几率极低。","title":"不容易中暑"},"cl":{"desc":"有降水,推荐您在室内进行休闲运动。","title":"较不宜"},"travel":{"desc":"有降水气温高，注意防暑降温携带雨具。","title":"较不宜"},"ag":{"desc":"暂缺","title":"暂缺"},"beauty":{"desc":"请选用保湿型霜类化妆品。","title":"保湿"},"pj":{"desc":"天气炎热，可适量饮用啤酒，不要过量。","title":"适宜"},"dy":{"desc":"天气不好，不适合垂钓","title":"不适宜钓鱼"},"nl":{"desc":"暂缺","title":"暂缺"},"pk":{"desc":"有雨，天气不好，不适宜放风筝。","title":"不适宜"},"uv":{"desc":"辐射弱，涂擦SPF8-12防晒护肤品","title":"最弱"},"aqi":{"desc":"空气很好，可以外出活动，呼吸新鲜空气","title":"优质"},"gj":{"desc":"有降水，出门带雨具并注意防雷。","title":"不适宜"},"ac":{"desc":"暂缺","title":"暂缺"},"mf":{"desc":"暂缺","title":"暂缺"},"ls":{"desc":"有降水会淋湿衣物，不适宜晾晒。","title":"不适宜"},"glass":{"desc":"不需要佩戴","title":"不需要"},"xq":{"desc":"差湿嗒塔的环境让人的心情难以开朗","title":"较差"},"clothes":{"desc":"建议穿长袖衬衫单裤等服装","title":"舒适"},"sports":{"desc":"有降水,推荐您在室内进行休闲运动。","title":"较不宜"},"hc":{"desc":"天气不好，建议选择别的娱乐方式。","title":"不适宜"},"comfort":{"desc":"偏热或较热，部分人感觉不舒适","title":"较差"},"wash_car":{"desc":"有雨，雨水和泥水会弄脏爱车","title":"不宜"},"cold":{"desc":"天冷湿度大，注意增加衣服。","title":"易发"}},"day_weather_code":"07","night_weather":"小雨","night_weather_pic":"http://app1.showapi.com/weather/icon/night/07.png","day":"20180921","day_wind_direction":"北风","jiangshui":"88%","day_air_temperature":"26","night_wind_direction":"北风","weekday":5,"ziwaixian":"最弱","night_air_temperature":"19","day_weather_pic":"http://app1.showapi.com/weather/icon/day/07.png","3hourForcast":[{"temperature":"20","wind_direction":"北风","temperature_min":"20","hour":"8时-11时","weather_pic":"http://app1.showapi.com/weather/icon/day/02.png","temperature_max":"24","wind_power":"3-4级,3","weather":"阴"},{"temperature":"24","wind_direction":"北风","temperature_min":"20","hour":"11时-14时","weather_pic":"http://app1.showapi.com/weather/icon/day/07.png","temperature_max":"26","wind_power":"4-5级,3","weather":"小雨"},{"temperature":"26","wind_direction":"北风","temperature_min":"24","hour":"14时-17时","weather_pic":"http://app1.showapi.com/weather/icon/day/07.png","temperature_max":"26","wind_power":"4-5级,3","weather":"小雨"},{"temperature":"26","wind_direction":"北风","temperature_min":"23","hour":"17时-20时","weather_pic":"http://app1.showapi.com/weather/icon/day/07.png","temperature_max":"26","wind_power":"3-4级,3","weather":"小雨"},{"temperature":"23","wind_direction":"北风","temperature_min":"20","hour":"20时-23时","weather_pic":"http://app1.showapi.com/weather/icon/night/07.png","temperature_max":"26","wind_power":"<3级,0","weather":"小雨"},{"temperature":"20","wind_direction":"北风","temperature_min":"20","hour":"23时-2时","weather_pic":"http://app1.showapi.com/weather/icon/night/07.png","temperature_max":"23","wind_power":"<3级,0","weather":"小雨"},{"temperature":"20","wind_direction":"北风","temperature_min":"19","hour":"2时-5时","weather_pic":"http://app1.showapi.com/weather/icon/night/07.png","temperature_max":"20","wind_power":"<3级,0","weather":"小雨"},{"temperature":"19","wind_direction":"北风","temperature_min":"19","hour":"5时-8时","weather_pic":"http://app1.showapi.com/weather/icon/night/07.png","temperature_max":"23","wind_power":"<3级,0","weather":"小雨"}]}
     * f1 : {"day_wind_power":"3-4级 5.5~7.9m/s","night_wind_power":"0-3级 <5.4m/s","night_weather_code":"07","day_weather":"多云","sun_begin_end":"06:09|18:24","air_press":"1013 hPa","index":{"yh":{"desc":"不用担心天气来调皮捣乱而影响了兴致。 ","title":"较适宜"},"zs":{"desc":"气温较高，易中暑人群注意阴凉休息。","title":"可能中暑"},"cl":{"desc":"适当减少运动时间并降低运动强度。","title":"适宜"},"travel":{"desc":"天气很热，如外出可选择水上娱乐项目。","title":"较不宜"},"ag":{"desc":"暂缺","title":"暂缺"},"beauty":{"desc":"请选用露质面霜打底，水质无油粉底霜。","title":"去油"},"pj":{"desc":"炎热干燥，适宜饮用冰镇啤酒，不要贪杯。","title":"适宜"},"dy":{"desc":"天气太热,不适宜垂钓。","title":"不适宜钓鱼"},"nl":{"desc":"暂缺","title":"暂缺"},"pk":{"desc":"天气酷热，不适宜放风筝。","title":"不宜"},"uv":{"desc":"辐射较弱，涂擦SPF12-15、PA+护肤品","title":"弱"},"aqi":{"desc":"空气很好，可以外出活动，呼吸新鲜空气","title":"优质"},"gj":{"desc":"风稍大，出门逛街前记得给秀发定型。","title":"较适宜"},"ac":{"desc":"暂缺","title":"暂缺"},"mf":{"desc":"暂缺","title":"暂缺"},"ls":{"desc":"天气阴沉，不太适宜晾晒。","title":"不适宜"},"glass":{"desc":"不需要佩戴","title":"不需要"},"xq":{"desc":"天气热，容易烦躁","title":"较差"},"clothes":{"desc":"建议穿短衫、短裤等清凉夏季服装","title":"炎热"},"sports":{"desc":"适当减少运动时间并降低运动强度。","title":"适宜"},"hc":{"desc":"风稍大会对划船产生一定影响。","title":"较适宜"},"comfort":{"desc":"热，感觉不舒适","title":"较差"},"wash_car":{"desc":"有雨，雨水和泥水会弄脏爱车","title":"不宜"},"cold":{"desc":"感冒机率较低，避免长期处于空调屋中。","title":"少发"}},"day_weather_code":"01","night_weather":"小雨","night_weather_pic":"http://app1.showapi.com/weather/icon/night/07.png","day":"20180919","day_wind_direction":"西南风","jiangshui":"10%","day_air_temperature":"34","night_wind_direction":"东北风","weekday":3,"ziwaixian":"弱","night_air_temperature":"24","day_weather_pic":"http://app1.showapi.com/weather/icon/day/01.png","3hourForcast":[{"temperature":"27","wind_direction":"西南风","temperature_min":"27","hour":"8时-11时","weather_pic":"http://app1.showapi.com/weather/icon/day/01.png","temperature_max":"33","wind_power":"<3级,3","weather":"多云"},{"temperature":"33","wind_direction":"西南风","temperature_min":"27","hour":"11时-14时","weather_pic":"http://app1.showapi.com/weather/icon/day/01.png","temperature_max":"34","wind_power":"3-4级,2","weather":"多云"},{"temperature":"34","wind_direction":"西南风","temperature_min":"30","hour":"14时-17时","weather_pic":"http://app1.showapi.com/weather/icon/day/01.png","temperature_max":"34","wind_power":"3-4级,2","weather":"多云"},{"temperature":"30","wind_direction":"西南风","temperature_min":"27","hour":"17时-20时","weather_pic":"http://app1.showapi.com/weather/icon/day/01.png","temperature_max":"34","wind_power":"<3级,3","weather":"多云"},{"temperature":"27","wind_direction":"西南风","temperature_min":"24","hour":"20时-23时","weather_pic":"http://app1.showapi.com/weather/icon/night/01.png","temperature_max":"30","wind_power":"<3级,0","weather":"多云"},{"temperature":"24","wind_direction":"东北风","temperature_min":"24","hour":"23时-2时","weather_pic":"http://app1.showapi.com/weather/icon/night/01.png","temperature_max":"27","wind_power":"<3级,0","weather":"多云"},{"temperature":"25","wind_direction":"东北风","temperature_min":"24","hour":"2时-5时","weather_pic":"http://app1.showapi.com/weather/icon/night/07.png","temperature_max":"25","wind_power":"<3级,0","weather":"小雨"},{"temperature":"25","wind_direction":"东北风","temperature_min":"25","hour":"5时-8时","weather_pic":"http://app1.showapi.com/weather/icon/night/07.png","temperature_max":"26","wind_power":"<3级,0","weather":"小雨"}]}
     * ret_code : 0
     */

    private FBean f4;
    private FBean f5;
    private FBean f2;
    private FBean f6;
    private String time;
    private CityInfoBean cityInfo;
    private FBean f7;
    private NowBean now;
    private FBean f3;
    private FBean f1;
    private int ret_code;
    private List<HourDataListBean> hourDataList;
    private List<AlarmListBean> alarmList;

    public FBean getF4() {
        return f4;
    }

    public void setF4(FBean f4) {
        this.f4 = f4;
    }

    public FBean getF5() {
        return f5;
    }

    public void setF5(FBean f5) {
        this.f5 = f5;
    }

    public FBean getF2() {
        return f2;
    }

    public void setF2(FBean f2) {
        this.f2 = f2;
    }

    public FBean getF6() {
        return f6;
    }

    public void setF6(FBean f6) {
        this.f6 = f6;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public CityInfoBean getCityInfo() {
        return cityInfo;
    }

    public void setCityInfo(CityInfoBean cityInfo) {
        this.cityInfo = cityInfo;
    }

    public FBean getF7() {
        return f7;
    }

    public void setF7(FBean f7) {
        this.f7 = f7;
    }

    public NowBean getNow() {
        return now;
    }

    public void setNow(NowBean now) {
        this.now = now;
    }

    public FBean getF3() {
        return f3;
    }

    public void setF3(FBean f3) {
        this.f3 = f3;
    }

    public FBean getF1() {
        return f1;
    }

    public void setF1(FBean f1) {
        this.f1 = f1;
    }

    public int getRet_code() {
        return ret_code;
    }

    public void setRet_code(int ret_code) {
        this.ret_code = ret_code;
    }

    public List<HourDataListBean> getHourDataList() {
        return hourDataList;
    }

    public void setHourDataList(List<HourDataListBean> hourDataList) {
        this.hourDataList = hourDataList;
    }

    public List<AlarmListBean> getAlarmList() {
        return alarmList;
    }

    public void setAlarmList(List<AlarmListBean> alarmList) {
        this.alarmList = alarmList;
    }

    public static class CityInfoBean {
        /**
         * c4 : wuhan
         * c17 : +8
         * c5 : 武汉
         * c6 : hubei
         * latitude : 30.573
         * c7 : 湖北
         * c1 : 101200101
         * c12 : 430000
         * c2 : wuhan
         * c15 : 27
         * c8 : china
         * c9 : 中国
         * longitude : 114.279
         * c3 : 武汉
         * c11 : 027
         * c16 : AZ9270
         * c10 : 1
         */

        private String c4;
        private String c17;
        private String c5;
        private String c6;
        private double latitude;
        private String c7;
        private String c1;
        private String c12;
        private String c2;
        private String c15;
        private String c8;
        private String c9;
        private double longitude;
        private String c3;
        private String c11;
        private String c16;
        private String c10;

        public String getC4() {
            return c4;
        }

        public void setC4(String c4) {
            this.c4 = c4;
        }

        public String getC17() {
            return c17;
        }

        public void setC17(String c17) {
            this.c17 = c17;
        }

        public String getC5() {
            return c5;
        }

        public void setC5(String c5) {
            this.c5 = c5;
        }

        public String getC6() {
            return c6;
        }

        public void setC6(String c6) {
            this.c6 = c6;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public String getC7() {
            return c7;
        }

        public void setC7(String c7) {
            this.c7 = c7;
        }

        public String getC1() {
            return c1;
        }

        public void setC1(String c1) {
            this.c1 = c1;
        }

        public String getC12() {
            return c12;
        }

        public void setC12(String c12) {
            this.c12 = c12;
        }

        public String getC2() {
            return c2;
        }

        public void setC2(String c2) {
            this.c2 = c2;
        }

        public String getC15() {
            return c15;
        }

        public void setC15(String c15) {
            this.c15 = c15;
        }

        public String getC8() {
            return c8;
        }

        public void setC8(String c8) {
            this.c8 = c8;
        }

        public String getC9() {
            return c9;
        }

        public void setC9(String c9) {
            this.c9 = c9;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public String getC3() {
            return c3;
        }

        public void setC3(String c3) {
            this.c3 = c3;
        }

        public String getC11() {
            return c11;
        }

        public void setC11(String c11) {
            this.c11 = c11;
        }

        public String getC16() {
            return c16;
        }

        public void setC16(String c16) {
            this.c16 = c16;
        }

        public String getC10() {
            return c10;
        }

        public void setC10(String c10) {
            this.c10 = c10;
        }
    }

    public static class NowBean {
        /**
         * wind_direction : 西南风
         * aqi : 46
         * weather_pic : http://app1.showapi.com/weather/icon/day/01.png
         * wind_power : 3级
         * temperature_time : 15:00
         * weather_code : 01
         * temperature : 33
         * sd : 57%
         * aqiDetail : {"quality":"优质","aqi":"46","pm10":"45","area":"武汉","co":"0.78","o3":"120","so2":"6","no2":"14","primary_pollutant":"","o3_8h":"53","num":"245","pm2_5":"27"}
         * weather : 多云
         */

        private String wind_direction;
        private String aqi;
        private String weather_pic;
        private String wind_power;
        private String temperature_time;
        private String weather_code;
        private String temperature;
        private String sd;
        private AqiDetailBean aqiDetail;
        private String weather;

        public String getWind_direction() {
            return wind_direction;
        }

        public void setWind_direction(String wind_direction) {
            this.wind_direction = wind_direction;
        }

        public String getAqi() {
            return aqi;
        }

        public void setAqi(String aqi) {
            this.aqi = aqi;
        }

        public String getWeather_pic() {
            return weather_pic;
        }

        public void setWeather_pic(String weather_pic) {
            this.weather_pic = weather_pic;
        }

        public String getWind_power() {
            return wind_power;
        }

        public void setWind_power(String wind_power) {
            this.wind_power = wind_power;
        }

        public String getTemperature_time() {
            return temperature_time;
        }

        public void setTemperature_time(String temperature_time) {
            this.temperature_time = temperature_time;
        }

        public String getWeather_code() {
            return weather_code;
        }

        public void setWeather_code(String weather_code) {
            this.weather_code = weather_code;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getSd() {
            return sd;
        }

        public void setSd(String sd) {
            this.sd = sd;
        }

        public AqiDetailBean getAqiDetail() {
            return aqiDetail;
        }

        public void setAqiDetail(AqiDetailBean aqiDetail) {
            this.aqiDetail = aqiDetail;
        }

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public static class AqiDetailBean {
            /**
             * quality : 优质
             * aqi : 46
             * pm10 : 45
             * area : 武汉
             * co : 0.78
             * o3 : 120
             * so2 : 6
             * no2 : 14
             * primary_pollutant :
             * o3_8h : 53
             * num : 245
             * pm2_5 : 27
             */

            private String quality;
            private String aqi;
            private String pm10;
            private String area;
            private String co;
            private String o3;
            private String so2;
            private String no2;
            private String primary_pollutant;
            private String o3_8h;
            private String num;
            private String pm2_5;

            public String getQuality() {
                return quality;
            }

            public void setQuality(String quality) {
                this.quality = quality;
            }

            public String getAqi() {
                return aqi;
            }

            public void setAqi(String aqi) {
                this.aqi = aqi;
            }

            public String getPm10() {
                return pm10;
            }

            public void setPm10(String pm10) {
                this.pm10 = pm10;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getCo() {
                return co;
            }

            public void setCo(String co) {
                this.co = co;
            }

            public String getO3() {
                return o3;
            }

            public void setO3(String o3) {
                this.o3 = o3;
            }

            public String getSo2() {
                return so2;
            }

            public void setSo2(String so2) {
                this.so2 = so2;
            }

            public String getNo2() {
                return no2;
            }

            public void setNo2(String no2) {
                this.no2 = no2;
            }

            public String getPrimary_pollutant() {
                return primary_pollutant;
            }

            public void setPrimary_pollutant(String primary_pollutant) {
                this.primary_pollutant = primary_pollutant;
            }

            public String getO3_8h() {
                return o3_8h;
            }

            public void setO3_8h(String o3_8h) {
                this.o3_8h = o3_8h;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public String getPm2_5() {
                return pm2_5;
            }

            public void setPm2_5(String pm2_5) {
                this.pm2_5 = pm2_5;
            }
        }
    }

    public static class FBean {
        /**
         * day_wind_power : 3-4级 5.5~7.9m/s
         * night_wind_power : 0-3级 <5.4m/s
         * night_weather_code : 07
         * day_weather : 多云
         * sun_begin_end : 06:09|18:24
         * air_press : 1013 hPa
         * index : {"yh":{"desc":"不用担心天气来调皮捣乱而影响了兴致。 ","title":"较适宜"},"zs":{"desc":"气温较高，易中暑人群注意阴凉休息。","title":"可能中暑"},"cl":{"desc":"适当减少运动时间并降低运动强度。","title":"适宜"},"travel":{"desc":"天气很热，如外出可选择水上娱乐项目。","title":"较不宜"},"ag":{"desc":"暂缺","title":"暂缺"},"beauty":{"desc":"请选用露质面霜打底，水质无油粉底霜。","title":"去油"},"pj":{"desc":"炎热干燥，适宜饮用冰镇啤酒，不要贪杯。","title":"适宜"},"dy":{"desc":"天气太热,不适宜垂钓。","title":"不适宜钓鱼"},"nl":{"desc":"暂缺","title":"暂缺"},"pk":{"desc":"天气酷热，不适宜放风筝。","title":"不宜"},"uv":{"desc":"辐射较弱，涂擦SPF12-15、PA+护肤品","title":"弱"},"aqi":{"desc":"空气很好，可以外出活动，呼吸新鲜空气","title":"优质"},"gj":{"desc":"风稍大，出门逛街前记得给秀发定型。","title":"较适宜"},"ac":{"desc":"暂缺","title":"暂缺"},"mf":{"desc":"暂缺","title":"暂缺"},"ls":{"desc":"天气阴沉，不太适宜晾晒。","title":"不适宜"},"glass":{"desc":"不需要佩戴","title":"不需要"},"xq":{"desc":"天气热，容易烦躁","title":"较差"},"clothes":{"desc":"建议穿短衫、短裤等清凉夏季服装","title":"炎热"},"sports":{"desc":"适当减少运动时间并降低运动强度。","title":"适宜"},"hc":{"desc":"风稍大会对划船产生一定影响。","title":"较适宜"},"comfort":{"desc":"热，感觉不舒适","title":"较差"},"wash_car":{"desc":"有雨，雨水和泥水会弄脏爱车","title":"不宜"},"cold":{"desc":"感冒机率较低，避免长期处于空调屋中。","title":"少发"}}
         * day_weather_code : 01
         * night_weather : 小雨
         * night_weather_pic : http://app1.showapi.com/weather/icon/night/07.png
         * day : 20180919
         * day_wind_direction : 西南风
         * jiangshui : 10%
         * day_air_temperature : 34
         * night_wind_direction : 东北风
         * weekday : 3
         * ziwaixian : 弱
         * night_air_temperature : 24
         * day_weather_pic : http://app1.showapi.com/weather/icon/day/01.png
         * 3hourForcast : [{"temperature":"27","wind_direction":"西南风","temperature_min":"27","hour":"8时-11时","weather_pic":"http://app1.showapi.com/weather/icon/day/01.png","temperature_max":"33","wind_power":"<3级,3","weather":"多云"},{"temperature":"33","wind_direction":"西南风","temperature_min":"27","hour":"11时-14时","weather_pic":"http://app1.showapi.com/weather/icon/day/01.png","temperature_max":"34","wind_power":"3-4级,2","weather":"多云"},{"temperature":"34","wind_direction":"西南风","temperature_min":"30","hour":"14时-17时","weather_pic":"http://app1.showapi.com/weather/icon/day/01.png","temperature_max":"34","wind_power":"3-4级,2","weather":"多云"},{"temperature":"30","wind_direction":"西南风","temperature_min":"27","hour":"17时-20时","weather_pic":"http://app1.showapi.com/weather/icon/day/01.png","temperature_max":"34","wind_power":"<3级,3","weather":"多云"},{"temperature":"27","wind_direction":"西南风","temperature_min":"24","hour":"20时-23时","weather_pic":"http://app1.showapi.com/weather/icon/night/01.png","temperature_max":"30","wind_power":"<3级,0","weather":"多云"},{"temperature":"24","wind_direction":"东北风","temperature_min":"24","hour":"23时-2时","weather_pic":"http://app1.showapi.com/weather/icon/night/01.png","temperature_max":"27","wind_power":"<3级,0","weather":"多云"},{"temperature":"25","wind_direction":"东北风","temperature_min":"24","hour":"2时-5时","weather_pic":"http://app1.showapi.com/weather/icon/night/07.png","temperature_max":"25","wind_power":"<3级,0","weather":"小雨"},{"temperature":"25","wind_direction":"东北风","temperature_min":"25","hour":"5时-8时","weather_pic":"http://app1.showapi.com/weather/icon/night/07.png","temperature_max":"26","wind_power":"<3级,0","weather":"小雨"}]
         */

        private String day_wind_power;
        private String night_wind_power;
        private String night_weather_code;
        private String day_weather;
        private String sun_begin_end;
        private String air_press;
        private IndexBeanXXXXXX index;
        private String day_weather_code;
        private String night_weather;
        private String night_weather_pic;
        private String day;
        private String day_wind_direction;
        private String jiangshui;
        private String day_air_temperature;
        private String night_wind_direction;
        private int weekday;
        private String ziwaixian;
        private String night_air_temperature;
        private String day_weather_pic;
        @SerializedName("3hourForcast")
        private List<_$3hourForcastBeanXXXXXX> _$3hourForcast;

        public String getDay_wind_power() {
            return day_wind_power;
        }

        public void setDay_wind_power(String day_wind_power) {
            this.day_wind_power = day_wind_power;
        }

        public String getNight_wind_power() {
            return night_wind_power;
        }

        public void setNight_wind_power(String night_wind_power) {
            this.night_wind_power = night_wind_power;
        }

        public String getNight_weather_code() {
            return night_weather_code;
        }

        public void setNight_weather_code(String night_weather_code) {
            this.night_weather_code = night_weather_code;
        }

        public String getDay_weather() {
            return day_weather;
        }

        public void setDay_weather(String day_weather) {
            this.day_weather = day_weather;
        }

        public String getSun_begin_end() {
            return sun_begin_end;
        }

        public void setSun_begin_end(String sun_begin_end) {
            this.sun_begin_end = sun_begin_end;
        }

        public String getAir_press() {
            return air_press;
        }

        public void setAir_press(String air_press) {
            this.air_press = air_press;
        }

        public IndexBeanXXXXXX getIndex() {
            return index;
        }

        public void setIndex(IndexBeanXXXXXX index) {
            this.index = index;
        }

        public String getDay_weather_code() {
            return day_weather_code;
        }

        public void setDay_weather_code(String day_weather_code) {
            this.day_weather_code = day_weather_code;
        }

        public String getNight_weather() {
            return night_weather;
        }

        public void setNight_weather(String night_weather) {
            this.night_weather = night_weather;
        }

        public String getNight_weather_pic() {
            return night_weather_pic;
        }

        public void setNight_weather_pic(String night_weather_pic) {
            this.night_weather_pic = night_weather_pic;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getDay_wind_direction() {
            return day_wind_direction;
        }

        public void setDay_wind_direction(String day_wind_direction) {
            this.day_wind_direction = day_wind_direction;
        }

        public String getJiangshui() {
            return jiangshui;
        }

        public void setJiangshui(String jiangshui) {
            this.jiangshui = jiangshui;
        }

        public String getDay_air_temperature() {
            return day_air_temperature;
        }

        public void setDay_air_temperature(String day_air_temperature) {
            this.day_air_temperature = day_air_temperature;
        }

        public String getNight_wind_direction() {
            return night_wind_direction;
        }

        public void setNight_wind_direction(String night_wind_direction) {
            this.night_wind_direction = night_wind_direction;
        }

        public int getWeekday() {
            return weekday;
        }

        public void setWeekday(int weekday) {
            this.weekday = weekday;
        }

        public String getZiwaixian() {
            return ziwaixian;
        }

        public void setZiwaixian(String ziwaixian) {
            this.ziwaixian = ziwaixian;
        }

        public String getNight_air_temperature() {
            return night_air_temperature;
        }

        public void setNight_air_temperature(String night_air_temperature) {
            this.night_air_temperature = night_air_temperature;
        }

        public String getDay_weather_pic() {
            return day_weather_pic;
        }

        public void setDay_weather_pic(String day_weather_pic) {
            this.day_weather_pic = day_weather_pic;
        }

        public List<_$3hourForcastBeanXXXXXX> get_$3hourForcast() {
            return _$3hourForcast;
        }

        public void set_$3hourForcast(List<_$3hourForcastBeanXXXXXX> _$3hourForcast) {
            this._$3hourForcast = _$3hourForcast;
        }

        public static class IndexBeanXXXXXX {
            /**
             * yh : {"desc":"不用担心天气来调皮捣乱而影响了兴致。 ","title":"较适宜"}
             * zs : {"desc":"气温较高，易中暑人群注意阴凉休息。","title":"可能中暑"}
             * cl : {"desc":"适当减少运动时间并降低运动强度。","title":"适宜"}
             * travel : {"desc":"天气很热，如外出可选择水上娱乐项目。","title":"较不宜"}
             * ag : {"desc":"暂缺","title":"暂缺"}
             * beauty : {"desc":"请选用露质面霜打底，水质无油粉底霜。","title":"去油"}
             * pj : {"desc":"炎热干燥，适宜饮用冰镇啤酒，不要贪杯。","title":"适宜"}
             * dy : {"desc":"天气太热,不适宜垂钓。","title":"不适宜钓鱼"}
             * nl : {"desc":"暂缺","title":"暂缺"}
             * pk : {"desc":"天气酷热，不适宜放风筝。","title":"不宜"}
             * uv : {"desc":"辐射较弱，涂擦SPF12-15、PA+护肤品","title":"弱"}
             * aqi : {"desc":"空气很好，可以外出活动，呼吸新鲜空气","title":"优质"}
             * gj : {"desc":"风稍大，出门逛街前记得给秀发定型。","title":"较适宜"}
             * ac : {"desc":"暂缺","title":"暂缺"}
             * mf : {"desc":"暂缺","title":"暂缺"}
             * ls : {"desc":"天气阴沉，不太适宜晾晒。","title":"不适宜"}
             * glass : {"desc":"不需要佩戴","title":"不需要"}
             * xq : {"desc":"天气热，容易烦躁","title":"较差"}
             * clothes : {"desc":"建议穿短衫、短裤等清凉夏季服装","title":"炎热"}
             * sports : {"desc":"适当减少运动时间并降低运动强度。","title":"适宜"}
             * hc : {"desc":"风稍大会对划船产生一定影响。","title":"较适宜"}
             * comfort : {"desc":"热，感觉不舒适","title":"较差"}
             * wash_car : {"desc":"有雨，雨水和泥水会弄脏爱车","title":"不宜"}
             * cold : {"desc":"感冒机率较低，避免长期处于空调屋中。","title":"少发"}
             */

            private YhBeanXXXXXX yh;
            private ZsBeanXXXXXX zs;
            private ClBeanXXXXXX cl;
            private TravelBeanXXXXXX travel;
            private AgBeanXXXXXX ag;
            private BeautyBeanXXXXXX beauty;
            private PjBeanXXXXXX pj;
            private DyBeanXXXXXX dy;
            private NlBeanXXXXXX nl;
            private PkBeanXXXXXX pk;
            private UvBeanXXXXXX uv;
            private AqiBeanXXXXXX aqi;
            private GjBeanXXXXXX gj;
            private AcBeanXXXXXX ac;
            private MfBeanXXXXXX mf;
            private LsBeanXXXXXX ls;
            private GlassBeanXXXXXX glass;
            private XqBeanXXXXXX xq;
            private ClothesBeanXXXXXX clothes;
            private SportsBeanXXXXXX sports;
            private HcBeanXXXXXX hc;
            private ComfortBeanXXXXXX comfort;
            private WashCarBeanXXXXXX wash_car;
            private ColdBeanXXXXXX cold;

            public YhBeanXXXXXX getYh() {
                return yh;
            }

            public void setYh(YhBeanXXXXXX yh) {
                this.yh = yh;
            }

            public ZsBeanXXXXXX getZs() {
                return zs;
            }

            public void setZs(ZsBeanXXXXXX zs) {
                this.zs = zs;
            }

            public ClBeanXXXXXX getCl() {
                return cl;
            }

            public void setCl(ClBeanXXXXXX cl) {
                this.cl = cl;
            }

            public TravelBeanXXXXXX getTravel() {
                return travel;
            }

            public void setTravel(TravelBeanXXXXXX travel) {
                this.travel = travel;
            }

            public AgBeanXXXXXX getAg() {
                return ag;
            }

            public void setAg(AgBeanXXXXXX ag) {
                this.ag = ag;
            }

            public BeautyBeanXXXXXX getBeauty() {
                return beauty;
            }

            public void setBeauty(BeautyBeanXXXXXX beauty) {
                this.beauty = beauty;
            }

            public PjBeanXXXXXX getPj() {
                return pj;
            }

            public void setPj(PjBeanXXXXXX pj) {
                this.pj = pj;
            }

            public DyBeanXXXXXX getDy() {
                return dy;
            }

            public void setDy(DyBeanXXXXXX dy) {
                this.dy = dy;
            }

            public NlBeanXXXXXX getNl() {
                return nl;
            }

            public void setNl(NlBeanXXXXXX nl) {
                this.nl = nl;
            }

            public PkBeanXXXXXX getPk() {
                return pk;
            }

            public void setPk(PkBeanXXXXXX pk) {
                this.pk = pk;
            }

            public UvBeanXXXXXX getUv() {
                return uv;
            }

            public void setUv(UvBeanXXXXXX uv) {
                this.uv = uv;
            }

            public AqiBeanXXXXXX getAqi() {
                return aqi;
            }

            public void setAqi(AqiBeanXXXXXX aqi) {
                this.aqi = aqi;
            }

            public GjBeanXXXXXX getGj() {
                return gj;
            }

            public void setGj(GjBeanXXXXXX gj) {
                this.gj = gj;
            }

            public AcBeanXXXXXX getAc() {
                return ac;
            }

            public void setAc(AcBeanXXXXXX ac) {
                this.ac = ac;
            }

            public MfBeanXXXXXX getMf() {
                return mf;
            }

            public void setMf(MfBeanXXXXXX mf) {
                this.mf = mf;
            }

            public LsBeanXXXXXX getLs() {
                return ls;
            }

            public void setLs(LsBeanXXXXXX ls) {
                this.ls = ls;
            }

            public GlassBeanXXXXXX getGlass() {
                return glass;
            }

            public void setGlass(GlassBeanXXXXXX glass) {
                this.glass = glass;
            }

            public XqBeanXXXXXX getXq() {
                return xq;
            }

            public void setXq(XqBeanXXXXXX xq) {
                this.xq = xq;
            }

            public ClothesBeanXXXXXX getClothes() {
                return clothes;
            }

            public void setClothes(ClothesBeanXXXXXX clothes) {
                this.clothes = clothes;
            }

            public SportsBeanXXXXXX getSports() {
                return sports;
            }

            public void setSports(SportsBeanXXXXXX sports) {
                this.sports = sports;
            }

            public HcBeanXXXXXX getHc() {
                return hc;
            }

            public void setHc(HcBeanXXXXXX hc) {
                this.hc = hc;
            }

            public ComfortBeanXXXXXX getComfort() {
                return comfort;
            }

            public void setComfort(ComfortBeanXXXXXX comfort) {
                this.comfort = comfort;
            }

            public WashCarBeanXXXXXX getWash_car() {
                return wash_car;
            }

            public void setWash_car(WashCarBeanXXXXXX wash_car) {
                this.wash_car = wash_car;
            }

            public ColdBeanXXXXXX getCold() {
                return cold;
            }

            public void setCold(ColdBeanXXXXXX cold) {
                this.cold = cold;
            }

            public static class YhBeanXXXXXX {
                /**
                 * desc : 不用担心天气来调皮捣乱而影响了兴致。
                 * title : 较适宜
                 */

                private String desc;
                private String title;

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
            }

            public static class ZsBeanXXXXXX {
                /**
                 * desc : 气温较高，易中暑人群注意阴凉休息。
                 * title : 可能中暑
                 */

                private String desc;
                private String title;

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
            }

            public static class ClBeanXXXXXX {
                /**
                 * desc : 适当减少运动时间并降低运动强度。
                 * title : 适宜
                 */

                private String desc;
                private String title;

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
            }

            public static class TravelBeanXXXXXX {
                /**
                 * desc : 天气很热，如外出可选择水上娱乐项目。
                 * title : 较不宜
                 */

                private String desc;
                private String title;

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
            }

            public static class AgBeanXXXXXX {
                /**
                 * desc : 暂缺
                 * title : 暂缺
                 */

                private String desc;
                private String title;

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
            }

            public static class BeautyBeanXXXXXX {
                /**
                 * desc : 请选用露质面霜打底，水质无油粉底霜。
                 * title : 去油
                 */

                private String desc;
                private String title;

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
            }

            public static class PjBeanXXXXXX {
                /**
                 * desc : 炎热干燥，适宜饮用冰镇啤酒，不要贪杯。
                 * title : 适宜
                 */

                private String desc;
                private String title;

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
            }

            public static class DyBeanXXXXXX {
                /**
                 * desc : 天气太热,不适宜垂钓。
                 * title : 不适宜钓鱼
                 */

                private String desc;
                private String title;

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
            }

            public static class NlBeanXXXXXX {
                /**
                 * desc : 暂缺
                 * title : 暂缺
                 */

                private String desc;
                private String title;

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
            }

            public static class PkBeanXXXXXX {
                /**
                 * desc : 天气酷热，不适宜放风筝。
                 * title : 不宜
                 */

                private String desc;
                private String title;

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
            }

            public static class UvBeanXXXXXX {
                /**
                 * desc : 辐射较弱，涂擦SPF12-15、PA+护肤品
                 * title : 弱
                 */

                private String desc;
                private String title;

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
            }

            public static class AqiBeanXXXXXX {
                /**
                 * desc : 空气很好，可以外出活动，呼吸新鲜空气
                 * title : 优质
                 */

                private String desc;
                private String title;

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
            }

            public static class GjBeanXXXXXX {
                /**
                 * desc : 风稍大，出门逛街前记得给秀发定型。
                 * title : 较适宜
                 */

                private String desc;
                private String title;

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
            }

            public static class AcBeanXXXXXX {
                /**
                 * desc : 暂缺
                 * title : 暂缺
                 */

                private String desc;
                private String title;

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
            }

            public static class MfBeanXXXXXX {
                /**
                 * desc : 暂缺
                 * title : 暂缺
                 */

                private String desc;
                private String title;

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
            }

            public static class LsBeanXXXXXX {
                /**
                 * desc : 天气阴沉，不太适宜晾晒。
                 * title : 不适宜
                 */

                private String desc;
                private String title;

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
            }

            public static class GlassBeanXXXXXX {
                /**
                 * desc : 不需要佩戴
                 * title : 不需要
                 */

                private String desc;
                private String title;

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
            }

            public static class XqBeanXXXXXX {
                /**
                 * desc : 天气热，容易烦躁
                 * title : 较差
                 */

                private String desc;
                private String title;

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
            }

            public static class ClothesBeanXXXXXX {
                /**
                 * desc : 建议穿短衫、短裤等清凉夏季服装
                 * title : 炎热
                 */

                private String desc;
                private String title;

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
            }

            public static class SportsBeanXXXXXX {
                /**
                 * desc : 适当减少运动时间并降低运动强度。
                 * title : 适宜
                 */

                private String desc;
                private String title;

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
            }

            public static class HcBeanXXXXXX {
                /**
                 * desc : 风稍大会对划船产生一定影响。
                 * title : 较适宜
                 */

                private String desc;
                private String title;

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
            }

            public static class ComfortBeanXXXXXX {
                /**
                 * desc : 热，感觉不舒适
                 * title : 较差
                 */

                private String desc;
                private String title;

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
            }

            public static class WashCarBeanXXXXXX {
                /**
                 * desc : 有雨，雨水和泥水会弄脏爱车
                 * title : 不宜
                 */

                private String desc;
                private String title;

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
            }

            public static class ColdBeanXXXXXX {
                /**
                 * desc : 感冒机率较低，避免长期处于空调屋中。
                 * title : 少发
                 */

                private String desc;
                private String title;

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
            }
        }

        public static class _$3hourForcastBeanXXXXXX {
            /**
             * temperature : 27
             * wind_direction : 西南风
             * temperature_min : 27
             * hour : 8时-11时
             * weather_pic : http://app1.showapi.com/weather/icon/day/01.png
             * temperature_max : 33
             * wind_power : <3级,3
             * weather : 多云
             */

            private String temperature;
            private String wind_direction;
            private String temperature_min;
            private String hour;
            private String weather_pic;
            private String temperature_max;
            private String wind_power;
            private String weather;

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getWind_direction() {
                return wind_direction;
            }

            public void setWind_direction(String wind_direction) {
                this.wind_direction = wind_direction;
            }

            public String getTemperature_min() {
                return temperature_min;
            }

            public void setTemperature_min(String temperature_min) {
                this.temperature_min = temperature_min;
            }

            public String getHour() {
                return hour;
            }

            public void setHour(String hour) {
                this.hour = hour;
            }

            public String getWeather_pic() {
                return weather_pic;
            }

            public void setWeather_pic(String weather_pic) {
                this.weather_pic = weather_pic;
            }

            public String getTemperature_max() {
                return temperature_max;
            }

            public void setTemperature_max(String temperature_max) {
                this.temperature_max = temperature_max;
            }

            public String getWind_power() {
                return wind_power;
            }

            public void setWind_power(String wind_power) {
                this.wind_power = wind_power;
            }

            public String getWeather() {
                return weather;
            }

            public void setWeather(String weather) {
                this.weather = weather;
            }
        }
    }

    public static class HourDataListBean {
        /**
         * wind_direction : 南风
         * aqi : 48
         * weather_pic : http://app1.showapi.com/weather/icon/night/00.png
         * wind_power : 1级
         * temperature_time : 00:00
         * weather_code : 00
         * temperature : 27
         * sd : 90%
         * aqiDetail : {"quality":"优质","aqi":"48","pm10":"47","area":"武汉","co":"0.8","o3":"41","so2":"5","no2":"39","primary_pollutant":"","o3_8h":"78","num":"211","pm2_5":"21"}
         * weather : 晴
         */

        private String wind_direction;
        private String aqi;
        private String weather_pic;
        private String wind_power;
        private String temperature_time;
        private String weather_code;
        private String temperature;
        private String sd;
        private AqiDetailBeanX aqiDetail;
        private String weather;

        public String getWind_direction() {
            return wind_direction;
        }

        public void setWind_direction(String wind_direction) {
            this.wind_direction = wind_direction;
        }

        public String getAqi() {
            return aqi;
        }

        public void setAqi(String aqi) {
            this.aqi = aqi;
        }

        public String getWeather_pic() {
            return weather_pic;
        }

        public void setWeather_pic(String weather_pic) {
            this.weather_pic = weather_pic;
        }

        public String getWind_power() {
            return wind_power;
        }

        public void setWind_power(String wind_power) {
            this.wind_power = wind_power;
        }

        public String getTemperature_time() {
            return temperature_time;
        }

        public void setTemperature_time(String temperature_time) {
            this.temperature_time = temperature_time;
        }

        public String getWeather_code() {
            return weather_code;
        }

        public void setWeather_code(String weather_code) {
            this.weather_code = weather_code;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getSd() {
            return sd;
        }

        public void setSd(String sd) {
            this.sd = sd;
        }

        public AqiDetailBeanX getAqiDetail() {
            return aqiDetail;
        }

        public void setAqiDetail(AqiDetailBeanX aqiDetail) {
            this.aqiDetail = aqiDetail;
        }

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public static class AqiDetailBeanX {
            /**
             * quality : 优质
             * aqi : 48
             * pm10 : 47
             * area : 武汉
             * co : 0.8
             * o3 : 41
             * so2 : 5
             * no2 : 39
             * primary_pollutant :
             * o3_8h : 78
             * num : 211
             * pm2_5 : 21
             */

            private String quality;
            private String aqi;
            private String pm10;
            private String area;
            private String co;
            private String o3;
            private String so2;
            private String no2;
            private String primary_pollutant;
            private String o3_8h;
            private String num;
            private String pm2_5;

            public String getQuality() {
                return quality;
            }

            public void setQuality(String quality) {
                this.quality = quality;
            }

            public String getAqi() {
                return aqi;
            }

            public void setAqi(String aqi) {
                this.aqi = aqi;
            }

            public String getPm10() {
                return pm10;
            }

            public void setPm10(String pm10) {
                this.pm10 = pm10;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getCo() {
                return co;
            }

            public void setCo(String co) {
                this.co = co;
            }

            public String getO3() {
                return o3;
            }

            public void setO3(String o3) {
                this.o3 = o3;
            }

            public String getSo2() {
                return so2;
            }

            public void setSo2(String so2) {
                this.so2 = so2;
            }

            public String getNo2() {
                return no2;
            }

            public void setNo2(String no2) {
                this.no2 = no2;
            }

            public String getPrimary_pollutant() {
                return primary_pollutant;
            }

            public void setPrimary_pollutant(String primary_pollutant) {
                this.primary_pollutant = primary_pollutant;
            }

            public String getO3_8h() {
                return o3_8h;
            }

            public void setO3_8h(String o3_8h) {
                this.o3_8h = o3_8h;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public String getPm2_5() {
                return pm2_5;
            }

            public void setPm2_5(String pm2_5) {
                this.pm2_5 = pm2_5;
            }
        }
    }

    public static class AlarmListBean {
        /**
         * issueTime : 2018-09-18 17:48:00
         * city : 武汉市
         * issueContent : 武汉市气象台2018年09月18日17时48分发布雷电黄色预警信号：预计未来6小时，江夏、黄陂、新洲局部有雷电活动，雨量30-50毫米，阵风6-8级，请注意防范。（预警信息来源：国家预警信息发布中心）
         * province : 湖北省
         * signalLevel : 黄色
         * signalType : 雷电
         */

        private String issueTime;
        private String city;
        private String issueContent;
        private String province;
        private String signalLevel;
        private String signalType;

        public String getIssueTime() {
            return issueTime;
        }

        public void setIssueTime(String issueTime) {
            this.issueTime = issueTime;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getIssueContent() {
            return issueContent;
        }

        public void setIssueContent(String issueContent) {
            this.issueContent = issueContent;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getSignalLevel() {
            return signalLevel;
        }

        public void setSignalLevel(String signalLevel) {
            this.signalLevel = signalLevel;
        }

        public String getSignalType() {
            return signalType;
        }

        public void setSignalType(String signalType) {
            this.signalType = signalType;
        }
    }
}
