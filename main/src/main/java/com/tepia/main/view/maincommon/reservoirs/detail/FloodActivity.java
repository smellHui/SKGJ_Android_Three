package com.tepia.main.view.maincommon.reservoirs.detail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.main.R;
import com.tepia.main.model.reserviros.FloodBean;
import com.tepia.main.model.reserviros.SafeRunningBean;
import com.tepia.main.utils.EmptyLayoutUtil;
import com.tepia.main.view.maincommon.reservoirs.AdapterMainReservoirs;
import com.tepia.main.view.maincommon.reservoirs.MyReservoirsItemBean;
import com.tepia.main.view.maincommon.reservoirs.ReservoirsFragment;
import com.tepia.main.view.maincommon.reservoirs.detailadapter.AdapterFloodReservoirs;
import com.tepia.main.view.maincommon.reservoirs.mvpreservoir.ReserviorContract;
import com.tepia.main.view.maincommon.reservoirs.mvpreservoir.ReserviorPresent;

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
public class FloodActivity extends MVPBaseActivity<ReserviorContract.View,ReserviorPresent> implements  ReserviorContract.View<FloodBean> {

    private RecyclerView floodRec;
    private AdapterFloodReservoirs adapterFloodReservoirs;
    private List<FloodBean.DataBean> myReservoirsItemBeanList = new ArrayList<>();
    @Override
    public int getLayoutId() {
        return R.layout.activity_flood;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCenterTitle("防汛物资");
        showBack();
        floodRec = findViewById(R.id.floodRec);

        floodRec.setLayoutManager(new LinearLayoutManager(this));
        adapterFloodReservoirs = new AdapterFloodReservoirs(this,R.layout.fragment_reservoirs_flood_item,myReservoirsItemBeanList);
        floodRec.setAdapter(adapterFloodReservoirs);
        adapterFloodReservoirs.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent();
                intent.setClass(FloodActivity.this,FloodDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("floodid", myReservoirsItemBeanList.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        String reservoirId = getIntent().getStringExtra(ReservoirsFragment.RESERVOIRId);
        mPresenter.getMaterialByReservoir(reservoirId);
    }

    @Override
    public void initView() {


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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (myReservoirsItemBeanList != null) {
            myReservoirsItemBeanList.clear();
        }
    }

    @Override
    public void success(FloodBean data) {
        myReservoirsItemBeanList.clear();
        myReservoirsItemBeanList.addAll(data.getData());
        adapterFloodReservoirs.notifyDataSetChanged();
    }

    @Override
    public void failure(String msg) {
        adapterFloodReservoirs.setEmptyView(EmptyLayoutUtil.show(msg));
    }
}
