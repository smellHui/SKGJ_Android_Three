package com.tepia.main.view.maintechnology.threekeypoint;

import android.content.Context;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tepia.base.mvp.BaseCommonFragment;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.NetUtil;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.utils.Utils;
import com.tepia.base.view.dialog.basedailog.ActionSheetDialog;
import com.tepia.base.view.dialog.basedailog.OnOpenItemClick;
import com.tepia.main.R;
import com.tepia.main.model.detai.ReservoirBean;
import com.tepia.main.model.jishu.threepoint.RainConditionResponse;
import com.tepia.main.model.jishu.threepoint.WaterLevelResponse;
import com.tepia.main.model.user.UserManager;
import com.tepia.main.utils.EmptyLayoutUtil;
import com.tepia.main.view.maintechnology.threekeypoint.adapter.MyRainListAdapter;
import com.tepia.main.view.maintechnology.threekeypoint.adapter.MyWaterListAdapter;
import com.tepia.main.view.maintechnology.yunwei.presenter.YunWeiJiShuContract;
import com.tepia.main.view.maintechnology.yunwei.presenter.YunWeiJiShuPresenter;

import java.util.ArrayList;
import java.util.List;

/**
  * Created by      Android studio
  *
  * @author :wwj (from Center Of Wuhan)
  * Date    :2018/10/9
  * Version :1.0
  * 功能描述 :三个重点监测预报
 **/

public class ThreePointListFragment extends BaseCommonFragment {

    private YunWeiJiShuPresenter mPresenter;
    private YunWeiJiShuPresenter waterPresenter;
    private TextView tvReservoirName;
    private TextView tvSelectReservoir;
    private RecyclerView rvWater;
    private RecyclerView rvRain;
    private ArrayList<ReservoirBean> localReservoirList;
    private int pageSize = 10;
    private int currentPage = 1;
    private List<WaterLevelResponse.DataBean.ListBean> waterDataList = new ArrayList<>();
    private List<RainConditionResponse.DataBean.ListBean> rainDataList = new ArrayList<>();
    private MyWaterListAdapter waterListAdapter;
    private MyRainListAdapter rainListAdapter;
    private SwipeRefreshLayout srl = null;
    private String reservoirId;
    private int reservoirPosition;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_three_point_tab;
    }

    @Override
    protected void initData() {
        localReservoirList = UserManager.getInstance().getLocalReservoirList();
        mPresenter = new YunWeiJiShuPresenter();
        waterPresenter = new YunWeiJiShuPresenter();
        mPresenter.attachView(new YunWeiJiShuContract.View<RainConditionResponse>() {
            @Override
            public void success(RainConditionResponse rainConditionResponse) {
                RainConditionResponse.DataBean dataBean = rainConditionResponse.getData();
                List<RainConditionResponse.DataBean.ListBean> data = dataBean.getList();
                if (data == null || data.size() == 0) {
//                    LogUtil.i("搜索为空");
                    rainDataList.clear();
                    rainListAdapter.setEmptyView(EmptyLayoutUtil.show(getString(R.string.empty_tv)));
                    rainListAdapter.notifyDataSetChanged();
                } else {
                    rainDataList.clear();
                    rainDataList.addAll(data);
                    rainListAdapter.notifyDataSetChanged();
                }
                srl.setRefreshing(false);
            }

            @Override
            public void failure(String msg) {
                rainListAdapter.setEmptyView(EmptyLayoutUtil.show(msg));
                ToastUtils.shortToast(msg);
                srl.setRefreshing(false);
            }

            @Override
            public Context getContext() {
                return null;
            }
        });
        waterPresenter.attachView(new YunWeiJiShuContract.View<WaterLevelResponse>() {
            @Override
            public void success(WaterLevelResponse waterLevelResponse) {
                WaterLevelResponse.DataBean dataBean = waterLevelResponse.getData();
                List<WaterLevelResponse.DataBean.ListBean> data = dataBean.getList();
                if (data == null || data.size() == 0) {
//                    LogUtil.i("搜索为空");
                    waterDataList.clear();
                    waterListAdapter.setEmptyView(EmptyLayoutUtil.show(getString(R.string.empty_tv)));
                    waterListAdapter.notifyDataSetChanged();
                } else {
                    waterDataList.clear();
                    waterDataList.addAll(data);
                    waterListAdapter.notifyDataSetChanged();
                }
                srl.setRefreshing(false);
            }

            @Override
            public void failure(String msg) {
                waterListAdapter.setEmptyView(EmptyLayoutUtil.show(msg));
                ToastUtils.shortToast(msg);
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
        srl = (SwipeRefreshLayout) findView(R.id.srl);
        srl.setOnRefreshListener(this::commonRequestDataFun);
        srl.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light);
        tvReservoirName = findView(R.id.tv_reservoir_name);
        tvSelectReservoir = findView(R.id.tv_select_reservoir);
        rvWater = findView(R.id.rv_water);
        rvRain = findView(R.id.rv_rain);
        //默认加载第一个
        initRequestResponse();
        initRecycleView();
        initClick();
    }

    private void commonRequestDataFun() {
        if (!NetUtil.isNetworkConnected(Utils.getContext())) {
            ToastUtils.shortToast(R.string.no_network);
        }else {
            if (mPresenter != null && waterPresenter != null) {
                mPresenter.listStPpthRByReservoir(reservoirId, "", "", String.valueOf(currentPage), String.valueOf(pageSize));
                waterPresenter.listStRsvrRRByReservoir(reservoirId, "", "", String.valueOf(currentPage), String.valueOf(pageSize),false);
            }
        }
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
            LogUtil.i("position:" + position);
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
//            mPopup.setAnchorView(v);
//            mPopup.show();
            showSelectReservoir();
        });
    }

    private void initRecycleView() {
//        for (int i = 0; i < 10; i++) {
//            WaterLevelResponse.DataBean.ListBean listBean = new WaterLevelResponse.DataBean.ListBean();
//            waterDataList.add(listBean);
//        }
//        for (int i = 0; i < 10; i++) {
//            RainConditionResponse.DataBean.ListBean listBean = new RainConditionResponse.DataBean.ListBean();
//            rainDataList.add(listBean);
//        }
        rvWater.setLayoutManager(new LinearLayoutManager(getActivity()));
        waterListAdapter = new MyWaterListAdapter(R.layout.threepoint_jishu_item_water, waterDataList);
        rvWater.setAdapter(waterListAdapter);
        rvRain.setLayoutManager(new LinearLayoutManager(getActivity()));
        rainListAdapter = new MyRainListAdapter(R.layout.threepoint_jishu_item_rain, rainDataList);
        rvRain.setAdapter(rainListAdapter);
    }

    private void initRequestResponse() {
        if (!NetUtil.isNetworkConnected(Utils.getContext())) {
            ToastUtils.shortToast(R.string.no_network);
        }else {
            if (localReservoirList != null && localReservoirList.size() > 0) {
                ReservoirBean defaultReservoir = UserManager.getInstance().getDefaultReservoir();
                reservoirId = defaultReservoir.getReservoirId();
                srl.setRefreshing(true);
                tvReservoirName.setText(defaultReservoir.getReservoir());
                mPresenter.listStPpthRByReservoir(reservoirId, "", "", String.valueOf(currentPage), String.valueOf(pageSize));
                waterPresenter.listStRsvrRRByReservoir(reservoirId, "", "", String.valueOf(currentPage), String.valueOf(pageSize),false);
            }
        }
    }

    @Override
    protected void initRequestData() {
    }

    private void showSelectReservoir() {
        if (localReservoirList != null) {
            String[] stringItems = new String[localReservoirList.size()];
            for (int i = 0; i < localReservoirList.size(); i++) {
                stringItems[i] = localReservoirList.get(i).getReservoir();
            }
            final ActionSheetDialog dialog = new ActionSheetDialog(getBaseActivity(), stringItems, null);
            dialog.title("请选择水库")
                    .titleTextSize_SP(14.5f)
                    .widthScale(0.8f)
                    .show();
            dialog.setOnOpenItemClickL(new OnOpenItemClick() {
                @Override
                public void onOpenItemClick(AdapterView<?> parent, View view, int position, long id) {
                    reservoirPosition = position;
//                    mBinding.tvReservoir.setText(selectedResrvoir.getReservoir());
//                    selectFinish(selectedYunWeiType, selectedResrvoir);
//                    mBinding.loHeader.tvReservoirName.setText(selectedResrvoir.getReservoir());
                    tvReservoirName.setText(stringItems[position]);
                    ReservoirBean selectedResrvoir = localReservoirList.get(position);
                    com.tepia.main.model.user.UserManager.getInstance().saveDefaultReservoir(selectedResrvoir);
                    dialog.dismiss();
                    srl.setRefreshing(true);
                    Handler handler = new Handler();
                    //半秒后执行runnable中的run方法commonRequestDatuaFn
                    handler.postDelayed(() -> commonRequestDataFun(), 500);
                }
            });
        }
    }

}
