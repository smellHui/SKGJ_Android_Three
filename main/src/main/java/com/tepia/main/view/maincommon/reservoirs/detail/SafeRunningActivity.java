package com.tepia.main.view.maincommon.reservoirs.detail;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.main.R;
import com.tepia.main.view.maincommon.reservoirs.MyReservoirsItemBean;
import com.tepia.main.view.maincommon.reservoirs.detailadapter.AdapterFloodReservoirs;
import com.tepia.main.view.maincommon.reservoirs.detailadapter.AdapterSafeRunningReservoirs;

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
public class SafeRunningActivity extends BaseActivity {
    private RecyclerView saferunningRec;
    private AdapterSafeRunningReservoirs adapterSafeRunningReservoirs;
    private List<MyReservoirsItemBean> myReservoirsItemBeanList = new ArrayList<>();
    @Override
    public int getLayoutId() {
        return R.layout.activity_safe_running;
    }

    @Override
    public void initView() {
        setCenterTitle("安全鉴定记录");
        showBack();
        saferunningRec = findViewById(R.id.saferunningRec);
        setResviorRec("安全鉴定", "RESERVOIRS DESCRIPTION", R.drawable.a_all);
        setResviorRec("除险加固", "RESERVOIRS DESCRIPTION", R.drawable.a_all);
        setResviorRec("蓄水验收", "RESERVOIRS DESCRIPTION", R.drawable.a_all);
        setResviorRec("竣工验收", "RESERVOIRS DESCRIPTION", R.drawable.a_all);

        saferunningRec.setLayoutManager(new LinearLayoutManager(this));
        adapterSafeRunningReservoirs = new AdapterSafeRunningReservoirs(this,R.layout.fragment_reservoirs_saferunning_item,myReservoirsItemBeanList);
        saferunningRec.setAdapter( adapterSafeRunningReservoirs);
        adapterSafeRunningReservoirs.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
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
