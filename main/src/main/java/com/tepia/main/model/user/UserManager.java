package com.tepia.main.model.user;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.tepia.base.http.BaseResponse;
import com.tepia.base.http.RetrofitManager;
import com.tepia.base.utils.SPUtils;
import com.tepia.base.utils.Utils;
import com.tepia.main.APPCostant;
import com.tepia.main.view.login.LoginPresenter;

import cn.jpush.android.api.JPushInterface;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Joeshould on 2018/5/22.
 */

public class UserManager {
    private UserHttpService mRetrofitService;
    private static final UserManager ourInstance = new UserManager();
    private static final UserManager ourInstance_admin = new UserManager(APPCostant.API_SERVER_USER_ADMIN);
    private UserInfoBean userBean;
    private static final String USERINFO = "USERINFO";
    private static final String TOKEN = "TOKEN";

    public static UserManager getInstance() {
        return ourInstance;
    }

    public static UserManager getInstance_ADMIN() {
        return ourInstance_admin;
    }

    private UserManager() {
        this.mRetrofitService = RetrofitManager.getRetrofit(APPCostant.API_SERVER_URL+APPCostant.API_SERVER_USER_AREA).create(UserHttpService.class);
    }

    private UserManager(String adminstr) {
        this.mRetrofitService = RetrofitManager.getRetrofit(APPCostant.API_SERVER_URL+adminstr).create(UserHttpService.class);
    }

    /**
     * 登录
     *
     * @param logincode
     * @param password
     * @return
     */
    public Observable<UserLoginResponse> login(String logincode, String password,String registId) {
        return mRetrofitService.login(logincode, password,registId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 修改密码
     * @param newPassword
     * @param password
     * @return
     */
    public Observable<BaseResponse> updatePwd(String newPassword, String password) {
        String token = getToken();

        return mRetrofitService.updatePwd(token,newPassword, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 获取用户登录信息
     * @return
     */
    public Observable<UserInfoBean> getLoginUser() {
        String token = getToken();

        return mRetrofitService.getLoginUser(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 获取动态菜单
     * @return
     */
    public Observable<MenuBean> getByTokenMenu() {
        String token = getToken();
        return mRetrofitService.getByTokenMenu(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }



    /**
     * 编辑用户信息
     * @param userName
     * @param email
     * @param mobile
     * @param address
     * @return
     */
    public Observable<BaseResponse> updateSysUser(String userName,String email,String mobile,String address) {
        String token = getToken();

        return mRetrofitService.updateSysUser(token,userName,email,mobile,address)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * 将用户信息 存到 sp中
     *
     * @param userBean
     */
    public void setUserBean(UserInfoBean userBean) {
        if(userBean != null) {
            this.userBean = userBean;
            SPUtils.getInstance(Utils.getContext()).putString(USERINFO, new Gson().toJson(userBean).toString());
        }
    }

    public void saveToken(UserLoginResponse userBean){
        if(userBean != null) {
            SPUtils.getInstance(Utils.getContext()).putString(TOKEN, userBean.getData());
        }

    }

    /**
     * 获取用户token
     * @return
     */
    public String getToken(){
        String temp = SPUtils.getInstance(Utils.getContext()).getString("TOKEN", "");
        return temp;
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public UserInfoBean getUserBean() {
        if (userBean != null) {
            return userBean;
        } else {
            String temp = SPUtils.getInstance(Utils.getContext()).getString(USERINFO, "");
            if (!TextUtils.isEmpty(temp)) {
                return new Gson().fromJson(temp, UserInfoBean.class);
            } else {
                return userBean;
            }
        }
    }

    /**
     * 退出登录时清空缓存和断开极光推送
     */
    public void clearCacheAndStopPush(){
        SPUtils.getInstance(Utils.getContext()).remove(USERINFO);
        SPUtils.getInstance(Utils.getContext()).remove(TOKEN);
        SPUtils.getInstance(Utils.getContext()).remove(LoginPresenter.prefence_menu);
        JPushInterface.stopPush(Utils.getContext());


    }
}
