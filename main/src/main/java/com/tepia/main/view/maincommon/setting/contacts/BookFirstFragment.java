package com.tepia.main.view.maincommon.setting.contacts;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tepia.base.mvp.BaseCommonFragment;
import com.tepia.base.mvp.MVPBaseFragment;
import com.tepia.base.utils.NetUtil;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.utils.Utils;
import com.tepia.main.ConfigConsts;
import com.tepia.main.R;
import com.tepia.main.databinding.FragmentBookFirstBinding;
import com.tepia.main.model.user.ReserviorBookBean;
import com.tepia.main.utils.EmptyLayoutUtil;
import com.tepia.main.view.maincommon.setting.adapter.PatrolStaticListOfUserNameAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookFirstFragment extends MVPBaseFragment<ContactsContract.ViewNew,ContactsNewPresent> implements ContactsContract.ViewNew {

    FragmentBookFirstBinding binding;
    private int pageSize = 50;
    private int currentPage = 1;



    private PatrolStaticListOfUserNameAdapter patrolListAdapter;
    private List<ReserviorBookBean.DataBean> dataList = new ArrayList<>();
    private String searchKey = "";


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_book_first;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        binding = DataBindingUtil.bind(view);

        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchKey = binding.etSearch.getText().toString().trim();
                search(false);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        initRecycleView();
        search(true);
    }

    @Override
    protected void initRequestData() {

    }

    /**
     * 初始化recycleview
     */
    private void initRecycleView() {
        binding.rvContact.setLayoutManager(new LinearLayoutManager(getContext()));
        patrolListAdapter = new PatrolStaticListOfUserNameAdapter(getContext(), R.layout.river_log_staticlist_item, dataList);
        binding.rvContact.setAdapter(patrolListAdapter);


    }

    private void search(boolean isshowloadiing) {

        if (!NetUtil.isNetworkConnected(Utils.getContext())) {
            ToastUtils.shortToast(R.string.no_network);
            return;
        }

        mPresenter.listReservoirAddressBook(searchKey,isshowloadiing);
    }

    @Override
    public void success(Object object) {
        ReserviorBookBean riverPatrolUsernameBean = (ReserviorBookBean) object;
        List<ReserviorBookBean.DataBean> data = riverPatrolUsernameBean.getData();

        if (data == null || data.size() == 0) {
            dataList.clear();
            patrolListAdapter.notifyDataSetChanged();
            patrolListAdapter.setEmptyView(EmptyLayoutUtil.show(getString(R.string.empty_tv)));
        } else {
            dataList.clear();
            //首次加载
            dataList.addAll(data);
            patrolListAdapter.notifyDataSetChanged();
            patrolListAdapter.loadMoreComplete();

        }
    }

    @Override
    public void failure(String msg) {
        patrolListAdapter.setEmptyView(EmptyLayoutUtil.show(msg));

    }
}
