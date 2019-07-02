package com.tepia.main.view.main.work.task.taskdetail;


import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.AdapterView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClientOption;
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
import com.tepia.base.utils.AppManager;
import com.tepia.base.utils.DoubleClickUtil;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.NetUtil;
import com.tepia.base.utils.ResUtils;
import com.tepia.base.utils.SPUtils;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.view.arcgisLayout.ArcgisLayout;
import com.tepia.base.view.dialog.basedailog.ActionSheetDialog;
import com.tepia.base.view.dialog.basedailog.NormalDialog;
import com.tepia.base.view.dialog.basedailog.OnOpenItemClick;
import com.tepia.base.view.dialog.loading.LoadingDialog;
import com.tepia.base.view.dialog.loading.SimpleLoadDialog;
import com.tepia.base.view.floatview.CollectionsUtil;
import com.tepia.main.R;
import com.tepia.main.databinding.ActivityTaskDetailBinding;
import com.tepia.main.model.route.RoutepointDataBean;
import com.tepia.main.model.route.RoutepointDataManager;
import com.tepia.main.model.task.bean.PeopleBean;
import com.tepia.main.model.task.bean.TaskBean;
import com.tepia.main.model.task.bean.TaskItemBean;
import com.tepia.main.utils.LocationUtils;
import com.tepia.main.utils.OSUtils;
import com.tepia.main.utils.XiaomiDeviceUtil;
import com.tepia.main.view.maincommon.setting.DownLoadActivity;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author :       zhang xinhua
 * @Version :       1.0
 * @创建人 ：      zhang xinhua
 * @创建时间 :       2019/3/11 12:07
 * @修改人 ：
 * @修改时间 :       2019/3/11 12:07
 * @功能描述 :        任务详情
 **/
@Route(path = AppRoutePath.app_task_detail)
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
    private ArrayList<Point> exeline2;
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
            refreshView();
        }
        initListView();
//        initMapView();
        getGaoDeLocation();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                initMapView2();
            }
        },2000);

        gpsCheck();
    }

    /**
     * 检查gps是否开启
     */
    private void gpsCheck() {
        if (!LocationUtils.isGpsEnabled(this)) {
            NormalDialog dialog = new NormalDialog(this);
            dialog.content("您需要在系统设置中打开GPS以提高定位精度")
                    .style(NormalDialog.STYLE_TWO)
                    .btnNum(2)
                    .btnText("知道了", "去设置")
                    .show();
            dialog.setOnBtnClickL(
                    () -> dialog.dismiss(),
                    () -> {
                        LocationUtils.openGpsSettings(this);
                        dialog.dismiss();
                    }
            );
        }
    }

    /**
     * 高德地图定位
     */
    private GaodeEntity gaodeEntity;

    private void getGaoDeLocation() {
        if (gaodeEntity == null) {
            gaodeEntity = new GaodeEntity(this, TaskDetailActivity.class, R.mipmap.logo);
            //支持离线模式定位，并在有网时使用网络定位
            gaodeEntity.getLocationClient().setLocationOption(getDefaultOption(5000));
        }
        if (taskBean != null && !TextUtils.isEmpty(taskBean.getExecuteStatus()) && com.tepia.main.model.user.UserManager.getInstance().getUserBean().getData().getUserCode().equals(taskBean.getExecuteId())) {
            gaodeEntity.setLocationListen(new OnGaodeLibraryListen.LocationListen() {
                @Override
                public void getCurrentGaodeLocation(AMapLocation aMapLocation) {
                    if (gaodeEntity != null) {
                        double[] temp = GPSUtil.gcj02_To_Gps84(aMapLocation.getLatitude(), aMapLocation.getLongitude());
                        double latitude = temp[0];//坐标经度
                        double longitude = temp[1];//坐标纬度
                        LogUtil.e(TaskDetailActivity.class.getName(), "经度：" + longitude);
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
//                                Point point1 = (Point) GeometryEngine.project(currentPoint, SpatialReferences.getWebMercator());
                                if (exeline2 != null) {
                                    exeline2.add(currentPoint);
                                    mBinding.alMapview.addPolyline(exeline2, SimpleLineSymbol.Style.SOLID, Color.RED, 6);
                                    refreshMapViewPoint();
                                }
                            }
                        }
                    }
                }
            });
        }

    }

    public AMapLocationClientOption getDefaultOption(int interval) {
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
        mOption.setInterval(interval);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(false);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
        return mOption;
    }

    private void initMapView2() {
        mBinding.alMapview.startLocation();
        mBinding.alMapview.getMapView().addDrawStatusChangedListener(new DrawStatusChangedListener() {
            @Override
            public void drawStatusChanged(DrawStatusChangedEvent drawStatusChangedEvent) {
                DrawStatus drawStatus = drawStatusChangedEvent.getDrawStatus();
                if (drawStatus == DrawStatus.COMPLETED) {
                    mBinding.alMapview.post(new Runnable() {
                        @Override
                        public void run() {
                            if (isFirstInitMap) {
                                if (taskBean == null) {
                                    return;
                                }
                                if ("2".equals(taskBean.getExecuteStatus())) {
                                    if (currentPoint != null) {
                                        mBinding.alMapview.setCenterPoint(currentPoint, ArcgisLayout.maxScale);
                                        isFirstInitMap = false;
                                    } else {
                                        mBinding.alMapview.postDelayed(this, 1500);
                                    }
                                } else {

                                }
                                if (positionPoint != null) {
                                    mBinding.alMapview.setCenterPoint(positionPoint,ArcgisLayout.maxScale*8);
                                    isFirstInitMap = false;
                                } else {
                                    mBinding.alMapview.postDelayed(this, 500);
                                }
                            }
                        }
                    });

                }
            }
        });
       /* mBinding.alMapview.setOnAddLocationChangedListener(new ArcgisLayout.OnAddLocationChangedListener()

        {
            @Override
            public void getLocation(Point point) {

                if (point == null) {
                    return;
                }

                if (taskBean == null) {
                    return;
                }
               *//* if (point != null && taskBean != null && !"2".equals(taskBean.getExecuteStatus())) {
                    currentPoint = point;
                }*//*
                if (!"2".equals(taskBean.getExecuteStatus())) {
                    currentPoint = point;
                }
            }
        });*/
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
        mBinding.tvDoTask.setVisibility(View.GONE);
        if (taskBean == null) {
            taskBean = new Gson().fromJson(temp, TaskBean.class);
        }
        refreshView();

        if (taskBean == null) {
            mPresenter.getTaskDetail(id, true, getString(R.string.data_loading));
        } else {
            mPresenter.getTaskDetail(id, false, "");
        }
    }

    @Override
    public void getTaskDetailSucess(TaskBean data) {
        this.taskBean = data;
        refreshView();
        getGaoDeLocation();
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
        String temp = RoutepointDataManager.getInstance().getRoutePointListString(id);
        if (taskBean.getIsProcess() != null && "1".equals(taskBean.getIsProcess())) {
            mPresenter.endExecute2(id, temp, false, ResUtils.getString(R.string.data_saving));
        } else {
            mPresenter.endExecute(id, temp, false, ResUtils.getString(R.string.data_saving));
        }
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
                if (taskBean != null && !TextUtils.isEmpty(taskBean.getExecuteStatus()) && com.tepia.main.model.user.UserManager.getInstance().getUserBean().getData().getUserCode().equals(taskBean.getExecuteId())) {
                    gaodeEntity.startTrace();
                }
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


        if (taskBean.getPlanStartTime() != null && taskBean.getPlanStartTime().length() == 19) {
            taskBean.setPlanStartTime(taskBean.getPlanStartTime().substring(0, 16));
        }
        if (taskBean.getPlanEndTime() != null && taskBean.getPlanEndTime().length() == 19) {
            taskBean.setPlanEndTime(taskBean.getPlanEndTime().substring(0, 16));
        }
        mBinding.tvTaskTime.setText("" + taskBean.getPlanStartTime() + "~" + taskBean.getPlanEndTime());
        if (!TextUtils.isEmpty(taskBean.getRemarks())) {
            mBinding.tvTaskDesc.setText("" + taskBean.getRemarks());
        } else if (taskBean.getBizPlanInfo() != null && !TextUtils.isEmpty(taskBean.getBizPlanInfo().getPlanName())) {
            mBinding.tvTaskDesc.setText("" + taskBean.getBizPlanInfo().getPlanName());
        } else {
            mBinding.loTaskDesc.setVisibility(View.GONE);
        }
        if (taskBean.getBizReservoirWorkOrderItems() != null) {
            SimpleLoadDialog simpleLoadDialog = new SimpleLoadDialog(this,ResUtils.getString(R.string.data_loading),true);
            simpleLoadDialog.show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    adapterTaskItemList.setNewData(taskBean.getBizReservoirWorkOrderItems());
                    if (simpleLoadDialog != null) {
                        simpleLoadDialog.dismiss();
                    }
                    refreshMapView();
                }
            },1000);

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

                            showTjDialog();


                        }
                    });
                } else {
                    mBinding.tvDoTask.setText("执行任务");
                    mBinding.tvDoTask.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (adapterTaskItemList != null && adapterTaskItemList.getData() != null
                                    && !adapterTaskItemList.isDealFinish()
                                    && !CollectionsUtil.isEmpty(adapterTaskItemList.getData())) {
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
                if (taskBean.getExecuteId() != null && taskBean.getExecuteId().equals(com.tepia.main.model.user.UserManager.getInstance().getUserBean().getData().getUserCode())) {
                    mBinding.tvDoTask.setVisibility(View.VISIBLE);
                } else {
                    mBinding.tvDoTask.setVisibility(View.GONE);
                }
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
                if (!TextUtils.isEmpty(taskBean.getWorkReportUrl())) {
                    mBinding.tvLookWorkReport.setVisibility(View.VISIBLE);
                    mBinding.tvLookWorkReport.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (DoubleClickUtil.isFastDoubleClick()) {
                                return;
                            }
//                            ARouter.getInstance()
//                                    .build(AppRoutePath.app_task_work_report)
//                                    .withString("workReportUrl",taskBean.getWorkReportUrl())
//                                    .navigation();

//                            final Intent intent = new Intent();
//                            intent.setAction(Intent.ACTION_VIEW);
//                            intent.setData(Uri.parse(taskBean.getWorkReportUrl()));
//                            // 注意此处的判断intent.resolveActivity()可以返回显示该Intent的Activity对应的组件名
//                            // 官方解释 : Name of the component implementing an activity that can display the intent
//                            if (intent.resolveActivity(context.getPackageManager()) != null) {
//                                final ComponentName componentName = intent.resolveActivity(context.getPackageManager());
//                                // 打印Log   ComponentName到底是什么
//
//                                context.startActivity(Intent.createChooser(intent, "请选择浏览器"));
//                            } else {
//                                Toast.makeText(context.getApplicationContext(), "请下载浏览器", Toast.LENGTH_SHORT).show();
//                            }

//
                            Intent intent = new Intent(getContext(), DownLoadActivity.class);
                            DownLoadActivity.setIntent(intent, taskBean.getWorkReportUrl(), taskBean.getWorkReportUrl());
                            getContext().startActivity(intent);
                        }
                    });
                } else {
                    mBinding.tvLookWorkReport.setVisibility(View.GONE);
                }
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

    private void showTjDialog() {
        SpannableString spannableString;
        if (adapterTaskItemList.getAbnormalityList().size() > 0) {
            String title = "有 " + adapterTaskItemList.getAbnormalityList().size() + " 项异常项是否确定提交？  ";
            if (!CollectionsUtil.isEmpty(adapterTaskItemList.getLocalData())) {
                title = title + "\n有" + adapterTaskItemList.getLocalData().size() + "项离线数据没有提交，请在网络较好的情况下完成此操作";
            }
            spannableString = new SpannableString(title);
            //设置颜色
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FE6026")), 2, 2 + (adapterTaskItemList.getAbnormalityList().size() + "").length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        } else {
            String title = "没有异常项是否确定提交？";
            if (!CollectionsUtil.isEmpty(adapterTaskItemList.getLocalData())) {
                title = title + "\n有" + adapterTaskItemList.getLocalData().size() + "项离线数据没有提交，请在网络较好的情况下完成此操作";
            }
            spannableString = new SpannableString(title);
        }

        TjDialogFragment tjDialogFragment = new TjDialogFragment();
        tjDialogFragment.tip = spannableString;
        tjDialogFragment.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                tjDialogFragment.dismiss();

                commitTotal();


            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                tjDialogFragment.dismiss();
                if (adapterTaskItemList.getAbnormalityList().size() > 0) {
                    int count = adapterTaskItemList.getData().indexOf(adapterTaskItemList.getAbnormalityList().get(0));
                    mBinding.nsvContainer.scrollTo(0, adapterTaskItemList.getViewByPosition(mBinding.rvTaskItemList, count, R.id.lo_container).getHeight() * count);
                }
            }
        });
        tjDialogFragment.show(getSupportFragmentManager(), "cb");

    }

    /**
     * 最终提交
     */
    private void commitTotal() {

        if (CollectionsUtil.isEmpty(adapterTaskItemList.getLocalData())) {
            try {
                LoadingDialog.with(TaskDetailActivity.this).setMessage(ResUtils.getString(R.string.data_saving)).show();
            } catch (Exception e) {
            }
            String temp = RoutepointDataManager.getInstance().getRoutePointListString(id);
            if (taskBean.getIsProcess() != null && "1".equals(taskBean.getIsProcess())) {
                mPresenter.endExecute2(id, temp, false, ResUtils.getString(R.string.data_saving));
            } else {
                mPresenter.endExecute(id, temp, false, ResUtils.getString(R.string.data_saving));
            }
        } else {
            mPresenter.commitTotal(adapterTaskItemList.getLocalData());
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
//                Point point = (Point) GeometryEngine.project(point1, SpatialReferences.getWebMercator());
                Map<String, Object> attrs = new HashMap<>();
                attrs.put("taskItemBean", new Gson().toJson(taskItemBean));
                mBinding.alMapview.addPic(R.drawable.icon_taskitem_exe, point1, attrs);
                positionPoint = point1;
                exeLine.add(point1);
            }
            if (TextUtils.isEmpty(taskItemBean.getPositionLongitude()) || TextUtils.isEmpty(taskItemBean.getPositionLatitude())) {
            } else {
                double longitude = Double.parseDouble(taskItemBean.getPositionLongitude());
                double Latitude = Double.parseDouble(taskItemBean.getPositionLatitude());
                Point point1 = new Point(longitude, Latitude, SpatialReference.create(4326));
//                Point point = (Point) GeometryEngine.project(point1, SpatialReferences.getWebMercator());
                Map<String, Object> attrs = new HashMap<>();
                attrs.put("taskItemBean", new Gson().toJson(taskItemBean));
                positionPoint = point1;
                mBinding.alMapview.addPic(R.drawable.icon_taskitem_temp, point1, attrs);
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
                ArrayList<Point> exeline = new ArrayList();
                for (List<Double> bean : list) {
                    if (bean == null || bean.size() < 2) {
                        continue;
                    }
                    double longitude = bean.get(0);
                    double Latitude = bean.get(1);
                    Point point1 = new Point(longitude, Latitude, SpatialReference.create(4326));
//                    Point point = (Point) GeometryEngine.project(point1, SpatialReferences.getWebMercator());
                    exeline.add(point1);
                }

                mBinding.alMapview.addPolyline(exeline, SimpleLineSymbol.Style.SOLID, Color.RED, 6);
                if (exeline != null && exeline.size() >= 2) {
                    mBinding.alMapview.setMapViewVisibleExtent(exeline);
                    mBinding.alMapview.addPic(R.mipmap.ic_me_history_startpoint, exeline.get(0), new HashMap<>());
                    mBinding.alMapview.addPic(R.mipmap.ic_me_history_finishpoint, exeline.get(exeline.size() - 1), new HashMap<>());
                }
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
//                Point point = (Point) GeometryEngine.project(point1, SpatialReferences.getWebMercator());
                exeline2.add(point1);
            }
            try {
                mBinding.alMapview.setMapViewVisibleExtent(exeline2);
                mBinding.alMapview.addPolyline(exeline2, SimpleLineSymbol.Style.SOLID, Color.RED, 6);
                if (exeline2 != null && exeline2.size() >= 2) {
                    mBinding.alMapview.addPic(R.mipmap.ic_me_history_startpoint, exeline2.get(0));
                }
            } catch (Exception e) {
            }
        }
    }
}
