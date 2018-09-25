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
import com.tepia.main.model.reserviros.SafeRunningBean;
import com.tepia.main.model.reserviros.SupportingBean;
import com.tepia.main.utils.EmptyLayoutUtil;
import com.tepia.main.view.maincommon.reservoirs.MyReservoirsItemBean;
import com.tepia.main.view.maincommon.reservoirs.ReservoirsFragment;
import com.tepia.main.view.maincommon.reservoirs.detailadapter.AdapterFloodReservoirs;
import com.tepia.main.view.maincommon.reservoirs.detailadapter.AdapterSafeRunningReservoirs;
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
  * 功能描述 :安全运行预案页面
 **/
public class SafeRunningActivity extends MVPBaseActivity<ReserviorContract.View,ReserviorPresent> implements  ReserviorContract.View<SafeRunningBean> {
    private RecyclerView saferunningRec;
    private AdapterSafeRunningReservoirs adapterSafeRunningReservoirs;
    private List<SafeRunningBean.DataBean> myReservoirsItemBeanList = new ArrayList<>();
    @Override
    public int getLayoutId() {
        return R.layout.activity_safe_running;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCenterTitle("安全鉴定记录");
        showBack();
        saferunningRec = findViewById(R.id.saferunningRec);

        saferunningRec.setLayoutManager(new LinearLayoutManager(this));
        adapterSafeRunningReservoirs = new AdapterSafeRunningReservoirs(this,R.layout.fragment_reservoirs_saferunning_item,myReservoirsItemBeanList);
        saferunningRec.setAdapter( adapterSafeRunningReservoirs);
        adapterSafeRunningReservoirs.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                Intent intent = new Intent();
                intent.putExtra("saferunningId",myReservoirsItemBeanList.get(position).getId());
                if (position == 0) {
                    intent.setClass(SafeRunningActivity.this, ItemSafeJiandingActivity.class);
                    startActivity(intent);
                } else if (position == 1) {
                    intent.setClass(SafeRunningActivity.this, ItemSafeJiandingActivity.class);
                    startActivity(intent);
                } else if (position == 2) {
                    intent.setClass(SafeRunningActivity.this, ItemSafeJiandingActivity.class);
                    startActivity(intent);
                } else if (position == 3) {
                    intent.setClass(SafeRunningActivity.this, ItemSafeJiandingActivity.class);
                    startActivity(intent);
                }
            }
        });
        String reservoirId = getIntent().getStringExtra(ReservoirsFragment.RESERVOIRId);
        mPresenter.getSafetyReportByReservoir(reservoirId);
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
    public void success(SafeRunningBean data) {
        myReservoirsItemBeanList.clear();
        myReservoirsItemBeanList.addAll(data.getData());
        adapterSafeRunningReservoirs.notifyDataSetChanged();
    }

    @Override
    public void failure(String msg) {
        adapterSafeRunningReservoirs.setEmptyView(EmptyLayoutUtil.show(msg));
    }
}
