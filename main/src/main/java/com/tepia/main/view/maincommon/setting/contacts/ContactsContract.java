package com.tepia.main.view.maincommon.setting.contacts;

import android.content.Context;

import com.tepia.base.mvp.BasePresenter;
import com.tepia.base.mvp.BaseView;
import com.tepia.main.model.user.ContactBean;

import java.util.List;

/**
 * @author         :      zhang xinhua
 * Version         :       1.0
 * 功能描述        :
 **/

public class ContactsContract {
    interface View extends BaseView {

        void getAddressBookSuccess(List<ContactBean> list);
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
