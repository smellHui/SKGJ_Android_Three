package com.tepia.main.model.reserviros;

import com.tepia.base.http.RetrofitManager;
import com.tepia.main.APPCostant;
import com.tepia.main.model.detai.DetailManager;
import com.tepia.main.model.question.AllproblemBean;
import com.tepia.main.model.user.UserManager;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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

    private  ReservirosManager() {
        this.mRetrofitService = RetrofitManager.getRetrofit(APPCostant.API_SERVER_URL+APPCostant.API_SERVER_USER_ADMIN).create(ReserviorsService.class);
    }

    /**
     * 水库到访日志列表
     * @param reservoirId
     * @param currentPage
     * @param pageSize
     * @return
     */
    public Observable<VisitLogBean> getPageList(String reservoirId,String currentPage, String pageSize) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getPageList(token,reservoirId,currentPage,pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 水库到访日志详情
     * @param id
     * @return
     */
    public Observable<VisitLogDetailBean> detail(String id) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.detail(token,id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 获取配套设施
     * @param reservoirId
     * @return
     */
    public Observable<SupportingBean> getDeviceByReservoir(String reservoirId) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getDeviceByReservoir(token,reservoirId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
