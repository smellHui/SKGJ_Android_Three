package com.tepia.main.view.maincommon.reservoirs;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.BaseCommonFragment;
import com.tepia.main.R;
import com.tepia.main.view.maincommon.reservoirs.detail.JianjieOfReservoirsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :
 * Version         :       1.0
 * 功能描述        :  主页四 —— 水库页
 **/
@Route(path = AppRoutePath.app_main_fragment_reservoirs)
public class ReservoirsFragment extends BaseCommonFragment {

    private RecyclerView resviorRec;
    private AdapterMainReservoirs adapterMainReservoirs;
    private List<MyReservoirsItemBean> myReservoirsItemBeanList = new ArrayList<>();

    public ReservoirsFragment() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_reservoirs;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        setCenterTitle(getString(R.string.main_reservoirs));
        getRightTianqi().setVisibility(View.VISIBLE);

        resviorRec = findView(R.id.resviorRec);
        setResviorRec("水库简介", "RESERVOIRS DESCRIPTION", R.drawable.a_all);
        setResviorRec("水库视频", "RESERVOIRS VEDIO", R.drawable.a_weihu);
        setResviorRec("水库库容曲线", "CAPACITY CURVE", R.drawable.a_all);
        setResviorRec("水库配套设置", "RESERVOIRS SUPPORTING", R.drawable.a_weihu);
        setResviorRec("防汛物资", "FLOOD-FIGHTING MATERIALS", R.drawable.a_all);
        setResviorRec("调度运行方案", "DISPATCHING OPERATION PLAN", R.drawable.a_weihu);
        setResviorRec("水库安全管理应急预案", "CONTINGENCY PLAN", R.drawable.a_all);
        setResviorRec("水库安全运行管理情况", "ADMINISTRATIVE SITUATION", R.drawable.a_weihu);
        resviorRec.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterMainReservoirs = new AdapterMainReservoirs(getBaseActivity(), R.layout.fragment_reservoirs_item, myReservoirsItemBeanList);
        resviorRec.setAdapter(adapterMainReservoirs);
        adapterMainReservoirs.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent();
                if (position == 0) {
                    intent.setClass(getBaseActivity(), JianjieOfReservoirsActivity.class);
                }
                startActivity(intent);
            }
        });

    }

    private void setResviorRec(String title, String middle_title, int resourceImg) {
        MyReservoirsItemBean myReservoirsItemBean = new MyReservoirsItemBean();
        myReservoirsItemBean.setTitle(title);
        myReservoirsItemBean.setMiddle_title(middle_title);
        myReservoirsItemBean.setResourceImg(resourceImg);
        myReservoirsItemBeanList.add(myReservoirsItemBean);
    }

    @Override
    protected void initRequestData() {

    }
}
