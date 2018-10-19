package com.tepia.main.view.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.Gson;
import com.tepia.base.AppRoutePath;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.base.utils.AppManager;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.NetUtil;
import com.tepia.base.utils.SPUtils;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.utils.Utils;
import com.tepia.base.view.dialog.loading.SimpleLoadDialog;
import com.tepia.main.R;
import com.tepia.main.model.dictmap.DictMapManager;
import com.tepia.main.model.map.ReservoirListResponse;
import com.tepia.main.model.user.MenuBean;
import com.tepia.main.model.user.MenuData;
import com.tepia.main.model.user.MenuListResponse;
import com.tepia.main.model.user.UserInfoBean;
import com.tepia.main.model.user.UserLoginResponse;
import com.tepia.main.model.user.UserManager;

import org.litepal.crud.DataSupport;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.wbtech.ums.util.GetInfoFromFile.context;


/**
 * 登陆页
 * 邮箱 784787081@qq.com
 */

public class LoginPresenter extends BasePresenterImpl<LoginContract.View> implements LoginContract.Presenter {
    public static String prefence_menu = "prefence_menu";
    public static String key_menu = "key_menu";
    private SimpleLoadDialog simpleLoadDialog;

    /**
     * 登录
     *
     * @param username
     * @param password
     */
    @Override
    public void login(String username, String password, String registId) {
        Boolean isShow = false;
        String msg = "正在登陆中";
        simpleLoadDialog = new SimpleLoadDialog(AppManager.getInstance().getCurrentActivity(), msg, true);
        if (simpleLoadDialog != null) {
            simpleLoadDialog.show();
        }
        UserManager.getInstance().login(username, password, registId).subscribe(new LoadingSubject<UserLoginResponse>(isShow, msg) {
            @Override
            protected void _onNext(UserLoginResponse userLoginResponse) {
                if (userLoginResponse.getCode() == 0) {
                    // TODO: 2018/8/28 动态菜单跟换后需要调整
//                    mView.loginSuccess();

                    UserManager.getInstance().saveToken(userLoginResponse);
                    getByTokenMenu2();
                    DictMapManager.getInstance().getDictMapEntity();
                    saveUserInfoBean();
//                    getByTokenMenu();

                } else {
                    ToastUtils.shortToast(R.string.errro_login);
                    if (simpleLoadDialog != null) {
                        simpleLoadDialog.dismiss();
                    }
                }

            }

            @Override
            protected void _onError(String message) {
                if (simpleLoadDialog != null) {
                    simpleLoadDialog.dismiss();
                }
                ToastUtils.shortToast(message);
            }
        });
    }

    private void saveUserInfo(UserLoginResponse userLoginResponse) {
        UserInfoBean userInfoBean = new UserInfoBean();
        userInfoBean.getData().setUserName(userLoginResponse.getUserName());
        userInfoBean.getData().setEmail(userLoginResponse.getEmail());
        userInfoBean.getData().setMobile(userLoginResponse.getPhone());
        UserManager.getInstance().setUserBean(userInfoBean);

    }

    /**
     * 获取用户信息并保存
     */
    private void saveUserInfoBean() {
        if (!NetUtil.isNetworkConnected(Utils.getContext())) {
            ToastUtils.shortToast(R.string.no_network);
            return;
        }
        UserManager.getInstance_ADMIN().getLoginUser().subscribe(new LoadingSubject<UserInfoBean>(false, "") {
            @Override
            protected void _onNext(UserInfoBean userInfoBean) {
                if (userInfoBean != null) {
                    if (userInfoBean.getCode() == 0) {
                        LogUtil.e("getLoginUser", "getLoginUser:成功获取用户信息------");
                        UserManager.getInstance().setUserBean(userInfoBean);
                    } else {
                        ToastUtils.longToast(userInfoBean.getMsg());

                    }
                }
            }

            @Override
            protected void _onError(String message) {
                UserInfoBean userInfoBean = UserManager.getInstance().getUserBean();
                if (userInfoBean != null) {
                }
                LogUtil.e("getLoginUser:获取用户信息失败-----");


            }
        });
    }

    /**
     * 获取动态菜单2
     *
     * @return
     */
    private void getByTokenMenu2() {
        UserManager.getInstance_ADMIN().getByTokenMenu2().safeSubscribe(new LoadingSubject<MenuListResponse>() {
            @Override
            protected void _onNext(MenuListResponse menuListResponse) {
                if (menuListResponse.getCode() == 0) {
                    UserManager.getInstance().saveMenuList(menuListResponse.getData());
                    getReservoirList();
                }
            }

            @Override
            protected void _onError(String message) {
                if (simpleLoadDialog != null) {
                    simpleLoadDialog.dismiss();
                }
                ToastUtils.shortToast(message);
            }
        });
    }

    /**
     * 获取负责的水库列表
     *
     * @return
     */
    private void getReservoirList() {
        UserManager.getInstance_ADMIN().getReservoirList().safeSubscribe(new LoadingSubject<ReservoirListResponse>() {
            @Override
            protected void _onNext(ReservoirListResponse response) {
                if (response.getCode() == 0) {
                    UserManager.getInstance().saveReservoirList(response.getData());
                    if (response.getData() != null && response.getData().size() > 0) {
                        UserManager.getInstance().saveDefaultReservoir(response.getData().get(0));
                    }
                    mView.loginSuccess();
                    if (simpleLoadDialog != null) {
                        simpleLoadDialog.dismiss();
                    }
                } else {
                    if (simpleLoadDialog != null) {
                        simpleLoadDialog.dismiss();
                    }
                }
            }

            @Override
            protected void _onError(String message) {
                ToastUtils.shortToast(message);
                if (simpleLoadDialog != null) {
                    simpleLoadDialog.dismiss();
                }
            }
        });

    }

    /**
     * 获取动态菜单
     *
     * @return
     */
    private void getByTokenMenu() {
        if (!NetUtil.isNetworkConnected(Utils.getContext())) {
            ToastUtils.shortToast(R.string.no_network);
            if (simpleLoadDialog != null) {
                simpleLoadDialog.dismiss();
            }
            return;
        }
        UserManager.getInstance_ADMIN().getByTokenMenu().subscribe(new LoadingSubject<MenuBean>(false, "") {
            @Override
            protected void _onNext(MenuBean menuBean) {
                if (menuBean != null) {
                    if (menuBean.getCode() == 0) {
                        LogUtil.e("getByTokenMenu", "getByTokenMenu:成功获取动态菜单------");
                        try {
                            saveUser(Utils.getContext(), prefence_menu, key_menu, menuBean);
                            MenuBean menuBean_get = getMenu(Utils.getContext(), prefence_menu, key_menu);
                            if (menuBean_get != null && menuBean_get.getData().get(0).getChildren() != null
                                    && menuBean_get.getData().get(0).getChildren().size() > 0) {
                                if (simpleLoadDialog != null) {
                                    simpleLoadDialog.dismiss();
                                }
                                mView.loginSuccess();
                            } else {
                                ToastUtils.shortToast("未能获取到菜单");
                                if (simpleLoadDialog != null) {
                                    simpleLoadDialog.dismiss();
                                }
                            }
                        } catch (Exception e) {
                            if (simpleLoadDialog != null) {
                                simpleLoadDialog.dismiss();
                            }
                            e.printStackTrace();
                            ToastUtils.shortToast("未能获取到菜单");
                        }


                    } else {
                        if (simpleLoadDialog != null) {
                            simpleLoadDialog.dismiss();
                        }
                        ToastUtils.longToast(menuBean.getMsg());


                    }
                }
            }

            @Override
            protected void _onError(String message) {
                if (simpleLoadDialog != null) {
                    simpleLoadDialog.dismiss();
                }
                LogUtil.e("getByTokenMenu:获取动态菜单失败-----");
                ToastUtils.shortToast("未能获取到菜单");

            }
        });
    }

    /**
     * by lying
     * 保存菜单实体
     *
     * @param menuBean
     */
    public static void saveUser(Context context, String preferenceName, String key, MenuBean menuBean) throws Exception {
        if (menuBean instanceof Serializable) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                //把对象写到流里
                oos.writeObject(menuBean);
                String temp = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
                editor.putString(key, temp);
                editor.apply();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new Exception("User must implements Serializable");
        }
    }


    public static MenuBean getMenu(Context context, String preferenceName, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
        String temp = sharedPreferences.getString(key, "");
        ByteArrayInputStream bais = new ByteArrayInputStream(Base64.decode(temp.getBytes(), Base64.DEFAULT));
        MenuBean menuBean = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(bais);
            menuBean = (MenuBean) ois.readObject();
        } catch (IOException e) {
        } catch (ClassNotFoundException e1) {

        }
        return menuBean;
    }
}
