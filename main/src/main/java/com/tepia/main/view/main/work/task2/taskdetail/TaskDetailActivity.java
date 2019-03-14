package com.tepia.main.view.main.work.task2.taskdetail;


import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.amap.api.location.AMapLocation;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.esri.arcgisruntime.geometry.GeometryEngine;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.SpatialReference;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.mapping.view.DrawStatus;
import com.esri.arcgisruntime.mapping.view.DrawStatusChangedEvent;
import com.esri.arcgisruntime.mapping.view.DrawStatusChangedListener;
import com.esri.arcgisruntime.symbology.SimpleLineSymbol;
import com.example.gaodelibrary.GPSUtil;
import com.example.gaodelibrary.GaodeEntity;
import com.example.gaodelibrary.OnGaodeLibraryListen;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.base.utils.DoubleClickUtil;
import com.tepia.base.utils.ResUtils;
import com.tepia.base.utils.SPUtils;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.view.arcgisLayout.ArcgisLayout;
import com.tepia.base.view.dialog.basedailog.ActionSheetDialog;
import com.tepia.base.view.dialog.basedailog.OnOpenItemClick;
import com.tepia.base.view.dialog.loading.LoadingDialog;
import com.tepia.main.R;
import com.tepia.main.databinding.ActivityTaskDetailBinding;
import com.tepia.main.model.route.RoutepointDataBean;
import com.tepia.main.model.route.RoutepointDataManager;
import com.tepia.main.model.task.bean.PeopleBean;
import com.tepia.main.model.task.bean.TaskBean;
import com.tepia.main.model.task.bean.TaskItemBean;
import com.tepia.main.utils.OSUtils;
import com.tepia.main.utils.XiaomiDeviceUtil;
import com.tepia.main.view.main.work.task.taskdetail.AdapterTaskItemList;
import com.tepia.main.view.main.work.task.taskdetail.TaskDetailContract;
import com.tepia.main.view.main.work.task.taskdetail.TaskDetailPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 任务详情
 * 邮箱 784787081@qq.com
 *
 * @author Joeshould
 */
@Route(path = AppRoutePath.app_task_detail_2)
public class TaskDetailActivity extends MVPBaseActivity<TaskDetailContract.View, TaskDetailPresenter> implements TaskDetailContract.View {
    @Autowired(name = "workOrderId")
    String id;
    @Autowired(name = "taskBean")
    String temp;
    TaskBean taskBean;


    private AdapterTaskItemList adapterTaskItemList;
    private ActivityTaskDetailBinding mBinding;
    private Point currentPoint;
    private boolean isFirstInitMap = false;
    private int initCount;
    private ArrayList exeline2;
    private List<PeopleBean> peopleList;
    private Point positionPoint;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isFirstInitMap = true;
        initCount = 0;

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_task_detail;
    }

    @Override
    public void initView() {
        mBinding = DataBindingUtil.bind(mRootView);
        if (taskBean == null && TextUtils.isEmpty(temp)) {
            taskBean = new Gson().fromJson(temp, TaskBean.class);
        }
        initListView();
//        initMapView();
        initMapView2();

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

        gaodeEntity.setLocationListen(new OnGaodeLibraryListen.LocationListen() {
            @Override
            public void getCurrentGaodeLocation(AMapLocation aMapLocation) {
                if (gaodeEntity != null) {
                    double[] temp = GPSUtil.gcj02_To_Gps84(aMapLocation.getLatitude(), aMapLocation.getLongitude());
                    double latitude = temp[0];//坐标经度
                    double longitude = temp[1];//坐标纬度
                    if (latitude == 0 || longitude == 0) {
                        return;
                    }
                    currentPoint = new Point(longitude, latitude, SpatialReference.create(4326));
                    if ("2".equals(taskBean.getExecuteStatus())) {
                        initCount++;
                        RoutepointDataManager.getInstance().addPoint(new RoutepointDataBean(id, longitude + "", latitude + ""));
                        if (initCount < 3) {
                            refreshMapView();
                        } else {
                            Point point1 = (Point) GeometryEngine.project(currentPoint, SpatialReferences.getWebMercator());
                            if (exeline2 != null) {
                                exeline2.add(point1);
                                mBinding.alMapview.addPolyline(exeline2, SimpleLineSymbol.Style.SOLID, Color.RED, 6);
                                refreshMapViewPoint();
                            }
                        }
                    }
                }
            }
        });

    }

    private void initMapView2() {
        getGaoDeLocation();
        mBinding.alMapview.startLocation();
        mBinding.alMapview.getMapView().addDrawStatusChangedListener(new DrawStatusChangedListener() {
            @Override
            public void drawStatusChanged(DrawStatusChangedEvent drawStatusChangedEvent) {
                DrawStatus drawStatus = drawStatusChangedEvent.getDrawStatus();
                if (drawStatus == DrawStatus.COMPLETED) {
                    if (isFirstInitMap) {
                        if (currentPoint != null) {
                            if (taskBean.getExecuteStatus().equals("2")) {
                                mBinding.alMapview.getMapView().setViewpointCenterAsync(currentPoint, mBinding.alMapview.itemScale);
                            } else {
                                if (positionPoint != null) {
                                    mBinding.alMapview.getMapView().setViewpointCenterAsync(positionPoint, mBinding.alMapview.itemScale);
                                }
                            }
                        }
                        isFirstInitMap = false;
                    }
                }
            }
        });
        mBinding.alMapview.setOnAddLocationChangedListener(new ArcgisLayout.OnAddLocationChangedListener() {
            @Override
            public void getLocation(Point point) {
                if (point == null) {
                    return;
                }
                if (!"2".equals(taskBean.getExecuteStatus())) {
                    currentPoint = point;
                }
            }
        });
    }

    private void initMapView() {
        mBinding.alMapview.startLocation();

        mBinding.alMapview.setOnAddLocationChangedListener(new ArcgisLayout.OnAddLocationChangedListener() {
            @Override
            public void getLocation(Point point) {
                if (point == null) {
                    return;
                }
                currentPoint = point;
                if ("2".equals(taskBean.getExecuteStatus())) {
                    initCount++;
                    if (initCount > 10 && initCount < 12) {
                        RoutepointDataManager.getInstance().addPoint(new RoutepointDataBean(id, point.getX() + "", point.getY() + ""));
                        refreshMapView();
                    } else if (initCount > 12) {
                        RoutepointDataManager.getInstance().addPoint(new RoutepointDataBean(id, point.getX() + "", point.getY() + ""));
                        Point point1 = (Point) GeometryEngine.project(point, SpatialReferences.getWebMercator());
                        mBinding.alMapview.addPolylineByPoint(point1, SimpleLineSymbol.Style.SOLID, Color.RED, 6);
                    }
                }
            }
        });

        mBinding.alMapview.getMapView().addDrawStatusChangedListener(new DrawStatusChangedListener() {
            @Override
            public void drawStatusChanged(DrawStatusChangedEvent drawStatusChangedEvent) {
                DrawStatus drawStatus = drawStatusChangedEvent.getDrawStatus();
                if (drawStatus == DrawStatus.COMPLETED) {
                    if (isFirstInitMap) {
                        if (currentPoint != null) {
                            mBinding.alMapview.setCenterPoint(currentPoint, mBinding.alMapview.itemScale);
                        }
                        isFirstInitMap = false;
                    }
                }
            }
        });

    }

    private void initListView() {
        mBinding.rvTaskItemList.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.rvTaskItemList.setNestedScrollingEnabled(false);
        adapterTaskItemList = new AdapterTaskItemList(getContext(), R.layout.lv_item_task_item_list, null);
        mBinding.rvTaskItemList.setAdapter(adapterTaskItemList);
        adapterTaskItemList.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if ("1".equals(taskBean.getExecuteStatus())) {
                    ToastUtils.shortToast("请点击下方执行任务");
                } else {
                    ARouter.getInstance().build(AppRoutePath.app_task_deal)
                            .withString("workOrderId", id)
                            .withInt("position", position)
                            .withString("taskBean", new Gson().toJson(taskBean))
                            .navigation();
                }
            }
        });
    }


    @Override
    public void initData() {
//        String type = PhoneTypeUtil.getSystem();
        boolean is_xiaomi = OSUtils.ROM_TYPE.MIUI.name().equals(OSUtils.getRomType().name());

        boolean hasset = SPUtils.getInstance(ResUtils.getContext()).getBoolean("go_set", false);

        if (is_xiaomi && !hasset) {
            android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
            builder.setTitle(R.string.xiaomiMind);
            builder.setMessage(R.string.whiteCard);
            builder.setCancelable(false);
            builder.setPositiveButton(R.string.go_set, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    SPUtils.getInstance(ResUtils.getContext()).putBoolean("go_set", true);
                    XiaomiDeviceUtil.toConfigApp(TaskDetailActivity.this, XiaomiDeviceUtil.getAppProcessName(ResUtils.getContext()), getString(R.string.app_name));
                }
            });
            builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            builder.create().show();

        }
    }

    @Override
    protected void initListener() {
        mBinding.ivChangeMapList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                if (mBinding.alMapview.getVisibility() == View.GONE) {
                    mBinding.alMapview.setVisibility(View.VISIBLE);
                    mBinding.nsvContainer.setVisibility(View.GONE);
                    mBinding.ivChangeMapList.setImageResource(R.drawable.icon_list);
                } else {
                    mBinding.alMapview.setVisibility(View.GONE);
                    mBinding.nsvContainer.setVisibility(View.VISIBLE);
                    mBinding.ivChangeMapList.setImageResource(R.drawable.icon_map);
                }
            }
        });

        mBinding.tvTaskEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                ARouter.getInstance().build(AppRoutePath.app_task_edit)
                        .withString("workOrderId", id)
                        .withString("taskBean", new Gson().toJson(taskBean))
                        .navigation();
            }
        });
        mBinding.tvSendTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                if (peopleList == null) {
                    mPresenter.getPeople(taskBean.getWorkOrderId());
                } else {
                    showSelectPeople();
                }

            }
        });
    }

    private void showSelectPeople() {
        if (peopleList != null && peopleList.size() != 0) {
            String[] stringItems = new String[peopleList.size()];
            for (int i = 0; i < peopleList.size(); i++) {
                stringItems[i] = peopleList.get(i).getUserName();
            }
            final ActionSheetDialog dialog = new ActionSheetDialog(TaskDetailActivity.this, stringItems, null);
            dialog.title("请选择执行人")
                    .titleTextSize_SP(14.5f)
                    .widthScale(0.8f)
                    .show();
            dialog.setOnOpenItemClickL(new OnOpenItemClick() {
                @Override
                public void onOpenItemClick(AdapterView<?> parent, View view, int position, long id) {

                    dialog.dismiss();
                    mPresenter.sendOrder(taskBean.getWorkOrderId(), peopleList.get(position).getUserCode());
                }
            });
        }
    }


    @Override
    protected void initRequestData() {
        mImmersionBar.titleBar(mBinding.loTitle).init();
        setStatusBarTextDark();
        if (taskBean == null) {
            taskBean = new Gson().fromJson(temp, TaskBean.class);
        }
        refreshView();

        if (taskBean == null) {
            mPresenter.getTaskDetail(id, false, getString(R.string.data_loading));
        } else {
            mPresenter.getTaskDetail(id, false, "");
        }
    }

    @Override
    public void getTaskDetailSucess(TaskBean data) {
        this.taskBean = data;
        refreshView();

    }

    @Override
    public void onBackPressed() {
        if (mBinding.alMapview.getVisibility() == View.GONE) {
            super.onBackPressed();
        } else {
            mBinding.alMapview.setVisibility(View.GONE);
            mBinding.nsvContainer.setVisibility(View.VISIBLE);
            mBinding.ivChangeMapList.setImageResource(R.drawable.icon_map);
        }
    }

    @Override
    public void startExecuteSucess() {
        mPresenter.getTaskDetail(id, true, ResUtils.getString(R.string.data_saving));
    }

    @Override
    public void endExecuteSucess() {
        mPresenter.getTaskDetail(id, true, ResUtils.getString(R.string.data_saving));
    }

    @Override
    public void getPeopleSucess(List<PeopleBean> data) {
        peopleList = data;
        showSelectPeople();
    }

    @Override
    public void sendOrderSucess() {
        mPresenter.getTaskDetail(id, true, ResUtils.getString(R.string.data_saving));
    }

    @Override
    public void appReservoirWorkOrderItemCommitOneByOneSuccess() {

    }

    /**
     * 跟新ui
     */
    private void refreshView() {
        if (taskBean == null) {
            return;
        }
        if ("2".equals(taskBean.getExecuteStatus())) {
            if (!gaodeEntity.isIs_trace_started()) {
                gaodeEntity.startTrace();
            }
        } else {
            if (gaodeEntity.isIs_trace_started()) {
                gaodeEntity.stopTrace();
            }
        }
        if (mBinding.alMapview.getVisibility() == View.GONE) {
            mBinding.ivChangeMapList.setImageResource(R.drawable.icon_map);
        } else {
            mBinding.ivChangeMapList.setImageResource(R.drawable.icon_list);
        }
        mBinding.tvTaskTitle.setText(taskBean.getWorkOrderName());
        if (taskBean.getBizPlanInfo() != null) {
            switch (taskBean.getBizPlanInfo().getOperationType()) {
                case "1":
                    mBinding.tvTaskType.setText(ResUtils.getString(R.string.xunjianstr));
                    break;
                case "2":
                    mBinding.tvTaskType.setText(ResUtils.getString(R.string.weihustr));
                    break;
                case "3":
                    mBinding.tvTaskType.setText(ResUtils.getString(R.string.baojiestr));
                    break;
                default:
                    break;
            }
        }

        if (taskBean.getPlanStartTime().length() == 19) {
            taskBean.setPlanStartTime(taskBean.getPlanStartTime().substring(0, 16));
        }
        if (taskBean.getPlanEndTime().length() == 19) {
            taskBean.setPlanEndTime(taskBean.getPlanEndTime().substring(0, 16));
        }
        mBinding.tvTaskTime.setText("" + taskBean.getPlanStartTime() + "~" + taskBean.getPlanEndTime());
        if (!TextUtils.isEmpty(taskBean.getRemarks())) {
            mBinding.tvTaskDesc.setText("" + taskBean.getRemarks());
        } else if (!TextUtils.isEmpty(taskBean.getBizPlanInfo().getPlanName())) {
            mBinding.tvTaskDesc.setText("" + taskBean.getBizPlanInfo().getPlanName());
        }
        if (taskBean.getBizReservoirWorkOrderItems() != null) {
            adapterTaskItemList.setNewData(taskBean.getBizReservoirWorkOrderItems());
            refreshMapView();
        }

        switch (taskBean.getExecuteStatus()) {
            case "1":
                mBinding.tvTaskExecStatus.setText(ResUtils.getString(R.string.text_task_status_dzx));
                mBinding.tvDoTask.setEnabled(true);
                mBinding.tvDoTask.setText("执行任务");
                mBinding.tvDoTask.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (DoubleClickUtil.isFastDoubleClick()) {
                            return;
                        }
                        mPresenter.startExecute(id, null, true, ResUtils.getString(R.string.data_saving));
                    }
                });
                mBinding.loEditAndSend.setVisibility(View.GONE);
                mBinding.tvDoTask.setVisibility(View.VISIBLE);
                break;
            case "2":
                mBinding.tvTaskExecStatus.setText(ResUtils.getString(R.string.text_task_status_zxz));
                mBinding.tvDoTask.setEnabled(true);
                mBinding.tvDoTask.setText("执行任务");
                if (adapterTaskItemList != null && adapterTaskItemList.isDealFinish()) {
                    mBinding.tvDoTask.setText("提交");
                    mBinding.tvDoTask.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (DoubleClickUtil.isFastDoubleClick()) {
                                return;
                            }
                            LoadingDialog.with(getContext()).setMessage(ResUtils.getString(R.string.data_saving)).show();
                            String temp = RoutepointDataManager.getInstance().getRoutePointListString(id);
                            if (taskBean.getIsProcess() != null &&taskBean.getIsProcess().equals("0")) {
                                mPresenter.endExecute2(id, temp, false, ResUtils.getString(R.string.data_saving));
                            } else {
                                mPresenter.endExecute(id, temp, false, ResUtils.getString(R.string.data_saving));
                            }
                        }
                    });
                } else {
                    mBinding.tvDoTask.setText("执行任务");
                    mBinding.tvDoTask.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (adapterTaskItemList != null && adapterTaskItemList.getData() != null
                                    && !adapterTaskItemList.isDealFinish()
                                    && adapterTaskItemList.getData().size() != 0) {
                                ARouter.getInstance().build(AppRoutePath.app_task_deal)
                                        .withString("workOrderId", id)
                                        .withInt("position", adapterTaskItemList.getFirstExePositoin())
                                        .withString("taskBean", new Gson().toJson(taskBean))
                                        .navigation();
                            } else {
                                ToastUtils.shortToast("没有任务项 不能执行任务");
                            }
                        }
                    });

                }
                mBinding.loEditAndSend.setVisibility(View.GONE);
                mBinding.tvDoTask.setVisibility(View.VISIBLE);
                break;
            case "3":
                mBinding.tvTaskExecStatus.setText(ResUtils.getString(R.string.text_task_status_ywc));
                mBinding.tvDoTask.setVisibility(View.GONE);
                mBinding.loEditAndSend.setVisibility(View.GONE);
                break;
            case "4":
                mBinding.tvTaskExecStatus.setText(ResUtils.getString(R.string.text_task_status_ysh));
                mBinding.tvDoTask.setVisibility(View.GONE);
                mBinding.loEditAndSend.setVisibility(View.GONE);
                break;
            case "5":
                mBinding.tvTaskExecStatus.setText("已生成报告");
                mBinding.tvDoTask.setVisibility(View.GONE);
                mBinding.loEditAndSend.setVisibility(View.GONE);
                break;
            case "0":
                mBinding.tvTaskExecStatus.setText("未下发");
                mBinding.tvDoTask.setVisibility(View.GONE);
                mBinding.loEditAndSend.setVisibility(View.VISIBLE);
                break;
            default:
                mBinding.tvTaskExecStatus.setText(ResUtils.getString(R.string.text_task_status_ysh));
                mBinding.tvDoTask.setVisibility(View.GONE);
                mBinding.loEditAndSend.setVisibility(View.GONE);
                break;
        }

    }

    private void refreshMapView() {
        if (taskBean == null) {
            return;
        }
        if (taskBean.getBizReservoirWorkOrderItems() == null) {
            return;
        }
        mBinding.alMapview.removeGraphics();
        mBinding.alMapview.post(new Runnable() {
            @Override
            public void run() {
                if (!TextUtils.isEmpty(taskBean.getWorkOrderRoute())) {
                    if (TextUtils.isEmpty(taskBean.getExecuteStatus())) {
                        return;
                    }
                    if (!"1".equals(taskBean.getExecuteStatus()) && !"2".equals(taskBean.getExecuteStatus())) {
                        refreshMapViewList2(taskBean.getWorkOrderRoute());
                    } else {
                        refreshMapViewList();
                    }
                } else {
                    refreshMapViewList();
                }
                refreshMapViewPoint();
            }
        });


    }

    private void refreshMapViewPoint() {
        List<Point> exeLine = new ArrayList<>();
        for (TaskItemBean taskItemBean : taskBean.getBizReservoirWorkOrderItems()) {
            if (TextUtils.isEmpty(taskItemBean.getExcuteLongitude()) || TextUtils.isEmpty(taskItemBean.getExcuteLatitude())) {
            } else {
                double longitude = Double.parseDouble(taskItemBean.getExcuteLongitude());
                double Latitude = Double.parseDouble(taskItemBean.getExcuteLatitude());
                Point point1 = new Point(longitude, Latitude, SpatialReference.create(4326));
                Point point = (Point) GeometryEngine.project(point1, SpatialReferences.getWebMercator());
                Map<String, Object> attrs = new HashMap<>();
                attrs.put("taskItemBean", new Gson().toJson(taskItemBean));
                mBinding.alMapview.addPic(R.drawable.icon_taskitem_exe, point, attrs);
                positionPoint = point;
                exeLine.add(point);
            }
            if (TextUtils.isEmpty(taskItemBean.getPositionLongitude()) || TextUtils.isEmpty(taskItemBean.getPositionLatitude())) {
            } else {
                double longitude = Double.parseDouble(taskItemBean.getPositionLongitude());
                double Latitude = Double.parseDouble(taskItemBean.getPositionLatitude());
                Point point1 = new Point(longitude, Latitude, SpatialReference.create(4326));
                Point point = (Point) GeometryEngine.project(point1, SpatialReferences.getWebMercator());
                Map<String, Object> attrs = new HashMap<>();
                attrs.put("taskItemBean", new Gson().toJson(taskItemBean));
                positionPoint = point;
                mBinding.alMapview.addPic(R.drawable.icon_taskitem_temp, point, attrs);
            }
        }
        mBinding.alMapview.setOnAddPointClickListener(new ArcgisLayout.OnAddPointClickListener() {
            @Override
            public void onCilck(Point point, Map<String, Object> attributes) {
                String temp = (String) attributes.get("taskItemBean");
                TaskItemBean taskItemBean = new Gson().fromJson(temp, TaskItemBean.class);
                int position = taskItemBean.getReservoirSuperviseSequence() - 1;
                if ("1".equals(taskBean.getExecuteStatus())) {
                    ToastUtils.shortToast("请点击下方执行任务");
                } else {
                    ARouter.getInstance().build(AppRoutePath.app_task_deal)
                            .withString("workOrderId", id)
                            .withInt("position", position)
                            .withString("taskBean", new Gson().toJson(taskBean))
                            .navigation();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        gaodeEntity.stopTrace();
        gaodeEntity.closeLocation();
    }

    private void refreshMapViewList2(String workOrderRoute) {
        if (TextUtils.isEmpty(workOrderRoute)) {
            return;
        }
        String temp = workOrderRoute.replace("{", "[");
        temp = temp.replace("}", "]");
        try {
            List<List<Double>> list = new Gson().fromJson(temp, new TypeToken<List<List<Double>>>() {
            }.getType());
            if (list != null && list.size() >= 2) {
                ArrayList exeline = new ArrayList();
                for (List<Double> bean : list) {
                    if (bean == null || bean.size() < 2) {
                        continue;
                    }
                    double longitude = bean.get(0);
                    double Latitude = bean.get(1);
                    Point point1 = new Point(longitude, Latitude, SpatialReference.create(4326));
                    Point point = (Point) GeometryEngine.project(point1, SpatialReferences.getWebMercator());
                    exeline.add(point);
                }
                mBinding.alMapview.addPolyline(exeline, SimpleLineSymbol.Style.SOLID, Color.RED, 6);
            }
        } catch (Exception e) {
        }

    }

    private void refreshMapViewList() {
        List<RoutepointDataBean> list = RoutepointDataManager.getInstance().getRoutePointListWithOutD(id);
        if (list != null && list.size() >= 2) {
            exeline2 = new ArrayList();
            for (RoutepointDataBean bean : list) {
                double longitude = Double.parseDouble(bean.getLgtd());
                double Latitude = Double.parseDouble(bean.getLttd());
                Point point1 = new Point(longitude, Latitude, SpatialReference.create(4326));
                Point point = (Point) GeometryEngine.project(point1, SpatialReferences.getWebMercator());
                exeline2.add(point);
            }
            try {
                mBinding.alMapview.addPolyline(exeline2, SimpleLineSymbol.Style.SOLID, Color.RED, 6);
            } catch (Exception e) {
            }
        }
    }
}
