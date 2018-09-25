package com.tepia.main.model.user;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tepia.base.http.BaseResponse;
import com.tepia.base.http.RetrofitManager;
import com.tepia.base.utils.SPUtils;
import com.tepia.base.utils.Utils;
import com.tepia.main.APPCostant;
import com.tepia.main.model.detai.ReservoirBean;
import com.tepia.main.model.map.ReservoirListResponse;
import com.tepia.main.view.MenuItemBean;
import com.tepia.main.view.login.LoginPresenter;

import java.util.ArrayList;
import java.util.List;

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
        this.mRetrofitService = RetrofitManager.getRetrofit(APPCostant.API_SERVER_URL + APPCostant.API_SERVER_USER_AREA).create(UserHttpService.class);
    }

    private UserManager(String adminstr) {
        this.mRetrofitService = RetrofitManager.getRetrofit(APPCostant.API_SERVER_URL + adminstr).create(UserHttpService.class);
    }

    /**
     * 登录
     *
     * @param logincode
     * @param password
     * @return
     */
    public Observable<UserLoginResponse> login(String logincode, String password, String registId) {
        return mRetrofitService.login(logincode, password, registId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 修改密码
     *
     * @param newPassword
     * @param password
     * @return
     */
    public Observable<BaseResponse> updatePwd(String newPassword, String password) {
        String token = getToken();

        return mRetrofitService.updatePwd(token, newPassword, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 获取用户登录信息
     *
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
     *
     * @return
     */
    public Observable<MenuBean> getByTokenMenu() {
        String token = getToken();
        return mRetrofitService.getByTokenMenu(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 获取动态菜单
     *
     * @return
     */
    public Observable<MenuListResponse> getByTokenMenu2() {
        String token = getToken();
        return mRetrofitService.getByTokenMenu2(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * 编辑用户信息
     *
     * @param userName
     * @param email
     * @param mobile
     * @param address
     * @return
     */
    public Observable<BaseResponse> updateSysUser(String userName, String email, String mobile, String address) {
        String token = getToken();

        return mRetrofitService.updateSysUser(token, userName, email, mobile, address)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * 将用户信息 存到 sp中
     *
     * @param userBean
     */
    public void setUserBean(UserInfoBean userBean) {
        if (userBean != null) {
            this.userBean = userBean;
            SPUtils.getInstance(Utils.getContext()).putString(USERINFO, new Gson().toJson(userBean).toString());
        }
    }

    public void saveToken(UserLoginResponse userBean) {
        if (userBean != null) {
            SPUtils.getInstance(Utils.getContext()).putString(TOKEN, userBean.getData());
        }

    }

    /**
     * 获取用户token
     *
     * @return
     */
    public String getToken() {
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
    public void clearCacheAndStopPush() {
        SPUtils.getInstance(Utils.getContext()).remove(USERINFO);
        SPUtils.getInstance(Utils.getContext()).remove(TOKEN);
        SPUtils.getInstance(Utils.getContext()).remove(LoginPresenter.prefence_menu);
        JPushInterface.stopPush(Utils.getContext());


    }


    /**
     * @return
     */
    public Observable<ReservoirListResponse> getReservoirList() {
        String token = getToken();

        return mRetrofitService.getReservoirList(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * @param data
     */
    public void saveMenuList(ArrayList<MenuItemBean> data) {
        SPUtils.getInstance().putString("MENULIST", new Gson().toJson(data));
    }

    /**
     * @return
     */
    public ArrayList<MenuItemBean> getMenuList() {
        ArrayList<MenuItemBean> menuItemBeans = null;
        String temp = SPUtils.getInstance().getString("MENULIST", "");
        if (!TextUtils.isEmpty(temp)) {
            menuItemBeans = new Gson().fromJson(temp, new TypeToken<ArrayList<MenuItemBean>>() {
            }.getType());
        }
        return menuItemBeans;
    }

    /**
     * 保存 我们负责的水库
     *
     * @param data
     */
    public void saveReservoirList(List<ReservoirBean> data) {
        ArrayList<ReservoirBean> menuItemBeans = null;
        SPUtils.getInstance().putString("RESERVOIRLIST", new Gson().toJson(data));
    }

    /**
     * 获取保存 我们负责的水库
     */
    public ArrayList<ReservoirBean> getLocalReservoirList() {
        ArrayList<ReservoirBean> reservoirBeans = null;
        String temp = SPUtils.getInstance().getString("RESERVOIRLIST", "");
        if (!TextUtils.isEmpty(temp)) {
            reservoirBeans = new Gson().fromJson(temp, new TypeToken<ArrayList<ReservoirBean>>() {
            }.getType());
        }
        return reservoirBeans;
    }

    /**
     * @param reservoirBean
     */
    public void saveDefaultReservoir(ReservoirBean reservoirBean) {
        SPUtils.getInstance().putString("DEFAULTRESERVOIR", new Gson().toJson(reservoirBean));
    }

    public ReservoirBean getDefaultReservoir() {
        ReservoirBean reservoirBean = null;
        String temp = SPUtils.getInstance().getString("DEFAULTRESERVOIR", "");
        try {
            if (!TextUtils.isEmpty(temp)) {
                reservoirBean = new Gson().fromJson(temp, ReservoirBean.class);
            }
        } catch (Exception e) {
        }
        return reservoirBean;
    }

    public Observable<HomeGetReservoirInfoResponse> getAppHomeGetReservoirInfo(String reservoirId) {
        String token = getToken();
        String types = "";
        if (UserManager.getInstance().getMenuList().get(1).getMenuIcon().equals("110")){
            MenuItemBean bean = UserManager.getInstance().getMenuList().get(1);
            if (bean.getChildren()!= null &&(bean.getChildren().size() ==2 || bean.getChildren().size() ==1)){
                for (MenuItemBean bean1 :bean.getChildren()){
                    if (bean1.getMenuIcon().equals("111") ){
                        types +="1,";
                    }
                    if (bean1.getMenuIcon().equals("112") ){
                        types +="2,";
                    }
                    if (bean1.getMenuIcon().equals("113") ){
                        types +="3,";
                    }
                }
                types = types.substring(0,types.length()-1);
            }
        }
        return mRetrofitService.getAppHomeGetReservoirInfo(token, reservoirId, types)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
