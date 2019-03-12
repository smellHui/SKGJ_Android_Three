package com.tepia.main.view.maintechnology.threekeypoint.util;

import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.tepia.main.R;

/**
  * Created by      Android studio
  * @author :wwj (from Center Of Wuhan)
  * Date    :2018/11/15
  * Version :1.0
  * 功能描述 :
 **/

public class ThreePointMarkerView extends MarkerView {
    private TextView tvDate;
    private TextView tvContent;
    private IAxisValueFormatter xAxisValueFormatter;
    /**
     * Constructor. Sets up the MarkerView with a custom layout resource.
     *
     * @param context
     * @param layoutResource the layout resource to use for the MarkerView
     */
    public ThreePointMarkerView(Context context, int layoutResource, IAxisValueFormatter xAxisValueFormatter) {
        super(context, layoutResource);
        this.xAxisValueFormatter = xAxisValueFormatter;
        tvContent = (TextView) findViewById(R.id.tvContent);
        tvDate = (TextView) findViewById(R.id.tv_date);
    }

    // callbacks everytime the MarkerView is redrawn, can be used to update the
    // content (user-interface)
    @Override
    public void refreshContent(Entry e, Highlight highlight) {

        if (e instanceof CandleEntry) {

            CandleEntry ce = (CandleEntry) e;

            tvContent.setText("雨量:" + ce.getHigh());
        } else {

            tvContent.setText("雨量:" + e.getY());
        }
        tvDate.setText(xAxisValueFormatter.getFormattedValue(e.getX(), null));
        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}
