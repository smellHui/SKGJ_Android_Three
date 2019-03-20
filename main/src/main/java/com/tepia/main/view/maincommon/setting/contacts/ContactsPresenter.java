package com.tepia.main.view.maincommon.setting.contacts;

import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.base.utils.NetUtil;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.utils.Utils;
import com.tepia.main.R;
import com.tepia.main.model.user.AddressBookResponse;
import com.tepia.main.model.user.UserManager;

/**
 * @author :      zhang xinhua
 * Version         :       1.0
 * 功能描述        :
 **/

public class ContactsPresenter extends BasePresenterImpl<ContactsContract.View> implements ContactsContract.Presenter {
    int currentPage = 1;
    String pageSize = "15";
    public boolean isCanLoadMore = true;

    public void getAddressBook(String searchKey,boolean isloading) {
        if (!NetUtil.isNetworkConnected(Utils.getContext())) {
            ToastUtils.shortToast(R.string.no_network);
            return;
        }
        currentPage = 1;
        UserManager.getInstance_ADMIN().listThreeAddressBook(searchKey, currentPage + "", pageSize).safeSubscribe(new LoadingSubject<AddressBookResponse>(isloading, Utils.getContext().getString(R.string.data_loading)) {
            @Override
            protected void _onNext(AddressBookResponse addressBookResponse) {
                currentPage = addressBookResponse.getData().getPageNum();
                if (addressBookResponse.getData().getPageNum() == addressBookResponse.getData().getPages()) {
                    isCanLoadMore = false;
                } else {
                    isCanLoadMore = true;
                }
                mView.getAddressBookSuccess(addressBookResponse.getData().getList());
            }

            @Override
            protected void _onError(String message) {
                ToastUtils.shortToast(message);
            }
        });
    }

    public void getAddressBookMore(String searchKey) {
        if (!isCanLoadMore) {
            return;
        }
        UserManager.getInstance_ADMIN().listThreeAddressBook(searchKey, currentPage + 1 + "", pageSize).safeSubscribe(new LoadingSubject<AddressBookResponse>() {
            @Override
            protected void _onNext(AddressBookResponse addressBookResponse) {
                currentPage = addressBookResponse.getData().getPageNum();
                if (addressBookResponse.getData().getPageNum() == addressBookResponse.getData().getPages()) {
                    isCanLoadMore = false;
                } else {
                    isCanLoadMore = true;
                }
                mView.getAddressBookMoreSuccess(addressBookResponse.getData());
            }

            @Override
            protected void _onError(String message) {
                ToastUtils.shortToast(message);
            }
        });
    }
}
