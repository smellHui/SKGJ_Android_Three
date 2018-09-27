package com.tepia.main.view.maintechnology.threekeypoint;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tepia.base.mvp.BaseCommonFragment;
import com.tepia.main.R;
import com.tepia.main.model.detai.ReservoirBean;
import com.tepia.main.model.reserviros.OperationPlanBean;
import com.tepia.main.model.user.UserManager;
import com.tepia.main.utils.EmptyLayoutUtil;
import com.tepia.main.view.maincommon.reservoirs.detail.OperationPlanActivity;
import com.tepia.main.view.maincommon.reservoirs.mvpreservoir.ReserviorContract;
import com.tepia.main.view.maincommon.reservoirs.mvpreservoir.ReserviorPresent;
import com.tepia.main.view.maintechnology.threekeypoint.adapter.MyTabListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by      Intellij IDEA
 *
 * @author :       wwj
 * Date    :       2018-09-27
 * Time    :       10:12
 * Version :       1.0
 * Company :       北京太比雅科技(武汉研发中心)
 **/
public class ThreePointTabFragment extends BaseCommonFragment{

    private TextView tvReservoirName;
    private TextView tvSelectReservoir;
    private SwipeRefreshLayout srl = null;
    private RecyclerView rvList;
    private ArrayList<ReservoirBean> localReservoirList;
    private String reservoirId;
    private List<OperationPlanBean.DataBean> dataList = new ArrayList<>();
    private MyTabListAdapter listAdapter;
    private int fragmentPosition;
    private ReserviorPresent mPresent;
    private int reservoirPosition;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_three_point_02_tab;
    }

    @Override
    protected void initData() {
        localReservoirList = UserManager.getInstance().getLocalReservoirList();
        fragmentPosition = getArguments().getInt("position");
        mPresent = new ReserviorPresent();
        mPresent.attachView(new ReserviorContract.View<OperationPlanBean>() {
            @Override
            public void success(OperationPlanBean operationPlanBean) {
                OperationPlanBean.DataBean data = operationPlanBean.getData();
                if (data==null){
                    dataList.clear();
                    listAdapter.setEmptyView(EmptyLayoutUtil.show(getString(R.string.empty_tv)));
                    listAdapter.notifyDataSetChanged();
                }else {
                    dataList.clear();
                    dataList.add(data);
                    listAdapter.notifyDataSetChanged();
                }
                srl.setRefreshing(false);
            }

            @Override
            public void failure(String msg) {
                listAdapter.setEmptyView(EmptyLayoutUtil.show(msg));
                srl.setRefreshing(false);
            }

            @Override
            public Context getContext() {
                return null;
            }
        });
    }

    @Override
    protected void initView(View view) {
        tvReservoirName = findView(R.id.tv_reservoir_name);
        tvSelectReservoir = findView(R.id.tv_select_reservoir);
        srl = (SwipeRefreshLayout) findView(R.id.srl);
        srl.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light);
        srl.setOnRefreshListener(this::commonRequestDataFun);
        rvList = findView(R.id.rv_list);
        initRecycleView();
        initClick();
    }

    private ListPopupWindow mPopup;

    private void initClick() {
        String[] reservoirs = {};
        if (localReservoirList != null && localReservoirList.size() > 0) {
            reservoirs = new String[localReservoirList.size()];
            for (int i = 0; i < localReservoirList.size(); i++) {
                reservoirs[i] = localReservoirList.get(i).getReservoir();
            }
        }
        mPopup = new ListPopupWindow(getActivity());
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.dropdown_stytle, reservoirs);
        mPopup.setAdapter(adapter);
//        mPopup.setWidth(LayoutParams.WRAP_CONTENT);
//        mPopup.setHeight(LayoutParams.WRAP_CONTENT);
        mPopup.setModal(true);
        mPopup.setOnItemClickListener((parent, view, position, id) -> {
//            LogUtil.i("position:" + position);
            mPopup.dismiss();
            reservoirPosition = position;
            reservoirId = localReservoirList.get(position).getReservoirId();
            tvReservoirName.setText(localReservoirList.get(position).getReservoir());
            srl.setRefreshing(true);
            Handler handler = new Handler();
            //半秒后执行runnable中的run方法
            handler.postDelayed(this::commonRequestDataFun, 500);
        });
        tvSelectReservoir.setOnClickListener(v -> {
//            setAnchorView : 设置下拉列表的参照控件。下拉列表在显示时将展现在参照控件的下方，注意：如果不设置参照控件就直接调用show函数，系统不知道要把下拉列表在何处展示，只能是异常退出了。
            mPopup.setAnchorView(v);
            mPopup.show();
        });
    }

    private void commonRequestDataFun() {
        if (fragmentPosition==1){
            //调度运用界面
            mPresent.getFloodControlByReservoir(reservoirId);
        }else if (fragmentPosition==2){
            //应急预案界面
            mPresent.getEmergencyByReservoir(reservoirId);
        }
    }

    private void initRecycleView() {
        rvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        listAdapter = new MyTabListAdapter(R.layout.common_office_layout, dataList);
        rvList.setAdapter(listAdapter);
        listAdapter.setOnPreviewTvClickListener((v, adapterPosition, item) -> {
            String defaultReservoirId = UserManager.getInstance().getDefaultReservoir().getReservoirId();
            if (!defaultReservoirId.equals(reservoirId)){
                UserManager.getInstance().saveDefaultReservoir(localReservoirList.get(reservoirPosition));
            }
            Intent intent = new Intent();
            intent.setClass(getContext(), OperationPlanActivity.class);
            intent.putExtra("select",OperationPlanActivity.value_preview);
            intent.putExtra(OperationPlanActivity.PREVIEW_PATH,item.getFilePath());
            startActivity(intent);
        });
    }

    boolean isFirstLoad;

    @Override
    protected void initRequestData() {
        if (!isFirstLoad){
            if (localReservoirList != null && localReservoirList.size() > 0) {
                reservoirId = localReservoirList.get(0).getReservoir();
                srl.setRefreshing(true);
                tvReservoirName.setText(reservoirId);
                commonRequestDataFun();
                isFirstLoad = true;
            }
        }
    }
}
