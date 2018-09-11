package com.tepia.main.model.dictmap;

import com.tepia.base.http.BaseResponse;
import com.tepia.main.model.image.DeleteImageResponse;
import com.tepia.main.model.image.ImageURLResponse;
import com.tepia.main.model.image.ImageUpDateResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

/**
 * Created by Joeshould on 2018/5/25.
 */

public interface DictMapService {
    /**
     * 获取数据字典
     * @param token
     * @return
     */
    @GET("api/admin/sysDict/getDictMap")
    Observable<DictMapResponse>  getDictMap(@Header("Authorization") String token);
}
