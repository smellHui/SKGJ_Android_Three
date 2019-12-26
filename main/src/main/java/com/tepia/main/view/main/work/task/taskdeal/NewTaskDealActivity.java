package com.tepia.main.view.main.work.task.taskdeal;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClientOption;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.SpatialReference;
import com.esri.arcgisruntime.mapping.Viewpoint;
import com.esri.arcgisruntime.mapping.view.DrawStatus;
import com.esri.arcgisruntime.mapping.view.DrawStatusChangedEvent;
import com.esri.arcgisruntime.mapping.view.DrawStatusChangedListener;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.example.gaodelibrary.GaodeEntity;
import com.example.gaodelibrary.OnGaodeLibraryListen;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.tepia.base.AppRoutePath;
import com.tepia.base.common.CommonFragmentPagerAdapter;
import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.base.utils.DoubleClickUtil;
import com.tepia.base.utils.ResUtils;
import com.tepia.base.utils.ScreenUtil;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.view.ScrollLayout.ScrollLayout;
import com.tepia.base.view.arcgisLayout.ArcgisLayout;
import com.tepia.base.view.dialog.loading.SimpleLoadDialog;
import com.tepia.main.R;
import com.tepia.main.common.KeyboardLayout;
import com.tepia.main.databinding.ActivityNewtaskdealBinding;
import com.tepia.main.databinding.ActivityTaskDeal2Binding;
import com.tepia.main.model.task.LatLngAndAddressBean;
import com.tepia.main.model.task.bean.PositionNamesBean;
import com.tepia.main.model.task.bean.TaskBean;
import com.tepia.main.model.task.bean.TaskItemBean;
import com.tepia.main.model.user.UserManager;
import com.tepia.main.view.main.work.task.taskdeal.taskitemdeal.TaskItemDealFragment;
import com.tepia.main.view.main.work.task.taskdetail.AdapterPositionTitle;
import com.tepia.main.view.main.work.task2.taskdetail.TaskDetailActivity;
import com.yanzhenjie.permission.target.SupportFragmentTarget;

import org.greenrobot.eventbus.util.ErrorDialogManager;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Android Studio
 *
 * @author : Arthur
 * Date :    2019/12/24
 * Time :    15:02
 * Describe :
 */
@Route(path = AppRoutePath.app_task_deal)
public class NewTaskDealActivity extends MVPBaseActivity<TaskDealContract.View, TaskDealPresenter> implements TaskDealContract.View {

    private ActivityNewtaskdealBinding mBinding;

    @Autowired(name = "workOrderId")
    String workOrderId;
    @Autowired(name = "taskBean")
    String temp;
    @Autowired(name = "position")
    int position = 0;
    @Autowired(name = "itemid")
    String itemId;

    private TaskBean taskBean;
    private ScrollLayout.OnScrollChangedListener mOnScrollChangedListener;

    /**
     * 进度条
     */
    private SimpleLoadDialog simpleLoadDialog;
    /**
     * 当前位置
     */
    private Point currentPoint;
    private boolean isFirstInitMap = false;
    //    private CommonFragmentPagerAdapter adapter;
    private Point positionPoint;

    private String city;

    public LatLngAndAddressBean getAddress() {
        LatLngAndAddressBean bean = new LatLngAndAddressBean();
        bean.setCity(city);
        bean.setPoint(currentPoint);
        bean.setReservoirName(taskBean == null ? "" : taskBean.getReservoirName());
        return bean;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isFirstInitMap = true;

        if (taskBean == null) {
            taskBean = new Gson().fromJson(temp, TaskBean.class);
        }
        refreshView(taskBean);
        if (taskBean == null) {
            mPresenter.getTaskDetail(workOrderId, true, getString(R.string.data_loading));
        } else {
            mPresenter.getTaskDetail(workOrderId, false, "");
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_newtaskdeal;
    }

    @Override
    public void initView() {
        setStatusBarTextDark();
        simpleLoadDialog = new SimpleLoadDialog(NewTaskDealActivity.this,"正在上传...",false);
        mBinding = DataBindingUtil.bind(mRootView);
        ImmersionBar.setTitleBar(this, mBinding.loTitle);
        initScrllLayout();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                initMapView();
            }
        }, 2000);
        getGaoDeLocation();
    }


    /**
     * 高德地图定位
     */
    private GaodeEntity gaodeEntity;

    private void getGaoDeLocation() {
//        gaodeEntity = new GaodeEntity(getContext());
//        MapView mapView = new MapView(this);
//        gaodeEntity = new GaodeEntity(this, mapView.getMap(),TaskDetailActivity.class, R.mipmap.logo);
        gaodeEntity = new GaodeEntity(this, TaskDetailActivity.class, R.mipmap.logo);
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        //高德地图说明，来自高德android开发常见问题：
        //GPS模块无法计算出定位结果的情况多发生在卫星信号被阻隔时，在室内环境卫星信号会被墙体、玻璃窗阻隔反射，在“城市峡谷”（高楼林立的狭长街道）地段卫星信号也会损失比较多。
        ////强依赖GPS模块的定位模式——如定位SDK的仅设备定位模式，需要在室外环境进行，多用于行车、骑行、室外运动等场景。
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Device_Sensors);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        /*mOption.setGpsFirst(true);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setInterval(interval);//可选，设置定位间隔。默认为2秒
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差*/
        mOption.setGpsFirst(true);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(10 * 60 * 1000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(true);//可选，设置是否单次定位。默认是false
//        mOption.setOnceLocationLatest(true);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
        gaodeEntity.setLocationOption(mOption);
        gaodeEntity.setLocationListen(new OnGaodeLibraryListen.LocationListen() {
            @Override
            public void getCurrentGaodeLocation(AMapLocation aMapLocation) {
                if (gaodeEntity != null) {
                    city = aMapLocation.getCity();
                    gaodeEntity.closeLocation();
                }
            }
        });
        gaodeEntity.startLocation();
    }

    private void initMapView() {
        mBinding.alMapview.startLocation();
        mBinding.alMapview.setOnAddLocationChangedListener(new ArcgisLayout.OnAddLocationChangedListener() {
            @Override
            public void getLocation(Point point) {
                currentPoint = point;
//                LogUtil.i("坐标:"+currentPoint.getX()+"..."+currentPoint.getY());
            }
        });

        mBinding.alMapview.getMapView().addDrawStatusChangedListener(new DrawStatusChangedListener() {
            @Override
            public void drawStatusChanged(DrawStatusChangedEvent drawStatusChangedEvent) {
                DrawStatus drawStatus = drawStatusChangedEvent.getDrawStatus();
                if (drawStatus == DrawStatus.COMPLETED) {
                    if (isFirstInitMap) {
                        if (currentPoint != null) {
                            if ("2".equals(taskBean.getExecuteStatus())) {
                                try {
                                    Point point = ArcgisLayout.transformationPoint(currentPoint.getX(), currentPoint.getY());
                                    mBinding.alMapview.getMapView().setViewpointCenterAsync(point, mBinding.alMapview.itemScale).addDoneListener(() -> {
                                        moveMap(true);
                                    });
                                } catch (Exception e) {

                                }
                            } else {
                                if (positionPoint != null) {
                                    try {
                                        Point point = ArcgisLayout.transformationPoint(positionPoint.getX(), positionPoint.getY());
                                        mBinding.alMapview.getMapView().setViewpointCenterAsync(point, mBinding.alMapview.itemScale).addDoneListener(() -> {
                                            moveMap(true);
                                        });
                                    } catch (Exception e) {

                                    }
                                }
                            }
                        }
                        isFirstInitMap = false;
                    }
                }
            }
        });
        mBinding.alMapview.setLayoutParams(
                new FrameLayout.LayoutParams(ScreenUtil.getScreenWidthPix(getBaseContext()),
                        ScreenUtil.getScreenHeightPix(getBaseContext()) - ScreenUtil.dp2px(getBaseContext(), 100)));
    }

    @Override
    public void initData() {
    }


    @Override
    protected void initListener() {
        mBinding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBinding.kblContainer.setOnkbdStateListener(new KeyboardLayout.onKeyboaddsChangeListener() {
            @Override
            public void onKeyBoardStateChange(int state) {
                switch (state) {
                    case KeyboardLayout.KEYBOARD_STATE_INIT:
                        break;
                    case KeyboardLayout.KEYBOARD_STATE_HIDE:
                        ToastUtils.shortToast("软键盘隐藏");
                        break;
                    case KeyboardLayout.KEYBOARD_STATE_SHOW:
                        ToastUtils.shortToast("软键盘弹起");
                        break;
                    default:
                        break;
                }
            }
        });
        mBinding.tvSaveAndNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                if ("2".equals(taskBean.getExecuteStatus()) && (taskBean.getExecuteId() != null && taskBean.getExecuteId().equals(UserManager.getInstance().getUserBean().getData().getUserCode()))) {
                    TaskItemDealFragment.ReturnData data = mFragment.getDealContent();
                    if (data.isFinish()) {
                        if (simpleLoadDialog != null){
                            simpleLoadDialog.show();
                        }
                        if (currentPoint != null) {
                            mPresenter.appReservoirWorkOrderItemCommitOne(data.getWorkOrderId(),
                                    data.getItemId(), data.getExResult(), data.getExDesc(), currentPoint.getX() + "",
                                    currentPoint.getY() + "", data.getFiles(), data.getEndfiles(), data.getDuringfiles(),
                                    true, ResUtils.getString(R.string.data_saving));
                        } else {
                            mPresenter.appReservoirWorkOrderItemCommitOne(data.getWorkOrderId(),
                                    data.getItemId(), data.getExResult(), data.getExDesc(), "",
                                    "", data.getFiles(), data.getEndfiles(), data.getDuringfiles(),
                                    true, ResUtils.getString(R.string.data_saving));
                        }
                    }
                } else {
                    finish();
//                    nextPage();
                }

            }
        });
    }



    @Override
    protected void initRequestData() {

    }

    private void refreshView(TaskBean taskBean) {
        List<TaskItemBean> taskItemBeanList = taskBean.getBizReservoirWorkOrderItems();
        if (taskItemBeanList == null || taskItemBeanList.size() == 0) {
            return;
        }

        initPositoinTitle();

        initFragment();
        initBottomBtn(taskBean);
        refreshMapView();
    }

    private TaskItemDealFragment mFragment;

    /**
     * 装载fragment
     */
    private void initFragment() {
        mFragment = TaskItemDealFragment.newInstance(taskBean, workOrderId, itemId, null, taskBean.getExecuteStatus());
        //获取管理者
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        //开启事务
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        //提交事务
        fragmentTransaction.replace(R.id.container_fragment, mFragment).commit();
    }

    private void refreshMapView() {
        mBinding.alMapview.removeGraphics();
        TaskItemBean taskItemBean = taskBean.getBizReservoirWorkOrderItems().get(position);
        try {
            {    //添加执行点
                if (TextUtils.isEmpty(taskItemBean.getExcuteLongitude()) || TextUtils.isEmpty(taskItemBean.getExcuteLatitude())) {
                } else {
                    double longitude = Double.parseDouble(taskItemBean.getExcuteLongitude());
                    double Latitude = Double.parseDouble(taskItemBean.getExcuteLatitude());
                    Point point1 = new Point(longitude, Latitude, SpatialReference.create(4326));
//                    Point point = (Point) GeometryEngine.project(point1, SpatialReferences.getWebMercator());
                    Map<String, Object> attrs = new HashMap<>();
                    attrs.put("taskItemBean", new Gson().toJson(taskItemBean));
                    mBinding.alMapview.addPic(R.drawable.icon_taskitem_exe, point1, attrs);
                    positionPoint = point1;
                }
            }
            {   //添加任务点
                if (TextUtils.isEmpty(taskItemBean.getPositionLongitude()) || TextUtils.isEmpty(taskItemBean.getPositionLatitude())) {
                } else {
                    double longitude = Double.parseDouble(taskItemBean.getPositionLongitude());
                    double Latitude = Double.parseDouble(taskItemBean.getPositionLatitude());
                    Point point1 = new Point(longitude, Latitude, SpatialReference.create(4326));
//                    Point point = (Point) GeometryEngine.project(point1, SpatialReferences.getWebMercator());
                    Map<String, Object> attrs = new HashMap<>();
                    attrs.put("taskItemBean", new Gson().toJson(taskItemBean));
                    mBinding.alMapview.addPic(R.drawable.icon_taskitem_temp, point1, attrs);
                    positionPoint = point1;
                }

            }
        } catch (Exception e) {
        }
        mBinding.alMapview.setOnAddPointClickListener(new ArcgisLayout.OnAddPointClickListener() {
            @Override
            public void onCilck(Point point, Map<String, Object> attributes) {
                if (mBinding.scrollDownLayout.getCurrentStatus() == ScrollLayout.Status.EXIT) {
                    mBinding.scrollDownLayout.setToOpen();
                }
                String temp = (String) attributes.get("taskItemBean");
                TaskItemBean taskItemBean = new Gson().fromJson(temp, TaskItemBean.class);
                mBinding.alMapview.showCallout(point, taskItemBean.getSuperviseItemName());
            }
        });
    }

    private Boolean isMoveUp = true;
    private Boolean isMoveDown = false;

    private void initScrllLayout() {

        mOnScrollChangedListener = new ScrollLayout.OnScrollChangedListener() {
            @Override
            public void onScrollProgressChanged(float currentProgress) {
                if (mBinding.tvFoot.getVisibility() == View.VISIBLE) {
                    mBinding.tvFoot.setVisibility(View.GONE);
                }
            }

            @Override
            public void onScrollFinished(ScrollLayout.Status currentStatus) {
                if (currentStatus.equals(ScrollLayout.Status.EXIT)) {
                    mBinding.tvFoot.setVisibility(View.VISIBLE);
                }

                if (currentStatus == ScrollLayout.Status.OPENED) {
//                    TaskItemDealFragment cur = (TaskItemDealFragment) adapter.getItem(position);
                    if (mFragment != null) {
                        mFragment.clearFocus();
                    }
                }
                changeMapViewLayoutParam(currentStatus);
                if (currentStatus == ScrollLayout.Status.EXIT) {
                    if (!isMoveDown) {
                        moveMap(false);
                        isMoveDown = true;
                    }
                    isMoveUp = false;
                } else if (currentStatus == ScrollLayout.Status.OPENED) {
                    if (!isMoveUp) {
                        moveMap(true);
                        isMoveUp = true;
                    }
                    isMoveDown = false;
                }
            }

            @Override
            public void onChildScroll(int top) {

            }
        };
        mBinding.tvFoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.scrollDownLayout.setToOpen();
            }
        });
        /**设置 setting*/
        mBinding.scrollDownLayout.setMinOffset(0);
        mBinding.scrollDownLayout.setMaxOffset((int) (ScreenUtil.getScreenPix(this).heightPixels * 0.6));
        mBinding.scrollDownLayout.setExitOffset(ScreenUtil.dp2px(this, 50));
        mBinding.scrollDownLayout.setIsSupportExit(true);
        mBinding.scrollDownLayout.setAllowHorizontalScroll(true);
        mBinding.scrollDownLayout.setOnScrollChangedListener(mOnScrollChangedListener);
        mBinding.scrollDownLayout.scrollToClose();
        mBinding.scrollDownLayout.getBackground().setAlpha(0);

        mBinding.scrollDownLayout.post(new Runnable() {
            @Override
            public void run() {
                mBinding.scrollDownLayout.setExitOffset(ScreenUtil.dp2px(getContext(), 100) + mBinding.loBottom.getHeight());
            }
        });
    }

    private void changeMapViewLayoutParam(ScrollLayout.Status currentStatus) {
        switch (currentStatus) {
            case EXIT:

                break;
            case OPENED:

                break;
            case CLOSED:
                break;
            default:
                break;

        }
    }

    private void initBottomBtn(TaskBean taskBean) {
        switch (taskBean.getExecuteStatus()) {
            case "1":
                mBinding.tvSaveAndNext.setText("退出");
                break;
            case "2":
                if (taskBean.getExecuteId() != null && taskBean.getExecuteId().equals(UserManager.getInstance().getUserBean().getData().getUserCode())) {
                    mBinding.tvSaveAndNext.setText("保存并退出");
                } else {
                    mBinding.tvSaveAndNext.setText("退出");
                }
                break;
            case "3":
                mBinding.tvSaveAndNext.setText("退出");
                break;
            default:
                mBinding.tvSaveAndNext.setText("退出");
                break;
        }
    }

    private void initPositoinTitle() {
        TaskItemBean taskItemBean = DataSupport.where("itemId=?", itemId).findFirst(TaskItemBean.class);
        String treeNames = taskItemBean.getPositionTreeNames();
        if (TextUtils.isEmpty(treeNames)) {
            return;
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBinding.rvPositionTitle.setLayoutManager(layoutManager);
        List<PositionNamesBean> positionNames = new ArrayList<>();
        if (treeNames.contains("/")) {
            String[] temps = TextUtils.split(treeNames, "/");
            for (int i = 0; i < temps.length; i++) {
                positionNames.add(new PositionNamesBean(temps[i]));
            }
        }
        AdapterPositionTitle adapterPositionTitle = new AdapterPositionTitle(R.layout.lv_item_position_title, positionNames);
        mBinding.rvPositionTitle.setAdapter(adapterPositionTitle);
    }

    @Override
    public void getTaskDetailSucess(TaskBean data) {
        taskBean = data;
        refreshView(data);
    }

    @Override
    public void commitSucess() {
//        nextPage();
    }

    @Override
    public void commitBack() {
        if (simpleLoadDialog!= null){
            simpleLoadDialog.dismiss();
        }
        finish();
    }

    @Override
    public void onResume() {
        super.onResume();
        mBinding.alMapview.onMapResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mBinding.alMapview.onMapPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        gaodeEntity.stopTrace();
        mBinding.alMapview.onMapDestroy();
        gaodeEntity.closeLocation();
        if (simpleLoadDialog!= null){
            simpleLoadDialog.dismiss();
        }
    }


    public void onFocusChange(View view, boolean b) {

        if (b) {
            if (mBinding.scrollDownLayout.getCurrentStatus() == ScrollLayout.Status.OPENED) {
                mBinding.scrollDownLayout.setToClosed();
            }
        } else {
//            if (mBinding.scrollDownLayout.getCurrentStatus() == ScrollLayout.Status.CLOSED) {
//                mBinding.scrollDownLayout.setToOpen();
//            }
        }
    }

    /**
     * 地图滑动
     *
     * @param status true上移  false 下移
     */
    private void moveMap(Boolean status) {
        try {
            MapView mapView = mBinding.alMapview.getMapView();
            android.graphics.Point screenPoint = new android.graphics.Point(0, 0);
            if (mapView.screenToLocation(screenPoint) != null) {
                double y = mapView.screenToLocation(screenPoint).getY();
                android.graphics.Point bottomPoint = new android.graphics.Point(0, mBinding.alMapview.mapHeight);
                double bottomY = mapView.screenToLocation(bottomPoint).getY();
                double translationY = (bottomY - y) / 2 - (bottomY - y) / 4;
//                mapView.visibleArea   返回一个Polygon，表示当前在MapView中可见的ArcGISMap区域。
                Viewpoint viewPoint = mapView.getCurrentViewpoint(Viewpoint.Type.CENTER_AND_SCALE);
                Point centerPoint = viewPoint.getTargetGeometry().getExtent().getCenter();
                if (status) {
                    //中心点上移
                    mapView.setViewpointCenterAsync(new Point(centerPoint.getX(), centerPoint.getY() + translationY));
                } else {
                    //下移
                    mapView.setViewpointCenterAsync(new Point(centerPoint.getX(), centerPoint.getY() - translationY));
                }
            }
        } catch (Exception e) {

        }
    }
}
