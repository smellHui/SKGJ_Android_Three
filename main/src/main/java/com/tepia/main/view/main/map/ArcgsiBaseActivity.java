package com.tepia.main.view.main.map;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.esri.arcgisruntime.geometry.GeometryEngine;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.SpatialReference;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.mapping.view.Callout;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.symbology.SimpleLineSymbol;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.view.arcgisLayout.ArcgisLayout;
import com.tepia.main.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArcgsiBaseActivity extends AppCompatActivity {

    private MapView mapView;
    private ArcgisLayout arcgisLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arcgsi_base);
        initMap();
    }

    private void initMap() {
        arcgisLayout = findViewById(R.id.arcgisLayout);
        mapView = arcgisLayout.getMapView();
        //添加图片
        Map<String, Object> attrs = new HashMap<>(1);
        arcgisLayout.addPic(R.drawable.m_reservior,new Point(12734302.279705,3574615.512000),attrs);
        //添加线
        List<Point> points =new ArrayList<>();
        points.add(new Point(12435862.164232, 3736503.596019));
        points.add(new Point(12616408.732277, 3832135.568133));
        points.add(new Point(12573539.227535,3508965.455470));
        arcgisLayout.addPolyline(points, SimpleLineSymbol.Style.SOLID, Color.RED,6);
        //添加点
        arcgisLayout.addPoint(new Point(12491984.627892,3964569.753069), SimpleMarkerSymbol.Style.CIRCLE,Color.RED,10);
        //获取点击地图对应的坐标点
        arcgisLayout.setOnLocationSelectListener(point ->{
            LogUtil.i(point.toString());
            initCallout(point);
        });
        //设置point点击事件
        arcgisLayout.setOnAddPointClickListener((point, attributes) -> {
            LogUtil.i(point.toString());
        });
        //设置线的点击事件
        arcgisLayout.setOnAddLineClickListener((polyline, point, attributes) -> {
            LogUtil.i(polyline.toString());
        });
        Point point1 = new Point(106.745, 27.60, SpatialReference.create(4326));
        Point point = (Point) GeometryEngine.project(point1, SpatialReferences.getWebMercator());
//        LogUtil.i("转换之后的坐标:"+point.toString());
        arcgisLayout.addPic(R.drawable.m_reservior,point,attrs);
    }

    /**
     * 定位  调用之前判断定位权限
     */
    private void getLocation() {
        arcgisLayout.setOnAddLocationChangedListener(point -> {
            LogUtil.i("定位的点:"+point);
        });
        arcgisLayout.startLocation();
//        arcgisLayout.stopLocation();
    }

    private float dip2px(Context context,Float dipValue){
        float scale = context.getResources().getDisplayMetrics().density;
        return  (dipValue * scale + 0.5f);
    }

    private void initCallout(Point point) {
        Callout callout = mapView.getCallout();
        TextView tv = new TextView(this);
        tv.setText("我是一个气泡");
        callout.setContent(tv);
        callout.getStyle().setLeaderPosition(Callout.Style.LeaderPosition.AUTOMATIC);
        callout.getStyle().setBackgroundColor(Color.WHITE);
        callout.getStyle().setBorderWidth(1);
        callout.getStyle().setCornerRadius(3);
        callout.getStyle().setLeaderWidth(5);
        callout.getStyle().setLeaderLength(10);
        callout.setLocation(point);
        callout.show();
    }

    @Override
    public void onResume() {
        super.onResume();
        arcgisLayout.onMapResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        arcgisLayout.onMapPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        arcgisLayout.onMapDestroy();
    }
}
