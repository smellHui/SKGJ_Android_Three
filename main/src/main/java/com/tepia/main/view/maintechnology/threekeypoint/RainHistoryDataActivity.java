package com.tepia.main.view.maintechnology.threekeypoint;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.jzxiang.pickerview.data.Type;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.NetUtil;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.utils.Utils;
import com.tepia.main.R;
import com.tepia.main.model.jishu.threepoint.RainConditionResponse;
import com.tepia.main.model.jishu.threepoint.RainHistoryResponse;
import com.tepia.main.utils.EmptyLayoutUtil;
import com.tepia.main.utils.TimePickerDialogUtil;
import com.tepia.main.view.maintechnology.threekeypoint.adapter.MyRainListAdapter;
import com.tepia.main.view.maintechnology.threekeypoint.adapter.RainHistoryListAdapter;
import com.tepia.main.view.maintechnology.threekeypoint.util.ThreePointCharUtil;
import com.tepia.main.view.maintechnology.threekeypoint.util.ThreePointMarkerView;
import com.tepia.main.view.maintechnology.yunwei.presenter.YunWeiJiShuContract;
import com.tepia.main.view.maintechnology.yunwei.presenter.YunWeiJiShuPresenter;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.github.mikephil.charting.utils.ColorTemplate.rgb;

/**
  * Created by      Android studio
  *
  * @author :wwj (from Center Of Wuhan)
  * Date    :2019/2/12
  * Version :1.0
  * 功能描述 :  三个重点雨情历史数据
 **/

public class RainHistoryDataActivity extends BaseActivity {
    private RecyclerView rvWater;
    private TextView tvStartDate;
    private TextView tvEndDate;
    private String startDate;
    private String endDate;
    private String reservoirId;
    private TextView tvTitle;
    private LinearLayout llChart;
    private Bundle extras;
    private Intent intent;
    private String reservoirName;
    private ImageView showChartIv;
    private BarChart mBarChart;
    private YunWeiJiShuPresenter mPresenter;
    private RecyclerView rvRain;
    private RainHistoryListAdapter rainListAdapter;
    private List<RainHistoryResponse.DataBean.StPptnRsBean> rainDataList ;

    @Override
    public int getLayoutId() {
        return R.layout.activity_history_data_rain;
    }

    @Override
    public void initView() {
        setCenterTitle("历史数据");
        showBack();
        tvStartDate = findViewById(R.id.tv_start_date);
        tvEndDate = findViewById(R.id.tv_end_date);
        tvTitle = findViewById(R.id.tv_title);
        llChart = findViewById(R.id.ll_chart);
        showChartIv = findViewById(R.id.showChartIv);
        llChart.setOnClickListener(v -> { });
        mBarChart = findViewById(R.id.rain_bar_chart);
        rvRain = findViewById(R.id.rv_rain);
        initStartDate();
        initEndDate();
        if (extras!=null&&extras.containsKey("reservoirName") && extras.containsKey("reservoirId")){
            reservoirName = intent.getStringExtra("reservoirName");
            reservoirId = intent.getStringExtra("reservoirId");
            tvTitle.setText(reservoirName + "雨情数据");
        }
        initRecyclerView();
        initBarChart();
        initSearch();
        showChartIv.setOnClickListener(v -> {
            if (llChart.getVisibility()==View.GONE){
                llChart.setVisibility(View.VISIBLE);
            }else {
                llChart.setVisibility(View.GONE);
            }
        });
        initResponse();
    }

    private void initResponse() {
        if (!NetUtil.isNetworkConnected(Utils.getContext())) {
            ToastUtils.shortToast(R.string.no_network);
        } else {
            if (mPresenter != null) {
                startDate = tvStartDate.getText().toString();
                if (startDate.length() > 0) {
                    startDate = startDate + " 00:00:00";
                }
                endDate = tvEndDate.getText().toString();
                if (endDate.length() > 0) {
                    endDate = endDate + " 23:59:59";
                }
                mPresenter.getRainHistoryResponse(reservoirId,startDate,endDate,"day",true);
            }
        }
    }

    private void initRecyclerView() {
        //设置recycle
        rainDataList = new ArrayList<>();
        rainListAdapter = new RainHistoryListAdapter(R.layout.threepoint_jishu_item_rain, rainDataList,reservoirName);
        rvRain.setLayoutManager(new LinearLayoutManager(this));
        rvRain.setAdapter(rainListAdapter);
    }

    private void initBarChart() {
        ThreePointCharUtil.initBarChart(mBarChart);
        ThreePointMarkerView myMarkerView = new ThreePointMarkerView(this, R.layout.appraisal_custom_marker_view);
        mBarChart.setMarker(myMarkerView);
//        //模拟数据
//        List<String> xValues = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            xValues.add("第"+i+"条");
//        }
//        List<Float> yValues = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            float mult = (20 + 1);
//            yValues.add ((float) (Math.random() * mult) + mult / 3);
//        }
//        ThreePointCharUtil.setBarCharData(mBarChart,yValues,xValues,"总分数",rgb("#2ecc71"));
    }

    @Override
    public void initData() {
        intent = getIntent();
        extras = intent.getExtras();
        mPresenter = new YunWeiJiShuPresenter();
        mPresenter.attachView(new YunWeiJiShuContract.View<RainHistoryResponse>() {
            @Override
            public void success(RainHistoryResponse rainHistoryResponse) {
                rainDataList.clear();
                RainHistoryResponse.DataBean data = rainHistoryResponse.getData();
                if (data!=null){
                    List<RainHistoryResponse.DataBean.StPptnRsBean> rainList = data.getStPptnRs();
                    if (rainList!=null&&rainList.size()>0){
                        rainDataList.addAll(rainList);
                        rainListAdapter.notifyDataSetChanged();
                        List<String> xValues = new ArrayList<>();
                        List<Float> yValues = new ArrayList<>();
                        for (int i = 0; i < rainList.size(); i++) {
                            RainHistoryResponse.DataBean.StPptnRsBean item = rainList.get(i);
                            String tm = item.getTm();
                            try{
                                String[] split = tm.split(" ");
                                if (split.length>0){
                                    tm = split[0];
                                }
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            xValues.add(tm);
                            yValues.add ((float) item.getDrp());
                        }
                        ThreePointCharUtil.setBarCharData(mBarChart,yValues,xValues,"雨量",rgb("#2ecc71"));
                    }else {
                        mBarChart.setData(null);
                        mBarChart.notifyDataSetChanged();
                        mBarChart.invalidate();
                        rainListAdapter.setEmptyView(EmptyLayoutUtil.show("暂无数据"));
                        rainListAdapter.notifyDataSetChanged();
                    }
                }else {
                    mBarChart.setData(null);
                    mBarChart.notifyDataSetChanged();
                    mBarChart.invalidate();
                    rainListAdapter.setEmptyView(EmptyLayoutUtil.show("暂无数据"));
                    rainListAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void failure(String msg) {
                rainDataList.clear();
                rainListAdapter.setEmptyView(EmptyLayoutUtil.show(msg));
                mBarChart.setData(null);
                mBarChart.notifyDataSetChanged();
                mBarChart.invalidate();
                ToastUtils.shortToast(msg);
            }

            @Override
            public Context getContext() {
                return null;
            }
        });
    }

    private void initSearch() {
        Button btConfirm = findViewById(R.id.bt_confirm);
        btConfirm.setOnClickListener(v -> {
            startDate = tvStartDate.getText().toString();
            if (startDate.length() > 0) {
                startDate = startDate + " 00:00:00";
            }
            endDate = tvEndDate.getText().toString();
            if (endDate.length() > 0) {
                endDate = endDate + " 23:59:59";
            }
            try {
                long days = daysOfTwoDate(startDate, endDate);
//                LogUtil.i("相差天数:"+days);
                if (days>40){
                    ToastUtils.longToast("时间跨度不能超过40天");
                }else if(days<0){
                    ToastUtils.longToast("开始日期大于结束日期");
                }else {
                    mPresenter.getRainHistoryResponse(reservoirId,startDate,endDate,"day",true);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            });
    }

    private TimePickerDialogUtil timePickerDialogUtil;
    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    private void initStartDate() {
        long fiveYears = 10L * 365 * 1000 * 60 * 60 * 24L;
        timePickerDialogUtil = new TimePickerDialogUtil(this, sf);
        //得到一个Calendar的实例
        Calendar ca = Calendar.getInstance();
        //设置时间为当前时间
        ca.setTime(new Date());
        //昨天减1
        ca.add(Calendar.DATE, -7);
        Date lastDate = ca.getTime();
        tvStartDate.setText(dateToStrLong(lastDate));
        tvStartDate.setOnClickListener(v -> {
            String current = (String) tvStartDate.getText();
            long currentLong = 0;
            if (current != null && current.length() > 0) {
                currentLong = strToLong(current);
            }
            timePickerDialogUtil.initTimePickerSetStartAndEnd((timePickerView, millseconds) -> {
                String text = timePickerDialogUtil.getDateToString(millseconds);
                tvStartDate.setText(text);
            }, Type.YEAR_MONTH_DAY, System.currentTimeMillis() - fiveYears, System.currentTimeMillis(), R.color.color_load_blue);
            if (timePickerDialogUtil.startDialog != null) {
                timePickerDialogUtil.startDialog = null;
            }
            if (currentLong != 0) {
                timePickerDialogUtil.builder.setCurrentMillseconds(currentLong);
            }
            timePickerDialogUtil.builder.setTitleStringId("请选择开始日期");
            timePickerDialogUtil.startDialog = timePickerDialogUtil.builder.build();
            timePickerDialogUtil.startDialog.show(getSupportFragmentManager(), "all");
        });
    }

    private void initEndDate() {
        long fiveYears = 10L * 365 * 1000 * 60 * 60 * 24L;
        timePickerDialogUtil = new TimePickerDialogUtil(this, sf);
        //得到一个Calendar的实例
        Calendar ca = Calendar.getInstance();
        //设置时间为当前时间
        ca.setTime(new Date());
        //昨天减1
//        ca.add(Calendar.DATE, -1);
        Date lastDate = ca.getTime();
        tvEndDate.setText(dateToStrLong(lastDate));
        tvEndDate.setOnClickListener(v -> {
            String current = (String) tvEndDate.getText();
            long currentLong = 0;
            if (current != null && current.length() > 0) {
                currentLong = strToLong(current);
            }
            timePickerDialogUtil.initTimePickerSetStartAndEnd((timePickerView, millseconds) -> {
                String text = timePickerDialogUtil.getDateToString(millseconds);
                tvEndDate.setText(text);
            }, Type.YEAR_MONTH_DAY, System.currentTimeMillis() - fiveYears, System.currentTimeMillis(), R.color.color_load_blue);
            if (timePickerDialogUtil.startDialog != null) {
                timePickerDialogUtil.startDialog = null;
            }
            if (currentLong != 0) {
                timePickerDialogUtil.builder.setCurrentMillseconds(currentLong);
            }
            timePickerDialogUtil.builder.setTitleStringId("请选择结束日期");
            timePickerDialogUtil.startDialog = timePickerDialogUtil.builder.build();
            timePickerDialogUtil.startDialog.show(getSupportFragmentManager(), "all");
        });
    }

    public static long strToLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        //继续转换得到秒数的long型
        long lTime = strtodate.getTime();
        return lTime;
    }

    public static String dateToStrLong(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    private long daysOfTwoDate(String startDate,String endDate) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        //跨年不会出现问题
        //如果时间为：2016-03-18 11:59:59 和 2016-03-19 00:00:01的话差值为 0
        Date fDate=sdf.parse(startDate);
        Date oDate=sdf.parse(endDate);
        long days=(oDate.getTime()-fDate.getTime())/(1000*3600*24);
        return days;
    }


    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {

    }
}
