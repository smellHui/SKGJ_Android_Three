package com.tepia.main.view.maincommon.setting.contacts;

import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.base.utils.Utils;
import com.tepia.main.R;
import com.tepia.main.model.reserviros.ReservirosManager;
import com.tepia.main.model.reserviros.VisitLogBean;
import com.tepia.main.model.user.ReserviorBookBean;
import com.tepia.main.model.user.ReserviorThreeAddressBook;
import com.tepia.main.model.user.UserManager;

/**
 * Created by      android studio
 *
 * @author :       ly
 * Date            :       2019-03-19
 * Time            :       下午4:29
 * Version         :       1.0
 * location        :       武汉研发中心
 * 功能描述         :
 **/
public class ContactsNewPresent extends BasePresenterImpl<ContactsContract.ViewNew> implements ContactsContract.PresenterNew {
    @Override
    public void listReservoirAddressBook(String searchParam, boolean isshowloading) {
        UserManager.getInstance_ADMIN().listReservoirAddressBook(searchParam)
                .subscribe(new LoadingSubject<ReserviorBookBean>(isshowloading, Utils.getContext().getString(R.string.data_loading)) {
                    @Override
                    protected void _onNext(ReserviorBookBean reserviorBookBean) {
                        if (reserviorBookBean != null && mView != null) {
                            if (reserviorBookBean.getCode() == 0) {
                                mView.success(reserviorBookBean);

                            }else{
                                mView.failure(reserviorBookBean.getMsg());
                            }
                        }
                    }

                    @Override
                    protected void _onError(String message) {
                        if(mView != null) {
                            mView.failure(message);
                        }

                    }
                });
    }

    @Override
    public void getThreeAddressBook(String searchParam, String currentPage, String pageSize, boolean isshowloading) {

    }
}
