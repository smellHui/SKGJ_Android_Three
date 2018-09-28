package com.tepia.main.view.maincommon.setting.contacts;


import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.loadmore.SimpleLoadMoreView;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.main.R;
import com.tepia.main.databinding.ActivityContactsBinding;
import com.tepia.main.model.user.AddressBookResponse;
import com.tepia.main.model.user.ContactBean;

import java.util.List;


/**
 * @author :      zhang xinhua
 * Version         :       1.0
 * 功能描述        :        通信录页面
 **/
@Route(path = AppRoutePath.app_contacts)
public class ContactsActivity extends MVPBaseActivity<ContactsContract.View, ContactsPresenter> implements ContactsContract.View {

    private ActivityContactsBinding mBinding;
    private AdapterContactsList adapterContactsList;
    private String searchKey = "";

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
        adapterContactsList = new AdapterContactsList(R.layout.lv_contact_list_item, null);
        mBinding.rvContact.setAdapter(adapterContactsList);
        adapterContactsList.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });

        mBinding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchKey = mBinding.etSearch.getText().toString().trim();
                mPresenter.getAddressBook(searchKey);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        adapterContactsList.openLoadAnimation();
        adapterContactsList.setEnableLoadMore(true);
        adapterContactsList.setLoadMoreView(new SimpleLoadMoreView());
        adapterContactsList.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mBinding.rvContact.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mPresenter.isCanLoadMore) {
                            mPresenter.getAddressBookMore(searchKey);

                        } else {
                            adapterContactsList.loadMoreEnd();
                        }
                    }
                }, 1000);
            }
        }, mBinding.rvContact);
    }

    @Override
    public void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {
        mPresenter.getAddressBook(searchKey);
    }

    @Override
    public void getAddressBookSuccess(List<ContactBean> list) {
        adapterContactsList.setNewData(list);
        adapterContactsList.loadMoreComplete();
    }

    @Override
    public void getAddressBookMoreSuccess(AddressBookResponse.DataBean dataBean) {
        int i = dataBean.getStartRow()-1;
        for (int j = 0; j < dataBean.getList().size(); j++) {
            if (!adapterContactsList.getData().contains(dataBean.getList().get(j))) {
                adapterContactsList.addData(i + j, dataBean.getList().get(j));
            } else {
                adapterContactsList.setData(i + j, dataBean.getList().get(j));
            }
        }
        adapterContactsList.loadMoreComplete();
    }
}
