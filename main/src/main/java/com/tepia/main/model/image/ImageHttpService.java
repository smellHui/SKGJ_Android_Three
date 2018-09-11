package com.tepia.main.model.image;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

/**
 * Created by Joeshould on 2018/5/25.
 */

public interface ImageHttpService {
    @Multipart
    @POST("xcxFileUpload/upload.jspx")
    Observable<ImageUpDateResponse> uploadImage(@PartMap Map<String, RequestBody> params,
                                                @Part List<MultipartBody.Part> partList);

    @FormUrlEncoded
    @POST("xcxFileUpload/queryPicByObjectId.jspx")
    Observable<ImageURLResponse> getImageURLbyObjectId(@Field("objectId") String objectId);


    @FormUrlEncoded
    @POST("xcxFileUpload/deleteFile.jspx")
    Observable<DeleteImageResponse> deleteImage(@Field("fileId")String fileId,
                                                @Field("suffix")String suffix);
}
