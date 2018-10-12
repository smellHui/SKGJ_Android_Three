package com.tepia.main.view.maincommon.reservoirs.detail;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.main.R;
import com.tepia.main.model.detai.StRiverRBean;
import com.tepia.main.model.reserviros.CapacityBean;
import com.tepia.main.model.reserviros.FloodBean;
import com.tepia.main.model.user.homepageinfo.HomeGetReservoirInfoBean;
import com.tepia.main.view.main.detail.LineChartEntity;
import com.tepia.main.view.maincommon.reservoirs.ReservoirsFragment;
import com.tepia.main.view.maincommon.reservoirs.mvpreservoir.ReserviorContract;
import com.tepia.main.view.maincommon.reservoirs.mvpreservoir.ReserviorPresent;
import com.tepia.main.view.mainworker.homepage.ChartUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by      Android studio
 *
 * @author :       ly(from Center Of Wuhan)
 * Date            :       2018-9-18
 * Version         :       1.0
 * 功能描述         :       水位库容曲线页面
 **/
public class CapacityActivity extends MVPBaseActivity<ReserviorContract.View,ReserviorPresent> implements  ReserviorContract.View<CapacityBean> ,OnChartGestureListener, OnChartValueSelectedListener {
    private LineChart mLineChart;
    private LineChartEntity lineChartEntity;


    @Override
    public int getLayoutId() {
        return R.layout.activity_capacity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCenterTitle("水库库容曲线");
        showBack();
        mLineChart = findViewById(R.id.line_chart);
        mLineChart.setOnChartGestureListener(this);
        mLineChart.setOnChartValueSelectedListener(this);
        ChartUtils.initChart(mLineChart);
        ChartUtils.setDesc(mLineChart,"库容(万m³)");
        lineChartEntity = new LineChartEntity(mLineChart,"库容(万m³)");
//        mLineChart.setOnChartGestureListener(this);
//        mLineChart.setOnChartValueSelectedListener(this);
        if (mLineChart != null) {
            mLineChart.clear();
            //重设所有缩放和拖动，使图表完全适合它的边界（完全缩小）。
            mLineChart.fitScreen();
        }
//        initLineChart();
        String reservoirId = getIntent().getStringExtra(ReservoirsFragment.RESERVOIRId);
        String reservoirName = getIntent().getStringExtra(ReservoirsFragment.RESERVOIRNAME);
        TextView nameTv = findViewById(R.id.nameTv);
        nameTv.setText(reservoirName);
        mPresenter.getStorageCurveByReservoir(reservoirId);
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
//        lineChartEntity.setDataOfCapacity("", dataBeans, granularity);
        ChartUtils.notifyDataSetChanged(mLineChart,getData(dataBeans),4);
    }

    private List<Entry> getData(List<HomeGetReservoirInfoBean.StorageCapacityBean> storageCapacity) {
        List<Entry> values = new ArrayList<>();
        for (HomeGetReservoirInfoBean.StorageCapacityBean bean : storageCapacity) {
            values.add(new Entry( bean.getStorageCapacity(),bean.getWaterLevel()));
        }
        return values;
    }


    @Override
    public void success(CapacityBean data) {
        refreshChart(data.getData());
//        ChartUtils.notifyDataSetChanged(mLineChart, getData(data.getData().getData()), ChartUtils.dayValue);
    }

    @Override
    public void failure(String msg) {

    }

    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

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
