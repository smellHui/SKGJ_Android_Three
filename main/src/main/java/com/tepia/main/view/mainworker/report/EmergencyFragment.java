package com.tepia.main.view.mainworker.report;


import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.tepia.base.mvp.MVPBaseFragment;
import com.tepia.main.R;
import com.tepia.main.view.maincommon.reservoirs.MyReservoirsItemBean;
import com.tepia.main.view.mainworker.report.adapter.AdapterEmergency;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by      Android studio
 *
 * @author :ly (from Center Of Wuhan)
 * Date    : 2018-9-20
 * Version :1.0
 * 功能描述 :应急
 **/
public class EmergencyFragment extends MVPBaseFragment<ReportContract.View, ReportPresenter> implements View.OnClickListener {

    private RecyclerView yingjiRec;
    private AdapterEmergency adapterYingji;
    private List<MyReservoirsItemBean> myReservoirsItemBeanList = new ArrayList<>();

    public EmergencyFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_report_emergency;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {

        yingjiRec = findView(R.id.yingjiRec);
        yingjiRec.setLayoutManager(new LinearLayoutManager(getBaseActivity()));

        setResviorRec("水库简介", "RESERVOIRS DESCRIPTION", R.drawable.jianjie1);
        setResviorRec("水库视频", "RESERVOIRS VEDIO", R.drawable.jianjie2);
        setResviorRec("水库库容曲线", "CAPACITY CURVE", R.drawable.jianjie3);
        setResviorRec("水库配套设施", "RESERVOIRS SUPPORTING", R.drawable.jianjie4);
        setResviorRec("防汛物资", "FLOOD-FIGHTING MATERIALS", R.drawable.jianjie5);
        setResviorRec("调度运行方案", "DISPATCHING OPERATION PLAN", R.drawable.jianjie6);
        setResviorRec("水库安全管理应急预案", "CONTINGENCY PLAN", R.drawable.jianjie7);
        setResviorRec("水库安全运行管理情况", "ADMINISTRATIVE SITUATION", R.drawable.jianjie8);
        setResviorRec("到访水库日志", "RESERVOIRS SUPPORTING", R.drawable.jianjie8);

        yingjiRec.setLayoutManager(new LinearLayoutManager(getBaseActivity()));
        adapterYingji = new AdapterEmergency(getBaseActivity(),R.layout.fragment_shuiwei_yingji_head_layout,myReservoirsItemBeanList);
        yingjiRec.setAdapter(adapterYingji);

        TextView sureSearchTv = findView(R.id.sureSearchTv);
        TextView shangbaoTv = findView(R.id.shangbaoTv);
        sureSearchTv.setOnClickListener(this);
        shangbaoTv.setOnClickListener(this);



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

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.shangbaoTv) {
           Intent intent = new Intent();
           intent.setClass(getBaseActivity(),EmergencyDetailActivity.class);
           startActivity(intent);
        } else if (v.getId() == R.id.sureSearchTv) {

        }
    }

}
