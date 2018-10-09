package com.tepia.main.view.maincommon.setting.worknotification.addworknotification;


import android.databinding.DataBindingUtil;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.main.R;
import com.tepia.main.databinding.ActivityAddWorkNotificationBinding;
import com.tepia.main.databinding.ActivityContactsBinding;

import java.util.ArrayList;


/**
 * @author         :      zhang xinhua
 * Version         :       1.0
 * 功能描述        :
 **/
@Route(path = AppRoutePath.app_add_work_notification)
public class AddWorkNotificationActivity extends MVPBaseActivity<AddWorkNotificationContract.View, AddWorkNotificationPresenter> implements AddWorkNotificationContract.View {

    private ActivityAddWorkNotificationBinding mBinding;

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_work_notification;
    }

    @Override
    public void initView() {
        setCenterTitle("新增通知");
        showBack();
        mBinding = DataBindingUtil.bind(mRootView);
    }

    @Override
    public void initData() {

    }

    @Override
    protected void initListener() {
        mBinding.tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String reservoirIds = "66fb3d579d084daf8a7d35d9d9612213,6942292cad144bds97fa6c31f96ee684";
                String noticeTitle = mBinding.etTitle.getText().toString();
                String noticeContent = mBinding.etContent.getText().toString();
                ArrayList<String> files = new ArrayList<>();
                mPresenter.addWorkNotice(reservoirIds,noticeTitle,noticeContent,files);
            }
        });
        mBinding.loSelectReservoir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ARouter.getInstance().build(AppRoutePath.app_select_reservor).navigation();
            }
        });
    }

    @Override
    protected void initRequestData() {

    }

    @Override
    public void addWorkNoticeSuccess() {
        finish();
    }
}
