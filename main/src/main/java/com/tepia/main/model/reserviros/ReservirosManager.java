package com.tepia.main.model.reserviros;

import com.tepia.base.http.BaseResponse;
import com.tepia.base.http.RetrofitManager;
import com.tepia.main.APPCostant;
import com.tepia.main.model.map.VideoResponse;
import com.tepia.main.model.user.UserManager;

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
 * Created by      android studio
 *
 * @author :       ly
 * Date            :       2018-09-20
 * Time            :       下午6:49
 * Version         :       1.0
 * location        :       武汉研发中心
 * 功能描述         :       首页水库模块
 **/
public class ReservirosManager {
    private ReserviorsService mRetrofitService;
    private static final ReservirosManager ourInstance = new ReservirosManager();

    public static ReservirosManager getInstance() {
        return ourInstance;
    }

    private ReservirosManager() {
        this.mRetrofitService = RetrofitManager.getRetrofit(APPCostant.API_SERVER_URL + APPCostant.API_SERVER_USER_ADMIN).create(ReserviorsService.class);
    }

    /**
     * 水库到访日志列表
     *
     * @param reservoirId
     * @param currentPage
     * @param pageSize
     * @return
     */
    public Observable<VisitLogBean> getPageList(String reservoirId, String currentPage, String pageSize) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getPageList(token, reservoirId, currentPage, pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 水库月汛限水位-分页查询
     *
     * @param reservoirId
     * @param currentPage
     * @param pageSize
     * @return
     */
    public Observable<FloodSeasonBean> getReservoirFloodSeason(String reservoirId, String currentPage, String pageSize) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getReservoirFloodSeason(token, reservoirId, currentPage, pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 查询汛限水位
     *
     * @param reservoirId
     * @return
     */
    public Observable<CurrentFloodSeasonBean> findReservoirCurrentFloodSeason(String reservoirId) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.findReservoirCurrentFloodSeason(token, reservoirId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 水库月汛限水位-修改
     *
     * @param id
     * @param floodLevel
     * @return
     */
    public Observable<BaseResponse> updateFloodSeason(String id, String floodLevel, String reservoirId) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.updateFloodSeason(token, id, floodLevel, reservoirId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * 水库月汛限水位-新增
     *
     * @param reservoirId
     * @param floodYearMonth
     * @param floodLevel
     * @return
     */
    public Observable<BaseResponse> addReservoirFloodSeason(String reservoirId, String floodYearMonth, String floodLevel) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.addReservoirFloodSeason(token, reservoirId, floodYearMonth, floodLevel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 水库到访日志详情
     *
     * @param id
     * @return
     */
    public Observable<VisitLogDetailBean> detail(String id) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.detail(token, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * 新增到访日志
     *
     * @param reservoirId
     * @param visitCause
     * @param workContent
     * @param remark
     * @param visitTime
     * @param selectPhotos
     * @return
     */
    public Observable<BaseResponse> insert(String reservoirId, String visitCause, String workContent, String remark, String visitTime,
                                           ArrayList<String> selectPhotos) {

        Map<String, RequestBody> params = new HashMap<>();
        params.put("reservoirId", RetrofitManager.convertToRequestBody(reservoirId));
        params.put("visitCause", RetrofitManager.convertToRequestBody(visitCause));
        params.put("workContent", RetrofitManager.convertToRequestBody(workContent));
        params.put("remark", RetrofitManager.convertToRequestBody(remark));
        params.put("visitTime", RetrofitManager.convertToRequestBody(visitTime));
        List<File> fileList = new ArrayList<>();
        for (int i = 0; i < selectPhotos.size(); i++) {
            File file = new File(selectPhotos.get(i));
            fileList.add(file);

        }
        List<MultipartBody.Part> pathList = RetrofitManager.filesToMultipartBodyParts("files", fileList);

        String token = UserManager.getInstance().getToken();

        return mRetrofitService.insert(token, params, pathList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());


    }

    /**
     * 获取配套设施
     *
     * @param reservoirId
     * @return
     */
    public Observable<SupportingBean> getDeviceByReservoir(String reservoirId) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getDeviceByReservoir(token, reservoirId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<BaseResponse> deleteReservoirDevice(String id){
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.deleteReservoirDevice(token,id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<BaseResponse> updateReservoirDevice(String id,String reservoirId, String deName, String deType, String deTotals, String deFunction,String beginUseDate,String position,
                                                          String deStatus,String remark,ArrayList<String> selectPhotos) {

        Map<String, RequestBody> params = new HashMap<>();
        params.put("id", RetrofitManager.convertToRequestBody(id));
        params.put("reservoirId", RetrofitManager.convertToRequestBody(reservoirId));
        params.put("deName", RetrofitManager.convertToRequestBody(deName));
        params.put("deType", RetrofitManager.convertToRequestBody(deType));
        params.put("deTotals", RetrofitManager.convertToRequestBody(deTotals));
        params.put("deFunction", RetrofitManager.convertToRequestBody(deFunction));
        params.put("beginUseDate", RetrofitManager.convertToRequestBody(beginUseDate));
        params.put("position", RetrofitManager.convertToRequestBody(position));
        params.put("deStatus", RetrofitManager.convertToRequestBody(deStatus));
        params.put("remark", RetrofitManager.convertToRequestBody(remark));
        List<File> fileList = new ArrayList<>();
        for (int i = 0; i < selectPhotos.size(); i++) {
            File file = new File(selectPhotos.get(i));
            fileList.add(file);

        }
        List<MultipartBody.Part> pathList = RetrofitManager.filesToMultipartBodyParts("files", fileList);
        String token = UserManager.getInstance().getToken();

        return mRetrofitService.updateReservoirDevice(token, params, pathList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public Observable<BaseResponse> insertReservoirDevice(String reservoirId, String deName, String deType, String deTotals, String deFunction,String beginUseDate,String position,
                                                          String deStatus,String remark,
                                           ArrayList<String> selectPhotos) {

        Map<String, RequestBody> params = new HashMap<>();
        params.put("reservoirId", RetrofitManager.convertToRequestBody(reservoirId));
        params.put("deName", RetrofitManager.convertToRequestBody(deName));
        params.put("deType", RetrofitManager.convertToRequestBody(deType));
        params.put("deTotals", RetrofitManager.convertToRequestBody(deTotals));
        params.put("deFunction", RetrofitManager.convertToRequestBody(deFunction));
        params.put("beginUseDate", RetrofitManager.convertToRequestBody(beginUseDate));
        params.put("position", RetrofitManager.convertToRequestBody(position));
        params.put("deStatus", RetrofitManager.convertToRequestBody(deStatus));
        params.put("remark", RetrofitManager.convertToRequestBody(remark));
        List<File> fileList = new ArrayList<>();
        for (int i = 0; i < selectPhotos.size(); i++) {
            File file = new File(selectPhotos.get(i));
            fileList.add(file);

        }
        List<MultipartBody.Part> pathList = RetrofitManager.filesToMultipartBodyParts("files", fileList);

        String token = UserManager.getInstance().getToken();

        return mRetrofitService.insertReservoirDevice(token, params, pathList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());


    }

    /**
     * 水库安全运行报告
     *
     * @param reservoirId
     * @return
     */
    public Observable<SafeRunningBean> getSafetyReportByReservoir(String reservoirId) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getSafetyReportByReservoir(token, reservoirId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 查询防汛物资
     *
     * @param reservoirId
     * @return
     */
    public Observable<FloodBean> getMaterialByReservoir(String reservoirId) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getMaterialByReservoir(token, reservoirId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 根据防汛物资ID查询防汛物资
     *
     * @param materialId
     * @return
     */
    public Observable<FloodBeanDetailBean> getMaterialByMaterialId(String materialId) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getMaterialByMaterialId(token, materialId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 查询水库安全管理应急预案
     *
     * @param reservoirId
     * @return
     */
    public Observable<OperationPlanBean> getEmergencyByReservoir(String reservoirId) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getEmergencyByReservoir(token, reservoirId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 查询调度运行方案
     *
     * @param reservoirId
     * @return
     */
    public Observable<OperationPlanBean> getFloodControlByReservoir(String reservoirId) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getFloodControlByReservoir(token, reservoirId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 查询水库简介
     *
     * @param reservoirId
     * @return
     */
    public Observable<IntroduceOfReservoirsBean> getBaseInfo(String reservoirId) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getBaseInfo(token, reservoirId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 查询视频
     *
     * @param reservoirId
     * @return
     */
    public Observable<VideoResponse> getReservoirVideo(String reservoirId) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getReservoirVideo(token, reservoirId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 查询水库库容曲线
     *
     * @param reservoirId
     * @return
     */
    public Observable<CapacityBean> getStorageCurveByReservoir(String reservoirId) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getStorageCurveByReservoir(token, reservoirId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 根据业务主键查询文件
     *
     * @param bizKey
     * @return
     */
    public Observable<BizkeyBean> getFileByBizKey(String bizKey) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getFileByBizKey(token, bizKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public Observable<BaseResponse> deleteReservoirMaterial(String id) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.deleteReservoirMaterial(token, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<BaseResponse> addReservoirMaterial(String reservoirId, String meName, String meType, String meTotals, String position,
                                                         String manageName, String phoneNum, String remark, ArrayList<String> files) {
        String token = UserManager.getInstance().getToken();
        Map<String, RequestBody> params = new HashMap<>();
        params.put("reservoirId", RetrofitManager.convertToRequestBody(reservoirId));
        params.put("meName", RetrofitManager.convertToRequestBody(meName));
        params.put("meType", RetrofitManager.convertToRequestBody(meType));
        params.put("meTotals", RetrofitManager.convertToRequestBody(meTotals));
        params.put("position", RetrofitManager.convertToRequestBody(position));
        params.put("manageName", RetrofitManager.convertToRequestBody(manageName));
        params.put("phoneNum", RetrofitManager.convertToRequestBody(phoneNum));
        params.put("remark", RetrofitManager.convertToRequestBody(remark));

        List<File> fileList = new ArrayList<>();
        for (int i = 0; i < files.size(); i++) {
            File file = new File(files.get(i));
            fileList.add(file);
        }
        List<MultipartBody.Part> beforePathList = RetrofitManager.filesToMultipartBodyParts("files", fileList);
        return mRetrofitService.addReservoirMaterial(token, params, beforePathList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<BaseResponse> updateReservoirMaterial(String id, String reservoirId, String meName, String meType, String meTotals, String position,
                                                            String manageName, String phoneNum, String remark, ArrayList<String> files) {
        String token = UserManager.getInstance().getToken();
        Map<String, RequestBody> params = new HashMap<>();
        params.put("id", RetrofitManager.convertToRequestBody(id));
        params.put("reservoirId", RetrofitManager.convertToRequestBody(reservoirId));
        params.put("meName", RetrofitManager.convertToRequestBody(meName));
        params.put("meType", RetrofitManager.convertToRequestBody(meType));
        params.put("meTotals", RetrofitManager.convertToRequestBody(meTotals));
        params.put("position", RetrofitManager.convertToRequestBody(position));
        params.put("manageName", RetrofitManager.convertToRequestBody(manageName));
        params.put("phoneNum", RetrofitManager.convertToRequestBody(phoneNum));
        params.put("remark", RetrofitManager.convertToRequestBody(remark));

        List<File> fileList = new ArrayList<>();
        for (int i = 0; i < files.size(); i++) {
            File file = new File(files.get(i));
            fileList.add(file);
        }
        List<MultipartBody.Part> beforePathList = RetrofitManager.filesToMultipartBodyParts("files", fileList);
        return mRetrofitService.updateReservoirMaterial(token, params, beforePathList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
