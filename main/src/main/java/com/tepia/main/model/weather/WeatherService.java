package com.tepia.main.model.weather;

import com.tepia.main.model.dictmap.DictMapResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by Joeshould on 2018/5/25.
 */

public interface WeatherService {


    @GET("weather/query")
    Observable<WeatherResponse> getWeather(@Header("Authorization") String appcode,
                                           @Query("city") String city);
}
