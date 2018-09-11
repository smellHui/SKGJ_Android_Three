package com.tepia.main.view.main.detail.liuliangzhanandrainfull;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.MVPBaseFragment;
import com.tepia.base.utils.DoubleClickUtil;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.NetUtil;
import com.tepia.base.utils.TimeFormatUtils;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.utils.Utils;
import com.tepia.main.APPCostant;
import com.tepia.main.R;
import com.tepia.main.model.detai.DetailManager;
import com.tepia.main.model.detai.OneThreeSixBean;
import com.tepia.main.model.detai.RainfullBean;
import com.tepia.main.model.map.RainfallResponse;
import com.tepia.main.utils.EmptyLayoutUtil;
import com.tepia.main.utils.TimePickerDialogUtil;
import com.tepia.main.view.main.detail.DetailContract;
import com.tepia.main.view.main.detail.DetailRainfullPresenter;
import com.tepia.main.view.main.detail.LineChartEntity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * 雨量站详情页面
 *
 * @author liying
 * @date 2018/7/26
 */
public class RainFullFragment extends MVPBaseFragment<DetailContract.RainfullView, DetailRainfullPresenter> implements OnDateSetListener {
    private static final String TAG = RainFullFragment.class.getName();

    private static String typekey_detail = "RainFullFragment_";
    private static String RainFullFragment_bean = "RainFullFragment_bean";
    private ImageView baseIv;
    private LineChart mLineChart;

    private TextView mstarttimeTv;

    private TextView mendtimeTv;

    private NestedScrollView nestedsv;

    private Button timeBtn;

    private Button dayBtn;
    private Button monthBtn;
    private Button yearBtn;

    private ImageView showLintIv;
    private FrameLayout lintFl;
    private LinearLayout recycleLy;


    /**
     * 雨量站适配器
     */
    private AdapterRainFullDetailList adapterRainFullDetailist;

    private LineChartEntity lineChartEntity;

    private RecyclerView rainfullRecycleview;
    private RecyclerView baseRecycleview;
    private AdapterStationDetailList adapterStationDetailList;
    private List<StationDetailResponse> baseinfo_list = new ArrayList<>();
    private List<StationDetailResponse> baseinfo_list_new = new ArrayList<>();
//    private List<StationDetailResponse> one_baseinfo_list = new ArrayList<>();

    private RecyclerView oneThreeSixRecV;
    private AdapterStationDetailList oneThreeSixRecVAdapter;


    /**
     * 时间类型
     * <p>
     * 查询类型（years 逐年，month 逐月，day 逐日，time 逐时）
     */
    private String timeType = "";
    private String stcdstr = "";
    private String startTime;
    private String endTime;
    private List<Button> buttonList = new ArrayList<>(5);

    private List<RainfullBean.DataBean.StPptnRsBean> stRiverRsBeanList = new ArrayList<>();
    /**
     * 是否已加载雨量接口
     */
    private boolean hasrainfull;

    public RainFullFragment() {
    }

    private static String RainFullFragment_STCD = "RainFullFragment_STCD";
    private RainfullBean.DataBean rainfullDataBean;
    /**
     *
     * @param stcd  测站id
     * @return
     */
    public static RainFullFragment newInstance(String stcd){

        RainFullFragment f = new RainFullFragment();
        Bundle bundle = new Bundle();
        bundle.putString(RainFullFragment_STCD, stcd);
        f.setArguments(bundle);
        return f;
    }

    /**
     * @param stcd 测站id
     * @return
     */
    public static RainFullFragment newInstance(String stcd, RainfallResponse.DataBean.StStbprpBBean dataBean) {

        RainFullFragment f = new RainFullFragment();
        Bundle bundle = new Bundle();
        bundle.putString(typekey_detail, stcd);
        bundle.putSerializable(RainFullFragment_bean, dataBean);
        f.setArguments(bundle);
        return f;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detail_rainfull;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        RainfallResponse.DataBean.StStbprpBBean dataBean = null;
        if (getArguments() != null && getArguments().containsKey(typekey_detail)) {
            stcdstr = getArguments().getString(typekey_detail);
            dataBean = (RainfallResponse.DataBean.StStbprpBBean) getArguments().getSerializable(RainFullFragment_bean);

        }
        if (getArguments() != null && getArguments().containsKey(RainFullFragment_STCD)) {
            stcdstr = getArguments().getString(RainFullFragment_STCD);
        }

        baseRecycleview = findView(R.id.baseinfoRecV);
        oneThreeSixRecV = findView(R.id.oneThreeSixRecV);


        adapterStationDetailList = intiRecycleView(baseRecycleview, baseinfo_list_new);
        getDetail(stcdstr);


        oneThreeSixRecVAdapter = intiRecycleView(oneThreeSixRecV, baseinfo_list);
        attachPresent();
        initViewLL();
        initTimePicker();
        getOneThreeSixTimePpth(stcdstr, false);
        changeBtn(1);
    }

    /**
     * 查询水质站
     * @param stcd
     */
    private void getDetail(String stcd) {
        DetailManager.getInstance().getStStbprpBAndNewPPByType(stcd)
                .subscribe(new LoadingSubject<RainfullBean>(true, Utils.getContext().getString(R.string.data_loading)) {
                    @Override
                    protected void _onNext(RainfullBean rainfullBean) {
                        if (rainfullBean != null) {
                            if (rainfullBean.getCode() == 0) {
                                rainfullDataBean = rainfullBean.getData();
                                if (rainfullDataBean != null) {
                                    getStaionDetai(rainfullDataBean);
                                    adapterStationDetailList.notifyDataSetChanged();
                                }else {
                                    adapterStationDetailList.setEmptyView(EmptyLayoutUtil.show(getString(R.string.empty_tv)));
                                }

                            } else {
                                adapterStationDetailList.setEmptyView(EmptyLayoutUtil.show(getString(R.string.empty_tv)));
                            }
                        }
                    }

                    @Override
                    protected void _onError(String message) {
                        adapterStationDetailList.setEmptyView(EmptyLayoutUtil.show(message));
                    }
                });
    }


    private void attachPresent() {
        mPresenter.attachView(new DetailContract.RainfullView() {
            @Override
            public void success(RainfullBean data) {

                LogUtil.e(TAG, "success getStPpthRChartDataInfo:  " + ((RainfullBean) data).getCode());
                if (data != null) {
                    stRiverRsBeanList.clear();
                    stRiverRsBeanList.addAll(((RainfullBean) data).getData().getStPptnRs());
                    if (stRiverRsBeanList != null && stRiverRsBeanList.size() > 0) {
                        adapterRainFullDetailist.notifyDataSetChanged();
                        refreshChart(stRiverRsBeanList);

                    } else {
                        View view = LayoutInflater.from(Utils.getContext()).inflate(R.layout.view_empty_list_view, null);
                        adapterRainFullDetailist.setEmptyView(view);
                    }

                } else {
                    View view = LayoutInflater.from(Utils.getContext()).inflate(R.layout.view_empty_list_view, null);
                    adapterRainFullDetailist.setEmptyView(view);
                }


            }

            @Override
            public void successThree(OneThreeSixBean data) {

                LogUtil.e(TAG, "success getOneThreeSixTimePpth" + ((OneThreeSixBean) data).getCode());

                getOneSixe(((OneThreeSixBean) data).getData());
                if (baseinfo_list != null && baseinfo_list.size() > 0) {
                    oneThreeSixRecVAdapter.notifyDataSetChanged();
                } else {
                    View view = LayoutInflater.from(Utils.getContext()).inflate(R.layout.view_empty_list_view, null);
                    oneThreeSixRecVAdapter.setEmptyView(view);
                }

            }

            @Override
            public void failure(String msg) {
                adapterRainFullDetailist.setEmptyView(EmptyLayoutUtil.show(msg));
            }

            @Override
            public void failureThree(String msg) {
                oneThreeSixRecVAdapter.setEmptyView(EmptyLayoutUtil.show(msg));
            }

            @Override
            public Context getContext() {
                return null;
            }
        });
    }

    /**
     * 初始化recyclerView
     *
     * @param recyclerView
     */
    private AdapterStationDetailList intiRecycleView(RecyclerView recyclerView, List<StationDetailResponse> stationDetailResponseList) {

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        AdapterStationDetailList mAdapterStationDetailList = new AdapterStationDetailList(getContext(), R.layout.fragment_mine_item_monitordetail, stationDetailResponseList);
        recyclerView.setAdapter(mAdapterStationDetailList);

        return mAdapterStationDetailList;

    }

    /**
     * 设置基础数据recycleview数据源
     *
     * @param dataBean
     */
    private void getStaionDetai(RainfullBean.DataBean dataBean) {
        baseinfo_list_new.clear();
        if (dataBean != null) {
            new_list_add_info(getString(R.string.stationName), dataBean.getStnm());
            new_list_add_info(getString(R.string.manageUnit), dataBean.getAdmauth());
            new_list_add_info(getString(R.string.belongtown), dataBean.getStlc());

            new_list_add_info(getString(R.string.latitude), dataBean.getLttd());
            new_list_add_info(getString(R.string.longtude), dataBean.getLgtd());
            if(dataBean.getStPptnRs() != null && dataBean.getStPptnRs().size() > 0) {
                RainfullBean.DataBean.StPptnRsBean stPptnRsBean = dataBean.getStPptnRs().get(0);
                if (stPptnRsBean != null) {
                    new_list_add_info(getString(R.string.time_unit), stPptnRsBean.getTm());
                    new_list_add_info("雨量", stPptnRsBean.getDrp() + "(mm)");

                }
            }
        }

    }

    /**
     * 设置数据源
     *
     * @param dataBean
     */
    private void getOneSixe(OneThreeSixBean.DataBean dataBean) {
        baseinfo_list.clear();
        if (dataBean != null) {
            list_add_baseinfo(getString(R.string.onehour), dataBean.getOneH() + " mm");
            list_add_baseinfo(getString(R.string.threehour), dataBean.getThreeH() + " mm");
            list_add_baseinfo(getString(R.string.sixhour), dataBean.getSixH() + " mm");
        }

    }

    private void list_add_baseinfo(String letfStr, String rightStr) {
        StationDetailResponse stationDetailResponse = new StationDetailResponse();
        stationDetailResponse.setTitleLeft(letfStr);
        setRightText(stationDetailResponse, rightStr);
        baseinfo_list.add(stationDetailResponse);
    }

    private void new_list_add_info(String letfStr, String rightStr) {
        StationDetailResponse stationDetailResponse = new StationDetailResponse();
        stationDetailResponse.setTitleLeft(letfStr);
        setRightText(stationDetailResponse, rightStr);
        baseinfo_list_new.add(stationDetailResponse);
    }

    private void setRightText(StationDetailResponse stationDetailResponse, String str) {
        if (TextUtils.isEmpty(str)) {
            stationDetailResponse.setTitleRight(getString(R.string.setting_t_null));
        } else {
            stationDetailResponse.setTitleRight(str);
        }

    }

    /**
     * 初始化雨量站图表布局
     */
    private void initViewLL() {
        ImageView refreshIv = findView(R.id.refreshIv);
        nestedsv = findView(R.id.nestedsv);
        baseIv = findView(R.id.baseIv);
        rainfullRecycleview = findView(R.id.rainfullRecV);
        mLineChart = findView(R.id.line_chart);
        mstarttimeTv = findView(R.id.mstarttimeTv);
        mendtimeTv = findView(R.id.mendtimeTv);

        showLintIv = findView(R.id.showLintIv);
        recycleLy = findView(R.id.recycleLy);
        lintFl = findView(R.id.lintFl);
        recycleLy.setVisibility(View.GONE);
        showLintIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (recycleLy.isShown()) {
                    recycleLy.setVisibility(View.GONE);
                    lintFl.setVisibility(View.VISIBLE);
                } else {
                    recycleLy.setVisibility(View.VISIBLE);
                    lintFl.setVisibility(View.GONE);
                }
            }
        });


        timeBtn = findView(R.id.timeBtn);
        dayBtn = findView(R.id.dayBtn);
        monthBtn = findView(R.id.monthBtn);
        yearBtn = findView(R.id.yearBtn);
        buttonList.add(timeBtn);
        buttonList.add(dayBtn);
        buttonList.add(monthBtn);
        buttonList.add(yearBtn);

        mstarttimeTv.setOnClickListener(onClickListener);
        mendtimeTv.setOnClickListener(onClickListener);
        timeBtn.setOnClickListener(onClickListener);
        dayBtn.setOnClickListener(onClickListener);
        monthBtn.setOnClickListener(onClickListener);
        yearBtn.setOnClickListener(onClickListener);
        baseIv.setOnClickListener(onClickListener);
        refreshIv.setOnClickListener(onClickListener);


        /**
         * s实时雨量图表信息
         */
        rainfullRecycleview.setLayoutManager(new LinearLayoutManager(getContext()));
        rainfullRecycleview.setHasFixedSize(true);
        rainfullRecycleview.setNestedScrollingEnabled(false);
        adapterRainFullDetailist = new AdapterRainFullDetailList(getActivity(), R.layout.fragment_item_ll, stRiverRsBeanList);
        rainfullRecycleview.setAdapter(adapterRainFullDetailist);
        //解决滑动冲突
        rainfullRecycleview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    //允许ScrollView截断点击事件，ScrollView可滑动
                    nestedsv.requestDisallowInterceptTouchEvent(false);
                } else {
                    //不允许ScrollView截断点击事件，点击事件由子View处理
                    nestedsv.requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }
        });

        lineChartEntity = new LineChartEntity(mLineChart);
        //解决滑动冲突
        mLineChart.setOnTouchListener((view, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                //允许ScrollView截断点击事件，ScrollView可滑动
                nestedsv.requestDisallowInterceptTouchEvent(false);
            } else {
                //不允许ScrollView截断点击事件，点击事件由子View处理
                nestedsv.requestDisallowInterceptTouchEvent(true);
            }
            return false;
        });


    }

    /**
     * 初始化时间选择器
     */
    private void initTimePicker() {
        timePickerDialogUtil = new TimePickerDialogUtil(getContext(), sf);
        timePickerDialogUtil.initTimePicker(this, Type.ALL);
        endTime = TimeFormatUtils.getStringDate();
        last_millseconds_end = TimeFormatUtils.strToLong(endTime);
        //往前一个月
        startTime = TimeFormatUtils.getNextDay(endTime, APPCostant.timeseriod);
        last_millseconds_start = TimeFormatUtils.strToLong(startTime);
        mstarttimeTv.setText(startTime);
        mendtimeTv.setText(endTime);

        timeType = APPCostant.TimeType[1];


    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int i = view.getId();
            if (i == R.id.mstarttimeTv) {
                if (timePickerDialogUtil.startDialog != null) {
                    timePickerDialogUtil.startDialog = null;
                }
                startflag = true;
                if (last_millseconds_start != 0) {
                    timePickerDialogUtil.builder.setCurrentMillseconds(last_millseconds_start);
                }
                timePickerDialogUtil.builder.setTitleStringId(getContext().getString(R.string.starttimeTitle));
                timePickerDialogUtil.startDialog = timePickerDialogUtil.builder.build();
                timePickerDialogUtil.startDialog.show(getFragmentManager(), "all");


            } else if (i == R.id.mendtimeTv) {
                if (timePickerDialogUtil.startDialog != null) {
                    timePickerDialogUtil.startDialog = null;
                }
                endflag = true;
                if (last_millseconds_end != 0) {
                    timePickerDialogUtil.builder.setCurrentMillseconds(last_millseconds_end);
                }
                timePickerDialogUtil.builder.setTitleStringId(getContext().getString(R.string.endtimeTitle));
                timePickerDialogUtil.startDialog = timePickerDialogUtil.builder.build();

                timePickerDialogUtil.startDialog.show(getFragmentManager(), "all");

            } else if (i == R.id.timeBtn) {
                timeType = APPCostant.TimeType[0];
                changeBtn(0);

            } else if (i == R.id.dayBtn) {
                timeType = APPCostant.TimeType[1];

                changeBtn(1);


            } else if (i == R.id.monthBtn) {
                timeType = APPCostant.TimeType[2];

                changeBtn(2);


            } else if (i == R.id.yearBtn) {
                timeType = APPCostant.TimeType[3];

                changeBtn(3);


            } else if (i == R.id.baseIv) {
                search();
            } else if (i == R.id.refreshIv) {
                getOneThreeSixTimePpth(stcdstr, true);
            }
        }
    };

    private void changeBtn(int mtype) {

        for (int i = 0; i < buttonList.size(); i++) {
            if (i == mtype) {
                buttonList.get(i).setTextColor(getResources().getColor(R.color.white));
                buttonList.get(i).setBackground(ContextCompat.getDrawable(getContext(), R.drawable.sel_btn_check));
            } else {
                buttonList.get(i).setTextColor(getResources().getColor(R.color.b5b5));
                buttonList.get(i).setBackground(ContextCompat.getDrawable(getContext(), R.drawable.sel_btn_uncheck));
            }
        }
        LogUtil.e(TAG, "开始调用changeBtn");
        search();

    }

    /**
     * 执行搜索
     */
    private void search() {

        if (!NetUtil.isNetworkConnected(getBaseActivity())) {
            LogUtil.e(TAG, "开始调用isNetworkConnected");
            ToastUtils.shortToast(R.string.no_network);
            return;
        }
        if (last_millseconds_end < last_millseconds_start) {
            ToastUtils.shortToast(R.string.cannot);
            LogUtil.e(TAG, "开始调用last_millseconds_end");
            return;
        }

        stRiverRsBeanList.clear();
        adapterRainFullDetailist.notifyDataSetChanged();
        if (mLineChart != null) {
            mLineChart.clear();
            //重设所有缩放和拖动，使图表完全适合它的边界（完全缩小）。
            mLineChart.fitScreen();
        }
        getStPpthRChartDataInfo(stcdstr, timeType, startTime, endTime);


    }

    /**
     * 获取1，3，6小时雨量
     *
     * @param stcd
     */
    private void getOneThreeSixTimePpth(String stcd, boolean isshow) {
        LogUtil.e(TAG, "开始调用getOneThreeSixTimePpth接口");

        if (DoubleClickUtil.isFastDoubleClick()) {
            return;
        }
        if (!NetUtil.isNetworkConnected(getBaseActivity())) {
            ToastUtils.shortToast(R.string.no_network);
            return;
        }
        mPresenter.getOneThreeSixTimePpth(stcd, isshow);
    }


    /**
     * 获取雨量站详细信息
     *
     * @param stcd
     * @param selectType
     * @param startDate
     * @param endDate
     */
    private void getStPpthRChartDataInfo(String stcd, String selectType, String startDate, String endDate) {
        LogUtil.e(TAG, "开始调用getStPpthRChartDataInfo接口");

        mPresenter.getStPpthRChartDataInfo(stcd, selectType, startDate, endDate);


    }


    /**
     * 刷新表格
     *
     * @param stPptnRsBeanList
     */
    private void refreshChart(List<RainfullBean.DataBean.StPptnRsBean> stPptnRsBeanList) {
        float granularity = 1.0f;
        int size = stPptnRsBeanList.size();
        /**
         * 时 标签间隔设置
         */
        /*if (timeType.equals(APPCostant.TimeType[0]) ||
                timeType.equals(APPCostant.TimeType[1])) {
            granularity = 10.0f;
            while (size > 0 && size < granularity) {
                granularity /= 2;
            }
            if (size < 10) {
                granularity = 1.0f;
            }
        } else if (timeType.equals(APPCostant.TimeType[2])) {

            granularity = 2.0f;

        } else if (timeType.equals(APPCostant.TimeType[3])) {
            granularity = 1.0f;
        }*/
        granularity = 1.0f;
        lineChartEntity.setDataOfLineChartRainfullBean(timeType, stPptnRsBeanList, granularity);
    }


    @Override
    protected void initRequestData() {

    }

    // 起始时间选择器
    private boolean startflag = false;
    // 终止时间选择器
    private boolean endflag = false;
    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    private long last_millseconds_start = 0;
    private long last_millseconds_end = 0;

    private TimePickerDialogUtil timePickerDialogUtil;

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {

        if (startflag) {
            last_millseconds_start = millseconds;
            String text = timePickerDialogUtil.getDateToString(millseconds);
            startTime = text;
            mstarttimeTv.setText(startTime);
            startflag = false;
            LogUtil.e("startTime", text);

        } else if (endflag) {
            last_millseconds_end = millseconds;
            String text = timePickerDialogUtil.getDateToString(millseconds);
            endTime = text;
            mendtimeTv.setText(endTime);
            endflag = false;
            LogUtil.e("endTime", text);

        }

    }
}
