package com.tepia.main.view.main.detail.shuizhizhan;


import android.content.Context;
import android.graphics.Color;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
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
import com.tepia.main.ConfigConsts;
import com.tepia.main.ConfigConsts;
import com.tepia.main.R;
import com.tepia.main.model.detai.DetailManager;
import com.tepia.main.model.detai.WaterPhBean;
import com.tepia.main.model.detai.WaterQualityDetailBean;
import com.tepia.main.model.detai.WaterlevelBean;
import com.tepia.main.model.map.WaterQualityResponse;
import com.tepia.main.utils.EmptyLayoutUtil;
import com.tepia.main.utils.TimePickerDialogUtil;
import com.tepia.main.view.main.detail.DetailContract;
import com.tepia.main.view.main.detail.DetailPresenter;
import com.tepia.main.view.main.detail.LineChartEntity;
import com.tepia.main.view.main.detail.MyMarkerView;
import com.tepia.main.view.main.detail.liuliangzhanandrainfull.AdapterStationDetailList;
import com.tepia.main.view.main.detail.liuliangzhanandrainfull.StationDetailResponse;
import com.tepia.main.view.main.detail.shuiweizhan.WaterLevelFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * 水质站详情页面
 * @author liying
 * @date 2018/7/25
 */
public class WaterQualityFragment extends MVPBaseFragment<DetailContract.View,DetailPresenter> implements OnDateSetListener {

    private static String WaterQualityFragment_ = "WaterQualityFragment_";
    private static String WaterQuality = "WaterQuality";

    private RecyclerView baseinfoRecV;
    private LinearLayout recycleLy;
    private AdapterStationDetailList adapterStationDetailList;
    private List<StationDetailResponse> baseinfo_list = new ArrayList<>();
    private WaterQualityResponse.DataBean waterQuality_databean;

    private TextView mstarttimeTv;
    private TextView mendtimeTv;
    private NestedScrollView nestedsv;
    private Button timeBtn;
    private Button dayBtn;
    private Button monthBtn;
    private Button yearBtn;
    private Button yuluBtn;
    private ImageView realtimeIv;

    private LineChart mLineChart;
    private LineChartEntity lineChartEntity;
    /**
     * 图表数据
     */
    private List<WaterPhBean.DataBean> dataBeanArrayListll = new ArrayList<>();

    /**取值类型
     *
     * 查询类型（）
     *
     */
    private int timeType;
    private String stcdstr = "";
    private String startTime;
    private String endTime;

    private List<Button> buttonList = new ArrayList<>(5);

    public WaterQualityFragment() {
        // Required empty public constructor
    }

    private static String WaterQualityFragment_STCD = "WaterQualityFragment_STCD";
    private WaterQualityDetailBean.DataBean waterQualityDetailBean;

    /**
     * @param stcd 测站id
     * @return
     */
    public static WaterQualityFragment newInstance(String stcd) {

        WaterQualityFragment f = new WaterQualityFragment();
        Bundle bundle = new Bundle();
        bundle.putString(WaterQualityFragment_STCD, stcd);
        f.setArguments(bundle);
        return f;
    }

    /**
     *
     * @param stcd  水质站ID
     * @return
     */
    public static WaterQualityFragment newInstance(String stcd,WaterQualityResponse.DataBean waterQuality_databean){

        WaterQualityFragment f = new WaterQualityFragment();
        Bundle bundle = new Bundle();
        bundle.putString(WaterQualityFragment_, stcd);
        bundle.putSerializable(WaterQuality, waterQuality_databean);
        f.setArguments(bundle);
        return f;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detail_waterph;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        if(getArguments() != null && getArguments().containsKey(WaterQualityFragment_)) {
            stcdstr = getArguments().getString(WaterQualityFragment_);
            waterQuality_databean = (WaterQualityResponse.DataBean) getArguments().getSerializable(WaterQuality);
        }

        if (getArguments() != null && getArguments().containsKey(WaterQualityFragment_STCD)) {
            stcdstr = getArguments().getString(WaterQualityFragment_STCD);
        }
        
        baseinfoRecV = findView(R.id.baseinfoRecV);

        nestedsv = findView(R.id.nestedsv);


        baseinfoRecV.setLayoutManager(new LinearLayoutManager(getContext()));
        baseinfoRecV.setHasFixedSize(true);
        baseinfoRecV.setNestedScrollingEnabled(false);
        adapterStationDetailList = new AdapterStationDetailList(getContext(),R.layout.fragment_mine_item_monitordetail,baseinfo_list);
        baseinfoRecV.setAdapter(adapterStationDetailList);
        getDetail(stcdstr);

        mLineChart = findView(R.id.line_chart);
        //初始化图表
        lineChartEntity = new LineChartEntity(mLineChart);

        //解决滑动冲突
        mLineChart.setOnTouchListener(new View.OnTouchListener() {
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
        realtimeIv = findView(R.id.realtimeIv);
        mstarttimeTv = findView(R.id.mstarttimeTv);
        mendtimeTv = findView(R.id.mendtimeTv);

        timeBtn = findView(R.id.timeBtn);
        dayBtn = findView(R.id.dayBtn);
        monthBtn = findView(R.id.monthBtn);
        yearBtn = findView(R.id.yearBtn);
        yuluBtn = findView(R.id.yuluBtn);

        buttonList.add(timeBtn);
        buttonList.add(dayBtn);
        buttonList.add(monthBtn);
        buttonList.add(yearBtn);
        buttonList.add(yuluBtn);

        mstarttimeTv.setOnClickListener(onClickListener);
        mendtimeTv.setOnClickListener(onClickListener);
        timeBtn.setOnClickListener(onClickListener);
        dayBtn.setOnClickListener(onClickListener);
        monthBtn.setOnClickListener(onClickListener);
        yearBtn.setOnClickListener(onClickListener);
        yuluBtn.setOnClickListener(onClickListener);
        realtimeIv.setOnClickListener(onClickListener);

        mPresenter.attachView(new DetailContract.View<WaterPhBean>() {
            @Override
            public void success(WaterPhBean data) {
                if(data != null){
                    changeBtn(timeType);
                    dataBeanArrayListll.clear();
                    dataBeanArrayListll = data.getData();
                    setDataOfLineChart(timeType,dataBeanArrayListll,10.0f);
                }
            }

            @Override
            public void failure(String msg) {
                ToastUtils.longToast(msg+"");
            }

            @Override
            public Context getContext() {
                return null;
            }
        });


        initTime();
        search();
    }

    /**
     * 查询水质站
     * @param stcd
     */
    private void getDetail(String stcd) {
        DetailManager.getInstance().getStStbprpBAndNewWQByType(stcd)
                .subscribe(new LoadingSubject<WaterQualityDetailBean>(true, Utils.getContext().getString(R.string.data_loading)) {
                    @Override
                    protected void _onNext(WaterQualityDetailBean waterlevelBean) {
                        if (waterlevelBean != null) {
                            if (waterlevelBean.getCode() == 0) {
                                waterQualityDetailBean = waterlevelBean.getData();
                                if (waterQualityDetailBean != null) {
                                    getStaionDetai(waterQualityDetailBean);
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

    /**
     * 初始化时间
     */
    private void initTime(){
        timePickerDialogUtil = new TimePickerDialogUtil(getContext(),sf);
        timePickerDialogUtil.initTimePicker(this, Type.ALL);
        endTime = TimeFormatUtils.getStringDate();
        last_millseconds_end = TimeFormatUtils.strToLong(endTime);
        //往前一个月
        startTime = TimeFormatUtils.getNextDay(endTime,ConfigConsts.timeseriod);
        last_millseconds_start = TimeFormatUtils.strToLong(startTime);
        mstarttimeTv.setText(startTime);
        mendtimeTv.setText(endTime);
        timeType = ConfigConsts.TimeTypeInt[0];
    }

    /**
     * 基础信息
     */
    private void getStaionDetai( WaterQualityDetailBean.DataBean waterQualityDetailBean) {
        baseinfo_list.clear();
        list_add_baseinfo(getString(R.string.manageUnit), waterQualityDetailBean.getAdmauth());
        list_add_baseinfo(getString(R.string.locationstation), waterQualityDetailBean.getStlc());
        list_add_baseinfo(getString(R.string.longtude), String.valueOf(waterQualityDetailBean.getLgtd()));
        list_add_baseinfo(getString(R.string.latitude), String.valueOf(waterQualityDetailBean.getLttd()));
        list_add_baseinfo(getString(R.string.belongshuixi), getString(R.string.setting_t_null));
        list_add_baseinfo(getString(R.string.belongRiver), getString(R.string.setting_t_null));
        list_add_baseinfo(getString(R.string.belongliuyu), getString(R.string.setting_t_null));
        if(waterQualityDetailBean.getStWqRS() != null && waterQualityDetailBean.getStWqRS().size() > 0){
            WaterQualityDetailBean.DataBean.StWqRSBean stWqRSBean = waterQualityDetailBean.getStWqRS().get(0);
            list_add_baseinfo(getString(R.string.jiancetime),stWqRSBean.getSpt());
            list_add_baseinfo(getString(R.string.phstr), stWqRSBean.getPh()+"");
            list_add_baseinfo(getString(R.string.watertem), stWqRSBean.getWt()+"(°C)");
            list_add_baseinfo(getString(R.string.wt), stWqRSBean.getTurb()+"(度)");
            list_add_baseinfo(getString(R.string.clstr), stWqRSBean.getCl2()+"(mg/l)");
        }

    }

    private void list_add_baseinfo(String letfStr, String rightStr) {
        StationDetailResponse stationDetailResponse = new StationDetailResponse();
        stationDetailResponse.setTitleLeft(letfStr);
        setRightText(stationDetailResponse, rightStr);
        baseinfo_list.add(stationDetailResponse);
    }

    private void setRightText(StationDetailResponse stationDetailResponse, String str) {
        if (TextUtils.isEmpty(str)) {
            stationDetailResponse.setTitleRight(getString(R.string.setting_t_null));
        } else {
            stationDetailResponse.setTitleRight(str);
        }

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

            } else if(i == R.id.timeBtn){
                timeType = ConfigConsts.TimeTypeInt[0];
                changeBtn(0);
                setDataOfLineChart(timeType,dataBeanArrayListll,10.0f);

            } else if(i == R.id.dayBtn){
                timeType = ConfigConsts.TimeTypeInt[1];

                changeBtn(1);
                setDataOfLineChart(timeType,dataBeanArrayListll,10.0f);

            } else if(i == R.id.monthBtn){
                timeType = ConfigConsts.TimeTypeInt[2];

                changeBtn(2);
                setDataOfLineChart(timeType,dataBeanArrayListll,10.0f);

            } else if(i == R.id.yearBtn){
                timeType = ConfigConsts.TimeTypeInt[3];

                changeBtn(3);
                setDataOfLineChart(timeType,dataBeanArrayListll,10.0f);

            }else if(i == R.id.yuluBtn){
                timeType = ConfigConsts.TimeTypeInt[4];

                changeBtn(4);
                setDataOfLineChart(timeType,dataBeanArrayListll,10.0f);


            }else if(i == R.id.realtimeIv){
                search();
            }

        }
    };

    private void changeBtn(int mtype){
        for (int i = 0; i < buttonList.size(); i++) {
           if(i == mtype){
               buttonList.get(i).setTextColor(getResources().getColor(R.color.white));
               buttonList.get(i).setBackground(ContextCompat.getDrawable(getContext(),R.drawable.sel_btn_check));
           }else {
               buttonList.get(i).setTextColor(getResources().getColor(R.color.b5b5));
               buttonList.get(i).setBackground(ContextCompat.getDrawable(getContext(),R.drawable.sel_btn_uncheck));
           }
        }


    }

    /**
     * 执行搜索
     */
    private void search() {
        if (DoubleClickUtil.isFastDoubleClick()) {
            return;
        }
        if(!NetUtil.isNetworkConnected(getBaseActivity())){
            ToastUtils.shortToast(R.string.no_network);
            return;
        }
        if (last_millseconds_end < last_millseconds_start) {
            ToastUtils.shortToast(R.string.cannot);
            return;
        }

        if (mLineChart != null) {
            mLineChart.clear();
            //重设所有缩放和拖动，使图表完全适合它的边界（完全缩小）。
            mLineChart.fitScreen();
        }
        mPresenter.findListStWqRByDate(stcdstr,startTime,endTime);


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

        }
        else if(endflag) {
            last_millseconds_end = millseconds;
            String text = timePickerDialogUtil.getDateToString(millseconds);
            endTime = text;
            mendtimeTv.setText(endTime);
            endflag = false;
            LogUtil.e("endTime", text);

        }

    }

    // 时间点
    private float[] data1;
    private String[] data3;
    // 时间点总数
    private int listCount;
    private void setDataOfLineChart(int timeType, List<WaterPhBean.DataBean> databean, float granularity) {
        if (mLineChart != null) {
            mLineChart.clear();
            //重设所有缩放和拖动，使图表完全适合它的边界（完全缩小）。
            mLineChart.fitScreen();
        }
        // 填入数据
        setData(databean,timeType);
        // 立即执行的动画,x轴
        mLineChart.animateX(1500);
        // 在setData之后加载示例图
        Legend l = mLineChart.getLegend();
        // 初始化示例图
        l.setForm(Legend.LegendForm.NONE);
        XAxis xAxis = mLineChart.getXAxis();
        xAxis.setTextSize(10f);
//        xAxis.setSpaceMin(10);

//        xAxis.setLabelCount(listCount);//设置标签个数
        xAxis.setAxisLineColor(Color.BLACK);
        xAxis.setTextColor(ConfigConsts.colortext);
        xAxis.setDrawGridLines(true);//
//        xAxis.setDrawGridLines(false);
//        xAxis.setDrawAxisLine(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(granularity); // 间隔几个显示一次标签
        xAxis.setGranularityEnabled(true);
        xAxis.setLabelCount(databean.size());//设置x轴显示的标签个数
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {

                if (data3 != null && data3.length > 0) {
                    String formatvalue = data3[(Math.abs((int) value) % data3.length)];
                    if(TextUtils.isEmpty(formatvalue)){
                        return " ";
                    }
                    return formatvalue;
                }
                return "";
            }
        });

        YAxis leftAxis = mLineChart.getAxisLeft();
        leftAxis.setAxisLineColor(Color.BLACK);
        leftAxis.setTextColor(ConfigConsts.colortext);
//        leftAxis.setDrawGridLines(true);//
        leftAxis.setGranularityEnabled(false);
//        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setDrawZeroLine(true);
        // limit lines are drawn behind data (and not on top)
        leftAxis.setDrawLimitLinesBehindData(true);
        mLineChart.getAxisRight().setEnabled(false);
    }



    private String getTm(String tm,int timeType) {
        if (tm != null && tm.length() > 16) {
            xVals.add(tm);
            tm = tm.substring(11, 16);

        }
        return tm;
    }

    private ArrayList<String> xVals = new ArrayList<>();

    private void setData(List<WaterPhBean.DataBean> databean,int timeType) {
        xVals.clear();
        if (databean == null || databean.size() == 0) {
            return;
        }
        listCount = databean.size();
        data1 = new float[listCount];

        data3 = new String[listCount];
        MyMarkerView mv = new MyMarkerView(Utils.getContext(), R.layout.custom_marker_view, xVals);
        mv.setChartView(mLineChart);
        mLineChart.setMarker(mv);
        for (int i = 0; i < listCount; i++) {

            if(timeType == 0) {
                double qstr = databean.get(i).getPh();
                data1[i] = (float) qstr;
            }else if(timeType == 1){
                double qstr = databean.get(i).getWt();
                data1[i] = (float) qstr;
            }else if(timeType == 2){
                data1[i] = (float) 0.0;
            }else if(timeType == 3){
                double qstr = databean.get(i).getTurb();
                data1[i] = (float) qstr;
            }else if(timeType == 4){
                double qstr = databean.get(i).getCl2();
                data1[i] = (float) qstr;
            }


            String tm = databean.get(i).getSpt();
            tm = getTm(tm, timeType);
            data3[i] = tm;


        }
        YAxis leftAxis = mLineChart.getAxisLeft();
        LimitLine ll1 = null;
        LimitLine ll2 = null;
//        leftAxis.setAxisMaximum(14f);//y轴最大值
//        leftAxis.setAxisMinimum(0f);//y轴最小值

        if (ll1 != null) {
            leftAxis.addLimitLine(ll1);
        }
        if (ll2 != null) {
            leftAxis.addLimitLine(ll2);
        }
        int groupCount = listCount;
        ArrayList<Entry> yVals1 = new ArrayList<>();
        ArrayList<Entry> yVals2 = new ArrayList<>();

        for (int j = 0; j < groupCount; j++) {
            float val = data1[j];
            yVals1.add(new Entry(j, val));

        }
        LineDataSet set1, set2;
        if (mLineChart.getData() != null &&
                mLineChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) mLineChart.getData().getDataSetByIndex(0);
//            set2 = (LineDataSet) mLineChart.getData().getDataSetByIndex(1);
            set1.setValues(yVals1);
//            set2.setValues(yVals2);
            mLineChart.getData().notifyDataChanged();
            mLineChart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(yVals1, "");
            set1.setAxisDependency(YAxis.AxisDependency.LEFT);
            set1.setColor(ColorTemplate.getHoloBlue());
            set1.setLineWidth(1f);
//            set1.setCircleColor(Color.rgb(0, 168, 255));// 圆周的颜色
//            set1.setFillAlpha(65);

            set1.setFillColor(Color.rgb(0, 168, 255));//线条颜色 00a8ff
            set1.setHighLightColor(Color.rgb(244, 117, 117));//高亮线颜色
//            set1.setDrawCircleHole(true);
//            set1.setColor(Color.rgb(254, 139, 3));

            /*set1.setCircleColor(Color.rgb(0, 168, 255));// 圆周的颜色
            set1.setCircleRadius(0f);
            set1.setFillAlpha(65);
            set1.setDrawCircles(true);//画圆
            //圆点填充颜色
            set1.setCircleColorHole(Color.rgb(255, 255, 255));
            set1.setCircleHoleRadius(3f);*/
            set1.setDrawFilled(false);// 背景填充
            set1.setDrawCircleHole(false);
            set1.setDrawCircles(false);//画圆

            LineData data = new LineData(set1, set1);
            //点上文字的颜色
            data.setValueTextColor(Color.RED);
            data.setValueTextSize(9f);
            data.setDrawValues(false);

            // set data
            mLineChart.setData(data);
            List<ILineDataSet> sets = mLineChart.getData()
                    .getDataSets();

            /*for (ILineDataSet iSet : sets) {
                LineDataSet set = (LineDataSet) iSet;
                set.setDrawCircles(false);
            }*/
        }
    }

}
