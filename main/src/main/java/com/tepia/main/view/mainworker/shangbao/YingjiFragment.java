package com.tepia.main.view.mainworker.shangbao;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.tepia.base.mvp.MVPBaseFragment;
import com.tepia.main.R;

/**
 * Created by      Android studio
 *
 * @author :ly (from Center Of Wuhan)
 * Date    : 2018-9-20
 * Version :1.0
 * 功能描述 :应急
 **/
public class YingjiFragment extends MVPBaseFragment<ShangbaoContract.View, ShangbaoPresenter> implements View.OnClickListener {

    private RecyclerView shuiweiRec;

    public YingjiFragment() {
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
        shuiweiRec.setLayoutManager(new LinearLayoutManager(getBaseActivity()));
//        shuiweiRec.setAdapter();

        TextView sureSearchTv = findView(R.id.sureSearchTv);
        TextView shangbaoTv = findView(R.id.shangbaoTv);
        sureSearchTv.setOnClickListener(this);
        shangbaoTv.setOnClickListener(this);



    }

    @Override
    protected void initRequestData() {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.shangbaoTv) {
           Intent intent = new Intent();
           intent.setClass(getBaseActivity(),YingjiShangbaoActivity.class);
           startActivity(intent);
        } else if (v.getId() == R.id.sureSearchTv) {

        }
    }

}
