package com.tepia.main.view.maincommon.setting.contacts;

import com.arialyy.frame.util.show.T;
import com.tepia.base.mvp.BasePresenter;
import com.tepia.base.mvp.BaseView;
import com.tepia.main.model.user.AddressBookResponse;
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

        void getAddressBookMoreSuccess(AddressBookResponse.DataBean list);
    }

    interface ViewNew<T> extends BaseView{
        void success(T data);
        void failure(String msg);
    }

    interface  Presenter extends BasePresenter<View> {
        
    }

    interface  PresenterNew extends BasePresenter<ViewNew> {
        void listReservoirAddressBook(String searchParam,boolean isshowloading);
        void getThreeAddressBook(String searchParam,String currentPage, String pageSize,boolean isshowloading);
    }
}
