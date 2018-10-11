package com.tepia.main.view.mainworker.homepage;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.google.gson.Gson;
import com.just.library.AgentWeb;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.MVPBaseFragment;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.view.dialog.basedailog.ActionSheetDialog;
import com.tepia.base.view.dialog.basedailog.OnOpenItemClick;
import com.tepia.main.R;
import com.tepia.main.databinding.FragmentHomeXunjianBinding;
import com.tepia.main.model.detai.ReservoirBean;
import com.tepia.main.model.user.UserManager;
import com.tepia.main.model.user.homepageinfo.HomeGetReservoirInfoBean;
import com.tepia.main.view.main.detail.LineChartEntity;
import com.tepia.main.view.maincommon.reservoirs.detail.FloodActivity;
import com.tepia.main.view.maincommon.reservoirs.detail.FloodDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :      zhang xinhua
 * Version         :       1.0
 * 功能描述        :        主页一 首页 巡查责任人
 **/

@Route(path = AppRoutePath.app_main_fragment_home_xuncha)
public class HomeXunChaFragment extends MVPBaseFragment<HomeXunChaContract.View, HomeXunChaPresenter> implements HomeXunChaContract.View,OnChartGestureListener, OnChartValueSelectedListener {
    private TextView tvReservoirName;
    private com.github.mikephil.charting.charts.LineChart lcReservoirCapacity;
    FragmentHomeXunjianBinding mBinding;
    private AdapterWorker adapterWorker;
    private AdapterFloodControlMaterialList adapterFloodControlMaterialList;
    private ReservoirBean selectedResrvoir;
    private HomeGetReservoirInfoBean homeGetReservoirInfoBean;
    private LineChartEntity lineChartEntity;
    private NestedScrollView nestedsv;


    public HomeXunChaFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_xunjian;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        lcReservoirCapacity = (LineChart) view.findViewWithTag("lc_reservoir_capacity");
        tvReservoirName = view.findViewById(R.id.tv_reservoir_name);
        mBinding = DataBindingUtil.bind(view);
        setCenterTitle(getString(R.string.main_home));
        getRightTianqi().setVisibility(View.VISIBLE);


        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBinding.rvWorker.setLayoutManager(layoutManager);
        adapterWorker = new AdapterWorker(R.layout.lv_tab_main_worker_item, null);
        mBinding.rvWorker.setAdapter(adapterWorker);
        mBinding.rvWorker.setNestedScrollingEnabled(false);


        mBinding.rvFloodControlMaterialList.setNestedScrollingEnabled(false);
        mBinding.rvFloodControlMaterialList.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterFloodControlMaterialList = new AdapterFloodControlMaterialList(R.layout.lv_flood_control_material_item, null);
        mBinding.rvFloodControlMaterialList.setAdapter(adapterFloodControlMaterialList);


        initFrequencyStatistics();
        initLineChart();


        initListener();


    }


    private void initListener() {
        mBinding.loHeader.switchTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSelectReservoir();
            }
        });
        mBinding.tvShowMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (homeGetReservoirInfoBean.getMaterial() != null && homeGetReservoirInfoBean.getMaterial().size() > 4) {
                    if (adapterFloodControlMaterialList.getData().size() == 4) {
                        adapterFloodControlMaterialList.setNewData(homeGetReservoirInfoBean.getMaterial());
                        mBinding.tvShowMore.setText("收起");
                    } else {
                        adapterFloodControlMaterialList.setNewData(homeGetReservoirInfoBean.getMaterial().subList(0, 3));
                        mBinding.tvShowMore.setText("查看更多");
                    }
                } else {
                    ToastUtils.shortToast("没有更多");
                }
            }
        });

        adapterWorker.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                if (!TextUtils.isEmpty(adapterWorker.getData().get(position).getMobile())) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    Uri data = Uri.parse("tel:" + adapterWorker.getData().get(position).getMobile());
                    intent.setData(data);
                    startActivity(intent);
                } else {
                    ToastUtils.shortToast("暂无手机号码");
                }
                return false;
            }
        });
        adapterFloodControlMaterialList.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ARouter.getInstance().build(AppRoutePath.app_flood_detail)
                        .withString("floodid", new Gson().toJson(adapterFloodControlMaterialList.getData().get(position)))
                        .navigation();
//                Intent intent = new Intent();
//                intent.setClass(getBaseActivity(),FloodDetailActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("floodid",adapterFloodControlMaterialList.getData().get(position));
//                intent.putExtras(bundle);
//                startActivity(intent);
            }
        });

        mBinding.wrflContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mBinding.wrflContainer.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mBinding.wrflContainer.setRefreshing(false);
                    }
                }, 500);
                mPresenter.getAppHomeGetReservoirInfo(UserManager.getInstance().getDefaultReservoir().getReservoirId());
            }
        });
    }

    /**
     * 初始化 巡查频率 巡查统计
     */
    private void initFrequencyStatistics() {
        mBinding.loXunjianFrequency.tvTitle.setText("巡查频率");

        mBinding.loXunjianStatisticsy.tvTitle.setText("巡检统计");
        mBinding.loXunjianStatisticsy.tvTjName.setText("本月巡查统计");
        mBinding.loXunjianStatisticsy.rtcpXunchaCount.setTextColor(Color.parseColor("#596470"));
        mBinding.loXunjianStatisticsy.rtcpXunchaCount.setTextSize(18);
        mBinding.loXunjianStatisticsy.rtcpXunchaCount.setMax(100);
        mBinding.loXunjianStatisticsy.rtcpXunchaCount.setProgress(100);
        mBinding.loXunjianStatisticsy.rtcpXunchaCount.getCircularProgressBar().setPrimaryColor(Color.parseColor("#21a1ff"));
        mBinding.loXunjianStatisticsy.rtcpXunchaCount.setText("0");

        mBinding.loXunjianStatisticsy.rtcpQuestionCount.setTextColor(Color.parseColor("#809dd2"));
        mBinding.loXunjianStatisticsy.rtcpQuestionCount.setTextSize(14);
        mBinding.loXunjianStatisticsy.rtcpQuestionCount.setMax(100);
        mBinding.loXunjianStatisticsy.rtcpQuestionCount.setProgress(100);
        mBinding.loXunjianStatisticsy.rtcpQuestionCount.getCircularProgressBar().setPrimaryColor(Color.parseColor("#809dd2"));
        mBinding.loXunjianStatisticsy.rtcpQuestionCount.getCircularProgressBar().setBackgroundColor(Color.parseColor("#e9f1fc"));
        mBinding.loXunjianStatisticsy.rtcpQuestionCount.setText("0");

        mBinding.loXunjianStatisticsy.rtcpDealedCount.setTextColor(Color.parseColor("#4acaa0"));
        mBinding.loXunjianStatisticsy.rtcpDealedCount.setTextSize(14);
        mBinding.loXunjianStatisticsy.rtcpDealedCount.setMax(100);
        mBinding.loXunjianStatisticsy.rtcpDealedCount.setProgress(75);
        mBinding.loXunjianStatisticsy.rtcpDealedCount.getCircularProgressBar().setPrimaryColor(Color.parseColor("#4acaa0"));
        mBinding.loXunjianStatisticsy.rtcpDealedCount.getCircularProgressBar().setBackgroundColor(Color.parseColor("#ddf9f0"));
        mBinding.loXunjianStatisticsy.rtcpDealedCount.setText("0");

        mBinding.loXunjianStatisticsy.rtcpNotDealCount.setTextColor(Color.parseColor("#ff8773"));
        mBinding.loXunjianStatisticsy.rtcpNotDealCount.setTextSize(14);
        mBinding.loXunjianStatisticsy.rtcpNotDealCount.setMax(100);
        mBinding.loXunjianStatisticsy.rtcpNotDealCount.setProgress(75);
        mBinding.loXunjianStatisticsy.rtcpNotDealCount.getCircularProgressBar().setPrimaryColor(Color.parseColor("#ff8773"));
        mBinding.loXunjianStatisticsy.rtcpNotDealCount.getCircularProgressBar().setBackgroundColor(Color.parseColor("#fae8e5"));
        mBinding.loXunjianStatisticsy.rtcpNotDealCount.setText("0");


        mBinding.loBaojieFrequency.tvTitle.setText("保洁频率");

        mBinding.loBaojieStatisticsy.tvTitle.setText("保洁统计");
        mBinding.loBaojieStatisticsy.tvTjName.setText("本月保洁统计");
        mBinding.loBaojieStatisticsy.rtcpXunchaCount.setTextColor(Color.parseColor("#596470"));
        mBinding.loBaojieStatisticsy.rtcpXunchaCount.setTextSize(18);
        mBinding.loBaojieStatisticsy.rtcpXunchaCount.setMax(100);
        mBinding.loBaojieStatisticsy.rtcpXunchaCount.setProgress(100);
        mBinding.loBaojieStatisticsy.rtcpXunchaCount.getCircularProgressBar().setPrimaryColor(Color.parseColor("#21a1ff"));
        mBinding.loBaojieStatisticsy.rtcpXunchaCount.setText("0");

        mBinding.loBaojieStatisticsy.rtcpQuestionCount.setTextColor(Color.parseColor("#809dd2"));
        mBinding.loBaojieStatisticsy.rtcpQuestionCount.setTextSize(14);
        mBinding.loBaojieStatisticsy.rtcpQuestionCount.setMax(100);
        mBinding.loBaojieStatisticsy.rtcpQuestionCount.setProgress(100);
        mBinding.loBaojieStatisticsy.rtcpQuestionCount.getCircularProgressBar().setPrimaryColor(Color.parseColor("#809dd2"));
        mBinding.loBaojieStatisticsy.rtcpQuestionCount.getCircularProgressBar().setBackgroundColor(Color.parseColor("#e9f1fc"));
        mBinding.loBaojieStatisticsy.rtcpQuestionCount.setText("0");

        mBinding.loBaojieStatisticsy.rtcpDealedCount.setTextColor(Color.parseColor("#4acaa0"));
        mBinding.loBaojieStatisticsy.rtcpDealedCount.setTextSize(14);
        mBinding.loBaojieStatisticsy.rtcpDealedCount.setMax(100);
        mBinding.loBaojieStatisticsy.rtcpDealedCount.setProgress(75);
        mBinding.loBaojieStatisticsy.rtcpDealedCount.getCircularProgressBar().setPrimaryColor(Color.parseColor("#4acaa0"));
        mBinding.loBaojieStatisticsy.rtcpDealedCount.getCircularProgressBar().setBackgroundColor(Color.parseColor("#ddf9f0"));
        mBinding.loBaojieStatisticsy.rtcpDealedCount.setText("0");

        mBinding.loBaojieStatisticsy.rtcpNotDealCount.setTextColor(Color.parseColor("#ff8773"));
        mBinding.loBaojieStatisticsy.rtcpNotDealCount.setTextSize(14);
        mBinding.loBaojieStatisticsy.rtcpNotDealCount.setMax(100);
        mBinding.loBaojieStatisticsy.rtcpNotDealCount.setProgress(75);
        mBinding.loBaojieStatisticsy.rtcpNotDealCount.getCircularProgressBar().setPrimaryColor(Color.parseColor("#ff8773"));
        mBinding.loBaojieStatisticsy.rtcpNotDealCount.getCircularProgressBar().setBackgroundColor(Color.parseColor("#fae8e5"));
        mBinding.loBaojieStatisticsy.rtcpNotDealCount.setText("0");

        mBinding.loWeihuFrequency.tvTitle.setText("维修养护频率");

        mBinding.loWeihuStatistics.tvTitle.setText("维修养护统计");
        mBinding.loWeihuStatistics.tvTjName.setText("本月维护统计");
        mBinding.loWeihuStatistics.rtcpXunchaCount.setTextColor(Color.parseColor("#596470"));
        mBinding.loWeihuStatistics.rtcpXunchaCount.setTextSize(18);
        mBinding.loWeihuStatistics.rtcpXunchaCount.setMax(100);
        mBinding.loWeihuStatistics.rtcpXunchaCount.setProgress(100);
        mBinding.loWeihuStatistics.rtcpXunchaCount.getCircularProgressBar().setPrimaryColor(Color.parseColor("#21a1ff"));
        mBinding.loWeihuStatistics.rtcpXunchaCount.setText("0");

        mBinding.loWeihuStatistics.rtcpQuestionCount.setTextColor(Color.parseColor("#809dd2"));
        mBinding.loWeihuStatistics.rtcpQuestionCount.setTextSize(14);
        mBinding.loWeihuStatistics.rtcpQuestionCount.setMax(100);
        mBinding.loWeihuStatistics.rtcpQuestionCount.setProgress(100);
        mBinding.loWeihuStatistics.rtcpQuestionCount.getCircularProgressBar().setPrimaryColor(Color.parseColor("#809dd2"));
        mBinding.loWeihuStatistics.rtcpQuestionCount.getCircularProgressBar().setBackgroundColor(Color.parseColor("#e9f1fc"));
        mBinding.loWeihuStatistics.rtcpQuestionCount.setText("0");

        mBinding.loWeihuStatistics.rtcpDealedCount.setTextColor(Color.parseColor("#4acaa0"));
        mBinding.loWeihuStatistics.rtcpDealedCount.setTextSize(14);
        mBinding.loWeihuStatistics.rtcpDealedCount.setMax(100);
        mBinding.loWeihuStatistics.rtcpDealedCount.setProgress(75);
        mBinding.loWeihuStatistics.rtcpDealedCount.getCircularProgressBar().setPrimaryColor(Color.parseColor("#4acaa0"));
        mBinding.loWeihuStatistics.rtcpDealedCount.getCircularProgressBar().setBackgroundColor(Color.parseColor("#ddf9f0"));
        mBinding.loWeihuStatistics.rtcpDealedCount.setText("0");

        mBinding.loWeihuStatistics.rtcpNotDealCount.setTextColor(Color.parseColor("#ff8773"));
        mBinding.loWeihuStatistics.rtcpNotDealCount.setTextSize(14);
        mBinding.loWeihuStatistics.rtcpNotDealCount.setMax(100);
        mBinding.loWeihuStatistics.rtcpNotDealCount.setProgress(75);
        mBinding.loWeihuStatistics.rtcpNotDealCount.getCircularProgressBar().setPrimaryColor(Color.parseColor("#ff8773"));
        mBinding.loWeihuStatistics.rtcpNotDealCount.getCircularProgressBar().setBackgroundColor(Color.parseColor("#fae8e5"));
        mBinding.loWeihuStatistics.rtcpNotDealCount.setText("0");

        mBinding.loWeihuStatistics.loStatisticsy.setVisibility(View.GONE);
        mBinding.loBaojieStatisticsy.loStatisticsy.setVisibility(View.GONE);
        mBinding.loWeihuFrequency.loFrequency.setVisibility(View.GONE);
        mBinding.loBaojieFrequency.loFrequency.setVisibility(View.GONE);

    }


    private void initLineChart() {

//        ChartUtils.initChart(lcReservoirCapacity);
        lcReservoirCapacity.setOnTouchListener((view, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                //允许ScrollView截断点击事件，ScrollView可滑动
                mBinding.nestedsv.requestDisallowInterceptTouchEvent(false);
            } else {
                //不允许ScrollView截断点击事件，点击事件由子View处理
                mBinding.nestedsv.requestDisallowInterceptTouchEvent(true);
            }
//
            return false;
        });
        lineChartEntity = new LineChartEntity(lcReservoirCapacity, "库容(万m³)");
        lcReservoirCapacity.setOnChartGestureListener(this);
        lcReservoirCapacity.setOnChartValueSelectedListener(this);
        if (lcReservoirCapacity != null) {
            lcReservoirCapacity.clear();
            //重设所有缩放和拖动，使图表完全适合它的边界（完全缩小）。
            lcReservoirCapacity.fitScreen();
        }
    }

    private void showSelectReservoir() {
        ArrayList<ReservoirBean> reservoirBeans = UserManager.getInstance().getLocalReservoirList();
        if (reservoirBeans != null) {
            String[] stringItems = new String[reservoirBeans.size()];
            for (int i = 0; i < reservoirBeans.size(); i++) {
                stringItems[i] = reservoirBeans.get(i).getReservoir();
            }
            final ActionSheetDialog dialog = new ActionSheetDialog(getBaseActivity(), stringItems, null);
            dialog.title("请选择水库")
                    .titleTextSize_SP(14.5f)
                    .widthScale(0.8f)
                    .show();
            dialog.setOnOpenItemClickL(new OnOpenItemClick() {
                @Override
                public void onOpenItemClick(AdapterView<?> parent, View view, int position, long id) {
                    selectedResrvoir = reservoirBeans.get(position);
//                    mBinding.tvReservoir.setText(selectedResrvoir.getReservoir());
//                    selectFinish(selectedYunWeiType, selectedResrvoir);
                    mBinding.loHeader.tvReservoirName.setText(selectedResrvoir.getReservoir());
                    UserManager.getInstance().saveDefaultReservoir(selectedResrvoir);
                    mPresenter.getAppHomeGetReservoirInfo(selectedResrvoir.getReservoirId());
                    dialog.dismiss();
                }
            });
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initRequestData() {

        if (UserManager.getInstance().getDefaultReservoir() != null) {
            tvReservoirName.setText(UserManager.getInstance().getDefaultReservoir().getReservoir());
            mPresenter.getAppHomeGetReservoirInfo(UserManager.getInstance().getDefaultReservoir().getReservoirId());
        }
    }

    private List<Entry> getData(List<HomeGetReservoirInfoBean.StorageCapacityBean> storageCapacity) {
        List<Entry> values = new ArrayList<>();
        for (HomeGetReservoirInfoBean.StorageCapacityBean bean : storageCapacity) {
            values.add(new Entry(bean.getWaterLevel(), bean.getStorageCapacity()));
        }
        return values;
    }

    @Override
    public void getHommeInfoSuccess(HomeGetReservoirInfoBean data) {
        refreshViewWaterLevelStorageCapacity(data.getReservoirWaterLevel());


        homeGetReservoirInfoBean = data;
        adapterWorker.setNewData(data.getPersonDuty());
        if (data.getPersonDuty() != null && data.getPersonDuty().size() != 0){
            mBinding.tvWorkerNum.setText("（共 "+data.getPersonDuty().size()+" 人）");
        }else {
            mBinding.tvWorkerNum.setText("");
        }

        if (data.getMaterial() != null && data.getMaterial().size() > 4) {
            adapterFloodControlMaterialList.setNewData(data.getMaterial().subList(0, 3));
            mBinding.tvShowMore.setVisibility(View.VISIBLE);
        } else {
            adapterFloodControlMaterialList.setNewData(data.getMaterial());
            mBinding.tvShowMore.setVisibility(View.GONE);
        }
        if (data.getExecuteFrequency() != null) {
            if (data.getExecuteFrequency().getInspection() != null) {
                mBinding.loXunjianFrequency.loFrequency.setVisibility(View.VISIBLE);
                mBinding.loXunjianFrequency.tvDaily.setText(data.getExecuteFrequency().getInspection().getNoFlood() + "次/月");
                mBinding.loXunjianFrequency.tvFlood.setText(data.getExecuteFrequency().getInspection().getFlood() + "次/月");
            } else {
                mBinding.loXunjianFrequency.loFrequency.setVisibility(View.GONE);
            }
            if (data.getExecuteFrequency().getClean() != null) {
                mBinding.loBaojieFrequency.loFrequency.setVisibility(View.VISIBLE);
                mBinding.loBaojieFrequency.tvDaily.setText(data.getExecuteFrequency().getClean().getNoFlood() + "次/月");
                mBinding.loBaojieFrequency.tvFlood.setText(data.getExecuteFrequency().getClean().getFlood() + "次/月");
            } else {
                mBinding.loBaojieFrequency.loFrequency.setVisibility(View.GONE);
            }
            if (data.getExecuteFrequency().getMaintain() != null) {
                mBinding.loWeihuFrequency.loFrequency.setVisibility(View.VISIBLE);
                mBinding.loWeihuFrequency.tvDaily.setText(data.getExecuteFrequency().getMaintain().getNoFlood() + "次/月");
                mBinding.loWeihuFrequency.tvFlood.setText(data.getExecuteFrequency().getMaintain().getFlood() + "次/月");
            } else {
                mBinding.loWeihuFrequency.loFrequency.setVisibility(View.GONE);
            }
        }

        if (data.getOAMStatistics() != null) {
            if (data.getOAMStatistics().getInspection() != null) {
                mBinding.loXunjianStatisticsy.loStatisticsy.setVisibility(View.VISIBLE);
                mBinding.loXunjianStatisticsy.rtcpDealedCount.setMax(data.getOAMStatistics().getInspection().getProblemCount());
                mBinding.loXunjianStatisticsy.rtcpNotDealCount.setMax(data.getOAMStatistics().getInspection().getProblemCount());
                mBinding.loXunjianStatisticsy.rtcpDealedCount.setProgress(data.getOAMStatistics().getInspection().getProcessedProblem());
                mBinding.loXunjianStatisticsy.rtcpNotDealCount.setProgress(data.getOAMStatistics().getInspection().getNotProcessedProblem());
                mBinding.loXunjianStatisticsy.rtcpXunchaCount.setText(data.getOAMStatistics().getInspection().getWorkOrderCount() + "");
                mBinding.loXunjianStatisticsy.rtcpDealedCount.setText(data.getOAMStatistics().getInspection().getProcessedProblem() + "");
                mBinding.loXunjianStatisticsy.rtcpNotDealCount.setText(data.getOAMStatistics().getInspection().getNotProcessedProblem() + "");
                mBinding.loXunjianStatisticsy.rtcpQuestionCount.setText(data.getOAMStatistics().getInspection().getProblemCount() + "");
            } else {
                mBinding.loXunjianStatisticsy.loStatisticsy.setVisibility(View.GONE);
            }
            if (data.getOAMStatistics().getClean() != null) {
                mBinding.loBaojieStatisticsy.loStatisticsy.setVisibility(View.VISIBLE);
                mBinding.loBaojieStatisticsy.rtcpDealedCount.setMax(data.getOAMStatistics().getClean().getProblemCount());
                mBinding.loBaojieStatisticsy.rtcpNotDealCount.setMax(data.getOAMStatistics().getClean().getProblemCount());
                mBinding.loBaojieStatisticsy.rtcpDealedCount.setProgress(data.getOAMStatistics().getClean().getProcessedProblem());
                mBinding.loBaojieStatisticsy.rtcpNotDealCount.setProgress(data.getOAMStatistics().getClean().getNotProcessedProblem());
                mBinding.loBaojieStatisticsy.rtcpXunchaCount.setText(data.getOAMStatistics().getClean().getWorkOrderCount() + "");
                mBinding.loBaojieStatisticsy.rtcpDealedCount.setText(data.getOAMStatistics().getClean().getProcessedProblem() + "");
                mBinding.loBaojieStatisticsy.rtcpNotDealCount.setText(data.getOAMStatistics().getClean().getNotProcessedProblem() + "");
                mBinding.loBaojieStatisticsy.rtcpQuestionCount.setText(data.getOAMStatistics().getClean().getProblemCount() + "");
            } else {
                mBinding.loBaojieStatisticsy.loStatisticsy.setVisibility(View.GONE);
            }
            if (data.getOAMStatistics().getMaintain() != null) {
                mBinding.loWeihuStatistics.loStatisticsy.setVisibility(View.VISIBLE);
                mBinding.loWeihuStatistics.rtcpDealedCount.setMax(data.getOAMStatistics().getMaintain().getProblemCount());
                mBinding.loWeihuStatistics.rtcpNotDealCount.setMax(data.getOAMStatistics().getMaintain().getProblemCount());
                mBinding.loWeihuStatistics.rtcpDealedCount.setProgress(data.getOAMStatistics().getMaintain().getProcessedProblem());
                mBinding.loWeihuStatistics.rtcpNotDealCount.setProgress(data.getOAMStatistics().getMaintain().getNotProcessedProblem());
                mBinding.loWeihuStatistics.rtcpXunchaCount.setText(data.getOAMStatistics().getMaintain().getWorkOrderCount() + "");
                mBinding.loWeihuStatistics.rtcpDealedCount.setText(data.getOAMStatistics().getMaintain().getProcessedProblem() + "");
                mBinding.loWeihuStatistics.rtcpNotDealCount.setText(data.getOAMStatistics().getMaintain().getNotProcessedProblem() + "");
                mBinding.loWeihuStatistics.rtcpQuestionCount.setText(data.getOAMStatistics().getMaintain().getProblemCount() + "");
            } else {
                mBinding.loWeihuStatistics.loStatisticsy.setVisibility(View.GONE);
            }
        }

        if (data.getStorageCapacity() != null) {
            refreshChart(data.getStorageCapacity());
        }
    }

    /**
     * 刷新表格
     *
     * @param dataBeans
     */
    private void refreshChart(List<HomeGetReservoirInfoBean.StorageCapacityBean> dataBeans) {
        float granularity = 1.0f;
        int size = dataBeans.size();
        if(size > 100){
            granularity = 10.0f;
        }else {
            granularity = 3.0f;
        }
        lineChartEntity.setDataOfCapacity("", dataBeans, granularity);
    }

    private void refreshViewWaterLevelStorageCapacity(HomeGetReservoirInfoBean.ReservoirWaterLevelBean reservoirWaterLevel) {
        String host = "http://202.98.201.102:1340/#/appcanvas?";
        String prarm = "";
        prarm += "reservoirCode=" + reservoirWaterLevel.getReservoirId() + "&";
        if (reservoirWaterLevel.getDamCrestElevation() != null) {
            prarm += "reservoirHeight=" + reservoirWaterLevel.getDamCrestElevation() + "&";
        }
        if (reservoirWaterLevel.getCheckFloodWaterLevel() != null) {
            prarm += "checkFloodWaterLevel=" + reservoirWaterLevel.getCheckFloodWaterLevel() + "&";
        }
        if (reservoirWaterLevel.getDesignFloodWaterLevel() != null) {
            prarm += "designFloodWaterLevel=" + reservoirWaterLevel.getDesignFloodWaterLevel() + "&";
        }
        if (reservoirWaterLevel.getNormalImpoundedLevel() != null) {
            prarm += "normalImpoundedLevel=" + reservoirWaterLevel.getNormalImpoundedLevel() + "&";
        }
        if (reservoirWaterLevel.getFloodSeasonWaterLevel() != null) {
            prarm += "floodSeasonWaterLevel=" + reservoirWaterLevel.getFloodSeasonWaterLevel() + "&";
        }
        if (reservoirWaterLevel.getFloodSeasonStartDate() != null) {
            prarm += "floodSeasonStartDate=" + reservoirWaterLevel.getFloodSeasonStartDate() + "&";
        }
        if (reservoirWaterLevel.getFloodSeasonStartDate() != null) {
            prarm += "floodSeasonEndDate=" + reservoirWaterLevel.getFloodSeasonStartDate() + "&";
        }
        if (reservoirWaterLevel.getRz() != null) {
            prarm += "realTimeLevel=" + reservoirWaterLevel.getRz() + "&";
        }
        if (reservoirWaterLevel.getW() != null) {
            prarm += "waterStorate=" + reservoirWaterLevel.getW() + "&";
        }
        if (reservoirWaterLevel.getTm() != null) {
            prarm += "dataTime=" + reservoirWaterLevel.getTm();
        }
        AgentWeb.with(this)
                .setAgentWebParent(mBinding.wvRealTimeWaterLevelStorageCapacity, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))//
                .setIndicatorColorWithHeight(-1, 2)
                .createAgentWeb()
                .ready()
                .go(host + prarm);
    }


    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        // un-highlight values after the gesture is finished and no single-tap
        if (lastPerformedGesture != ChartTouchListener.ChartGesture.SINGLE_TAP) {
            // or highlightTouch(null) for callback to onNothingSelected(...)
            lcReservoirCapacity.highlightValues(null);
        }
    }

    @Override
    public void onChartLongPressed(MotionEvent me) {

    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {

    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {

    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {

    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {

    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {

    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}
