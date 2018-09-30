package com.tepia.main.view.maincommon.setting.worknotification.addworknotification;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.main.R;


/**
 * @author         :      zhang xinhua
 * Version         :       1.0
 * 功能描述        :
 **/
@Route(path = AppRoutePath.app_add_work_notification)
public class AddWorkNotificationActivity extends MVPBaseActivity<AddWorkNotificationContract.View, AddWorkNotificationPresenter> implements AddWorkNotificationContract.View {

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_work_notification;
    }

    @Override
    public void initView() {
        setCenterTitle("新增通知");
        showBack();
    }

    @Override
    public void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {

    }
}
