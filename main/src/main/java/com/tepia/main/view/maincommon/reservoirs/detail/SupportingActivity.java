package com.tepia.main.view.maincommon.reservoirs.detail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.UserManager;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.main.R;
import com.tepia.main.model.reserviros.SupportingBean;
import com.tepia.main.utils.EmptyLayoutUtil;
import com.tepia.main.view.maincommon.reservoirs.MyReservoirsItemBean;
import com.tepia.main.view.maincommon.reservoirs.ReservoirsFragment;
import com.tepia.main.view.maincommon.reservoirs.detailadapter.AdapterFloodReservoirs;
import com.tepia.main.view.maincommon.reservoirs.detailadapter.AdapterSupportingReservoirs;
import com.tepia.main.view.maincommon.reservoirs.mvpreservoir.ReserviorContract;
import com.tepia.main.view.maincommon.reservoirs.mvpreservoir.ReserviorPresent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
  * Created by      Android studio
  *
  * @author         :       ly(from Center Of Wuhan)
  * Date            :       2018-9-18
  * Version         :       1.0
  * 功能描述         :       水库配套设施页面
 **/
public class SupportingActivity extends MVPBaseActivity<ReserviorContract.View,ReserviorPresent> implements  ReserviorContract.View<SupportingBean>{

    private RecyclerView supportingRec;
    private AdapterSupportingReservoirs adapterSupportingReservoirs;
    private List<SupportingBean.DataBean> myReservoirsItemBeanList = new ArrayList<>();
    @Override
    public int getLayoutId() {
        return R.layout.activity_supporting;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCenterTitle("水库配套设施");
        showBack();
        supportingRec = findViewById(R.id.supportingRec);
        supportingRec.setLayoutManager(new GridLayoutManager(this,2));
        adapterSupportingReservoirs = new AdapterSupportingReservoirs(this,R.layout.fragment_reservoirs_supporting_item,myReservoirsItemBeanList);
        supportingRec.setAdapter(adapterSupportingReservoirs);
        adapterSupportingReservoirs.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent();
                intent.setClass(SupportingActivity.this,SupportingDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("supportingid", myReservoirsItemBeanList.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        String reservoirId = getIntent().getStringExtra(ReservoirsFragment.RESERVOIRId);
        String reservoirName = getIntent().getStringExtra(ReservoirsFragment.RESERVOIRNAME);
        TextView nameTv = findViewById(R.id.nameTv);
        nameTv.setText(reservoirName);
        mPresenter.getDeviceByReservoir(reservoirId);
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
    public void success(SupportingBean data) {
        myReservoirsItemBeanList.clear();
        myReservoirsItemBeanList.addAll(data.getData());
        adapterSupportingReservoirs.notifyDataSetChanged();
    }

    @Override
    public void failure(String msg) {
        adapterSupportingReservoirs.setEmptyView(EmptyLayoutUtil.show(msg));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (myReservoirsItemBeanList != null) {
            myReservoirsItemBeanList.clear();
        }
    }
}
