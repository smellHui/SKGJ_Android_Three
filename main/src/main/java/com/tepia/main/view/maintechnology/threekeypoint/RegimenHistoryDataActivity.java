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

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.jzxiang.pickerview.data.Type;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.base.utils.NetUtil;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.utils.Utils;
import com.tepia.main.R;
import com.tepia.main.model.jishu.threepoint.WaterHistoryResponse;
import com.tepia.main.utils.EmptyLayoutUtil;
import com.tepia.main.utils.TimePickerDialogUtil;
import com.tepia.main.view.maintechnology.threekeypoint.adapter.WaterHistoryListAdapter;
import com.tepia.main.view.maintechnology.threekeypoint.util.MyLineChartMarkView;
import com.tepia.main.view.maintechnology.threekeypoint.util.ThreePointCharUtil;
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

/**
 * Created by      Android studio
 *
 * @author :wwj (from Center Of Wuhan)
 * Date    :2019/2/12
 * Version :1.0
 * 功能描述 :  三个重点水情历史数据
 **/

public class RegimenHistoryDataActivity extends BaseActivity {
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
    private LineChart mLineChart;
    private YunWeiJiShuPresenter mPresenter;
    private List<WaterHistoryResponse.DataBean> waterDataList;
    private WaterHistoryListAdapter waterListAdapter;
    private TextView tvKurong;

    @Override
    public int getLayoutId() {
        return R.layout.activity_history_data_regimen;
    }

    @Override
    public void initView() {
        setCenterTitle("历史数据");
        showBack();
        tvStartDate = findViewById(R.id.tv_start_date);
        tvEndDate = findViewById(R.id.tv_end_date);
        tvKurong = findViewById(R.id.tv_kurong);
        tvTitle = findViewById(R.id.tv_title);
        llChart = findViewById(R.id.ll_chart);
        showChartIv = findViewById(R.id.showChartIv);
        llChart.setOnClickListener(v -> {
        });
        rvWater = findViewById(R.id.rv_water);
        if (extras != null && extras.containsKey("reservoirName") && extras.containsKey("reservoirId")) {
            reservoirName = intent.getStringExtra("reservoirName");
            reservoirId = intent.getStringExtra("reservoirId");
            tvTitle.setText(reservoirName + "水情数据");
        }
        mLineChart = findViewById(R.id.regimen_line_chart);
        initStartDate();
        initEndDate();
        initLineChart();
        initSearch();
        initRecyclerView();
        showChartIv.setOnClickListener(v -> {
            if (llChart.getVisibility() == View.GONE) {
                llChart.setVisibility(View.VISIBLE);
            } else {
                llChart.setVisibility(View.GONE);
            }
        });
        initResponse();
    }

    private void initRecyclerView() {
        waterDataList = new ArrayList<>();
        waterListAdapter = new WaterHistoryListAdapter(R.layout.threepoint_jishu_item_water, waterDataList);
        rvWater.setLayoutManager(new LinearLayoutManager(this));
        rvWater.setAdapter(waterListAdapter);
    }

    private void initLineChart() {
        ThreePointCharUtil.initLineChartTwo(mLineChart);
        //这里我模拟一些数据
//        List<String> xValues = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            xValues.add("第" + i + "条");
//        }
//        List<Float> yValues = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            float mult = (40 + 1);
//            yValues.add((float) (Math.random() * mult) + mult / 3);
//        }
//        List<Float> yValues2 = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            float mult = (10 + 1);
//            yValues2.add((float) (Math.random() * mult) + mult / 3);
//        }
//
//        ThreePointCharUtil.setLineData(yValues, xValues, yValues2, mLineChart, "水位", "库容");
//        MyLineChartMarkView mv = new MyLineChartMarkView(this, mLineChart.getXAxis().getValueFormatter());
//        mv.setChartView(mLineChart);
//        mLineChart.setMarker(mv);
//        mLineChart.invalidate();
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
                mPresenter.getWaterHistoryResponse(reservoirId, startDate, endDate, true);
            }
        }
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
                if (days > 7) {
                    ToastUtils.longToast("时间跨度不能超过7天");
                }else if(days<0){
                    ToastUtils.longToast("开始日期大于结束日期");
                } else {
                    mPresenter.getWaterHistoryResponse(reservoirId, startDate, endDate, true);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        });
    }

    @Override
    public void initData() {
        intent = getIntent();
        extras = intent.getExtras();
        mPresenter = new YunWeiJiShuPresenter();
        mPresenter.attachView(new YunWeiJiShuContract.View<WaterHistoryResponse>() {
            @Override
            public void success(WaterHistoryResponse waterHistoryResponse) {
                llChart.setVisibility(View.GONE);
                waterDataList.clear();
                List<WaterHistoryResponse.DataBean> data = waterHistoryResponse.getData();
                if (data != null && data.size() > 0) {
                    waterDataList.addAll(data);
                    waterListAdapter.notifyDataSetChanged();
                    List<String> xValues = new ArrayList<>();
                    List<Float> yValues = new ArrayList<>();
                    List<Float> yValues2 = new ArrayList<>();
                    for (int i = 0; i < data.size(); i++) {
                        xValues.add(data.get(data.size()-i-1).getTm());
                        yValues.add((float) data.get(data.size()-i-1).getRz());
                        float w = (float) data.get(data.size()-i-1).getW();
                        yValues2.add(w);
                    }
                    if (data.get(0).getW()==0){
                        tvKurong.setVisibility(View.GONE);
                    }else {
                        tvKurong.setVisibility(View.VISIBLE);
                    }
                    setLineChartData(xValues,yValues,yValues2);
                } else {
                    waterListAdapter.setEmptyView(EmptyLayoutUtil.show("暂无数据"));
                    waterListAdapter.notifyDataSetChanged();
                    mLineChart.setData(null);
                    mLineChart.notifyDataSetChanged();
                    mLineChart.invalidate();
                }
            }

            @Override
            public void failure(String msg) {
                mLineChart.setData(null);
                mLineChart.notifyDataSetChanged();
                mLineChart.invalidate();
                waterListAdapter.setEmptyView(EmptyLayoutUtil.show("暂无数据"));
                waterListAdapter.notifyDataSetChanged();
                ToastUtils.shortToast(msg);
            }

            @Override
            public Context getContext() {
                return null;
            }
        });
    }

    private void setLineChartData(List<String> xValues, List<Float> yValues, List<Float> yValues2) {
////        List<String> xValues = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            xValues.add("第"+i+"条");
//        }
////        List<Float> yValues = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            float mult = (40 + 1);
//            yValues.add ((float) (Math.random() * mult) + mult / 3);
//        }
////        List<Float> yValues2 = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            float mult = (10 + 1);
//            yValues2.add ((float) (Math.random() * mult) + mult / 3);
//        }

        ThreePointCharUtil.setLineData(yValues, xValues, yValues2, mLineChart, "水位", "库容");
        MyLineChartMarkView mv = new MyLineChartMarkView(this, mLineChart.getXAxis().getValueFormatter());
        mv.setChartView(mLineChart);
        mLineChart.setMarker(mv);
        mLineChart.invalidate();
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

    private long daysOfTwoDate(String startDate, String endDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //跨年不会出现问题
        //如果时间为：2016-03-18 11:59:59 和 2016-03-19 00:00:01的话差值为 0
        Date fDate = sdf.parse(startDate);
        Date oDate = sdf.parse(endDate);
        long days = (oDate.getTime() - fDate.getTime()) / (1000 * 3600 * 24);
        return days;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {

    }
}
