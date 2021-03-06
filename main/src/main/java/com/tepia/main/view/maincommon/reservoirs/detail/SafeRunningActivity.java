package com.tepia.main.view.maincommon.reservoirs.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.base.utils.DoubleClickUtil;
import com.tepia.base.utils.NetUtil;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.utils.Utils;
import com.tepia.main.R;
import com.tepia.main.model.reserviros.SafeRunningBean;
import com.tepia.main.utils.EmptyLayoutUtil;
import com.tepia.main.view.maincommon.reservoirs.ReservoirsFragment;
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
        setCenterTitle("安全运行记录");
        showBack();
        saferunningRec = findViewById(R.id.saferunningRec);
        TextView nameTv = findViewById(R.id.nameTv);
        saferunningRec.setLayoutManager(new LinearLayoutManager(this));
        adapterSafeRunningReservoirs = new AdapterSafeRunningReservoirs(this,R.layout.fragment_reservoirs_saferunning_item,myReservoirsItemBeanList);
        saferunningRec.setAdapter( adapterSafeRunningReservoirs);
        adapterSafeRunningReservoirs.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                if (!NetUtil.isNetworkConnected(Utils.getContext())) {
                    ToastUtils.shortToast(R.string.no_network);
                    return;
                }
                if(DoubleClickUtil.isFastDoubleClick()){
                    return;
                }
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("saferunning",myReservoirsItemBeanList.get(position));
                intent.putExtras(bundle);
                //intent.putExtra("saferunningId",myReservoirsItemBeanList.get(position).getId());
                if (position == 0) {
                    intent.setClass(SafeRunningActivity.this, SafeRunningDetailActivity.class);
                    startActivity(intent);
                } else if (position == 1) {
                    intent.setClass(SafeRunningActivity.this, SafeRunningDetailActivity.class);
                    startActivity(intent);
                } else if (position == 2) {
                    intent.setClass(SafeRunningActivity.this, SafeRunningDetailActivity.class);
                    startActivity(intent);
                } else if (position == 3) {
                    intent.setClass(SafeRunningActivity.this, SafeRunningDetailActivity.class);
                    startActivity(intent);
                }
            }
        });
        String reservoirId = getIntent().getStringExtra(ReservoirsFragment.RESERVOIRId);
        String reservoirName = getIntent().getStringExtra(ReservoirsFragment.RESERVOIRNAME);
        nameTv.setText(reservoirName);
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
        if(data.getData() != null && data.getData().size() > 0){
            myReservoirsItemBeanList.addAll(data.getData());
            adapterSafeRunningReservoirs.notifyDataSetChanged();
        }else{
            adapterSafeRunningReservoirs.setEmptyView(EmptyLayoutUtil.show(getString(R.string.empty_tv)));
        }

    }

    @Override
    public void failure(String msg) {
        adapterSafeRunningReservoirs.setEmptyView(EmptyLayoutUtil.show(msg));
    }
}
