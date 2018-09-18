package com.tepia.main.view.maincommon.reservoirs.detail;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.main.R;
import com.tepia.main.view.maincommon.reservoirs.AdapterMainReservoirs;
import com.tepia.main.view.maincommon.reservoirs.MyReservoirsItemBean;
import com.tepia.main.view.maincommon.reservoirs.detailadapter.AdapterFloodReservoirs;

import java.util.ArrayList;
import java.util.List;

/**
  * Created by      Android studio
  *
  * @author :ly (from Center Of Wuhan)
  * Date    :2018-9-18
  * Version :1.0
  * 功能描述 :防汛物资
 **/
public class FloodActivity extends BaseActivity {

    private RecyclerView floodRec;
    private AdapterFloodReservoirs adapterFloodReservoirs;
    private List<MyReservoirsItemBean> myReservoirsItemBeanList = new ArrayList<>();
    @Override
    public int getLayoutId() {
        return R.layout.activity_flood;
    }

    @Override
    public void initView() {
        setCenterTitle("防汛物资");
        showBack();
        floodRec = findViewById(R.id.floodRec);
        setResviorRec("水库简介", "RESERVOIRS DESCRIPTION", R.drawable.a_all);
        setResviorRec("水库简介", "RESERVOIRS DESCRIPTION", R.drawable.a_all);
        setResviorRec("水库简介", "RESERVOIRS DESCRIPTION", R.drawable.a_all);
        setResviorRec("水库简介", "RESERVOIRS DESCRIPTION", R.drawable.a_all);
        setResviorRec("水库简介", "RESERVOIRS DESCRIPTION", R.drawable.a_all);
        setResviorRec("水库简介", "RESERVOIRS DESCRIPTION", R.drawable.a_all);
        setResviorRec("水库简介", "RESERVOIRS DESCRIPTION", R.drawable.a_all);
        setResviorRec("水库简介", "RESERVOIRS DESCRIPTION", R.drawable.a_all);
        setResviorRec("水库简介", "RESERVOIRS DESCRIPTION", R.drawable.a_all);
        setResviorRec("水库简介", "RESERVOIRS DESCRIPTION", R.drawable.a_all);
        setResviorRec("水库简介", "RESERVOIRS DESCRIPTION", R.drawable.a_all);
        floodRec.setLayoutManager(new LinearLayoutManager(this));
        adapterFloodReservoirs = new AdapterFloodReservoirs(this,R.layout.fragment_reservoirs_flood_item,myReservoirsItemBeanList);
        floodRec.setAdapter(adapterFloodReservoirs);
        adapterFloodReservoirs.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
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

    }

    private void setResviorRec(String title, String middle_title, int resourceImg) {
        MyReservoirsItemBean myReservoirsItemBean = new MyReservoirsItemBean();
        myReservoirsItemBean.setTitle(title);
        myReservoirsItemBean.setMiddle_title(middle_title);
        myReservoirsItemBean.setResourceImg(resourceImg);
        myReservoirsItemBeanList.add(myReservoirsItemBean);
    }
}
