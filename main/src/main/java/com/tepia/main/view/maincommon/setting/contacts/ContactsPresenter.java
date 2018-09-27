package com.tepia.main.view.maincommon.setting.contacts;

import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.main.model.user.UserManager;

/**
 * @author         :      zhang xinhua
 * Version         :       1.0
 * 功能描述        :
 **/

public class ContactsPresenter extends BasePresenterImpl<ContactsContract.View> implements ContactsContract.Presenter{

    public void getAddressBook(String searchKey) {
        String currentPage = "1";
        String pageSize = "20";
        UserManager.getInstance_ADMIN().getAddressBook(searchKey);
    }
}
