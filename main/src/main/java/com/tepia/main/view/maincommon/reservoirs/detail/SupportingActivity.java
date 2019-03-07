package com.tepia.main.view.maincommon.reservoirs.detail;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.UserManager;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.arialyy.frame.util.show.T;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.tepia.base.AppRoutePath;
import com.tepia.base.http.BaseResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.utils.Utils;
import com.tepia.main.R;
import com.tepia.main.model.reserviros.ReservirosManager;
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
    private  String reservoirId;
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
        TextView addTv = findViewById(R.id.addTv);

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

        adapterSupportingReservoirs.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                new AlertDialog.Builder(SupportingActivity.this)
                        .setMessage("是否确定删除该设施")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
//                                mPresenter.deleteImage(imageInfoBean, false, true, "图片删除中...");
                                delete(position);

                            }
                        }).show();

                return false;
            }
        });
        reservoirId = getIntent().getStringExtra(ReservoirsFragment.RESERVOIRId);
        String reservoirName = getIntent().getStringExtra(ReservoirsFragment.RESERVOIRNAME);
        TextView nameTv = findViewById(R.id.nameTv);
        nameTv.setText(reservoirName);

        addTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(AppRoutePath.app_support_change)
                        .withString(ReservoirsFragment.RESERVOIRId,reservoirId)
                        .navigation();
            }
        });
    }

    private void delete(int position){
        ReservirosManager.getInstance().deleteReservoirDevice(myReservoirsItemBeanList.get(position).getId())
                .subscribe(new LoadingSubject<BaseResponse>(true, Utils.getContext().getString(R.string.data_loading)) {
                    @Override
                    protected void _onNext(BaseResponse baseResponse) {
                        if (baseResponse != null) {
                            if (baseResponse.getCode() == 0) {
                                ToastUtils.shortToast("删除成功");
                                myReservoirsItemBeanList.remove(position);
                                adapterSupportingReservoirs.notifyDataSetChanged();

                            }else{
                                ToastUtils.shortToast("删除失败");

                            }
                        }
                    }

                    @Override
                    protected void _onError(String message) {
                        ToastUtils.shortToast(message);

                    }
                });
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

        mPresenter.getDeviceByReservoir(reservoirId);

    }



    @Override
    public void success(SupportingBean data) {
        myReservoirsItemBeanList.clear();
//        myReservoirsItemBeanList.addAll(data.getData());
//        adapterSupportingReservoirs.notifyDataSetChanged();

        if(data.getData() != null && data.getData().size() > 0){
            myReservoirsItemBeanList.addAll(data.getData());
            adapterSupportingReservoirs.notifyDataSetChanged();
        }else{
            adapterSupportingReservoirs.setEmptyView(EmptyLayoutUtil.show(getString(R.string.empty_tv)));
        }
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
