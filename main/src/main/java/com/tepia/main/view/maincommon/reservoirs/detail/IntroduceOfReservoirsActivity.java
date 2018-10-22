package com.tepia.main.view.maincommon.reservoirs.detail;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.esri.arcgisruntime.geometry.GeometryEngine;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.SpatialReference;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.layers.ArcGISTiledLayer;
import com.esri.arcgisruntime.mapping.view.LocationDisplay;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.ScreenUtil;
import com.tepia.base.view.arcgisLayout.ArcgisLayout;
import com.tepia.main.R;
import com.tepia.main.databinding.ActivityIntroduceOfReservoirsBinding;
import com.tepia.main.model.dictmap.DictMapEntity;
import com.tepia.main.model.dictmap.DictMapManager;
import com.tepia.main.model.reserviros.IntroduceOfReservoirsBean;
import com.tepia.main.view.maincommon.reservoirs.ReservoirsFragment;
import com.tepia.main.view.maincommon.reservoirs.mvpreservoir.ReserviorContract;
import com.tepia.main.view.maincommon.reservoirs.mvpreservoir.ReserviorPresent;

import java.util.Map;

/**
  * Created by      Android studio
  *
  * @author         :       ly(from Center Of Wuhan)
  * Date            :       2018-9-18
  * Version         :       1.0
  * 功能描述         :       水库简介
 **/
public class IntroduceOfReservoirsActivity extends MVPBaseActivity<ReserviorContract.View,ReserviorPresent> implements  ReserviorContract.View<IntroduceOfReservoirsBean> {

    private LinearLayout baseLy;
    private FrameLayout moreorlessFy;
    private TextView moreorlessTv;
    ActivityIntroduceOfReservoirsBinding binding;
    private boolean isopen;

    private MapView mapView;
    private ArcGISTiledLayer layer;
    private ArcGISTiledLayer imgLayer;
    private LocationDisplay mLocationDisplay;

    ArcgisLayout arcgisLayout;

    @Override
    public int getLayoutId() {
        return R.layout.activity_introduce_of_reservoirs;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCenterTitle("水库简介");
        showBack();
        binding = DataBindingUtil.bind(mRootView);
        baseLy = findViewById(R.id.baseLy);
        moreorlessFy = findViewById(R.id.moreorlessFy);
        moreorlessTv = findViewById(R.id.moreorlessTv);
        initBaseLy();
        moreorlessFy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isopen) {
                    initBaseLy();

                }else {
                    isopen = true;
                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                            RelativeLayout.LayoutParams.MATCH_PARENT);
                    layoutParams.height = RelativeLayout.LayoutParams.WRAP_CONTENT;
                    baseLy.setLayoutParams(layoutParams);
                    moreorlessTv.setText("收起");
                }

            }
        });
        initMap();
        String reservoirId = getIntent().getStringExtra(ReservoirsFragment.RESERVOIRId);
        String reservoirName = getIntent().getStringExtra(ReservoirsFragment.RESERVOIRNAME);
        TextView nameTv = findViewById(R.id.nameTv);
        nameTv.setText(reservoirName);
        mPresenter.getBaseInfo(reservoirId);
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
     * 初始化基本信息布局（默认收缩）
     */
    private void initBaseLy(){
        isopen = false;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        layoutParams.height = ScreenUtil.dp2px(getBaseContext(),200);
        baseLy.setLayoutParams(layoutParams);
        moreorlessTv.setText("查看更多");
    }

    private void initMap() {
        /*mapView = mRootView.findViewWithTag("mapView");
        mLocationDisplay = mapView.getLocationDisplay();
        //去水印
        ArcGISRuntimeEnvironment.setLicense("runtimelite,1000,rud4163659509,none,1JPJD4SZ8L4HC2EN0229");
        mapView.setAttributionTextVisible(false);
        OpenStreetMapLayer streetlayer = new OpenStreetMapLayer();
        Basemap basemap = new Basemap(streetlayer);
        ArcGISMap arcGISMap = new ArcGISMap(basemap);
        mapView.setMap(arcGISMap);

        mLocationDisplay.setAutoPanMode(LocationDisplay.AutoPanMode.RECENTER);*/
        arcgisLayout = mRootView.findViewWithTag("arcgisLayout");
        mapView = arcgisLayout.getMapView();
        mapView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                return;
            }
        });
        mapView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

    }

    private  void zoomToScale(Point point,double scale){
        if (scale<= 60000){
            imgLayer.setVisible(true);
        }else {
            imgLayer.setVisible(false);
        }
//        mapView.zoomToScale(point,scale);
    }

    @Override
    public void success(IntroduceOfReservoirsBean introduceOfReservoirsBean) {
        IntroduceOfReservoirsBean.DataBean dataBean = introduceOfReservoirsBean.getData();
        binding.reservoirNameTv.setText(dataBean.getReservoir());
        binding.scrollviewS.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                return false;
            }
        });


        DictMapEntity dictMapEntity = DictMapManager.getInstance().getmDictMap();
        Map<String, String> map_Reservoirtype = dictMapEntity.getObject().getReservoir_type();

        if(map_Reservoirtype != null) {
            binding.reservoirTypeTv.setText("水库类型：" + map_Reservoirtype.get(dataBean.getReservoirType()));
        }
        binding.belongTv.setText("所属乡镇："+dataBean.getAreaName());
        String buildtime = dataBean.getBuildStartDate();
        if(TextUtils.isEmpty(buildtime)){
            buildtime = getString(R.string.setting_t_null);
        }
        String endtime = dataBean.getBuildEndDate();
        if(TextUtils.isEmpty(endtime)){
            endtime = getString(R.string.setting_t_null);
        }
        binding.buildStartDateTv.setText("兴建时间："+ buildtime);
        binding.buildEndDateTv.setText("竣工时间："+ endtime);
        binding.widthAndlengthTv.setText("坝高:"+dataBean.getDamHeight()+"m    |   坝长："+dataBean.getDamLength()+
                "m    |   坝宽："+dataBean.getDamWidth()+"m");
        binding.normalImpoundedLevelTv.setText("正常蓄水位："+dataBean.getNormalImpoundedLevel()+"m");


        Map<String, String> map_Damtype = dictMapEntity.getObject().getDam_type();
        binding.damTypeTv.setText("大坝类型："+ map_Damtype.get(dataBean.getDamType())+"");
        binding.damCrestElevationTv.setText("坝顶高程："+dataBean.getDamCrestElevation()+"m");
        binding.damBotmMaxWidthTv.setText("坝底最大宽度："+dataBean.getDamBotmMaxWidth()+"m");
        binding.capacityCoefficientTv.setText("库容系数："+dataBean.getCapacityCoefficient()+"");

        String mainFunctionStr = dataBean.getMainFunction();
        if (TextUtils.isEmpty(mainFunctionStr)) {
            mainFunctionStr = getString(R.string.setting_t_null);
        }
        binding.mainFunctionTv.setText(mainFunctionStr);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            binding.reservoirProduceTv.setText(Html.fromHtml(dataBean.getReservoirProduce(),0));
        }else{
            binding.reservoirProduceTv.setText(Html.fromHtml(dataBean.getReservoirProduce()));

        }
        binding.reservoirAddressTv.setText(dataBean.getReservoirAddress()+"");
        LogUtil.e("经度："+dataBean.getReservoirLongitude()+"，纬度："+dataBean.getReservoirLatitude());

        if(!TextUtils.isEmpty(dataBean.getReservoirLongitude()) && !TextUtils.isEmpty(dataBean.getReservoirLatitude())) {
            try{
                Point point1 = new Point(Double.parseDouble(dataBean.getReservoirLongitude()),
                        Double.parseDouble(dataBean.getReservoirLatitude()), SpatialReference.create(4326));
                Point point = (Point) GeometryEngine.project(point1, SpatialReferences.getWebMercator());
                arcgisLayout.addPic(R.drawable.map_ku, point);
                arcgisLayout.setCenterPoint(point, arcgisLayout.groupScale);
            }catch (NumberFormatException e){
                LogUtil.e(e.getMessage());
            }

        }


    }

    @Override
    public void failure(String msg) {

    }
}
