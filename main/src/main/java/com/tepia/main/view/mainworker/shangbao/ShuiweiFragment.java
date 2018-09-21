package com.tepia.main.view.mainworker.shangbao;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tepia.base.mvp.BaseCommonFragment;
import com.tepia.base.mvp.MVPBaseFragment;
import com.tepia.main.R;
import com.tepia.main.view.maincommon.reservoirs.MyReservoirsItemBean;
import com.tepia.main.view.mainworker.shangbao.adapter.AdapterShuiweiReservoirs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by      Android studio
 *
 * @author :ly (from Center Of Wuhan)
 * Date    : 2018-9-20
 * Version :1.0
 * 功能描述 :水位
 **/
public class ShuiweiFragment extends MVPBaseFragment<ShangbaoContract.View, ShangbaoPresenter> implements View.OnClickListener {

    private RecyclerView shuiweiRec;
    private AdapterShuiweiReservoirs adapterShuiweiReservoirs;
    private List<MyReservoirsItemBean> myReservoirsItemBeanList = new ArrayList<>();


    public ShuiweiFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shuiwei;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {

        shuiweiRec = findView(R.id.shuiweiRec);

        setResviorRec("水库简介", "RESERVOIRS DESCRIPTION", R.drawable.jianjie1);
        setResviorRec("水库视频", "RESERVOIRS VEDIO", R.drawable.jianjie2);
        setResviorRec("水库库容曲线", "CAPACITY CURVE", R.drawable.jianjie3);
        setResviorRec("水库配套设施", "RESERVOIRS SUPPORTING", R.drawable.jianjie4);
        setResviorRec("防汛物资", "FLOOD-FIGHTING MATERIALS", R.drawable.jianjie5);
        setResviorRec("调度运行方案", "DISPATCHING OPERATION PLAN", R.drawable.jianjie6);
        setResviorRec("水库安全管理应急预案", "CONTINGENCY PLAN", R.drawable.jianjie7);
        setResviorRec("水库安全运行管理情况", "ADMINISTRATIVE SITUATION", R.drawable.jianjie8);
        setResviorRec("到访水库日志", "RESERVOIRS SUPPORTING", R.drawable.jianjie8);

        shuiweiRec.setLayoutManager(new LinearLayoutManager(getBaseActivity()));
        adapterShuiweiReservoirs = new AdapterShuiweiReservoirs(getBaseActivity(),R.layout.fragment_shuiwei_head_layout,myReservoirsItemBeanList);
        shuiweiRec.setAdapter(adapterShuiweiReservoirs);

        TextView sureSearchTv = findView(R.id.sureSearchTv);
        TextView shangbaoTv = findView(R.id.shangbaoTv);
        sureSearchTv.setOnClickListener(this);
        shangbaoTv.setOnClickListener(this);


        initDialog();

    }

    @Override
    protected void initRequestData() {

    }

    private Dialog dialog_show;
    private TextView selectReserviorTv;
    private TextView selectShuiweiEv;

    private void initDialog() {
        LayoutInflater inflater = (LayoutInflater) getBaseActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final ViewGroup nullparent = null;
        View layout = inflater.inflate(R.layout.fragment_shangbao_dailog, nullparent);
        selectReserviorTv = layout.findViewById(R.id.selectReserviorTv);
        selectShuiweiEv = layout.findViewById(R.id.selectShuiweiEv);
        Button cancelBtn = layout.findViewById(R.id.cancelBtn);
        Button suerBtn = layout.findViewById(R.id.suerBtn);

        dialog_show = new Dialog(getBaseActivity(), R.style.Transparent);
        dialog_show.setCanceledOnTouchOutside(true);
        dialog_show.setContentView(layout);
        WindowManager windowManager = getBaseActivity().getWindowManager();
        DisplayMetrics metric = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metric);
        // 屏幕宽度（像素）
        int width = metric.widthPixels * 4 / 5;
//        int height = metric.heightPixels / 3;
        WindowManager.LayoutParams lp = dialog_show.getWindow().getAttributes();
        lp.width = width;
//        lp.height = height;
        dialog_show.getWindow().setAttributes(lp);

        /*dialog_show.setOnCancelListener(new DialogInterface.OnCancelListener() {

            @Override
            public void onCancel(DialogInterface dialog) {
                *//*if (translateAnimation != null)
                    translateAnimation.cancel();*//*
            }
        });*/
        suerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog_show.dismiss();
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
    public void onClick(View v) {
        if (v.getId() == R.id.shangbaoTv) {
            dialog_show.show();

        } else if (v.getId() == R.id.sureSearchTv) {

        }
    }

}
