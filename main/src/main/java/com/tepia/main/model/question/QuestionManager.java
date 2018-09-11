package com.tepia.main.model.question;

import com.tepia.base.http.BaseResponse;
import com.tepia.base.http.RetrofitManager;
import com.tepia.main.APPCostant;
import com.tepia.main.model.user.UserManager;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 问题反馈
 * Created by ly on 2018/5/24.
 */
public class QuestionManager {
    private QuestionService mRetrofitService;
    private static final QuestionManager ourInstance = new QuestionManager();

    public static QuestionManager getInstance() {
        return ourInstance;
    }


    public static List<MultipartBody.Part> filesToMultipartBodyParts(List<File> fileList) {
        List<MultipartBody.Part> param = new ArrayList<>(fileList.size());
        for (File file : fileList) {
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
            MultipartBody.Part params = MultipartBody.Part.createFormData("imagesKey", file.getName(), requestBody);
            param.add(params);
        }
        return param;
    }


    private QuestionManager() {
        this.mRetrofitService = RetrofitManager.getRetrofit(APPCostant.API_SERVER_URL+APPCostant.API_SERVER_TASK_AREA).create(QuestionService.class);
    }

    /**
     * 提交问题
     * @param questionType
     * @param questionTitle
     * @param questionContent
     * @param reservoirId
     * @param selectPhotos
     * @return
     */
    public Observable<BaseResponse> feedback(String questionType, String questionTitle, String questionContent,  String reservoirId,  ArrayList<String> selectPhotos) {

        //problemType") String problemType,
        //   //                                      @Field("problemTitle") String problemTitle,x
        //   //                                      @Field("problemDescription") String problemDescription,
        //   //                                      @Field("reservoirId") String reservoirId,
        //   //                                      @Field("files") String files
        Map<String, RequestBody> params = new HashMap<>();
        params.put("problemType", RetrofitManager.convertToRequestBody(questionType));
        params.put("problemTitle", RetrofitManager.convertToRequestBody(questionTitle));
        params.put("problemDescription", RetrofitManager.convertToRequestBody(questionContent));
        params.put("reservoirId", RetrofitManager.convertToRequestBody(reservoirId));
        List<File> fileList = new ArrayList<>();
        for (int i = 0; i < selectPhotos.size(); i++) {
            File file = new File(selectPhotos.get(i));
            fileList.add(file);

        }
        List<MultipartBody.Part> pathList = RetrofitManager.filesToMultipartBodyParts("files",fileList);

        String token = UserManager.getInstance().getToken();

        return mRetrofitService.feedback(token,params,pathList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 获取水库列表
     *
     * @return
     */
    public Observable<ReservoirBean> listReservoirInCenter(String sparent){
        String token = UserManager.getInstance().getToken();

        return mRetrofitService.listReservoirInCenter(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * 意见反馈
     * @param msgContent
     * @param mobile
     * @param selectPhotos
     * @return
     */
    public Observable<BaseResponse> appFeedBack(String msgContent, String mobile, ArrayList<String> selectPhotos) {

        Map<String, RequestBody> params = new HashMap<>();
        params.put("msgContent", RetrofitManager.convertToRequestBody(msgContent));
        params.put("mobile", RetrofitManager.convertToRequestBody(mobile));
        List<File> fileList = new ArrayList<>();
        for (int i = 0; i < selectPhotos.size(); i++) {
            File file = new File(selectPhotos.get(i));
            fileList.add(file);

        }
        List<MultipartBody.Part> pathList = RetrofitManager.filesToMultipartBodyParts("files",fileList);

        String token = UserManager.getInstance().getToken();

        return mRetrofitService.appFeedBack(token,params,pathList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 事件上报查询当前用户上报的所有事件
     * @param reservoirName
     * @param problemTitle
     * @param problemType
     * @param currentPage
     * @param pageSize
     * @return
     */
    public Observable<AllproblemBean> listAllProblem(String reservoirName, String problemTitle,  String problemType,  String currentPage, String pageSize) {
        String token = UserManager.getInstance().getToken();

        return mRetrofitService.listAllProblem(token,reservoirName,problemTitle,problemType,currentPage,pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

//    getDetailedProblemInfoByProblemId

    /**
     * 事件详情
     * @param problemId
     * @return
     */
    public Observable<ProblemDetailBean> getDetailedProblemInfoByProblemId(String problemId){
        String token = UserManager.getInstance().getToken();

        return mRetrofitService.getDetailedProblemInfoByProblemId(token,problemId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * 获取待确认问题列表
     * @param reservoirName
     * @param problemCofirmType
     * @param problemStatus
     * @param problemSource
     * @param startDate
     * @param endDate
     * @param currentPage
     * @param pageSize
     * @return
     */
    public Observable<ProblemCallListBean> getProblemCallList(String reservoirName, String problemCofirmType,  String problemStatus,  String problemSource,
                                                              String startDate,String endDate,String currentPage, String pageSize) {
        String token = UserManager.getInstance().getToken();

        return mRetrofitService.getProblemCallList(token,reservoirName,problemCofirmType,problemStatus,problemSource,startDate,endDate,currentPage,pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 获取待审核问题列表
     * @param reservoirName
     * @param problemType
     * @param problemTitle
     * @param problemCofirmType
     * @param problemStatus
     * @param startDate
     * @param endDate
     * @param currentPage
     * @param pageSize
     * @return
     */
    public Observable<ProblemCallListBean> getProblemExamineList(String reservoirName, String problemType,String problemTitle, String problemCofirmType,  String problemStatus,
                                                              String startDate,String endDate,String currentPage, String pageSize) {
        String token = UserManager.getInstance().getToken();

        return mRetrofitService.getProblemExamineList(token,reservoirName,problemType,problemTitle,problemCofirmType,problemStatus,startDate,endDate,currentPage,pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 待确认问题中意见反馈
     * @param problemId
     * @param excuteDes
     * @return
     */
    public Observable<BaseResponse> feedback(String problemId,String excuteDes){
        String token = UserManager.getInstance().getToken();

        return mRetrofitService.feedback(token,problemId,excuteDes)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 提交确认问题操作
     * @param problemId
     * @param planType
     * @param problemConfirmType
     * @return
     */
    public Observable<BaseResponse> confirmProblem(String problemId,String planType,String problemConfirmType){
        String token = UserManager.getInstance().getToken();

        return mRetrofitService.confirmProblem(token,problemId,planType,problemConfirmType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 提交审核问题操作
     * @param problemId
     * @param executeType
     * @param executeDes
     * @param isAnimal
     * @return
     */
    public Observable<BaseResponse> reviewProblem(String problemId,String executeType,String executeDes,String isAnimal){
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.reviewProblem(token,problemId,executeType,executeDes,isAnimal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }



}
