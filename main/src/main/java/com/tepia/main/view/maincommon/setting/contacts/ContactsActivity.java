package com.tepia.main.view.maincommon.setting.contacts;


import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.main.R;
import com.tepia.main.databinding.ActivityContactsBinding;
import com.tepia.main.model.user.ContactBean;
import com.tepia.main.view.main.question.problemlist.AdapterBizProblem;
import com.tepia.voice.xunfei.DemoBean;

import java.util.ArrayList;
import java.util.List;


/**
 * @author         :      zhang xinhua
 * Version         :       1.0
 * 功能描述        :        通信录页面
 **/
@Route(path = AppRoutePath.app_contacts)
public class ContactsActivity extends MVPBaseActivity<ContactsContract.View, ContactsPresenter> implements ContactsContract.View {

    private ActivityContactsBinding mBinding;
    private AdapterContactsList adapterContactsList;

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
        adapterContactsList = new AdapterContactsList(R.layout.lv_contact_list_item,null);
        mBinding.rvContact.setAdapter(adapterContactsList);
        adapterContactsList.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {
        String currentPage = "1";
        String pageSize = "20";
        mPresenter.getAddressBook("",currentPage,pageSize);
    }

    @Override
    public void getAddressBookSuccess(List<ContactBean> list) {
        adapterContactsList.setNewData(list);
    }
}
