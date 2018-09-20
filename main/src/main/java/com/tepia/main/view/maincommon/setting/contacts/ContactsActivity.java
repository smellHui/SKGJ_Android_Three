package com.tepia.main.view.maincommon.setting.contacts;


import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.main.R;
import com.tepia.main.databinding.ActivityContactsBinding;
import com.tepia.main.view.main.question.problemlist.AdapterBizProblem;
import com.tepia.voice.xunfei.DemoBean;

import java.util.ArrayList;


/**
 * @author         :      zhang xinhua
 * Version         :       1.0
 * 功能描述        :        通信录页面
 **/
@Route(path = AppRoutePath.app_contacts)
public class ContactsActivity extends MVPBaseActivity<ContactsContract.View, ContactsPresenter> implements ContactsContract.View {

    private ActivityContactsBinding mBinding;

    @Override
    public int getLayoutId() {
        return R.layout.activity_contacts;
    }

    @Override
    public void initView() {
        mBinding = DataBindingUtil.bind(mRootView);
        setCenterTitle("通讯录");
        showBack();
        mBinding.rvContact.setLayoutManager(new LinearLayoutManager(getContext()));
        AdapterContactsList adapterContactsList = new AdapterContactsList(R.layout.lv_contact_list_item,null);
        mBinding.rvContact.setAdapter(adapterContactsList);
        ArrayList<DemoBean> contactlist = new ArrayList<>();
        contactlist.add(new DemoBean());
        contactlist.add(new DemoBean());
        contactlist.add(new DemoBean());
        contactlist.add(new DemoBean());
        adapterContactsList.setNewData(contactlist);
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
