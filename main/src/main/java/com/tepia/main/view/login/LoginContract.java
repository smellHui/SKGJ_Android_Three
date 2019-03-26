package com.tepia.main.view.login;

import android.content.Context;

import com.tepia.base.mvp.BasePresenter;
import com.tepia.base.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class LoginContract {
    interface View extends BaseView {
        void loginSuccess();
    }

    interface  Presenter extends BasePresenter<View> {
        void login(Context mContext,String username, String password, String registId);
    }
}
