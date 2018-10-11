
package com.tepia.main.view.main.detail;

import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.tepia.main.R;

import java.util.ArrayList;

/**
 * Custom implementation of the MarkerView.
 * 
 * @author Philipp Jahoda
 */
public class MyMarkerViewOther extends MarkerView {

    private TextView tvContent;
    private ArrayList<String> data;

    public MyMarkerViewOther(Context context, int layoutResource, ArrayList<String> data) {
        super(context, layoutResource);
        this.data = data;
        tvContent = findViewById(R.id.tvContent);
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {


        if(data != null && data.size() > e.getX()) {
            tvContent.setText("库容:" + e.getY() + "\n" + "水位:" + data.get((int) Math.abs(e.getX())));
        }
        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}
