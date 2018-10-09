package com.tepia.main.view.maincommon.setting.worknotification.addworknotification;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.main.R;
import com.tepia.main.databinding.ActivitySelectReservorsBinding;
import com.tepia.main.model.detai.ReservoirBean;
import com.tepia.main.model.user.UserManager;
import com.tepia.main.view.main.work.task.taskdetail.AdapterTaskItemList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2018-10-09
 * Time            :       8:54
 * Version         :       1.0
 * 功能描述        :        多选水库
 **/
@Route(path = AppRoutePath.app_select_reservor)
public class SelectReservorsActivity extends BaseActivity {
    private ArrayList<ReservoirBean> list;
    private ActivitySelectReservorsBinding mBinding;
    private AdapterReservorList adapterReservorList;

    public static List<ReservoirBean> selectReservors;

    @Override
    public int getLayoutId() {
        return R.layout.activity_select_reservors;
    }

    @Override
    public void initView() {
        setCenterTitle("选择通知水库");
        showBack();
        mBinding = DataBindingUtil.bind(mRootView);
        getRithtTv().setVisibility(View.VISIBLE);
//        getRithtTv().setTextColor(Color.parseColor("#ffffffff"));
        getRithtTv().setText("确定");
        initListView();
    }

    private void initListView() {

        list = UserManager.getInstance().getLocalReservoirList();
        mBinding.rvReservorList.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        adapterReservorList = new AdapterReservorList(R.layout.lv_item_select_reservors_view, null);
        mBinding.rvReservorList.setAdapter(adapterReservorList);
        adapterReservorList.setNewData(list);
        if (selectReservors != null && selectReservors.size()!= 0){
            adapterReservorList.setSelectResrvoirList(selectReservors);
        }
        adapterReservorList.notifyDataSetChangedNew();
    }

    @Override
    public void initData() {


    }

    @Override
    protected void initListener() {
        getRithtTv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectReservors = adapterReservorList.getSelectResrvoirList();
                finish();
            }
        });
        mBinding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (adapterReservorList != null){
                    adapterReservorList.flterResrvoir(mBinding.etSearch.getText().toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        adapterReservorList.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (adapterReservorList.getSelectResrvoirList().contains(adapterReservorList.getData().get(position))) {
                    adapterReservorList.getSelectResrvoirList().remove(adapterReservorList.getData().get(position));
                } else {
                    adapterReservorList.getSelectResrvoirList().add(adapterReservorList.getData().get(position));
                }
                selectReservors = adapterReservorList.getSelectResrvoirList();
                adapterReservorList.notifyDataSetChangedNew();
            }
        });
    }

    @Override
    protected void initRequestData() {

    }
}
