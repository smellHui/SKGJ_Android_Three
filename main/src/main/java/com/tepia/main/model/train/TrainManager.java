package com.tepia.main.model.train;

import com.tepia.base.http.BaseResponse;
import com.tepia.base.http.RetrofitManager;
import com.tepia.main.APPCostant;
import com.tepia.main.model.user.UserManager;
import com.tepia.main.view.maintechnology.yunwei.presenter.YunWeiJIShuService;

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
 * Created by      Intellij IDEA
 *
 * @author :       wwj
 * Date    :       2018-09-27
 * Time    :       15:27
 * Version :       1.0
 * Company :       北京太比雅科技(武汉研发中心)
 **/
public class TrainManager {
    private TrainService mRetrofitService;
    private static final TrainManager ourInstance = new TrainManager();
    public static TrainManager getInstance(){
        return ourInstance;
    }

    private TrainManager() {
        this.mRetrofitService = RetrofitManager.getRetrofit(APPCostant.API_SERVER_URL + APPCostant.API_SERVER_USER_ADMIN).create(TrainService.class);
    }

    public Observable<TrainListResponse> getTrainList(String currentPage,String pageSize){
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getTrainList(token,currentPage,pageSize).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<TrainDetailResponse> getTrainDetail(String id){
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getTrainDetail(token,id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<BaseResponse> addUserTrain(String trainTitle, String position, String trainContent, String organizeCompany, String trainDate, ArrayList<String> selectFiles){
        Map<String, RequestBody> params = new HashMap<>();
        params.put("trainTitle",RetrofitManager.convertToRequestBody(trainTitle));
        params.put("position",RetrofitManager.convertToRequestBody(position));
        params.put("trainContent",RetrofitManager.convertToRequestBody(trainContent));
        params.put("organizeCompany",RetrofitManager.convertToRequestBody(organizeCompany));
        params.put("trainDate",RetrofitManager.convertToRequestBody(trainDate));
        List<File> fileList = new ArrayList<>();
        for (int i = 0; i < selectFiles.size(); i++) {
            File file = new File(selectFiles.get(i));
            fileList.add(file);
        }
        List<MultipartBody.Part> pathList = RetrofitManager.filesToMultipartBodyParts("files",fileList);
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.addUserTrain(token,params,pathList)  .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public boolean isImage(String fileName){
        if (fileName.toLowerCase().endsWith(".gif")){
            return true;
        }else if (fileName.toLowerCase().endsWith(".jpg")){
            return true;
        }else if (fileName.toLowerCase().endsWith(".png")){
            return true;
        }else {
            return false;
        }
    }
}
