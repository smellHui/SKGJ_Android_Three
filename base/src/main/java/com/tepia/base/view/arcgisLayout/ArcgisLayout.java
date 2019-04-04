package com.tepia.base.view.arcgisLayout;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.esri.arcgisruntime.ArcGISRuntimeEnvironment;
import com.esri.arcgisruntime.geometry.Envelope;
import com.esri.arcgisruntime.geometry.Geometry;
import com.esri.arcgisruntime.geometry.GeometryEngine;
import com.esri.arcgisruntime.geometry.GeometryType;
import com.esri.arcgisruntime.geometry.ImmutablePartCollection;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.PointCollection;
import com.esri.arcgisruntime.geometry.Polyline;
import com.esri.arcgisruntime.geometry.SpatialReference;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.layers.ArcGISTiledLayer;
import com.esri.arcgisruntime.layers.OpenStreetMapLayer;
import com.esri.arcgisruntime.layers.WebTiledLayer;
import com.esri.arcgisruntime.loadable.LoadStatus;
import com.esri.arcgisruntime.location.LocationDataSource;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.Viewpoint;
import com.esri.arcgisruntime.mapping.view.Callout;
import com.esri.arcgisruntime.mapping.view.DefaultMapViewOnTouchListener;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.IdentifyGraphicsOverlayResult;
import com.esri.arcgisruntime.mapping.view.LocationDisplay;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.symbology.PictureMarkerSymbol;
import com.esri.arcgisruntime.symbology.SimpleLineSymbol;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;
import com.esri.arcgisruntime.util.ListenableList;
import com.tepia.base.R;
import com.tepia.base.utils.NetUtil;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.ResUtils;
import com.tepia.base.utils.TimeFormatUtils;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * arcgis地图布局
 *
 * @author 44822
 */
public class ArcgisLayout extends RelativeLayout {

    //    public double itemScale = 72223.819286;
    public double itemScale = 50000;
    public double groupScale = 1155581.108577;
    private LocationDisplay mLocationDisplay;
    private Point currentPoint;
    private Polyline polyline;
    public int mapHeight;
    public boolean isLoaded = false;
    public static double minScale = 7.335451152802595E7* 0.5* 0.5;
    public static double maxScale = 2647.4016185056967;
    public Point mapCenterPoint = new Point(11620672.230780,4930386.331908, 0.000000);//中国地图中心点
    private WebTiledLayer imgTitleLayer;
    private GraphicsOverlay locationOverlay;

    public interface OnLocationSelectListener {
        void onSelect(Point point);
    }

    public interface OnAddLineClickListener {
        void onCilck(Polyline polyline, Point point, Map<String, Object> attributes);
    }

    public interface OnAddLocationChangedListener {
        void getLocation(Point point);
    }

    public interface OnAddPointClickListener {
        void onCilck(Point point, Map<String, Object> attributes);
    }

    private OnLocationSelectListener onLocationSelectListener;
    private OnAddPointClickListener onAddPointClickListener;
    private OnAddLineClickListener onAddLineClickListener;
    private OnAddLocationChangedListener onAddLocationChangedListener;

    public void setOnAddPointClickListener(OnAddPointClickListener onAddPointClicKListener) {
        this.onAddPointClickListener = onAddPointClicKListener;
    }

    public void setOnAddLineClickListener(OnAddLineClickListener onAddLineClickListener) {
        this.onAddLineClickListener = onAddLineClickListener;
    }

    public void setOnLocationSelectListener(OnLocationSelectListener onLocationSelectListener) {
        this.onLocationSelectListener = onLocationSelectListener;
    }

    public void setOnAddLocationChangedListener(OnAddLocationChangedListener onAddLocationChangedListener) {
        this.onAddLocationChangedListener = onAddLocationChangedListener;
    }

    MapView mapView;
    private GraphicsOverlay graphicsOverlay;

    public ArcgisLayout(Context context) {
        super(context);
        initView();
    }

    public ArcgisLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        //获取布局
        View view = LayoutInflater.from(getContext()).inflate(R.layout.arcgis_base, this);
        initMap(view);
        initOnClick(view);
    }

    private void initMap(View view) {
        mapView = view.findViewById(R.id.mapView);
        ArcGISRuntimeEnvironment.setLicense("runtimelite,1000,rud4163659509,none,1JPJD4SZ8L4HC2EN0229");
        mapView.setAttributionTextVisible(false);
//        ArcGISTiledLayer layer = new ArcGISTiledLayer("http://map.geoq.cn/arcgis/rest/services/ChinaOnlineCommunity/MapServer");
        ArcGISTiledLayer imgLayer = new ArcGISTiledLayer("https://services.arcgisonline.com/ArcGIS/rest/services/World_Imagery/MapServer");
//        imgLayer.setVisible(false);
//        layer.setVisible(false);
        OpenStreetMapLayer streetlayer=new OpenStreetMapLayer();
        //google影像图
        imgTitleLayer = GoogleMapLayer.createWebTiteLayer(GoogleMapLayer.Type.IMAGE);
        WebTiledLayer webTitleLayer = GoogleMapLayer.createWebTiteLayer(GoogleMapLayer.Type.IMAGE_ANNOTATION);
        Basemap basemap = new Basemap();
        ArcGISMap arcGISMap = new ArcGISMap(basemap);
//        arcGISMap.getOperationalLayers().add(layer);
        arcGISMap.getOperationalLayers().add(imgTitleLayer);
        arcGISMap.getOperationalLayers().add(webTitleLayer);
//        arcGISMap.getOperationalLayers().add(imgLayer);
        arcGISMap.setMinScale(ArcgisLayout.minScale);
        Point point1 = new Point(1.1992433073197773E7, 4885139.106039485,SpatialReference.create(3857));
        Point point2 = new Point(1.3532164647994766E7, 2329702.2487083403,SpatialReference.create(3857));
        Envelope initEnvelope = new Envelope(point1,point2);
        arcGISMap.setInitialViewpoint(new Viewpoint(initEnvelope));
        arcGISMap.setMinScale(minScale);
        mapView.setMap(arcGISMap);
        //模拟定位图标
        locationOverlay = new GraphicsOverlay();
        mapView.getGraphicsOverlays().add(locationOverlay);
        //添加覆盖物
        graphicsOverlay = new GraphicsOverlay();
        mapView.getGraphicsOverlays().add(graphicsOverlay);
        //设置地图点击事件
        mapView.setOnTouchListener(new DefaultMapViewOnTouchListener(getContext(), mapView) {
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                if (!NetUtil.isNetworkConnected(Utils.getContext())) {
                    ToastUtils.shortToast("当前无网络，无法查看地图");
                    return super.onSingleTapConfirmed(e);
                }
//                android.graphics.Point screenPoint = new android.graphics.Point(Math.round(e.getX()), Math.round(e.getY()));
//                Point clickPoint = mapView.screenToLocation(screenPoint);
//                LogUtil.i("地图坐标："+clickPoint.toString());
                handleSingleTapEvent(e);
                return super.onSingleTapConfirmed(e);
            }
        });
        basemap.addDoneLoadingListener(new Runnable() {
            @Override
            public void run() {
                isLoaded = true;
            }
        });

        if (!NetUtil.isNetworkConnected(Utils.getContext())) {
            try {
                ViewTreeObserver viewTreeObserver = mapView.getViewTreeObserver();
                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {

                        mapView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        mapHeight = mapView.getHeight();
                    }
                });
            }catch (Exception e){

            }
        }

        initLocation();
    }

    /**
     * 初始化定位
     */
    private void initLocation() {
        //定位
        mLocationDisplay = mapView.getLocationDisplay();
        mLocationDisplay.setAutoPanMode(LocationDisplay.AutoPanMode.OFF);
        mLocationDisplay.setShowLocation(false);//设置不显示定位图标
        mLocationDisplay.addLocationChangedListener(locationChangedEvent -> {
            //getMapLocation获取的点是基于当前地图坐标系的点
            //getPosition是获取基于GPS的位置信息，再获取的点是基于WGS84的经纬度坐标。
            LocationDataSource.Location location = locationChangedEvent.getLocation();
            Point point = mLocationDisplay.getMapLocation();
//            LogUtil.i(" LocationDataSource.Location:"+location.getPosition());
            currentPoint = location.getPosition();
            Point position = location.getPosition();
//            LogUtil.i(" LocationDataSource.Location:"+location.getPosition());
//            LogUtil.i("point:"+point.toString());
            Point locationPoint = transformationPoint(position.getX(), position.getY());
            addLocationPic(locationOverlay,R.drawable.google_location,locationPoint);
//            LogUtil.i("point:"+point.toString());
            if (onAddLocationChangedListener != null) {
                onAddLocationChangedListener.getLocation(currentPoint);
            }
        });
//        mLocationDisplay.setAutoPanMode(LocationDisplay.AutoPanMode.RECENTER);
//        mLocationDisplay.startAsync();
    }

    /**
     * 添加定位图标
     * @param graphicsOverlay
     * @param id
     * @param point  地图的坐标系
     */
    public void addLocationPic(GraphicsOverlay graphicsOverlay, int id, Point point) {
        PictureMarkerSymbol pictureMarkerSymbol1 = null;
        try {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), id);
            if (bitmap == null) {
                return;
            }
            Bitmap result = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight() * 2, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(result);
            canvas.drawBitmap(bitmap, 0, 0, null);
//            canvas.drawBitmap(bitmap, bitmap.getHeight(), 0, null);
            pictureMarkerSymbol1 = PictureMarkerSymbol.createAsync(new BitmapDrawable(getResources(), result)).get();
            Graphic picGraphic = new Graphic(point, pictureMarkerSymbol1);
            ListenableList<Graphic> graphics = graphicsOverlay.getGraphics();
            if (graphics!=null&&graphics.size()>0){
                graphics.set(0,picGraphic);
            }else {
                graphicsOverlay.getGraphics().add(picGraphic);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 开启定位     开启前需要定位权限
     */
    public void startLocation() {
        // Start Location Display
        if (!mLocationDisplay.isStarted()) {
            mLocationDisplay.startAsync();
        }
    }

    /**
     * 关闭定位
     */
    public void stopLocation() {
        // Stop Location Display
        if (mLocationDisplay.isStarted()) {
            mLocationDisplay.stop();
        }
    }

    /**
     * 地图点击事件
     *
     * @param e
     */
    private void handleSingleTapEvent(MotionEvent e) {

        try {
            android.graphics.Point screenPoint = new android.graphics.Point(Math.round(e.getX()), Math.round(e.getY()));
            Point clickPoint = mapView.screenToLocation(screenPoint);
            if (onLocationSelectListener != null) {
                onLocationSelectListener.onSelect(clickPoint);
            }
            IdentifyGraphicsOverlayResult identifyGraphicsOverlayResult = mapView.identifyGraphicsOverlayAsync(graphicsOverlay, screenPoint, 1.0, false).get();
            List<Graphic> graphics = identifyGraphicsOverlayResult.getGraphics();
            Map<String, Object> attributes = graphics.get(0).getAttributes();
            GeometryType geometryType = graphics.get(0).getGeometry().getGeometryType();
//            LogUtil.i("点击的要素类型" + geometryType + "------" + "要素参数:" + attributes);
            if (GeometryType.POINT.equals(geometryType)) {
                //点击的是点要素
                if (onAddPointClickListener != null) {
                    onAddPointClickListener.onCilck((Point) graphics.get(0).getGeometry(), attributes);
                }
            } else if (GeometryType.POLYLINE.equals(geometryType)) {
                //点击的是线
                if (onAddLineClickListener != null) {
                    onAddLineClickListener.onCilck((Polyline) graphics.get(0).getGeometry(), clickPoint, attributes);
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    private void initOnClick(View view) {
        ImageView btZoonIn = view.findViewById(R.id.zoomBtnIn);
        ImageView btZoonOut = view.findViewById(R.id.zoomBtnOut);
        btZoonIn.setOnClickListener(this::bottomBtnClick);
        btZoonOut.setOnClickListener(this::bottomBtnClick);
    }

    private void bottomBtnClick(View view) {
        int i = view.getId();
        if (i == R.id.zoomBtnIn) {
            if (mapView != null) {
                //放大
                if (isLoaded){
                    mapViewZoomIn();
                }
            }
        } else if (i == R.id.zoomBtnOut) {
            if (mapView != null) {
                //缩小
                if (isLoaded){
                    mapViewZoomOut();
                }
            }
        }
    }

    public MapView getMapView() {
        return mapView;
    }


    /**
     * 添加图片
     *
     * @param id    图片id
     * @param point 坐标点  统一4326坐标系
     */
    public void addPic(int id, Point point) {
        PictureMarkerSymbol pictureMarkerSymbol1 = null;
        try {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), id);
            pictureMarkerSymbol1 = PictureMarkerSymbol.createAsync(new BitmapDrawable(getResources(), bitmap)).get();
            Graphic picGraphic = new Graphic(point, pictureMarkerSymbol1);
            graphicsOverlay.getGraphics().add(picGraphic);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加图片 attributes 不能传自定义对象
     *
     * @param id         图片id
     * @param point      坐标点  统一4326坐标系
     * @param attributes 要素传值
     */
    public void addPic(int id, Point point, Map<String, Object> attributes) {
        PictureMarkerSymbol pictureMarkerSymbol1 = null;
        try {
            Point point1 = transformationPoint(point.getX(), point.getY());
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), id);
            if (bitmap == null) {return;}
            Bitmap result =   Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight()*2, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(result);
            canvas.drawBitmap(bitmap, 0, 0, null);
//            canvas.drawBitmap(bitmap, bitmap.getHeight(), 0, null);
            pictureMarkerSymbol1 = PictureMarkerSymbol.createAsync(new BitmapDrawable(getResources(), result)).get();
            Graphic picGraphic = new Graphic(point1, attributes, pictureMarkerSymbol1);
            graphicsOverlay.getGraphics().add(picGraphic);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加线
     *
     * @param points     路径集合  统一4326坐标系
     * @param style      线的样式
     * @param color      线的颜色
     * @param width      线的宽度
     * @param attributes 要素传值
     */
    public void addPolyline(List<Point> points, SimpleLineSymbol.Style style, int color, float width, Map<String, Object> attributes) {
        try {
            List<Point> pointList = new ArrayList<>();
            if (points!=null&&points.size()>0){
                for (int i = 0; i < points.size(); i++) {
                    Point point = points.get(i);
                    Point point1 = transformationPoint(point.getX(), point.getY());
                    pointList.add(point1);
                }
            }

            PointCollection coloradoCorners = new PointCollection(mapView.getSpatialReference());
            if (pointList != null && pointList.size() > 0) {
                coloradoCorners.addAll(pointList);
            }
            polyline = new Polyline(coloradoCorners);
            SimpleLineSymbol lineSymbol = new SimpleLineSymbol(style, color, width);
            graphicsOverlay.getGraphics().add(new Graphic(polyline, attributes, lineSymbol));
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 添加线
     *
     * @param points 路径集合    统一4326坐标系
     * @param style  线的样式
     * @param color  线的颜色
     * @param width  线的宽度
     */
    public void addPolyline(List<Point> points, SimpleLineSymbol.Style style, int color, float width) {
        try {
            List<Point> pointList = new ArrayList<>();
            if (points.size()>0){
                for (int i = 0; i < points.size(); i++) {
                    Point point = points.get(i);
                    Point point1 = transformationPoint(point.getX(), point.getY());
                    pointList.add(point1);
                }
                PointCollection coloradoCorners = new PointCollection(mapView.getSpatialReference());
                if (pointList != null && pointList.size() > 0) {
                    coloradoCorners.addAll(pointList);
                }
                polyline = new Polyline(coloradoCorners);
                SimpleLineSymbol lineSymbol = new SimpleLineSymbol(style, color, width);
                graphicsOverlay.getGraphics().add(new Graphic(polyline, lineSymbol));
            }
        }catch (Exception e){

        }
    }


    /**
     * 添加点
     *
     * @param point 统一4326
     * @param style
     * @param color
     * @param width
     */
    public void addPoint(Point point, SimpleMarkerSymbol.Style style, int color, float width) {
        try {
            Point point1 = transformationPoint(point.getX(), point.getY());
            SimpleMarkerSymbol markerSymbol = new SimpleMarkerSymbol(style, color, width);
            graphicsOverlay.getGraphics().add(new Graphic(point1, markerSymbol));
        }catch (Exception e){

        }
    }

    /**
     * 清空自定义要素图层
     */
    public void removeGraphics() {
        graphicsOverlay.getGraphics().clear();
    }

    /**
     * 设置地图缩放比例
     *
     * @param scale
     */
    public void setMapScale(double scale) {
        try {
            mapView.setViewpointScaleAsync(scale);
        }catch (Exception e){

        }
    }

    /**
     * 初始化地图缩放比例
     */
    public void initMapScale() {
        setMapScale(groupScale);
    }

    /**
     * 设置地图中心点
     *
     * @param point  统一4326坐标系
     */
    public void setCenterPoint(Point point) {
        try {
            Point point1 = transformationPoint(point.getX(), point.getY());
            mapView.setViewpointCenterAsync(point1);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 根据比例设置地图中心点
     *
     * @param point
     * @param scale 缩放比例
     */
    public void setCenterPoint(Point point, double scale) {
        try {
            Point point1 = transformationPoint(point.getX(), point.getY());
            mapView.setViewpointCenterAsync(point1, scale);
        }catch (Exception e){
//            e.printStackTrace();
        }

    }

    /**
     * 缩小地图
     */
    public void mapViewZoomOut() {
        if (mapView != null) {
            //缩小
            double mScale = mapView.getMapScale();
            try {
                mapView.setViewpointScaleAsync(mScale * 2);
            }catch (Exception e){
//                e.printStackTrace();
            }
        }
    }

    /**
     * 放大地图
     */
    public void mapViewZoomIn() {
        if (mapView != null) {
            try {
                //放大
                double mScale = mapView.getMapScale();
                mapView.setViewpointScaleAsync(mScale * 0.5);
            }catch (Exception e){
//                e.printStackTrace();
            }
        }
    }

    public void addPolylineByPoint(Point point, SimpleLineSymbol.Style style, int color, float width) {
        try {
            if (polyline != null) {
                ImmutablePartCollection parts = polyline.getParts();
                if (null != parts && parts.size() > 0) {
                    Point endPoint = parts.get(0).getEndPoint();
                    PointCollection coloradoCorners = new PointCollection(mapView.getSpatialReference());
                    coloradoCorners.add(endPoint);
                    coloradoCorners.add(point);
                    polyline = new Polyline(coloradoCorners);
                    SimpleLineSymbol lineSymbol = new SimpleLineSymbol(style, color, width);
                    graphicsOverlay.getGraphics().add(new Graphic(polyline, lineSymbol));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 显示一个汽包
     *
     * @param point
     * @param name
     */
    public void showCallout(Point point, String name) {
        try {
            Callout callout = mapView.getCallout();
            TextView tv = new TextView(getContext());
            tv.setText(name);
            callout.setContent(tv);
            callout.getStyle().setLeaderPosition(Callout.Style.LeaderPosition.AUTOMATIC);
            callout.getStyle().setBackgroundColor(Color.WHITE);
            callout.getStyle().setBorderWidth(1);
            callout.getStyle().setCornerRadius(3);
            callout.getStyle().setLeaderWidth(5);
            callout.getStyle().setLeaderLength(10);
            callout.setLocation(point);
            callout.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 设置地图的可见范围
     *
     * @param points   统一4326坐标系
     */
    public void setMapViewVisibleExtent(List<Point> points) {
        try {
            if (points != null && points.size() > 0) {
                List<Point>  pointList = new ArrayList<>();
                for (int i = 0; i < points.size(); i++) {
                    Point point = points.get(i);
                    Point point1 = transformationPoint(point.getX(), point.getY());
                    pointList.add(point1);
                }
                double numx = (double) pointList.get(0).getX();
                double numy = (double) pointList.get(0).getY();
                double minx = (double) pointList.get(0).getX();
                double miny = (double) pointList.get(0).getY();
                for (int i = 0; i < pointList.size(); i++) {
                    double x = (double) pointList.get(i).getX();
                    double y = (double) pointList.get(i).getY();
                    numx = x < numx ? numx : x;
                    numy = y < numy ? numy : y;
                    minx = x > minx ? minx : x;
                    miny = y > miny ? miny : y;
                }
                double xcen = (numx - minx) > 0 ? (numx - minx) : 0;
                double ycen = (numy - miny) > 0 ? (numy - miny) : 0;
//            Envelope envelope = new Envelope();
//            envelope.setXMin(minPoint.getX() - xcen / 10);
//            envelope.setYMin(minPoint.getY() - ycen / 10);
//            envelope.setXMax(maxPoint.getX() + xcen / 10);
//            envelope.setYMax(maxPoint.getY() + ycen / 10);
//            mapView.setExtent(envelope);
                Envelope envelope = new Envelope(minx - xcen / 10, miny- ycen / 10, numx+ xcen / 10, numy+ ycen / 10, SpatialReference.create(3857));
                mapView.setViewpointGeometryAsync(envelope);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 地图坐标转换
     *
     * @param lgtd
     * @param lttd
     * @return
     */
    public static Point transformationPoint(double lgtd, double lttd) {
        Point point1 = new Point(lgtd, lttd, SpatialReference.create(4326));
//        Google地图偏移
        double[] doubles = ArcgisGpsUtils.gps84_To_Gcj02(lttd, lgtd);
        Point point = (Point) GeometryEngine.project(new Point(doubles[1],doubles[0],SpatialReference.create(4326)), SpatialReferences.getWebMercator());
//        Point point = (Point) GeometryEngine.project(point1, SpatialReferences.getWebMercator());
        return point;
    }


    public void onMapResume() {
        if (mapView != null) {
            mapView.resume();
        }
    }

    public void onMapPause() {
        if (mapView != null) {
            mapView.pause();
        }
    }

    public void onMapDestroy() {
        if (mapView != null) {
            mapView.dispose();
        }
    }
}
