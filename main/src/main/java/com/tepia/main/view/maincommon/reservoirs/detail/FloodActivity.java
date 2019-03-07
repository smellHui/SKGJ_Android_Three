package com.tepia.main.view.maincommon.reservoirs.detail;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.tepia.base.AppRoutePath;
import com.tepia.base.http.BaseResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.base.utils.AppManager;
import com.tepia.base.utils.DoubleClickUtil;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.utils.Utils;
import com.tepia.main.R;
import com.tepia.main.model.reserviros.FloodBean;
import com.tepia.main.model.reserviros.ReservirosManager;
import com.tepia.main.model.reserviros.SafeRunningBean;
import com.tepia.main.model.reserviros.SupportingBean;
import com.tepia.main.model.user.UserManager;
import com.tepia.main.utils.EmptyLayoutUtil;
import com.tepia.main.view.TabMainFragmentFactory;
import com.tepia.main.view.login.LoginActivity;
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
    private TextView addTv;
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
//                Intent intent = new Intent();
//                intent.setClass(FloodActivity.this,FloodDetailActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("floodid", myReservoirsItemBeanList.get(position));
//                intent.putExtras(bundle);
//                startActivity(intent);
                String reservoirId = getIntent().getStringExtra(ReservoirsFragment.RESERVOIRId);
                ARouter.getInstance().build(AppRoutePath.app_flood_detail)
                        .withString("floodid",new Gson().toJson(myReservoirsItemBeanList.get(position)))
                        .withString(ReservoirsFragment.RESERVOIRId,reservoirId)
                        .navigation();
            }
        });
        adapterFloodReservoirs.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("确定要删除该种防汛物资吗？");
                builder.setCancelable(true);
                builder.setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ReservirosManager.getInstance().deleteReservoirMaterial(adapterFloodReservoirs.getData().get(position).getId())
                                .subscribe(new LoadingSubject<BaseResponse>(true, Utils.getContext().getString(R.string.data_loading)) {

                                    @Override
                                    protected void _onNext(BaseResponse response) {
                                        String reservoirId = getIntent().getStringExtra(ReservoirsFragment.RESERVOIRId);
                                        mPresenter.getMaterialByReservoir(reservoirId);
                                    }

                                    @Override
                                    protected void _onError(String message) {
                                        ToastUtils.shortToast(message);
                                    }
                                });
                    }
                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create().show();
                return false;
            }
        });

        String reservoirName = getIntent().getStringExtra(ReservoirsFragment.RESERVOIRNAME);
        TextView nameTv = findViewById(R.id.nameTv);
        nameTv.setText(reservoirName);

    }

    @Override
    protected void onResume() {
        super.onResume();
        String reservoirId = getIntent().getStringExtra(ReservoirsFragment.RESERVOIRId);
        mPresenter.getMaterialByReservoir(reservoirId);
    }

    @Override
    public void initView() {

        addTv = findViewById(R.id.addTv);

    }

    @Override
    public void initData() {

    }

    @Override
    protected void initListener() {
        addTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DoubleClickUtil.isFastDoubleClick()){
                    return;
                }
                String reservoirId = getIntent().getStringExtra(ReservoirsFragment.RESERVOIRId);
                ARouter.getInstance().build(AppRoutePath.app_flood_add_or_edit)
                        .withString("TYPE","add")
                        .withString(ReservoirsFragment.RESERVOIRId,reservoirId)
                        .navigation();
            }
        });
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
        if(data.getData() != null && data.getData().size() > 0){
            myReservoirsItemBeanList.addAll(data.getData());
            adapterFloodReservoirs.notifyDataSetChanged();
        }else{
            adapterFloodReservoirs.setEmptyView(EmptyLayoutUtil.show(getString(R.string.empty_tv)));
        }
    }

    @Override
    public void failure(String msg) {
        adapterFloodReservoirs.setEmptyView(EmptyLayoutUtil.show(msg));
    }
}
