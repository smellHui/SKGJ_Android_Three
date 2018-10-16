package com.tepia.main.view.mainworker.homepage;

import android.graphics.Color;
import android.graphics.drawable.Drawable;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.tepia.base.utils.ResUtils;
import com.tepia.main.ConfigConsts;
import com.tepia.main.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2018-09-17
 * Time            :       14:56
 * Version         :       1.0
 * 功能描述        :       水位库容曲线 图标 工具类
 **/
public class ChartUtils {

    public static int dayValue = 0;
    public static int weekValue = 1;
    public static int monthValue = 2;

    /**
     * 初始化图表
     *
     * @param chart 原始图表
     * @return 初始化后的图表
     */
    public static LineChart initChart(LineChart chart) {
        // 不显示数据描述
        chart.getDescription().setEnabled(false);
        // 没有数据的时候，显示“暂无数据”
        chart.setNoDataText("暂无数据");
        // 不显示表格颜色
        chart.setDrawGridBackground(false);
        // 不可以缩放
        chart.setScaleEnabled(true);
        // 不显示y轴右边的值
        chart.getAxisRight().setEnabled(false);
        // 不显示图例
        Legend legend = chart.getLegend();
        legend.setEnabled(false);
        // 向左偏移15dp，抵消y轴向右偏移的30dp
        chart.setExtraLeftOffset(-15);
        chart.setExtraBottomOffset(10);

        XAxis xAxis = chart.getXAxis();
        // 不显示x轴
        xAxis.setDrawAxisLine(true);
        // 设置x轴数据的位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.parseColor("#999999"));
        xAxis.setTextSize(12);
        xAxis.setGridColor(Color.parseColor("#30000000"));
        // 设置x轴数据偏移量
        xAxis.setYOffset(8);
        // 不从X轴发出纵向直线
        xAxis.setDrawGridLines(false);
        YAxis yAxis = chart.getAxisLeft();
        // 不显示y轴
        yAxis.setDrawAxisLine(false);
        // 设置y轴数据的位置
        yAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        // 不从y轴发出横向直线
        yAxis.setDrawGridLines(true);

        yAxis.setTextColor(Color.parseColor("#999999"));
        yAxis.setTextSize(12);
        // 设置y轴数据偏移量
        yAxis.setXOffset(30);
        yAxis.setYOffset(0);
        yAxis.setAxisMinimum(0);

        //Matrix matrix = new Matrix();
        // x轴缩放1.5倍
        //matrix.postScale(1.5f, 1f);
        // 在图表动画显示之前进行缩放
        //chart.getViewPortHandler().refresh(matrix, chart, false);
        // x轴执行动画
        //chart.animateX(2000);
        chart.invalidate();
        return chart;
    }


    /**
     * 设置图表数据
     *
     * @param chart  图表
     * @param values 数据
     */
    public static void setChartData(LineChart chart, List<Entry> values) {

        LineDataSet lineDataSet;

        if (chart.getData() != null && chart.getData().getDataSetCount() > 0 && values != null && values.size() != 0) {
            lineDataSet = (LineDataSet) chart.getData().getDataSetByIndex(0);
            lineDataSet.setValues(values);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            lineDataSet = new LineDataSet(values, "");
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
            Drawable drawable = ResUtils.getResources().getDrawable(R.drawable.fade_blue);
            lineDataSet.setFillDrawable(drawable);
            LineData data = new LineData(lineDataSet);
            chart.setData(data);
            chart.invalidate();
        }
    }

    /**
     * 更新图表
     *
     * @param chart     图表
     * @param values    数据
     * @param valueType 数据类型
     */
    public static void notifyDataSetChanged(LineChart chart, List<Entry> values,
                                            final int valueType) {
        chart.getXAxis().setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
//                if (valueType == 4) {
//                    return xValuesProcess(values)[(int) value];
//                }


                return "" + (int) value;
            }
        });
        if (chart.getLineData() != null) {
            chart.getLineData().clearValues();
            chart.clear();
            chart.invalidate();
        }
        if (values != null && values.size() > 0) {
            ArrayList<Float> xlist = new ArrayList<Float>();
            ArrayList<Float> ylist = new ArrayList<Float>();
            for (Entry entry : values) {
                xlist.add(entry.getX());
                ylist.add(entry.getY());
            }

            YAxis yAxis = chart.getAxisLeft();
            XAxis xAxis = chart.getXAxis();

            yAxis.setAxisMaximum(Collections.max(ylist));//y轴最大值
            yAxis.setAxisMinimum(Collections.min(ylist));//y轴最小值

            xAxis.setAxisMaximum(Collections.max(xlist));//y轴最大值
            xAxis.setAxisMinimum(0);//y轴最小值
            chart.invalidate();
            setChartData(chart, values);
        } else {
            if (chart.getLineData() != null) {
                chart.getLineData().clearValues();
                chart.clear();
                chart.invalidate();
            }
        }

    }

    /**
     * x轴数据处理
     *
     * @param valueType 数据类型
     * @return x轴数据
     */
    private static String[] xValuesProcess(int valueType) {
        String[] week = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};

        if (valueType == dayValue) { // 今日
            String[] dayValues = new String[7];
            long currentTime = System.currentTimeMillis();
            for (int i = 6; i >= 0; i--) {
                dayValues[i] = TimeUtils.dateToString(currentTime, TimeUtils.dateFormat_day);
                currentTime -= (3 * 60 * 60 * 1000);
            }
            return dayValues;

        } else if (valueType == weekValue) { // 本周
            String[] weekValues = new String[7];
            Calendar calendar = Calendar.getInstance();
            int currentWeek = calendar.get(Calendar.DAY_OF_WEEK);

            for (int i = 6; i >= 0; i--) {
                weekValues[i] = week[currentWeek - 1];
                if (currentWeek == 1) {
                    currentWeek = 7;
                } else {
                    currentWeek -= 1;
                }
            }
            return weekValues;

        } else if (valueType == monthValue) { // 本月
            String[] monthValues = new String[7];
            long currentTime = System.currentTimeMillis();
            for (int i = 6; i >= 0; i--) {
                monthValues[i] = TimeUtils.dateToString(currentTime, TimeUtils.dateFormat_month);
                currentTime -= (4 * 24 * 60 * 60 * 1000);
            }
            return monthValues;
        }
        return new String[]{};
    }

    public static void setDesc(LineChart mLineChart, String xUnitStr) {
        // 数据描述
        Description description = new Description();
        description.setText(xUnitStr);
        description.setTextColor(ConfigConsts.colortext);

        mLineChart.setDescription(description);
    }

    public static void clearData(LineChart chart) {
        if (chart.getLineData() != null) {
            chart.getLineData().clearValues();
            chart.clear();
            chart.invalidate();
        }
    }
}
