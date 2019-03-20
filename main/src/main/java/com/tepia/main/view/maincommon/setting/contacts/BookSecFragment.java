package com.tepia.main.view.maincommon.setting.contacts;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.loadmore.SimpleLoadMoreView;
import com.tepia.base.mvp.BaseCommonFragment;
import com.tepia.base.mvp.MVPBaseFragment;
import com.tepia.base.utils.DoubleClickUtil;
import com.tepia.base.utils.Utils;
import com.tepia.main.R;
import com.tepia.main.databinding.FragmentBookSecBinding;
import com.tepia.main.model.user.AddressBookResponse;
import com.tepia.main.model.user.ContactBean;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookSecFragment extends MVPBaseFragment<ContactsContract.View, ContactsPresenter> implements ContactsContract.View {

    FragmentBookSecBinding mBinding;
    private AdapterContactsList adapterContactsList;
    private String searchKey = "";

    @Override
    public int getLayoutId() {
        return R.layout.fragment_book_sec;
    }

    @Override
    public void initView(View view) {
        mBinding = DataBindingUtil.bind(view);
        mBinding.secRvContact.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterContactsList = new AdapterContactsList(R.layout.lv_contact_list_item, null);
        mBinding.secRvContact.setAdapter(adapterContactsList);
        adapterContactsList.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });

        mBinding.secEtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchKey = mBinding.secEtSearch.getText().toString().trim();
                mPresenter.getAddressBook(searchKey,false);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        adapterContactsList.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (DoubleClickUtil.isFastDoubleClick()){
                    return;
                }
                if (!TextUtils.isEmpty(adapterContactsList.getData().get(position).getMobile())) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    Uri data = Uri.parse("tel:" + adapterContactsList.getData().get(position).getMobile());
                    intent.setData(data);
                    startActivity(intent);
                }
            }
        });

        adapterContactsList.openLoadAnimation();
        adapterContactsList.setEnableLoadMore(true);
        adapterContactsList.setLoadMoreView(new SimpleLoadMoreView());
        adapterContactsList.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mBinding.secRvContact.postDelayed(new Runnable() {
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
        }, mBinding.secRvContact);
        mPresenter.getAddressBook(searchKey,true);

    }

    @Override
    public void initData() {

    }


    @Override
    protected void initRequestData() {
    }

    @Override
    public void getAddressBookSuccess(List<ContactBean> list) {
        adapterContactsList.setNewData(list);
        if (list == null || list.size() == 0) {
            adapterContactsList.getData().clear();
            adapterContactsList.notifyDataSetChanged();
            View view = LayoutInflater.from(Utils.getContext()).inflate(R.layout.view_empty_list_view, null);
            adapterContactsList.setEmptyView(view);
        }
        adapterContactsList.loadMoreComplete();
    }

    @Override
    public void getAddressBookMoreSuccess(AddressBookResponse.DataBean dataBean) {
        int i = dataBean.getStartRow() - 1;
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
