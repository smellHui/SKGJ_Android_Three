package com.tepia.main.view.maincommon.setting.contacts;

import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.base.utils.ToastUtils;
import com.tepia.main.model.user.AddressBookResponse;
import com.tepia.main.model.user.UserManager;

/**
 * @author         :      zhang xinhua
 * Version         :       1.0
 * 功能描述        :
 **/

public class ContactsPresenter extends BasePresenterImpl<ContactsContract.View> implements ContactsContract.Presenter{

    public void getAddressBook(String searchKey, String currentPage, String pageSize) {

        UserManager.getInstance_ADMIN().getAddressBook(searchKey,currentPage,pageSize).safeSubscribe(new LoadingSubject<AddressBookResponse>() {
            @Override
            protected void _onNext(AddressBookResponse addressBookResponse) {
                mView.getAddressBookSuccess(addressBookResponse.getData().getList());
            }

            @Override
            protected void _onError(String message) {
                ToastUtils.shortToast(message);
            }
        });
    }
}
