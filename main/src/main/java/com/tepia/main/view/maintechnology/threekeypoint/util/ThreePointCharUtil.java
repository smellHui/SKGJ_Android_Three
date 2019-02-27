package com.tepia.main.view.maintechnology.threekeypoint.util;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.tepia.base.view.floatview.CollectionsUtil;
import com.tepia.main.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by      Android studio
 *
 * @author :wwj (from Center Of Wuhan)
 * Date    :2018/11/15
 * Version :1.0
 * 功能描述 :  图表工具
 **/

public class ThreePointCharUtil {

    /**
     * 初始化饼状图
     *
     * @param mPieChart
     */
    public static void initPieChar(PieChart mPieChart) {
        //饼状图
        mPieChart.setUsePercentValues(true);
        mPieChart.getDescription().setEnabled(false);
        mPieChart.setExtraOffsets(5, 10, 5, 5);
        //设置隐藏饼状图上文字
        mPieChart.setDrawSliceText(false);
        mPieChart.setDragDecelerationFrictionCoef(0.95f);
        //设置中间文件
//        mPieChart.setCenterText(generateCenterSpannableText());
//        mChart.setDrawCenterText(true);//饼状图中间可以添加文字
        mPieChart.setDrawHoleEnabled(true);
        mPieChart.setHoleColor(Color.WHITE);
        // 如果没有数据的时候，会显示这个，类似ListView的EmptyView
        mPieChart.setNoDataText("饼状图暂无数据");
        mPieChart.setTransparentCircleColor(Color.WHITE);
        mPieChart.setTransparentCircleAlpha(110);
        //半径
        mPieChart.setHoleRadius(58f);
        //半透明圆
        mPieChart.setTransparentCircleRadius(61f);
        //mChart.setHoleRadius(0)  //实心圆
        mPieChart.setDrawCenterText(true);

        mPieChart.setRotationAngle(0);
        // 触摸旋转
        mPieChart.setRotationEnabled(true);
        mPieChart.setHighlightPerTapEnabled(true);
        //显示百分比
        mPieChart.setUsePercentValues(false);
        mPieChart.animateY(1000, Easing.EasingOption.EaseInOutQuad); //设置动画
        Legend mLegend = mPieChart.getLegend();  //设置比例图
        mLegend.setOrientation(Legend.LegendOrientation.VERTICAL);
        mLegend.setPosition(Legend.LegendPosition.LEFT_OF_CHART_CENTER);  //左下边显示
        mLegend.setFormSize(20f);//比例块字体大小
        mLegend.setXEntrySpace(10f);//设置距离饼图的距离，防止与饼图重合
        mLegend.setYEntrySpace(10f);
        //设置比例块换行...
        mLegend.setWordWrapEnabled(true);
        mLegend.setDirection(Legend.LegendDirection.LEFT_TO_RIGHT);
//      mLegend.setForm(Legend.LegendForm.SQUARE);//设置比例块形状，默认为方块
//      mLegend.setEnabled(false);//设置禁用比例块

        //变化监听
//        mPieChart.setOnChartValueSelectedListener(this);
        //设置数据
        //模拟数据
//        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
//        entries.add(new PieEntry(3, "优秀    3人"));
//        entries.add(new PieEntry(5, "良好    5人"));
//        entries.add(new PieEntry(3, "及格    3人"));
//        entries.add(new PieEntry(1, "不及格  1人"));
//        setPieDate(mPieChart,entries,mLegend);
    }

    /**
     * 设置饼状图数据
     *
     * @param entries
     * @return
     */
    public static void setPieDate(PieChart mPieChart, ArrayList<PieEntry> entries, ArrayList<Integer> colors) {
        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new LargeValueFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        data.setDrawValues(true);
        mPieChart.setData(data);
        mPieChart.highlightValues(null);
        //刷新
        mPieChart.invalidate();
        mPieChart.animateXY(1000, 1000);
    }

    /**
     * 初始化柱状图
     *
     * @param mBarChart
     */
    public static void initBarChart(BarChart mBarChart) {
        mBarChart.getDescription().setEnabled(false);
        mBarChart.setMaxVisibleValueCount(40);
        // 扩展现在只能分别在x轴和y轴
        mBarChart.setPinchZoom(false);
        mBarChart.setDrawGridBackground(false);
        mBarChart.setDrawBarShadow(false);
        mBarChart.setDrawValueAboveBar(true);//设置柱状图上数据显示的位置
        mBarChart.setHighlightFullBarEnabled(false);
        //设置y轴显示动画
        mBarChart.animateY(1000);
        mBarChart.setNoDataText("柱状图暂无数据");
        // 改变y标签的位置
        YAxis leftAxis = mBarChart.getAxisLeft();
        leftAxis.setValueFormatter((value, axis) -> String.valueOf((int) value));
        leftAxis.setAxisMinimum(0f);
        //不显示y轴线条
//        leftAxis.setDrawAxisLine(false);
        mBarChart.getAxisRight().setEnabled(false);
        XAxis xLabels = mBarChart.getXAxis();
        xLabels.setPosition(XAxis.XAxisPosition.BOTTOM);
        //设置x轴显示坐标在对应柱状图下
        xLabels.setGranularity(1f);
        xLabels.setDrawGridLines(false);
        //设置x轴显示的名称的个数
        xLabels.setLabelCount(5);

        //设置缩放
        //设置是否可以触摸
        mBarChart.setTouchEnabled(true);
        //设置是否可以拖拽
        mBarChart.setDragEnabled(true);
        //设置是否可以缩放x和y
        mBarChart.setScaleYEnabled(false);
        //x轴缩放比例
//        mBarChart.setScaleX(1.5f);
        //设置双击屏幕放大
        mBarChart.setDoubleTapToZoomEnabled(false);
        mBarChart.setFitBars(true);//使两侧的柱图完全显示

        Legend l = mBarChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setFormSize(8f);
        l.setFormToTextSpace(4f);
        l.setXEntrySpace(30f);
    }

    /**
     * 单个柱状图
     *
     * @param barChart
     * @param yAxisValue
     * @param xValues
     * @param title
     * @param barColor
     */
    public static void setBarCharData(BarChart barChart, List<Float> yAxisValue, List<String> xValues, String title, Integer barColor) {
        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i = 0, n = yAxisValue.size(); i < n; ++i) {
            entries.add(new BarEntry(i, yAxisValue.get(i)));
        }
        BarDataSet set1;
        if (barChart.getData() != null && barChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) barChart.getData().getDataSetByIndex(0);
            set1.setValues(entries);
            barChart.getData().notifyDataChanged();
            barChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(entries, title);
            if (barColor == null) {
                set1.setColor(ContextCompat.getColor(barChart.getContext(), R.color.blue_user));//设置set1的柱的颜色
            } else {
                set1.setColor(barColor);
            }
            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);
            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setBarWidth(0.9f);
            data.setValueFormatter((value, entry, dataSetIndex, viewPortHandler) -> {
                return String.valueOf(value);
            });
            barChart.setData(data);
        }
        XAxis xLabels = barChart.getXAxis();
        //修改x轴显示文字  monthList.get((int)value%monthList.size())
        xLabels.setValueFormatter((value, axis) -> xValues.get((int) value % xValues.size()));
        //修改x轴显示文字  monthList.get((int)value%monthList.size())
        barChart.setFitBars(true);
        barChart.invalidate();
        barChart.animateY(1000);
    }

    /**
     * 一个柱显示多个数据
     *
     * @param mBarChart
     */
    private void initMoreBarChartDate(BarChart mBarChart) {
        ArrayList<String> monthList = new ArrayList<>();
        monthList.add("2018-09");
        monthList.add("2018-10");
        monthList.add("2018-11");
        monthList.add("2018-09");
        monthList.add("2018-10");
        monthList.add("2018-11");
        monthList.add("2018-09");
        monthList.add("2018-10");
        monthList.add("2018-11");
        XAxis xLabels = mBarChart.getXAxis();
        //修改x轴显示文字  monthList.get((int)value%monthList.size())
        xLabels.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return monthList.get((int) value % monthList.size());
            }
        });
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

        for (int i = 0; i < 8 + 1; i++) {
            float mult = (50 + 1);
            float val1 = (float) (Math.random() * mult) + mult / 3;
            float val2 = (float) (Math.random() * mult) + mult / 3;
            float val3 = (float) (Math.random() * mult) + mult / 3;
            yVals1.add(new BarEntry(i, new float[]{val1, val2, val3}));
        }

        BarDataSet set1;

        if (mBarChart.getData() != null &&
                mBarChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mBarChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            mBarChart.getData().notifyDataChanged();
            mBarChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "三年级一班期末考试");
            set1.setColors(getColors());
            set1.setStackLabels(new String[]{"及格", "优秀", "不及格"});

            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueFormatter(new IValueFormatter() {
                @Override
                public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                    //value就是显示柱状图的position  从0开始
                    return String.valueOf((int) value);
                }
            });
            data.setValueTextColor(Color.WHITE);
            //设置柱状图宽度
            data.setBarWidth(0.3f);
            mBarChart.setData(data);
        }
        mBarChart.setFitBars(true);
        mBarChart.invalidate();
        mBarChart.animateY(1000);
    }

    private int[] getColors() {
        int stacksize = 3;
        //有尽可能多的颜色每项堆栈值
        int[] colors = new int[stacksize];
        for (int i = 0; i < colors.length; i++) {
            colors[i] = ColorTemplate.MATERIAL_COLORS[i];
        }
        return colors;
    }


    /**
     * 初始化折线图
     *
     * @param mLineChart
     */
    public static void initLineChart(LineChart mLineChart) {
        mLineChart.getDescription().setEnabled(false);
        // 没有数据的时候，显示“暂无数据”
        mLineChart.setNoDataText("暂无数据");
        mLineChart.setMaxVisibleValueCount(40);
        // 扩展现在只能分别在x轴和y轴
        mLineChart.setPinchZoom(false);
        //后台绘制
        mLineChart.setDrawGridBackground(false);
        //设置描述文本
        mLineChart.getDescription().setEnabled(false);
        //设置支持触控手势
        mLineChart.setTouchEnabled(true);
        //设置缩放
        mLineChart.setDragEnabled(true);
        //设置推动
        mLineChart.setScaleEnabled(true);
        //如果禁用,扩展可以在x轴和y轴分别完成
        mLineChart.setPinchZoom(true);

        //x轴
        LimitLine llXAxis = new LimitLine(10f, "标记");
        //设置线宽
        llXAxis.setLineWidth(4f);
        //
//        llXAxis.enableDashedLine(10f, 10f, 0f);
        //设置
        llXAxis.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        llXAxis.setTextSize(10f);

        XAxis xAxis = mLineChart.getXAxis();
        xAxis.enableGridDashedLine(10f, 10f, 0f);
//        //设置x轴的最大值
//        xAxis.setAxisMaximum(100f);
//        //设置x轴的最小值
//        xAxis.setAxisMinimum(0f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        YAxis leftAxis = mLineChart.getAxisLeft();
        //重置所有限制线,以避免重叠线
        leftAxis.removeAllLimitLines();
        //y轴最大
        leftAxis.setAxisMaximum(200f);
        //y轴最小
        leftAxis.setAxisMinimum(0f);
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setDrawZeroLine(false);

        // 限制数据(而不是背后的线条勾勒出了上面)
        leftAxis.setDrawLimitLinesBehindData(true);

        //默认动画
        mLineChart.animateY(2500);
        //刷新
        //mChart.invalidate();

        // 得到这个文字
        Legend l = mLineChart.getLegend();

        // 修改文字 ...
        l.setForm(Legend.LegendForm.LINE);

        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setFormSize(8f);
        l.setFormToTextSpace(4f);
        l.setXEntrySpace(30f);

        //这里我模拟一些数据
        ArrayList<Entry> values = new ArrayList<Entry>();
        values.add(new Entry(5, 50));
        values.add(new Entry(10, 66));
        values.add(new Entry(15, 120));
        values.add(new Entry(20, 30));
        values.add(new Entry(35, 10));
        values.add(new Entry(40, 110));
        values.add(new Entry(45, 30));
        values.add(new Entry(50, 160));
        values.add(new Entry(100, 30));

        //设置数据
//        setData(values,mLineChart);
    }

    public static void setLineChartData(List<Float> yAxisValue, List<String> xValues,List<Float> yAxisValueTwo,LineChart mLineChar) {
        XAxis xLabels = mLineChar.getXAxis();
        //修改x轴显示文字  monthList.get((int)value%monthList.size())
        xLabels.setValueFormatter((value, axis) -> String.valueOf(xValues.get((int) value % xValues.size())));
        ArrayList<Entry> values = new ArrayList<Entry>();
        for (int i = 0; i < yAxisValue.size(); i++) {
            values.add(new Entry(i,yAxisValue.get(i)));
        }
        Float max = Collections.max(yAxisValue);
        Float min = Collections.min(yAxisValue);
        Float len = (max-min)/8;
        max = max+len;
        min = min-len;
        Float max2 = Collections.max(yAxisValueTwo);
        Float min2 = Collections.min(yAxisValueTwo);
        Float len2 = (max2-min2)/8;
        max2 = max2+len2;
        min2 = min2-len2;

        ArrayList<Entry> yVals2 = new ArrayList<Entry>();

        for (int i = 0; i < yAxisValueTwo.size(); i++) {
            yVals2.add(new Entry(i, yAxisValueTwo.get(i)));
        }
        YAxis leftAxis = mLineChar.getAxisLeft();
        //重置所有限制线,以避免重叠线
        leftAxis.removeAllLimitLines();
        //y轴最大
        leftAxis.setAxisMaximum(max);
        //y轴最小
        leftAxis.setAxisMinimum(min);
        YAxis rightAxis = mLineChar.getAxisRight();
        rightAxis.setTextColor(Color.RED);
        rightAxis.setAxisMaximum(max2);
        rightAxis.setAxisMinimum(min2);
        rightAxis.setDrawGridLines(false);
        rightAxis.setDrawZeroLine(false);
        rightAxis.setGranularityEnabled(false);

        //修改x轴显示文字  monthList.get((int)value%monthList.size())
        LineDataSet set1,set2;
        if (mLineChar.getData() != null && mLineChar.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) mLineChar.getData().getDataSetByIndex(0);
            set2 = (LineDataSet) mLineChar.getData().getDataSetByIndex(1);
            set1.setValues(values);
            set2.setValues(yVals2);
            mLineChar.getData().notifyDataChanged();
            mLineChar.notifyDataSetChanged();
        } else {
            // 创建一个数据集,并给它一个类型
            set1 = new LineDataSet(values, "水位");

            // 在这里设置虚线
//            set1.enableDashedLine(10f, 5f, 0f);
//            set1.enableDashedHighlightLine(10f, 5f, 0f);
            set1.setColor(Color.BLACK);
            set1.setCircleColor(Color.BLACK);
            set1.setLineWidth(1f);
            set1.setCircleRadius(3f);
            set1.setDrawCircleHole(false);
            set1.setValueTextSize(9f);
            set1.setDrawFilled(true);
            set1.setFormLineWidth(1f);
//            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormSize(15.f);

//            if (Utils.getSDKInt() >= 18) {
//                // 填充背景只支持18以上
//                //Drawable drawable = ContextCompat.getDrawable(this, R.mipmap.ic_launcher);
//                //set1.setFillDrawable(drawable);
//                Drawable drawable = ContextCompat.getDrawable(com.tepia.base.utils.Utils.getContext(), R.drawable.fade_blue);
//                set1.setFillDrawable(drawable);
//            } else {
//                set1.setFillColor(Color.BLACK);
//            }
//            ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
//            //添加数据集
//            dataSets.add(set1);
            set2 = new LineDataSet(yVals2, "库容");

            set2.setAxisDependency(YAxis.AxisDependency.LEFT);
            set2.setColor(ColorTemplate.getHoloBlue());
            set2.setCircleColor(Color.WHITE);
            set2.setLineWidth(2f);
            set2.setCircleRadius(3f);
            set2.setFillAlpha(65);
            set2.setFillColor(ColorTemplate.getHoloBlue());
            set2.setHighLightColor(Color.rgb(244, 117, 117));
            set2.setDrawCircleHole(false);
            //创建一个数据集的数据对象
            LineData data = new LineData(set1,set2);

            //设置数据
            mLineChar.setData(data);
        }
//        List<ILineDataSet> setsHorizontalCubic = mLineChar.getData().getDataSets();
//        if (setsHorizontalCubic!=null&&setsHorizontalCubic.size()>0){
//            for (ILineDataSet iSet : setsHorizontalCubic) {
//                LineDataSet set = (LineDataSet) iSet;
//                set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
//            }
//        }
        mLineChar.invalidate();
        mLineChar.animateY(2500);
    }


    public static void initLineChartTwo(LineChart lineChart) {

        // no description text
        lineChart.getDescription().setEnabled(false);
        lineChart.setNoDataText("图表暂无数据");
        // enable touch gestures
        lineChart.setTouchEnabled(true);

        lineChart.setDragDecelerationFrictionCoef(0.9f);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        lineChart.setMaxVisibleValueCount(40);

        // enable scaling and dragging
        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);
        lineChart.setScaleYEnabled(false);
        lineChart.setDrawGridBackground(false);
        lineChart.setHighlightPerDragEnabled(true);
// 向左偏移15dp，抵消y轴向右偏移的30dp
        lineChart.setExtraLeftOffset(-15);
        // if disabled, scaling can be done on x- and y-axis separately
        lineChart.setPinchZoom(true);

        // set an alternative background color
//        lineChart.setBackgroundColor(Color.LTGRAY);

        lineChart.animateY(1000);

        // get the legend (only possible after setting data)
        Legend l = lineChart.getLegend();

        // modify the legend ...
        l.setForm(Legend.LegendForm.LINE);
        l.setTextSize(11f);
        l.setTextColor(Color.BLACK);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
//        l.setYOffset(11f);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setTextSize(8f);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(4);
        xAxis.setXOffset(15);

        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setDrawGridLines(true);
        leftAxis.setGranularityEnabled(true);
        // 设置y轴数据偏移量
        leftAxis.setXOffset(15);
        // 限制数据(而不是背后的线条勾勒出了上面)
        leftAxis.setDrawLimitLinesBehindData(true);

        YAxis rightAxis = lineChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setDrawZeroLine(false);
        rightAxis.setGranularityEnabled(false);
    }

    public static void setLineData(List<Float> yAxisValue, List<String> xValues,List<Float> yAxisValueTwo,LineChart lineChart,String title1,String title2) {
        XAxis xLabels = lineChart.getXAxis();
        //修改x轴显示文字  monthList.get((int)value%monthList.size())
        xLabels.setValueFormatter((value, axis) -> String.valueOf(xValues.get((int) value % xValues.size())));

        Float max = Collections.max(yAxisValue);
        Float min = Collections.min(yAxisValue);
        float v = max - min;
        if (v<1){
            max = max+1;
            min = min-1;
        }else {
            Float len = (max-min)/8;
            max = max+len;
            min = min-len*3;
        }
        Float max2 = Collections.max(yAxisValueTwo);
        Float min2 = Collections.min(yAxisValueTwo);
        float v1 = max2 - min2;
        if (v1<1){
            max2 = max2+1;
            min2 = min2-1;
        }else {
            Float len2 = (max2-min2)/8;
            max2 = max2+len2;
            min2 = min2-len2*3;
        }
        YAxis leftAxis = lineChart.getAxisLeft();
        //重置所有限制线,以避免重叠线
        leftAxis.removeAllLimitLines();
        //y轴最大
        leftAxis.setAxisMaximum(max);
        //y轴最小
        leftAxis.setAxisMinimum(min);
        leftAxis.setTextColor(Color.parseColor("#376fff"));

        YAxis rightAxis = lineChart.getAxisRight();
        rightAxis.setAxisMaximum(max2);
        rightAxis.setAxisMinimum(min2);
        rightAxis.setDrawGridLines(false);
        rightAxis.setDrawZeroLine(false);
        rightAxis.setGranularityEnabled(false);
        rightAxis.setTextColor(Color.rgb(10,184,180));

        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

        for (int i = 0; i < yAxisValue.size(); i++) {
            yVals1.add(new Entry(i, yAxisValue.get(i)));
        }

        ArrayList<Entry> yVals2 = new ArrayList<Entry>();

        for (int i = 0; i < yAxisValueTwo.size(); i++) {
            yVals2.add(new Entry(i, yAxisValueTwo.get(i)));
//            if(i == 10) {
//                yVals2.add(new Entry(i, val + 50));
//            }
        }

        LineDataSet set1, set2;

        if (lineChart.getData() != null &&
                lineChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) lineChart.getData().getDataSetByIndex(0);
            set2 = (LineDataSet) lineChart.getData().getDataSetByIndex(1);
            set1.setValues(yVals1);
            set2.setValues(yVals2);
            lineChart.getData().notifyDataChanged();
            lineChart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(yVals1, title1);

            set1.setAxisDependency(YAxis.AxisDependency.LEFT);
            set1.setColor(Color.parseColor("#376fff"));
            set1.setCircleColor(Color.parseColor("#376fff"));
            set1.setLineWidth(2f);
            set1.setDrawCircles(false);
//            set1.setCircleRadius(2f);
//            set1.setFillAlpha(65);
//            set1.setFillColor(ColorTemplate.getHoloBlue());
            set1.setHighLightColor(Color.parseColor("#376fff"));
            set1.setDrawCircleHole(false);
            //set1.setFillFormatter(new MyFillFormatter(0f));
            //set1.setDrawHorizontalHighlightIndicator(false);
            //set1.setVisible(false);
            //set1.setCircleHoleColor(Color.WHITE);

            // create a dataset and give it a type
            set2 = new LineDataSet(yVals2, title2);
            set2.setAxisDependency(YAxis.AxisDependency.RIGHT);
            set2.setColor(Color.rgb(10,184,180));
            set2.setCircleColor(Color.rgb(10,184,180));
            set2.setLineWidth(2f);
            set2.setDrawCircles(false);
            set2.setDrawCircleHole(false);
            set2.setHighLightColor(Color.rgb(10,184,180));
            //set2.setFillFormatter(new MyFillFormatter(900f));

            // create a data object with the datasets
            LineData data = new LineData(set1, set2);
//            data.setValueTextColor(Color.BLACK);
//            data.setValueTextSize(9f);
            //曲线图上不显示值
            data.setDrawValues(false);

            // set data
            lineChart.setData(data);
                    List<ILineDataSet> setsHorizontalCubic = lineChart.getData().getDataSets();
            if (setsHorizontalCubic!=null&&setsHorizontalCubic.size()>0){
                for (ILineDataSet iSet : setsHorizontalCubic) {
                    LineDataSet set = (LineDataSet) iSet;
                    set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
                }
            }
            lineChart.invalidate();
        }
    }
}
