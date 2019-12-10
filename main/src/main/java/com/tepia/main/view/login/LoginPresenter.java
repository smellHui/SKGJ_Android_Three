package com.tepia.main.view.login;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.util.Base64;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.Gson;
//import com.pgyersdk.update.PgyUpdateManager;
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
import com.tepia.main.CacheConsts;
import com.tepia.main.R;
import com.tepia.main.model.dictmap.DictMapManager;
import com.tepia.main.model.map.ReservoirListResponse;
import com.tepia.main.model.user.MenuBean;
import com.tepia.main.model.user.MenuData;
import com.tepia.main.model.user.MenuListResponse;
import com.tepia.main.model.user.UserInfoBean;
import com.tepia.main.model.user.UserLoginResponse;
import com.tepia.main.model.user.UserManager;
import com.tepia.main.view.MainActivity;
import com.tepia.main.view.TabMainFragmentFactory;

import org.litepal.crud.DataSupport;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

import cn.jpush.android.api.JPushInterface;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by      Android studio
 *
 * @author :ly (from Center Of Wuhan)
 * 创建时间 :
 * 更新时间 : 2019-3-26 改为根据用户信息返回角色list来切换角色
 * Version :1.0
 * 功能描述 :
 **/

public class LoginPresenter extends BasePresenterImpl<LoginContract.View> implements LoginContract.Presenter {
    public static String prefence_menu = "prefence_menu";
    public static String key_menu = "key_menu";

    /**
     * 登录
     *
     * @param username
     * @param password
     */
    @Override
    public void login(Context mContext, String username, String password, String registId) {
        Boolean isShow = true;
        String msg = "正在登录中";
        /*simpleLoadDialog = new SimpleLoadDialog(AppManager.getInstance().getCurrentActivity(), msg, true);
        if (simpleLoadDialog != null) {
            simpleLoadDialog.show();
        }*/
        UserManager.getInstance().login(username, password, registId).subscribe(new LoadingSubject<UserLoginResponse>(isShow, msg) {
            @Override
            protected void _onNext(UserLoginResponse userLoginResponse) {
                if (userLoginResponse.getCode() == 0) {
                    // TODO: 2018/8/28 动态菜单跟换后需要调整
//                    mView.loginSuccess();

                    if(mContext != null) {
                        UserManager.getInstance().saveToken(userLoginResponse);
//                    getByTokenMenu2();
                        DictMapManager.getInstance().getDictMapEntity();
                        saveUserInfoBean(mContext, false);
                    }else{
                        UserManager.getInstance().saveToken(userLoginResponse);
                        DictMapManager.getInstance().getDictMapEntity();
                        saveUserInfoBean(mContext, false);
                        getByTokenMenu2();


                    }

                } else {
                    ToastUtils.shortToast(R.string.errro_login);

                }

            }

            @Override
            protected void _onError(String message) {

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
    public void saveUserInfoBean(Context mContext,boolean loginAgain) {
        if (!NetUtil.isNetworkConnected(Utils.getContext())) {
            ToastUtils.shortToast(R.string.no_network);
            return;
        }
        UserManager.getInstance_ADMIN().getLoginUser().subscribe(new LoadingSubject<UserInfoBean>(true, "正在获取数据...") {
            @Override
            protected void _onNext(UserInfoBean userInfoBean) {
                if (userInfoBean != null) {
                    if (userInfoBean.getCode() == 0) {

                        LogUtil.e("getLoginUser", "getLoginUser:成功获取用户信息------");
                        UserManager.getInstance().setUserBean(userInfoBean);
                        List<UserInfoBean.DataBean.SysRolesBean> sysRolesBeanList = userInfoBean.getData().getSysRoles();
                        if (sysRolesBeanList != null) {
                            if (sysRolesBeanList.size() > 1) {
                                choiceRole(mContext, sysRolesBeanList,loginAgain);
                            } else if (sysRolesBeanList.size() == 1) {
                                getByTokenMenuByRole(sysRolesBeanList.get(0).getRoleCode(),loginAgain);
                            }
                        }


                    } else {
                        ToastUtils.longToast(userInfoBean.getMsg());

                    }
                }
            }

            @Override
            protected void _onError(String message) {
                ToastUtils.shortToast(message + " ");
                LogUtil.e("getLoginUser:获取用户信息失败-----");


            }
        });
    }


    /**
     * 如果是多个角色则可以单选
     * @param mContext
     * @param sysRolesBeanList
     */
//    private int choiceWhich = 0;
    private void choiceRole(Context mContext, List<UserInfoBean.DataBean.SysRolesBean> sysRolesBeanList,boolean loginAgain) {
        int choiceWhich =  SPUtils.getInstance().getInt(CacheConsts.ROLEWHICH,-1);
        int size = sysRolesBeanList.size();
        String[] items = new String[size];
        for (int i = 0; i < size; i++) {
            if(choiceWhich == i) {
                items[i] = sysRolesBeanList.get(i).getRoleName()+"(上一次登录时身份)";
            }else{
                items[i] = sysRolesBeanList.get(i).getRoleName();

            }
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("您有多个身份，请选择其中一个登录")
                .setSingleChoiceItems(items, choiceWhich, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SPUtils.getInstance().putInt(CacheConsts.ROLEWHICH,which);


                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int choiceWhich =  SPUtils.getInstance().getInt(CacheConsts.ROLEWHICH,-1);
                        if (choiceWhich == -1) {
                            ToastUtils.shortToast("请选择一个身份");
                            return;
                        }
                        getByTokenMenuByRole(sysRolesBeanList.get(choiceWhich).getRoleCode(),loginAgain);

                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }

    /**
     * 获取动态菜单2
     *
     * @return
     */
    private void getByTokenMenu2() {
        UserManager.getInstance_ADMIN().getByTokenMenu2().safeSubscribe(new LoadingSubject<MenuListResponse>(true, "正在获取动态菜单...") {
            @Override
            protected void _onNext(MenuListResponse menuListResponse) {
                if (menuListResponse.getCode() == 0) {
                    UserManager.getInstance().saveMenuList(menuListResponse.getData());
                    getReservoirList(false);
                }
            }

            @Override
            protected void _onError(String message) {

                ToastUtils.shortToast(message);
            }
        });
    }

    /**
     * 通过角色id获取相应菜单
     *
     * @param roleCode
     */
    private void getByTokenMenuByRole(String roleCode,boolean loginAgain) {
        UserManager.getInstance_ADMIN().getByTokenMenuByRole(roleCode).safeSubscribe(new LoadingSubject<MenuListResponse>(true, "正在获取动态菜单...") {
            @Override
            protected void _onNext(MenuListResponse menuListResponse) {
                if (menuListResponse.getCode() == 0) {
                    UserManager.getInstance().saveMenuList(menuListResponse.getData());
                    getReservoirList(loginAgain);
                }
            }

            @Override
            protected void _onError(String message) {

                ToastUtils.shortToast(message);
            }
        });
    }

    /**
     * 获取负责的水库列表
     *
     * @return
     */
    private void getReservoirList(boolean loginAgain) {
        UserManager.getInstance_ADMIN().getReservoirList().safeSubscribe(new LoadingSubject<ReservoirListResponse>(true,"正在获取水库列表...") {
            @Override
            protected void _onNext(ReservoirListResponse response) {
                if (response.getCode() == 0) {
                    UserManager.getInstance().saveReservoirList(response.getData());
                    if (response.getData() != null && response.getData().size() > 0) {
                        UserManager.getInstance().saveDefaultReservoir(response.getData().get(0));
                    }
                    if (loginAgain){
                        SPUtils.getInstance(Utils.getContext()).remove(LoginPresenter.prefence_menu);
                        JPushInterface.stopPush(Utils.getContext());
                        //综合监控标志为置空
                        SPUtils.getInstance(Utils.getContext()).putBoolean(CacheConsts.haslook,false);
//                        PgyUpdateManager.unregister();
                        AppManager.getInstance().finishAll();
                        TabMainFragmentFactory.getInstance().clearFragment();
                        ARouter.getInstance().build(AppRoutePath.appMain).navigation();

                    }else {
                        if (mView != null) {
                            mView.loginSuccess();
                        }
                    }

                }
            }

            @Override
            protected void _onError(String message) {
                ToastUtils.shortToast(message);

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

                            } else {
                                ToastUtils.shortToast("未能获取到菜单");

                            }
                        } catch (Exception e) {

                            e.printStackTrace();
                            ToastUtils.shortToast("未能获取到菜单");
                        }


                    } else {

                        ToastUtils.longToast(menuBean.getMsg());


                    }
                }
            }

            @Override
            protected void _onError(String message) {

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
            LogUtil.e(e.getMessage());
        } catch (ClassNotFoundException e1) {

        }
        return menuBean;
    }
}
