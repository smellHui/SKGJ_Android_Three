package com.tepia.main.view.main.detail;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
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
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.ResUtils;
import com.tepia.base.utils.Utils;
import com.tepia.main.ConfigConsts;
import com.tepia.main.ConfigConsts;
import com.tepia.main.R;
import com.tepia.main.model.detai.RainfullBean;
import com.tepia.main.model.detai.StRiverRBean;
import com.tepia.main.model.detai.WaterlevelBean;
import com.tepia.main.model.map.RainfallResponse;
import com.tepia.main.model.reserviros.CapacityBean;
import com.tepia.main.model.user.homepageinfo.HomeGetReservoirInfoBean;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 线状图
 * @author liying
 * @date 2018/7/24
 */

public class LineChartEntity {

    public LineChart mLineChart;
    // 时间点
    private float[] data1;
    private String[] data3;
    // 时间点总数
    private int listCount;
    public LineChartEntity(LineChart lineChart){
        this.mLineChart = lineChart;
        initLineChart(Utils.getContext().getString(R.string.time_unit));
    }

    public LineChartEntity(LineChart lineChart,String xUnitStr){
        this.mLineChart = lineChart;
        initLineChart(xUnitStr);
    }



    private void initLineChart(String xUnitStr) {

        mLineChart.setDrawGridBackground(false);
        mLineChart.setBackgroundColor(Color.WHITE);
//        mLineChart.setOnChartValueSelectedListener(getActivity());
//        mLineChart.setPadding(0, 0, 0, 0);
        // 描述文字
        mLineChart.getDescription().setEnabled(false);
        // 触摸手势
        mLineChart.setTouchEnabled(true);
        // 阻尼系数
        mLineChart.setDragDecelerationFrictionCoef(0.9f);
        // 拖拽
        mLineChart.setDragEnabled(true);
        // 缩放
//        mLineChart.setScaleEnabled(false);
        //启用X轴上的缩放
        mLineChart.setScaleXEnabled(true);
        mLineChart.setScaleYEnabled(true);
        // 设置双击,两指拉伸等交互的开关
        mLineChart.setDoubleTapToZoomEnabled(true);
        // 是否显示表格颜色
        mLineChart.setDrawGridBackground(false);
        mLineChart.setHighlightPerDragEnabled(true);
//        mLineChart.setNoDataTextColor(R.color.colorPrimary);
//        mLineChart.setNoDataText("暂无数据，下拉刷新试试吧");
        // 不显示y轴右边的值
        mLineChart.getAxisRight().setEnabled(false);
        // if disabled, scaling can be done on x- and y-axis separately
        mLineChart.setPinchZoom(true);
        // 背景颜色
//        mLineChart.getResources().getColor(R.color.top_background);
        //设置无数据时显示
        mLineChart.setNoDataText(Utils.getContext().getString(R.string.empty_tv));
        mLineChart.setNoDataTextColor(ConfigConsts.colortext);
        //x轴字旋转角度
        //mLineChart.getXAxis().setLabelRotationAngle(-60);
       /* MyMarkerView mv = new MyMarkerView(getActivity(), R.layout.custom_marker_view,data3);
        mv.setChartView(mLineChart); // For bounds control
        mLineChart.setMarker(mv); // Set the marker to the chart*/
        // 数据描述
        Description description = new Description();
        description.setText(xUnitStr);
        description.setTextColor(ConfigConsts.colortext);

        mLineChart.setDescription(description);

    }

    /**
     * 流量站
     * @param type
     * @param stRiverRsBeanList
     * @param granularity
     */
    public void setDataOfLineChartLL(String type, List<StRiverRBean.DataBean.StRiverRsBean> stRiverRsBeanList, float granularity) {
        // 填入数据
        setLLDate(stRiverRsBeanList,type);
        setLineChartData(granularity,stRiverRsBeanList.size());

    }

    /**
     * 雨量站
     * @param type
     * @param stRiverRsBeanList
     * @param granularity
     */
    public void setDataOfLineChartRainfullBean(String type, List<RainfullBean.DataBean.StPptnRsBean> stRiverRsBeanList, float granularity) {
        // 填入数据
        setRainfullBeanDate(stRiverRsBeanList,type);
        setLineChartData(granularity,stRiverRsBeanList.size());

    }

    /**
     * 水位站
     * @param type
     * @param stRsvrRSBeanList
     * @param granularity
     */
    public void setDataOfLineChartWaterlevel(String type, List<WaterlevelBean.DataBean.StRsvrRSBean> stRsvrRSBeanList, float granularity) {
        // 填入数据
        setWaterBeanDate(stRsvrRSBeanList,type);
        setLineChartData(granularity,stRsvrRSBeanList.size());

    }

    /**
     * 水位库容曲线
     * @param granularity
     */
    public void setDataOfCapacity(String type,List<HomeGetReservoirInfoBean.StorageCapacityBean> dataBeans, float granularity){
        setCapacityBeanDate(dataBeans,"");
        setLineChartData(granularity,dataBeans.size());

    }

    private void setLineChartData(float granularity,int lablecount){
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
        xAxis.setDrawGridLines(false);//
//        xAxis.setDrawGridLines(false);
//        xAxis.setDrawAxisLine(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(granularity); // 间隔几个显示一次标签
        xAxis.setGranularityEnabled(true);
        xAxis.setLabelCount(lablecount);//设置x轴显示的标签个数
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



    private String getTm(String tm,String timeType) {
        if (tm != null && tm.length() > 16) {
            xVals.add(tm);
            if (timeType.equals(ConfigConsts.TimeType[0])) {
                //时 time
                tm = tm.substring(10, 16);
            } else if(ConfigConsts.TimeType[1].equals(timeType)){
                //日 day
                tm = tm.substring(5, 10);
            }else if(ConfigConsts.TimeType[2].equals(timeType)){
                //月 month
                tm = tm.substring(0, 10);
            }else if(ConfigConsts.TimeType[3].equals(timeType)){
                //年
                tm = tm.substring(0, 10);
            }
            return tm;
        }
        return "";
    }

    private ArrayList<String> xVals = new ArrayList<>();

    /**
     * 流量站
     * @param databean
     * @param timeType
     */
    private void setLLDate(List<StRiverRBean.DataBean.StRiverRsBean> databean,String timeType){
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

            double qstr = databean.get(i).getQ();

            data1[i] = (float) qstr;
            String tm = databean.get(i).getTm();

            tm = getTm(tm,timeType);
            data3[i] = tm;


        }
        setData();

    }

    /**
     * 雨量站
     * @param databean
     * @param timeType
     */
    private void setRainfullBeanDate(List<RainfullBean.DataBean.StPptnRsBean> databean, String timeType){
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

            double qstr = databean.get(i).getDrp();

            data1[i] = (float) qstr;
            String tm = databean.get(i).getTm();

            tm = getTm(tm,timeType);
            data3[i] = tm;


        }
        setData();

    }

    private void setWaterBeanDate(List<WaterlevelBean.DataBean.StRsvrRSBean> databean, String timeType){
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

            double qstr = databean.get(i).getRz();

            data1[i] = (float) qstr;
            String tm = databean.get(i).getTm();

            tm = getTm(tm,timeType);
            data3[i] = tm;


        }
        setData();

    }

    /**
     * 水位库容曲线
     * @param databean
     * @param timeType
     * creat on 2018-9-26
     */
    private void setCapacityBeanDate(List<HomeGetReservoirInfoBean.StorageCapacityBean> databean, String timeType){
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

            double qstr = databean.get(i).getStorageCapacity();

            data1[i] = (float) qstr;
            String tm = String.valueOf(databean.get(i).getWaterLevel());
            data3[i] = tm;


        }
        setDataNew();

    }




    private void setData() {
//
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

    private void setDataNew() {
//
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
        LineDataSet lineDataSet, set2;
        if (mLineChart.getData() != null &&
                mLineChart.getData().getDataSetCount() > 0) {
            lineDataSet = (LineDataSet) mLineChart.getData().getDataSetByIndex(0);
            lineDataSet.setValues(yVals1);
            mLineChart.getData().notifyDataChanged();
            mLineChart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            lineDataSet = new LineDataSet(yVals1, "");

            // 设置曲线颜色
            lineDataSet.setColor(Color.parseColor("#35bcf3"));
            // 设置平滑曲线
            lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            // 显示坐标点的小圆点
            lineDataSet.setDrawCircles(true);
            // 不显示坐标点的数据
            lineDataSet.setDrawValues(false);
            // 不显示定位线
            lineDataSet.setHighlightEnabled(false);
            // 填充渐变色
            lineDataSet.setDrawFilled(true);
            Drawable drawable = ContextCompat.getDrawable(Utils.getContext(),R.drawable.fade_blue);
            lineDataSet.setFillDrawable(drawable);
            //点上文字的颜色
            LineData data = new LineData(lineDataSet,lineDataSet);
            data.setValueTextColor(Color.RED);
            data.setValueTextSize(9f);
            data.setDrawValues(false);

            // set data
            mLineChart.setData(data);

        }
    }

}
