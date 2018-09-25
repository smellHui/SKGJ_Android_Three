package com.tepia.main.view.main.detail.liuliangzhanandrainfull;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
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
import com.tepia.main.R;
import com.tepia.main.model.detai.DetailManager;
import com.tepia.main.model.detai.LiuliangDetailBean;
import com.tepia.main.model.detai.RainfullBean;
import com.tepia.main.model.detai.StRiverRBean;
import com.tepia.main.utils.EmptyLayoutUtil;
import com.tepia.main.utils.TimePickerDialogUtil;
import com.tepia.main.view.main.detail.DetailContract;
import com.tepia.main.view.main.detail.DetailPresenter;
import com.tepia.main.view.main.detail.LineChartEntity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * 流量站详情页面
 * @author liying
 */
public class LiuliangFragment extends MVPBaseFragment<DetailContract.View,DetailPresenter> implements OnDateSetListener,
        OnChartGestureListener, OnChartValueSelectedListener {
    private static final String TAG = LiuliangFragment.class.getName();

    private static String typekey_detail = "DetailFragment_";
    private ImageView baseIv;


    private RecyclerView baseinfoRecV;
    private RecyclerView new_baseinfoRecV;


    private LineChart mLineChart;


    private Button searchStartBtn;

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







    // 时间点总数
    private int listCount;
    // data1
    private float[] data1;
    // 时间点
    private String[] data3;

    private AdapterStationDetailList adapterStationDetailList;
    /**
     * 流量站适配器
     */
    private AdapterFlowLLDetailList adapterFlowLLDetailList;

    private LineChartEntity lineChartEntity;

    public static int evenType;
    /**时间类型
     *
     * 查询类型（years 逐年，month 逐月，day 逐日，time 逐时）
     *
     */
    private String timeType = "";
    private String stcdstr = "";
    private String startTime;
    private String endTime;
    private List<Button> buttonList = new ArrayList<>(5);

    List<StRiverRBean.DataBean.StRiverRsBean> stRiverRsBeanList = new ArrayList<>();

    private LiuliangDetailBean.DataBean liuliangDataBean;
    private List<StationDetailResponse> baseinfo_list_new = new ArrayList<>();


    public LiuliangFragment() {
    }

    /**
     *
     * @param stcd  测站id
     * @return
     */
    public static LiuliangFragment newInstance(String stcd){

        LiuliangFragment f = new LiuliangFragment();
        Bundle bundle = new Bundle();
        bundle.putString(typekey_detail, stcd);
        f.setArguments(bundle);
        return f;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detail_liuliang;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        if(getArguments() != null && getArguments().containsKey(typekey_detail)) {
            stcdstr = getArguments().getString(typekey_detail);
        }
        initViewLL();
    }
    /**
     * 初始化流量站布局
     */
    private void initViewLL(){

        nestedsv = findView(R.id.nestedsv);
        baseIv = findView(R.id.baseIv);
        baseinfoRecV = findView(R.id.baseinfoRecV);
        mLineChart = findView(R.id.line_chart);
        mstarttimeTv = findView(R.id.mstarttimeTv);
        mendtimeTv = findView(R.id.mendtimeTv);

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

        showLintIv = findView(R.id.showLintIv);
        recycleLy = findView(R.id.recycleLy);
        lintFl = findView(R.id.lintFl);
        recycleLy.setVisibility(View.GONE);
        showLintIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(recycleLy.isShown()){
                    recycleLy.setVisibility(View.GONE);
                    lintFl.setVisibility(View.VISIBLE);
                }else{
                    recycleLy.setVisibility(View.VISIBLE);
                    lintFl.setVisibility(View.GONE);
                }
            }
        });

        new_baseinfoRecV = findView(R.id.new_baseinfoRecV);
        new_baseinfoRecV.setLayoutManager(new LinearLayoutManager(getContext()));
        new_baseinfoRecV.setHasFixedSize(true);
        new_baseinfoRecV.setNestedScrollingEnabled(false);
        adapterStationDetailList = new AdapterStationDetailList(getContext(),R.layout.fragment_mine_item_monitordetail,baseinfo_list_new);
        new_baseinfoRecV.setAdapter(adapterStationDetailList);
        getDetail(stcdstr);
        /**
         * 基础信息
         */
        baseinfoRecV.setLayoutManager(new LinearLayoutManager(getContext()));
        baseinfoRecV.setHasFixedSize(true);
        baseinfoRecV.setNestedScrollingEnabled(false);
        adapterFlowLLDetailList = new AdapterFlowLLDetailList(getActivity(), R.layout.fragment_item_ll, stRiverRsBeanList);
        baseinfoRecV.setAdapter(adapterFlowLLDetailList);
        //解决滑动冲突
        baseinfoRecV.setOnTouchListener(new View.OnTouchListener() {
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
        mLineChart.setOnChartGestureListener(this);
        mLineChart.setOnChartValueSelectedListener(this);
        //解决滑动冲突
        mLineChart.setOnTouchListener((view, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                //允许ScrollView截断点击事件，ScrollView可滑动
                nestedsv.requestDisallowInterceptTouchEvent(false);
            } else {
                //不允许ScrollView截断点击事件，点击事件由子View处理
                nestedsv.requestDisallowInterceptTouchEvent(true);
            }
//
            return false;
        });

        firstSearch();




    }

    private void getDetail(String stcd) {
        DetailManager.getInstance().getStStbprpBAndNewZQByType(stcd)
                .subscribe(new LoadingSubject<LiuliangDetailBean>(true, Utils.getContext().getString(R.string.data_loading)) {
                    @Override
                    protected void _onNext(LiuliangDetailBean liuliangDetailBean) {
                        if (liuliangDetailBean != null) {
                            if (liuliangDetailBean.getCode() == 0) {
                                liuliangDataBean = liuliangDetailBean.getData();
                                if (liuliangDataBean != null) {
                                    getStaionDetai(liuliangDataBean);
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
     * 设置基础数据recycleview数据源
     *
     * @param dataBean
     */
    private void getStaionDetai(LiuliangDetailBean.DataBean dataBean) {
        baseinfo_list_new.clear();
        if (dataBean != null) {
            list_add_baseinfo_new(getString(R.string.stationName), dataBean.getStnm(),"");
            list_add_baseinfo_new(getString(R.string.manageUnit), dataBean.getAdmauth(),"");
            list_add_baseinfo_new(getString(R.string.belongtown), dataBean.getStlc(),"");

            list_add_baseinfo_new(getString(R.string.latitude), dataBean.getLttd(),"");
            list_add_baseinfo_new(getString(R.string.longtude), dataBean.getLgtd(),"");
            list_add_baseinfo_new(getString(R.string.belongRiver), getString(R.string.setting_t_null),"");
            if (dataBean.getStRiverRs() != null && dataBean.getStRiverRs().size() > 0) {
                LiuliangDetailBean.DataBean.StRiverRsBean stRiverRsBean = dataBean.getStRiverRs().get(0);
                if(stRiverRsBean != null){
                    list_add_baseinfo_new(getString(R.string.time_unit), stRiverRsBean.getTm(),"");
                    list_add_baseinfo_new(getString(R.string.liuliangstr), stRiverRsBean.getQ()+"","((万m³))");
                }
            }

        }

    }

    private void list_add_baseinfo_new(String letfStr, String rightStr,String unitStr) {
        StationDetailResponse stationDetailResponse = new StationDetailResponse();
        stationDetailResponse.setTitleLeft(letfStr);
        setRightText(stationDetailResponse, rightStr,unitStr);
        baseinfo_list_new.add(stationDetailResponse);
    }

    private void setRightText(StationDetailResponse stationDetailResponse, String str,String unitStr) {
        if (TextUtils.isEmpty(str)) {
            stationDetailResponse.setTitleRight(getString(R.string.setting_t_null)+unitStr);
        } else {
            stationDetailResponse.setTitleRight(str+unitStr);
        }

    }

    /**
     *
     * @param direction
     * @return
     */
    @SuppressLint("RestrictedApi")
    public boolean canScrollVertically(int direction) {
       final int offset = nestedsv.computeVerticalScrollOffset();
        final int range = nestedsv.computeVerticalScrollRange() - nestedsv.computeVerticalScrollExtent();
        if (range == 0) {
            return false;
        }
        if (direction < 0) {
            return offset > 0;
        } else {
            return offset < range - 1;
        }
    }

    private void firstSearch(){
        timePickerDialogUtil = new TimePickerDialogUtil(getContext(),sf);
        timePickerDialogUtil.initTimePicker(this, Type.ALL);
        endTime = TimeFormatUtils.getStringDate();
        last_millseconds_end = TimeFormatUtils.strToLong(endTime);
        //往前一个月
        startTime = TimeFormatUtils.getNextDay(endTime,ConfigConsts.timeseriod);
        last_millseconds_start = TimeFormatUtils.strToLong(startTime);
        mstarttimeTv.setText(startTime);
        mendtimeTv.setText(endTime);

        timeType = ConfigConsts.TimeType[1];
        changeBtn(1);
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
                timeType = ConfigConsts.TimeType[0];
                changeBtn(0);
            } else if(i == R.id.dayBtn){
                timeType = ConfigConsts.TimeType[1];

                changeBtn(1);

            } else if(i == R.id.monthBtn){
                timeType = ConfigConsts.TimeType[2];

                changeBtn(2);

            } else if(i == R.id.yearBtn){
                timeType = ConfigConsts.TimeType[3];

                changeBtn(3);

            }else if(i == R.id.baseIv){
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
        search();
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

        stRiverRsBeanList.clear();
        adapterFlowLLDetailList.notifyDataSetChanged();

        if (mLineChart != null) {
            mLineChart.clear();
            //重设所有缩放和拖动，使图表完全适合它的边界（完全缩小）。
            mLineChart.fitScreen();
        }
        getStRiverByID(stcdstr, timeType,startTime,endTime);


    }



    /**
     * 获取流量站详细信息
     * @param stcd
     * @param selectType
     * @param startDate
     * @param endDate
     */
    private void getStRiverByID(String stcd,String selectType,String startDate,String endDate){
        mPresenter.attachView(new DetailContract.View<StRiverRBean>() {


            @Override
            public void success(StRiverRBean data) {
                LogUtil.e(TAG,"success "+data.getCode());
                if(data != null) {
                    stRiverRsBeanList.clear();
                    stRiverRsBeanList.addAll(data.getData().getStRiverRs());
                    if(stRiverRsBeanList != null && stRiverRsBeanList.size() > 0) {
                        adapterFlowLLDetailList.notifyDataSetChanged();
                        refreshChart(stRiverRsBeanList);
                    }else{
                        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_empty_list_view,null);
                        adapterFlowLLDetailList.setEmptyView(view);
                    }
                }else{
                    View view = LayoutInflater.from(getContext()).inflate(R.layout.view_empty_list_view,null);
                    adapterFlowLLDetailList.setEmptyView(view);
                }

            }

            @Override
            public void failure(String msg) {
                ToastUtils.longToast(msg+"");
                LogUtil.e(TAG,"failuer "+msg);
                View view = LayoutInflater.from(getContext()).inflate(R.layout.view_empty_list_view,null);
                TextView tv_empty_view_text = view.findViewById(R.id.tv_empty_view_text);
                tv_empty_view_text.setText(msg);
                adapterFlowLLDetailList.setEmptyView(view);
            }

            @Override
            public Context getContext() {
                return null;
            }
        });
       mPresenter.findStRiverRByStcd(stcd,selectType,startDate,endDate);


    }

    /**
     * 刷新数据流量站页面
     * @param stRiverRsBeanList
     */
    private void refreshLL(List<StRiverRBean.DataBean.StRiverRsBean> stRiverRsBeanList){
       adapterFlowLLDetailList.addData(stRiverRsBeanList);

    }

    /**
     * 刷新表格
     * @param stRiverRsBeanList
     */
    private void refreshChart(List<StRiverRBean.DataBean.StRiverRsBean> stRiverRsBeanList){
        float granularity = 1.0f;
        int size = stRiverRsBeanList.size();
        /**
         * 时 标签间隔设置
         */
        /*if (timeType.equals(ConfigConsts.TimeType[0]) ||
                timeType.equals(ConfigConsts.TimeType[1])) {

            granularity = 1.0f;
        }else if(timeType.equals(ConfigConsts.TimeType[2])){

            granularity = 2.0f;

        } else if(timeType.equals(ConfigConsts.TimeType[3])){
            granularity = 1.0f;
        }*/
        granularity = 1.0f;
        lineChartEntity.setDataOfLineChartLL(timeType,stRiverRsBeanList,granularity);
    }


    @Override
    protected void initRequestData() {

    }

    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        // un-highlight values after the gesture is finished and no single-tap
        if (lastPerformedGesture != ChartTouchListener.ChartGesture.SINGLE_TAP) {
            // or highlightTouch(null) for callback to onNothingSelected(...)
            mLineChart.highlightValues(null);
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
}
