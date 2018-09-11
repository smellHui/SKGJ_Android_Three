package com.tepia.main.model.image;

import com.tepia.base.BuildConfig;
import com.tepia.base.http.RetrofitManager;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


/**
 * 图片管理
 * Created by Joeshould on 2018/5/25.
 */

public class ImageManager {
    private static final ImageManager ourInstance = new ImageManager();
    private ImageHttpService mRetrofitService;

    public static ImageManager getInstance() {
        return ourInstance;
    }

    private ImageManager() {
        this.mRetrofitService = RetrofitManager.getRetrofit(BuildConfig.API_SERVER_URL_IMAGE).create(ImageHttpService.class);
    }


    /**
     * 上传图片
     * @param imagePath
     * @return
     */
    public Observable<ImageUpDateResponse> uploadImage(String imagePath) {
        String userId = "9517D806FD5D4CF09AB0E6206281F84D";
        Map<String, RequestBody> params = new HashMap<>();
        params.put("userId", RetrofitManager.convertToRequestBody(userId));
        List<File> fileList = new ArrayList<>();

        File file = new File(imagePath);
        fileList.add(file);

        List<MultipartBody.Part> partList = RetrofitManager.filesToMultipartBodyParts(fileList);

        return mRetrofitService.uploadImage(params, partList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 删除图片
     * @param fileId
     * @param suffix
     * @return
     */
    public Observable<DeleteImageResponse> deleteImage(String fileId,String suffix) {
        return mRetrofitService.deleteImage(fileId,suffix)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 获取图片
     * @param objectId
     */
    public Observable<ImageURLResponse> getImageURLbyObjectId(String objectId) {
        return mRetrofitService.getImageURLbyObjectId(objectId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
