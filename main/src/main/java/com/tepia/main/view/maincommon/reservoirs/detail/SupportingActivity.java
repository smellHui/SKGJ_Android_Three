package com.tepia.main.view.maincommon.reservoirs.detail;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.main.R;
import com.tepia.main.view.maincommon.reservoirs.MyReservoirsItemBean;
import com.tepia.main.view.maincommon.reservoirs.detailadapter.AdapterFloodReservoirs;
import com.tepia.main.view.maincommon.reservoirs.detailadapter.AdapterSupportingReservoirs;

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
public class SupportingActivity extends BaseActivity {

    private RecyclerView supportingRec;
    private AdapterSupportingReservoirs adapterSupportingReservoirs;
    private List<MyReservoirsItemBean> myReservoirsItemBeanList = new ArrayList<>();
    @Override
    public int getLayoutId() {
        return R.layout.activity_supporting;
    }

    @Override
    public void initView() {
        setCenterTitle("水库配套设施");
        showBack();
        supportingRec = findViewById(R.id.supportingRec);
        setResviorRec("雨量站", "RESERVOIRS DESCRIPTION", R.drawable.a_all);
        setResviorRec("水位站", "RESERVOIRS DESCRIPTION", R.drawable.a_all);
        setResviorRec("流量站", "RESERVOIRS DESCRIPTION", R.drawable.a_all);
        setResviorRec("水质站", "RESERVOIRS DESCRIPTION", R.drawable.a_all);
        setResviorRec("水位站", "RESERVOIRS DESCRIPTION", R.drawable.a_all);
        setResviorRec("其他设置", "RESERVOIRS DESCRIPTION", R.drawable.a_all);
        supportingRec.setLayoutManager(new GridLayoutManager(this,2));
        adapterSupportingReservoirs = new AdapterSupportingReservoirs(this,R.layout.fragment_reservoirs_supporting_item,myReservoirsItemBeanList);
        supportingRec.setAdapter(adapterSupportingReservoirs);
        adapterSupportingReservoirs.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
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
